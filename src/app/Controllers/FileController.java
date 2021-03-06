package app.Controllers;

import app.Model.CSVFile.CSVFile;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import app.Model.CSVFile.EntityTable;
import app.Model.CSVFile.FileControler;
import javafx.util.Callback;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class FileController {
    @FXML
    public TextArea resultArea;
    private FileControler fileC = new FileControler();
    private File file;
    private ObservableList<EntityTable> list = FXCollections.observableList(new ArrayList<EntityTable>());
    private CSVFile csvFile;


    public void initialize() {

    }
    private void initFile(String path){
        //todo if(file==null)
        file = new File(path);
        csvFile = new CSVFile(path);
    }
   // private Fil
    @FXML
    private TableView<EntityTable> table;
    @FXML
    public TableColumn<EntityTable,String> columnName;
    @FXML
    public TableColumn<EntityTable,String> columnTorZ;
    @FXML
    public TableColumn<EntityTable,String> columnDate;


    @FXML
    private void refreshTableView()
    {
        columnName.setVisible(false);
        columnDate.setVisible(false);
        columnDate.setVisible(false);
        columnName.setVisible(true);
        columnDate.setVisible(true);
        columnDate.setVisible(true);
    }
    private void drawTable(){
        columnName.setCellValueFactory(new PropertyValueFactory<EntityTable, String>("Name"));
        columnTorZ.setCellValueFactory(new PropertyValueFactory<EntityTable, String>("Type"));
        columnDate.setCellValueFactory(new PropertyValueFactory<EntityTable, String>("Date"));
        table.setItems(list);
    }

    public void update(javafx.event.ActionEvent actionEvent) {
        resultArea.appendText("Из update");
    }


    public void open(String name) {
        initFile(name);
        //EntityTable[] table=fileC.open2(file.getAbsolutePath());
        list = FXCollections.observableList(fileC.open2(file.getAbsolutePath()));
        drawTable();
//        columnName.setCellValueFactory(cellFactory);
//        columnName.setOnEditCommit(
//                new EventHandler<TableColumn.CellEditEvent<EntityTable, String>>() {
//                    @Override
//                    public void handle(TableColumn.CellEditEvent<EntityTable, String> event) {
//                        event.getTableView().getItems().get(event.getTablePosition().getRow()).setName(event.getNewValue());
//                    }
//                }
//        );
        resultArea.appendText("Файл был успешно открыт");

    }

    public void create(String name) {
        try {
            File file = new File("C:\\Users\\Артём\\Desktop\\" + name);
            file.createNewFile();
            initFile("C:\\Users\\Артём\\Desktop\\" + name);
            csvFile.setNameFile(file.getAbsolutePath());
            resultArea.setText("Файл успешно создан");
            list.remove(0,list.size());
            drawTable();

//            columnName.setCellValueFactory( new PropertyValueFactory<EntityTable,String>());
        }
        catch (IOException e){
            resultArea.setText(e.getMessage());
        }
    }

    public void save(javafx.event.ActionEvent actionEvent) {
        if(file==null){resultArea.setText("Ошибка. Файл не был открыт");}
        else {
            try {
                list = table.getItems();
                csvFile.write(new ArrayList<EntityTable>(table.getItems()));
                resultArea.setText("Файл успешно сохранен");
            }
            catch (IOException ex){resultArea.setText(ex.getMessage());}
        }

    }

    public void saveAs(String path) {
        try {

            csvFile.setNameFile(path);
            ArrayList<EntityTable> op =new ArrayList<EntityTable>(table.getItems());
            csvFile.write(op);
            resultArea.setText("Файл успешно сохранен");
        }
        catch (IOException e){
            resultArea.setText(e.getMessage());
        }
    }

    public void exit(javafx.event.ActionEvent actionEvent) {System.out.println("Из exit");
    }

    public void addLine(String line) {
        if(line!=null && line.length()>3){
        String[] entity = line.split(";");
        if(entity.length==3) {
            EntityTable e = new EntityTable(entity[0],entity[1],entity[2]);
            list.add(list.size(),e);
        }
        else{
            resultArea.setText("Ошибка. Неправильное количество аргументов.");
        }
        }
        else{
            resultArea.setText("Ошибка. Добавление пустой строки невозможно.");
        }
    }

    public void editTable(javafx.event.ActionEvent actionEvent) {System.out.println("Из editTable");
    }

    public void about(javafx.event.ActionEvent actionEvent) {System.out.println("Из about");
    }

    public void help(javafx.event.ActionEvent actionEvent) {System.out.println(" Из help");
    }

}
