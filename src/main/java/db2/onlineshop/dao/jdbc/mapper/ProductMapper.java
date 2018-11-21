package db2.onlineshop.dao.jdbc.mapper;

import db2.onlineshop.entity.Product;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

public class ProductMapper implements RowMapper<Product> {
    @Override
    public Product mapRow(ResultSet resultSet, int i) throws SQLException {
        Product result = new Product();

        result.setId(resultSet.getInt("id"));
        result.setName(resultSet.getString("name"));
        result.setPrice(resultSet.getDouble("price"));
        Timestamp creationDate = resultSet.getTimestamp("creation_date");
        result.setCreationDate(creationDate.toLocalDateTime());

        return result;
    }
}
