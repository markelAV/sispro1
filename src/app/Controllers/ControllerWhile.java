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
        if(text!=null && text.length()>1){ //todo 1 символ это от балды - по-хорошему надо посчитать минимальное количество символов
            whileModule = new WhileModule();
            try {
                if(whileModule.control(text)){
                    result.append("Цикл выполнится более одного раза"); //todo добавить флаг "do" и проверәтғ его для ввывода коректного сообщения
                }
                else{
                    result.append("Цикд не выполниться более одного раза");
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
