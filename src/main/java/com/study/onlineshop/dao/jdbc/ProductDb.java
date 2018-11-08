package com.study.onlineshop.dao.jdbc;

import com.study.onlineshop.dao.*;
import com.study.onlineshop.dao.jdbc.mapper.ProductRowMapper;
import com.study.onlineshop.entity.Product;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

//ProductDao
public class ProductDb implements ProductDao {
    private Connection connection;

    private static final String SELECT_ALL = "SELECT * FROM product";
    private static final String FETCH_RAW = "SELECT * FROM product t WHERE t.id = ?";

    private static final ProductRowMapper PRODUCT_ROW_MAPPER = new ProductRowMapper();

    public void setConnection(Connection connection) {
        this.connection = connection;
    }
    @Override
    public List<Product> getAll() {
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(SELECT_ALL)) {


            List<Product> products = new ArrayList<>();
            while (resultSet.next()) {
                Product product = PRODUCT_ROW_MAPPER.mapRow(resultSet);
                products.add(product);
            }

            return products;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Override
    public Product getUnique(int id) {
        try (PreparedStatement statement = connection.prepareStatement(FETCH_RAW)) {

            Product product = new Product();
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();

            resultSet.next();
            if (resultSet != null) {
                product = PRODUCT_ROW_MAPPER.mapRow(resultSet);
            }

            return product;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

}
