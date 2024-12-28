package com.example.studentapi.dao;

import com.example.studentapi.config.DatabaseConnection;
import com.example.studentapi.model.Student;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StudentDAO {

    public List<Student> getAllStudents() throws SQLException {
        List<Student> students = new ArrayList<>();
        String query = "SELECT * FROM Students";

        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                Student student = new Student();
                student.setStudId(rs.getInt("studId"));
                student.setStudName(rs.getString("studName"));
                student.setStudDepartement(rs.getString("studDepartement"));
                student.setStudYear(rs.getInt("studYear"));
                students.add(student);
            }
        }
        return students;
    }

    public void addStudent(Student student) throws SQLException {
        String query = "INSERT INTO Students (studId,studName, studDepartement, studYear) VALUES (?, ?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, student.getStudId());
            pstmt.setString(2, student.getStudName());
            pstmt.setString(3, student.getStudDepartement());
            pstmt.setInt(4, student.getStudYear());
            pstmt.executeUpdate();
        }
    }

    public Student getStudentById(int id) throws SQLException {
        String query = "SELECT * FROM Students WHERE studId = ?";
        Student student = null;

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setInt(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    student = new Student();
                    student.setStudId(rs.getInt("studId"));
                    student.setStudName(rs.getString("studName"));
                    student.setStudDepartement(rs.getString("studDepartement"));
                    student.setStudYear(rs.getInt("studYear"));
                }
            }
        }
        return student;
    }

    public void updateStudent(Student student) throws SQLException {
        String query = "UPDATE Students SET studName = ?, studDepartement = ?, studYear = ? WHERE studId = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setString(1, student.getStudName());
            pstmt.setString(2, student.getStudDepartement());
            pstmt.setInt(3, student.getStudYear());
            pstmt.setInt(4, student.getStudId());
            pstmt.executeUpdate();
        }
    }

    public void deleteStudent(int id) throws SQLException {
        String query = "DELETE FROM Students WHERE studId = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        }
    }
}
