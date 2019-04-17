package app.Windows;

import app.Controllers.DataBaseWindowController;
import app.Controllers.FileController;
import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.MenuBar;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.awt.*;
import java.io.IOException;

public class DataBaseWindow extends Application {
    private static Parent scene;
    private static FXMLLoader loader;
    private void init1() throws IOException {
        loader = new FXMLLoader(MainWindow.class.getResource("DataBaseWindow.fxml"));
        scene = loader.load();
    }
    @Override
    public void start(Stage primaryStage) throws Exception{
        init1();
        primaryStage.setTitle("DataBase");
        primaryStage.setScene(new Scene(scene, 585, 432));
        ObservableList<Node> list = scene.getChildrenUnmodifiable();
        ((MenuBar)list.get(0)).getMenus().get(1).getItems().get(0).setOnAction(new EventHandler<ActionEvent>() {
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
                    ((javafx.scene.control.Label)root.getChildrenUnmodifiable().get(1)).setText("Введите эелементы строки через \';\'");
                    javafx.scene.control.Button button =(javafx.scene.control.Button) root.getChildrenUnmodifiable().get(0);
                    button.setOnAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent event) {
                            javafx.scene.control.TextField text =(TextField) root.getChildrenUnmodifiable().get(2);
                            DataBaseWindowController controller =loader.getController();
                            controller.addLine(text.getText());
                            newWindow.close();
                        }
                    });
                }
                catch (IOException e) {e.printStackTrace();}

            }
        });
        primaryStage.show();


    }
}
