package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class ManagementLogInFormController{
    public AnchorPane ManagementLoginContext;
    public TextField txtUserName;
    public PasswordField pwdPassword;
    public Button btnCancel;

    public void cancelOnAction(ActionEvent event) throws IOException {
       Stage stage = (Stage)btnCancel.getScene().getWindow();
       stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/ParkingSystem.fxml"))));
       stage.show();

       
       /*  Stage stage1 = new Stage();
       stage1.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/ManagementLogInForm.fxml"))));
       stage1.show();
    */
    }

    public void logInOnAction(ActionEvent event) throws IOException {
        if(txtUserName.getText().equals("admin") && pwdPassword.getText().equals("admin")){
            Stage stage = (Stage)btnCancel.getScene().getWindow();
            stage.close();

            Stage stage1 = new Stage();
            stage1.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/ManagementForm.fxml"))));
            stage1.show();

            /*ManagementLoginContext.getChildren().clear();
            Parent parent = FXMLLoader.load(getClass().getResource("../view/ManagementForm.fxml"));
            ManagementLoginContext.getChildren().add(parent);*/
        }else{
            new Alert(Alert.AlertType.WARNING,"Incorrect User name or password!..").show();

        }
    }
}
