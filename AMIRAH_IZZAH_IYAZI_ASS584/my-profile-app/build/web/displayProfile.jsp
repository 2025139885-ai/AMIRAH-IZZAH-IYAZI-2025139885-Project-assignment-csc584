<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Profile Submission Details</title>
    <link rel="icon" type="image/png" href="images/winx_club_logo4.png">
    <link rel="stylesheet" href="css/style.css">
</head>
<body>
    <div class="container">
        <header>
            <h1>✅ Submission Successful!</h1>
            <h2>Personal Profile for ${requestScope.profileName}</h2>
        </header>
        
        <div class="profile-card">
            <h3>Student Information</h3>
            <p><strong>Student ID:</strong> <span>${requestScope.profileStudentId}</span></p>
            <p><strong>Program/Course:</strong> <span>${requestScope.profileProgram}</span></p>
            <p><strong>Email:</strong> <span>${requestScope.profileEmail}</span></p>
            
            <h3>Hobbies & Interests</h3>
            <p><strong>Hobbies:</strong> <span>${requestScope.profileHobbies}</span></p>
            
            <h3>Self-Introduction</h3>
            <div class="introduction-box">
                <p>${requestScope.profileIntroduction}</p>
            </div>
        </div>
        
        <footer>
            <p>Data successfully processed by Servlet and displayed via JSP.</p>
        </footer>
    </div>
</body>
</html>