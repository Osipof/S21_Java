package edu.school21.chat.repositories;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

public class JdbcDataSource {
    private static final String PATH_TO_PROPERTIES = "ex01/Chat/src/main/resources/jdbc.properties";

    private final HikariDataSource dataSource;

    public JdbcDataSource() {
        try {
            FileInputStream fileInputStream = new FileInputStream(PATH_TO_PROPERTIES);
            Properties properties = new Properties();
            properties.load(fileInputStream);
            String DB_USERNAME = (String) properties.get("USERNAME");
            String DB_PASSWORD = (String) properties.get("PASSWORD");
            String DB_URL = (String) properties.get("URL");
            HikariConfig config = new HikariConfig();
            config.setJdbcUrl(DB_URL);
            config.setUsername(DB_USERNAME);
            config.setPassword(DB_PASSWORD);
            dataSource = new HikariDataSource(config);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public HikariDataSource getDataSource() {
        return dataSource;
    }

    public Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }
}
