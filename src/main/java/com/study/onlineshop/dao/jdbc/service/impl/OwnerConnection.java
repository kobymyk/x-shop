package com.study.onlineshop.dao.jdbc.service.impl;

import com.study.onlineshop.dao.jdbc.service.LocaleConnection;
import org.apache.commons.dbcp2.BasicDataSource;

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

    public BasicDataSource getDataSource()  {
        return ConnectionHolder.INSTANCE.dataSource;
    }

    private static class ConnectionHolder {
        private static OwnerConnection INSTANCE;
    }

}
