<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Student Management System</title>
<link rel="stylesheet" type="text/css" href="css/style.css">
</head>
<body>

<div class="container">
<h1>🎓 Student Management System</h1>
<h3>Add New Student</h3>
<hr>
<form action="addStudent" method="post">

<label>Name</label>
<input type="text" name="name">

<label>Email</label>
<input type="email" name="email">

<label>Course</label>
<input type="text" name="course">

<input type="submit" value="➕ Add Student">

</form>
<br><br>

<a href="ViewStudentsServlet" class="view-btn">
    📋 View Students
</a>


<hr>

<div class="footer">
    <p>
        Developed by <b>Sameera Shaikh</b> ❤️<br>
        Java | JSP | Servlets | JDBC | PostgreSQL
    </p>
</div>
</div>

</body>
</html>