package school21.spring.service.repositories;

import school21.spring.service.models.User;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UsersRepositoryJdbcImpl implements UsersRepository {
    private final DataSource dataSource;
    public UsersRepositoryJdbcImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public User findById(Long id) {
        try (Connection connection = dataSource.getConnection();
             Statement statement = connection.createStatement()) {
            String findByIdQuery = "SELECT * FROM spring.user WHERE id = ";
            ResultSet resultSet = statement.executeQuery(findByIdQuery + id);
            if (!resultSet.next()) {
                return null;
            }
            return User.builder()
                    .id(resultSet.getLong(1))
                    .email(resultSet.getString(2))
                    .build();
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return null;
    }

    @Override
    public List<User> findAll() {
        List<User> users = new ArrayList<>();
        try (Connection connection = dataSource.getConnection();
             Statement statement = connection.createStatement()) {
            String findAllQuery = "SELECT * FROM spring.user";
            ResultSet resultSet = statement.executeQuery(findAllQuery);
            while (resultSet.next()) {
                users.add(User.builder()
                        .id(resultSet.getLong(1))
                        .email(resultSet.getString(2))
                        .build());
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return users.isEmpty() ? null : users;
    }

    @Override
    public void save(User entity) {
        try (Connection connection = dataSource.getConnection();
             Statement statement = connection.createStatement()) {
            String saveQuery = "INSERT INTO spring.user (id, email) VALUES (" +
                    entity.getId() + ", '" +
                    entity.getEmail() + "');";
            int result = statement.executeUpdate(saveQuery);
            if (result == 0) {
                System.err.println("User wasn't saved with id = " + entity.getId());
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }

    @Override
    public void update(User entity) {
        String updateQuery = "UPDATE spring.user SET email = ? WHERE id = ?";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(updateQuery)) {
            statement.setString(1, entity.getEmail());
            statement.setLong(2, entity.getId());
            int result = statement.executeUpdate();
            if (result == 0) {
                System.err.println("User wasn't updated with id = " + entity.getId());
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }

    @Override
    public void delete(Long id) {
        String deleteQuery = "DELETE FROM spring.user WHERE id = ?";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(deleteQuery)) {
            statement.setLong(1, id);
            int result = statement.executeUpdate();
            if (result == 0) {
                System.err.println("User not found with id = " + id);
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }

    @Override
    public Optional<User> findByEmail(String email) {
        String findByEmailQuery = "SELECT * FROM spring.user WHERE email = ?";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(findByEmailQuery)) {
            statement.setString(1, email);
            ResultSet resultSet = statement.executeQuery();
            if (!resultSet.next()) {
                return Optional.empty();
            }
            return Optional.of(User.builder()
                    .id(resultSet.getLong(1))
                    .email(resultSet.getString(2))
                    .build());
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return Optional.empty();
    }
}
