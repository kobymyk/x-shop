package db2.onlineshop.service;

import db2.onlineshop.entity.Product;

import java.util.List;
import java.util.Map;

public interface ProductService {

    List<Product> get();

    Product get(int id);

    int update(Product product);

    int add(Product product);

    int delete(int id);
}
