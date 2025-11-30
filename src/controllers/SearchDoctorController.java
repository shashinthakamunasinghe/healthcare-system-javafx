// File: controllers/SearchDoctorController.java
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
import java.util.List;
import java.util.stream.Collectors;

public class SearchDoctorController {

    @FXML private TextField txtSpecialization;
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
    }

    @FXML
    public void searchDoctor() {
        String specialization = txtSpecialization.getText().toLowerCase();
        List<Doctor> allDoctors = CSVHelper.loadDoctors();
        List<Doctor> filtered = allDoctors.stream()
            .filter(d -> d.getSpecialization().toLowerCase().contains(specialization))
            .collect(Collectors.toList());

        ObservableList<Doctor> list = FXCollections.observableArrayList(filtered);
        tableDoctors.setItems(list);
    }

    @FXML
    public void goBack(ActionEvent event) throws IOException {
        sceneController.switchToScene(event, "/views/patient_dashboard.fxml");
    }
}
