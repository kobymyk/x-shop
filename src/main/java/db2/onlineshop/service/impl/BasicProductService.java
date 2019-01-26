package db2.onlineshop.service.impl;

import db2.onlineshop.dao.jdbc.ProductDb;
import db2.onlineshop.entity.Product;
import db2.onlineshop.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BasicProductService implements ProductService {
    @Autowired
    private ProductDb productDb;

    public BasicProductService(ProductDb productDb) {
        this.productDb = productDb;
    }

    @Override
    public List<Product> get() {
        return productDb.selectAll();
    }

    @Override
    public int update(Product product) {
        return productDb.updateRow(product);
    }

    @Override
    public int add(Product product) {
        return productDb.insertRow(product);
    }

    @Override
    public Product get(int id) {
        return (Product) productDb.fetchRow(id);
    }

    @Override
    public int delete(int id) {
        return productDb.deleteRow(id);
    }
}
