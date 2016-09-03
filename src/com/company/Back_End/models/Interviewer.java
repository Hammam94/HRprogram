package com.company.Back_End.models;

import com.company.Back_End.core.model;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by user on 9/4/2016.
 */
public class Interviewer extends model {

    public Interviewer() throws SQLException, IOException {
        this.createTable("interviewers.sql");
        this.createTable("interviews_interviewers.sql");
    }

    public ResultSet interview(String id) throws SQLException {
        return this.belongsToManytoMany("interview", id);
    }

    public ResultSet interviewee(String id) throws SQLException {
        return this.belongsToManytoMany("interviewee", id);
    }

}
