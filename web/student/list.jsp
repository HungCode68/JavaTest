<%-- 
    Document   : list
    Created on : May 27, 2025, 10:19:56 PM
    Author     : Nguyễn Hùng
--%>

<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="model.Student" %>

<jsp:include page="header.jsp" />

<h2>Danh sách sinh viên</h2>
<button type="button" class="btn btn-primary mb-3" onclick="openAddModal()">Thêm sinh viên</button>

<!-- Modal Thêm -->
<div id="addStudentModal" class="modal" tabindex="-1">
    <div class="modal-dialog">
        <div class="modal-content">
            <form action="add-student" method="post">
                <div class="modal-header">
                    <h5 class="modal-title">Thêm sinh viên</h5>
                    <button type="button" class="btn-close" onclick="closeAddModal()"></button>
                </div>
                <div class="modal-body">
                    <input type="text" name="studentId" placeholder="Mã SV" class="form-control mb-2" required>
                    <input type="text" name="name" placeholder="Họ tên" class="form-control mb-2" required>
                    <select name="gender" class="form-control mb-2" required>
                        <option value="Nam">Nam</option>
                        <option value="Nữ">Nữ</option>
                    </select>
                    <input type="date" name="birthDate" class="form-control mb-2" required>
                    <input type="text" name="classId" placeholder="Lớp" class="form-control mb-2" required>
                    <input type="text" name="major" placeholder="Ngành học" class="form-control mb-2" required>
                </div>
                <div class="modal-footer">
                    <button type="submit" class="btn btn-success">Lưu</button>
                    <button type="button" class="btn btn-secondary" onclick="closeAddModal()">Đóng</button>
                </div>
            </form>
        </div>
    </div>
</div>

<!-- Modal Sửa -->
<div id="editStudentModal" class="modal" tabindex="-1">
    <div class="modal-dialog">
        <div class="modal-content">
            <form action="update-student" method="post">
                <div class="modal-header">
                    <h5 class="modal-title">Sửa sinh viên</h5>
                    <button type="button" class="btn-close" onclick="closeEditModal()"></button>
                </div>
                <div class="modal-body">
                    <input type="text" id="editStudentId" name="studentId" class="form-control mb-2" readonly>
                    <input type="text" id="editName" name="name" class="form-control mb-2" required>
                    <select id="editGender" name="gender" class="form-control mb-2" required>
                        <option value="Nam">Nam</option>
                        <option value="Nữ">Nữ</option>
                    </select>
                    <input type="date" id="editBirthDate" name="birthDate" class="form-control mb-2" required>
                    <input type="text" id="editClassId" name="classId" class="form-control mb-2" required>
                    <input type="text" id="editMajor" name="major" class="form-control mb-2" required>
                </div>
                <div class="modal-footer">
                    <button type="submit" class="btn btn-success">Cập nhật</button>
                    <button type="button" class="btn btn-secondary" onclick="closeEditModal()">Đóng</button>
                </div>
            </form>
        </div>
    </div>
</div>

<table border="1" class="table table-bordered">
    <thead>
        <tr>
            <th>Mã SV</th>
            <th>Họ tên</th>
            <th>Giới tính</th>
            <th>Ngày sinh</th>
            <th>Lớp</th>
            <th>Ngành</th>
        </tr>
    </thead>
    <tbody>
        <%
            List<Student> students = (List<Student>) request.getAttribute("students");
            if (students != null && !students.isEmpty()) {
                for (Student s : students) {
        %>
        <tr>
            <td><%= s.getStudentId() %></td>
            <td><%= s.getName() %></td>
            <td><%= s.getGender() %></td>
            <td><%= s.getBirthDate() %></td>
            <td><%= s.getClassId() %></td>
            <td><%= s.getMajor() %></td>
            <td>
    <button class="btn btn-warning btn-sm" onclick="openEditModal('<%= s.getStudentId() %>', '<%= s.getName() %>', '<%= s.getGender() %>', '<%= s.getBirthDate() %>', '<%= s.getClassId() %>', '<%= s.getMajor() %>')">Sửa</button>
    <form action="delete-student" method="post" style="display:inline;" onsubmit="return confirm('Bạn có chắc muốn xóa sinh viên này?');">
        <input type="hidden" name="studentId" value="<%= s.getStudentId() %>">
        <button type="submit" class="btn btn-danger btn-sm">Xóa</button>
    </form>
</td>
        </tr>
        <%
                }
            } else {
        %>
        <tr><td colspan="6">Không có sinh viên nào.</td></tr>
        <%
            }
        %>
    </tbody>
</table>
    
    <script>
    function openAddModal() {
        document.getElementById('addStudentModal').style.display = 'block';
    }

    function closeAddModal() {
        document.getElementById('addStudentModal').style.display = 'none';
    }

    function openEditModal(id, name, gender, birthDate, classId, major) {
        document.getElementById('editStudentId').value = id;
        document.getElementById('editName').value = name;
        document.getElementById('editGender').value = gender;
        document.getElementById('editBirthDate').value = birthDate;
        document.getElementById('editClassId').value = classId;
        document.getElementById('editMajor').value = major;
        document.getElementById('editStudentModal').style.display = 'block';
    }

    function closeEditModal() {
        document.getElementById('editStudentModal').style.display = 'none';
    }
</script>


<jsp:include page="footer.jsp" />



