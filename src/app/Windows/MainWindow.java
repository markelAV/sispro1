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
                Parent root = FXMLLoader.load(getClass().getResource("FileWindow.fxml"));
                Scene scene = new Scene(root);
                Stage newWindow = new Stage();
                newWindow.setTitle("File");
                newWindow.setScene(scene);
                newWindow.initModality(Modality.WINDOW_MODAL);
                newWindow.initOwner(primaryStage);
                newWindow.setX(primaryStage.getX() + 200);
                newWindow.setY(primaryStage.getY() + 100);
                newWindow.show();
                newWindow.setResizable(false);
            }
            catch (IOException e) {e.printStackTrace(); }
            }
        });

        menuse.get(1).getItems().get(0).setOnAction(new EventHandler<javafx.event.ActionEvent>() {
            @Override
            public void handle(javafx.event.ActionEvent event) {
                try{
            Parent root = FXMLLoader.load(getClass().getResource("ASMWindow.fxml"));
            Scene scene = new Scene(root);
            Stage newWindow = new Stage();
            newWindow.setTitle("ASMWindow");
            newWindow.setScene(scene);


            newWindow.initModality(Modality.WINDOW_MODAL);
            newWindow.initOwner(primaryStage);
            newWindow.setX(primaryStage.getX() + 200);
            newWindow.setY(primaryStage.getY() + 100);

            newWindow.show();

            newWindow.setResizable(false);
        }
        catch (IOException e) {e.printStackTrace();}

    }

        });

        menuse.get(2).getItems().get(0).setOnAction(new EventHandler<javafx.event.ActionEvent>() {
            @Override
            public void handle(javafx.event.ActionEvent event) {
                try{
           Parent root = FXMLLoader.load(getClass().getResource("WhileWindow.fxml"));
           Scene scene = new Scene(root);
           Stage newWindow = new Stage();
           newWindow.setTitle("WhileWindow");
           newWindow.setScene(scene);


           newWindow.initModality(Modality.WINDOW_MODAL);
           newWindow.initOwner(primaryStage);
           newWindow.setX(primaryStage.getX() + 200);
           newWindow.setY(primaryStage.getY() + 100);

           newWindow.show();

           newWindow.setResizable(false);
       }
       catch (IOException e) {e.printStackTrace();}

            }
        });

        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
