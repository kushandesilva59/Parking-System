package controller;

import db.Database;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.Window;
import model.OnDeliveryVehicle;
import model.parkedVehicle;

import java.io.IOException;

public class ManagementFormController {


    public AnchorPane ManagementFormContext;
    public ComboBox comboBoxParking;
    public TableView tblParkingTable;
    public TableColumn colVehicleNumber;
    public TableColumn colVehicleType;
    public TableColumn colParkingSlot;
    public TableColumn colParkedTime;
    public Button btnAddVehicle;
    public TableView tblOnDelivery;
    public TableColumn clmVehicleNumber;
    public TableColumn clmVehicleType;
    public TableColumn clmDriverName;
    public TableColumn clmLeftTome;

    public void initialize(){
        colVehicleNumber.setCellValueFactory(new PropertyValueFactory("vehicleNumber"));
        colVehicleType.setCellValueFactory(new PropertyValueFactory("vehicleType"));
        colParkingSlot.setCellValueFactory(new PropertyValueFactory("parkingSlot"));
        colParkedTime.setCellValueFactory(new PropertyValueFactory("parkedTime"));

        clmVehicleNumber.setCellValueFactory(new PropertyValueFactory("vehicleNumber"));
        clmVehicleType.setCellValueFactory(new PropertyValueFactory("vehicleType"));
        clmDriverName.setCellValueFactory(new PropertyValueFactory("driverName"));
        clmLeftTome.setCellValueFactory(new PropertyValueFactory("leftTime"));

        loadAllParkedVehicles();
        loadAllOnDeliverVehicles();

        ObservableList <String> comboList = FXCollections.observableArrayList();
        comboList.add("In Parking");
        comboList.add("On Delivery");
        comboBoxParking.setItems(comboList);

    }

    public void loadAllParkedVehicles(){
        ObservableList <parkedVehicle> parkedVehicles = FXCollections.observableArrayList();

        for(parkedVehicle p1 : Database.parkedVehicles){
            parkedVehicles.add(p1);
        }
        tblParkingTable.setItems(parkedVehicles);
    }

    public void loadAllOnDeliverVehicles(){
        ObservableList <OnDeliveryVehicle> onDeliveryVehicles = FXCollections.observableArrayList();

        for(OnDeliveryVehicle o1 : Database.onDeliveryVehicles){
            onDeliveryVehicles.add(o1);
        }
        tblOnDelivery.setItems(onDeliveryVehicles);
    }

    public void addDriverOnAction(ActionEvent event) throws IOException {
        ManagementFormContext.getChildren().clear();
        Parent parent = FXMLLoader.load(getClass().getResource("../view/AddDriverForm.fxml"));
        ManagementFormContext.getChildren().add(parent);

        /*Stage stage = (Stage) btnAddVehicle.getScene().getWindow();
        stage.setScene(FXMLLoader.load(getClass().getResource("../view/AddDriverForm.fxml")));
        stage.show();*/
    }


    public void addVehicleOnAction(ActionEvent event) throws IOException {
        ManagementFormContext.getChildren().clear();
        Parent parent = FXMLLoader.load(getClass().getResource("../view/AddVehicleForm.fxml"));
        ManagementFormContext.getChildren().add(parent);
    }

    public void logOutOnAction(ActionEvent event) throws IOException {
      /* Stage stage = (Stage) ManagementFormContext.getScene().getWindow();
       stage.close();*/

        ManagementFormContext.getChildren().clear();
        Parent parent =  FXMLLoader.load(getClass().getResource("../view/ParkingSystem.fxml"));
        ManagementFormContext.getChildren().add(parent);
    }

    public void comboBoxOnAction(ActionEvent event) {
        String value = (String) comboBoxParking.getValue();
        if(value.equals("In Parking")){
            tblOnDelivery.setVisible(false);
            tblParkingTable.setVisible(true);
        }else{
            tblParkingTable.setVisible(false);
            tblOnDelivery.setVisible(true);
        }
    }
}
