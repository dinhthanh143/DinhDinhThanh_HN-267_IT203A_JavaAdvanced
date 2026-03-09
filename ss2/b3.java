public class b3 {
    static class User implements Authenticatable {
        private String password;
        User(String password) { this.password = password; }

        @Override
        public String getPassword() { return password; }
    }

    static void run() {
        User user = new User("123456");

        System.out.println("Is Authenticated: " + user.isAuthenticated());

        String hashed = Authenticatable.encrypt("123456");
        System.out.println("Hashed Password: " + hashed);
    }

    public static void main(String[] args) {
        run();
    }
}

interface Authenticatable {
    String getPassword();

    default boolean isAuthenticated() {
        String pwd = getPassword();
        return pwd != null && !pwd.isEmpty();
    }

    static String encrypt(String rawPassword) {
        return "ENCRYPTED_" + rawPassword + "_2026";
    }
}