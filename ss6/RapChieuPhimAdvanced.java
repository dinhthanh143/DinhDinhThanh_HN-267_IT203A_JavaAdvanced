import java.util.*;

class AdvancedTicketPool {
    private String name;
    private List<String> tickets = new ArrayList<>();

    public AdvancedTicketPool(String name, int qty) {
        this.name = name;
        for (int i = 1; i <= qty; i++) tickets.add(name + "-" + i);
    }

    public synchronized void addTickets(int qty) {
        for (int i = 1; i <= qty; i++) tickets.add(name + "-New-" + i);
        this.notifyAll();
    }

    public synchronized String getTicket() throws InterruptedException {
        while (tickets.isEmpty()) {
            this.wait();
        }
        return tickets.remove(0);
    }

    public synchronized boolean hasTicket() { return !tickets.isEmpty(); }
    public String getName() { return name; }
}

public class RapChieuPhimAdvanced {
    public static void main(String[] args) {
        AdvancedTicketPool poolA = new AdvancedTicketPool("A", 5);
        AdvancedTicketPool poolB = new AdvancedTicketPool("B", 5);

        Runnable comboTask = () -> {
            String name = Thread.currentThread().getName();
            try {
                synchronized (poolA) {
                    System.out.println(name + " đã khóa phòng A");
                    Thread.sleep(100);
                    synchronized (poolB) {
                        System.out.println(name + " đã khóa phòng B");
                        if (poolA.hasTicket() && poolB.hasTicket()) {
                            System.out.println(name + " BÁN COMBO THÀNH CÔNG: " + poolA.getTicket() + " & " + poolB.getTicket());
                        }
                    }
                }
            } catch (InterruptedException e) {}
        };

        new Thread(comboTask, "Quầy 1").start();
        new Thread(comboTask, "Quầy 2").start();
    }
}