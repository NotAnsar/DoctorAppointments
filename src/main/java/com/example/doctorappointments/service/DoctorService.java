package com.example.doctorappointments.service;

import com.example.doctorappointments.model.Speciality;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DoctorService {


    public static boolean insertDoctor(int idSpeciality, String nom, String prenom, String tel, String adresse) {
        String sql = "INSERT INTO doctor (IDSpeciality, Nom, Prenom, Tel, Adresse) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, idSpeciality);
            pstmt.setString(2, nom);
            pstmt.setString(3, prenom);
            pstmt.setString(4, tel);
            pstmt.setString(5, adresse);

            int affectedRows = pstmt.executeUpdate();
            return affectedRows > 0;

        } catch (SQLException e) {
            System.err.println("Error inserting doctor: " + e.getMessage());
            return false;
        }
    }

    public static ObservableList<Speciality> getAllSpecialities() {
        ObservableList<Speciality> specialities = FXCollections.observableArrayList();

        String query = "SELECT * FROM speciality";

        try (Connection conn = DatabaseConnection.getConnection()) {
            PreparedStatement pstmt = conn.prepareStatement(query);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                Speciality speciality = new Speciality(
                        rs.getInt("IDSpeciality"),
                        rs.getString("NomSpeciality")
                );
                specialities.add(speciality);
            }
        } catch (SQLException e) {
            System.err.println("Error retrieving specialities: " + e.getMessage());
        }

        return specialities;
    }
}