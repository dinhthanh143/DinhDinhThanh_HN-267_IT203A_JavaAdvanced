import java.io.IOException;

public class b4 {
    static void use() {
        try {
            processUserData();
        } catch (IOException e) {
            System.out.println("Error in main: " + e.getMessage());
        }
    }
    
    static void processUserData() throws IOException {
        saveToFile();
    }
    
    static void saveToFile() throws IOException {
        throw new IOException("Khong the ghi file!");
    }
}
