package app.Controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;
import app.Model.CSVFile.EntityTable;
import app.Model.CSVFile.FileControler;

import java.io.File;

public class FileController {
    @FXML
    public TextArea resultArea;
    private FileControler fileC = new FileControler();
    private File file;
    private ObservableList<EntityTable> list;


    private void initFile(String path){
        //todo if(file==null)
        file = new File(path);
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
        resultArea.appendText("Файл был успешно открыт");
    }

    public void create(javafx.event.ActionEvent actionEvent) {System.out.println("Из create");
    }

    public void save(javafx.event.ActionEvent actionEvent) {resultArea.setText("Из save");
    }

    public void saveAs(javafx.event.ActionEvent actionEvent) {System.out.println("Из saveAs");
    }

    public void exit(javafx.event.ActionEvent actionEvent) {System.out.println("Из exit");
    }

    public void addLine(javafx.event.ActionEvent actionEvent) {
        System.out.println("Из addLine");
        ObservableList<EntityTable> t = table.getItems();
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i <t.size() ; i++) {
            builder.append(t.get(i).getName())
                    .append(' ')
                    .append(t.get(i).getDate())
                    .append(' ')
                    .append(t.get(i));
            resultArea.appendText(builder.toString());
        }
    }

    public void editTable(javafx.event.ActionEvent actionEvent) {System.out.println("Из editTable");
    }

    public void about(javafx.event.ActionEvent actionEvent) {System.out.println("Из about");
    }

    public void help(javafx.event.ActionEvent actionEvent) {System.out.println(" Из help");
    }
}
