package com.company.Back_End.controllers;

import com.company.Back_End.models.Attendant;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by user on 8/31/2016.
 */
public class attendantController extends Attendant {
    private String fields = "name, telephone, email";
    public attendantController() throws SQLException, IOException {
    }

    public ResultSet index() throws SQLException {
        return this.all();
    }

    public ResultSet show(String id) throws SQLException {
        return this.find(id);
    }

    public boolean create(String name, String telephone, String email) throws SQLException{
        String values = "\"" + name + "\",\"" + telephone + "\",\"" + email +"\"";
        this.save(fields, values);
        return true;
    }

    public boolean attachEvent(String eventId, String attendantId) throws SQLException {
        String values = eventId + ", " +attendantId;
        String fields = "event_id, attendant_id";
        this.attach("event", fields, values);
        return true;
    }

    public boolean attachEventDays(String eventId, String attendantId, String dayId) throws SQLException{
        String values = eventId + "," + dayId + "," + attendantId;
        String fields = "event_id, day_id, attendant_id";
        this.attach("days_event", fields, values);
        return true;
    }

    public boolean updata (String[] fields, Object[] values, String id) throws SQLException {
        this.update(fields, values, "id = " + id);
        return true;
    }

    public boolean delete(String id) throws SQLException {
        this.delete(id);
        return true;
    }

    public ResultSet getDays(String id) throws SQLException{
        return this.day(id);
    }

    public ResultSet getEvents(String id) throws SQLException{
        return this.event(id);
    }
}
