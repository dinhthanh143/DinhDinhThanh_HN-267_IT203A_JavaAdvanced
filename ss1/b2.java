import java.util.Scanner;

public class b2 {
    static void use(Scanner sc){
        System.out.println("Moi nhap tong so nguoi dung");
        int user = sc.nextInt();
        System.out.println("Moi nhap so luong nhom: ");
        int group = sc.nextInt();
        try {
            int avg = user/group;
            System.out.println("Moi nhom co " + avg +" nguoi");
        }catch (ArithmeticException e){
            System.out.println("Loi");
        }finally {
            System.out.println("Don dep data..");
        }
    }
}
