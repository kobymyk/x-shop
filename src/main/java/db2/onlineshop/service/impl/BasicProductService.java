package db2.onlineshop.service.impl;

import db2.onlineshop.dao.ProductDao;
import db2.onlineshop.dao.jdbc.mapper.ProductMapper;
import db2.onlineshop.entity.Product;
import db2.onlineshop.service.ProductService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BasicProductService implements ProductService {
    private static final ProductMapper PRODUCT_MAPPER = new ProductMapper();
    private ProductDao productDao;

    public BasicProductService(ProductDao productDao) {
        this.productDao = productDao;
    }

    @Override
    public List<Product> getItems() {
        return productDao.selectAll();
    }

    @Override
    public int updateItem(Map<String, String> params) {
        Product product = PRODUCT_MAPPER.fromParams(params);

        return productDao.updateRow(product);
    }

    @Override
    public int addItem(Map<String, String> params) {
        Product product = PRODUCT_MAPPER.fromParams(params);

        return productDao.insertRow(product);
    }

    @Override
    public Product getItem(String paramName, String paramValue) {
        if ("id".equals(paramName)) {
            return productDao.getUnique(Integer.parseInt(paramValue));
        }
        throw new RuntimeException("Invalid paramName");
    }
}
