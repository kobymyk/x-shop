package db2.onlineshop.service.impl;

import db2.onlineshop.dao.jdbc.ProductDao;
import db2.onlineshop.dao.jdbc.mapper.ProductMapper;
import db2.onlineshop.entity.Product;
import db2.onlineshop.service.ProductService;

import java.util.List;
import java.util.Map;

public class BasicProductService implements ProductService {
    private static final ProductMapper PRODUCT_MAPPER = new ProductMapper();
    private ProductDao productDao;

    public BasicProductService(ProductDao productDao) {
        this.productDao = productDao;
    }

    @Override
    public List<Object> getItems() {
        return productDao.selectAll();
    }

    @Override
    public int updateItem(Map params) {
        Product product = PRODUCT_MAPPER.fromParams(params);

        return productDao.updateRow(product);
    }

    @Override
    public int addItem(Map params) {
        Product product = PRODUCT_MAPPER.fromParams(params);

        return productDao.insertRow(product);
    }

    @Override
    public Object getItem(String key) {
        return productDao.getUnique(key);
    }

    @Override
    public int removeItem(String key) {
        return productDao.deleteRow(key);
    }
}
