package com.company.Back_End.models;

import com.company.Back_End.core.model;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by user on 9/4/2016.
 */
public class Question extends model {
    public Question() throws SQLException, IOException {
        this.createTable("questions.sql");
    }

    public ResultSet answer(String id) throws SQLException {
        return this.hasMany("answer", id);
    }
}
