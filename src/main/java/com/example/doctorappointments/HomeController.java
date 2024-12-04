package com.example.doctorappointments;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

public class HomeController {

    public void handleCreateMedicalPrescription() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("prescription-medical-form.fxml"));
            Parent root = fxmlLoader.load();
            Stage stage = new Stage();
            stage.setTitle("Medical Prescription Form");
            stage.setScene(new Scene(root, 1100, 700));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void handleCreateTestPrescription() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("prescription-test-form.fxml"));
            Parent root = fxmlLoader.load();
            Stage stage = new Stage();
            stage.setTitle("Test Prescription Form");
            stage.setScene(new Scene(root, 1100, 700));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}