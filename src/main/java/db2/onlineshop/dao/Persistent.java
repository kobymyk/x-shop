package db2.onlineshop.dao;

import javax.sql.DataSource;
import java.util.List;

public interface Persistent<T> {
    List<T> selectAll();
    // return rows updated/inserted
    int updateRow(T version);
    int insertRow(T version);
    // not good
    void setDataSource(DataSource dataSource);
}
