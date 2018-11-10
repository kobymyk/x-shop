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
    public void update(HashMap<String, String> params) {
        Product product = new Product();
        product.setId(Integer.parseInt(params.get("id")));
        product.setName(params.get("name"));

        productDao.update(product);
    }

    @Override
    public Product getScalar(String paramName, String paramValue) {
        if ("id".equals(paramName)) {
            return productDao.getUnique(Integer.parseInt(paramValue));
        }
        throw new RuntimeException("Invalid paramName");
    }
}
