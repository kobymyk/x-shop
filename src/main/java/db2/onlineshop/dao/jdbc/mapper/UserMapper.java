package db2.onlineshop.dao.jdbc.mapper;

import db2.onlineshop.entity.User;
import db2.onlineshop.entity.UserRole;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

public class UserMapper implements RowMapper {
    @Override
    public User fromCursor(ResultSet cursor) throws SQLException {
        User user = new User();

        user.setId(cursor.getInt("id"));
        user.setLogin(cursor.getString("login"));
        user.setPassword(cursor.getString("password"));
        user.setUserRole(UserRole.valueOf(cursor.getString("user_role")));
        user.setSole(cursor.getString("sole"));

        return user;
    }

    @Override
    public Object fromParams(Map params) {
        return null;
    }

}
