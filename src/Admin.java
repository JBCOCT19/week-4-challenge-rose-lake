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

    @Override
    public String displayShort() {
        return "Admin Id: " + id + "\n" +
                "Admin Name: " + name + "\n";
    }
}
