package com.csc584;

import com.csc584.bean.ProfileBean;
import com.csc584.dao.ProfileDAO;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/ProfileServlet")
public class ProfileServlet extends HttpServlet {
    private ProfileDAO profileDAO = new ProfileDAO();
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        System.out.println("=== FORM SUBMISSION ===");
        
        // Check if this is a search
        String action = request.getParameter("action");
        if ("search".equals(action)) {
            String keyword = request.getParameter("keyword");
            System.out.println("Searching for: " + keyword);
            List<ProfileBean> profiles = profileDAO.searchProfiles(keyword);
            request.setAttribute("profiles", profiles);
            request.setAttribute("searchKeyword", keyword);
            request.getRequestDispatcher("viewAllProfiles.jsp").forward(request, response);
            return;
        }
        
        // Get form data
        String name = request.getParameter("name");
        String studentId = request.getParameter("studentId");
        String email = request.getParameter("email");
        String program = request.getParameter("program");
        String hobbies = request.getParameter("hobbies");
        String introduction = request.getParameter("introduction");
        
        System.out.println("Form Data:");
        System.out.println("Name: " + name);
        System.out.println("Student ID: " + studentId);
        System.out.println("Email: " + email);
        
        // Create profile object
        ProfileBean profile = new ProfileBean();
        profile.setName(name);
        profile.setStudentId(studentId);
        profile.setEmail(email);
        profile.setProgram(program != null ? program : "");
        profile.setHobbies(hobbies != null ? hobbies : "");
        profile.setIntroduction(introduction != null ? introduction : "");
        
        // Save to database
        boolean saved = profileDAO.saveProfile(profile);
        
        // Forward to display page
        if (saved) {
            request.setAttribute("profile", profile);
            request.setAttribute("message", "✅ Profile saved successfully!");
            request.getRequestDispatcher("displayProfile.jsp").forward(request, response);
        } else {
            request.setAttribute("error", "❌ Failed to save profile. Please try again.");
            request.getRequestDispatcher("profileForm.html").forward(request, response);
        }
    }
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        System.out.println("Viewing all profiles");
        List<ProfileBean> profiles = profileDAO.getAllProfiles();
        request.setAttribute("profiles", profiles);
        request.getRequestDispatcher("viewAllProfiles.jsp").forward(request, response);
    }
}