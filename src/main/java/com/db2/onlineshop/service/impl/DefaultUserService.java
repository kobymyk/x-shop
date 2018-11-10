package com.db2.onlineshop.service.impl;

import com.db2.onlineshop.dao.UserDao;
import com.db2.onlineshop.service.UserService;
import com.db2.onlineshop.entity.User;

import java.math.BigInteger;
import java.security.MessageDigest;

public class DefaultUserService implements UserService {
    private UserDao userDb;

    public DefaultUserService(UserDao userDb) {
        this.userDb = userDb;
    }

    private String encrypt(String word) {
        MessageDigest crypt = null;
        try {
            crypt = MessageDigest.getInstance("SHA-1");
            crypt.reset();
            crypt.update(word.getBytes("utf8"));
            // converts byte[] to String
            return new BigInteger(1, crypt.digest()).toString(16);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Override
    public User getUser(String login, String password) {
        User user = userDb.getByName(login);
        if (user != null) {
            String cryptPassword = encrypt(password + user.getSole());

            if (user.getPassword().equals(cryptPassword)) {
                return user;
            }
        }
        return null;
    }
}
