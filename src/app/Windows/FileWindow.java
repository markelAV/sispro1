package app.Windows;

import app.Controllers.FileController;
import app.Model.CSVFile.EntityTable;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;

import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;

import javafx.scene.control.TableView;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;


public class FileWindow extends Application {
    private Desktop desktop = Desktop.getDesktop();
    private static Parent scene;
    private static FXMLLoader loader;
    private static Stage thisStage;
    private static final String NAME_APPLICATION="Work with file";
    private void init1() throws IOException {
        loader = new FXMLLoader(MainWindow.class.getResource("FileWindow.fxml"));
        scene = loader.load();
    }
    @Override
    public void start(Stage primaryStage) throws Exception{
        //Parent root = FXMLLoader.load(getClass().getResource("app.fxml"));
        init1();
        thisStage=primaryStage;
        primaryStage.setTitle(NAME_APPLICATION);
        primaryStage.setScene(new Scene(scene, 585, 432));
        ObservableList<Node> list =scene.getChildrenUnmodifiable();
        Pane node =(Pane)list.get(0);


        ObservableList<Node> list2=node.getChildren();
        TableView tableView =(TableView) list2.get(1);
        ObservableList<TableColumn> columns =tableView.getColumns();
        TableColumn<EntityTable,String> column=(TableColumn<EntityTable,String>)columns.get(0);

        Callback<TableColumn<EntityTable, String>,
                TableCell<EntityTable, String>> cellFactory
                = (TableColumn<EntityTable, String> p) -> new EditingCell();

        column.setCellFactory(cellFactory);
        column.setOnEditCommit(
                (TableColumn.CellEditEvent<EntityTable, String> t) -> {
                    column.getColumns().get(t.getTablePosition().getRow()).setText(t.getNewValue());
//                    ((EntityTable) t.getTableView().getItems().get(
//                            t.getTablePosition().getRow())
//                    ).setName(t.getNewValue());
                });



        javafx.scene.control.MenuBar menu =(javafx.scene.control.MenuBar)list2.get(0);
        ObservableList<javafx.scene.control.Menu> menuse = menu.getMenus();
        javafx.scene.control.Menu m= menuse.get(0);
        javafx.scene.control.Menu m1= menuse.get(1);
        javafx.scene.control.Menu n=menuse.get(2);
        final FileChooser fileChooser = new FileChooser();
        ObservableList<javafx.scene.control.MenuItem> item = m.getItems();
        ObservableList<javafx.scene.control.MenuItem> item1=n.getItems();
        ObservableList<javafx.scene.control.MenuItem> item2=m1.getItems();

        tableView.setEditable(true);
        columns.get(0).setOnEditCommit(new EventHandler<TableColumn.CellEditEvent>() {
            @Override
            public void handle(TableColumn.CellEditEvent event) {


            }
        });


        item.get(1).setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try{
                    Parent root = FXMLLoader.load(getClass().getResource("File_CreateNewFile.fxml"));
                    Scene scene = new Scene(root);
                    Stage newWindow = new Stage();
                    newWindow.setTitle("Create");
                    newWindow.setScene(scene);
                    newWindow.initModality(Modality.WINDOW_MODAL);
                    newWindow.initOwner(primaryStage);
                    newWindow.setX(primaryStage.getX() + 200);
                    newWindow.setY(primaryStage.getY() + 100);
                    newWindow.show();
                    newWindow.setResizable(false);
                    Button button =(Button) root.getChildrenUnmodifiable().get(0);
                    button.setOnAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent event) {
                            TextField text =(TextField) root.getChildrenUnmodifiable().get(2);
                            String name = text.getText();
                            if(name.indexOf(".csv")==-1){
                                name+=".csv";
                            }
                            FileController controller =loader.getController();
                            controller.create(name);
                            newWindow.close();
                            thisStage.setTitle(name+" - "+NAME_APPLICATION);

                        }
                    });
                }
                    catch (IOException e) {e.printStackTrace();}


            }
        });
        item.get(0).setOnAction(
                new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(final ActionEvent e) {
                        configureFileChooser(fileChooser);
                        File file = fileChooser.showOpenDialog(primaryStage);
                        if (file != null) {
                            openFile(file);

                        }

                    }
                });
        item.get(3).setOnAction(
                new EventHandler<ActionEvent>(){
                    @Override
                    public void handle(final ActionEvent e){
                        configureFileChooser(fileChooser);
                        File file = fileChooser.showSaveDialog(primaryStage);
                        if (file != null) {
                            saveFile(file);

                        }
                    }}
        );
        item.get(4).setOnAction(
                new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        Platform.exit();
                        System.exit(0);
                    }
                }
        );
        item2.get(0).setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try{
                    Parent root = FXMLLoader.load(getClass().getResource("File_CreateNewFile.fxml"));
                    Scene scene = new Scene(root);
                    Stage newWindow = new Stage();
                    newWindow.setTitle("Add Line");
                    newWindow.setScene(scene);
                    newWindow.initModality(Modality.WINDOW_MODAL);
                    newWindow.initOwner(primaryStage);
                    newWindow.setX(primaryStage.getX() + 200);
                    newWindow.setY(primaryStage.getY() + 100);
                    newWindow.show();
                    newWindow.setResizable(false);
                    ((Label)root.getChildrenUnmodifiable().get(1)).setText("Введите эелементы строки через \';\'");
                    Button button =(Button) root.getChildrenUnmodifiable().get(0);
                    button.setOnAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent event) {
                            TextField text =(TextField) root.getChildrenUnmodifiable().get(2);
                            FileController controller =loader.getController();
                            controller.addLine(text.getText());
                            newWindow.close();
                        }
                    });
                }
                catch (IOException e) {e.printStackTrace();}


            }
        });
        item1.get(0).setOnAction(
                new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        javafx.scene.control.Label lbl = new Label("О разработчике: Кусаинов Д.М., Маркелов А.В. \n vk.com ");

                        FlowPane secondaryLayout = new FlowPane();

                        secondaryLayout.getChildren().add(lbl);

                        Scene secondScene = new Scene(secondaryLayout, 290, 100);


                        Stage newWindow = new Stage();
                        newWindow.setTitle("About");
                        newWindow.setScene(secondScene);


                        newWindow.initModality(Modality.WINDOW_MODAL);
                        newWindow.initOwner(primaryStage);
                        newWindow.setX(primaryStage.getX() + 200);
                        newWindow.setY(primaryStage.getY() + 100);

                        newWindow.show();
                        newWindow.setResizable(false);
                    }
                }
        );




        node.getAccessibleHelp();
        primaryStage.show();
