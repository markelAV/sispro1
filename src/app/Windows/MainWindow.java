package app.Windows;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.stage.Modality;
import javafx.stage.Stage;
import java.io.IOException;

public class MainWindow extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{

        Parent root = FXMLLoader.load(getClass().getResource("MainWindow.fxml"));
        primaryStage.setTitle("MainWindow Menu");
        primaryStage.setScene(new Scene(root, 585, 432));
        ObservableList<Node> list =root.getChildrenUnmodifiable();
        MenuBar menu = (MenuBar) list.get(0);
        ObservableList<javafx.scene.control.Menu> menuse = menu.getMenus();

        menuse.get(0).getItems().get(0).setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(javafx.event.ActionEvent event) {
                try {
                    Stage stage = new Stage();
                    stage.initModality(Modality.APPLICATION_MODAL);
                    FileWindow fileWindow = new FileWindow();
                    fileWindow.start(stage);
                }
                catch (Exception e){ System.out.println(e.getMessage());}
            }
        });

        menuse.get(1).getItems().get(0).setOnAction(new EventHandler<javafx.event.ActionEvent>() {
            @Override
            public void handle(javafx.event.ActionEvent event) {
                try{
                    ASMWindow window = new ASMWindow();
                    Stage stage = new Stage();
                    stage.initModality(Modality.APPLICATION_MODAL);
                    window.start(stage);
                }
                catch (Exception e) {e.printStackTrace();}
            }
        });

        menuse.get(2).getItems().get(0).setOnAction(new EventHandler<javafx.event.ActionEvent>() {
            @Override
            public void handle(javafx.event.ActionEvent event) {
                try{
                    WhileWindow window = new WhileWindow();
                    Stage stage=new Stage();
                    stage.initModality(Modality.APPLICATION_MODAL);
                    window.start(stage);
                }
       catch (Exception e) {e.printStackTrace();}
            }
        });
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
