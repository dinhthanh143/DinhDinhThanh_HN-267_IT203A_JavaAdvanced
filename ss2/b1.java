public class b1{

    public static void run() {
        User defaultUser = new User();
        defaultUser.setId(1);
        defaultUser.setName("Default User");
        defaultUser.setEmail("default@example.com");
        defaultUser.setRole("User");
        System.out.println("Thông tin chi tiết của User:");
        System.out.println("ID: " + defaultUser.getId());
        System.out.println("Name: " + defaultUser.getName());
        System.out.println("Email: " + defaultUser.getEmail());
        System.out.println("Full User Info: " + defaultUser.toString());

        User customUser = new User(2, "John Doe", "john.doe@example.com", "Admin");
        System.out.println("\nThông tin User thứ hai:");
        System.out.println("Full User Info: " + customUser.toString());
        System.out.println(customUser.isAdmin());
    }

    public static void main(String[] args) {
        run();
    }
}
class User {
    private int id;
    private String name;
    private String email;
    private String role;
    public User() {
    }

    public User(int id, String name, String email, String role) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.role = role;
    }

    public boolean isAdmin(){
        if (this.role.equals("Admin")) return true;
        return false;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "User{id=" + id + ", name='" + name + "', email='" + email + "'}";
    }
}
