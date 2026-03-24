package ss11.b5;

public class DoctorModel {
    private int id;
    private String name;
    private String spec;

    public DoctorModel(int id, String name, String spec) {
        this.id = id;
        this.name = name;
        this.spec = spec;
    }
    public String toString() {
        return String.format("ID: %-5d | Ten: %-20s | Khoa: %-15s", id, name, spec);
    }
}