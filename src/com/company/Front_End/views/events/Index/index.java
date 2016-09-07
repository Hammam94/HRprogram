package com.company.Front_End.views.events.Index;

import com.company.Back_End.core.viewer;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.util.Callback;
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
        indexTable.setItems(rows);
    }

    private void initColumns(TableColumn tableColumn, int order){
        tableColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<ObservableList,String>,ObservableValue<String>>(){
            public ObservableValue<String> call(TableColumn.CellDataFeatures<ObservableList, String> param) {
                return new SimpleStringProperty(param.getValue().get(order).toString());
            }
        });
    }
}
