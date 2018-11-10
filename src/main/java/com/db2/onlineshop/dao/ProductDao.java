package com.db2.onlineshop.dao;

import com.db2.onlineshop.entity.Product;

public interface ProductDao extends Persistent {

    //List<Product> getAll();

    Product getUnique(int id);
}