//////////



    }



    @Override
    public void stop(){
        // todo Сделать развилку для проверки изменения и последующего сохранения файла

    }
    private static void configureFileChooser(final FileChooser fileChooser){
        fileChooser.setTitle("File");
        fileChooser.setInitialDirectory(
                new File(System.getProperty("user.home"))
        );
    }

    private void openFile(File file) {
            FileController fileController = (FileController) (loader.getController());
            fileController.open(file.getAbsolutePath());
            thisStage.setTitle(file.getName()+ " - " + NAME_APPLICATION);

    }
    private void saveFile(File file) {
        try {
            String name =file.getAbsolutePath();
            if(name.indexOf(".csv")==-1){
                name+=".csv";
            }
            PrintWriter writer;
            writer = new PrintWriter(new File(name));
            writer.close();
            FileController fileController = (FileController) (loader.getController());
            fileController.saveAs(file.getAbsolutePath());
            thisStage.setTitle(file.getName()+ " - " + NAME_APPLICATION);

        } catch (FileNotFoundException e){}
    }
    private void createFile(){

    }

    class EditingCell extends TableCell<EntityTable,String>{
        private TextField textField;

        public EditingCell() {
        }

        @Override
        public void startEdit() {
            if (!isEmpty()) {
                super.startEdit();
                createTextField();
                setText(null);
                setGraphic(textField);
                textField.selectAll();
            }
        }

        @Override
        public void cancelEdit() {
            super.cancelEdit();

            setText((String) getItem());
            setGraphic(null);
        }

        @Override
        public void updateItem(String item, boolean empty) {
            super.updateItem(item, empty);

            if (empty) {
                setText(null);
                setGraphic(null);
            } else {
                if (isEditing()) {
                    if (textField != null) {
                        textField.setText(getString());
                    }
                    setText(null);
                    setGraphic(textField);
                } else {
                    setText(getString());
                    setGraphic(null);
                }
            }
        }

        private void createTextField() {
            textField = new TextField(getString());
            textField.setMinWidth(this.getWidth() - this.getGraphicTextGap()* 2);
            textField.focusedProperty().addListener(
                    (ObservableValue<? extends Boolean> arg0,
                     Boolean arg1, Boolean arg2) -> {
                        if (!arg2) {
                            commitEdit(textField.getText());
                        }
                    });
        }

        private String getString() {
            return getItem() == null ? "" : getItem().toString();
        }

    }


    ///////////



}
