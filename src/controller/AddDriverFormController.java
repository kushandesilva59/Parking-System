package controller;

import db.Database;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import model.Driver;

import java.io.IOException;

public class AddDriverFormController {
    public AnchorPane addDiverContext;
    public TextField txtDriverName;
    public TextField txtNIC;
    public TextField txtDrivingLNo;
    public TextField txtAddress;
    public TextField txtContact;
    public Button btnAddDriver;

    public void addDriverOnAction(ActionEvent event) {
        if(txtDriverName.getText() != null && txtNIC.getText() != null && txtDrivingLNo.getText() != null && txtAddress.getText() != null && txtContact.getText() != null){
            Driver d1 = new Driver(
                    txtDriverName.getText(),
                    txtNIC.getText(),
                    txtDrivingLNo.getText(),
                    txtAddress.getText(),
                    txtContact.getText()
            );
            Database.drivers.add(d1);
            new Alert(Alert.AlertType.CONFIRMATION,"Driver succsessfully added!..").show();
        }
    }

    public void backOnAction(ActionEvent event) throws IOException {
        addDiverContext.getChildren().clear();
        Parent parent = FXMLLoader.load(getClass().getResource("../view/ManagementForm.fxml"));
        addDiverContext.getChildren().add(parent);
    }
}
