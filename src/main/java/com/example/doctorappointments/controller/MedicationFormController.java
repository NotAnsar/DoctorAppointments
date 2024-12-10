package com.example.doctorappointments.controller;


import com.example.doctorappointments.model.Appointment;
import com.example.doctorappointments.model.AppointmentDetails;
import com.example.doctorappointments.model.Medication;
import com.example.doctorappointments.model.Pharmacy;
import com.example.doctorappointments.service.AppointmentService;
import com.example.doctorappointments.service.PharmacyService;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.util.HashMap;
import java.util.Map;


public class MedicationFormController {


    private int appointmentID;


    @FXML
    private Label title;

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
    private Label label_doctor;

    @FXML
    private Label label_patient;

    private final PharmacyService pharmacyService = new PharmacyService();
    private AppointmentDetails appointment = null;

    private Map<String, Integer> pharmacyMap = new HashMap<>();
    private Map<String, Integer> medicationMap = new HashMap<>();



    @FXML
    public void initialize() {
        loadPharmacies();
        loadMedicaments();

        label_date.setText("Date: " + java.time.LocalDate.now());
        medicamentColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue()));
    }



    private void loadPharmacies() {
        ObservableList<Pharmacy> pharmacies = pharmacyService.getAllPharmacies();
        ObservableList<String> pharmacyNames = FXCollections.observableArrayList();

        for (Pharmacy pharmacy : pharmacies) {
            String pharmacyName = pharmacy.getNom() + " " + pharmacy.getPrenom();

            pharmacyNames.add(pharmacyName);
            pharmacyMap.put(pharmacyName, pharmacy.getIDPharmacien());
        }

        combo_pharmacy.setItems(pharmacyNames);
    }

    private void loadMedicaments() {
        ObservableList<Medication> medications = pharmacyService.getAllMedications();
        ObservableList<String> medicationNames = FXCollections.observableArrayList();

        for (Medication medication : medications) {
            medicationNames.add(medication.getNomMed());
            medicationMap.put(medication.getNomMed(), medication.getIDMedicament());
        }
        medicamentsComboBox.setItems(medicationNames);



        /*ObservableList<String> medicamentList = FXCollections.observableArrayList(
                "Aspirin", "Ibuprofen", "Paracetamol", "Amoxicillin", "Ciprofloxacin",
                "Metformin", "Lisinopril", "Atorvastatin", "Omeprazole", "Simvastatin"
        );
        medicamentsComboBox.setItems(medicamentList);*/
    }



    public void setAppointmentID(int appointmentID) {
        System.out.println("Appointment ID: " + appointmentID);
        this.appointmentID = appointmentID;
        title.setText("Add New Prescription Medication : " + appointmentID);
        appointment = AppointmentService.getAppointmentById(1);


        if (appointment != null) {
            label_doctor.setText("Doctor: " + appointment.getDoctorFullName());
            label_patient.setText("Patient: " + appointment.getPatientFullName());
        }
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

        Integer pharmacyID = pharmacyMap.get(selectedPharmacy);
        if (pharmacyID == null) {
            showAlert(Alert.AlertType.WARNING, "Pharmacy ID not found.");
            return;
        }

        ObservableList<String> selectedMedicaments = medicamentsTableView.getItems();
        if (selectedMedicaments.isEmpty()) {
            showAlert(Alert.AlertType.WARNING, "No medicament's selected.");
            return;
        }

        // Collect all medication IDs
        ObservableList<Integer> medicationIDs = FXCollections.observableArrayList();
        for (String medicamentName : selectedMedicaments) {
            Integer medicationID = medicationMap.get(medicamentName);
            if (medicationID != null) {
                medicationIDs.add(medicationID);
            } else {
                showAlert(Alert.AlertType.WARNING, "Medication ID not found for: " + medicamentName);
                return; // Exit if any ID is missing
            }
        }

        System.out.println("Selected Pharmacy ID: " + pharmacyID);
        System.out.println("Selected Pharmacy: " + selectedPharmacy);
        System.out.println("Selected Medicament IDs: " + medicationIDs);
        System.out.println("Selected Medicament: " + selectedMedicaments);
        System.out.println("Creation Date: " + java.time.LocalDate.now());
        System.out.println("Appointment ID: " + appointmentID);




        String message = "Selected Medicaments: " + selectedMedicaments +
                "\nMedicaments IDs: " + medicationIDs +
                "\nSelected Pharmacy: " + selectedPharmacy +
                "\nPharmacy ID: " + pharmacyID +
                "\nCreation Date: " + java.time.LocalDate.now() + "\n" +
                "Appointment ID: " + appointmentID + "\n" +
                "Doctor : " + appointment.getDoctorFullName()+ "\n" +
                "Doctor Id : " + appointment.getIDDoctor()+ "\n" +
                "Patient Id : " + appointment.getIDPatient()+ "\n" +
                "Patient : " + appointment.getPatientFullName();


        showAlert(Alert.AlertType.INFORMATION, message);
    }




    private void showAlert(Alert.AlertType alertType, String message) {
        Alert alert = new Alert(alertType);
        alert.setContentText(message);
        alert.showAndWait();
    }

}