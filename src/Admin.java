public class Admin extends Person {
    public Admin(){ super(); }
    public Admin(int id, String name, String email, String password) {
        super(id, name, email, password);
    }

    @Override
    public String toString() {
        return "Admin{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    public String displayAdmin() {
        return "\tAdmin Id: " + id + "\n" +
                "\tAdmin Name: " + name + "\n";
    }
}
