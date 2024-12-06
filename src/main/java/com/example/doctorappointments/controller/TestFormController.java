package com.example.doctorappointments.controller;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class TestFormController {
    private int appointmentID;

    @FXML
    private Label title;

    @FXML
    private ComboBox<String> testsComboBox;

    @FXML
    private TableView<String> testsTableView;

    @FXML
    private TableColumn<String, String> testColumn;

    @FXML
    private DatePicker date_test;

    @FXML
    public void initialize() {
        ObservableList<String> testList = FXCollections.observableArrayList("Blood Test", "X-Ray", "MRI", "CT Scan", "Ultrasound", "ECG", "Echocardiogram", "Biopsy", "Allergy Test");
        testsComboBox.setItems(testList);

        testColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue()));
    }

    public void setAppointmentID(int appointmentID) {
        System.out.println("Appointment ID: " + appointmentID);
        this.appointmentID = appointmentID;
        title.setText("Add New Prescription Test : " + appointmentID);
    }

    @FXML
    private void handleAddTestAction() {
        String selectedTest = testsComboBox.getValue();
        System.out.println(selectedTest);
        if (selectedTest != null && !testsTableView.getItems().contains(selectedTest)) {
            testsTableView.getItems().add(selectedTest);
        }
    }

    @FXML
    private void handleSubmitButtonAction() {
        ObservableList<String> selectedTests = testsTableView.getItems();
        if (selectedTests.isEmpty()) {
            showAlert(Alert.AlertType.WARNING, "No tests selected.");
            return;
        }


        if (date_test.getValue() == null) {
            showAlert(Alert.AlertType.WARNING, "No date selected.");
            return;
        }

        System.out.println("Selected Tests: " + selectedTests);
        System.out.println("Test Date: " + date_test.getValue());
        String message = "Selected Tests: " + selectedTests + "\nTest Date: " + date_test.getValue();
        showAlert(Alert.AlertType.INFORMATION, message);
    }

    private void showAlert(Alert.AlertType alertType, String message) {
        Alert alert = new Alert(alertType);
        alert.setContentText(message);
        alert.showAndWait();
    }

}
