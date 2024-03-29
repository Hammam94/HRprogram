package com.company.Back_End.controllers;

import com.company.Back_End.models.Comment;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by user on 9/3/2016.
 */
public class commentController extends Comment {
    private String fields = "comment, problem_id";

    public commentController() throws SQLException, IOException {
    }

    public boolean create(String comment, String problemId) throws SQLException {
        String values = "\"" + comment + "\", \"" + problemId + "\"";
        this.save(fields, values);
        return true;
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

    public ResultSet getProblem(String problemId) throws SQLException {
        return this.problem(problemId);
    }
}
