package sample;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class AddScucessScreenController {


    @FXML
    private Button ok_button;

    public void add_ok_add(){

        Stage stage = (Stage) ok_button.getScene().getWindow();
        stage.close();
    }

}
