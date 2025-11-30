package controllers;

import application.SceneController;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.event.ActionEvent;
import models.Doctor;
import utils.CSVHelper;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AddDoctorController implements Initializable {

    @FXML
    private TextField txtName;

    @FXML
    private TextField txtSpecialization;

    @FXML
    private ComboBox<String> comboGender;

    @FXML
    private TextField txtPhone;

    private final SceneController sceneController = new SceneController();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        comboGender.getItems().addAll("Male", "Female", "Other");
    }

    @FXML
    private void saveDoctor(ActionEvent event) {
        String name = txtName.getText();
        String specialization = txtSpecialization.getText();
        String gender = comboGender.getValue();
        String phone = txtPhone.getText();

        if (name.isEmpty() || specialization.isEmpty() || gender == null || phone.isEmpty()) {
            showAlert(AlertType.ERROR, "Validation Error", "Please fill all fields.");
            return;
        }

        // Generate a doctor ID 
        String doctorId = "D" + String.format("%03d", (int)(Math.random() * 1000));
        
        // Create doctor object and save to CSV
        Doctor doctor = new Doctor(name, doctorId, specialization, gender, phone);
        boolean success = CSVHelper.writeDoctorToCSV(doctor);
        
        if (success) {
            showAlert(AlertType.INFORMATION, "Success", "Doctor added successfully.");
            clearFields();
        } else {
            showAlert(AlertType.ERROR, "Error", "Failed to save doctor.");
        }
    }

    @FXML
    private void cancel(ActionEvent event) throws IOException {
        sceneController.switchToScene(event, "/views/admin_dashboard.fxml");
    }

    private void clearFields() {
        txtName.clear();
        txtSpecialization.clear();
        comboGender.setValue(null);
        txtPhone.clear();
    }

    private void showAlert(AlertType type, String title, String message) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}