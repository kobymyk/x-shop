package com.study.onlineshop.dao;

import javax.sql.DataSource;
import java.util.List;

public interface Persistent<T> {
    List<T> getAll();

    void setDataSource(DataSource dataSource);
}
