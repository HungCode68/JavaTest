<%-- 
    Document   : teacher
    Created on : Jun 3, 2025, 9:56:56 AM
    Author     : Nguyễn Hùng
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="model.TeacherModel" %>
<%
    List<TeacherModel> list = (List<TeacherModel>) request.getAttribute("teacherList");
    TeacherModel teacherEdit = (TeacherModel) request.getAttribute("teacherEdit");
%>
<!DOCTYPE html>
<html>
<head>
    <title>Quản lý giáo viên</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet"/>
</head>
<body class="container py-4">

    <h2 class="mb-4">Danh sách giáo viên</h2>
    
    <button class="btn btn-primary mb-3" onclick="openModal()">+ Thêm giáo viên</button>

    <table class="table table-bordered table-hover">
        <thead class="table-secondary">
            <tr>
                <th>Mã GV</th>
                <th>Họ tên</th>
                <th>Giới tính</th>
                <th>Môn học</th>
                <th>Hành động</th>
            </tr>
        </thead>
        <tbody>
        <%
            for (TeacherModel t : list) {
        %>
            <tr>
                <td><%= t.getTeacherId() %></td>
                <td><%= t.getName() %></td>
                <td><%= t.getGender() %></td>
                <td><%= t.getSubject() %></td>
                <td>
                    <button class="btn btn-warning btn-sm"
                            onclick="openModal('<%= t.getTeacherId() %>', '<%= t.getName() %>', '<%= t.getGender() %>', '<%= t.getSubject() %>')">
                        Sửa
                    </button>
                    <a class="btn btn-danger btn-sm" href="teacher?action=delete&id=<%= t.getTeacherId() %>" onclick="return confirm('Bạn có chắc muốn xoá?')">
                        Xoá
                    </a>
                </td>
            </tr>
        <%
            }
        %>
        </tbody>
    </table>

    <!-- Modal -->
    <div class="modal fade" id="teacherModal" tabindex="-1" aria-labelledby="teacherModalLabel" aria-hidden="true">
        <div class="modal-dialog">
            <form class="modal-content" method="post" action="teacher">
                <div class="modal-header">
                    <h5 class="modal-title" id="teacherModalLabel">Thêm / Sửa giáo viên</h5>
                    <button type="button" class="btn-close" data-bs-dismiss="modal"></button>
                </div>
                <div class="modal-body">
                    <input type="hidden" name="action" id="modalAction" value="insert">
                    
                    <div class="mb-3">
                        <label for="teacherId" class="form-label">Mã GV</label>
                        <input type="text" class="form-control" id="teacherId" name="teacherId" required>
                    </div>
                    <div class="mb-3">
                        <label for="name" class="form-label">Họ tên</label>
                        <input type="text" class="form-control" id="name" name="name" required>
                    </div>
                    <div class="mb-3">
                        <label for="gender" class="form-label">Giới tính</label>
                        <select class="form-control" id="gender" name="gender">
                            <option value="Nam">Nam</option>
                            <option value="Nữ">Nữ</option>
                        </select>
                    </div>
                    <div class="mb-3">
                        <label for="subject" class="form-label">Môn học</label>
                        <input type="text" class="form-control" id="subject" name="subject" required>
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="submit" class="btn btn-success">Lưu</button>
                    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Đóng</button>
                </div>
            </form>
        </div>
    </div>

    <!-- Bootstrap JS + Modal Control -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
    <script>
        const modal = new bootstrap.Modal(document.getElementById('teacherModal'));

        function openModal(id = '', name = '', gender = 'Nam', subject = '') {
            document.getElementById('teacherId').value = id;
            document.getElementById('name').value = name;
            document.getElementById('gender').value = gender;
            document.getElementById('subject').value = subject;

            document.getElementById('teacherId').readOnly = id !== '';
            document.getElementById('modalAction').value = id !== '' ? 'update' : 'insert';
            
            modal.show();
        }
    </script>
</body>
</html>

