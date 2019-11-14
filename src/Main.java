/*
 * Weekly Challenge : School System
 * Thursday November 14, 2019
 * Ksenia Lake
 *
 * Implements a school system with Object-Oriented + Linked-Objects approach to tracking
 *   Students, Faculty, Courses and their interconnections
 */
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    // constants for constructing Courses in a slightly more legible way...
    // these are the INDEX associated with the Faculty NAMED in the constant
    // these are set in the createFaculties() hard-coded generator method
    private static final int VICTOR = 0;
    private static final int KENISHA = 1;
    private static final int SUE = 2;

    // locally-held lists of all current Persons, Courses, and Links between Students and Courses
    private static ArrayList<Admin> admins = new ArrayList<>();
    private static ArrayList<Faculty> faculties = new ArrayList<>();
    private static ArrayList<Student> students = new ArrayList<>();
    private static ArrayList<Course> courses = new ArrayList<>();
    private static ArrayList<Link> links = new ArrayList<>();

    // other class-level vars
    private static Scanner keyboard = new Scanner(System.in);

    public static void main(String[] args){
        // create hard-coded data
        createAdmins();
        createFaculties();
        createStudents();
        createCourses();
        createLinks();

        showLinks();
        showLinksByCourse();
        showLinksByStudent();

        // begin user interaction!
        System.out.println("\nWelcome to Rose's School System!");
        showAllOptions();

        String userStr;
        userStr = keyboard.nextLine();
        if (userStr.equalsIgnoreCase("1")){
            System.out.println("\nNew Student Information... ");
            System.out.print("Name: ");
            String name = keyboard.nextLine();
            System.out.print("Email: ");
            String email = keyboard.nextLine();
            System.out.print("Password: ");
            String password = keyboard.nextLine();
            int id = students.size()+1;
            students.add(new Student(id, name, email, password));
            System.out.println("\nSuccessfully added a new Student ::");
            System.out.print(students.get(id-1).display());
            showStudents();
        } else if (userStr.equalsIgnoreCase("2")){
            System.out.println("\nNew Faculty Information...");
            System.out.print("Name: ");
            String name = keyboard.nextLine();
            System.out.print("Email: ");
            String email = keyboard.nextLine();
            System.out.print("Password: ");
            String password = keyboard.nextLine();
            int id = faculties.size()+1;
            faculties.add(new Faculty(id, name, email, password));
            System.out.println("\nSuccessfully added a new Faculty ::");
            System.out.print(faculties.get(id-1).display());
            showFaculties();
        }

    }

    private static void showAllOptions(){
        System.out.println("Please select an option:");
        System.out.println("1 - Add a new Student");
        System.out.println("2 - Add new Faculty member");
        System.out.println("3 - Add a new Course");
        System.out.println("4 - Edit a Student");
        System.out.println("5 - Edit a Faculty member");
        System.out.println("6 - Edit a Course");
        System.out.println("7 - Enroll a Student in a Course");
        System.out.println("8 - Assign Faculty to teach a Course");
        System.out.println("9 - View all information");
        System.out.println("or \"Q\" to Quit");
    }

    //********************************************
    // METHODS for viewing and working with links
    //********************************************
    private static void showLinks() {
        System.out.println();
        System.out.println("******************************");
        System.out.println("* All Course + Student Links *");
        System.out.println("******************************");
        for (Link link : links) {
            System.out.println(lookUpCourseName(link.getCourseId()) + " + "
                    + lookUpStudentName(link.getStudentId()) + " :: "
                    + link.getDateEnrolled());
        }
    }

    private static void showLinksByStudent(){
        System.out.println();
        System.out.println("********************");
        System.out.println("* Links by Student *");
        System.out.println("********************");
        for (Student s : students) {
            System.out.println(s.getName() + ":");
            for (Link link : links) {
                if (link.getStudentId() == s.getId()) { // if we have a link taht matches our current student
                    System.out.println("\t" + lookUpCourseName(link.getCourseId()) + ", " + link.getDateEnrolled());
                }
            }
            System.out.println();   // new line between students
        }
    }

    private static void showLinksByCourse(){
        System.out.println();
        System.out.println("*******************");
        System.out.println("* Links by Course *");
        System.out.println("*******************");
        for (Course c : courses){
            System.out.println(c.getName() + ":");
            for (Link link : links) {
                if (link.getCourseId() == c.getId()) {    // if we have a link that matches our current course
                    System.out.println("\t" + lookUpStudentName(link.getStudentId()) + ", " + link.getDateEnrolled());
                }
            }
            System.out.println();   // new line between courses
        }
    }

    private static String lookUpStudentName(int personId){
        for (Student s : students) {
            if (s.getId() == personId) {
                return s.getName();
            }
        }
        return "Unknown Student";
    }

    private static String lookUpCourseName(int courseId){
        for (Course c : courses) {
            if (c.getId() == courseId) {
                return c.getName();
            }
        }
        return "Unknown Course";
    }


    //**********************************************************
    // DISPLAY METHODS for Admins, Faculties, Students, Courses
    // These show the information in user-friendly fashion
    //**********************************************************
    private static void showAdmins(){
        System.out.println();
        System.out.println("**************");
        System.out.println("* All Admins *");
        System.out.println("**************");
        for(Admin a : admins) {
            System.out.println(a.displayShort());
        }
    }
    private static void showFaculties(){
        System.out.println();
        System.out.println("***************");
        System.out.println("* All Faculty *");
        System.out.println("***************");
        for(Faculty f : faculties){
            System.out.println(f.displayShort());
        }
    }
    private static void showStudents(){
        System.out.println();
        System.out.println("****************");
        System.out.println("* All Students *");
        System.out.println("****************");
        for(Student s : students){
            System.out.println(s.displayShort());
        }
    }
    private static void showCourses(){
        System.out.println();
        System.out.println("***************");
        System.out.println("* All Courses *");
        System.out.println("***************");
        for(Course c : courses){
            System.out.println(c.display());
        }
    }

    //****************************************
    // DEBUG displays of all Lists/DBs
    // This shows ALL the info via toString()
    // It's quick and complete
    //****************************************
    private static void debugAdmins(){
        System.out.println("\nDEBUG INFO admins:");
        for(Admin a : admins){
            System.out.println(a.toString());
        }
    }
    private static void debugFaculties(){
        System.out.println("\nDEBUG INFO faculties:");
        for(Faculty f : faculties){
            System.out.println(f.toString());
        }
    }
    private static void debugStudents(){
        System.out.println("\nDEBUG INFO students:");
        for(Student s : students){
            System.out.println(s.toString());
        }
    }
    private static void debugCourses(){
        System.out.println("\nDEBUG INFO courses:");
        for(Course c : courses){
            System.out.println(c.toString());
        }
    }
    private static void debugLinks(){
        System.out.println("\nDEBUG INFO linkStudentCourseDB:");
        for (Link link : links){
            System.out.println(link.toString());
        }
    }

    //****************************
    //GENERATE DEFAULT Lists/DBs
    //****************************
    private static void createAdmins(){
        admins.add(new Admin(1, "Admin1", "admin1@me.com", "password"));
        admins.add(new Admin(2, "Admin2", "admin2@me.com", "password"));
    }
    private static void createFaculties(){
        faculties.add(new Faculty(1, "Victor", "victor@me.com", "password"));
        faculties.add(new Faculty(2, "Kenisha", "kenisha@me.com", "password"));
        faculties.add(new Faculty(3, "Sue", "sue@me.com", "password"));
    }
    private static void createStudents(){
        students.add(new Student(1, "Mina", "mina@me.com", "password"));
        students.add(new Student(2, "Prisanti", "prisanti@me.com", "password"));
        students.add(new Student(3, "Soheila", "soheila@me.com", "password"));
        students.add(new Student(4, "Efrem", "efrem@me.com", "password"));
        students.add(new Student(5, "Feng", "feng@me.com", "password"));
    }
    private static void createCourses() {
        courses.add(new Course(1, "Java", "mad java skills"));
        courses.add(new Course(2, "Boot Camp", "eight-week intensive", faculties));
        if (faculties.size() != 0) {
            courses.add(new Course(3, "Python", "lotsa python", faculties.get(VICTOR)));
            courses.add(new Course(4, "Data Analysis", "learn to wrangle data", faculties.get(KENISHA)));
            courses.add(new Course(5, "AWS Test Prep", "amazon web services", faculties.get(SUE)));
        }
    }
    private static void createLinks() {
        links.add(new Link(1, 1, 1, "August 2019"));      // java, Mina
        links.add(new Link(2, 1, 2, "September 2019"));   // java, Prisanti
        links.add(new Link(3, 2, 3, "October 2019"));     // boot camp, Soheila
        links.add(new Link(4, 3, 4, "November 2019"));    // python, Efrem
        links.add(new Link(5, 4, 3, "November 2019"));    // data, Soheila
        links.add(new Link(6, 2, 5, "October 2019"));     // boot camp, Feng
        links.add(new Link(7, 4, 1, "November 14, 2019"));// data, Mina
        links.add(new Link(8, 2, 2, "October 2019"));     // boot camp, Prisanti
        links.add(new Link(9, 2, 4, "August 2019"));      // data, Prisanti
        links.add(new Link(10, 5, 1, "December 2019"));   // advanced java, Mina
        links.add(new Link(11, 5, 5, "December 2019"));   // advanced java, Feng
    }
}
