package app.Windows;

import app.Controllers.FileController;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;

public class FileWindow extends Application {
    private Desktop desktop = Desktop.getDesktop();
    private static Parent scene;
    private static FXMLLoader loader;
    private void init1() throws IOException{
        loader = new FXMLLoader(MainWindow.class.getResource("FileWindow.fxml"));//.load(getClass().getResource("app.fxml"));
        scene = loader.load();
    }
    @Override
    public void start(Stage primaryStage) throws Exception{
        //Parent root = FXMLLoader.load(getClass().getResource("app.fxml"));
        init1();
        primaryStage.setTitle("Work with file");
        primaryStage.setScene(new Scene(scene, 585, 432));
        ObservableList<Node> list =scene.getChildrenUnmodifiable();
        Pane node =(Pane)list.get(0);
        //  Platform.exit();

        ObservableList<Node> list2=node.getChildren();
        javafx.scene.control.MenuBar menu =(javafx.scene.control.MenuBar)list2.get(1);
        ObservableList<javafx.scene.control.Menu> menuse = menu.getMenus();
        javafx.scene.control.Menu m= menuse.get(0);
        javafx.scene.control.Menu n=menuse.get(2);
        final FileChooser fileChooser = new FileChooser();
        ObservableList<javafx.scene.control.MenuItem> item = m.getItems();
        ObservableList<javafx.scene.control.MenuItem> item1=n.getItems();

        item.get(1).setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try{ Parent root = FXMLLoader.load(getClass().getResource("File_CreateNewFile.fxml"));
                    Scene scene = new Scene(root);
                    Stage newWindow = new Stage();
                    newWindow.setTitle("Create");
                    newWindow.setScene(scene);
                    newWindow.initModality(Modality.WINDOW_MODAL);
                    newWindow.initOwner(primaryStage);
                    newWindow.setX(primaryStage.getX() + 200);
                    newWindow.setY(primaryStage.getY() + 100);
                    newWindow.show();
                    newWindow.setResizable(false);}
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



    }
    @Override
    public void stop(){
        // todo Сделать развилку для проверки изменения и последующего сохранения файла

    }
    private static void configureFileChooser(final FileChooser fileChooser){
        fileChooser.setTitle("View Pictures");
        fileChooser.setInitialDirectory(
                new File(System.getProperty("user.home"))
        );
    }

    private void openFile(File file) {
  //      try {
            //desktop.open(file);
            //FXMLLoader root = new FXMLLoader(MainWindow.class.getResource("app.fxml"));//.load(getClass().getResource("app.fxml"));
           // Parent sceneFXML = root.load();
            FileController fileController = (FileController) (loader.getController());
            fileController.open(file.getAbsolutePath());
            fileController.update(new ActionEvent());
//        } catch (IOException ex) {
//            Logger.getLogger(
//                    MainWindow.class.getName()).log(
//                    Level.SEVERE, null, ex
//
//            );
//        }
    }
    private void saveFile(File file) {
        try {
            PrintWriter writer;
            writer = new PrintWriter(file);
            writer.close();
        } catch (FileNotFoundException e){}
    }
    private void createFile(){

    }


}
