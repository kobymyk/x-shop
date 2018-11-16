package db2.onlineshop.dao.jdbc;

import db2.onlineshop.dao.Persistent;

import javax.sql.DataSource;
import java.sql.*;
import java.util.List;
import java.util.Locale;

public abstract class TemplateDb<T, K> implements Persistent<T, K> {
    DataSource dataSource;

    String sqlSelectAll;
    String sqlFetchRow;
    String dmlInsertRow;
    String dmlUpdateRow;
    String dmlDeleteRow;

    // unique key: int | String
    abstract void setKey(Statement statement, K key);

    public void setDataSource(DataSource dataSource) {
        Locale.setDefault(Locale.ENGLISH); // XE limitation
        this.dataSource = dataSource;
    }

    abstract void prepareUpdate(PreparedStatement statement, T version) throws SQLException;
    abstract void prepareInsert(PreparedStatement statement, T version) throws SQLException;

    abstract List<T> fetchCursor(ResultSet cursor) throws SQLException;

    public final T getUnique(K key) {
        try (
                Connection connection = dataSource.getConnection();
                PreparedStatement statement = connection.prepareStatement(sqlFetchRow)) {
            //setKey
            setKey(statement, key);

            ResultSet cursor = statement.executeQuery();
            T result = fetchCursor(cursor).get(0);

            return result;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public int deleteRow(K key) {
        try (
                Connection connection = dataSource.getConnection();
                PreparedStatement statement = connection.prepareStatement(dmlDeleteRow)) {

            setKey(statement, key);

            return statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public List<T> selectAll() {
        try (
                Connection connection = dataSource.getConnection();
                Statement sqlSelect = connection.createStatement();
                ResultSet cursor = sqlSelect.executeQuery(sqlSelectAll)) {

            List<T> result = fetchCursor(cursor);

            return result;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public int updateRow(T version) {
        try (
                Connection connection = dataSource.getConnection();
                PreparedStatement statement = connection.prepareStatement(dmlUpdateRow)) {

            prepareUpdate(statement, version);

            return statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public int insertRow(T version) {
        try (
                Connection connection = dataSource.getConnection();
                PreparedStatement statement = connection.prepareStatement(dmlInsertRow)) {

            prepareInsert(statement, version);

            return statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
