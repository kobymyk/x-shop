package com.db2.onlineshop.service;

import com.db2.onlineshop.entity.User;

public interface UserService {
    User getUser(String login, String password);
}
