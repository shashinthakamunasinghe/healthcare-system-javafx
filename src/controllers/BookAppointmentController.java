package controllers;

import application.SceneController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.event.ActionEvent;
import models.Appointment;
import models.Doctor;
import utils.CSVHelper;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

public class BookAppointmentController {

    @FXML private ComboBox<String> comboDoctorName;
    @FXML private TextField txtPatientId;
    @FXML private TextField txtPatientName;
    @FXML private DatePicker datePickerAppointment;
    @FXML private ComboBox<String> comboTimeSlot;
    @FXML private Label lblMessage;

    private final ObservableList<String> doctorNames = FXCollections.observableArrayList();
    private List<Doctor> doctorsList;
    SceneController sceneController = new SceneController();

    @FXML
    public void initialize() {
        // Load doctors and populate dropdown
        doctorsList = CSVHelper.loadDoctors();
        for (Doctor doctor : doctorsList) {
            doctorNames.add(doctor.getName());
        }
        comboDoctorName.setItems(doctorNames);
        
        // Initialize time slots
        comboTimeSlot.getItems().addAll(
            "09:00", "09:30", "10:00", "10:30", "11:00", "11:30",
            "12:00", "12:30", "13:00", "13:30", "14:00", "14:30",
            "15:00", "15:30", "16:00", "16:30", "17:00", "17:30"
        );
        
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
    public void bookAppointment() {
        // Validate all fields
        if (comboDoctorName.getValue() == null || txtPatientName.getText().isEmpty() || 
            txtPatientId.getText().isEmpty() || datePickerAppointment.getValue() == null || 
            comboTimeSlot.getValue() == null) {
            
            lblMessage.setText("Please fill all fields including date and time.");
            lblMessage.setStyle("-fx-text-fill: red;");
            return;
        }

        // Get selected doctor details
        String selectedDoctorName = comboDoctorName.getValue();
        String doctorId = "";
        
        // Find the doctor ID from the selected name
        for (Doctor doctor : doctorsList) {
            if (doctor.getName().equals(selectedDoctorName)) {
                doctorId = doctor.getId();
                break;
            }
        }

        String appointmentDate = datePickerAppointment.getValue().toString();
        String appointmentTime = comboTimeSlot.getValue();

        Appointment appointment = new Appointment(
            selectedDoctorName,
            doctorId,
            txtPatientName.getText(),
            txtPatientId.getText(),
            appointmentDate,
            appointmentTime
        );

        boolean success = CSVHelper.writeAppointmentToCSV(appointment);
        if (success) {
            lblMessage.setText("Appointment booked successfully for " + appointmentDate + " at " + appointmentTime + "!");
            lblMessage.setStyle("-fx-text-fill: green;");
            clearFields();
        } else {
            lblMessage.setText("Failed to book appointment.");
            lblMessage.setStyle("-fx-text-fill: red;");
        }
    }

    private void clearFields() {
        comboDoctorName.getSelectionModel().clearSelection();
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