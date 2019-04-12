package app.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class ASMController {
    @FXML
    private TextArea resultArea;
    @FXML
    private TextField value1;
    @FXML
    private TextField value2;
    @FXML
    private RadioButton checkDiv;
    @FXML
    private RadioButton checkAnd;

    private double validation(String s){
       //todo прикрутитғ проверки (валидацйию и бросать exception)
        return Double.parseDouble(s);
    }

    public void count(ActionEvent actionEvent) { //todo переделать на работу с модулем
        StringBuilder builder = new StringBuilder();
        double value1 = validation(this.value1.getText());
        double value2 = validation(this.value2.getText());
        builder.append("Операция прошла успешно. \nРезультат: ");
        if(checkDiv.isSelected()){
            builder.append(value1/value2);
        }
        else{
            builder.append((int)value1&(int)value2);
        }
        resultArea.setText(builder.toString());
    }
}
