package com.db2.onlineshop.dao;

import com.db2.onlineshop.entity.User;

public interface UserDao extends Persistent {
    //List<User> getAll();

    User getByName(String name);
}
