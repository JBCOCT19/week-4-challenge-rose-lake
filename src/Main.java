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

    // remember who the currently logged-in Admin is...
    // we're only implementing log-in for Admins right now...
    private static int currentAdminId;

    // other class-level vars
    private static Scanner keyboard = new Scanner(System.in);

    public static void main(String[] args){
        String userStr;

        // create hard-coded data
        createAdmins();
        createFaculties();
        createStudents();
        createCourses();
        createLinks();

        System.out.println("\nWelcome to Rose's School System!");

        // first log in
        System.out.println("\nWould you like to...\n\t[\"L\"] Log In, or \n\t[\"Q\"] Quit?");
        System.out.print(">> ");
        userStr = keyboard.nextLine();

        loginLoop:
        do {
            // user wants to log in, or log in again...
            if (userStr.equalsIgnoreCase("L")) {

                // if the login fails, exit the program
                if (!validateLogIn()) {
                    break loginLoop;
                }
                // otherwise the login was successful, so we continue...

                // interact with the logged in user...
                playLoop:
                do {
                    showAllOptions();
                    userStr = keyboard.nextLine();
                    if (userStr.equalsIgnoreCase("1")) {
                        addNewStudent();
                    } else if (userStr.equalsIgnoreCase("2")) {
                        addNewFaculty();
                    } else if (userStr.equalsIgnoreCase("3")) {
                        addNewCourse();
                    } else if (userStr.equalsIgnoreCase("4")) {
                        editStudent();
                    } else if (userStr.equalsIgnoreCase("5")) {
                        editFaculty();
                    } else if (userStr.equalsIgnoreCase("6")) {
                        editCourse();
                    } else if (userStr.equalsIgnoreCase("7")) {
                        enrollStudentInCourse();
                    } else if (userStr.equalsIgnoreCase("8")) {
                        assignFacultyToCourse();
                    } else if (userStr.equalsIgnoreCase("9")) {
                        showStudents();
                        showFaculties();
                        showCourses();
                        showLinks();
                    } else if (userStr.equalsIgnoreCase("W")){
                        whoAmI();
                    }
                    else if (userStr.equalsIgnoreCase("L")) {
                        break playLoop;
                    } else if (userStr.equalsIgnoreCase("Q")) {
                        break loginLoop;
                    }
                } while (true);  // end play loop (label: playLoop)

            } else if (userStr.equalsIgnoreCase("Q")) {
                // user wishes to quit the program
                break loginLoop;
            }

            // subsequent logins
            System.out.println("\nWould you like to...\n\t[\"L\"] Log In again, or \n\t[\"Q\"] Quit?");
            System.out.print(">> ");
            userStr = keyboard.nextLine();

        } while (true) ;  // end log in loop (label: loginLoop)

        // exit
        System.out.println("\nThank you for using Rose's School System!");
        System.out.println("So long!...");
        keyboard.close();

    } //end main method

    //************************
    // METHODS for LOGGING IN
    //************************
    private static boolean validateLogIn() {
        String userStr;
        System.out.println("\nYou may log in as an...\n" +
                "\t[\"A\"] Admin\n" +
                "\t[\"F\"] Faculty, or\n" +
                "\t[\"S\"] Student");
        System.out.print(">> ");
        userStr = keyboard.nextLine();
        if (userStr.equalsIgnoreCase("A")) {
            return validateAdmin();
        } else if (userStr.equalsIgnoreCase("F")) {
            return false;       // we are not implementing other logins today
        } else if (userStr.equalsIgnoreCase("S")) {
            return false;       // no other logins today
        } else {
            return false;
        }
    }

    private static boolean validateAdmin(){
        String userName, userPassword;
        int counter = 0;
        System.out.println("\nWelcome, Admin!");
        while(true){
            System.out.println("Please enter your user name:");
            System.out.print(">> ");
            userName = keyboard.nextLine();
            System.out.println("Please enter your password:");
            System.out.print(">> ");
            userPassword = keyboard.nextLine();

            // check for a matching Admin
            for (Admin a : admins) {
                if (a.getName().equals(userName)) {
                    if (a.getPassword().equals(userPassword)) {
                        currentAdminId = a.getId();
                        return true;
                    }
                }
            }
            counter++;
            if (counter < 3) {
                System.out.println("\nInvalid login. Please try again. ");
            }
            else {
                System.out.println("\nThree invalid logins. Sorry! Try again another day.");
                return false;
            }

        } // end while

    }

    private static void whoAmI(){
        // for this implementation you can only be logged in as an Admin
        System.out.println("You are currently logged in as the following Admin...");
        System.out.println(admins.get(currentAdminId-1).display());
    }

    //***********************************
    // METHODS for OPTIONS 1 through 9
    //***********************************
    private static void showAllOptions(){
        System.out.println("\nPlease select an option:\n");
        System.out.println("\t1 - Add a new Student");
        System.out.println("\t2 - Add new Faculty member");
        System.out.println("\t3 - Add a new Course");
        System.out.println("\t4 - Edit a Student");
        System.out.println("\t5 - Edit a Faculty member");
        System.out.println("\t6 - Edit a Course");
        System.out.println("\t7 - Enroll a Student in a Course");
        System.out.println("\t8 - Assign Faculty to teach a Course");
        System.out.println("\t9 - View all information");
        System.out.println("\n\tAdditional Options:");
        System.out.println("\t[\"W\"] Who Am I?\n\t[\"L\"] Logout, or \n\t[\"Q\"] Quit");
        System.out.print(">> ");
    }

    // OPTION 1
    private static void addNewStudent(){
        System.out.println("\nEnter New Student Information... ");
        System.out.print("Name: ");
        String name = keyboard.nextLine();
        System.out.print("Email: ");
        String email = keyboard.nextLine();
        System.out.print("Password: ");
        String password = keyboard.nextLine();
        int id = students.size()+1;
        students.add(new Student(id, name, email, password));
        System.out.println("\nSuccessfully added a new Student:");
        System.out.print(students.get(id-1).display());
        //showStudents();
    }

    // OPTION 2
    private static void addNewFaculty(){
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
        //showFaculties();
    }

    // OPTION 3
    private static void addNewCourse(){
        System.out.println("\nNew Course Information...");
        System.out.print("Name: ");
        String name = keyboard.nextLine();
        System.out.print("Description: ");
        String description = keyboard.nextLine();
        int id = courses.size()+1;
        courses.add(new Course(id, name, description));
        System.out.println("\nSuccessfully added a new Course ::");
        System.out.println(courses.get(id-1).displayShort());
        //showCourses();
    }

    // OPTION 4
    private static void editStudent(){
        showStudents();
        System.out.println("Please enter the Id of the Student you wish to edit...");
        System.out.print(">> ");
        int studentId = keyboard.nextInt();
        keyboard.nextLine();
        Student editMe = new Student();
        for (Student s : students) {
            if (s.getId() == studentId) {
                editMe = s;
                break;
            }
        }
        System.out.println("Editing Student " + editMe.getId() + "...");
        System.out.println(editMe.display());
        System.out.println("You may edit the... \n" +
                "\tN - Name\n" +
                "\tE - Email, or\n" +
                "\tP - Password");
        System.out.print(">> ");
        String userStr = keyboard.nextLine();
        if(userStr.equalsIgnoreCase("n")){
            System.out.println("Enter a new Name...");
            System.out.print(">> ");
            String name = keyboard.nextLine();
            editMe.setName(name);
        } else if(userStr.equalsIgnoreCase("e")){
            System.out.println("Enter a new Email...");
            System.out.print(">> ");
            String email = keyboard.nextLine();
            editMe.setEmail(email);
        } else if(userStr.equalsIgnoreCase("p")){
            System.out.println("Enter a new Password...");
            System.out.print(">> ");
            String password = keyboard.nextLine();
            editMe.setPassword(password);
        }
        System.out.println("\nSuccessfully edited Student " + editMe.getId() + " ::");
        System.out.println(editMe.display());
        //showStudents();
    }

    // OPTION 5
    private static void editFaculty(){
        showFaculties();
        System.out.println("Please enter the Id of the Faculty member you wish to edit...");
        System.out.print(">> ");
        int facultyId = keyboard.nextInt();
        keyboard.nextLine();
        Faculty editMe = new Faculty();
        for (Faculty f : faculties) {
            if (f.getId() == facultyId) {
                editMe = f;
                break;
            }
        }
        System.out.println("Editing Faculty " + editMe.getId() + "...");
        System.out.println(editMe.display());
        System.out.println("You may edit the... \n" +
                "\tN - Name\n" +
                "\tE - Email, or\n" +
                "\tP - Password");
        System.out.print(">> ");
        String userStr = keyboard.nextLine();
        if(userStr.equalsIgnoreCase("n")){
            System.out.println("Enter a new Name...");
            System.out.print(">> ");
            String name = keyboard.nextLine();
            editMe.setName(name);
        } else if(userStr.equalsIgnoreCase("e")){
            System.out.println("Enter a new Email...");
            System.out.print(">> ");
            String email = keyboard.nextLine();
            editMe.setEmail(email);
        } else if(userStr.equalsIgnoreCase("p")){
            System.out.println("Enter a new Password...");
            System.out.print(">> ");
            String password = keyboard.nextLine();
            editMe.setPassword(password);
        }
        System.out.println("\nSuccessfully edited Faculty " + editMe.getId() + " ::");
        System.out.println(editMe.display());
        //showFaculties();
    }

    // OPTION 6
    private static void editCourse(){
        showCourses();
        System.out.println("Please enter the Id of the Course you wish to edit...");
        System.out.print(">> ");
        int courseId = keyboard.nextInt();
        keyboard.nextLine();
        Course editMe = new Course();
        for (Course c : courses) {
            if (c.getId() == courseId) {
                editMe = c;
                break;
            }
        }
        System.out.println("Editing Course " + editMe.getId() + "...");
        System.out.println(editMe.display());
        System.out.println("You may edit the... \n" +
                "\tN - Name, or\n" +
                "\tD - Description");
        System.out.print(">> ");
        String userStr = keyboard.nextLine();
        if(userStr.equalsIgnoreCase("n")){
            System.out.println("Enter a new Name...");
            System.out.print(">> ");
            String name = keyboard.nextLine();
            editMe.setName(name);
        } else if(userStr.equalsIgnoreCase("d")){
            System.out.println("Enter a new Description...");
            System.out.print(">> ");
            String description = keyboard.nextLine();
            editMe.setDescription(description);
        }
        System.out.println("\nSuccessfully edited Course " + editMe.getId() + " ::");
        System.out.println(editMe.display());
        //showCourses();
    }

    // OPTION 7
    private static void enrollStudentInCourse(){

        showStudents();
        System.out.println("Enter the Id of the Student you wish to Enroll in a Course...");
        System.out.print(">> ");
        int studentId = keyboard.nextInt();
        keyboard.nextLine();

        Student enrollStudent = new Student();
        for (Student s : students){
            if (s.getId() == studentId){
                enrollStudent = s;
                break;
            }
        }

        showCourses();
        System.out.println("Enter the Id of the Course you wish to Enroll " + enrollStudent.getName() + " in..." );
        System.out.print(">> ");
        int courseId = keyboard.nextInt();
        keyboard.nextLine();

        Course enrollCourse = new Course();
        for (Course c : courses) {
            if (c.getId() == courseId) {
                enrollCourse = c;
                break;
            }
        }

        System.out.println("Enter the Enrollment Date, as a String...");
        System.out.print(">> ");
        String enrollDate = keyboard.nextLine();

        int linkId = links.size()+1;

        links.add(new Link(linkId, courseId, studentId, enrollDate));

        System.out.println("Successfully enrolled " + enrollStudent.getName() + " in " + enrollCourse.getName() + "...");
        showOneLink(linkId);
        //showLinks();
    }

    // OPTION 8
    private static void assignFacultyToCourse(){
        showFaculties();
        System.out.println("Please enter the Id of the Faculty member you wish to assign to Teach a Course...");
        System.out.print(">> ");
        int facultyId = keyboard.nextInt();
        keyboard.nextLine();
        Faculty assignFaculty = new Faculty();
        for (Faculty f : faculties) {
            if (f.getId() == facultyId) {
                assignFaculty = f;
                break;
            }
        }
        showCourses();
        System.out.println("Enter the Id of the Course you wish to assign " + assignFaculty.getName() + " to..." );
        System.out.print(">> ");
        int courseId = keyboard.nextInt();
        keyboard.nextLine();
        Course assignCourse = new Course();
        for (Course c : courses) {
            if (c.getId() == courseId) {
                assignCourse = c;
                break;
            }
        }
        assignCourse.addOneTeacher(assignFaculty);
        System.out.println("Successfully assigned " + assignFaculty.getName() + " to Teach " + assignCourse.getName() + ".");
        System.out.println(assignCourse.display());
        //showCourses();
    }

    //********************************************
    // METHODS for viewing and working with links
    //********************************************
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

    private static void showOneLink(int linkId) {
        Link link = links.get(linkId-1);
        System.out.println("\tEnrollment Id: " + link.getId() + "\n" +
                "\tCourse: " + lookUpCourseName(link.getCourseId()) + "\n" +
                "\tStudent: " + lookUpStudentName(link.getStudentId()) + "\n" +
                "\tDate: " + link.getDateEnrolled());
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
        System.out.println("\t**********");
        System.out.println("\t* Admins *");
        System.out.println("\t**********");
        for(Admin a : admins) {
            System.out.println(a.displayAdmin());
        }
    }
    private static void showFaculties(){
        System.out.println();
        System.out.println("\t***********");
        System.out.println("\t* Faculty *");
        System.out.println("\t***********");
        for(Faculty f : faculties){
            System.out.println(f.displayFaculty());
        }
    }
    private static void showStudents(){
        System.out.println();
        System.out.println("\t************");
        System.out.println("\t* Students *");
        System.out.println("\t************");
        for(Student s : students){
            System.out.println(s.displayStudent());
        }
    }
    private static void showCourses(){
        System.out.println();
        System.out.println("\t***********");
        System.out.println("\t* Courses *");
        System.out.println("\t***********");
        for(Course c : courses){
            System.out.println(c.displayCourse());
        }
    }
    private static void showLinks(){
        System.out.println();
        System.out.println("\t*****************");
        System.out.println("\t* Enrollments *");
        System.out.println("\t*****************");
        for (Link link : links) {

            // print each on one line
//            System.out.println("\tEnrollment Id: " + link.getId() +
//                    "   Course: " + lookUpCourseName(link.getCourseId()) +
//                    "   Student: " + lookUpStudentName(link.getStudentId()) +
//                    "   Date: " + link.getDateEnrolled() + "\n");

            // print each with line-break between each field
            System.out.println("\tEnrollment Id: " + link.getId() + "\n" +
                    "\tCourse: " + lookUpCourseName(link.getCourseId()) + "\n" +
                    "\tStudent: " + lookUpStudentName(link.getStudentId()) + "\n" +
                    "\tDate: " + link.getDateEnrolled() + "\n");
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
        admins.add(new Admin(1, "Rose", "rose@me.com", "rose"));
        admins.add(new Admin(2, "Lake", "lake@me.com", "lake"));
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
