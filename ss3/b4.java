import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class b4 {
    public static void run() {
        List<User> users = List.of(
                new User("alice", "alice@gmail.com", "ACTIVE"),
                new User("alice", "alice_new@gmail.com", "ACTIVE"),
                new User("bob", "bob@yahoo.com", "INACTIVE"),
                new User("charlie", "charlie@gmail.com", "ACTIVE")
        );

        List<User> distinctUsers = users.stream()
                .collect(Collectors.toMap(
                        User::username,
                        u -> u,
                        (existing, replacement) -> existing
                ))
                .values()
                .stream()
                .toList();

        distinctUsers.forEach(System.out::println);
    }

    public static void main(String[] args) {
        run();
    }
}