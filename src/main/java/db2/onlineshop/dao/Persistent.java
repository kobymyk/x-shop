package db2.onlineshop.dao;

import java.util.List;

public interface Persistent<T> {
    List<T> selectAll();

    T fetchRow(Object key);

    int updateRow(T version);

    int insertRow(T version);

    int deleteRow(Object key);
}
