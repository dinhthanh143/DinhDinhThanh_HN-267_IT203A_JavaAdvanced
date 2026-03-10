import java.util.List;
import java.util.Optional;

class UserRepository {
    private final List<User> users = List.of(
            new User("alice", "alice@gmail.com", "ACTIVE"),
            new User("bob", "bob@yahoo.com", "INACTIVE")
    );

    public Optional<User> findUserByUsername(String username) {
        return users.stream()
                .filter(u -> u.username().equalsIgnoreCase(username))
                .findFirst();
    }
}

public class b3 {
    public static void run() {
        UserRepository repo = new UserRepository();
        String result = repo.findUserByUsername("alice")
                .map(u -> "Welcome " + u.username())
                .orElse("Guest login");
        System.out.println(result);
    }

    public static void main(String[] args) {
        run();
    }
}