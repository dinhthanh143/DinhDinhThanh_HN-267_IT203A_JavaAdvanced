import java.time.LocalDate;
import java.util.List;

public class ProfileUpdateService {
    
    public User updateProfile(User existingUser, UserProfile newProfile, List<User> allUsers) {
        if (existingUser == null || newProfile == null) {
            return null;
        }
        
        LocalDate today = LocalDate.now();
        
        if (newProfile.getBirthDate() != null && newProfile.getBirthDate().isAfter(today)) {
            return null;
        }
        
        if (newProfile.getEmail() != null && !newProfile.getEmail().equals(existingUser.getEmail())) {
            if (allUsers != null) {
                for (User user : allUsers) {
                    if (user != existingUser && newProfile.getEmail().equals(user.getEmail())) {
                        return null;
                    }
                }
            }
        }
        
        if (newProfile.getEmail() != null) {
            existingUser.setEmail(newProfile.getEmail());
        }
        if (newProfile.getBirthDate() != null) {
            existingUser.setBirthDate(newProfile.getBirthDate());
        }
        if (newProfile.getFirstName() != null) {
            existingUser.setFirstName(newProfile.getFirstName());
        }
        if (newProfile.getLastName() != null) {
            existingUser.setLastName(newProfile.getLastName());
        }
        
        return existingUser;
    }
}
