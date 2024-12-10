package com.example.doctorappointments.service;

import java.sql.*;
import java.util.List;

public class OrdonnanceService {

    public static int insertOrdonnance(int IDDoctor, Timestamp DateCreation, int IDPharmacien, int IDPatient, String Status) {
        String sql = "INSERT INTO Ordonnance (IDDoctor, DateCreation, IDPharmacien, IDPatient, Status) VALUES (?, ?, ?, ?, ?)";
        int generatedId = -1;

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            pstmt.setInt(1, IDDoctor);
            pstmt.setTimestamp(2, DateCreation);
            pstmt.setInt(3, IDPharmacien);
            pstmt.setInt(4, IDPatient);
            pstmt.setString(5, Status);

            pstmt.executeUpdate();

            try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    generatedId = generatedKeys.getInt(1);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return generatedId;
    }

    public static void insertOrdonnanceMedicament(int IDOrdonnance, List<Integer> IDMedicaments) {
        String sql = "INSERT INTO Ordonnance_Medicament (IDOrdonnance, IDMedicament) VALUES (?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            for (Integer IDMedicament : IDMedicaments) {
                pstmt.setInt(1, IDOrdonnance);
                pstmt.setInt(2, IDMedicament);
                pstmt.addBatch();
            }

            pstmt.executeBatch();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}