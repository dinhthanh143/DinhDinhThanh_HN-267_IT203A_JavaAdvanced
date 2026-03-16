import java.util.*;

class ProductTH {
    private String name;
    private double price;
    public ProductTH(String name, double price) { this.name = name; this.price = price; }
    public double getPrice() { return price; }
}

class CustomerTH {
    private String name, email;
    public CustomerTH(String name, String email) { this.name = name; this.email = email; }
    public String getName() { return name; }
    public String getEmail() { return email; }
}

class OrderTH {
    private CustomerTH customer;
    private double totalAmount;
    private double finalAmount;
    public OrderTH(CustomerTH customer, double totalAmount) {
        this.customer = customer;
        this.totalAmount = totalAmount;
    }
    public void setFinalAmount(double finalAmount) { this.finalAmount = finalAmount; }
    public double getTotalAmount() { return totalAmount; }
    public double getFinalAmount() { return finalAmount; }
    public CustomerTH getCustomer() { return customer; }
}

interface DiscountStrategyTH {
    double applyDiscount(double total);
}

class PercentageDiscountTH implements DiscountStrategyTH {
    public double applyDiscount(double total) { return total * 0.9; }
}

class FixedDiscountTH implements DiscountStrategyTH {
    public double applyDiscount(double total) { return Math.max(0, total - 50000); }
}

class NoDiscountTH implements DiscountStrategyTH {
    public double applyDiscount(double total) { return total; }
}

class HolidayDiscountTH implements DiscountStrategyTH {
    public double applyDiscount(double total) { return total * 0.85; }
}

interface PaymentMethodTH {
    boolean process(double amount);
}

class CODPaymentTH implements PaymentMethodTH {
    public boolean process(double amount) {
        System.out.println("Xử lý thanh toán COD: " + (long)amount + " - Thành công!");
        return true;
    }
}

class CreditCardPaymentTH implements PaymentMethodTH {
    public boolean process(double amount) {
        System.out.println("Xử lý thanh toán qua thẻ tín dụng: " + (long)amount + " - Thành công!");
        return true;
    }
}

class MomoPaymentTH implements PaymentMethodTH {
    public boolean process(double amount) {
        System.out.println("Xử lý thanh toán qua ví Momo: " + (long)amount + " - Thành công!");
        return true;
    }
}

interface NotificationServiceTH {
    void send(String message, String recipient);
}

class EmailServiceTH implements NotificationServiceTH {
    public void send(String msg, String to) {
        System.out.println("Gửi email đến " + to + ": " + msg);
    }
}

interface OrderRepositoryTH {
    void save(OrderTH order);
}

class FileOrderRepositoryTH implements OrderRepositoryTH {
    public void save(OrderTH order) {
        System.out.println("Đã lưu đơn hàng vào file.");
    }
}

class OrderServiceTH {
    private DiscountStrategyTH discountStrategy;
    private PaymentMethodTH paymentMethod;
    private NotificationServiceTH notificationService;
    private OrderRepositoryTH orderRepository;

    public OrderServiceTH(DiscountStrategyTH ds, PaymentMethodTH pm, NotificationServiceTH ns, OrderRepositoryTH or) {
        this.discountStrategy = ds;
        this.paymentMethod = pm;
        this.notificationService = ns;
        this.orderRepository = or;
    }

    public void processOrder(OrderTH order) {
        double finalAmt = discountStrategy.applyDiscount(order.getTotalAmount());
        order.setFinalAmount(finalAmt);

        System.out.println("\nTổng tiền: " + (long)order.getTotalAmount());
        System.out.println("Sau giảm giá: " + (long)order.getFinalAmount());

        if (paymentMethod.process(finalAmt)) {
            orderRepository.save(order);
            notificationService.send("Đơn hàng của bạn đã được xác nhận.", order.getCustomer().getEmail());
        }
    }
}

public class TongHop {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("\n===== HỆ THỐNG ĐƠN HÀNG =====");
            System.out.println("1. Tạo đơn hàng mới");
            System.out.println("0. Thoát");
            System.out.print("Chọn: ");
            int choice = Integer.parseInt(sc.nextLine());

            if (choice == 0) break;

            System.out.print("Nhập tên khách hàng: "); String name = sc.nextLine();
            System.out.print("Nhập email: "); String email = sc.nextLine();
            System.out.print("Nhập tổng tiền đơn hàng: "); double total = Double.parseDouble(sc.nextLine());

            System.out.println("Chọn loại giảm giá: 1. Giảm 10% | 2. Giảm 50k | 3. Không giảm | 4. Lễ (15%)");
            int dType = Integer.parseInt(sc.nextLine());
            DiscountStrategyTH ds;
            switch (dType) {
                case 1: ds = new PercentageDiscountTH(); break;
                case 2: ds = new FixedDiscountTH(); break;
                case 4: ds = new HolidayDiscountTH(); break;
                default: ds = new NoDiscountTH(); break;
            }

            System.out.println("Chọn phương thức thanh toán: 1. COD | 2. Thẻ tín dụng | 3. Momo");
            int pType = Integer.parseInt(sc.nextLine());
            PaymentMethodTH pm;
            switch (pType) {
                case 2: pm = new CreditCardPaymentTH(); break;
                case 3: pm = new MomoPaymentTH(); break;
                default: pm = new CODPaymentTH(); break;
            }

            OrderServiceTH service = new OrderServiceTH(ds, pm, new EmailServiceTH(), new FileOrderRepositoryTH());
            service.processOrder(new OrderTH(new CustomerTH(name, email), total));
        }
        sc.close();
    }
}