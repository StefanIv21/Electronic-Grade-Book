import java.util.*;

public interface Visitor {
    void visit(Assistant assistant);
    void visit(Teacher teacher);
}
class ScoreVisitor implements Visitor {

    private static ScoreVisitor obj = null;
   private Map<Teacher, ArrayList<Tuple<Student, String, Double>>> examScores;
    private Map<Assistant, ArrayList<Tuple<Student, String, Double>>> partialScores;

    public Map<Assistant, ArrayList<Tuple<Student, String, Double>>> getPartialScores() {
        return partialScores;
    }

    public Map<Teacher, ArrayList<Tuple<Student, String, Double>>> getExamScores() {
        return examScores;
    }

    private ScoreVisitor()
    {
        examScores=new HashMap<>();
        partialScores=new HashMap<>();
    }
    public static ScoreVisitor getInstance() {

        if (obj == null)
            obj = new ScoreVisitor();
        return obj;
    }

    public  void add_grade_teacher(Teacher teacher,Grade g) {
        Catalog catalog = Catalog.getInstance();
        for (int i = 0; i < catalog.getCursuri().size(); i++) {
            Course course = catalog.getCursuri().get(i);
            Teacher t = course.getTitular();
            if (t.getLastName().equals(teacher.getLastName()) &&
                    t.getFirstName().equals(teacher.getFirstName())) {

                ArrayList<Tuple<Student, String, Double>> List;
                if (examScores.get(t) == null) {
                    List = new ArrayList<>();
                    examScores.put(t, List);
                } else {
                    List = examScores.get(t);
                }
                Tuple<Student, String, Double> tuple = new Tuple<>(g.getStudent(), g.getCourse(), g.getExamScore());
                if (!List.contains(tuple))
                    List.add(tuple);
            }

        }
    }
    public  void add_grade_assistant(Assistant assistant,Grade g)
    {
        Catalog catalog = Catalog.getInstance();
        for (int i = 0; i < catalog.getCursuri().size(); i++) {
            Course course = catalog.getCursuri().get(i);
            for (Assistant a : course.getAsistenti()) {
                if (a.getLastName().equals(assistant.getLastName()) &&
                        a.getFirstName().equals(a.getFirstName())) {

                    ArrayList<Tuple<Student, String, Double>> List;
                    if (partialScores.get(a) == null) {
                        List = new ArrayList<>();
                        partialScores.put(a, List);
                    } else {
                        List = partialScores.get(a);
                    }
                    Tuple<Student, String, Double> tuple = new Tuple<>(g.getStudent(), g.getCourse(), g.getPartialScore());
                    if (!List.contains(tuple))
                        List.add(tuple);
                }
            }
        }

    }
    @Override
    public void visit(Teacher teacher) {

        Catalog catalog = Catalog.getInstance();
        for (int i = 0; i < catalog.getCursuri().size(); i++) {
            Course course = catalog.getCursuri().get(i);
            Teacher t=course.getTitular();

            if (t.getLastName().equals(teacher.getLastName()) &&
               t.getFirstName().equals(teacher.getFirstName()) ) {

                if(examScores.get(teacher) != null)
                {

                    ArrayList<Tuple<Student, String, Double>> List = examScores.get(teacher);
                    ArrayList<Student> students = course.getAllStudents();
                    for (Tuple<Student, String, Double> tuple : List) {
                        if(tuple.getCourse().equals(course.getNume()))
                        {

                            Iterator<Student> iterator = students.iterator();
                            while (iterator.hasNext()) {
                                Student s = iterator.next();

                                if (s.getLastName().equals(tuple.getStudent().getLastName()) &&
                                        s.getFirstName().equals(tuple.getStudent().getFirstName())) {

                                    if (course.getGrade(s).getExamScore() == 0) {
                                        course.getGrade(s).setExamScore(tuple.getGrade());


                                        catalog.notifyObservers(course.getGrade(s));
                                    }
                                }
                            }
                        }
                    }
                    examScores.remove(teacher);
                }
            }
        }
    }

        public void addlist(Teacher teacher, Vector<String> note) {

            if (examScores.get(teacher) != null) {
                ArrayList<Tuple<Student, String, Double>> list = examScores.get(teacher);
                for (int i = 0; i < list.size(); i++) {
                    String s = list.get(i).getGrade().toString();
                    note.add(s);
                }
            }

        }
    public void addlist(Assistant assistant, Vector<String> note)
    {

        if(partialScores.get(assistant) == null)
            return;
        ArrayList<Tuple<Student, String, Double>> list = partialScores.get(assistant);
        for (int i = 0; i < list.size(); i++) {
            String s=list.get(i).getGrade().toString();
            note.add(s);
        }
    }

    @Override
    public void visit(Assistant assistant) {
        Catalog catalog = Catalog.getInstance();
        for (int i = 0; i < catalog.getCursuri().size(); i++) {
            Course course = catalog.getCursuri().get(i);
            for (Assistant j : course.getAsistenti()) {
                if (j.getLastName().equals(assistant.getLastName()) &&
                        j.getFirstName().equals(assistant.getFirstName())) {

                    if (partialScores.get(assistant) != null) {
                        ArrayList<Tuple<Student, String, Double>> List = partialScores.get(assistant);
                        ArrayList<Student> students = course.getAllStudents();
                        for (Tuple<Student, String, Double> tuple : List) {

                            if (tuple.getCourse().equals(course.getNume())) {

                                Iterator<Student> iterator = students.iterator();
                                while (iterator.hasNext()) {
                                    Student s = iterator.next();

                                    if (s.getLastName().equals(tuple.getStudent().getLastName()) &&
                                            s.getFirstName().equals(tuple.getStudent().getFirstName())) {

                                        if (course.getGrade(s).getPartialScore() == 0) {
                                            course.getGrade(s).setPartialScore(tuple.getGrade());

                                            catalog.notifyObservers(course.getGrade(s));
                                        }
                                    }
                                }
                            }
                        }
                        partialScores.remove(assistant);
                    }
                }
            }
        }




    }

    private class Tuple<A, B, C> {
        private A student;
        private B course;
        private C grade;

        public Tuple(A student, B course, C grade) {
            this.student = student;
            this.course = course;
            this.grade = grade;
        }

        public A getStudent() {
            return student;
        }

        public B getCourse() {
            return course;
        }

        public C getGrade() {
            return grade;
        }

        @Override
        public String toString() {
            return '{' +
                    "Student=" + student +
                    ", Course=" + course +
                    ", Grade=" + grade +
                    '}' +"\n";
        }
    }
}