package db2.onlineshop.dao.jdbc;

import db2.onlineshop.dao.jdbc.mapper.ProductMapper;
import db2.onlineshop.entity.Product;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Repository;

@Repository
public class ProductDb extends TemplateDb<Product> {
    private static final ProductMapper PRODUCT_MAPPER = new ProductMapper();

    public ProductDb() {
        super();
        this.rowMapper = PRODUCT_MAPPER;
        this.sqlFetch = "SELECT t.* FROM product t WHERE t.id = ?";
        this.sqlSelect = "SELECT t.* FROM product t";
        this.sqlUpdate = "UPDATE product t SET t.name = :name WHERE t.id = :id";
        this.sqlDelete = "DELETE product t WHERE t.id = ?";
        this.sqlInsert = "INSERT INTO product(id, name, price, creation_date) values (seq_product.nextval, :name, :price, sysdate)";
    }

    @Override
    final MapSqlParameterSource prepareUpdate(Product version) {
        MapSqlParameterSource result = new MapSqlParameterSource();
        result.addValue("name", version.getName());
        result.addValue("id", version.getId());

        return result;
    }

    @Override
    MapSqlParameterSource prepareInsert(Product version) {
        MapSqlParameterSource result = new MapSqlParameterSource();
        result.addValue("name", version.getName());
        result.addValue("price", version.getPrice());

        return result;
    }

}
