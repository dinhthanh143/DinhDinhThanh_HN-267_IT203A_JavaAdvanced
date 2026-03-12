public class UserProcessor {
    
    public String processEmail(String email) {
        if (email == null) {
            throw new IllegalArgumentException("Email khong duoc de trong");
        }
        
        int atIndex = email.indexOf('@');
        if (atIndex == -1) {
            throw new IllegalArgumentException("Email phai chua ky tu @");
        }
        
        String domain = email.substring(atIndex + 1);
        if (domain.isEmpty()) {
            throw new IllegalArgumentException("Email phai co ten mien sau @");
        }
        
        String localPart = email.substring(0, atIndex);
        if (localPart.isEmpty()) {
            throw new IllegalArgumentException("Email phai co phan local truoc @");
        }
        
        return email.toLowerCase();
    }
}
