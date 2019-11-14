import java.util.ArrayList;

public class Course {
    private int id;
    private String name;
    private String description;
    private ArrayList<Faculty> teachers;

    // default constructor
    public Course(){}

    // fully overloaded constructor :: ArrayList of Faculty passed in
    public Course(int id, String name, String description, ArrayList<Faculty> teachers) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.teachers = teachers;
    }

    // partially overloaded constructor :: pass in ONE teacher ;)
    public Course(int id, String name, String description, Faculty teacher){
        this.id = id;
        this.name = name;
        this.description = description;
        this.teachers = new ArrayList<>();
        this.teachers.add(teacher);
    }

    // partially overloaded constructor :: no teachers
    public Course(int id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.teachers = new ArrayList<>();
    }

    //********************************
    // Custom Methods
    //********************************
    public void addOneTeacher(Faculty teacher){
        this.teachers.add(teacher);
    }

    // Displays the FULL COURSE INFORMATION
    public String display(){
        String str = "";
        str += "Course Id: " + id + "\n" +
                "Course Name: " + name + "\n" +
                "Course Description: " + description + "\n" +
                "Teacher(s): ";
        if (teachers.size() != 0) {
            for (int i = 0; i < teachers.size(); i++) {   // loop through teachers
                str += teachers.get(i).getName();
                if (i < teachers.size() - 1) {  // add a comma if there is another teacher down the list
                    str += ", ";
                }
            }
        } else {
            str += "No Teacher Assigned";
        }
        str += "\n"; // place a new line after the teachers line
        return str;
    }

    @Override
    public String toString() {
        return "Course{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", teachers=" + teachers +
                '}';
    }

    //********************************
    // Default Getters + Setters
    //********************************
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ArrayList<Faculty> getTeachers() {
        return teachers;
    }

    public void setTeachers(ArrayList<Faculty> teachers) {
        this.teachers = teachers;
    }

}
