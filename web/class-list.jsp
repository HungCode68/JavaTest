<%-- 
    Document   : class-list
    Created on : Jun 2, 2025, 10:33:43 PM
    Author     : Nguyễn Hùng
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="model.ClassModel" %>
<%@ page import="java.util.List" %>
<%
    List<ClassModel> classList = (List<ClassModel>) request.getAttribute("classList");
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Danh sách lớp học</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
</head>
<body>
<div class="container mt-4">
    <h2 class="mb-4">Danh sách lớp học</h2>
    <button class="btn btn-primary mb-3" data-toggle="modal" data-target="#addModal">➕ Thêm lớp</button>
    <table class="table table-bordered">
        <thead class="thead-light">
            <tr>
                <th>Mã lớp</th>
                <th>Tên lớp</th>
                <th>Khoa</th>
                <th>Hành động</th>
            </tr>
        </thead>
        <tbody>
        <%
            for (ClassModel c : classList) {
        %>
            <tr>
                <td><%= c.getClassId() %></td>
                <td><%= c.getName() %></td>
                <td><%= c.getDepartment() %></td>
                <td>
                    <button class="btn btn-warning btn-sm editBtn"
                            data-id="<%= c.getClassId() %>"
                            data-name="<%= c.getName() %>"
                            data-department="<%= c.getDepartment() %>">Sửa</button>
                    <a href="class?action=delete&id=<%= c.getClassId() %>" class="btn btn-danger btn-sm"
                       onclick="return confirm('Bạn có chắc muốn xóa lớp này không?')">Xóa</a>
                </td>
            </tr>
        <%
            }
        %>
        </tbody>
    </table>
</div>

<!-- ✅ Modal Thêm -->
<div class="modal fade" id="addModal" tabindex="-1" role="dialog" aria-labelledby="addModalLabel">
    <div class="modal-dialog" role="document">
        <form action="class" method="post">
            <input type="hidden" name="action" value="insert">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title">Thêm lớp mới</h5>
                    <button type="button" class="close" data-dismiss="modal">&times;</button>
                </div>
                <div class="modal-body">
                    <div class="form-group">
                        <label>Mã lớp</label>
                        <input type="text" name="classId" class="form-control" required>
                    </div>
                    <div class="form-group">
                        <label>Tên lớp</label>
                        <input type="text" name="name" class="form-control" required>
                    </div>
                    <div class="form-group">
                        <label>Khoa</label>
                        <input type="text" name="department" class="form-control" required>
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="submit" class="btn btn-primary">Lưu</button>
                    <button type="button" class="btn btn-secondary" data-dismiss="modal">Hủy</button>
                </div>
            </div>
        </form>
    </div>
</div>

<!-- ✅ Modal Sửa -->
<div class="modal fade" id="editModal" tabindex="-1" role="dialog" aria-labelledby="editModalLabel">
    <div class="modal-dialog" role="document">
        <form action="class" method="post">
            <input type="hidden" name="action" value="update">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title">Sửa lớp học</h5>
                    <button type="button" class="close" data-dismiss="modal">&times;</button>
                </div>
                <div class="modal-body">
                    <div class="form-group">
                        <label>Mã lớp</label>
                        <input type="text" name="classId" id="editClassId" class="form-control" readonly>
                    </div>
                    <div class="form-group">
                        <label>Tên lớp</label>
                        <input type="text" name="name" id="editName" class="form-control" required>
                    </div>
                    <div class="form-group">
                        <label>Khoa</label>
                        <input type="text" name="department" id="editDepartment" class="form-control" required>
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="submit" class="btn btn-success">Cập nhật</button>
                    <button type="button" class="btn btn-secondary" data-dismiss="modal">Hủy</button>
                </div>
            </div>
        </form>
    </div>
</div>

<!-- Bootstrap & jQuery -->
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>

<!-- ✅ Script để mở modal Sửa và đổ dữ liệu -->
<script>
    $(document).ready(function () {
        $('.editBtn').click(function () {
            const classId = $(this).data('id');
            const name = $(this).data('name');
            const department = $(this).data('department');

            $('#editClassId').val(classId);
            $('#editName').val(name);
            $('#editDepartment').val(department);

            $('#editModal').modal('show');
        });
    });
</script>
</body>
</html>

