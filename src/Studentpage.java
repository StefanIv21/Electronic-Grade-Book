import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;

public class Studentpage extends JFrame implements ListSelectionListener, EventListener, ActionListener {
    JLabel titlu;
    JLabel cursuri;

    JPanel titlupanel;

    JPanel listacurusuri;
    JPanel combina;


    Vector<String> courses;
    DefaultListModel<String> defList;

    JList list;

    JScrollPane scrollPane;
    JScrollPane scrollPane2;

    JTextArea detalii;
    JButton back;

    Student nou;



    public Studentpage(String message, Student s) {
        super(message);
        nou=s;
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 800);
        setMinimumSize(new Dimension(800, 800));
        courses = new Vector<>();
        Catalog catalog = Catalog.getInstance();
        for (int i = 0; i < catalog.getCursuri().size(); i++) {
            Course course = catalog.getCursuri().get(i);
            ArrayList<Student> students = course.getAllStudents();
            for (int j = 0; j < students.size(); j++) {
                if (students.get(j).getLastName().equals(s.getLastName()) &&
                        students.get(j).getFirstName().equals(s.getFirstName()))
                    courses.add(course.getNume());
            }
        }
        defList = new DefaultListModel<>();

        for (int i = 0; i < courses.size(); i++) {
            defList.add(i, courses.get(i));
        }
        list = new JList(defList);
        list.addListSelectionListener(this);
        titlu=new JLabel("Hi" +" " + s.getFirstName() + " "+s.getLastName());
        titlu.setFont(new Font("Serif",Font.BOLD,39));


        titlupanel=new JPanel();
        titlupanel.add(titlu);
        add(titlupanel,BorderLayout.NORTH);
        titlupanel.setBackground(new Color(255,111,71));

        cursuri=new JLabel("Courses assigned :");
        cursuri.setFont(new Font("Serif",Font.BOLD,20));
        scrollPane =new JScrollPane(list);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        detalii=new JTextArea();
        scrollPane2 =new JScrollPane(detalii);

        listacurusuri=new JPanel();
        listacurusuri.setLayout(new GridLayout(1,2));
        listacurusuri.add(scrollPane);
        listacurusuri.add(scrollPane2);



        combina=new JPanel();
        combina.setLayout(new GridLayout(2,1));
        combina.add(cursuri);
        combina.add(listacurusuri);


        add(combina,BorderLayout.CENTER);

        back=new JButton("Logout");
        back.addActionListener(this);
        JPanel buton=new JPanel();
        buton.add(back);
        add(buton,BorderLayout.SOUTH);




        pack();
        setVisible(true);
    }


    @Override
    public void valueChanged(ListSelectionEvent e) {
        if(list.isSelectionEmpty()) {
           return;
        }
        else {

            Object value = list.getSelectedValue();
            String titlu = (String) value;
            Catalog catalog = Catalog.getInstance();
            for (int i = 0; i < catalog.getCursuri().size(); i++) {
                Course course = catalog.getCursuri().get(i);
                if (course.getNume().equals(titlu)) {
                    String info = "Info\n";
                    String teacher = "Course teacher:" + " " + course.getTitular().toString() + "\n";
                    //System.out.println(teacher);
                    Iterator<Assistant> it = course.getAsistenti().iterator();
                    String asistent = "Course assistamts:\n";
                    while (it.hasNext()) {
                        asistent += "\t" + it.next() + "\n";
                    }

                    String id = "";
                    String asistent1 = "";
                    Map<String, Group> map = course.getMap();
                    for (Map.Entry<String, Group> mp : map.entrySet()) {
                        Group g = mp.getValue();
                        for (Student s:g) {
                            if (s.getFirstName().equals(nou.getFirstName()) &&
                                s.getLastName().equals(nou.getLastName())) {
                                id = "ID" + " " + g.getID() + "\n";
                                asistent1 = "Assistant:" + " " + g.getAsistent().toString() + "\n";
                                break;
                            }
                        }
                    }


                        Grade grade = course.getGrade(nou);
                       String mygrade = grade.toString();




                    detalii.setText(info + teacher + asistent + asistent1 +id + mygrade);

                    break;
                }
            }

        }


    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == back) {

            Mainpage p= new Mainpage("Login");
            dispose();
        }
    }
}
