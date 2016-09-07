package com.company.Front_End.views.events.Index;

import com.company.Back_End.core.viewer;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.layout.GridPane;
import javafx.util.Callback;
import javafx.util.Duration;

import java.lang.reflect.InvocationTargetException;
import java.sql.ResultSet;
import java.sql.SQLException;


/**
 * Created by user on 9/2/2016.
 */
public class index {
    @FXML private TableView indexTable;
    @FXML private TableColumn name, eventType, startTime, endTime, numberOfDays;
    private static ObservableList<ObservableList> rows = FXCollections.observableArrayList();

    @FXML
    public void initialize() throws ClassNotFoundException, NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException, SQLException {
        init();
        Timeline timeline = new Timeline(
            new KeyFrame(Duration.seconds(2),
                new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent actionEvent) {
                        try {
                            update();
                        } catch (SQLException e) {
                            e.printStackTrace();
                        } catch (ClassNotFoundException e) {
                            e.printStackTrace();
                        } catch (NoSuchMethodException e) {
                            e.printStackTrace();
                        } catch (InstantiationException e) {
                            e.printStackTrace();
                        } catch (IllegalAccessException e) {
                            e.printStackTrace();
                        } catch (InvocationTargetException e) {
                            e.printStackTrace();
                        }


                    }
                })
        );
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
    }

    private void init() throws SQLException, ClassNotFoundException, NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException {
        viewer view = new viewer();
        view.close();
        view.createControllerInstance("eventController", "index");
        Object[] params = {};
        ResultSet rs = (ResultSet) view.getdata(params);
        TableColumn[] columns = {name, eventType, startTime, endTime, numberOfDays};
        for(int i = 0; i < columns.length; ++i){
            initColumns(columns[i], i+1);
        }
        while (rs.next()){
            ObservableList<String> row = FXCollections.observableArrayList();
            for(int i = 1; i<=rs.getMetaData().getColumnCount(); i++){
                row.add(rs.getString(i));
            }
            rows.add(row);
        }
        indexTable.setRowFactory( tv -> {
            TableRow<ObservableList> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && (! row.isEmpty()) ) {
                    Object[] data = row.getItem().toArray();
                    System.out.println((String) data[0]);
                }
            });
            return row ;
        });
        indexTable.setItems(rows);
    }

    private void update() throws SQLException, ClassNotFoundException, NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException {
        viewer view = new viewer();
        view.close();
        view.createControllerInstance("eventController", "rowsNumber");
        Object[] params = {"COUNT", "id"};
        ResultSet rs = (ResultSet) view.getdata(params);
        if(rows.size() < rs.getInt("COUNT")){
            view.close();
            view.changeMethod("specifiedIndex");
            int specificIndex = rows.size();
            rs = (ResultSet) view.getdata(new Object[]{String.valueOf(specificIndex)});
            while (rs.next()){
                if(rs.getInt("id") > specificIndex){
                    ObservableList<String> row = FXCollections.observableArrayList();
                    for(int i = 1; i<=rs.getMetaData().getColumnCount(); i++){
                        row.add(rs.getString(i));
                    }
                    rows.add(row);
                }
            }
            indexTable.setItems(rows);
        }
    }
    private void initColumns(TableColumn tableColumn, int order){
        tableColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<ObservableList,String>,ObservableValue<String>>(){
            public ObservableValue<String> call(TableColumn.CellDataFeatures<ObservableList, String> param) {
                return new SimpleStringProperty(param.getValue().get(order).toString());
            }
        });
    }
}
