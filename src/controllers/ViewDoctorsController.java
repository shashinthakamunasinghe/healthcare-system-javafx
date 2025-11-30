package controllers;

import application.SceneController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.event.ActionEvent;
import models.Doctor;
import utils.CSVHelper;

import java.io.IOException;

public class ViewDoctorsController {

    @FXML private TableView<Doctor> tableDoctors;
    @FXML private TableColumn<Doctor, String> colName;
    @FXML private TableColumn<Doctor, String> colId;
    @FXML private TableColumn<Doctor, String> colSpecialization;
    @FXML private TableColumn<Doctor, String> colGender;
    @FXML private TableColumn<Doctor, String> colContact;

    SceneController sceneController = new SceneController();

    @FXML
    public void initialize() {
        colName.setCellValueFactory(data -> data.getValue().nameProperty());
        colId.setCellValueFactory(data -> data.getValue().idProperty());
        colSpecialization.setCellValueFactory(data -> data.getValue().specializationProperty());
        colGender.setCellValueFactory(data -> data.getValue().genderProperty());
        colContact.setCellValueFactory(data -> data.getValue().contactNumberProperty());

        ObservableList<Doctor> doctors = FXCollections.observableArrayList(CSVHelper.loadDoctors());
        tableDoctors.setItems(doctors);
    }

    @FXML
    public void goBack(ActionEvent event) throws IOException {
        sceneController.switchToScene(event, "/views/main_menu.fxml");
    }
}
