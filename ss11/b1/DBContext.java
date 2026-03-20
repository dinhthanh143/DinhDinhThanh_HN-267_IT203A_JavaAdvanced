package ss11.b1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;

/*
 * PHẦN 1 - PHÂN TÍCH:
 * Việc khởi tạo kết nối liên tục mà không đóng (Close) gây nguy hiểm cho hệ thống y tế 24/7 vì:
 * 1. Gây cạn kiệt tài nguyên bộ nhớ (Memory Leak) và giới hạn kết nối của Database (Connection Pool Exhaustion).
 * 2. Khi đạt ngưỡng giới hạn, hệ thống sẽ từ chối các kết nối mới, dẫn đến tình trạng "treo" hoặc sập ứng dụng.
 * 3. Trong y tế, việc truy cập hồ sơ bệnh nhân bị gián đoạn có thể gây hậu quả nghiêm trọng đến tính mạng và quy trình cấp cứu.
 * 4. Database phải tốn tài nguyên duy trì các kết nối "ma", làm giảm hiệu suất xử lý các truy vấn quan trọng khác.
 */

public class DBContext {
    private static final String URL = "jdbc:mysql://localhost:3306/Hospital_DB";
    private static final String USER = "root";
    private static final String PASS = "neonlime0987";

    public Connection getConnection() throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            return DriverManager.getConnection(URL, USER, PASS);
        } catch (ClassNotFoundException e) {
            return null;
        }
    }

    public void testConnection() {
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        try {
            conn = getConnection();
            if (conn != null) {
                stmt = conn.createStatement();
                rs = stmt.executeQuery("SELECT 1");
                System.out.println("Ket noi va truy van thanh cong!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
                if (conn != null) {
                    conn.close();
                    System.out.println("Da dong ket noi an toan.");
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        DBContext db = new DBContext();
        db.testConnection();
    }
}