package com.csc584; // Use your actual package name

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays; // Used for joining array elements

@WebServlet("/ProfileServlet") // Maps the URL to this servlet
public class ProfileServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        // 1. Collect user information from the HTML form (Requirement 1 & 2)
        String name = request.getParameter("name");
        String studentId = request.getParameter("studentId");
        String program = request.getParameter("program");
        String email = request.getParameter("email");
        String introduction = request.getParameter("introduction");
        
        // Hobbies is a multi-value field (checkboxes)
        String[] hobbiesArray = request.getParameterValues("hobbies");
        
        // Format hobbies into a single string for cleaner display in JSP
        String hobbies = "";
        if (hobbiesArray != null) {
            // Join the array elements with commas for a nice presentation
            hobbies = String.join(", ", hobbiesArray);
        } else {
            hobbies = "None specified";
        }

        // 2. Set the collected data as attributes in the request scope
        //    This makes the data available to the forwarded JSP (Requirement 5)
        request.setAttribute("profileName", name);
        request.setAttribute("profileStudentId", studentId);
        request.setAttribute("profileProgram", program);
        request.setAttribute("profileEmail", email);
        request.setAttribute("profileHobbies", hobbies);
        request.setAttribute("profileIntroduction", introduction);

        // 3. Forward the request to the JSP page for display (Requirement 3)
        request.getRequestDispatcher("/displayProfile.jsp").forward(request, response);
    }
}