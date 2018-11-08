package com.study.onlineshop.service;

import com.study.onlineshop.entity.Product;

import java.util.List;

public interface ProductService {
    List<Product> getAll();
    // scalar
    Product getScalar(int id);
}
