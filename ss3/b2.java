import java.util.List;

public class b2 {
    public static void run() {
        List<User> users = List.of(
                new User("alice", "alice@gmail.com", "ACTIVE"),
                new User("bob", "bob@yahoo.com", "INACTIVE"),
                new User("charlie", "charlie@gmail.com", "ACTIVE")
        );

        users.stream()
                .filter(u -> u.email().endsWith("@gmail.com"))
                .map(User::username)
                .forEach(System.out::println);
    }

    public static void main(String[] args) {
        run();
    }
}