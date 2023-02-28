import java.util.ArrayList;

class FullCourse extends Course {
    public FullCourse(Course.CourseBuilder builder) {
        super(builder);
    }
    public ArrayList<Student> getGraduatedStudents() {
        ArrayList<Student> result = new ArrayList<>();
        ArrayList<Student> students = getAllStudents();

        for (int i = 0; i < students.size(); i++) {
            if (getGrade(students.get(i)) != null) {
                if (getGrade(students.get(i)).getPartialScore() >= 3 && getGrade(students.get(i)).getExamScore() >= 2) {
                    result.add(students.get(i));
                }
            }
        }

        return result;
    }
    static class FullCourseBuilder extends Course.CourseBuilder {
        public FullCourseBuilder(String name ) {
            super(name);
        }

        @Override
        public FullCourse build() {
            return new FullCourse(this);
        }
    }
}