package com.study.onlineshop.dao.jdbc.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Locale;

public interface LocaleConnection {
    static final String KEY_URL = "jdbc.url";
    static final String KEY_USER = "jdbc.user";
    static final String KEY_PASSWORD = "jdbc.password";

    // XE limitation
    static final Locale DEFAULT_LOCALE = Locale.ENGLISH;

    Connection getConnection() throws SQLException;
}
