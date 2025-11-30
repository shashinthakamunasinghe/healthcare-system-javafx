package controllers;

import application.SceneController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import java.io.IOException;

public class PatientDashboardController {

    SceneController sceneController = new SceneController();

    @FXML
    public void handleBookAppointment(ActionEvent event) throws IOException {
        sceneController.switchToScene(event, "/views/book_appointment.fxml");
    }

    @FXML
    public void handleViewAppointments(ActionEvent event) throws IOException {
        sceneController.switchToScene(event, "/views/view_appointments.fxml");
    }

    @FXML
    public void handleLogout(ActionEvent event) throws IOException {
        sceneController.switchToScene(event, "/views/login.fxml");
    }
    
    @FXML
    public void backToMainMenu(ActionEvent event) throws IOException {
        sceneController.switchToScene(event, "/views/main_menu.fxml");
    }
    
    @FXML
    public void viewDoctors(ActionEvent event) throws IOException {
        sceneController.switchToScene(event, "/views/view_doctors.fxml");
    }
    
    @FXML
    public void viewAppointment(ActionEvent event) throws IOException {
        sceneController.switchToScene(event, "/views/view_appointments.fxml");
    }
}
