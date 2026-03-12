import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserValidatorTest {
    @Test
    void testTC01(){
        String user = "user123";
        boolean result = UserValidator.isValidUsername(user);
        assertTrue(result, "Hop le");
    }

    @Test
    void testTC02(){
        String user = "abc";
        boolean result = UserValidator.isValidUsername(user);
        assertFalse(result, "Qua ngan");
    }

    @Test
    void testTC03(){
        String user = "user name";
        boolean result = UserValidator.isValidUsername(user);
        assertFalse(result, "Chua khoang trang");
    }


}

