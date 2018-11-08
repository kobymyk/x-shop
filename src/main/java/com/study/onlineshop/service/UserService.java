package com.study.onlineshop.service;

import com.study.onlineshop.entity.User;

public interface UserService {
    User getUser(String name, String password);
}
