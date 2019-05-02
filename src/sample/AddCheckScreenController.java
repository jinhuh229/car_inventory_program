package sample;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class AddCheckScreenController {


    @FXML
    private Button ok_button;

    public void add_ok(){

        Stage stage = (Stage) ok_button.getScene().getWindow();
        stage.close();
    }
}
