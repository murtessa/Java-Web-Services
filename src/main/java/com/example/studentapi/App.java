package com.example.studentapi;

import com.example.studentapi.config.DatabaseConnection;

import java.sql.Connection;

public class App {
    public static void main(String[] args) {
        System.out.println("Starting Student Registration API...");
        
        // Test database connection
        try (Connection conn = DatabaseConnection.getConnection()) {
            System.out.println("Database connection successful!");
        } catch (Exception e) {
            System.err.println("Failed to connect to the database: " + e.getMessage());
        }

        System.out.println("Student Registration API is up and running!");
    }
}
