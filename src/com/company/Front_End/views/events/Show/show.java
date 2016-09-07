package com.company.Front_End.views.events.Show;

import com.company.Back_End.core.viewer;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;

import java.lang.reflect.InvocationTargetException;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by user on 9/7/2016.
 */
public class show {
    @FXML Button delete, update, attendants;
    @FXML Label eventName, eventType, startTime, endTime, numberOfDays;

    @FXML
    public void show(String id) throws SQLException, ClassNotFoundException, NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException {
        viewer v = new viewer();
        v.close();
        v.createControllerInstance("eventController", "show");
        Object[] params = {id};
        ResultSet rs = (ResultSet) v.getdata(params);
        eventName.setText(rs.getString("name"));
        eventType.setText(rs.getString("event_type"));
        startTime.setText(rs.getString("start_time"));
        endTime.setText(rs.getString("end_time"));
        numberOfDays.setText(String.valueOf(rs.getInt("number_of_days")));

        delete.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                viewer v = new viewer();
                try {
                    v.close();
                    v.createControllerInstance("eventController", "delete");
                    Object[] params = {id};
                    if ((boolean) v.getdata(params)){
                        System.out.println("deleted");
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                } catch (InstantiationException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                } catch (NoSuchMethodException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
        });

        update.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
            }
        });

        attendants.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {

            }
        });
    }
}
