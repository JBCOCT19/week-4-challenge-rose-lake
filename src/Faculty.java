public class Faculty extends Person {
    public Faculty() {}
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

    @Override
    public String displayShort() {
        return "Faculty Id: " + id + "\n" +
                "Faculty Name: " + name + "\n";
    }
}
