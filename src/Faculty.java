public class Faculty extends Person {
    public Faculty() {super();}
    public Faculty(int id, String name, String email, String password) {
        super(id, name, email, password);
    }

    @Override
    public String toString() {
        return "Faculty{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    public String displayFaculty() {
        return "\tFaculty Id: " + id + "\n" +
                "\tFaculty Name: " + name + "\n";
    }
}
