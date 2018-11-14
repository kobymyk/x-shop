package db2.onlineshop.dao.jdbc;

import db2.onlineshop.dao.Persistent;

import javax.sql.DataSource;
import java.sql.*;
import java.util.List;
import java.util.Locale;

public abstract class PersistTemplate implements Persistent {
    DataSource dataSource;

    String SELECT_ALL;
    String FETCH_ROW;
    String INSERT_ROW;
    String UPDATE_ROW;
    String DELETE_ROW;

    // unique key: id | name
    abstract void setKey(Statement statement, Object key);

    public void setDataSource(DataSource dataSource) {
        Locale.setDefault(Locale.ENGLISH); // XE limitation
        this.dataSource = dataSource;
    }

    abstract void prepareUpdate(PreparedStatement statement, Object version) throws SQLException;
    abstract void prepareInsert(PreparedStatement statement, Object version) throws SQLException;

    abstract List<Object> fetchCursor(ResultSet cursor) throws SQLException;

    public final Object getUnique(Object key) {
        try (
                Connection connection = dataSource.getConnection();
                PreparedStatement statement = connection.prepareStatement(FETCH_ROW)) {
            //setKey
            setKey(statement, key);

            ResultSet cursor = statement.executeQuery();
            Object result = fetchCursor(cursor).get(0);

            return result;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public int deleteRow(Object key) {
        try (
                Connection connection = dataSource.getConnection();
                PreparedStatement statement = connection.prepareStatement(DELETE_ROW)) {

            setKey(statement, key);

            return statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public List<Object> selectAll() {
        try (
                Connection connection = dataSource.getConnection();
                Statement sqlSelect = connection.createStatement();
                ResultSet cursor = sqlSelect.executeQuery(SELECT_ALL)) {

            List<Object> result = fetchCursor(cursor);

            return result;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public int updateRow(Object version) {
        try (
                Connection connection = dataSource.getConnection();
                PreparedStatement statement = connection.prepareStatement(UPDATE_ROW)) {

            prepareUpdate(statement, version);

            return statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public int insertRow(Object version) {
        try (
                Connection connection = dataSource.getConnection();
                PreparedStatement statement = connection.prepareStatement(INSERT_ROW)) {

            prepareInsert(statement, version);

            return statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
