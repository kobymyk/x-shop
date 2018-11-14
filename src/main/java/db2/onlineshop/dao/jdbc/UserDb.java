package db2.onlineshop.dao.jdbc;

import db2.onlineshop.dao.UserDao;
import db2.onlineshop.dao.jdbc.mapper.UserRowMapper;
import db2.onlineshop.entity.User;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class UserDb implements UserDao/*, Persistent*/ {
    private DataSource dataSource;

    private static final String SELECT_ALL = "SELECT * FROM users";
    private static final String FETCH_BY_LOGIN = "SELECT * FROM users t where t.login = ?";

    private static final UserRowMapper USER_ROW_MAPPER = new UserRowMapper();

    @Override
    public List<User> selectAll() {
        try (Connection connection = dataSource.getConnection();
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(SELECT_ALL)) {

            List<User> users = new ArrayList<>();
            while (resultSet.next()) {
                User user = USER_ROW_MAPPER.mapRow(resultSet);
                users.add(user);
            }

            return users;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Override
    public int updateRow(Object version) {
        return 0;
    }

    @Override
    public int insertRow(Object version) {
        return 0;
    }

    @Override
    public void setDataSource(DataSource dataSource) {
        Locale.setDefault(Locale.ENGLISH); // XE limitation
        this.dataSource = dataSource;
    }

    @Override
    public User getByName(String name) {
        try (Connection connection = dataSource.getConnection();
                PreparedStatement statement = connection.prepareStatement(FETCH_BY_LOGIN)) {

            User result = null;
            statement.setString(1, name);
            ResultSet resultSet = statement.executeQuery();
            resultSet.next();
            if (resultSet != null) {
                result = USER_ROW_MAPPER.mapRow(resultSet);
            }

            return result;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
