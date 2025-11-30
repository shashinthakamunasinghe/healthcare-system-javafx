package controllers;

import application.SceneController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import java.io.IOException;

public class AdminDashboardController {

    SceneController sceneController = new SceneController();

    @FXML
    public void goToAddDoctor(ActionEvent event) throws IOException {
        sceneController.switchToScene(event, "/views/add_doctor.fxml");
    }

    @FXML
    public void goToAddPatient(ActionEvent event) throws IOException {
        sceneController.switchToScene(event, "/views/add_patient.fxml");
    }

    @FXML
    public void goToViewDoctors(ActionEvent event) throws IOException {
        sceneController.switchToScene(event, "/views/view_doctors.fxml");
    }

    @FXML
    public void goToViewPatients(ActionEvent event) throws IOException {
        sceneController.switchToScene(event, "/views/view_patients.fxml");
    }

    @FXML
    public void goToViewAppointments(ActionEvent event) throws IOException {
        sceneController.switchToScene(event, "/views/view_appointments.fxml");
    }

    @FXML
    public void backToMainMenu(ActionEvent event) throws IOException {
        sceneController.switchToScene(event, "/views/main_menu.fxml");
    }
    
    @FXML
    public void viewAppointments(ActionEvent event) throws IOException {
        sceneController.switchToScene(event, "/views/view_appointments.fxml");
    }

}