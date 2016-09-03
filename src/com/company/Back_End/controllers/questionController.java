package com.company.Back_End.controllers;

import com.company.Back_End.models.Question;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by user on 9/4/2016.
 */
public class questionController extends Question {
    private String fields = "question, main_point";
    public questionController() throws SQLException, IOException {
    }

    public boolean create(String question, String mainPoint) throws SQLException {
        String values = "\"" + question + "\", \"" + mainPoint + "\"";
        this.save(fields, values);
        return true;
    }

    public ResultSet index () throws SQLException {
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
