public class PasswordEvaluator {
    
    public String evaluatePasswordStrength(String password) {
        if (password == null || password.length() < 8) {
            return "Yếu";
        }
        
        boolean hasUpperCase = false;
        boolean hasLowerCase = false;
        boolean hasDigit = false;
        boolean hasSpecialChar = false;
        
        for (char c : password.toCharArray()) {
            if (Character.isUpperCase(c)) {
                hasUpperCase = true;
            } else if (Character.isLowerCase(c)) {
                hasLowerCase = true;
            } else if (Character.isDigit(c)) {
                hasDigit = true;
            } else if (!Character.isLetterOrDigit(c)) {
                hasSpecialChar = true;
            }
        }
        
        int criteriaCount = 0;
        if (hasUpperCase) criteriaCount++;
        if (hasLowerCase) criteriaCount++;
        if (hasDigit) criteriaCount++;
        if (hasSpecialChar) criteriaCount++;
        
        if (criteriaCount == 4) {
            return "Mạnh";
        } else if (criteriaCount >= 3) {
            return "Trung bình";
        } else {
            return "Yếu";
        }
    }
}
