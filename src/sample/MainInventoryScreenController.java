package sample;


import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import javax.xml.crypto.Data;

public class MainInventoryScreenController implements Initializable {

    @FXML
    private TableView<car> productTable;

    @FXML
    private TableColumn<car, String>  model_table;

    @FXML
    private TableColumn<car, Integer>  year_table;

    @FXML
    private TableColumn<car, Integer>  mileage_table;

    @FXML
    private TableColumn<car, String>  color_table;

    @FXML
    private TableColumn<car, Integer>  id_table;

    @FXML
    void car_serach_onAction(ActionEvent event) {


        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("car_searched_screen.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root1));
            stage.show();
        } catch(Exception e) {
            e.printStackTrace();
        }

    }


    ObservableList<car> oblist = FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources ) {

        loadDataBase();
    }

     public void loadDataBase(){

        try {
            Connection con = DBConnection.getConnection();
            ResultSet rs = con.createStatement().executeQuery("select * from car");

            while(rs.next()) {
                oblist.add(new car(rs.getString("model"), rs.getInt("year"),rs.getInt("mileage"),rs.getString("color"),rs.getInt("id")));
            }
        } catch (SQLException ex){
            ex.printStackTrace();
        }

        model_table.setCellValueFactory(new PropertyValueFactory<>("model"));
        year_table.setCellValueFactory(new PropertyValueFactory<>("year"));
        mileage_table.setCellValueFactory(new PropertyValueFactory<>("mileage"));
        color_table.setCellValueFactory(new PropertyValueFactory<>("color"));
        id_table.setCellValueFactory(new PropertyValueFactory<>("id"));

        productTable.setItems(oblist);
    }

    public void refesh(){

        oblist.clear();
        loadDataBase();
    }

}


