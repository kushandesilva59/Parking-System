package controller;

import com.jfoenix.controls.JFXButton;
import db.Database;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.JFXPanel;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import model.*;
import org.omg.CORBA.DATA_CONVERSION;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Optional;

public class ParkingSystemController{

    public AnchorPane parkingSysytemContext;
    public ComboBox comboBoxVehicle;
    public Label lblVehicleType;
    public ComboBox comboBoxDriver;
    public Label lblSlotNumber;
    public Label lblDate;
    public Label lblTime;
    public JFXButton btnManagementLogin;
    public JFXButton btnParkVehicle;
    public JFXButton btnOnDelivery;
    public AnchorPane logInAnchorPane;
    public AnchorPane LoginFormAnhorPane;
    public TextField loginUserName;
    public PasswordField loginPassword;
    public Button BtnCancel;
    public Stage primaryStage;
    public AnchorPane loginFormContext;


    public void initialize() {
       /* SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        Date date = new Date();
        lblDate.setText(formatter.format(date));

        formatter = new SimpleDateFormat("HH:mm");
        date = new Date();
        lblTime.setText(formatter.format(date));*/

       // setTimeDate();

        ObservableList<String> vehicleList = FXCollections.observableArrayList();
        for (Vehicle v1 : Database.vehicles) {
            vehicleList.add(v1.getVehicleNumber());
        }
        comboBoxVehicle.getItems().addAll(vehicleList);

        ObservableList<String> driverList = FXCollections.observableArrayList();
        for (Driver d1 : Database.drivers) {
            driverList.add(d1.getName());
        }
        comboBoxDriver.getItems().addAll(driverList);
    }

    public void setTimeDate(){
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat format1 = new SimpleDateFormat("aa");


        Timeline clock = new Timeline(new KeyFrame(Duration.INDEFINITE.ZERO, e -> {
            LocalTime currentTime = LocalTime.now();
            LocalDate currentDate = LocalDate.now();
            lblTime.setText(currentTime.getHour() + ":" + currentTime.getMinute() + ":" + currentTime.getSecond()+"  "+format1.format(calendar.getTime()));
            lblDate.setText(currentDate.getDayOfMonth() + "-" + currentDate.getMonthValue() + "-" + currentDate.getYear());

        }),
                new KeyFrame(Duration.seconds(1))
        );
        clock.setCycleCount(Animation.INDEFINITE);
        clock.play();
    }

    public Stage getWindowObj() throws IOException {

        return primaryStage;
    }

    public void setLblVehicleType(){

        //if bus selected slot number 14
        String vehicleNumber = (String) comboBoxVehicle.getValue();
        if(vehicleNumber.equals("NA-3434")){
            lblSlotNumber.setText("14");
        }

        //set label to vehicle type
                for(Vehicle v2 : Database.vehicles){
                    if(v2.getVehicleNumber().equals(vehicleNumber)){
                        lblVehicleType.setText(v2.getVehicleType());
                        if(v2.getVehicleType().equals("Van")){
                            Slot [] slotVans = Database.slotVans;

                            for(int i = 0; i < slotVans.length; i++){
                                if(slotVans[i].isEmpty()){
                                    lblSlotNumber.setText(String.valueOf(slotVans[i].getSlotNumber()));
                                    // slotVans[i].setEmpty(false);
                                    break;
                                }
                            }
                        }else if (v2.getVehicleType().equals("Cargo Lorry")){
                            Slot [] slotLorry = Database.slotLorry;

                            for(int i = 0; i < slotLorry.length; i++){
                                if(slotLorry[i].isEmpty()){
                                    lblSlotNumber.setText(String.valueOf(slotLorry[i].getSlotNumber()));
                                    //   slotLorry[i].setEmpty(false);
                                    break;
                                }
                            }
                        }
                    }
                }

       /* String vehicle = getVehicleTypeByLabel();
        if(vehicle.equals("Van")){
            Slot [] slotVans = Database.slotVans;

            for(int i = 0; i < slotVans.length; i++){
                if(slotVans[i].isEmpty()){
                    lblSlotNumber.setText(String.valueOf(slotVans[i].getSlotNumber()));
                   // slotVans[i].setEmpty(false);
                    break;
                }
            }
        }else if(vehicle.equals("Cargo Lorry")){
            Slot [] slotLorry = Database.slotLorry;

            for(int i = 0; i < slotLorry.length; i++){
                if(slotLorry[i].isEmpty()){
                    lblSlotNumber.setText(String.valueOf(slotLorry[i].getSlotNumber()));
                 //   slotLorry[i].setEmpty(false);
                    break;
                }
            }
        }*/
    }

