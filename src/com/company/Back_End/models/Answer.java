package com.company.Back_End.models;

import com.company.Back_End.core.model;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by user on 9/4/2016.
 */
public class Answer extends model {

    public Answer() throws SQLException, IOException {
        this.createTable("answers.sql");
    }

    public ResultSet question(String id) throws SQLException {
        return this.belongsTo("question", id);
    }

    public ResultSet interviewee(String id) throws SQLException {
        return this.belongsTo("interviewee", id);
    }

    public ResultSet point(String id) throws SQLException {
        return this.hasMany("point", id);
    }

}
