package db2.onlineshop.service;

import java.util.List;
import java.util.Map;

public interface ProductService<T> {
    List<T> getItems();

    int updateItem(Map<String, String> params);

    int addItem(Map<String, String> params);

    T getItem(String key);

    int removeItem(String key);
}
