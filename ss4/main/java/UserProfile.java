import java.time.LocalDate;

public class UserProfile {
    private String email;
    private LocalDate birthDate;
    private String firstName;
    private String lastName;
    
    public UserProfile(String email, LocalDate birthDate, String firstName, String lastName) {
        this.email = email;
        this.birthDate = birthDate;
        this.firstName = firstName;
        this.lastName = lastName;
    }
    
    public String getEmail() {
        return email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    
    public LocalDate getBirthDate() {
        return birthDate;
    }
    
    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }
    
    public String getFirstName() {
        return firstName;
    }
    
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    
    public String getLastName() {
        return lastName;
    }
    
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
