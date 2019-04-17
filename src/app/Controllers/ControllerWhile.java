package app.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import app.Model.While.InvalidOperationException;
import app.Model.While.ParseConstException;
import app.Model.While.ParseIdentifierException;
import app.Model.While.WhileModule;

public class ControllerWhile {
    @FXML
    private TextArea textWhile;
    @FXML
    private TextArea textResult;


    public void checkWhile(ActionEvent actionEvent) {
        String text = textWhile.getText();
        StringBuilder result = new StringBuilder();
        WhileModule whileModule;
        if(text!=null && text.length()>1){
            whileModule = new WhileModule();
            try {
                if(whileModule.control(text)){
                    if(whileModule.isFlagDo()) {
                        result.append("Цикл выполнится более одного раза");
                    }
                    else{
                        result.append("Цикл выполнится хотя бы 1 раз");
                    }
                }
                else {
                    if (whileModule.isFlagDo()) {
                        result.append("Цикл не выполнится более одного раза");
                    } else {
                        result.append("Цикл не выполнится ни разу");

                    }
                }

            } catch (InvalidOperationException e){
                result.append(e.getMessage());
            } catch (ParseIdentifierException e){
                result.append(e.getMessage());
            } catch (ParseConstException e) {
                result.append(e.getMessage());
            }
            finally {
                textResult.setText(result.toString());
            }

        }
    }
}
