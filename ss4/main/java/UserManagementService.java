import java.util.ArrayList;
import java.util.List;

public class UserManagementService {
    private List<UserManagement> users;
    
    public UserManagementService() {
        this.users = new ArrayList<>();
    }
    
    public void addUser(UserManagement user) {
        if (user == null || user.getUsername() == null || user.getUsername().trim().isEmpty()) {
            throw new IllegalArgumentException("Username cannot be null or empty");
        }
        users.add(user);
    }
    
    public UserManagement findUserById(int id) {
        for (UserManagement user : users) {
            if (user.getId() == id) {
                return user;
            }
        }
        return null;
    }
    
    public boolean isValidEmail(String email) {
        return email != null && !email.trim().isEmpty() && email.contains("@");
    }
    
    public List<UserManagement> getUsers() {
        return new ArrayList<>(users);
    }
    
    public void clearUsers() {
        users.clear();
    }
}
