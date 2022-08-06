package controller;

import db.Database;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import model.Vehicle;

import java.io.IOException;
import java.util.Optional;

public class AddVehicleFormController {
    public AnchorPane addVehicleContext;
    public TextField txtVehicleNumber;
    public TextField txtMaximumWeight;
    public TextField txtNoOfPassengers;
    public ComboBox comboBoxVehicleType;

    public void initialize(){
        comboBoxVehicleType.getItems().add("Van");
        comboBoxVehicleType.getItems().add("Cargo Lorry");
        comboBoxVehicleType.getItems().add("Bus");
    }

    public void addVehicleOnAction(ActionEvent event) throws IOException {

        if(txtVehicleNumber.getText() != null && txtMaximumWeight.getText() != null && txtNoOfPassengers.getText() != null && comboBoxVehicleType.getValue() != null){
            Vehicle v1 = new Vehicle(txtVehicleNumber.getText(),(String) comboBoxVehicleType.getValue(),Integer.valueOf(txtMaximumWeight.getText()),Integer.valueOf(txtNoOfPassengers.getText()));

          //  Optional<ButtonType> buttonType = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure?", ButtonType.YES, ButtonType.NO).showAndWait();
           // if (buttonType.equals(ButtonType.YES)){
                Database.vehicles.add(v1);
                new Alert(Alert.AlertType.CONFIRMATION,"Vehicle succsessfully added!..").show();

           /* }else{
                txtVehicleNumber.clear();
                txtMaximumWeight.clear();
                txtNoOfPassengers.clear();
            }*/

        }else{
            new Alert(Alert.AlertType.WARNING,"Incorrect values !").show();
        }
    }

    public void backOnAction(ActionEvent event) throws IOException {
        addVehicleContext.getChildren().clear();
        Parent parent = FXMLLoader.load(getClass().getResource("../view/ManagementForm.fxml"));
        addVehicleContext.getChildren().add(parent);
    }
}
