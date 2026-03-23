package ss12.TongHop;

import java.sql.*;
import java.util.Scanner;
import config.DatabaseConfig;



public class PharmacyManager {
    private static final String DB_NAME = "PharmacyDB";

    private Connection getConnection() throws Exception {
        Class.forName(DatabaseConfig.DRIVER);
        return DriverManager.getConnection(DatabaseConfig.getURL(DB_NAME), DatabaseConfig.USER, DatabaseConfig.PASS);
    }

    // Bước 1: Quản lý kho thuốc
    public void updateMedicineStock(int id, int addedQuantity) throws Exception {
        String sql = "UPDATE medicines SET stock = stock + ? WHERE id = ?";
        try (Connection conn = getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, addedQuantity);
            pstmt.setInt(2, id);
            int rows = pstmt.executeUpdate();
            System.out.println(rows > 0 ? "Cap nhat kho thanh cong!" : "Khong tim thay ID thuoc.");
        }
    }

    // Bước 2: Tìm kiếm thuốc theo khoảng giá
    public void findMedicinesByPriceRange(double minPrice, double maxPrice) throws Exception {
        String sql = "SELECT * FROM medicines WHERE price BETWEEN ? AND ?";
        try (Connection conn = getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setDouble(1, minPrice);
            pstmt.setDouble(2, maxPrice);
            ResultSet rs = pstmt.executeQuery();
            System.out.println("--- Danh sach thuoc trong khoang gia ---");
            while (rs.next()) {
                System.out.printf("ID: %d | Ten: %s | Gia: %,.2f | Ton: %d\n",
                        rs.getInt("id"), rs.getString("name"), rs.getDouble("price"), rs.getInt("stock"));
            }
        }
    }

    // Bước 3: Tính tổng tiền đơn thuốc (OUT)
    public void getPrescriptionTotal(int prescriptionId) throws Exception {
        String sql = "{call CalculatePrescriptionTotal(?, ?)}";
        try (Connection conn = getConnection(); CallableStatement cstmt = conn.prepareCall(sql)) {
            cstmt.setInt(1, prescriptionId);
            cstmt.registerOutParameter(2, Types.DECIMAL);
            cstmt.execute();
            double total = cstmt.getDouble(2);
            System.out.printf("Tong tien don thuoc ID %d: %,.2f VND\n", prescriptionId, total);
        }
    }

    // Bước 4: Thống kê doanh thu theo ngày (IN/OUT)
    public void getDailyRevenue(String dateStr) throws Exception {
        String sql = "{call GetDailyRevenue(?, ?)}";
        try (Connection conn = getConnection(); CallableStatement cstmt = conn.prepareCall(sql)) {
            cstmt.setDate(1, Date.valueOf(dateStr));
            cstmt.registerOutParameter(2, Types.DECIMAL);
            cstmt.execute();
            double revenue = cstmt.getDouble(2);
            System.out.printf("Doanh thu ngay %s: %,.2f VND\n", dateStr, revenue);
        }
    }

    public static void main(String[] args) {
        PharmacyManager manager = new PharmacyManager();
        Scanner sc = new Scanner(System.in);

        try {
            System.out.println("1. Cap nhat ton kho (ID 1, them 20)");
            manager.updateMedicineStock(1, 20);

            System.out.print("\n2. Nhap khoang gia (min max): ");
            manager.findMedicinesByPriceRange(sc.nextDouble(), sc.nextDouble());
            sc.nextLine();

            System.out.print("\n3. Nhap ID don thuoc can tinh tien: ");
            manager.getPrescriptionTotal(sc.nextInt());
            sc.nextLine();

            System.out.print("\n4. Nhap ngay thong ke (yyyy-MM-dd): ");
            manager.getDailyRevenue(sc.nextLine());

        } catch (Exception e) {
            System.err.println("Loi: " + e.getMessage());
        }
    }
}