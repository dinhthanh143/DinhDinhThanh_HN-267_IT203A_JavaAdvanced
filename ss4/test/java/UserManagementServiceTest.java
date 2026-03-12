import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class UserManagementServiceTest {
    
    private UserManagementService userService;
    
    @BeforeEach
    void setUp() {
        userService = new UserManagementService();
    }
    
    @Test
    void shouldAddUserSuccessfullyWhenUsernameIsValid() {
        UserManagement testUser = new UserManagement(1, "johndoe", "john@example.com");
        int expectedSize = 1;
        
        userService.addUser(testUser);
        int actualSize = userService.getUsers().size();
        
        assertEquals(expectedSize, actualSize, "User should be added successfully");
        
        assertNotNull(userService.findUserById(1), "User should be found by ID");
        assertEquals("johndoe", userService.findUserById(1).getUsername(), "Username should match");
    }
    
    @Test
    void shouldThrowExceptionWhenAddingUserWithNullUsername() {
        UserManagement testUser = new UserManagement(2, null, "test@example.com");
        
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            userService.addUser(testUser);
        }, "Should throw IllegalArgumentException for null username");
        
        assertEquals("Username cannot be null or empty", exception.getMessage(), 
                    "Exception message should be descriptive");
        
        assertEquals(0, userService.getUsers().size(), "User list should remain empty");
    }
    
    @Test
    void shouldThrowExceptionWhenAddingUserWithEmptyUsername() {
        UserManagement testUser = new UserManagement(3, "", "test@example.com");
        
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            userService.addUser(testUser);
        }, "Should throw IllegalArgumentException for empty username");
        
        assertEquals("Username cannot be null or empty", exception.getMessage(), 
                    "Exception message should be descriptive");
        
        assertEquals(0, userService.getUsers().size(), "User list should remain empty");
    }
    
    @Test
    void shouldReturnNullWhenFindingNonExistentUser() {
        int nonExistentId = 999;
        
        UserManagement result = userService.findUserById(nonExistentId);
        
        assertNull(result, "Should return null for non-existent user");
        
        assertEquals(0, userService.getUsers().size(), "User list should remain empty");
    }
    
    @Test
    void shouldReturnTrueWhenEmailIsValid() {
        String validEmail1 = "user@example.com";
        String validEmail2 = "test.email+tag@domain.co.uk";
        String validEmail3 = "a@b.c";
        
        boolean result1 = userService.isValidEmail(validEmail1);
        boolean result2 = userService.isValidEmail(validEmail2);
        boolean result3 = userService.isValidEmail(validEmail3);
        
        assertTrue(result1, "Email with @ should be valid");
        assertTrue(result2, "Complex email with @ should be valid");
        assertTrue(result3, "Simple email with @ should be valid");
        
        assertAll("Multiple valid email tests",
            () -> assertTrue(userService.isValidEmail("simple@domain.com")),
            () -> assertTrue(userService.isValidEmail("user.name@company.org")),
            () -> assertTrue(userService.isValidEmail("123@456.789"))
        );
    }
    
    @Test
    void shouldReturnFalseWhenEmailIsInvalid() {
        String invalidEmail1 = "userexample.com";
        String invalidEmail2 = "";
        String invalidEmail3 = null;
        
        boolean result1 = userService.isValidEmail(invalidEmail1);
        boolean result2 = userService.isValidEmail(invalidEmail2);
        boolean result3 = userService.isValidEmail(invalidEmail3);
        
        assertFalse(result1, "Email without @ should be invalid");
        assertFalse(result2, "Empty email should be invalid");
        assertFalse(result3, "Null email should be invalid");
    }
    
    @Test
    void shouldFindUserByIdWhenUserExists() {
        UserManagement testUser = new UserManagement(4, "alice", "alice@example.com");
        userService.addUser(testUser);
        
        UserManagement result = userService.findUserById(4);
        
        assertNotNull(result, "Should find existing user");
        assertEquals(4, result.getId(), "Found user ID should match");
        assertEquals("alice", result.getUsername(), "Found user username should match");
        assertEquals("alice@example.com", result.getEmail(), "Found user email should match");
        
        assertEquals(1, userService.getUsers().size(), "Should have exactly one user");
    }
    
    @Test
    void shouldHandleMultipleUsersCorrectly() {
        UserManagement user1 = new UserManagement(5, "bob", "bob@example.com");
        UserManagement user2 = new UserManagement(6, "charlie", "charlie@example.com");
        userService.addUser(user1);
        userService.addUser(user2);
        
        UserManagement foundUser1 = userService.findUserById(5);
        UserManagement foundUser2 = userService.findUserById(6);
        UserManagement notFoundUser = userService.findUserById(999);
        
        assertNotNull(foundUser1, "Should find first user");
        assertNotNull(foundUser2, "Should find second user");
        assertNull(notFoundUser, "Should not find non-existent user");
        assertEquals(2, userService.getUsers().size(), "Should have exactly two users");
        
        assertAll("Multiple user verification",
            () -> assertEquals("bob", foundUser1.getUsername()),
            () -> assertEquals("charlie", foundUser2.getUsername()),
            () -> assertEquals("bob@example.com", foundUser1.getEmail()),
            () -> assertEquals("charlie@example.com", foundUser2.getEmail())
        );
    }
}
