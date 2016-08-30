package com.company.Back_End.Core;

import java.sql.*;
/**
 * Created by user on 8/25/2016.
 */
public class query {

    private Connection c;
    private Statement stat;

    public query(Connection c) throws SQLException {
        this.c = c;
        this.stat = c.createStatement();
    }

    public void openStatment(Connection c) throws SQLException {
        stat = c.createStatement();
    }

    public void closeStatment() throws SQLException {
        stat.close();
    }

    public void createTable(String table) throws SQLException {
        stat.executeUpdate(table);
    }

    public void dropTable(String table) throws SQLException {
        stat.executeUpdate("DROP TABLE " + table + ";");
    }

    /////////////////////////////                           Insertion group                              ///////////////////////////////////////////////////////////////////////////
    public void insert(String table, String fields, String values) throws SQLException {
        String sql = "INSERT INTO " + table + "(" + fields + ") VALUES (" + values + ");";
        stat.executeUpdate(sql);
    }

    public void insertAttach(String table, String firstValue, String lastValue) throws SQLException {
        String sql = "INSERT INTO " + table + " VALUES (" + firstValue + " , " + lastValue + ");";
        stat.executeUpdate(sql);
    }

    /////////////////////////////                           Update group                              ///////////////////////////////////////////////////////////////////////////
    public void update(String table, String data, String condition) throws SQLException {
        String sql = "UPDATE " +table + " set" + data + " where" + condition + ";";
        stat.executeUpdate(sql);
    }

    /////////////////////////////                           Delete group                              ///////////////////////////////////////////////////////////////////////////
    public void delete(String table, String condition) throws SQLException {
        String sql = "DELETE from " + table + " WHERE " + condition + ";";
        stat.executeUpdate(sql);
    }

    public void deleteDettach(String table, String key, String role, String value) throws SQLException {
        String sql = "DELETE FROM " + table + "WHERE id = " + key + " AND " + role +"_id = " + value + ";";
        stat.executeUpdate(sql);
    }

    /////////////////////////////                           Selection group                              ///////////////////////////////////////////////////////////////////////////
    public ResultSet select(String fields, String tables, String condition) throws SQLException {
        String sql = "SELECT " + fields + " FROM "+ tables + " WHERE " + condition + ";";
        return stat.executeQuery(sql);
    }

    public ResultSet selectAllById(String table, String id) throws SQLException {
        String sql = "SELECT * FROM "+ table + " WHERE id = "+ id +" ;";
        return stat.executeQuery(sql);
    }

    public ResultSet selectAllByIdBelongs(String ftable, String stable, String id) throws SQLException {
        String secondId = stable.substring(0, stable.length() - 1);
        String sql= "SELECT * FROM " + ftable  + " WHERE " + ftable + ".id IN (SELECT " + secondId + "_id FROM " + ftable + " NATURAL JOIN " + stable + " WHERE " + ftable + ".id = " + id + ");";
        return stat.executeQuery(sql);
    }

    public ResultSet selectAllByIdBelongsMany(String ftable, String stable, String id) throws SQLException {
        String secondId = stable.substring(0, stable.length() - 1);
        String sql= "SELECT * FROM " + ftable  + " WHERE " + ftable + ".id IN (SELECT " + secondId + "_id FROM " + ftable + " NATURAL JOIN " + stable + " WHERE " + ftable + ".id = " + id + ");";
        return stat.executeQuery(sql);
    }

    public ResultSet selectAllByMany(String table, String key, String value) throws SQLException {
        String sql= "SELECT * FROM " + table  + " WHERE " + key + " = " + value + ";";
        return stat.executeQuery(sql);
    }

    public ResultSet selectAllWhere(String table, String condition) throws SQLException {
        String sql = "SELECT * FROM "+ table + " WHERE " + condition + ";";
        return stat.executeQuery(sql);
    }

    public ResultSet selectAllTable(String table) throws SQLException {
        String sql = "SELECT * FROM "+ table + ";";
        return stat.executeQuery(sql);
    }

}
