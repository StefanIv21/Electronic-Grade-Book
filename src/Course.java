
import java.util.*;

public abstract class Course {
    private String nume;
    private Teacher titular;
    private TreeSet<Assistant> asistenti;
    private TreeSet<Grade> grades;
    private Map<String, Group> map;
    private int credits;

    private Strategy strategy;
    private Snapshot snapshot;

    public Course(CourseBuilder builder) {
        this.nume = builder.nume;
        this.titular =builder.titular;
        this.asistenti =builder.asistenti;
        this.grades=builder.grades;
        this.map=builder.map;
        this.credits=builder.credits;
        this.strategy=builder.strategy;
        this.snapshot=new Snapshot();
    }

    public String getNume() {
        return nume;
    }

    public Teacher getTitular() {
        return titular;
    }

    public TreeSet<Assistant> getAsistenti() {
        return asistenti;
    }

    public TreeSet<Grade> getGrades() {
        return grades;
    }

    public Map<String, Group> getMap() {
        return map;
    }

    public int getCredits() {
        return credits;
    }

    public Strategy getStrategy() {
        return strategy;
    }

    public Snapshot getSnapshot() {
        return snapshot;
    }

    public void setAsistenti(TreeSet<Assistant> asistenti) {
        this.asistenti = asistenti;
    }

    public void setCredits(int credits) {
        this.credits = credits;
    }

    public void setGrades(TreeSet<Grade> grades) {
        this.grades = grades;
    }

    public void setMap(Map<String, Group> map) {
        this.map = map;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public void setTitular(Teacher titular) {
        this.titular = titular;
    }

    public void setStrategy(Strategy strategy) {
        this.strategy = strategy;
    }

    public void setSnapshot(Snapshot snapshot) {
        this.snapshot = snapshot;
    }

    public void addAssistant(String ID, Assistant assistant) {
        if(map.get(ID)==null)
            return;
        if (map.get(ID).getAsistent() == null) {
            map.get(ID).setAsistent(assistant);
            if (!asistenti.contains(assistant))
                asistenti.add(assistant);

        }
    }

    public void addStudent(String ID, Student student) {
        if(map.get(ID)==null)
            return;
        Group group = map.get(ID);
        if (!group.contains(student))
            group.add(student);
        else
            System.out.println("Studentul exista");
    }

    public void addGroup(Group group) {
        String id = group.getID();
        if(!map.containsKey(id))
            map.put(id, group);
    }

    public void addGroup(String ID, Assistant assistant) {
        Group group = new Group(ID, assistant);
        if(!map.containsKey(ID))
            map.put(group.getID(), group);
    }

    public void addGroup(String ID, Assistant assist, Comparator<Student> comp) {
        Group group = new Group(ID, assist, comp);
        if(!map.containsKey(ID))
            map.put(group.getID(), group);
    }

    public ArrayList<Student> getAllStudents(){
        ArrayList<Student> result = new ArrayList<>();
        for (Map.Entry<String, Group> mp : map.entrySet()) {
            Iterator<Student> it = mp.getValue().iterator();

            while (it.hasNext()) {
                result.add(it.next());
            }
        }
        return result;
    }


    public Grade getGrade(Student student) {
        Iterator <Grade> it =grades.iterator();
        while (it.hasNext()) {
            Grade g =it.next();
            if(g.getStudent().getFirstName().equals(student.getFirstName()) &&
                    g.getStudent().getLastName().equals(student.getLastName()) )
                return g;
        }
        return null;

    }

    public void addGrade(Grade grade) {
        Iterator <Grade> it =grades.iterator();
        while (it.hasNext()) {
            Grade g =it.next();
            if(g.getStudent().getFirstName().equals(grade.getStudent().getFirstName()) &&
                    g.getStudent().getLastName().equals(grade.getStudent().getLastName()) )
                return;
        }
        grades.add(grade);


    }

    public HashMap<Student, Grade> getAllStudentGrades() {
        HashMap<Student, Grade> map = new HashMap<>();
        Iterator <Grade> it =grades.iterator();
        while (it.hasNext()) {
            Grade g =it.next();
            map.put(g.getStudent(),g);
        }
        return map;
    }
    public abstract ArrayList<Student> getGraduatedStudents();


    static abstract class CourseBuilder {
        private String nume;
        private Teacher titular;
        private TreeSet<Assistant> asistenti;
        private TreeSet<Grade> grades;
        private Map<String, Group> map;
        private int credits;
        private Strategy strategy;

        public CourseBuilder(String nume) {
            this.nume = nume;
        }
        public CourseBuilder setCredits(int credits) {
            this.credits = credits;
            return this;
        }

        public CourseBuilder setTitular(Teacher titular) {
            this.titular = titular;
            return this;
        }

        public CourseBuilder setAsistenti(TreeSet<Assistant> asistenti) {
            this.asistenti = asistenti;
            return this;
        }


        public CourseBuilder setGrades(TreeSet<Grade> grades) {
            this.grades = grades;
            return this;
        }

        public CourseBuilder setMap(Map<String, Group> map) {
            this.map = map;
            return this;
        }

        public CourseBuilder setStrategy(Strategy strategy) {

            this.strategy = strategy;
            return this;
        }
        abstract public  Course build();
    }
    public Student getBestStudent()
    {
        Grade score=strategy.metoda_aleasa(grades);
        return score.getStudent();
    }

    private class Snapshot{
        private TreeSet<Grade> backup;


        public Snapshot() {
            backup=new TreeSet<>();
        }

        public String toString() {
            String ans = "";
            Iterator<Grade> it = backup.iterator();

            while (it.hasNext()) {
                ans += it.next() + "\n";
            }
            return ans;
        }
    }

    public void makeBackup() throws CloneNotSupportedException {
        Iterator<Grade> it = grades.iterator();
        Snapshot snapshot = getSnapshot();
        while (it.hasNext()) {
            Grade grade = it.next().clone();
            snapshot.backup.add(grade);
        }
    }
    public void undo() {
        this.grades = getSnapshot().backup;
    }


    }


