package com.company.Back_End.Controller;

import com.company.Back_End.Model.Attendant;

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

    public boolean updata (String[] fields, Object[] values, String id) throws SQLException {
        this.update(fields, values, "id = " + id);
        return true;
    }

    public boolean delete(String id) throws SQLException {
        this.delete(id);
        return true;
    }
}
