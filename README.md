# School System
Week 4 Challenge

## This implementation

I believe this implementation meets the "Assignment Requirements" outlined below...

Some specifics of this implementation...
- there are pre-defined lists of Students, Faculty, Courses, and Enrollments (links between Students and Courses)
- Course has a field called teachers which is a list of type Faculty. It has several constructors to handle creating a new course with no teacher, one teacher, or a list of teachers.
- options are slightly re-ordered to keep all ADDING and all EDITING adjacent.
- option 9, `View all information`, displays all Students, Faculty, Courses, *and Enrollments*.

Future things...
- only an Admin can log in, but it's structured in such a way that it could easily (...well, with some work) be scaled to accommodate Faculty or User logins...
- someday this program could be expanded to include "user-permissions" -- different options available depending on user's Type (Admin|Faculty|Student). 

Some things this implementation specifically leaves out...
- no error-checking on user inputs, though we do check for correct login/password combination
- no checking to see if a Student is already enrolled in a Course before re-enrolling them
- no checking to see if a Faculty is already assigned to a Course before assigning them
- no error-checking for null faculty list passed-in to Course constructor
- there is no pretty error message for the user who tries to log in as a faculty or student, alas. the program simply quits. this isn't ideal, but... time was limited.


## Assignment Requirements

You are creating a login application that deals with student enrollment and faculty hiring. 

You are an admin

- You will have an Admin, Faculty, and a Student
- Each "person" must have an ID, name , email, and password.
- The class must have an ID, name, and description
- The admin must be able to login
- The admin must be able to add a student, add a faculty, and add a class to the program.
- The admin must be able to "enroll" a student to a class and assign a Faculty to teach that class.
- When a student is enrolled, you must include the Date (can be Date or String) they enrolled.
- The login info once put in should be able to give you an option to tell the USER who they are.
- Lastly, the admin must be able to edit information about the student or faculty.

The program must include these concepts:
- Composition
- Inheritance
- Polymorphism (such as as the toString method hint hint)
