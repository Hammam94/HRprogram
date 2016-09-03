package com.company.Front_End;

import com.company.Front_End.Core.Viewer;
import com.company.Front_End.views.events.index;
import java.lang.reflect.InvocationTargetException;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Main {

    public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException, SQLException {
        Viewer v = new Viewer("events");
        v.createControllerInstance("attendantController", "Aggregation");
        String[] params = {};
        ResultSet r = (ResultSet) v.getdata(params);
        System.out.println(r.getInt("AVG"));
    }


}
