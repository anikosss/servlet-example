package com.example.servlet.repository;

import com.example.servlet.entity.ProductEntity;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ProductRepository {


    private ProductRepository() {

    }

    public static class ProductRepositoryHolder {
        public static final ProductRepository INSTANCE = new ProductRepository();
    }

    public static ProductRepository getInstance() {
        return ProductRepositoryHolder.INSTANCE;
    }

    public List<ProductEntity> getProducts() {
        final List<ProductEntity> productEntities = new ArrayList<>();
        try(Connection connection =
                DriverManager.getConnection("jdbc:postgresql://localhost:5432/jdbc_lesson", "postgres", "root");
            Statement statement = connection.createStatement()) {
            final ResultSet resultSet = statement.executeQuery("SELECT * FROM products");
            while (resultSet.next()) {
                final long id = resultSet.getLong("id");
                final String title = resultSet.getString("title");
                final double price = resultSet.getDouble("price");
                productEntities.add(ProductEntity.builder().id(id).title(title).price(price).build());
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return productEntities;
    }

    public void addProduct(ProductEntity productEntity) {
//        products.add(productEntity);
    }
}
