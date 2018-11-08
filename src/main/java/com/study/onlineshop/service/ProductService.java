package com.study.onlineshop.service;

import com.study.onlineshop.entity.Product;

import java.util.HashMap;
import java.util.List;

public interface ProductService {
    List<Product> getAll();
    void update(HashMap<String, String> parameters);
    // scalar
    Product getScalar(int id);
}
