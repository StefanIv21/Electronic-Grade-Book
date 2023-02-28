import java.io.FileReader;
import java.io.Reader;
import java.util.*;


import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;


public class Main2 {
    public static void main(String[] args)  {
        UserFactory a= new UserFactory();
        User b;
        JSONParser jsonParser = new JSONParser();
        try (Reader reader = new FileReader("catalog.json"))
        {

            JSONObject obj = (JSONObject) jsonParser.parse(reader);
            JSONArray cursuri = (JSONArray) obj.get("courses");

            for (int i=0;i<cursuri.size();i++) {
                Map<String, Group> map = new HashMap<>();
                Course.CourseBuilder courseBuilder=null;
                Course newcurs=null;
                TreeSet<Assistant> asistenti = new TreeSet<>();
                JSONObject arr = (JSONObject) cursuri.get(i);
                String type = (String) arr.get("type");
                String strategy = (String) arr.get("strategy");
                String cursname = (String) arr.get("name");

                if(type.equals("FullCourse")) {
                    courseBuilder = new FullCourse.FullCourseBuilder(cursname);
                }
                if(type.equals("PartialCourse")) {
                    courseBuilder = new PartialCourse.PartialCourseBuilder(cursname);
                }

                if(strategy.equals("BestPartialScore")) {
                    courseBuilder.setStrategy(new BestPartialScore());
                }
                else if(strategy.equals("BestExamScore")) {
                    courseBuilder.setStrategy(new BestExamScore());
                }
                else {
                    courseBuilder.setStrategy(new BestTotalScore());
                }

                JSONObject teacher = (JSONObject) arr.get("teacher");

                String teachername = (String) teacher.get("firstName");
                String teacherlast = (String) teacher.get("lastName");


                b = a.getUser("Teahcer", teachername, teacherlast);
                courseBuilder.setTitular((Teacher) b);


                JSONArray asisarr = (JSONArray) arr.get("assistants");

                for (int j = 0; j < asisarr.size(); j++) {
                    JSONObject asistent = (JSONObject) asisarr.get(j);

                    String asisnume = (String) asistent.get("firstName");
                    String assislast = (String) asistent.get("lastName");

                    User c = a.getUser("Assistant", asisnume, assislast);

                    asistenti.add((Assistant) c);
                }

                courseBuilder.setAsistenti(asistenti);

                JSONArray grupuri = (JSONArray) arr.get("groups");

                TreeSet<Grade> grade = new TreeSet<>();

                for (int j = 0; j < grupuri.size(); j++) {
                    JSONObject group = (JSONObject) grupuri.get(j);
                    String id = (String) group.get("ID");

                    JSONObject asistent = (JSONObject) group.get("assistant");

                    String asisnume = (String) asistent.get("firstName");
                    String assislast = (String) asistent.get("lastName");

                    User c = a.getUser("Assistant", asisnume, assislast);

                    Group group1 = new Group(id, (Assistant) c);
                    JSONArray students = (JSONArray) group.get("students");

                    for (int k = 0; k < students.size(); k++) {

                        JSONObject student = (JSONObject) students.get(k);
                        String name = (String) student.get("firstName");
                        String last = (String) student.get("lastName");

                        User f = a.getUser("Student", name, last);
                        Student s = (Student) f;

                        JSONObject mother = (JSONObject) student.get("mother");
                        if(mother != null) {
                            String namem = (String) mother.get("firstName");
                            String lastm = (String) mother.get("lastName");
                            User d = a.getUser("Parent", namem, lastm);
                            s.setMother((Parent) d);
                        }

                        JSONObject father = (JSONObject) student.get("father");
                        if(father != null) {
                            String namef = (String) father.get("firstName");
                            String lastf = (String) father.get("lastName");
                            User e = a.getUser("Parent", namef, lastf);
                            s.setFather((Parent) e);
                        }

                        group1.add(s);
                        Grade g = new Grade(0.00, 0.00, cursname, s);
                        grade.add(g);

                    }
                    map.put(id, group1);

                }
                courseBuilder.setGrades(grade);
                courseBuilder.setMap(map);
                Random rand = new Random();
                courseBuilder.setCredits(rand.nextInt((10-2)+1)+2);

                if(type.equals("FullCourse")) {
                    newcurs= courseBuilder.build();
                }
                if(type.equals("PartialCourse")) {
                    newcurs= courseBuilder.build();

                }

                Catalog catalog =Catalog.getInstance();
                catalog.addCourse(newcurs);

            }

            JSONArray exam = (JSONArray) obj.get("examScores");
            for (int i=0;i<exam.size();i++) {

                JSONObject arr = (JSONObject) exam.get(i);

                JSONObject teacher = (JSONObject) arr.get("teacher");

                String teachername = (String) teacher.get("firstName");
                String teacherlast = (String) teacher.get("lastName");

                b = a.getUser("Teahcer", teachername, teacherlast);

                JSONObject student = (JSONObject) arr.get("student");
                String name = (String) student.get("firstName");
                String last = (String) student.get("lastName");

               User c = a.getUser("Student", name, last);

                String cursname = (String) arr.get("course");

                Object grade1 =(Object) arr.get("grade");
                Double grade= new Double(grade1.toString());
                ScoreVisitor v = ScoreVisitor.getInstance();

                Grade g2 = new Grade(0.00, grade, cursname, (Student) c);
                v.add_grade_teacher((Teacher) b, g2);

            }
            JSONArray partial = (JSONArray) obj.get("partialScores");
            for (int i=0;i<partial.size();i++) {

                JSONObject arr = (JSONObject) partial.get(i);

                JSONObject assistant = (JSONObject) arr.get("assistant");

                String assname = (String) assistant.get("firstName");
                String asslast = (String) assistant.get("lastName");

                b = a.getUser("Assistant", assname, asslast);

                JSONObject student = (JSONObject) arr.get("student");
                String name = (String) student.get("firstName");
                String last = (String) student.get("lastName");

                User c = a.getUser("Student", name, last);

                String cursname = (String) arr.get("course");
                Object grade1 =(Object) arr.get("grade");
                Double grade= new Double(grade1.toString());

                ScoreVisitor v = ScoreVisitor.getInstance();

                Grade g2 = new Grade(grade, 0.00, cursname, (Student) c);
                v.add_grade_assistant((Assistant) b, g2);

            }

        }catch (Exception e) {
            e.printStackTrace();
        }

        Mainpage p =new Mainpage("Login");



    }
}