import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserServiceTest {
    
    @Test
    void testCheckRegistrationAge_ValidBoundary() {
        UserService userService = new UserService();
        boolean result = userService.checkRegistrationAge(18);
        assertEquals(true, result, "Nguoi 18 tuoi duoc phep dang ky");
    }
    
    @Test
    void testCheckRegistrationAge_UnderRequiredAge() {
        UserService userService = new UserService();
        boolean result = userService.checkRegistrationAge(17);
        assertEquals(false, result, "Nguoi 17 tuoi khong duoc phep dang ky");
    }
    
    @Test
    void testCheckRegistrationAge_NegativeAge() {
        UserService userService = new UserService();
        assertThrows(IllegalArgumentException.class, () -> {
            userService.checkRegistrationAge(-1);
        }, "Tuoi am phai nem ra IllegalArgumentException");
    }
}
