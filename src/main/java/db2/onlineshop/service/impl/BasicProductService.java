package db2.onlineshop.service.impl;

import db2.onlineshop.dao.jdbc.ProductDb;
import db2.onlineshop.dao.jdbc.mapper.ProductMapper;
import db2.onlineshop.entity.Product;
import db2.onlineshop.service.ProductService;

import java.util.List;
import java.util.Map;

public class BasicProductService implements ProductService {
    private static final ProductMapper PRODUCT_MAPPER = new ProductMapper();
    private ProductDb productDb;

    public BasicProductService(ProductDb productDb) {
        this.productDb = productDb;
    }

    @Override
    public List<Object> getItems() {
        return productDb.selectAll();
    }

    @Override
    public int updateItem(Map params) {
        Product product = PRODUCT_MAPPER.fromParams(params);

        return productDb.updateRow(product);
    }

    @Override
    public int addItem(Map params) {
        Product product = PRODUCT_MAPPER.fromParams(params);

        return productDb.insertRow(product);
    }

    @Override
    public Object getItem(String key) {
        return productDb.getUnique(key);
    }

    @Override
    public int removeItem(String key) {
        return productDb.deleteRow(key);
    }
}
