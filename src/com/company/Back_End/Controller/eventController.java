package com.company.Back_End.Controller;

import com.company.Back_End.Model.Event;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by user on 8/30/2016.
 */
public class eventController extends Event {
    private String fields = "name, event_type, start_time, end_time, number_of_days";

    public eventController() throws SQLException, IOException {
    }


    public ResultSet index() throws SQLException {
        return this.all();
    }

    public boolean save(String eventName, String eventType, String startTime, String endTime, String numberOfDays) throws SQLException {
        String values = "\"" + eventName + "\", \""+ eventType + "\", \"" + startTime + "\", \"" + endTime + "\", " + String.valueOf(numberOfDays);
        this.save(fields,values);
        return true;
    }

    public ResultSet show(String id) throws SQLException {
        return this.find(id);
    }

    public boolean updata (String[] fields, String[] values, String id) throws SQLException {
        this.update(fields, values, "id = " + id);
        return true;
    }

    public boolean delete(String id) throws SQLException {
        this.delete(id);
        return true;
    }
}