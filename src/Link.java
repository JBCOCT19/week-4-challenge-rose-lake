public class Link {
    private int id;
    private int courseId;
    private int studentId;
    private String dateEnrolled;

    public Link() {
    }

    public Link(int id, int courseId, int studentId, String dateEnrolled) {
        this.id = id;
        this.courseId = courseId;
        this.studentId = studentId;
        this.dateEnrolled = dateEnrolled;
    }

    @Override
    public String toString() {
        return "Link{" +
                "id=" + id +
                ", courseId=" + courseId +
                ", studentId=" + studentId +
                ", dateEnrolled='" + dateEnrolled + '\'' +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public String getDateEnrolled() {
        return dateEnrolled;
    }

    public void setDateEnrolled(String dateEnrolled) {
        this.dateEnrolled = dateEnrolled;
    }
}
