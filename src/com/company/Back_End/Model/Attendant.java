package com.company.Back_End.Model;

import com.company.Back_End.Core.model;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by user on 8/31/2016.
 */
public class Attendant extends model {
    public Attendant() throws SQLException, IOException {
        this.createTable("attendants.sql");
        this.createTable("attendants_days_events.sql");
    }

    public ResultSet event(String id) throws SQLException{
        return this.belongsToManytoMany("event", id);
    }

    public ResultSet day(String id) throws SQLException {
        return this.manyTOManyTables("day","days_event", id);
    }
}
