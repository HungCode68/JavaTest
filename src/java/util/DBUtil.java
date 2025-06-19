/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package util;

import com.sun.jdi.connect.spi.Connection;
import java.sql.DriverManager;
import java.sql.DriverManager;
/**
 *
 * @author Nguyễn Hùng
 */
public class DBUtil {
    public static java.sql.Connection getConnection() {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");

            String url = "jdbc:sqlserver://localhost:1433;databaseName=University_db;encrypt=true;trustServerCertificate=true";
            String user = "sa";       // thay bằng user của bạn
            String pass = "123"; // thay bằng mật khẩu của bạn

            return DriverManager.getConnection(url, user, pass);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}

