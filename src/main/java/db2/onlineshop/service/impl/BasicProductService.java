package db2.onlineshop.service.impl;

import db2.onlineshop.dao.ProductDao;
import db2.onlineshop.entity.Product;
import db2.onlineshop.service.ProductService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BasicProductService implements ProductService {
    private ProductDao productDao;

    public BasicProductService(ProductDao productDao) {
        this.productDao = productDao;
    }

    @Override
    public List<Product> getAll() {
        return productDao.selectAll();
    }

    @Override
    public void update(HashMap<String, String> params) {
        Product product = new Product();
        product.setId(Integer.parseInt(params.get("id")));
        product.setName(params.get("name"));

        productDao.updateRow(product);
    }

    @Override
    public void add(Map<String, String> params) {
        Product product = new Product();
        product.setName(params.get("name"));
        product.setPrice(Double.parseDouble(params.get("price")));

        productDao.insertRow(product);
    }

    @Override
    public Product getScalar(String paramName, String paramValue) {
        if ("id".equals(paramName)) {
            return productDao.getUnique(Integer.parseInt(paramValue));
        }
        throw new RuntimeException("Invalid paramName");
    }
}
