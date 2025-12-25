package com.csc584.dao;

import com.csc584.bean.ProfileBean;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProfileDAO {
    
    public boolean saveProfile(ProfileBean profile) {
        System.out.println("\n💾 ===== SAVE PROFILE ======");
        System.out.println("Name: " + profile.getName());
        System.out.println("Student ID: " + profile.getStudentId());
        System.out.println("Email: " + profile.getEmail());
        
        String sql = "INSERT INTO PROFILES (STUDENT_ID, FULL_NAME, EMAIL, PROGRAM, HOBBIES, INTRODUCTION) " +
                    "VALUES (?, ?, ?, ?, ?, ?)";
        
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            if (conn == null) {
                System.err.println("❌ Connection is NULL!");
                return false;
            }
            
            // Log connection info
            System.out.println("✅ Connected to: " + conn.getMetaData().getURL());
            
            // Set parameters
            pstmt.setString(1, profile.getStudentId());
            pstmt.setString(2, profile.getName());  // Maps to FULL_NAME
            pstmt.setString(3, profile.getEmail());
            pstmt.setString(4, profile.getProgram() != null ? profile.getProgram() : "");
            pstmt.setString(5, profile.getHobbies() != null ? profile.getHobbies() : "");
            pstmt.setString(6, profile.getIntroduction() != null ? profile.getIntroduction() : "");
            
            // Execute
            int rows = pstmt.executeUpdate();
            System.out.println("✅ Saved " + rows + " row(s) to database");
            
            return rows > 0;
            
        } catch (SQLException e) {
            System.err.println("\n❌ SQL ERROR in saveProfile:");
            System.err.println("Message: " + e.getMessage());
            System.err.println("SQL State: " + e.getSQLState());
            System.err.println("Error Code: " + e.getErrorCode());
            
            if (e.getSQLState().equals("23505")) {
                System.err.println("⚠️ Duplicate entry - Student ID or Email already exists");
            }
            
            e.printStackTrace();
            return false;
        }
    }
    
    public List<ProfileBean> getAllProfiles() {
        List<ProfileBean> profiles = new ArrayList<>();
        System.out.println("\n📋 ===== GET ALL PROFILES =====");
        
        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM PROFILES")) {
            
            if (conn == null) {
                System.err.println("❌ Connection is NULL!");
                return profiles;
            }
            
            System.out.println("✅ Reading from: " + conn.getMetaData().getURL());
            
            int count = 0;
            while (rs.next()) {
                count++;
                ProfileBean profile = new ProfileBean();
                
                // Try different column name cases
                try {
                    profile.setId(rs.getInt("ID"));
                    profile.setStudentId(rs.getString("STUDENT_ID"));
                    profile.setName(rs.getString("FULL_NAME"));
                    profile.setEmail(rs.getString("EMAIL"));
                    profile.setProgram(rs.getString("PROGRAM"));
                    profile.setHobbies(rs.getString("HOBBIES"));
                    profile.setIntroduction(rs.getString("INTRODUCTION"));
                } catch (SQLException e) {
                    // Try lowercase
                    profile.setId(rs.getInt("id"));
                    profile.setStudentId(rs.getString("student_id"));
                    profile.setName(rs.getString("full_name"));
                    profile.setEmail(rs.getString("email"));
                    profile.setProgram(rs.getString("program"));
                    profile.setHobbies(rs.getString("hobbies"));
                    profile.setIntroduction(rs.getString("introduction"));
                }
                
                profiles.add(profile);
            }
            
            System.out.println("✅ Retrieved " + count + " profiles");
            
        } catch (SQLException e) {
            System.err.println("\n❌ SQL ERROR in getAllProfiles:");
            System.err.println("Message: " + e.getMessage());
            System.err.println("SQL State: " + e.getSQLState());
            e.printStackTrace();
        }
        
        return profiles;
    }
    
    public List<ProfileBean> searchProfiles(String keyword) {
        List<ProfileBean> profiles = new ArrayList<>();
        
        if (keyword == null || keyword.trim().isEmpty()) {
            return getAllProfiles();
        }
        
        String sql = "SELECT * FROM PROFILES WHERE " +
                    "LOWER(FULL_NAME) LIKE LOWER(?) OR " +
                    "LOWER(STUDENT_ID) LIKE LOWER(?) OR " +
                    "LOWER(EMAIL) LIKE LOWER(?)";
        
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            String searchTerm = "%" + keyword + "%";
            pstmt.setString(1, searchTerm);
            pstmt.setString(2, searchTerm);
            pstmt.setString(3, searchTerm);
            
            ResultSet rs = pstmt.executeQuery();
            
            while (rs.next()) {
                ProfileBean profile = new ProfileBean();
                profile.setId(rs.getInt("ID"));
                profile.setStudentId(rs.getString("STUDENT_ID"));
                profile.setName(rs.getString("FULL_NAME"));
                profile.setEmail(rs.getString("EMAIL"));
                profile.setProgram(rs.getString("PROGRAM"));
                profile.setHobbies(rs.getString("HOBBIES"));
                profile.setIntroduction(rs.getString("INTRODUCTION"));
                profiles.add(profile);
            }
            
        } catch (SQLException e) {
            System.err.println("Search error: " + e.getMessage());
        }
        
        return profiles;
    }
}