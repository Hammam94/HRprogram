package com.company.Back_End.controllers;

import com.company.Back_End.models.Problem;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by user on 9/2/2016.
 */
public class problemController extends Problem {
    private String fields = "title, problem_subject, email";
    public problemController() throws SQLException, IOException {
    }

    public boolean create(String title, String problemSubject, String email) throws SQLException {
        String values = "\"" + title + "\", \"" + problemSubject + "\", \"" + email + "\"";
        this.save(fields, values);
        return true;
    }

    public ResultSet index() throws SQLException{
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

    public ResultSet getSolutions(String problemId) throws SQLException {
        return this.solution(problemId);
    }
}
