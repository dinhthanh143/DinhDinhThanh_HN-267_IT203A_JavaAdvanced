import java.util.Comparator;
import java.util.List;

public class b5 {
    public static void run() {
        List<User> users = List.of(
                new User("alexander", "alex@mail.com", "ACTIVE"),
                new User("charlotte", "char@mail.com", "ACTIVE"),
                new User("bob", "bob@mail.com", "INACTIVE"),
                new User("benjamin", "ben@mail.com", "ACTIVE"),
                new User("an", "an@mail.com", "ACTIVE")
        );

        users.stream()
                .sorted(Comparator.comparingInt((User u) -> u.username().length()).reversed())
                .limit(3)
                .forEach(System.out::println);
    }

    public static void main(String[] args) {
        run();
    }
}