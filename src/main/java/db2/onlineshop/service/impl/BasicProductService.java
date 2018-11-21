package db2.onlineshop.service.impl;

import db2.onlineshop.dao.jdbc.ProductDb;
import db2.onlineshop.entity.Product;
import db2.onlineshop.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class BasicProductService implements ProductService {
    @Autowired
    private ProductDb productDb;

    public BasicProductService(ProductDb productDb) {
        this.productDb = productDb;
    }

    @Override
    public List<Product> getItems() {
        return productDb.selectAll();
    }

    @Override
    public int updateItem(Map params) {
        Product product = fromParams(params);

        return productDb.updateRow(product);
    }

    @Override
    public int addItem(Map params) {
        Product product = fromParams(params);

        return productDb.insertRow(product);
    }

    @Override
    public Product getItem(String key) {
        return (Product) productDb.fetchRow(key);
    }

    @Override
    public int removeItem(String key) {
        return productDb.deleteRow(key);
    }

    private Product fromParams(Map params) {
        Product result = new Product();
        result.setId(Integer.parseInt((String) params.get("id")));
        result.setName((String) params.get("name"));
        result.setPrice(Double.parseDouble((String) params.get("price")));

        return result;
    }
}
