package db2.onlineshop.dao.jdbc;

import db2.onlineshop.dao.jdbc.mapper.ProductMapper;
import db2.onlineshop.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Service
public class ProductDb extends TemplateDb {
    private static final ProductMapper PRODUCT_MAPPER = new ProductMapper();

    @Autowired
    String sqlSelectProducts;

    public ProductDb() {
        sqlFetchRow = "SELECT * FROM product t WHERE t.id = ?";
        dmlInsertRow = "INSERT INTO product(id, name, price, creation_date) values (seq_product.nextval, ?, ?, sysdate)";
        dmlUpdateRow = "UPDATE product t SET t.name = ? WHERE t.id = ?";
        dmlDeleteRow = "DELETE product t WHERE t.id = ?";
    }

    @Override
    protected String getSqlSelectAll() {
        return sqlSelectProducts;
    }

    @Override
    public void setKey(Statement statement, Object key) {
        int id = Integer.parseInt((String) key);

        try {
            ((PreparedStatement) statement).setInt(1, id);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Override
    final List<Product> fetchCursor(ResultSet cursor) throws SQLException {
        List<Product> result = new ArrayList<>();
        while (cursor.next()) {
            Product product = PRODUCT_MAPPER.fromCursor(cursor);
            result.add(product);
        }
        return result;
    }

    @Override
    final void prepareUpdate(PreparedStatement statement, Object version) throws SQLException {
        Product product = (Product) version;
        statement.setString(1, product.getName());
        statement.setInt(2, product.getId());
    }

    @Override
    final void prepareInsert(PreparedStatement statement, Object version) throws SQLException {
        Product product = (Product) version;
        statement.setString(1, product.getName());
        statement.setDouble(2, product.getPrice());
    }

}
