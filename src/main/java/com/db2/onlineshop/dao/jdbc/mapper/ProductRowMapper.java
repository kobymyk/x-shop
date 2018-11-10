package com.db2.onlineshop.dao.jdbc.mapper;

import com.db2.onlineshop.entity.Product;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

public class ProductRowMapper {
    // maps DB to obj
    public Product mapRow(ResultSet resultSet) throws SQLException {
        Product product = new Product();

        product.setId(resultSet.getInt("id"));
        product.setName(resultSet.getString("name"));
        product.setPrice(resultSet.getDouble("price"));
        Timestamp creationDate = resultSet.getTimestamp("creation_date");
        product.setCreationDate(creationDate.toLocalDateTime());

        return product;
    }
}
