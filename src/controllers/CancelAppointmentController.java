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
import java.util.List;

public class CancelAppointmentController {

    @FXML private TableView<Appointment> tableAppointments;
    @FXML private TableColumn<Appointment, String> colDoctorName;
    @FXML private TableColumn<Appointment, String> colDoctorId;
    @FXML private TableColumn<Appointment, String> colPatientName;
    @FXML private TableColumn<Appointment, String> colPatientId;
    @FXML private Label lblStatus;

    private final SceneController sceneController = new SceneController();

    @FXML
    public void initialize() {
        colDoctorName.setCellValueFactory(data -> data.getValue().doctorNameProperty());
        colDoctorId.setCellValueFactory(data -> data.getValue().doctorIdProperty());
        colPatientName.setCellValueFactory(data -> data.getValue().patientNameProperty());
        colPatientId.setCellValueFactory(data -> data.getValue().patientIdProperty());

        loadAppointments();
    }

    private void loadAppointments() {
        List<Appointment> appointments = CSVHelper.loadAppointments();
        ObservableList<Appointment> appointmentList = FXCollections.observableArrayList(appointments);
        tableAppointments.setItems(appointmentList);
    }

    @FXML
    public void handleCancel(ActionEvent event) {
        Appointment selected = tableAppointments.getSelectionModel().getSelectedItem();
        if (selected == null) {
            lblStatus.setText("Please select an appointment to cancel.");
            return;
        }

        boolean removed = CSVHelper.deleteAppointment(selected);
        if (removed) {
            lblStatus.setText("Appointment cancelled successfully.");
            loadAppointments(); // refresh table
        } else {
            lblStatus.setText("Failed to cancel the appointment.");
        }
    }

    @FXML
    public void goBack(ActionEvent event) throws IOException {
        sceneController.switchToScene(event, "/views/patient_dashboard.fxml");
    }
}
