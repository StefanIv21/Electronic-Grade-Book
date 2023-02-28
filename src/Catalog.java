
import java.util.*;

public class Catalog implements Subject {
     private static Catalog obj = null;
     private  List <Course> cursuri;
     private  List< Observer> observers;
     private  List <Notification> notifications;

    public List<Notification> getNotifications() {
        return notifications;
    }

    public List<Course> getCursuri() {
        return cursuri;
    }

    public List<Observer> getObservers() {
        return observers;
    }

    private Catalog() {
         cursuri=new ArrayList<>();
         observers=new ArrayList<>();
         notifications=new ArrayList<>();
    }

    public static Catalog getInstance() {

            if (obj == null)
                obj = new Catalog();
            return obj;
        }

    public void addCourse(Course course) {
        if (cursuri.contains(course))
            return;
        cursuri.add(course);

        TreeSet<Grade> grades = course.getGrades();
        Iterator<Grade> it = grades.iterator();
        while (it.hasNext()) {
            Grade grade = it.next();

            Notification notify = new Notification(grade.getCourse(), grade);
            if (!observers.contains(notify))
                addObserver(notify);
        }

    }
    public void removeCourse(Course course){
         cursuri.remove(course);
    }

    @Override
    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers(Grade grade) {
         Notification notify=new Notification(grade.getCourse(),grade);
         for(Observer observer : observers) {
             Notification obs =(Notification) observer;
             if(obs.getGrade().getStudent().equals(grade.getStudent())) {
                 observer.update(notify);

             }
         }

    }
}



