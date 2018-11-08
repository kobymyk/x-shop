package com.study.onlineshop.security;

import com.study.onlineshop.entity.User;
import com.study.onlineshop.service.UserService;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class SecurityService {
    private List<Session> sessionList = new ArrayList<>();

    private UserService userService;

    public SecurityService(UserService userService) {
        this.userService = userService;
    }

    public Session login(String name, String password) {
        // load from DB
        User user = userService.getUser(name, password);
        if (user != null) {
            Session session = new Session();
            session.setUser(user);
            session.setToken(UUID.randomUUID().toString());
            // token could be modified on client
            session.setExpireDate(LocalDateTime.now().plusHours(5));
            sessionList.add(session);
            return session;
        }

        return null;
    }

    public void logout(String token) {
        for (Session session : sessionList) {
            if (session.getToken().equals(token)) {
                sessionList.remove(session);
            }
        }
    }
    // Optional
    public Optional<Session> getSession(String token) {
        for (Session session : sessionList) {
            if (session.getToken().equals(token)) {
                // check if not expired
                if (session.getExpireDate().isBefore(LocalDateTime.now())) {
                    sessionList.remove(session);
                    return Optional.empty();
                }
                return Optional.of(session);
            }
        }
        return Optional.empty();
    }
}
