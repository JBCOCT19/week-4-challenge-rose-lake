public class Student extends Person {
    public Student() {}
    public Student(int id, String name, String email, String password) {
        super(id, name, email, password);
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    @Override
    public String displayShort() {
        return "Student Id: " + id + "\n" +
                "Student Name: " + name + "\n";
    }

}
