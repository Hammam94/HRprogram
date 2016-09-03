package com.company.Back_End.models;

import com.company.Back_End.core.model;
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
        return this.manyTOManyTables("attendant", "attendants", "events", id);
    }
}
