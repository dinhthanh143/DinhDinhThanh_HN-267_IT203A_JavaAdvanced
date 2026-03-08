import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class b6 {
    static void use(){
        try {
            processUserAge(-5);
        } catch (InvalidAgeException e) {
            logError("Age validation failed", e);
        }
        
        String userName = getUserName();
        if (userName != null) {
            System.out.println("User name: " + userName.toUpperCase());
        } else {
            logError("User name is null", new NullPointerException("User name cannot be null"));
        }
        
        try {
            readFile("config.txt");
        } catch (java.io.FileNotFoundException e) {
            logError("File not found", e);
        } catch (java.io.IOException e) {
            logError("IO error while reading file", e);
        }
    }
    
    static void processUserAge(int age) throws InvalidAgeException {
        if (age < 0) {
            throw new InvalidAgeException("Age cannot be negative: " + age);
        }
        System.out.println("Processing user with age: " + age);
    }
    
    static String getUserName() {
        return Math.random() > 0.5 ? "John Doe" : null;
    }
    
    static void readFile(String filename) throws java.io.IOException {

        if (filename == null || filename.isEmpty()) {
            throw new java.io.FileNotFoundException("File name cannot be null or empty");
        }
        System.out.println("Reading file: " + filename);
    }
    
    static void logError(String message, Exception e) {
        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        System.out.println("[ERROR] " + timestamp + " - " + message + " - " + e.getMessage());
        e.printStackTrace();
    }
}


