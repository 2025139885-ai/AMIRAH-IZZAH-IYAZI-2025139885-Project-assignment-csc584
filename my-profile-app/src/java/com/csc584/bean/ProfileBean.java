package com.csc584.bean;

import java.io.Serializable;

public class ProfileBean implements Serializable {
    private int id;
    private String studentId;        // This maps to STUDENT_ID
    private String name;             // This should map to FULL_NAME
    private String email;            // This maps to EMAIL
    private String program;          // This maps to PROGRAM
    private String hobbies;          // This maps to HOBBIES
    private String introduction;     // This maps to INTRODUCTION
    
    // Add a getter for full_name to match database
    public String getFullName() {
        return name;
    }
    
    // And setter
    public void setFullName(String fullName) {
        this.name = fullName;
    }
    
    // Default constructor
    public ProfileBean() {}
    
    // Getters and setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    
    public String getStudentId() { return studentId; }
    public void setStudentId(String studentId) { this.studentId = studentId; }
    
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    
    public String getProgram() { return program; }
    public void setProgram(String program) { this.program = program; }
    
    public String getHobbies() { return hobbies; }
    public void setHobbies(String hobbies) { this.hobbies = hobbies; }
    
    public String getIntroduction() { return introduction; }
    public void setIntroduction(String introduction) { this.introduction = introduction; }
}