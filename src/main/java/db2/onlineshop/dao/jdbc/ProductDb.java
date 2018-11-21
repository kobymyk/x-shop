package db2.onlineshop.dao.jdbc;

import db2.onlineshop.dao.jdbc.mapper.ProductMapper;
import db2.onlineshop.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Service;

@Service
public class ProductDb extends TemplateDb {
    private static final ProductMapper PRODUCT_MAPPER = new ProductMapper();

    @Autowired
    String sqlSelectProducts;
    @Autowired
    String sqlFetchProduct;
    @Autowired
    String dmlUpdateRow;
    @Autowired
    String dmlInsertRow;
    @Autowired
    String dmlDeleteRow;

    @Override
    Class getEntityClass() { return Product.class; }

    @Override
    RowMapper getRowMapper() { return PRODUCT_MAPPER; }

    @Override
    String getSqlSelectAll() { return sqlSelectProducts; }
    @Override
    String getSqlFetchRow() { return sqlFetchProduct; }
    @Override
    String getDmlUpdateRow() { return dmlUpdateRow; }
    @Override
    String getDmlInsertRow() { return dmlInsertRow; }
    @Override
    String getDmlDeleteRow() { return dmlDeleteRow; }

    @Override
    final MapSqlParameterSource prepareUpdate(Object version) {
        MapSqlParameterSource result = new MapSqlParameterSource();
        Product product = (Product) version;
        result.addValue("name", product.getName());
        result.addValue("id", product.getId());

        return result;
    }

    @Override
    MapSqlParameterSource prepareInsert(Object version) {
        Product product = (Product) version;
        MapSqlParameterSource result = new MapSqlParameterSource();
        result.addValue("name", product.getName());
        result.addValue("price", product.getPrice());

        return result;
    }

}
