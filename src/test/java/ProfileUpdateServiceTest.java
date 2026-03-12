import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import java.util.List;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.*;

class ProfileUpdateServiceTest {
    
    private ProfileUpdateService profileUpdateService;
    private User existingUser;
    private User otherUser;
    private List<User> allUsers;
    
    @BeforeEach
    void setUp() {
        profileUpdateService = new ProfileUpdateService();
        
        existingUser = new User("john_doe", Role.USER, "john@example.com", 
                               LocalDate.of(1990, 5, 15), "John", "Doe");
        
        otherUser = new User("jane_smith", Role.USER, "jane@example.com", 
                             LocalDate.of(1985, 8, 22), "Jane", "Smith");
        
        allUsers = new ArrayList<>();
        allUsers.add(existingUser);
        allUsers.add(otherUser);
    }
    
    @Test
    void testUpdateProfile_ValidEmailAndValidBirthDate_ShouldUpdateSuccessfully() {
        UserProfile newProfile = new UserProfile("newemail@example.com", 
                                                LocalDate.of(1990, 5, 15), 
                                                "John", "UpdatedDoe");
        
        User result = profileUpdateService.updateProfile(existingUser, newProfile, allUsers);
        
        assertNotNull(result, "Profile should be updated successfully");
        assertEquals("newemail@example.com", result.getEmail());
        assertEquals("UpdatedDoe", result.getLastName());
    }
    
    @Test
    void testUpdateProfile_FutureBirthDate_ShouldRejectUpdate() {
        UserProfile newProfile = new UserProfile("john@example.com", 
                                                LocalDate.now().plusDays(1), 
                                                "John", "Doe");
        
        User result = profileUpdateService.updateProfile(existingUser, newProfile, allUsers);
        
        assertNull(result, "Update should be rejected for future birth date");
    }
    
    @Test
    void testUpdateProfile_DuplicateEmailWithOtherUser_ShouldRejectUpdate() {
        UserProfile newProfile = new UserProfile("jane@example.com", 
                                                LocalDate.of(1990, 5, 15), 
                                                "John", "Doe");
        
        User result = profileUpdateService.updateProfile(existingUser, newProfile, allUsers);
        
        assertNull(result, "Update should be rejected for duplicate email");
    }
    
    @Test
    void testUpdateProfile_SameEmailAsCurrent_ShouldAllowOtherUpdates() {
        UserProfile newProfile = new UserProfile("john@example.com", 
                                                LocalDate.of(1990, 5, 15), 
                                                "Johnny", "Doe");
        
        User result = profileUpdateService.updateProfile(existingUser, newProfile, allUsers);
        
        assertNotNull(result, "Update should succeed when email is unchanged");
        assertEquals("john@example.com", result.getEmail());
        assertEquals("Johnny", result.getFirstName());
    }
    
    @Test
    void testUpdateProfile_ValidEmailWithEmptyUserList_ShouldUpdateSuccessfully() {
        UserProfile newProfile = new UserProfile("newemail@example.com", 
                                                LocalDate.of(1990, 5, 15), 
                                                "John", "Doe");
        
        User result = profileUpdateService.updateProfile(existingUser, newProfile, new ArrayList<>());
        
        assertNotNull(result, "Update should succeed when no other users exist");
        assertEquals("newemail@example.com", result.getEmail());
    }
    
    @Test
    void testUpdateProfile_DuplicateEmailAndFutureBirthDate_ShouldRejectUpdate() {
        UserProfile newProfile = new UserProfile("jane@example.com", 
                                                LocalDate.now().plusDays(1), 
                                                "John", "Doe");
        
        User result = profileUpdateService.updateProfile(existingUser, newProfile, allUsers);
        
        assertNull(result, "Update should be rejected for multiple constraint violations");
    }
    
    @Test
    void testUpdateProfile_NullExistingUser_ShouldReturnNull() {
        UserProfile newProfile = new UserProfile("test@example.com", 
                                                LocalDate.of(1990, 5, 15), 
                                                "Test", "User");
        
        User result = profileUpdateService.updateProfile(null, newProfile, allUsers);
        
        assertNull(result, "Should return null when existing user is null");
    }
    
    @Test
    void testUpdateProfile_NullNewProfile_ShouldReturnNull() {
        User result = profileUpdateService.updateProfile(existingUser, null, allUsers);
        
        assertNull(result, "Should return null when new profile is null");
    }
    
    @Test
    void testUpdateProfile_NullUserList_ShouldUpdateSuccessfully() {
        UserProfile newProfile = new UserProfile("newemail@example.com", 
                                                LocalDate.of(1990, 5, 15), 
                                                "John", "Doe");
        
        User result = profileUpdateService.updateProfile(existingUser, newProfile, null);
        
        assertNotNull(result, "Update should succeed when user list is null");
        assertEquals("newemail@example.com", result.getEmail());
    }
    
    @Test
    void testUpdateProfile_NullEmailInProfile_ShouldNotChangeEmail() {
        UserProfile newProfile = new UserProfile(null, 
                                                LocalDate.of(1990, 5, 15), 
                                                "John", "Doe");
        
        User result = profileUpdateService.updateProfile(existingUser, newProfile, allUsers);
        
        assertNotNull(result, "Update should succeed");
        assertEquals("john@example.com", result.getEmail(), "Email should remain unchanged");
    }
    
    @Test
    void testUpdateProfile_NullBirthDateInProfile_ShouldNotChangeBirthDate() {
        UserProfile newProfile = new UserProfile("newemail@example.com", 
                                                null, 
                                                "John", "Doe");
        
        User result = profileUpdateService.updateProfile(existingUser, newProfile, allUsers);
        
        assertNotNull(result, "Update should succeed");
        assertEquals(LocalDate.of(1990, 5, 15), result.getBirthDate(), "Birth date should remain unchanged");
    }
    
    @Test
    void testUpdateProfile_ComprehensiveBranchCoverage() {
        assertAll("Comprehensive profile update test covering all branches",
            () -> {
                UserProfile validProfile = new UserProfile("unique@example.com", 
                                                          LocalDate.of(1990, 5, 15), 
                                                          "John", "Doe");
                User result = profileUpdateService.updateProfile(existingUser, validProfile, allUsers);
                assertNotNull(result, "Valid update should succeed");
            },
            () -> {
                UserProfile futureDateProfile = new UserProfile("unique@example.com", 
                                                              LocalDate.now().plusDays(1), 
                                                              "John", "Doe");
                User result = profileUpdateService.updateProfile(existingUser, futureDateProfile, allUsers);
                assertNull(result, "Future date should be rejected");
            },
            () -> {
                UserProfile duplicateEmailProfile = new UserProfile("jane@example.com", 
                                                                   LocalDate.of(1990, 5, 15), 
                                                                   "John", "Doe");
                User result = profileUpdateService.updateProfile(existingUser, duplicateEmailProfile, allUsers);
                assertNull(result, "Duplicate email should be rejected");
            },
            () -> {
                UserProfile sameEmailProfile = new UserProfile("john@example.com", 
                                                             LocalDate.of(1990, 5, 15), 
                                                             "Johnny", "Doe");
                User result = profileUpdateService.updateProfile(existingUser, sameEmailProfile, allUsers);
                assertNotNull(result, "Same email should be allowed");
                assertEquals("Johnny", result.getFirstName());
            }
        );
    }
}
