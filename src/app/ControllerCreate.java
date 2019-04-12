package app;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class ControllerCreate {
    @FXML
    TextField tfDisplay;

    @FXML
    private void handleButtonAction(ActionEvent event){
        Button okButton=((Button)event.getSource());
        String currentButtonPress=okButton.getText();


    }
}
