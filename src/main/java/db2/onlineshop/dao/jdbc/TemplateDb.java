package db2.onlineshop.dao.jdbc;

import db2.onlineshop.dao.Persistent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;


import javax.sql.DataSource;
import java.util.List;
import java.util.Locale;

@Repository
public abstract class TemplateDb<T> implements Persistent<T> {
    private final Logger log = LoggerFactory.getLogger(getClass());

    private DataSource dataSource;
    private JdbcTemplate operation;
    private NamedParameterJdbcTemplate namedOperation;

    protected Class entityClass;
    protected RowMapper rowMapper;

    protected String sqlSelect;
    protected String sqlFetch;
    protected String sqlUpdate;
    protected String sqlInsert;
    protected String sqlDelete;

    public TemplateDb() {
        // XE limitation
        Locale.setDefault(Locale.ENGLISH);
    }

    abstract MapSqlParameterSource prepareUpdate(T version);
    abstract MapSqlParameterSource prepareInsert(T version);

    @Override
    public final T fetchRow(Object key) {
        log.info("fetchRow:key={}", key);
        T result = (T) operation.queryForObject(sqlFetch, new Object[]{key}, rowMapper);
        log.info("fetchRow:end");

        return result;
    }

    @Override
    public List<T> selectAll() {
        log.info("selectAll::start");
        List<T> result  = operation.query(sqlSelect, rowMapper);
        log.info("selectAll::end");

        return result;
    }

    @Override
    public int updateRow(T version) {
        log.info("updateRow::start");
        int result = namedOperation.update(sqlUpdate, prepareUpdate(version));
        log.info("updateRow::end");

        return result;
    }

    @Override
    public int insertRow(T version) {
        log.info("insertRow::start");
        int result = namedOperation.update(sqlInsert, prepareInsert(version));
        log.info("insertRow::end");

        return result;
    }

    @Override
    public int deleteRow(Object key) {
        log.info("deleteRow:id={}", key);
        int result = operation.update(sqlDelete, new Object[]{key});
        log.info("deleteRow:result={}", result);

        return result;
    }

    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
        this.operation = new JdbcTemplate(dataSource);
        this.namedOperation = new NamedParameterJdbcTemplate(dataSource);
    }
}
