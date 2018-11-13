package db2.onlineshop.dao;

import javax.sql.DataSource;
import java.util.List;

public interface Persistent<T> {
    List<T> getAll();
    // return rows updated
    int update(T version);

    void setDataSource(DataSource dataSource);
}
