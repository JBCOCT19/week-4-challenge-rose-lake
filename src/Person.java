abstract class Person {

    protected int id;
    protected String name;
    protected String email;
    protected String password;

    protected Person(){}
    protected Person(int id, String name, String email, String password){
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
    }

    // this will display the current User in a SHORTENED form
    // such as after a successful Login or for displaying all [Faculty | Students]
    // it will be overridden so that it can display specifically to the Person-Type
    abstract String displayShort();

    public String display(){
        return "\tId: " + id + "\n" +
                "\tName: " + name + "\n" +
                "\tEmail: " + email + "\n" +
                "\tPassword: " + password + "\n";
    }

    public void setId(int id){
        this.id = id;
    }
    public int getId(){
        return id;
    }
    public void setName(String name){
        this.name = name;
    }
    public String getName(){
        return name;
    }
    public void setEmail(String email){
        this.email = email;
    }
    public String getEmail(){
        return email;
    }
    public void setPassword(String password){
        this.password = password;
    }
    public String getPassword(){
        return password;
    }

}
