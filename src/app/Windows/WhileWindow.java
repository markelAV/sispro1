package app.Windows;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class WhileWindow extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("WhileWindow.fxml"));
        primaryStage.setTitle("WhileWindow");
        primaryStage.setScene(new Scene(root, 585, 432));
        primaryStage.show();

    }

}
