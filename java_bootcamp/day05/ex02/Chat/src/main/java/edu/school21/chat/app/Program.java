package edu.school21.chat.app;

import edu.school21.chat.models.Chatroom;
import edu.school21.chat.models.Message;
import edu.school21.chat.models.User;
import edu.school21.chat.repositories.JdbcDataSource;
import edu.school21.chat.repositories.MessagesRepository;
import edu.school21.chat.repositories.MessagesRepositoryJdbcImpl;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Scanner;

public class Program {
    public static void main(String[] args) {

        JdbcDataSource dataSource = new JdbcDataSource();
        updateData("schema.sql", dataSource);
        updateData("data.sql", dataSource);

        User author = new User(6L, "user", "user", new ArrayList<>(), new ArrayList<>());
        Chatroom room = new Chatroom(5L, "room", author, new ArrayList<>());
        Message message = new Message(null, author, room, "Test message!", LocalDateTime.now());
        MessagesRepository repository = new MessagesRepositoryJdbcImpl(dataSource.getDataSource());
        repository.save(message);
        System.out.println(message.getId());
    }

    private static void updateData(String file, JdbcDataSource dataSource) {
        try (Connection connection = dataSource.getConnection();
             Statement statement = connection.createStatement()) {

            InputStream inputStream = Program.class.getClassLoader().getResourceAsStream(file);
            assert inputStream != null;
            Scanner scanner = new Scanner(inputStream).useDelimiter(";");

            while (scanner.hasNext()) {
                statement.executeUpdate(scanner.next().trim());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
