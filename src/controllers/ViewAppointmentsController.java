package controllers;

import application.SceneController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.event.ActionEvent;
import models.Appointment;
import utils.CSVHelper;

import java.io.IOException;

public class ViewAppointmentsController {

    @FXML private TableView<Appointment> tableAppointments;
    @FXML private TableColumn<Appointment, String> colDoctorName;
    @FXML private TableColumn<Appointment, String> colDoctorId;
    @FXML private TableColumn<Appointment, String> colPatientName;
    @FXML private TableColumn<Appointment, String> colPatientId;
    @FXML private TableColumn<Appointment, String> colAppointmentDate;
    @FXML private TableColumn<Appointment, String> colAppointmentTime;

    SceneController sceneController = new SceneController();

    @FXML
    public void initialize() {
        colDoctorName.setCellValueFactory(data -> data.getValue().doctorNameProperty());
        colDoctorId.setCellValueFactory(data -> data.getValue().doctorIdProperty());
        colPatientName.setCellValueFactory(data -> data.getValue().patientNameProperty());
        colPatientId.setCellValueFactory(data -> data.getValue().patientIdProperty());
        colAppointmentDate.setCellValueFactory(data -> data.getValue().appointmentDateProperty());
        colAppointmentTime.setCellValueFactory(data -> data.getValue().appointmentTimeProperty());

        ObservableList<Appointment> appointments = FXCollections.observableArrayList(CSVHelper.loadAppointments());
        tableAppointments.setItems(appointments);
    }

    @FXML
    public void goBack(ActionEvent event) throws IOException {
        sceneController.switchToScene(event, "/views/main_menu.fxml");
    }
}