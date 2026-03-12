import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class PermissionServiceTest {
    
    private PermissionService permissionService;
    private User adminUser;
    private User moderatorUser;
    private User regularUser;
    
    @BeforeEach
    void setUp() {
        permissionService = new PermissionService();
        
        adminUser = new User("admin", Role.ADMIN);
        moderatorUser = new User("moderator", Role.MODERATOR);
        regularUser = new User("user", Role.USER);
    }
    
    @AfterEach
    void tearDown() {
        permissionService = null;
        adminUser = null;
        moderatorUser = null;
        regularUser = null;
    }
    
    @Test
    void testAdminPermissions_TopDownApproach() {
        assertAll("ADMIN permissions - highest role testing first",
            () -> {
                boolean result = permissionService.canPerformAction(adminUser, Action.DELETE_USER);
                assertTrue(result, "ADMIN should be able to delete users");
            },
            () -> {
                boolean result = permissionService.canPerformAction(adminUser, Action.LOCK_USER);
                assertTrue(result, "ADMIN should be able to lock users");
            },
            () -> {
                boolean result = permissionService.canPerformAction(adminUser, Action.VIEW_PROFILE);
                assertTrue(result, "ADMIN should be able to view profiles");
            }
        );
    }
    
    @Test
    void testModeratorPermissions_SecondLevelTesting() {
        assertAll("MODERATOR permissions - second level role testing",
            () -> {
                boolean result = permissionService.canPerformAction(moderatorUser, Action.DELETE_USER);
                assertFalse(result, "MODERATOR should NOT be able to delete users");
            },
            () -> {
                boolean result = permissionService.canPerformAction(moderatorUser, Action.LOCK_USER);
                assertTrue(result, "MODERATOR should be able to lock users");
            },
            () -> {
                boolean result = permissionService.canPerformAction(moderatorUser, Action.VIEW_PROFILE);
                assertTrue(result, "MODERATOR should be able to view profiles");
            }
        );
    }
    
    @Test
    void testUserPermissions_LowestLevelTesting() {
        assertAll("USER permissions - lowest role testing",
            () -> {
                boolean result = permissionService.canPerformAction(regularUser, Action.DELETE_USER);
                assertFalse(result, "USER should NOT be able to delete users");
            },
            () -> {
                boolean result = permissionService.canPerformAction(regularUser, Action.LOCK_USER);
                assertFalse(result, "USER should NOT be able to lock users");
            },
            () -> {
                boolean result = permissionService.canPerformAction(regularUser, Action.VIEW_PROFILE);
                assertTrue(result, "USER should be able to view personal profile");
            }
        );
    }
    
    @Test
    void testPermissionMatrix_CompleteVerification() {
        assertAll("Complete Permission Matrix Verification",
            () -> assertTrue(permissionService.canPerformAction(adminUser, Action.DELETE_USER), 
                          "ADMIN - DELETE_USER: should be true"),
            () -> assertTrue(permissionService.canPerformAction(adminUser, Action.LOCK_USER), 
                          "ADMIN - LOCK_USER: should be true"),
            () -> assertTrue(permissionService.canPerformAction(adminUser, Action.VIEW_PROFILE), 
                          "ADMIN - VIEW_PROFILE: should be true"),
            
            () -> assertFalse(permissionService.canPerformAction(moderatorUser, Action.DELETE_USER), 
                           "MODERATOR - DELETE_USER: should be false"),
            () -> assertTrue(permissionService.canPerformAction(moderatorUser, Action.LOCK_USER), 
                          "MODERATOR - LOCK_USER: should be true"),
            () -> assertTrue(permissionService.canPerformAction(moderatorUser, Action.VIEW_PROFILE), 
                          "MODERATOR - VIEW_PROFILE: should be true"),
            
            () -> assertFalse(permissionService.canPerformAction(regularUser, Action.DELETE_USER), 
                           "USER - DELETE_USER: should be false"),
            () -> assertFalse(permissionService.canPerformAction(regularUser, Action.LOCK_USER), 
                           "USER - LOCK_USER: should be false"),
            () -> assertTrue(permissionService.canPerformAction(regularUser, Action.VIEW_PROFILE), 
                          "USER - VIEW_PROFILE: should be true")
        );
    }
    
    @Test
    void testEdgeCases_NullInputs() {
        assertAll("Edge cases testing",
            () -> assertFalse(permissionService.canPerformAction(null, Action.DELETE_USER), 
                           "Null user should return false"),
            () -> assertFalse(permissionService.canPerformAction(adminUser, null), 
                           "Null action should return false"),
            () -> assertFalse(permissionService.canPerformAction(null, null), 
                           "Both null should return false")
        );
    }
}
