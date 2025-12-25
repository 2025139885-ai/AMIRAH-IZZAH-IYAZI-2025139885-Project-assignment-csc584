<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List, com.csc584.bean.ProfileBean" %>
<!DOCTYPE html>
<html>
<head>
    <title>Winx Club Profile</title>
    <link rel="stylesheet" href="css/style.css">
    <link rel="stylesheet" href="css/viewAllProfiles.css">
    <link rel="icon" type="image/png" href="images/winx_club_logo4.png">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
</head>
<body>
    <div class="container view-all-container">
        <!-- Header -->
        <div class="winx-header">
            <h1>Winx Club Profile</h1>
            <h2>ALL MAGICAL MEMBERS IN OUR DATABASE</h2>
        </div>
        
        <!-- Search Panel -->
        <div class="search-panel">
            <span class="search-label">eg. name.student id</span>
            <form action="ProfileServlet" method="post">
                <input type="hidden" name="action" value="search">
                <div class="search-row">
                    <input type="text" name="keyword" class="search-input" 
                           placeholder="Search" 
                           value="<%= request.getAttribute("searchKeyword") != null ? 
                                   request.getAttribute("searchKeyword") : "" %>">
                    <button type="submit" class="search-btn">Search</button>
                </div>
            </form>
        </div>
        
        <!-- Display search results message -->
        <%
            String searchKeyword = (String) request.getAttribute("searchKeyword");
            if (searchKeyword != null && !searchKeyword.trim().isEmpty()) {
        %>
            <div class="search-results-msg">
                <h3>
                    <span>🔍</span>
                    Search Results for: "<%= searchKeyword %>"
                </h3>
            </div>
        <%
            }
        %>
        
        <!-- Profiles Table -->
        <%
            List<ProfileBean> profiles = (List<ProfileBean>) request.getAttribute("profiles");
            if (profiles != null && !profiles.isEmpty()) {
        %>
        <div class="profiles-table-container">
            <table class="profiles-table">
    <thead>
        <tr>
            <th>Name</th>
            <th>Student ID</th>
            <th>Email</th>
            <th>Program</th>
            <th>Hobbies</th>
            <th>Self - Introduction</th>
        </tr>
    </thead>
    <tbody>
        <% for (ProfileBean profile : profiles) { %>
        <tr>
            <td class="name-cell"><%= profile.getName() %></td>
            <td class="student-id-cell"><%= profile.getStudentId() %></td>
            <td class="email-cell"><%= profile.getEmail() %></td>
            <td><span class="program-cell"><%= profile.getProgram() %></span></td>
            <td class="hobbies-cell"><%= profile.getHobbies() %></td>
            <td class="intro-cell"><%= profile.getIntroduction() %></td>
        </tr>
        <% } %>
    </tbody>
</table>
        <%
            } else {
        %>
        <!-- Empty State -->
        <div class="empty-state">
            <div class="empty-icon">📊</div>
            <h3>No Magical Members Found Yet!</h3>
            <p>The Winx Club is waiting for its first member!</p>
        </div>
        <%
            }
        %>
        
        <!-- Back Button -->
        <div class="back-section">
            <a href="profileForm.html" class="btn-back">
                <span>←</span> Back to Form
            </a>
        </div>
    </div>
    
    <!-- JavaScript -->
    <script>
        // Search input focus effect
        document.addEventListener('DOMContentLoaded', function() {
            const searchInput = document.querySelector('.search-input');
            if (searchInput) {
                searchInput.addEventListener('focus', function() {
                    this.style.boxShadow = '0 0 0 3px rgba(74, 74, 156, 0.1)';
                    this.style.borderColor = '#4a4a9c';
                });
                
                searchInput.addEventListener('blur', function() {
                    this.style.boxShadow = '';
                    this.style.borderColor = '#d1d5ff';
                });
            }
            
            // Auto-focus search input if there's a search keyword
            const searchKeyword = '<%= searchKeyword %>';
            if (searchInput && searchKeyword && searchKeyword.trim() !== '') {
                searchInput.focus();
                searchInput.select();
            }
        });
    </script>
</body>
</html>