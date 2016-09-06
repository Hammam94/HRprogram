package com.company.Front_End.views.events;

import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.JFXPanel;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.VBox;
import javafx.util.Callback;
import java.sql.ResultSet;
import java.sql.SQLException;


/**
 * Created by user on 9/2/2016.
 */
public class index extends JFXPanel {
    private TableView table = new TableView();
    private VBox pane;
    private ResultSet data;
    private ObservableList<ObservableList> rows;

    public index(ResultSet data) throws SQLException {
        this.data = data;
        rows = FXCollections.observableArrayList();
        init();
    }

    private void init() throws SQLException {
        pane = new VBox();
        pane.setSpacing(20);
        pane.setPadding(new Insets(10, 0, 0, 10));
        pane.setPrefHeight(2000);

        table.setPrefSize(pane.getPrefWidth(), pane.getPrefHeight());
        for(int i=0 ; i<data.getMetaData().getColumnCount(); i++){
            final int j = i;
            TableColumn col = new TableColumn(data.getMetaData().getColumnName(i+1));
            col.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<ObservableList,String>,ObservableValue<String>>(){
                public ObservableValue<String> call(TableColumn.CellDataFeatures<ObservableList, String> param) {
                    return new SimpleStringProperty(param.getValue().get(j).toString());
                }
            });

            table.getColumns().addAll(col);
        }
        while(data.next()){
            ObservableList<String> row = FXCollections.observableArrayList();
            for(int i=1 ; i<=data.getMetaData().getColumnCount(); i++){
                row.add(data.getString(i));
            }
            rows.add(row);
        }


        table.setItems(rows);
        pane.getChildren().add(table);
        Platform.runLater(this::createScene);
    }

    private void createScene() {
        Scene scene = new Scene(pane);
        setScene(scene);
    }
}
