package com.study.onlineshop.dao;

import com.study.onlineshop.entity.Product;
import java.util.List;

public interface ProductDao {

    List<Product> getAll();

    Product getUnique(int id);
}
