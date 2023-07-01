package edu.school21.chat.app;

import edu.school21.chat.models.Message;
import edu.school21.chat.repositories.JdbcDataSource;
import edu.school21.chat.repositories.MessagesRepository;
import edu.school21.chat.repositories.MessagesRepositoryJdbcImpl;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Optional;
import java.util.Scanner;

public class Program {
    public static void main(String[] args) {

        JdbcDataSource dataSource = new JdbcDataSource();
        updateData("schema.sql", dataSource);
        updateData("data.sql", dataSource);

        MessagesRepository messagesRepository = new MessagesRepositoryJdbcImpl(dataSource.getDataSource());
        Optional<Message> messageOptional = messagesRepository.findById(2L);
        if (messageOptional.isPresent()) {
            Message message = messageOptional.get();
            message.setText(null);
            message.setLocalDateTime(null);
            messagesRepository.update(message);
            System.out.println(message);
        }
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
