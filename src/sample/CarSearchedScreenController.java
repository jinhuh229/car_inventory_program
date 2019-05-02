package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class CarSearchedScreenController implements Initializable {

    @FXML
    TextField model_text;

    @FXML
    TextField year_text;

    @FXML
    TextField mileage_text;

    @FXML
    TextField color_text;

    @FXML
    Button add_button;



    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }


    @FXML
    public void AddNewUser() throws SQLException{
        String model = model_text.getText();
        int year = Integer.parseInt(year_text.getText());
        int mileage = Integer.parseInt(mileage_text.getText());
        String color = color_text.getText();


        String query = "INSERT INTO car (model, year, mileage, color) VALUES (?,?,?,?)";
        PreparedStatement preparedStatement=null;

        try {
            Connection con = DBConnection.getConnection();
            preparedStatement = con.prepareStatement(query);

            preparedStatement.setString(1, model);
            preparedStatement.setInt(2, year);
            preparedStatement.setInt(3, mileage);
            preparedStatement.setString(4, color);



        } catch (SQLException ex){
            ex.printStackTrace();
        }
        finally{
            preparedStatement.execute();
            preparedStatement.close();
        }


        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("add_check_screen.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root1));
            stage.show();
        } catch(Exception e) {
            e.printStackTrace();
        }



    }
}
