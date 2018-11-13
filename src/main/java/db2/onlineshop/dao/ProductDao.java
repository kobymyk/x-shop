package db2.onlineshop.dao;

import db2.onlineshop.entity.Product;

public interface ProductDao extends Persistent {

    //List<Product> getAll();

    Product getUnique(int id);
}
