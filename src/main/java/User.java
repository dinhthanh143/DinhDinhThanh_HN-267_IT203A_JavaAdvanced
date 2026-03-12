import java.time.LocalDate;

public class User {
    private String username;
    private Role role;
    private String email;
    private LocalDate birthDate;
    private String firstName;
    private String lastName;
    
    public User(String username, Role role) {
        this.username = username;
        this.role = role;
    }
    
    public User(String username, Role role, String email, LocalDate birthDate, String firstName, String lastName) {
        this.username = username;
        this.role = role;
        this.email = email;
        this.birthDate = birthDate;
        this.firstName = firstName;
        this.lastName = lastName;
    }
    
    public String getUsername() {
        return username;
    }
    
    public Role getRole() {
        return role;
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
