import java.util.*;

class Product {
    private String id;
    private String name;
    private double price;

    public Product(String id, String name, double price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    public String getId() { return id; }
    public String getName() { return name; }
    public double getPrice() { return price; }
}

class Customer {
    private String name;
    private String email;

    public Customer(String name, String email) {
        this.name = name;
        this.email = email;
    }

    public String getName() { return name; }
    public String getEmail() { return email; }
}

class Order {
    private String orderId;
    private Customer customer;
    private Map<Product, Integer> items = new HashMap<>();

    public Order(String orderId, Customer customer) {
        this.orderId = orderId;
        this.customer = customer;
    }

    public void addProduct(Product product, int quantity) {
        items.put(product, quantity);
    }

    public String getOrderId() { return orderId; }
    public Customer getCustomer() { return customer; }
    public Map<Product, Integer> getItems() { return items; }
}

class OrderCalculator {
    public double calculateTotal(Order order) {
        double total = 0;
        for (Map.Entry<Product, Integer> entry : order.getItems().entrySet()) {
            total += entry.getKey().getPrice() * entry.getValue();
        }
        return total;
    }
}

class OrderRepository {
    public void save(Order order) {
        System.out.println("Đã lưu đơn hàng " + order.getOrderId());
    }
}

class EmailService {
    public void sendConfirmation(Order order) {
        System.out.println("Đã gửi email đến " + order.getCustomer().getEmail() + ": Đơn hàng " + order.getOrderId() + " đã được tạo");
    }
}

public class b1 {
    public static void main(String[] args) {
        Product sp01 = new Product("SP01", "Laptop", 15000000);
        Product sp02 = new Product("SP02", "Chuột", 300000);
        System.out.println("Đã thêm sản phẩm " + sp01.getId() + ", " + sp02.getId());

        Customer customer = new Customer("Nguyễn Văn A", "a@example.com");
        System.out.println("Đã thêm khách hàng");

        Order order = new Order("ORD001", customer);
        order.addProduct(sp01, 1);
        order.addProduct(sp02, 2);
        System.out.println("Đơn hàng " + order.getOrderId() + " được tạo");

        OrderCalculator calculator = new OrderCalculator();
        double total = calculator.calculateTotal(order);
        System.out.println("Tổng tiền: " + (long)total);

        OrderRepository repository = new OrderRepository();
        repository.save(order);

        EmailService emailService = new EmailService();
        emailService.sendConfirmation(order);
    }
}