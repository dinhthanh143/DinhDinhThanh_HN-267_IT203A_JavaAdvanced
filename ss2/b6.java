class User2 {
    private String username;
    public User2(String username) { this.username = username; }
    public String getUsername() { return username; }
}

@FunctionalInterface
interface UserProcessor {
    String process(User2 u);
}

class UserUtils {
    public static String convertToUpperCase(User2 u) {
        if (u == null || u.getUsername() == null) return "";
        return u.getUsername().toUpperCase();
    }
}

public class b6 {
    static void run() {
        User2 myUser = new User2("nguyen van a");

        UserProcessor processor = UserUtils::convertToUpperCase;

        String result = processor.process(myUser);

        System.out.println("=== Running b6: Custom Functional Interface & Method Reference ===");
        System.out.println("Input: " + myUser.getUsername());
        System.out.println("Result: " + result);
    }

    public static void main(String[] args) {
        run();
    }
}