package com.db2.onlineshop.service;

import com.db2.onlineshop.entity.Product;

import java.util.HashMap;
import java.util.List;

public interface ProductService {
    List<Product> getAll();
    void update(HashMap<String, String> params);
    // scalar
    Product getScalar(String paramName, String paramValue);
}
