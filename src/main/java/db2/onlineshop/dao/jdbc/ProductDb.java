package db2.onlineshop.dao.jdbc;

import db2.onlineshop.dao.jdbc.mapper.ProductMapper;
import db2.onlineshop.entity.Product;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

//TemplateDb impl
public class ProductDb extends TemplateDb {
    //private DataSource dataSource;

    private static final ProductMapper PRODUCT_MAPPER = new ProductMapper();

    public ProductDb() {
        sqlSelectAll = "SELECT * FROM product";
        sqlFetchRow = "SELECT * FROM product t WHERE t.id = ?";
        dmlInsertRow = "INSERT INTO product(id, name, price, creation_date) values (seq_product.nextval, ?, ?, sysdate)";
        dmlUpdateRow = "UPDATE product t SET t.name = ? WHERE t.id = ?";
        dmlDeleteRow = "DELETE product t WHERE t.id = ?";
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

    final List<Product> fetchCursor(ResultSet cursor) throws SQLException {
        List<Product> result = new ArrayList<>();
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
