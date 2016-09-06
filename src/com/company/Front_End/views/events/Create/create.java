package com.company.Front_End.views.events.Create;

import javafx.fxml.FXML;
import com.company.Front_End.views.events.Form.form;

/**
 * Created by user on 9/6/2016.
 */
public class create {
    @FXML private form formController;

    @FXML
    public void initialize(){
        formController.create();
    }
}
