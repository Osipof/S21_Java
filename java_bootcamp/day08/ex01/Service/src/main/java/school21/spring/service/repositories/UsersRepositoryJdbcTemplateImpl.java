package school21.spring.service.repositories;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import school21.spring.service.models.User;

import javax.sql.DataSource;
import java.util.List;
import java.util.Optional;

public class UsersRepositoryJdbcTemplateImpl implements UsersRepository {
    private NamedParameterJdbcTemplate jdbcTemplate;

    public UsersRepositoryJdbcTemplateImpl(DataSource dataSource) {
        this.jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }

    @Override
    public User findById(Long id) {
        String findByIdQuery = "SELECT * FROM spring.user WHERE id = :id";
        return jdbcTemplate.query(findByIdQuery,
                new MapSqlParameterSource().addValue("id", id),
                new BeanPropertyRowMapper<>(User.class)).stream().findAny().orElse(null);
    }

    @Override
    public List<User> findAll() {
        String findAllQuery = "SELECT * FROM spring.user";
        List<User> users = jdbcTemplate.query(findAllQuery,
                new BeanPropertyRowMapper<>(User.class));
        return users.isEmpty() ? null : users;
    }

    @Override
    public void save(User entity) {
        String saveQuery = "INSERT INTO spring.user (id, email) VALUES (:id, :email)";
        if (jdbcTemplate.update(saveQuery, new MapSqlParameterSource()
                .addValue("id", entity.getId())
                .addValue("email", entity.getEmail())) == 0) {
            System.err.println("User wasn't saved with id = " + entity.getId());
        }
    }

    @Override
    public void update(User entity) {
        String updateQuery = "UPDATE spring.user SET email = :email WHERE id = :id";
        if (jdbcTemplate.update(updateQuery, new MapSqlParameterSource()
                .addValue("id", entity.getId())
                .addValue("email", entity.getEmail())) == 0) {
            System.err.println("User wasn't updated with id = " + entity.getId());
        }
    }

    @Override
    public void delete(Long id) {
        String updateQuery = "DELETE FROM spring.user WHERE id = :id";
        if (jdbcTemplate.update(updateQuery, new MapSqlParameterSource()
                .addValue("id", id)) == 0) {
            System.err.println("User not found with id = " + id);
        }
    }

    @Override
    public Optional<User> findByEmail(String email) {
        String findByEmailQuery = "SELECT * FROM spring.user WHERE email = :email";
        User user = jdbcTemplate.query(findByEmailQuery,
                new MapSqlParameterSource().addValue("email", email),
                new BeanPropertyRowMapper<>(User.class)).stream().findAny().orElse(null);
        return Optional.ofNullable(user);
    }
}
