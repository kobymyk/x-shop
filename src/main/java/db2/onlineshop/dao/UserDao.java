package db2.onlineshop.dao;

import db2.onlineshop.entity.User;

public interface UserDao {

    User getByName(String name);
}
