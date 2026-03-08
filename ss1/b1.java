import java.util.Scanner;

public class b1 {
    static void use(Scanner sc){
        System.out.println("Moi nhap nam sinh: ");
        try {
            int year = Integer.parseInt(sc.nextLine());
            System.out.println("Nam sinh: " + year);
        }catch (NumberFormatException e){
            System.out.println("Loi: Ban phai nhap so nguyen, khong nhap chu!");
        }finally {
            sc.close();
            System.out.println("Thực hiện dọn dẹp tài nguyên trong finally...");
        }

    }
}
