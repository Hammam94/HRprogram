package com.company.Back_End.models;

import com.company.Back_End.core.model;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by user on 9/4/2016.
 */
public class Interviewee extends model {

    public Interviewee() throws SQLException, IOException {
        this.createTable("interviewees.sql");
        this.createTable("interviews_interviewees.sql");
        this.createTable("interviewers_interviewees.sql");
    }

    public ResultSet interview(String id) throws SQLException {
        return this.belongsToManytoMany("interview", id);
    }

    public ResultSet interviewer(String id) throws SQLException {
        return this.belongsToManytoMany("interviewer", id);
    }

    public ResultSet answer(String id) throws SQLException {
        return this.hasMany("answer", id);
    }
}
