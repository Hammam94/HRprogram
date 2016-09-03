package com.company.Back_End.controllers;

import com.company.Back_End.models.Day;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by user on 8/30/2016.
 */
public class dayController extends Day {
    private String fields = "data";

    public dayController() throws SQLException, IOException {
    }

    public ResultSet index() throws SQLException{
        return this.all();
    }
    public ResultSet show(String id) throws SQLException {
        return this.find(id);
    }

    public boolean create(String date) throws  SQLException{
        String values = "\"" + date + "\"";
        this.save(fields, values);
        return true;
    }

    public boolean updata (String[] fields, Object[] values, String id) throws SQLException {
        this.update(fields, values, "id = " + id);
        return true;
    }

    public boolean delete(String id) throws SQLException {
        System.out.println(id);
        this.remove(id);
        return true;
    }

    public ResultSet getAttendants(String id) throws SQLException{
        return this.attendant(id);
    }

    public ResultSet getEvents(String id) throws SQLException {
        return this.event(id);
    }

}
