package com.study.onlineshop.service.impl;

import com.study.onlineshop.dao.ProductDao;
import com.study.onlineshop.entity.Product;
import com.study.onlineshop.service.ProductService;

import java.util.HashMap;
import java.util.List;

public class DefaultProductService implements ProductService {
    private ProductDao productDao;

    public DefaultProductService(ProductDao productDao) {
        this.productDao = productDao;
    }

    @Override
    public List<Product> getAll() {
        return productDao.getAll();
    }

    @Override
    public void update(HashMap<String, String> parameters) {
        Product product = new Product();
        product.setId(Integer.parseInt(parameters.get("id")));
        product.setName(parameters.get("name"));

        productDao.update(product);
    }

    @Override
    public Product getScalar(int id) {
        return productDao.getUnique(id);
    }
}
