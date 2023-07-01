package edu.school21.chat.repositories;

import edu.school21.chat.exceptions.NotSavedSubEntityException;
import edu.school21.chat.models.Chatroom;
import edu.school21.chat.models.Message;
import edu.school21.chat.models.User;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Optional;

public class MessagesRepositoryJdbcImpl implements MessagesRepository {
    private final DataSource dataSource;

    public MessagesRepositoryJdbcImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public Optional<Message> findById(Long id) {
        String findByIdQuery = "SELECT * FROM chat.message WHERE id = " + id;

        try (Connection connection = dataSource.getConnection();
             Statement st = connection.createStatement()) {
            ResultSet resultSet = st.executeQuery(findByIdQuery);

            if (!resultSet.next()) {
                return Optional.empty();
            }
            Long userId = resultSet.getLong(2);
            Long roomId = resultSet.getLong(3);
            User user = findUser(userId);
            Chatroom room = findChat(roomId);
            return Optional.of(new Message(resultSet.getLong(1), user, room,
                    resultSet.getString(4), resultSet.getTimestamp(5).toLocalDateTime()));
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return Optional.empty();
    }

    @Override
    public boolean save(Message message) {

        String saveMessageQuery = "INSERT INTO chat.message (sender_id, room_id, text, ldatetime) VALUES (" +
                message.getAuthor().getId() + ", " +
                message.getRoom().getId() + ", " +
                "'" + message.getText() + "', " +
                "'" + message.getLocalDateTime() + "') RETURNING id;";

        try (Connection connection = dataSource.getConnection();
             Statement statement = connection.createStatement()) {

            if (findUser(message.getAuthor().getId()) == null) {
                throw new NotSavedSubEntityException("User with id = " + message.getAuthor().getId() + " does not exist");
            }
            if (findChat(message.getRoom().getId()) == null) {
                throw new NotSavedSubEntityException("Chat with id = " + message.getRoom().getId() + " does not exist");
            }

            ResultSet rs = statement.executeQuery(saveMessageQuery);
            if (!rs.next()) {
                throw new NotSavedSubEntityException("Initial error!");
            }

            message.setId(rs.getLong(1));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    private User findUser(Long id) throws SQLException {
        String findUserQuery = "SELECT * FROM chat.user WHERE id = " + id;

        try (Connection connection = dataSource.getConnection();
             Statement st = connection.createStatement()) {
            ResultSet resultSet = st.executeQuery(findUserQuery);
            if (!resultSet.next()) {
                return null;
            }
            return new User(id, resultSet.getString(2), resultSet.getString(3));
        }
    }

    private Chatroom findChat(Long id) throws SQLException {
        String findChatQuery = "SELECT * FROM chat.chatroom WHERE id = " + id;

        try (Connection connection = dataSource.getConnection();
             Statement st = connection.createStatement()) {
            ResultSet resultSet = st.executeQuery(findChatQuery);
            if (!resultSet.next()) {
                return null;
            }
            return new Chatroom(id, resultSet.getString(2));
        }
    }
}
