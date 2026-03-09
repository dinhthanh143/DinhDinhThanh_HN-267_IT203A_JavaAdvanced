public class b2 {
    static void run(){
        PasswordValidator validator = password -> password.length() >= 8;
    }
}

@FunctionalInterface
interface  PasswordValidator{
    boolean isValid(String password);
}
