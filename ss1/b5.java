public class b5 {
    static void use(){

        User2 u1 = new User2(10);
        try {
            u1.setAge(-1);
        } catch (InvalidAgeException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}

class InvalidAgeException extends RuntimeException{

    public InvalidAgeException(String msg){
        super(msg);
    }

}

class User2{
    private int age;

    public User2(int age) {
        this.age = age;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        if(age < 0 ){
            throw new InvalidAgeException("Tuoi khong the am!");
        }else{
            this.age = age;
        }

    }
}
