package db2.onlineshop.dao.jdbc.mapper;

import db2.onlineshop.entity.Product;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Map;

public class ProductMapper implements RowMapper {
    @Override
    public Product fromCursor(ResultSet cursor) throws SQLException {
        Product result = new Product();

        result.setId(cursor.getInt("id"));
        result.setName(cursor.getString("name"));
        result.setPrice(cursor.getDouble("price"));
        Timestamp creationDate = cursor.getTimestamp("creation_date");
        result.setCreationDate(creationDate.toLocalDateTime());

        return result;
    }
    @Override
    public Product fromParams(Map params) {
        Product result = new Product();
        result.setId(Integer.parseInt((String) params.get("id")));
        result.setName((String) params.get("name"));
        result.setPrice(Double.parseDouble((String) params.get("price")));

        return result;
    }
}
