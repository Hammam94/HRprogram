package com.company.Back_End.Model;

import com.company.Back_End.Core.model;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by user on 8/30/2016.
 */
public class Day extends model {
    public Day() throws SQLException, IOException {
        this.createTable("days.sql");
        this.createTable("days_events.sql");
    }

    public ResultSet event(String id) throws SQLException {
        return this.belongsToManytoMany("event", id);
    }

    public ResultSet attendant(String id) throws SQLException{
        return this.manyTOManyTables("attendant" ,"event", id);
    }
}
