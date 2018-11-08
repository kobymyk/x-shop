package com.study.onlineshop.dao.jdbc.service.impl;

import com.study.onlineshop.dao.jdbc.service.LocaleConnection;
import org.apache.commons.dbcp2.BasicDataSource;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Locale;
import java.util.Properties;

public class OwnerConnection implements LocaleConnection {
    private BasicDataSource dataSource = new BasicDataSource();

    private static final int POOL_SIZE = 5;

    private OwnerConnection(Properties properties) {
        //dataSource.setDriverClassName(DRIVER_CLASS_NAME);
        dataSource.setUrl(properties.getProperty(KEY_URL));
        dataSource.setUsername(properties.getProperty(KEY_USER));
        dataSource.setPassword(properties.getProperty(KEY_PASSWORD));
        dataSource.setInitialSize(POOL_SIZE);
    }

    public static OwnerConnection getInstance(Properties properties) {
        if (ConnectionHolder.INSTANCE == null) {
            ConnectionHolder.INSTANCE = new OwnerConnection(properties);
        }
        return ConnectionHolder.INSTANCE;
    }

    public Connection getConnection() throws SQLException  {
        Locale.setDefault(DEFAULT_LOCALE);
        return ConnectionHolder.INSTANCE.dataSource.getConnection();
    }

    private static class ConnectionHolder {
        private static OwnerConnection INSTANCE;
    }

}
