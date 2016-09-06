package com.company.Front_End.views.events.Form;


import com.company.Back_End.core.viewer;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import java.lang.reflect.InvocationTargetException;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by user on 9/6/2016.
 */
public class form {

    @FXML private TextField eventName, eventType, startTime, endTime, numberOfDays;
    @FXML private Button save;

    private String[] classFields = {"name", "event_type", "start_time", "end_time", "number_of_days"};

    private String[] fields;
    private Object[] values, newDate, oldData;


    @FXML
    public void create(){
        save.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                String name = eventName.getText(),
                       type = eventType.getText(),
                       start= startTime.getText(),
                       end  = endTime.getText(),
                       days = numberOfDays.getText();
                viewer v = new viewer();
                v.createControllerInstance("eventController", "create");
                String[] params = {name, type, start, end, days};
                try {
                    if((boolean) v.getdata(params)){
                        System.out.println("attached");
                    }
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                } catch (NoSuchMethodException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                } catch (InstantiationException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    @FXML
    public void update(ResultSet data) throws SQLException, ClassNotFoundException, NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException {
        oldData = new Object[]{data.getString("name"), data.getString("event_type"), data.getString("start_time"), data.getString("end_time"), String.valueOf(data.getInt("number_of_days"))};
        eventName.setText((String) oldData[0]);
        eventType.setText((String) oldData[1]);
        startTime.setText((String) oldData[2]);
        endTime.setText((String) oldData[3]);
        numberOfDays.setText((String) oldData[4]);
        Platform.runLater(this::getData);
    }

    private void getData() {
        save.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                newDate= new Object[]{eventName.getText(), eventType.getText(), startTime.getText(), endTime.getText(), Integer.parseInt(numberOfDays.getText())};
                viewer view = new viewer();
                view.createControllerInstance("eventController","updata");
                try {
                    view.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                setFieldsValues();
                String id = "1";
                Object[] params = {fields, values, id};
                try {
                    if((boolean) view.getdata(params)){
                        System.out.println("updated");
                    }
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                } catch (NoSuchMethodException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                } catch (InstantiationException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private void setFieldsValues() {
        int iterator = 0;
        System.out.println(oldData.length + " " + classFields.length);
        for(int i = 0; i < oldData.length; ++i){
            if(!oldData[i].equals(newDate[i])){
                ++iterator;
            }
        }
        fields = new String[iterator];
        values = new Object[iterator];
        iterator = 0;
        for(int i = 0; i < oldData.length; ++i){
            if(!oldData[i].equals(newDate[i])){
                fields[iterator] = classFields[i];
                values[iterator] = newDate[i];
                ++iterator;
            }
        }
    }

}
