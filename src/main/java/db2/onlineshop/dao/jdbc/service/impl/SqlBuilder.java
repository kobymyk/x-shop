package db2.onlineshop.dao.jdbc.service.impl;

// Builder pattern
public class SqlBuilder {
    private String tableName = "";

    SqlBuilder setTable(String tableName) {
        this.tableName = tableName;
        return this;
    }

    Object build() {
        return null;
    }
}
