package com.study.onlineshop.dao.jdbc.service;

import javax.sql.DataSource;

public interface LocaleConnection {
    static final String KEY_URL = "jdbc.url";
    static final String KEY_USER = "jdbc.user";
    static final String KEY_PASSWORD = "jdbc.password";

    DataSource getDataSource();
}
