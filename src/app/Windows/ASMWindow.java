package app.Windows;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class ASMWindow extends Application {
    private static Parent scene;
    private static FXMLLoader loader;
    private void init1() throws IOException {
        loader = new FXMLLoader(MainWindow.class.getResource("ASMWindow.fxml"));//.load(getClass().getResource("app.fxml"));
        scene = loader.load();
    }
    @Override
    public void start(Stage primaryStage) throws Exception{
        init1();
        primaryStage.setTitle("ASMModule");
        primaryStage.setScene(new Scene(scene, 585, 432));
        primaryStage.show();

    }
}
