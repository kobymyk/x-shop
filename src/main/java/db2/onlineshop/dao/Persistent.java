package db2.onlineshop.dao;

import javax.sql.DataSource;
import java.util.List;

public interface Persistent<T, K> {
    List<T> selectAll();
    // return rows updated/inserted
    int updateRow(T version);
    int insertRow(T version);

    int deleteRow(K key);
}
