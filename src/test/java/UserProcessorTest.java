import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class UserProcessorTest {
    
    private UserProcessor userProcessor;
    
    @BeforeEach
    void setUp() {
        userProcessor = new UserProcessor();
    }
    
    @Test
    void testProcessEmail_ValidEmailWithAtAndDomain() {
        String email = "user@gmail.com";
        String result = userProcessor.processEmail(email);
        assertEquals("user@gmail.com", result, "Email hop le voi @ va ten mien");
    }
    
    @Test
    void testProcessEmail_InvalidEmailMissingAtSymbol() {
        String email = "usergmail.com";
        assertThrows(IllegalArgumentException.class, () -> {
            userProcessor.processEmail(email);
        }, "Email thieu ky tu @ phai nem ra exception");
    }
    
    @Test
    void testProcessEmail_InvalidEmailHasAtButNoDomain() {
        String email = "user@";
        assertThrows(IllegalArgumentException.class, () -> {
            userProcessor.processEmail(email);
        }, "Email co @ nhung khong co ten mien phai nem ra exception");
    }
    
    @Test
    void testProcessEmail_NormalizeEmailToLowercase() {
        String email = "Example@Gmail.com";
        String result = userProcessor.processEmail(email);
        assertEquals("example@gmail.com", result, "Email phai duoc chuan hoa ve lowercase");
    }
    
    @Test
    void testProcessEmail_ComprehensiveEmailValidation() {
        assertAll("Comprehensive email processing tests",
            () -> {
                String result = userProcessor.processEmail("user@gmail.com");
                assertEquals("user@gmail.com", result, "Valid email should be processed correctly");
            },
            () -> {
                assertThrows(IllegalArgumentException.class, () -> {
                    userProcessor.processEmail("usergmail.com");
                }, "Email without @ should throw exception");
            },
            () -> {
                assertThrows(IllegalArgumentException.class, () -> {
                    userProcessor.processEmail("user@");
                }, "Email with @ but no domain should throw exception");
            },
            () -> {
                String result = userProcessor.processEmail("Example@Gmail.com");
                assertEquals("example@gmail.com", result, "Email should be normalized to lowercase");
            }
        );
    }
}
