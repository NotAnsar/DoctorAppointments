package com.example.doctorappointments;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;


public class MedicationFormController {
    @FXML
    private ComboBox<String> combo_pharmacy;

    @FXML
    private ComboBox<String> medicamentsComboBox;

    @FXML
    private TableView<String> medicamentsTableView;

    @FXML
    private TableColumn<String, String> medicamentColumn;

    @FXML
    private Label label_date;


    @FXML
    public void initialize() {
        ObservableList<String> pharmacyList = FXCollections.observableArrayList("CVS Pharmacy", "Walgreens", "Rite Aid", "Walmart Pharmacy", "Kroger Pharmacy", "Costco Pharmacy", "Safeway Pharmacy", "Publix Pharmacy", "Target Pharmacy", "Albertsons Pharmacy");
        combo_pharmacy.setItems(pharmacyList);

        ObservableList<String> medicamentList = FXCollections.observableArrayList("Aspirin", "Ibuprofen", "Paracetamol", "Amoxicillin", "Ciprofloxacin", "Metformin", "Lisinopril", "Atorvastatin", "Omeprazole", "Simvastatin");
        medicamentsComboBox.setItems(medicamentList);

        label_date.setText("Date: " + java.time.LocalDate.now());

        medicamentColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue()));

    }


    @FXML
    private void handleAddMedicamentAction() {
        String selectedMedicament = medicamentsComboBox.getValue();
        System.out.println(selectedMedicament);
        if (selectedMedicament != null && !medicamentsTableView.getItems().contains(selectedMedicament)) {
            medicamentsTableView.getItems().add(selectedMedicament);
        }
    }

    @FXML
    private void handleSubmitButtonAction() {
        String selectedPharmacy = combo_pharmacy.getValue();
        if (selectedPharmacy == null) {
            showAlert(Alert.AlertType.WARNING, "No pharmacy selected.");
            return;
        }

        ObservableList<String> selectedMedicaments = medicamentsTableView.getItems();
        if (selectedMedicaments.isEmpty()) {
            showAlert(Alert.AlertType.WARNING, "No medicament's selected.");
            return;
        }

        System.out.println("Selected Pharmacy: " + selectedPharmacy);
        System.out.println("Selected Medicament: " + selectedMedicaments);
        System.out.println("Creation Date: " + java.time.LocalDate.now());

        String message = "Selected Medicament's: " + selectedMedicaments + "\nSelected Pharmacy: " + selectedPharmacy + "\nCreation Date: " + java.time.LocalDate.now();
        showAlert(Alert.AlertType.INFORMATION, message);
    }




    private void showAlert(Alert.AlertType alertType, String message) {
        Alert alert = new Alert(alertType);
        alert.setContentText(message);
        alert.showAndWait();
    }

}