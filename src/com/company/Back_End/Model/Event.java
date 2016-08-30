package com.company.Back_End.Model;

import com.company.Back_End.Core.model;

import java.io.IOException;
import java.sql.SQLException;

/**
 * Created by user on 8/30/2016.
 */
public class Event extends model {
    public Event() throws SQLException, IOException {
         this.createTable("events.sql");
    }




}
