public class b3 {
    static void use(){

        User u1 = new User(10);
        try {
            u1.setAge(-1);
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}

class User{
    private int age;

    public User(int age) {
        this.age = age;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        if(age < 0 ){
            throw new IllegalArgumentException("Tuoi khong the am!");
        }else{
            this.age = age;
        }

    }
}