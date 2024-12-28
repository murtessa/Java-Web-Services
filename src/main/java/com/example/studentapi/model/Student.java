package com.example.studentapi.model;

public class Student {
    private int studId;
    private String studName;
    private String studDepartement;
    private int studYear;

    // Getters and Setters
    public int getStudId() { return studId; }
    public void setStudId(int studId) { this.studId = studId; }

    public String getStudName() { return studName; }
    public void setStudName(String studName) { this.studName = studName; }

    public String getStudDepartement() { return studDepartement; }
    public void setStudDepartement(String studDepartment) { this.studDepartement = studDepartment; }

    public int getStudYear() { return studYear; }
    public void setStudYear(int studYear) { this.studYear = studYear; }
}
