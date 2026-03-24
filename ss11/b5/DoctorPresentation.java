package ss11.b5;

import java.util.Scanner;

public class DoctorPresentation {
    private DoctorDAO dao = new DoctorDAO();
    private Scanner sc = new Scanner(System.in);

    public void start() {
        while (true) {
            System.out.println("\n=== HE THONG RIKKEI-CARE ===");
            System.out.println("1. Xem danh sach bac si");
            System.out.println("2. Them bac si moi");
            System.out.println("3. Thong ke chuyen khoa");
            System.out.println("0. Thoat");
            System.out.print("Chon chuc nang: ");

            try {
                int choice = Integer.parseInt(sc.nextLine());
                if (choice == 0) break;
                switch (choice) {
                    case 1: showAll(); break;
                    case 2: addNew(); break;
                    case 3: dao.statistic(); break;
                    default: System.out.println("Chon sai roi Thanh oi!");
                }
            } catch (Exception e) {
                System.out.println("LOI: " + e.getMessage());
            }
        }
    }

    private void showAll() throws Exception {
        System.out.println("\n--- DANH SACH BAC SI ---");
        for (DoctorModel d : dao.getAll()) System.out.println(d);
    }

    private void addNew() throws Exception {
        System.out.print("Nhap ID: "); int id = Integer.parseInt(sc.nextLine());
        System.out.print("Nhap Ho ten: "); String name = sc.nextLine();
        System.out.print("Nhap Chuyen khoa: "); String spec = sc.nextLine();

        if (dao.add(id, name, spec)) System.out.println("Them thanh cong!");
    }

    public static void main(String[] args) {
        new DoctorPresentation().start();
    }
}