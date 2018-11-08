package com.study.onlineshop.dao;

import com.study.onlineshop.entity.User;

import java.util.List;

public interface UserDao extends Persistent {
    //List<User> getAll();

    User getByName(String name);
}
