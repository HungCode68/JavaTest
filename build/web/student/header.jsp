<%-- 
    Document   : header
    Created on : May 30, 2025, 8:15:47 AM
    Author     : Nguyễn Hùng
--%>

<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <title>Hệ Thống Quản Lý Trường Đại Học</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>

<!-- Navbar -->
<nav class="navbar navbar-expand-lg navbar-dark bg-primary">
    <div class="container-fluid">
        <a class="navbar-brand" href="index.jsp">Đại Học XYZ</a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" 
                data-bs-target="#navbarNav" aria-controls="navbarNav" 
                aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        
        <div class="collapse navbar-collapse" id="navbarNav">
            <ul class="navbar-nav ms-auto">
                <li class="nav-item">
                    <a class="nav-link" href="index.jsp">Trang chủ</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="/UniversitySystem/students">Sinh viên</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="teachers.jsp">Giáo viên</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="classes.jsp">Lớp học</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="exams.jsp">Lịch thi</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link text-warning" href="logout.jsp">Đăng xuất</a>
                </li>
            </ul>
        </div>
    </div>
</nav>

<div class="container mt-5">

