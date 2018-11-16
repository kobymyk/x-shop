package db2.onlineshop.service;

import db2.onlineshop.entity.Product;

import java.util.List;
import java.util.Map;

public interface ProductService {
    List<Product> getItems();

    int updateItem(Map<String, String> params);

    int addItem(Map<String, String> params);

    Product getItem(String key);

    int removeItem(String key);
}
