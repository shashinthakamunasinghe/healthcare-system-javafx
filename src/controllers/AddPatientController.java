package controllers;

import application.SceneController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import models.Patient;
import utils.CSVHelper;

import java.io.IOException;

public class AddPatientController {

    @FXML private TextField txtName;
    @FXML private TextField txtId;
    @FXML private DatePicker dateBirthday;
    @FXML private ComboBox<String> comboGender;
    @FXML private TextField txtContact;

    SceneController sceneController = new SceneController();

    @FXML
    public void initialize() {
        comboGender.getItems().addAll("Male", "Female", "Other");
    }

    @FXML
    public void savePatient(ActionEvent event) {
        String name = txtName.getText();
        String id = txtId.getText();
        String birthday = dateBirthday.getValue() != null ? dateBirthday.getValue().toString() : "";
        String gender = comboGender.getValue();
        String contact = txtContact.getText();

        if (name.isEmpty() || id.isEmpty() || birthday.isEmpty() || gender == null || contact.isEmpty()) {
            showAlert("Validation Error", "Please fill all fields");
            return;
        }

        Patient patient = new Patient(name, id, birthday, gender, contact);
        boolean success = CSVHelper.writePatientToCSV(patient);

        if (success) {
            showAlert("Success", "Patient saved successfully!");
            txtName.clear();
            txtId.clear();
            dateBirthday.setValue(null);
            comboGender.setValue(null);
            txtContact.clear();
        } else {
            showAlert("Error", "Failed to save patient.");
        }
    }

    @FXML
    public void goBack(ActionEvent event) throws IOException {
        sceneController.switchToScene(event, "/views/admin_dashboard.fxml");
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
