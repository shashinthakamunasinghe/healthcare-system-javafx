package controllers;

import application.SceneController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import java.io.IOException;

public class MainMenuController {

    SceneController sceneController = new SceneController();

    @FXML
    public void goToAdminDashboard(ActionEvent event) throws IOException {
        sceneController.switchToScene(event, "/views/admin_dashboard.fxml");
    }

    @FXML
    public void goToPatientDashboard(ActionEvent event) throws IOException {
        sceneController.switchToScene(event, "/views/patient_dashboard.fxml");
    }
}
