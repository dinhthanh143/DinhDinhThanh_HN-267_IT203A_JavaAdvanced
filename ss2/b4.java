import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;
import java.util.function.Function;
import java.util.function.Consumer;

public class b4 {
    static void run(){
        List<User> users = new ArrayList<>();
        users.add(new User(1, "Alice", "alice@example.com", "Admin"));
        users.add(new User(2, "Bob", "bob@example.com", "Manager"));
        users.add(new User(3, "Charlie", "charlie@example.com", "User"));
        

        Function<User, String> getNameFunction = User::getName;
        System.out.println("Using Method Reference for getUsername:");
        users.forEach(user -> System.out.println("Username: " + getNameFunction.apply(user)));
        

        Consumer<String> printConsumer = System.out::println;
        System.out.println("\nUsing Method Reference for println:");
        users.forEach(user -> printConsumer.accept("User: " + user.getName()));
        

        Supplier<User> userSupplier = User::new;
        System.out.println("\nUsing Method Reference for constructor:");
        User newUser = userSupplier.get();
        newUser.setId(4);
        newUser.setName("New User");
        newUser.setEmail("newuser@example.com");
        System.out.println("Created new user: " + newUser.toString());
        
        System.out.println("\nDirect usage examples:");
        
        System.out.println("Lambda: users.forEach(user -> System.out.println(user.getName()));");
        users.forEach(user -> System.out.println(user.getName()));
        
        System.out.println("Method Reference: users.forEach(System.out::println);");
        users.forEach(System.out::println);
        
        System.out.println("\nUsing User::getName with map:");
        users.stream()
             .map(User::getName)
             .forEach(System.out::println);
    }
    
    public static void main(String[] args) {
        run();
    }
}


