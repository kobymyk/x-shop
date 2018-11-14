package db2.onlineshop.dao.jdbc.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

public interface RowMapper<T> {

    public T fromCursor(ResultSet cursor) throws SQLException;

    public T fromParams(Map<String, String> params);
}
