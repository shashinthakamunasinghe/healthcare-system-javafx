package controllers;

import application.SceneController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.event.ActionEvent;
import models.Patient;
import utils.CSVHelper;

import java.io.IOException;

public class ViewPatientsController {

    @FXML private TableView<Patient> tablePatients;
    @FXML private TableColumn<Patient, String> colName;
    @FXML private TableColumn<Patient, String> colId;
    @FXML private TableColumn<Patient, String> colBirthday;
    @FXML private TableColumn<Patient, String> colGender;
    @FXML private TableColumn<Patient, String> colContact;

    SceneController sceneController = new SceneController();

    @FXML
    public void initialize() {
        colName.setCellValueFactory(data -> data.getValue().nameProperty());
        colId.setCellValueFactory(data -> data.getValue().idProperty());
        colBirthday.setCellValueFactory(data -> data.getValue().birthdayProperty());
        colGender.setCellValueFactory(data -> data.getValue().genderProperty());
        colContact.setCellValueFactory(data -> data.getValue().contactNumberProperty());

        ObservableList<Patient> patients = FXCollections.observableArrayList(CSVHelper.loadPatients());
        tablePatients.setItems(patients);
    }

    @FXML
    public void goBack(ActionEvent event) throws IOException {
        sceneController.switchToScene(event, "/views/admin_dashboard.fxml");
    }
}
