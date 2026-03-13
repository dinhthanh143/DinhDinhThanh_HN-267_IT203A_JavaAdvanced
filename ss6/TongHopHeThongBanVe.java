import java.util.*;

class FinalTicket {
    String ticketId;
    String roomName;

    public FinalTicket(String ticketId, String roomName) {
        this.ticketId = ticketId;
        this.roomName = roomName;
    }
}

class FinalTicketPool {
    private String roomName;
    private List<FinalTicket> tickets = new ArrayList<>();

    public FinalTicketPool(String name, int initialQty) {
        this.roomName = name;
        addTickets(initialQty);
    }

    public synchronized FinalTicket sellTicket(String counterName) throws InterruptedException {
        while (tickets.isEmpty()) {
            System.out.println(">>> " + counterName + ": " + roomName + " đang hết vé, đang chờ...");
            wait();
        }
        return tickets.remove(0);
    }

    public synchronized void addTickets(int count) {
        int currentSize = tickets.size();
        for (int i = 1; i <= count; i++) {
            tickets.add(new FinalTicket(roomName + "-" + (currentSize + i), roomName));
        }
        notifyAll();
    }

    public synchronized int getAvailableCount() {
        return tickets.size();
    }
    public String getRoomName() { return roomName; }
}

class FinalBookingCounter implements Runnable {
    private String counterName;
    private FinalTicketPool poolA, poolB;
    private int totalSold = 0;
    private volatile boolean isActive = true;

    public FinalBookingCounter(String name, FinalTicketPool a, FinalTicketPool b) {
        this.counterName = name;
        this.poolA = a;
        this.poolB = b;
    }

    public void stopCounter() { this.isActive = false; }

    @Override
    public void run() {
        Random rand = new Random();
        try {
            while (isActive) {
                FinalTicketPool target = rand.nextBoolean() ? poolA : poolB;

                synchronized (poolA) {
                    synchronized (poolB) {
                        if (poolA.getAvailableCount() > 0 || poolB.getAvailableCount() > 0) {
                            FinalTicket t = target.sellTicket(counterName);
                            if (t != null) {
                                totalSold++;
                                System.out.println(counterName + " bán vé " + t.ticketId);
                            }
                        } else {
                            break;
                        }
                    }
                }
                Thread.sleep(400);
            }
        } catch (InterruptedException e) {
            System.out.println(counterName + " dừng.");
        }
    }

    public int getTotalSold() { return totalSold; }
}

class FinalTicketSupplier implements Runnable {
    private FinalTicketPool poolA, poolB;

    public FinalTicketSupplier(FinalTicketPool a, FinalTicketPool b) {
        this.poolA = a;
        this.poolB = b;
    }

    @Override
    public void run() {
        try {
            for (int i = 0; i < 2; i++) {
                Thread.sleep(4000);
                poolA.addTickets(5);
                poolB.addTickets(5);
                System.out.println("\n[HỆ THỐNG]: Đã nạp thêm 10 vé mới!\n");
            }
        } catch (InterruptedException e) {}
    }
}

public class TongHopHeThongBanVe {
    public static void main(String[] args) throws InterruptedException {
        FinalTicketPool roomA = new FinalTicketPool("Phòng A", 10);
        FinalTicketPool roomB = new FinalTicketPool("Phòng B", 10);

        FinalBookingCounter bc1 = new FinalBookingCounter("Quầy 1", roomA, roomB);
        FinalBookingCounter bc2 = new FinalBookingCounter("Quầy 2", roomA, roomB);
        FinalBookingCounter bc3 = new FinalBookingCounter("Quầy 3", roomA, roomB);
        FinalTicketSupplier supplier = new FinalTicketSupplier(roomA, roomB);

        Thread t1 = new Thread(bc1);
        Thread t2 = new Thread(bc2);
        Thread t3 = new Thread(bc3);
        Thread ts = new Thread(supplier);

        t1.start(); t2.start(); t3.start(); ts.start();

        ts.join();
        Thread.sleep(2000);
        bc1.stopCounter(); bc2.stopCounter(); bc3.stopCounter();

        t1.join(); t2.join(); t3.join();

        System.out.println("\n=== THỐNG KÊ TỔNG HỢP ===");
        System.out.println("Quầy 1: " + bc1.getTotalSold());
        System.out.println("Quầy 2: " + bc2.getTotalSold());
        System.out.println("Quầy 3: " + bc3.getTotalSold());
        System.out.println("Vé còn dư A: " + roomA.getAvailableCount());
        System.out.println("Vé còn dư B: " + roomB.getAvailableCount());
    }
}