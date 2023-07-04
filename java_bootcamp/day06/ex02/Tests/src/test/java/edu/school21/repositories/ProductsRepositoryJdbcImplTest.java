package edu.school21.repositories;

import edu.school21.models.Product;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

class ProductsRepositoryJdbcImplTest {
    ProductsRepository productsRepository;
    EmbeddedDatabase embeddedDatabase;

    final List<Product> EXPECTED_FIND_ALL_PRODUCTS = Arrays.asList(
            new Product(1L, "iphone", 65000),
            new Product(2L, "microwave", 8000),
            new Product(3L, "cap", 300),
            new Product(4L, "Toyota Camry", 3000000),
            new Product(5L, "tomato", 20),
            new Product(6L, "cucumber", 30)
    );
    final Product EXPECTED_FIND_BY_ID_PRODUCT = new Product(2L, "microwave", 8000);
    final Product EXPECTED_UPDATED_PRODUCT = new Product(4L, "nothing", 10);
    final Product EXPECTED_SAVED_PRODUCT = new Product(7L, "LG TV", 99999);

    @BeforeEach
    void init() {
        embeddedDatabase = new EmbeddedDatabaseBuilder()
                .setType(EmbeddedDatabaseType.HSQL)
                .addScript("schema.sql")
                .addScript("data.sql")
                .build();
        productsRepository = new ProductsRepositoryJdbcImpl(embeddedDatabase);
    }

    @Test
    void findAllTest() throws SQLException {
        Assertions.assertEquals(EXPECTED_FIND_ALL_PRODUCTS, productsRepository.findAll());
    }

    @Test
    void findByIdTest() throws SQLException {
        Assertions.assertEquals(EXPECTED_FIND_BY_ID_PRODUCT, productsRepository.findById(2L).get());
    }

    @Test
    void updateTest() throws SQLException {
        productsRepository.update(new Product(4L, "nothing", 10));
        Assertions.assertEquals(EXPECTED_UPDATED_PRODUCT, productsRepository.findById(4L).get());
    }

    @Test
    void saveTest() throws SQLException {
        productsRepository.save(new Product(7L, "LG TV", 99999));
        Assertions.assertEquals(EXPECTED_SAVED_PRODUCT, productsRepository.findById(7L).get());
    }

    @Test
    void deleteTest() throws SQLException {
        productsRepository.delete(3L);
        Assertions.assertEquals(Optional.empty(), productsRepository.findById(3L));
    }
}