    public void parkVehicleOnAction(ActionEvent event) {

        String vehicleNumber = (String) comboBoxVehicle.getValue();
        if(vehicleNumber != null){
         for(OnDeliveryVehicle o1 : Database.onDeliveryVehicles){
             if(o1.getVehicleNumber().equals(vehicleNumber)){
                     Database.onDeliveryVehicles.remove(o1);
                     break;
             }
         }

       for( Vehicle v1 : Database.vehicles){
           if(vehicleNumber.equals(v1.getVehicleNumber())){
               if(v1.getVehicleType().equals("Van")){
                   Slot[] slotVans = Database.slotVans;
                   if(slotVans.length < 7){
                       for (int i = 0; i<slotVans.length; i++){
                           if(slotVans[i].isEmpty()){
                               slotVans[i].setEmpty(false);
                               break;
                           }
                       }
                   }else{
                       new Alert(Alert.AlertType.WARNING,"Slots are full!..").show();
                   }

               }else if(v1.getVehicleType().equals("Cargo Lorry")){
                   Slot[] slotLorry = Database.slotLorry;
                   if(slotLorry.length < 8){
                       for (int i = 0; i<slotLorry.length; i++){
                           if(slotLorry[i].isEmpty()){
                               slotLorry[i].setEmpty(false);
                               break;
                           }
                       }
                   }else{
                       new Alert(Alert.AlertType.WARNING,"Slots are full..").show();
                   }
               }
           }
       }
            parkedVehicle p1 = new parkedVehicle(vehicleNumber,lblVehicleType.getText(),Integer.valueOf(lblSlotNumber.getText()),lblDate.getText()+"-"+lblTime.getText());
            Database.parkedVehicles.add(p1);

            new Alert(Alert.AlertType.CONFIRMATION,"Parking successfull!").show();
            btnParkVehicle.setDisable(true);
            btnOnDelivery.setDisable(false);
        }else{
            new Alert(Alert.AlertType.WARNING,"Select a Vehicle Number!..").show();
        }


    }

    public void onDeleveryShiftOnAction(ActionEvent event) {
        String vehicleNumberText = (String) comboBoxVehicle.getValue();
        String vehicleTypeText = lblVehicleType.getText();
        String driverNameText = (String) comboBoxDriver.getValue();
        String timeText = lblDate.getText() + "-" + lblTime.getText();

        if(vehicleNumberText != null && vehicleTypeText != null && driverNameText != null){
            for(parkedVehicle p1 :Database.parkedVehicles){
                //String comboBoxNumber = (String)comboBoxVehicle.getValue();
                if(vehicleNumberText.equals(p1.getVehicleNumber())){
                    if(p1.getVehicleType().equals("Van")){
                        Slot[] slotVans = Database.slotVans;
                        slotVans[p1.getParkingSlot()].setEmpty(true);
                    }else if(p1.getVehicleType().equals("Cargo Lorry")){
                        Slot[] slotLorry = Database.slotLorry;
                        slotLorry[p1.getParkingSlot()].setEmpty(true);
                    }
                    Database.parkedVehicles.remove(p1);
                    break;
                }
            }

            OnDeliveryVehicle o1 = new OnDeliveryVehicle(vehicleNumberText,vehicleTypeText,driverNameText,timeText);
            Database.onDeliveryVehicles.add(o1);
            new Alert(Alert.AlertType.CONFIRMATION,"success!..").show();
            btnOnDelivery.setDisable(true);
            btnParkVehicle.setDisable(false);
        }else{
            new Alert(Alert.AlertType.WARNING,"Select a Vehicle and a Driver!..").show();
        }
    }

    public void managementLoginOnAction(ActionEvent event) throws IOException {
     //   logInAnchorPane = loginFormContext;

        Scene scene = new Scene(FXMLLoader.load(getClass().getResource("../view/ManagementLogInForm.fxml")));
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();

        Stage stage1 = (Stage)parkingSysytemContext.getScene().getWindow();
        stage1.close();

    }

    public void clickedOnAction(ActionEvent event) {
        for(parkedVehicle p1 : Database.parkedVehicles) {
            if(p1.getVehicleNumber().equals(comboBoxVehicle.getValue())){
                btnParkVehicle.setDisable(true);
                break;
            }else{
                btnParkVehicle.setDisable(false);
            }
        }

        setLblVehicleType();

    }

   /* public String getVehicleTypeByLabel(){
        String vehicleType = lblVehicleType.getText();
        return vehicleType;
    }

    public void CancelOnAction(ActionEvent event) {
    }

    public void LogInOnAction(ActionEvent event) {
    }*/
}
