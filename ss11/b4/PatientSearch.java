package ss11.b4;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import config.DatabaseConfig;

/*
 * PHẦN 1 - PHÂN TÍCH:
 * 1. Luồng thực thi: Khi nối chuỗi trực tiếp, ký tự đơn (') của hacker kết thúc sớm giá trị của biến.
 * Phần còn lại biến thành các từ khóa SQL có nghĩa (OR, --, ...), thay đổi hoàn toàn logic câu lệnh.
 * 2. Tại sao luôn đúng: Biểu thức '1'='1' là một chân trị (luôn true).
 * Trong phép toán OR, nếu có một vế true thì kết quả cuối cùng là true,
 * khiến mệnh đề WHERE bỏ qua mọi bộ lọc và lấy hết dữ liệu trong bảng Patients.
 */

public class PatientSearch {
    private static final String DB_NAME = "Hospital_DB";

    private String sanitize(String input) {
        if (input == null) return "";
        return input.replace("'", "")
                .replace("--", "")
                .replace(";", "");
    }

    public void searchByName(String patientName) {
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;

        String safeName = sanitize(patientName);

        try {
            Class.forName(DatabaseConfig.DRIVER);
            conn = DriverManager.getConnection(DatabaseConfig.getURL(DB_NAME), DatabaseConfig.USER, DatabaseConfig.PASS);

            stmt = conn.createStatement();
            String sql = "SELECT full_name, diagnosis FROM Patients WHERE full_name = '" + safeName + "'";

            System.out.println("SQL dang chay: " + sql);
            rs = stmt.executeQuery(sql);

            System.out.println("--- KẾT QUẢ TÌM KIẾM ---");
            boolean isExist = false;
            while (rs.next()) {
                isExist = true;
                System.out.println("Bệnh nhân: " + rs.getString("full_name") + " | Chẩn đoán: " + rs.getString("diagnosis"));
            }

            if (!isExist) {
                System.out.println("Khong tim thay ket qua phu hop.");
            }

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        PatientSearch app = new PatientSearch();

        String hackerInput = "' OR '1'='1";

        System.out.println("Tim kiem voi input nguy hiem: " + hackerInput);
        app.searchByName(hackerInput);
    }
}