package db2.onlineshop.dao.jdbc.mapper;

import db2.onlineshop.entity.User;
import db2.onlineshop.entity.UserRole;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserRowMapper implements RowMapper {
    @Override
    public User mapRow(ResultSet resultSet) throws SQLException {
        User user = new User();

        user.setId(resultSet.getInt("id"));
        user.setLogin(resultSet.getString("login"));
        user.setPassword(resultSet.getString("password"));
        user.setUserRole(UserRole.valueOf(resultSet.getString("user_role")));
        user.setSole(resultSet.getString("sole"));

        return user;
    }
}
