package com.company.Front_End.views.events.Update;

import com.company.Back_End.core.viewer;
import javafx.fxml.FXML;
import com.company.Front_End.views.events.Form.form;
import java.lang.reflect.InvocationTargetException;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by user on 9/6/2016.
 */
public class update {
    @FXML private form formController;

    @FXML
    public void initialize() throws NoSuchMethodException, InstantiationException, SQLException, IllegalAccessException, InvocationTargetException, ClassNotFoundException {
        viewer v = new viewer();
        v.createControllerInstance("eventController","show");
        String[] params = {"1"};
        formController.update((ResultSet) v.getdata(params));
    }
}
