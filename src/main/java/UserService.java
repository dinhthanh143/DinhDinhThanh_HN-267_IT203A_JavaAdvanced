public class UserService {

    public boolean checkRegistrationAge(int age) {
        if (age < 0) {
            throw new IllegalArgumentException("Tuoi khong duoc la so am");
        }
        
        return age >= 18;
    }
}
