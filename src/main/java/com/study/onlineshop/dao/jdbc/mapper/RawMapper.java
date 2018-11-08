package com.study.onlineshop.dao.jdbc.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface RawMapper<T> {
    public T mapRow(ResultSet resultSet) throws SQLException;
}
