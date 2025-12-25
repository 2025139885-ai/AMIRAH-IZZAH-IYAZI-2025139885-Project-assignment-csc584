package com.csc584.dao;

import java.sql.*;

public class DBConnection {
    public static Connection getConnection() {
        try {
            // ONLY use student_profiles
            String url = "jdbc:derby://localhost:1527/student_profiles;create=true";
            System.out.println("🔍 Connecting to: " + url);
            
            Class.forName("org.apache.derby.jdbc.ClientDriver");
            Connection conn = DriverManager.getConnection(url, "app", "app");
            System.out.println("✅ Connected to: " + conn.getMetaData().getURL());
            
            // Simple table check
            try {
                conn.createStatement().executeQuery("SELECT 1 FROM PROFILES");
                System.out.println("✅ Table exists");
            } catch (SQLException e) {
                System.out.println("📝 Creating table...");
                String sql = 
                    "CREATE TABLE PROFILES (" +
                    "ID INTEGER GENERATED ALWAYS AS IDENTITY PRIMARY KEY, " +
                    "STUDENT_ID VARCHAR(50), " +
                    "FULL_NAME VARCHAR(100), " +
                    "EMAIL VARCHAR(100), " +
                    "PROGRAM VARCHAR(100), " +
                    "HOBBIES VARCHAR(500), " +
                    "INTRODUCTION VARCHAR(1000)" +
                    ")";
                conn.createStatement().execute(sql);
                System.out.println("✅ Table created");
            }
            
            return conn;
            
        } catch (Exception e) {
            System.err.println("❌ Connection failed: " + e.getMessage());
            return null;
        }
    }
}