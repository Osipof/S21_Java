package edu.school21.repositories;

import edu.school21.models.Product;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ProductsRepositoryJdbcImpl implements ProductsRepository {

    private final DataSource dataSource;

    public ProductsRepositoryJdbcImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public List<Product> findAll() throws SQLException {
        List<Product> products = new ArrayList<>();
        ResultSet resultSet = getStatement().executeQuery("SELECT * FROM tests.product;");
        while (resultSet.next()) {
            products.add(Product.builder()
                    .id(resultSet.getLong(1))
                    .name(resultSet.getString(2))
                    .price(resultSet.getInt(3))
                    .build());
        }
        return products;
    }

    @Override
    public Optional<Product> findById(Long id) throws SQLException {
        ResultSet resultSet = getStatement().executeQuery("SELECT * FROM tests.product WHERE identifier = " + id + ";");
        if (!resultSet.next()) {
            return Optional.empty();
        }
        return Optional.of(Product.builder()
                .id(resultSet.getLong(1))
                .name(resultSet.getString(2))
                .price(resultSet.getInt(3))
                .build());
    }

    @Override
    public void update(Product product) throws SQLException {
        getStatement().executeUpdate("UPDATE tests.product SET " +
                "name = '" + product.getName() + "', " +
                "price = " + product.getPrice() +
                " WHERE identifier = " + product.getId() + ";");
    }

    @Override
    public void save(Product product) throws SQLException {
        getStatement().executeQuery("INSERT INTO tests.product VALUES (" +
                product.getId() + ", '" +
                product.getName() + "', " +
                product.getPrice() + ");");
    }

    @Override
    public void delete(Long id) throws SQLException {
        getStatement().executeUpdate("DELETE FROM tests.product WHERE identifier = " + id + ";");
    }

    private Statement getStatement() throws SQLException {
        Connection connection = dataSource.getConnection();
        return connection.createStatement();
    }
}
