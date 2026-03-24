package ss11.b2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import config.DatabaseConfig;
public class PharmacyCatalogue {
    private static final String DB_NAME = "Hospital_DB";

    public void displayAllMedicines() {
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;

        try {
            Class.forName(DatabaseConfig.DRIVER);
            conn = DriverManager.getConnection(DatabaseConfig.getURL(DB_NAME), DatabaseConfig.USER, DatabaseConfig.PASS);

            String sql = "SELECT medicine_name, stock_quantity FROM Medicines";
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);

            System.out.println("----- DANH MỤC THUỐC TRONG KHO -----");
            System.out.printf("%-25s | %-15s\n", "Ten Thuoc", "So Luong Ton");
            System.out.println("----------------------------------------------");

            while (rs.next()) {
                String name = rs.getString("medicine_name");
                int stock = rs.getInt("stock_quantity");

                System.out.printf("%-25s | %-15d\n", name, stock);
            }
            System.out.println("----------------------------------------------");

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
        PharmacyCatalogue app = new PharmacyCatalogue();
        app.displayAllMedicines();
    }
}