package db2.onlineshop.dao.jdbc;

import db2.onlineshop.dao.UserDao;
import db2.onlineshop.dao.jdbc.mapper.impl.UserMapper;
import db2.onlineshop.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class UserDb implements UserDao {
    @Autowired
    private DataSource dataSource;

    private static final String SELECT_ALL = "SELECT * FROM users";
    private static final String FETCH_BY_LOGIN = "SELECT * FROM users t where t.login = ?";

    private static final UserMapper USER_ROW_MAPPER = new UserMapper();

    //@Override
    public List<User> selectAll() {
        try (Connection connection = dataSource.getConnection();
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(SELECT_ALL)) {

            List<User> users = new ArrayList<>();
            while (resultSet.next()) {
                User user = USER_ROW_MAPPER.fromCursor(resultSet);
                users.add(user);
            }

            return users;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
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
                result = USER_ROW_MAPPER.fromCursor(resultSet);
            }

            return result;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
