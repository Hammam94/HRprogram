package com.company.Back_End.models;

import com.company.Back_End.core.model;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by user on 9/2/2016.
 */
public class Problem extends model {
    public Problem() throws SQLException, IOException {
        this.createTable("problems.sql");
    }

    public ResultSet solution(String id) throws SQLException {
        return this.hasMany("solution", id);
    }
}
