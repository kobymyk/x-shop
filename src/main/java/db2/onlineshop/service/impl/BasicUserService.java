package db2.onlineshop.service.impl;

import db2.onlineshop.dao.UserDao;
import db2.onlineshop.service.UserService;
import db2.onlineshop.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.security.MessageDigest;

@Service
public class BasicUserService implements UserService {
    @Autowired
    private UserDao userDao;

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
        User user = userDao.getByName(login);
        if (user != null) {
            String cryptPassword = encrypt(password + user.getSole());

            if (user.getPassword().equals(cryptPassword)) {
                return user;
            }
        }
        return null;
    }
}
