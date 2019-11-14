public class Student extends Person {
    public Student() {super();}
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

    public String displayStudent() {
        return "\tStudent Id: " + id + "\n" +
                "\tStudent Name: " + name + "\n";
    }

}
