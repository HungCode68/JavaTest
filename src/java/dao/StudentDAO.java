/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import model.Student;
import util.DBUtil;


/**
 *
 * @author Nguyễn Hùng
 */
public class StudentDAO {
    public List<Student> getAllStudents() {
        List<Student> list = new ArrayList<>();
        try (Connection conn = DBUtil.getConnection()) {
            String sql = "SELECT * FROM Student";
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Student s = new Student();
                s.setStudentId(rs.getString("student_id"));
                s.setName(rs.getString("name"));
                s.setGender(rs.getString("gender"));
                s.setBirthDate(rs.getString("birth_date"));
                s.setClassId(rs.getString("class_id"));
                s.setMajor(rs.getString("major"));
                list.add(s);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
    
    public void addStudent(Student s) {
    String sql = "INSERT INTO Student(student_id, name, gender, birth_date, class_id, major) VALUES (?, ?, ?, ?, ?, ?)";
    try (Connection conn = DBUtil.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
        ps.setString(1, s.getStudentId());
        ps.setString(2, s.getName());
        ps.setString(3, s.getGender());
        ps.setString(4, s.getBirthDate());
        ps.setString(5, s.getClassId());
        ps.setString(6, s.getMajor());
        ps.executeUpdate();
    } catch (Exception e) {
        e.printStackTrace();
    }
}

public void updateStudent(Student s) {
    String sql = "UPDATE Student SET name=?, gender=?, birth_date=?, class_id=?, major=? WHERE student_id=?";
    try (Connection conn = DBUtil.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
        ps.setString(1, s.getName());
        ps.setString(2, s.getGender());
        ps.setString(3, s.getBirthDate());
        ps.setString(4, s.getClassId());
        ps.setString(5, s.getMajor());
        ps.setString(6, s.getStudentId());
        ps.executeUpdate();
    } catch (Exception e) {
        e.printStackTrace();
    }
}

public void deleteStudent(String studentId) {
    String sql = "DELETE FROM Student WHERE student_id=?";
    try (Connection conn = DBUtil.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
        ps.setString(1, studentId);
        ps.executeUpdate();
    } catch (Exception e) {
        e.printStackTrace();
    }
}

}

