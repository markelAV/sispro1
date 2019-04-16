package app.Controllers;

import app.database.entities.File7;
import app.database.servise.File7Service;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.sql.SQLException;

public class DataBaseWindowController {
    @FXML
    public TableView table;
    @FXML
    public TableColumn<File7,String> columnName;
    public TableColumn<File7, String> columnType;
    public TableColumn<File7,String> columnData;

    @FXML
    public void initialize(){
        try {
            File7Service fileService = new File7Service();
            ObservableList<File7> list = FXCollections.observableList(fileService.getAll());
            columnName.setCellValueFactory(new PropertyValueFactory<File7, String>("Address"));
            columnType.setCellValueFactory(new PropertyValueFactory<File7, String>("Access"));
            columnData.setCellValueFactory(new PropertyValueFactory<File7, String>("Data"));
            table.setItems(list);
        } catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }

}
