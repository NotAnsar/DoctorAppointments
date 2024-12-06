package com.example.doctorappointments;

import com.example.doctorappointments.model.Ordonnance;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DatabaseUtil {
    private static final String URL = "jdbc:mysql://localhost:3306/chms";
    private static final String USER = "root";
    private static final String PASSWORD = "";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    public static void main(String[] args) {
        try (Connection connection = getConnection()) {
            if (connection != null) {
                System.out.println("Connection successful!");
            } else {
                System.out.println("Failed to make connection!");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}