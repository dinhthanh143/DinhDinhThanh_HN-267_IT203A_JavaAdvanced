package ss11.b5;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import config.DatabaseConfig;
//Dât access object
public class DoctorDAO {
    private static final String DB_NAME = "Hospital_DB";

    private Connection getConnection() throws Exception {
        Class.forName(DatabaseConfig.DRIVER);
        return DriverManager.getConnection(DatabaseConfig.getURL(DB_NAME), DatabaseConfig.USER, DatabaseConfig.PASS);
    }

    public List<DoctorModel> getAll() throws Exception {
        List<DoctorModel> list = new ArrayList<>();
        String sql = "SELECT * FROM Doctors";
        try (Connection conn = getConnection(); Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                list.add(new DoctorModel(rs.getInt("doctor_id"), rs.getString("full_name"), rs.getString("specialization")));
            }
        }
        return list;
    }

    public boolean add(int id, String name, String spec) throws Exception {
        String sql = "INSERT INTO Doctors (doctor_id, full_name, specialization) VALUES (?, ?, ?)";
        try (Connection conn = getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            pstmt.setString(2, name);
            pstmt.setString(3, spec);
            return pstmt.executeUpdate() > 0;
        }
    }

    public void statistic() throws Exception {
        String sql = "SELECT specialization, COUNT(*) as total FROM Doctors GROUP BY specialization";
        try (Connection conn = getConnection(); Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            System.out.println("\n--- THONG KE CHUYEN KHOA ---");
            while (rs.next()) {
                System.out.println("Khoa: " + rs.getString("specialization") + " | So luong: " + rs.getInt("total"));
            }
        }
    }
}