import java.util.List;

record User(String username, String email, String status) {}

public class b1 {
    public static void run() {
        List<User> users = List.of(
                new User("alice", "alice@gmail.com", "ACTIVE"),
                new User("bob", "bob@inactive.com", "INACTIVE"),
                new User("charlie", "charlie@gmail.com", "ACTIVE")
        );
        users.forEach(System.out::println);
    }

    public static void main(String[] args) {
        run();
    }
}