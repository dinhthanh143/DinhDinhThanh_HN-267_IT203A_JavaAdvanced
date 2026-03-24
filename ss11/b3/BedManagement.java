package ss11.b3;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import config.DatabaseConfig;

/*
 * PHẦN 1 - PHÂN TÍCH:
 * 1. Giá trị trả về của executeUpdate(): Là số lượng bản ghi (dòng) bị thay đổi bởi câu lệnh SQL.
 * 2. Cách phản hồi chính xác: Kiểm tra giá trị trả về. Nếu result > 0 là thành công,
 * nếu result == 0 nghĩa là không tìm thấy bản ghi thỏa mãn điều kiện WHERE (Mã giường không tồn tại).
 */

public class BedManagement {
    private static final String DB_NAME = "Hospital_DB";

    public void updateBedStatus(String inputId) {
        Connection conn = null;
        PreparedStatement pstmt = null;

        String sql = "UPDATE Beds SET bed_status = 'Dang su dung' WHERE bed_id = ?";

        try {
            Class.forName(DatabaseConfig.DRIVER);
            conn = DriverManager.getConnection(DatabaseConfig.getURL(DB_NAME), DatabaseConfig.USER, DatabaseConfig.PASS);

            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, inputId);

            int rowsAffected = pstmt.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Thanh cong: Da cap nhat trang thai cho giuong " + inputId);
            } else {
                System.out.println("LOI: Ma giuong '" + inputId + "' khong ton tai trong he thong!");
            }

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (pstmt != null) pstmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        BedManagement app = new BedManagement();

        System.out.println("--- Test ma dung ---");
        app.updateBedStatus("Bed_001");

        System.out.println("\n--- Test ma sai ---");
        app.updateBedStatus("Bed_999");
    }
}