package sample;


import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
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
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
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


    // -----------------------  searching table --------------------------------------
    @FXML
    private TableView<car> search_table;

    @FXML
    private TableColumn<car, String>  model_table_search;

    @FXML
    private TableColumn<car, Integer>  year_table_search;

    @FXML
    private TableColumn<car, Integer>  mileage_table_search;

    @FXML
    private TableColumn<car, String>  color_table_search;

    @FXML
    private TableColumn<car, Integer>  id_table_search;

    @FXML
    Button deleteItem;

    @FXML
    TextField model_search;

    @FXML
    TextField year_search;

    @FXML
    TextField mileage_search;

    @FXML
    TextField color_search;

    @FXML
    TextField search_text;


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
    ObservableList<car> oblist1 = FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources ) {

        loadDataBase();
    }

    @FXML
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

    // --------------------------- Refresh the list on the table after add or delete  ----------------------------
    @FXML
    public void refesh(){

        oblist.clear();
        oblist1.clear();
        loadDataBase();
    }


    // --------------------------- show the item from the list & database on search part ----------------------------
    @FXML
    public void showColumnTable() throws SQLException{
        PreparedStatement preparedStatement=null;
        //productTable.getItems().removeAll(productTable.getSelectionModel().getSelectedItem());

        try {
            car car =(car)productTable.getSelectionModel().getSelectedItem();
            String query = "SELECT * FROM car";
            Connection con = DBConnection.getConnection();
            preparedStatement = con.prepareStatement(query);

            model_search.setText(car.getModel());
            year_search.setText(String.valueOf(car.getYear()));
            mileage_search.setText(String.valueOf(car.getMileage()));
            color_search.setText(car.getColor());

            preparedStatement.close();





        } catch (SQLException ex){
            ex.printStackTrace();
        }
    }


    // --------------------------- Delete a item from the list & database ----------------------------
    @FXML
    public void deleteColumnTable() throws SQLException{

        PreparedStatement preparedStatement=null;

        try {
            car car =(car)productTable.getSelectionModel().getSelectedItem();
            String query = "DELETE FROM car WHERE model=?";
            Connection con = DBConnection.getConnection();
            preparedStatement = con.prepareStatement(query);
            preparedStatement.setString(1, car.getModel());
            preparedStatement.executeUpdate();

            preparedStatement.close();

            Alert alert = new Alert(AlertType.CONFIRMATION);
            alert.setTitle("Confirmation Dialog");
            alert.setHeaderText("Look, a Confirmation Dialog");
            alert.setContentText("Delete Complete");

            refesh();



        } catch (SQLException ex){
            ex.printStackTrace();
        }


    }


    // --------------------------- Reset a item from the searching boxes ----------------------------
    @FXML
    public void resetButton() {

        model_search.clear();
        year_search.clear();
        mileage_search.clear();
        color_search.clear();


    }



    // --------------------------- Search the items ----------------------------
    @FXML
    public void Search_items() throws SQLException{



            PreparedStatement preparedStatement=null;

            try {

                String query = "SELECT * FROM car WHERE model LIKE '%" + search_text.getText() + "%'"
                                    + "UNION SELECT * FROM car WHERE year LIKE '%" + search_text.getText() + "%'"
                                    + "UNION SELECT * FROM car WHERE mileage LIKE '%" + search_text.getText() + "%'"
                                    + "UNION SELECT * FROM car WHERE color LIKE '%" + search_text.getText() + "%'";
                Connection con = DBConnection.getConnection();
                preparedStatement = con.prepareStatement(query);

                //preparedStatement.setString(1, model_search.getText());

                ResultSet rs = preparedStatement.executeQuery();

                while(rs.next()){

                    oblist1.add(new car(rs.getString("model"), rs.getInt("year"),rs.getInt("mileage"),rs.getString("color"),rs.getInt("id")));

                }

                //preparedStatement.close();


            } catch (SQLException ex){
                ex.printStackTrace();
            }


        model_table_search.setCellValueFactory(new PropertyValueFactory<>("model"));
        year_table_search.setCellValueFactory(new PropertyValueFactory<>("year"));
        mileage_table_search.setCellValueFactory(new PropertyValueFactory<>("mileage"));
        color_table_search.setCellValueFactory(new PropertyValueFactory<>("color"));
        id_table_search.setCellValueFactory(new PropertyValueFactory<>("id"));

        search_table.setItems(oblist1);

    }

    // --------------------------- Delete a item from the list & database ----------------------------
    @FXML
    public void AddNewUser() throws SQLException{
        String model = model_search.getText();
        int year = Integer.parseInt(year_search.getText());
        int mileage = Integer.parseInt(mileage_search.getText());
        String color = color_search.getText();


        if(model_search.getText().isEmpty() || year_search.getText().isEmpty() || mileage_search.getText().isEmpty() || color_search.getText().isEmpty()){
            try {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("add_check_screen.fxml"));
                Parent root1 = (Parent) fxmlLoader.load();
                Stage stage = new Stage();
                stage.setScene(new Scene(root1));
                stage.show();
            } catch(Exception e) {
                e.printStackTrace();
            }
        } else {
            String query = "INSERT INTO car (model, year, mileage, color) VALUES (?,?,?,?)";
            PreparedStatement preparedStatement = null;

            try {
                Connection con = DBConnection.getConnection();
                preparedStatement = con.prepareStatement(query);

                preparedStatement.setString(1, model);
                preparedStatement.setInt(2, year);
                preparedStatement.setInt(3, mileage);
                preparedStatement.setString(4, color);



            } catch (SQLException ex) {
                ex.printStackTrace();
            } finally {
                preparedStatement.execute();
                preparedStatement.close();
            }


            try {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("add_scucess_screen.fxml"));
                Parent root1 = (Parent) fxmlLoader.load();
                Stage stage = new Stage();
                stage.setScene(new Scene(root1));
                stage.show();
            } catch(Exception e) {
                e.printStackTrace();
            }


            refesh();
        }

    }
}




