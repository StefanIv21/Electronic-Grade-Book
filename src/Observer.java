
import java.util.List;

public interface Observer {
    void update(Notification notification);
}
class  Notification implements  Observer
{
    private Grade grade;
    private String coursname;


    public Notification(String coursname, Grade grade)
    {
        this.coursname=coursname;
        this.grade=grade;

    }

    public Grade getGrade() {
        return grade;
    }

    public String getCoursname() {
        return coursname;
    }

    public void setCoursname(String coursname) {
        this.coursname = coursname;
    }

    public void setGrade(Grade grade) {
        this.grade = grade;
    }

    @Override
    public String toString() {
        return  grade.getStudent() + "\n" +
                "\t-> Partial score: " + grade.getPartialScore() + "\n" +
                "\t-> Exam score: " + grade.getExamScore() + "\n" +
                "\t-> coursname=" + coursname + "\n";
    }

    @Override
    public void update(Notification notification) {
        Catalog catalog =Catalog.getInstance();
        List<Notification> notifications =catalog.getNotifications();
        for(int i = 0;i<notifications.size();i++) {
            Grade grade = notifications.get(i).getGrade();
            if (grade.getStudent().equals(notification.grade.getStudent()) &&
                    notifications.get(i).getCoursname().equals(notification.coursname)) {
                notifications.remove(i);
            }
        }
            catalog.getNotifications().add(notification);
        }

}
