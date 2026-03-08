import java.util.Scanner;
import java.io.FileNotFoundException;
class InvalidAgeException2 extends Exception {
    public InvalidAgeException2(String message) {
        super(message);
    }
}

class InvalidEmailException extends Exception {
    public InvalidEmailException(String message) {
        super(message);
    }
}

public class TongHop {

    public static void registerUser(String name, String ageInput, String email) throws InvalidAgeException2, InvalidEmailException {
        int age;
        try {
            age = Integer.parseInt(ageInput);
        } catch (NumberFormatException e) {
            throw e;
        }

        if (age < 18) {
            throw new InvalidAgeException2("Lỗi nghiệp vụ: Tuổi không đủ để đăng ký hệ thống.");
        }

        if (!email.contains("@")) {
            throw new InvalidEmailException("Lỗi nghiệp vụ: Email không hợp lệ.");
        }

        System.out.println("Đăng ký thành công cho người dùng: " + name);
    }

    public static void saveUserToFile(String userData) throws FileNotFoundException {
        throw new FileNotFoundException("Không tìm thấy file lưu trữ.");
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        try {
            System.out.print("Nhập tên: ");
            String name = sc.nextLine();

            System.out.print("Nhập tuổi: ");
            String ageInput = sc.nextLine();

            System.out.print("Nhập email: ");
            String email = sc.nextLine();

            registerUser(name, ageInput, email);

            String userData = name + "," + ageInput + "," + email;

            saveUserToFile(userData);

        } catch (InvalidAgeException2 e) {
            System.out.println(e.getMessage());
        } catch (InvalidEmailException e) {
            System.out.println(e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("Tuổi phải là một con số!");
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        } finally {
            System.out.println("Hoàn tất luồng xử lý đăng ký.");
            sc.close();
        }
    }
}