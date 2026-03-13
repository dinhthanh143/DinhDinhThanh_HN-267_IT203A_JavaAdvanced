import java.util.*;

class Ticket {
    String ticketId;
    String roomName;
    boolean isSold = false;

    public Ticket(String ticketId, String roomName) {
        this.ticketId = ticketId;
        this.roomName = roomName;
    }
}

class TicketPool {
    private String roomName;
    private List<Ticket> tickets = new ArrayList<>();

    public TicketPool(String roomName, int quantity) {
        this.roomName = roomName;
        addTickets(quantity);
    }

    public synchronized Ticket sellTicket() {
        for (Ticket t : tickets) {
            if (!t.isSold) {
                t.isSold = true;
                return t;
            }
        }
        return null;
    }

    public synchronized void addTickets(int count) {
        int currentSize = tickets.size();
        for (int i = 1; i <= count; i++) {
            tickets.add(new Ticket(roomName + "-" + (currentSize + i), roomName));
        }
    }

    public synchronized int getRemainingCount() {
        return (int) tickets.stream().filter(t -> !t.isSold).count();
    }
    public String getRoomName() { return roomName; }
}

class BookingCounter implements Runnable {
    private String counterName;
    private TicketPool poolA, poolB;
    private int soldCount = 0;

    public BookingCounter(String name, TicketPool a, TicketPool b) {
        this.counterName = name;
        this.poolA = a;
        this.poolB = b;
    }

    @Override
    public void run() {
        Random rand = new Random();
        while (poolA.getRemainingCount() > 0 || poolB.getRemainingCount() > 0) {
            TicketPool target = rand.nextBoolean() ? poolA : poolB;
            Ticket t = target.sellTicket();

            if (t == null) {
                target = (target == poolA) ? poolB : poolA;
                t = target.sellTicket();
            }

            if (t != null) {
                soldCount++;
                System.out.println(counterName + " đã bán vé " + t.ticketId);
                try { Thread.sleep(500); } catch (InterruptedException e) {}
            }
        }
    }
    public int getSoldCount() { return soldCount; }
}

class TicketSupplier implements Runnable {
    private TicketPool poolA, poolB;
    public TicketSupplier(TicketPool a, TicketPool b) { this.poolA = a; this.poolB = b; }

    @Override
    public void run() {
        for (int i = 0; i < 2; i++) {
            try { Thread.sleep(3000); } catch (InterruptedException e) {}
            poolA.addTickets(3);
            poolB.addTickets(3);
            System.out.println("\n[NHÀ CUNG CẤP]: Đã thêm 3 vé vào mỗi phòng\n");
        }
    }
}

public class RapChieuPhimBasic {
    public static void main(String[] args) throws InterruptedException {
        TicketPool roomA = new TicketPool("A", 10);
        TicketPool roomB = new TicketPool("B", 10);

        BookingCounter bc1 = new BookingCounter("Quầy 1", roomA, roomB);
        BookingCounter bc2 = new BookingCounter("Quầy 2", roomA, roomB);

        Thread t1 = new Thread(bc1);
        Thread t2 = new Thread(bc2);

        t1.start();
        t2.start();

        t1.join();
        t2.join();

        System.out.println("\nKết thúc chương trình");

        System.out.println("Quầy 1 bán được: " + bc1.getSoldCount() + " vé");
        System.out.println("Quầy 2 bán được: " + bc2.getSoldCount() + " vé");

        System.out.println("Vé còn lại phòng A: " + roomA.getRemainingCount());
        System.out.println("Vé còn lại phòng B: " + roomB.getRemainingCount());
    }
}