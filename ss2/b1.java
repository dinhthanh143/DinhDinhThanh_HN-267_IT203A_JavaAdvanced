import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class b1 {

    public static void run() {
        // 1. Supplier: Khởi tạo User mặc định (Không đầu vào -> Trả về User)
        Supplier<User> userSupplier = () -> new User(1, "Default User", "default@example.com", "User");
        User defaultUser = userSupplier.get();

        // 2. Consumer: In thông tin chi tiết (Đầu vào User -> Thực hiện hành động, không trả về)
        Consumer<User> userPrinter = u -> {
            System.out.println("ID: " + u.getId());
            System.out.println("Name: " + u.getName());
            System.out.println("Email: " + u.getEmail());
        };

        System.out.println("=== Thông tin chi tiết của User (dùng Consumer): ===");
        userPrinter.accept(defaultUser);

        // 3. Function: Chuyển đổi User thành String (Đầu vào User -> Trả về String)
        Function<User, String> userToStringConverter = u -> "Full User Info: " + u.toString();
        System.out.println(userToStringConverter.apply(defaultUser));

        // 4. Predicate: Kiểm tra Admin (Đầu vào User -> Trả về boolean)
        User customUser = new User(2, "John Doe", "john.doe@example.com", "Admin");
        Predicate<User> adminCheck = u -> "Admin".equals(u.getRole());

        System.out.println("\n=== Kiểm tra quyền Admin (dùng Predicate): ===");
        System.out.println("User: " + customUser.getName() + " is Admin? " + adminCheck.test(customUser));
    }

    public static void main(String[] args) {
        run();
    }
}

class User {
    private int id;
    private String name;
    private String email;
    private String role;

    public User() {}

    public User(int id, String name, String email, String role) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.role = role;
    }

    public boolean isAdmin() {
        return "Admin".equals(this.role);
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }

    @Override
    public String toString() {
        return "User{id=" + id + ", name='" + name + "', email='" + email + "'}";
    }
}
