package ss11.TongHop;

import java.sql.Date;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        AppointmentRepository repo = new AppointmentRepository();

        try {
            System.out.println("--- 1. Them moi lich kham ---");
            Appointment newApp = new Appointment("Tran Van Thanh", Date.valueOf("2026-04-01"), "Dr. Strange", "Pending");
            repo.addAppointment(newApp);

            System.out.println("\n--- 2. Danh sach hien tai ---");
            printList(repo.getAllAppointments());

            System.out.println("\n--- 3. Cap nhat lich kham ID = 1 ---");
            Appointment updateApp = new Appointment(1, "Nguyen Van Thanh Updated", Date.valueOf("2026-03-25"), "Dr. Strange", "Confirmed");
            repo.updateAppointment(updateApp);

            System.out.println("\n--- 4. Xoa lich kham ID = 2 ---");
            repo.deleteAppointment(2);

            System.out.println("\n--- KET QUA CUOI CUNG ---");
            printList(repo.getAllAppointments());

        } catch (Exception e) {
            System.err.println("Loi he thong: " + e.getMessage());
        }
    }

    private static void printList(List<Appointment> list) {
        if (list.isEmpty()) System.out.println("Danh sach trong.");
        for (Appointment a : list) System.out.println(a);
    }
}