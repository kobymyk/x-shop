package db2.onlineshop.service;

import db2.onlineshop.entity.User;

public interface UserService {
    User getUser(String login, String password);
}
