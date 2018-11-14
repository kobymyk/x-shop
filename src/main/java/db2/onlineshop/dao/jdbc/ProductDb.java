package db2.onlineshop.dao.jdbc;

import db2.onlineshop.dao.jdbc.mapper.ProductMapper;
import db2.onlineshop.entity.Product;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

//PersistTemplate impl
public class ProductDb extends PersistTemplate {
    //private DataSource dataSource;

    private static final ProductMapper PRODUCT_MAPPER = new ProductMapper();

    public ProductDb() {
        SELECT_ALL = "SELECT * FROM product";
        FETCH_ROW = "SELECT * FROM product t WHERE t.id = ?";
        INSERT_ROW = "INSERT INTO product(id, name, price, creation_date) values (seq_product.nextval, ?, ?, sysdate)";
        UPDATE_ROW = "UPDATE product t SET t.name = ? WHERE t.id = ?";
        DELETE_ROW = "DELETE product t WHERE t.id = ?";
    }

    public void setKey(Statement statement, Object key) {
        int id = Integer.parseInt((String) key);

        try {
            ((PreparedStatement) statement).setInt(1, id);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    final List<Object> fetchCursor(ResultSet cursor) throws SQLException {
        List<Object> result = new ArrayList<>();
        while (cursor.next()) {
            Product product = PRODUCT_MAPPER.fromCursor(cursor);
            result.add(product);
        }
        return result;
    }

    final void prepareUpdate(PreparedStatement statement, Object version) throws SQLException {
        Product product = (Product) version;
        statement.setString(1, product.getName());
        statement.setInt(2, product.getId());
    }

    final void prepareInsert(PreparedStatement statement, Object version) throws SQLException {
        Product product = (Product) version;
        statement.setString(1, product.getName());
        statement.setDouble(2, product.getPrice());
    }

}
