package controllers;

import application.SceneController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.event.ActionEvent;
import models.Appointment;
import models.Doctor;
import models.Patient;
import utils.CSVHelper;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

public class DoctorBookingController {

    @FXML private ComboBox<String> comboDoctor;
    @FXML private TextField txtDoctorId;
    @FXML private TextField txtPatientName;
    @FXML private TextField txtPatientId;
    @FXML private DatePicker datePickerAppointment;
    @FXML private ComboBox<String> comboTimeSlot;
    @FXML private Label lblStatus;

    private final ObservableList<String> doctorNames = FXCollections.observableArrayList();
    private final SceneController sceneController = new SceneController();

    @FXML
    public void initialize() {
        List<Doctor> doctors = CSVHelper.loadDoctors();
        for (Doctor doc : doctors) {
            doctorNames.add(doc.getName());
        }
        comboDoctor.setItems(doctorNames);

        comboDoctor.setOnAction(event -> {
            String selectedName = comboDoctor.getSelectionModel().getSelectedItem();
            for (Doctor doc : doctors) {
                if (doc.getName().equals(selectedName)) {
                    txtDoctorId.setText(doc.getId());
                    break;
                }
            }
        });

        // Initialize time slots
        comboTimeSlot.getItems().addAll(
            "09:00", "09:30", "10:00", "10:30", "11:00", "11:30",
            "12:00", "12:30", "13:00", "13:30", "14:00", "14:30",
            "15:00", "15:30", "16:00", "16:30", "17:00", "17:30"
        );
        
        // Set minimum date to today
        datePickerAppointment.setValue(LocalDate.now());
        datePickerAppointment.setDayCellFactory(picker -> new DateCell() {
            @Override
            public void updateItem(LocalDate date, boolean empty) {
                super.updateItem(date, empty);
                if (date.isBefore(LocalDate.now())) {
                    setDisable(true);
                    setStyle("-fx-background-color: #ffc0cb;");
                }
            }
        });
    }

    @FXML
    public void handleBook(ActionEvent event) {
        String doctorName = comboDoctor.getValue();
        String doctorId = txtDoctorId.getText();
        String patientName = txtPatientName.getText();
        String patientId = txtPatientId.getText();
        
        if (doctorName == null || doctorId.isEmpty() || patientName.isEmpty() || patientId.isEmpty() ||
            datePickerAppointment.getValue() == null || comboTimeSlot.getValue() == null) {
            lblStatus.setText("Please fill in all fields including date and time.");
            return;
        }

        String appointmentDate = datePickerAppointment.getValue().toString();
        String appointmentTime = comboTimeSlot.getValue();

        Appointment appointment = new Appointment(doctorName, doctorId, patientName, patientId, appointmentDate, appointmentTime);
        boolean success = CSVHelper.writeAppointmentToCSV(appointment);
        if (success) {
            lblStatus.setText("Appointment booked successfully for " + appointmentDate + " at " + appointmentTime + ".");
            clearFields();
        } else {
            lblStatus.setText("Failed to book appointment.");
        }
    }

    private void clearFields() {
        comboDoctor.getSelectionModel().clearSelection();
        txtDoctorId.clear();
        txtPatientName.clear();
        txtPatientId.clear();
        datePickerAppointment.setValue(LocalDate.now());
        comboTimeSlot.setValue(null);
    }

    @FXML
    public void goBack(ActionEvent event) throws IOException {
        sceneController.switchToScene(event, "/views/patient_dashboard.fxml");
    }
}