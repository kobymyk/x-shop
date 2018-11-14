package db2.onlineshop.dao.jdbc;

import db2.onlineshop.dao.ProductDao;
import db2.onlineshop.dao.jdbc.mapper.ProductMapper;
import db2.onlineshop.entity.Product;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

//ProductDao impl
public class ProductDb implements ProductDao {
    private DataSource dataSource;

    private static final String SELECT_ALL = "SELECT * FROM product";
    private static final String FETCH_ROW = "SELECT * FROM product t WHERE t.id = ?";
    private static final String INSERT_ROW = "INSERT INTO product(id, name, price, creation_date) values (seq_product.nextval, ?, ?, sysdate)";
    private static final String UPDATE_ROW = "UPDATE product t SET t.name = ? WHERE t.id = ?";

    private static final ProductMapper PRODUCT_MAPPER = new ProductMapper();

    @Override
    public List<Product> selectAll() {
        try (Connection connection = dataSource.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(SELECT_ALL)) {


            List<Product> products = new ArrayList<>();
            while (resultSet.next()) {
                Product product = PRODUCT_MAPPER.fromCursor(resultSet);
                products.add(product);
            }

            return products;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Override
    public void setDataSource(DataSource dataSource) {
        Locale.setDefault(Locale.ENGLISH); // XE limitation
        this.dataSource = dataSource;
    }

    @Override
    public Product getUnique(int id) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(FETCH_ROW)) {

            Product product = new Product();
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();

            resultSet.next();
            if (resultSet != null) {
                product = PRODUCT_MAPPER.fromCursor(resultSet);
            }

            return product;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Override
    public int updateRow(Object version) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE_ROW)) {

            Product product = (Product) version;
            statement.setString(1, product.getName());
            statement.setInt(2, product.getId());

            return statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Override
    public int insertRow(Object version) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(INSERT_ROW)) {

            Product product = (Product) version;
            statement.setString(1, product.getName());
            statement.setDouble(2, product.getPrice());

            return statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

}
