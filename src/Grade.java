public class Grade implements Comparable,Cloneable {
    private Double partialScore,examScore;
    private Student student;
    private String course;

    public Grade(Double partialScore, Double examScore, String course, Student student) {
        this.partialScore = partialScore;
        this.examScore = examScore;
        this.course = course;
        this.student = student;
    }

    public Double getPartialScore() {
        return partialScore;
    }

    public Double getExamScore() {
        return examScore;
    }

    public String getCourse() {
        return course;
    }

    public Student getStudent() {
        return student;
    }

    public void setPartialScore(Double partialScore) {
        this.partialScore = partialScore;
    }

    public void setExamScore(Double examScore) {
        this.examScore = examScore;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public void setStudent(Student student) {
        this.student = student;
    }
    public Double getTotal()
    {
        return Math.round((partialScore+examScore)*100.0)/100.0;
    }
    @Override
    public int compareTo(Object o) {
        Grade grade =(Grade) o;
        if(grade.getTotal() < this.getTotal())
            return 1;
        if(grade.getTotal() >= this.getTotal())
            return  -1;
        return 0;
    }

    public Grade clone() throws CloneNotSupportedException {
        return (Grade) super.clone();
    }
    public String toString() {
        return  student.getFirstName() +
                " " + student.getLastName() +
                " has at " + course + "\n" +
                "\t Partial score: " + partialScore + ";\n" +
                "\t Exam score: " + examScore + ";\n" +
                "\t Total score: " + getTotal() + ".\n";

    }

}
