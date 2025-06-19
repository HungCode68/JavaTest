/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

/**
 *
 * @author Nguyễn Hùng
 */
import model.TeacherModel;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import util.DBUtil;

public class TeacherDAO {
   

    // Lấy toàn bộ danh sách giáo viên
    public List<TeacherModel> getAllTeachers() {
        List<TeacherModel> list = new ArrayList<>();
        String sql = "SELECT * FROM Teacher";

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                TeacherModel teacher = new TeacherModel(
                        rs.getString("teacher_id"),
                        rs.getString("name"),
                        rs.getString("gender"),
                        rs.getString("subject")
                );
                list.add(teacher);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }

    // Thêm giáo viên
    public void insertTeacher(TeacherModel teacher) {
        String sql = "INSERT INTO Teacher (teacher_id, name, gender, subject) VALUES (?, ?, ?, ?)";

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, teacher.getTeacherId());
            stmt.setString(2, teacher.getName());
            stmt.setString(3, teacher.getGender());
            stmt.setString(4, teacher.getSubject());

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Sửa thông tin giáo viên
    public void updateTeacher(TeacherModel teacher) {
        String sql = "UPDATE Teacher SET name = ?, gender = ?, subject = ? WHERE teacher_id = ?";

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, teacher.getName());
            stmt.setString(2, teacher.getGender());
            stmt.setString(3, teacher.getSubject());
            stmt.setString(4, teacher.getTeacherId());

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Xoá giáo viên theo ID
    public void deleteTeacher(String teacherId) {
        String sql = "DELETE FROM Teacher WHERE teacher_id = ?";

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, teacherId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Tìm giáo viên theo ID
    public TeacherModel getTeacherById(String teacherId) {
        String sql = "SELECT * FROM Teacher WHERE teacher_id = ?";
        TeacherModel teacher = null;

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, teacherId);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                teacher = new TeacherModel(
                        rs.getString("teacher_id"),
                        rs.getString("name"),
                        rs.getString("gender"),
                        rs.getString("subject")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return teacher;
    }
}
