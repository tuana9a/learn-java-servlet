package com.tuana9a.learn.java.servlet.dao;

import com.tuana9a.learn.java.servlet.configs.AppConfig;
import com.tuana9a.learn.java.servlet.database.JdbcMySQLClient;
import com.tuana9a.learn.java.servlet.utils.Utils;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public abstract class BaseDao<T> {
    protected String table;
    protected Class<T> _class;

    public BaseDao(String table, Class<T> _class) {
        this.table = table;
        this._class = _class;
    }


    protected PreparedStatement createPrepared(String sql) throws SQLException {
        // return getConnection().prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
        return getConnection().prepareStatement(sql);
    }

    protected Connection getConnection() {
        JdbcMySQLClient mySQLClient = JdbcMySQLClient.getInstance();
        return mySQLClient.getConnection();
    }

    public List<T> findAll() throws SQLException, InstantiationException, IllegalAccessException {
        Utils utils = Utils.getInstance();
        String sql = "SELECT * FROM " + table + " WHERE deleted = false";
        PreparedStatement prepared = createPrepared(sql);
        ResultSet rs = prepared.executeQuery();
        return utils.getList(rs, _class);
    }

    public T findById(Long id) throws SQLException, InstantiationException, IllegalAccessException {
        Utils utils = Utils.getInstance();
        AppConfig config = AppConfig.getInstance();
        String sql = "SELECT * FROM " + table + " WHERE id = ? AND deleted = false";
        if (config.SHOW_SQL) System.out.println(sql);
        PreparedStatement prepared = createPrepared(sql);
        prepared.setLong(1, id);
        ResultSet rs = prepared.executeQuery();
        if (!rs.next()) return null;
        return utils.getObject(rs, _class);
    }

    public T findBy(String field, String value) throws SQLException, InstantiationException, IllegalAccessException {
        Utils utils = Utils.getInstance();
        String sql = "SELECT * FROM " + table + " WHERE " + field + " = ? AND deleted = false";
        PreparedStatement prepared = createPrepared(sql);
        prepared.setObject(1, value);
        ResultSet rs = prepared.executeQuery();
        if (!rs.next()) return null;
        return utils.getObject(rs, _class);
    }

    public List<T> sortBy(String field, boolean asc) {
        return null;
    }

    public boolean insert(T object) throws SQLException, InstantiationException, IllegalAccessException {
        PreparedStatement prepared;
        Field[] fields = _class.getDeclaredFields();
        int fieldCount = fields.length;
        Utils utils = Utils.getInstance();
        AppConfig config = AppConfig.getInstance();

        StringBuilder sql = new StringBuilder("INSERT INTO " + table + "(");
        // create column names
        for (int i = 0; i < fieldCount; i++) {
            Field field = fields[i];
            String fieldSnakeCase = utils.camelToSnake(field.getName());
            sql.append(fieldSnakeCase).append(i != fieldCount - 1 ? "," : ")");
        }
        // append values to sql queries
        sql.append("VALUES(");
        for (int i = 0; i < fieldCount; i++) {
            // Field field = fields[i];
            sql.append(i != fieldCount - 1 ? "?," : "?)");
        }

        if (config.SHOW_SQL) System.out.println(sql);
        prepared = createPrepared(sql.toString());
        for (int i = 0; i < fieldCount; i++) {
            Field f = fields[i];
            f.setAccessible(true);
            prepared.setObject(i + 1, f.get(object));
        }

        int count = prepared.executeUpdate();
        return count == 1;
    }

    public boolean update(T object) throws SQLException, IllegalAccessException {
        PreparedStatement prepared;
        Field[] fields = _class.getDeclaredFields();
        int fieldCount = fields.length;
        AppConfig config = AppConfig.getInstance();
        Utils utils = Utils.getInstance();

        StringBuilder sql = new StringBuilder("UPDATE " + table + " SET ");
        for (int i = 1; i < fieldCount; i++) {
            Field f = fields[i];
            String fieldSnakeCase = utils.camelToSnake(f.getName());
            sql.append(fieldSnakeCase).append(i != fieldCount - 1 ? " = ?, " : " = ? ");
        }
        sql.append(" WHERE ");
        String idFieldName = utils.camelToSnake(fields[0].getName());
        sql.append(idFieldName);
        sql.append(" = ?");

        if (config.SHOW_SQL) System.out.println(sql);
        prepared = createPrepared(sql.toString());

        for (int i = 1; i < fieldCount; i++) {
            Field f = fields[i];
            f.setAccessible(true);
            prepared.setObject(i, f.get(object));
        }
        Field idField = fields[0];//id field
        idField.setAccessible(true);
        prepared.setObject(fieldCount, idField.get(object));

        int count = prepared.executeUpdate();
        return count == 1;
    }

    public boolean delete(Integer id) throws SQLException {
        String sql = "DELETE FROM " + table + " WHERE id = ? LIMIT 1";
        PreparedStatement prepared = createPrepared(sql);
        prepared.setInt(1, id);
        int count = prepared.executeUpdate();
        return count == 1;
    }
}
