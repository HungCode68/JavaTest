/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

/**
 *
 * @author Nguyễn Hùng
 */
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import model.ClassModel;
import util.DBUtil;

public class ClassDAO {
    
    // Lấy tất cả lớp học
    public List<ClassModel> getAllClasses() {
        List<ClassModel> list = new ArrayList<>();
        String sql = "SELECT * FROM Class";

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                ClassModel c = new ClassModel(
                    rs.getString("class_id"),
                    rs.getString("name"),
                    rs.getString("department")
                );
                list.add(c);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }

    // Lấy lớp học theo ID
    public ClassModel getClassById(String id) {
        String sql = "SELECT * FROM Class WHERE class_id = ?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
             
            ps.setString(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new ClassModel(
                        rs.getString("class_id"),
                        rs.getString("name"),
                        rs.getString("department")
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // Thêm lớp học
    public void insertClass(ClassModel c) {
        String sql = "INSERT INTO Class (class_id, name, department) VALUES (?, ?, ?)";

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
             
            ps.setString(1, c.getClassId());
            ps.setString(2, c.getName());
            ps.setString(3, c.getDepartment());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Cập nhật lớp học
    public void updateClass(ClassModel c) {
        String sql = "UPDATE Class SET name = ?, department = ? WHERE class_id = ?";

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, c.getName());
            ps.setString(2, c.getDepartment());
            ps.setString(3, c.getClassId());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Xoá lớp học
    public void deleteClass(String id) {
        String sql = "DELETE FROM Class WHERE class_id = ?";

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
             
            ps.setString(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
