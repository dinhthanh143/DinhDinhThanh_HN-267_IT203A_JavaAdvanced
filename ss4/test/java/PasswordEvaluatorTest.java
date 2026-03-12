import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class PasswordEvaluatorTest {
    
    private PasswordEvaluator passwordEvaluator;
    
    @BeforeEach
    void setUp() {
        passwordEvaluator = new PasswordEvaluator();
    }
    
    @Test
    void testEvaluatePasswordStrength_TC01_StrongPassword() {
        String password = "Abc123!@";
        String result = passwordEvaluator.evaluatePasswordStrength(password);
        assertEquals("Mạnh", result, "TC01: Mật khẩu có đủ: ≥8 ký tự, chữ hoa, chữ thường, số và ký tự đặc biệt");
    }
    
    @Test
    void testEvaluatePasswordStrength_TC02_MissingUpperCase() {
        String password = "abc123!@";
        String result = passwordEvaluator.evaluatePasswordStrength(password);
        assertEquals("Trung bình", result, "TC02: Thiếu chữ hoa");
    }
    
    @Test
    void testEvaluatePasswordStrength_TC03_MissingLowerCase() {
        String password = "ABC123!@";
        String result = passwordEvaluator.evaluatePasswordStrength(password);
        assertEquals("Trung bình", result, "TC03: Thiếu chữ thường");
    }
    
    @Test
    void testEvaluatePasswordStrength_TC04_MissingDigit() {
        String password = "Abcdef!@";
        String result = passwordEvaluator.evaluatePasswordStrength(password);
        assertEquals("Trung bình", result, "TC04: Thiếu số");
    }
    
    @Test
    void testEvaluatePasswordStrength_TC05_MissingSpecialChar() {
        String password = "Abc12345";
        String result = passwordEvaluator.evaluatePasswordStrength(password);
        assertEquals("Trung bình", result, "TC05: Thiếu ký tự đặc biệt");
    }
    
    @Test
    void testEvaluatePasswordStrength_TC06_TooShort() {
        String password = "Ab1!";
        String result = passwordEvaluator.evaluatePasswordStrength(password);
        assertEquals("Yếu", result, "TC06: Mật khẩu quá ngắn (<8 ký tự)");
    }
    
    @Test
    void testEvaluatePasswordStrength_TC07_OnlyLowerCase() {
        String password = "password";
        String result = passwordEvaluator.evaluatePasswordStrength(password);
        assertEquals("Yếu", result, "TC07: Chỉ có chữ thường");
    }
    
    @Test
    void testEvaluatePasswordStrength_TC08_OnlyUpperCaseAndDigits() {
        String password = "ABC12345";
        String result = passwordEvaluator.evaluatePasswordStrength(password);
        assertEquals("Yếu", result, "TC08: Chỉ có chữ hoa và số");
    }
    
    @Test
    void testEvaluatePasswordStrength_BehaviorFocusedTest() {
        assertAll("Password strength behavior evaluation - focusing on output only",
            () -> {
                String result = passwordEvaluator.evaluatePasswordStrength("Abc123!@");
                assertEquals("Mạnh", result, "Strong password behavior should return 'Mạnh'");
            },
            () -> {
                String result = passwordEvaluator.evaluatePasswordStrength("abc123!@");
                assertEquals("Trung bình", result, "Password missing uppercase behavior should return 'Trung bình'");
            },
            () -> {
                String result = passwordEvaluator.evaluatePasswordStrength("ABC123!@");
                assertEquals("Trung bình", result, "Password missing lowercase behavior should return 'Trung bình'");
            },
            () -> {
                String result = passwordEvaluator.evaluatePasswordStrength("Abcdef!@");
                assertEquals("Trung bình", result, "Password missing digit behavior should return 'Trung bình'");
            },
            () -> {
                String result = passwordEvaluator.evaluatePasswordStrength("Abc12345");
                assertEquals("Trung bình", result, "Password missing special character behavior should return 'Trung bình'");
            },
            () -> {
                String result = passwordEvaluator.evaluatePasswordStrength("Ab1!");
                assertEquals("Yếu", result, "Too short password behavior should return 'Yếu'");
            },
            () -> {
                String result = passwordEvaluator.evaluatePasswordStrength("password");
                assertEquals("Yếu", result, "Lowercase only password behavior should return 'Yếu'");
            },
            () -> {
                String result = passwordEvaluator.evaluatePasswordStrength("ABC12345");
                assertEquals("Yếu", result, "Uppercase and digits only password behavior should return 'Yếu'");
            }
        );
    }
}
