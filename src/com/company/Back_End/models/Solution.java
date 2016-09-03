package com.company.Back_End.models;

import com.company.Back_End.core.model;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by user on 9/3/2016.
 */
public class Solution extends model {
    public Solution() throws SQLException, IOException {
        this.createTable("solutions.sql");
    }

    public ResultSet problem(String id) throws SQLException {
        return this.belongsTo("problem", id);
    }
}
