package com.company.Back_End.models;

import com.company.Back_End.core.model;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by user on 9/4/2016.
 */
public class Interview extends model {
    public Interview() throws SQLException, IOException {
        this.createTable("interviews.sql");
    }

    public ResultSet interviewer(String id) throws SQLException {
        return this.belongsToManytoMany("interviewer", id);
    }

    public ResultSet interviewee(String id) throws SQLException {
        return this.belongsToManytoMany("interviewee", id);
    }

}
