/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

/**
 *
 * @author Nguyễn Hùng
 */
import model.AccountModel;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import util.DBUtil;

public class AccountDAO {

    // Đăng nhập: kiểm tra username và password
    public AccountModel login(String username, String password) {
        String sql = "SELECT * FROM Account WHERE Username = ? AND Password = ?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, username);
            stmt.setString(2, password);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new AccountModel(
                        rs.getInt("Id"),
                        rs.getString("Username"),
                        rs.getString("Password"),
                        rs.getString("Role")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // Đăng ký tài khoản mới
    public boolean register(AccountModel account) {
        String sql = "INSERT INTO Account (Username, Password, Role) VALUES (?, ?, ?)";

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, account.getUsername());
            stmt.setString(2, account.getPassword());
            stmt.setString(3, account.getRole());

            int affectedRows = stmt.executeUpdate();
            return affectedRows > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // Lấy danh sách tất cả tài khoản
    public List<AccountModel> getAllAccounts() {
        List<AccountModel> list = new ArrayList<>();
        String sql = "SELECT * FROM Account";

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                AccountModel account = new AccountModel(
                        rs.getInt("Id"),
                        rs.getString("Username"),
                        rs.getString("Password"),
                        rs.getString("Role")
                );
                list.add(account);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }

    // Thêm tài khoản (giống đăng ký, nhưng cho quản trị viên)
    public void insertAccount(AccountModel account) {
        register(account);
    }

    // Cập nhật tài khoản
    public void updateAccount(AccountModel account) {
        String sql = "UPDATE Account SET Username = ?, Password = ?, Role = ? WHERE Id = ?";

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, account.getUsername());
            stmt.setString(2, account.getPassword());
            stmt.setString(3, account.getRole());
            stmt.setInt(4, account.getId());

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Xoá tài khoản theo ID
    public void deleteAccount(int id) {
        String sql = "DELETE FROM Account WHERE Id = ?";

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Lấy tài khoản theo ID
    public AccountModel getAccountById(int id) {
        String sql = "SELECT * FROM Account WHERE Id = ?";
        AccountModel account = null;

        try (Connection conn =DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                account = new AccountModel(
                        rs.getInt("Id"),
                        rs.getString("Username"),
                        rs.getString("Password"),
                        rs.getString("Role")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return account;
    }
}
