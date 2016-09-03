package com.company.Back_End.controllers;

import com.company.Back_End.core.model;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by user on 9/4/2016.
 */
public class intervieweeController extends model {
    private String fields = "name, email,telephone";
    public intervieweeController() throws SQLException {
    }

    public boolean create(String name, String email, String telephone) throws SQLException {
        String values = "\"" + name + "\", \"" + email + "\", \"" + telephone + "\"";
        this.save(fields, values);
        return true;
    }
    public ResultSet index() throws SQLException {
        return this.all();
    }

    public ResultSet show(String id) throws SQLException {
        return this.find(id);
    }

    public boolean updata (String[] fields, Object[] values, String id) throws SQLException {
        this.update(fields, values, "id = " + id);
        return true;
    }

    public boolean delete(String id) throws SQLException {
        this.delete(id);
        return true;
    }
}
