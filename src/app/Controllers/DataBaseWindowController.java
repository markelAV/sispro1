package app.Controllers;

import app.Model.CSVFile.EntityTable;
import app.database.entities.File3;
import app.database.entities.File7;
import app.database.servise.File3Service;
import app.database.servise.File7Service;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DataBaseWindowController {
    public TextArea resultArea;
    private File7Service file7Service;
    private File3Service file3Service;
    private ObservableList<EntityTable> list;
    private boolean file7=false;

    @FXML
    public TableView table;
    @FXML
    public TableColumn<EntityTable,String> columnName;
    public TableColumn<EntityTable,String> columnType;
    public TableColumn<EntityTable,String> columnData;
    


    @FXML
    public void initialize(){
        file3Service = new File3Service();
        file7Service = new File7Service();

    }
    private void drawTable(){
        columnName.setCellValueFactory(new PropertyValueFactory<EntityTable, String>("Name"));
        columnType.setCellValueFactory(new PropertyValueFactory<EntityTable, String>("Type"));
        columnData.setCellValueFactory(new PropertyValueFactory<EntityTable, String>("Date"));
        table.setItems(list);
    }

    public void loadDBFile3(ActionEvent actionEvent) {
        try {
            file7=false;
            List<File3> l3 = file3Service.getAll();
            list=convert3(l3);
            drawTable();
            resultArea.setText("OK");
        }
        catch (SQLException e){
            resultArea.setText(e.getMessage());
        }
    }
    public void addLine(String str){
        String[] line = str.split(";");
           if (line.length == 3) {
               try {
                   if (file7) {
                       file7Service.add(new File7(line[0], line[1], line[2]));
                       loadFile7(new ActionEvent());
                   } else {
                       file3Service.add(new File3(line[0], Integer.parseInt(line[1]), line[2]));
                       loadDBFile3(new ActionEvent());
                   }
                   resultArea.setText("Данные успешно добавлены");

               } catch (SQLException e) {
                   resultArea.setText(e.getMessage());
               }
           }
           else{
               resultArea.setText("Ошибка добавления данных");
           }
    }

    private ObservableList<EntityTable> convert7(List<File7> l7){
        ArrayList<EntityTable> result = new ArrayList<EntityTable>();
        int i=0;
        File7 file7;
        while(i<l7.size()){
            file7=l7.get(i);
            result.add(new EntityTable(file7.getAddress(),file7.getAccess(),file7.getData()));
            i++;
        }
        return FXCollections.observableList(result);
    }
    private ObservableList<EntityTable> convert3(List<File3> l3){
        ArrayList<EntityTable> result = new ArrayList<EntityTable>();
        int i=0;
        File3 file7;
        while(i<l3.size()){
            file7=l3.get(i);
            result.add(new EntityTable(file7.getLocation(),String.valueOf(file7.getSize()),file7.getEditDate()));
            i++;
        }
        return FXCollections.observableList(result);
    }

    public void loadFile7(ActionEvent actionEvent) {
        try {
            file7=true;
            List<File7> l7 = file7Service.getAll();
            list=convert7(l7);
            drawTable();
            resultArea.setText("OK");
        }
        catch (SQLException e){
            resultArea.setText(e.getMessage());
        }
    }

    public void saveDB(ActionEvent actionEvent) {
    }
}
