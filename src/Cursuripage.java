

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.util.*;


public class Cursuripage extends JFrame implements  ActionListener {
    JTextArea titlu;
    JPanel titlupanel;

    JPanel liste;
    JPanel combina;

    JButton assbutton;
    JPanel vali;
    JScrollPane scrollPane;
    JScrollPane scrollPane2;
    JScrollPane scrollPane3;
    JTextArea detalii;
    JTextArea studentitext;
    JTextArea gradestext;
    JTextField addasistantid;
    JTextField addasistantfirstname;
    JTextField addasistantlastname;
    JLabel ass;
    JPanel adauga;
    JTextField studentfirstname;
    JTextField studentlastname;
    JTextField grade1;
    JTextField grade2;
    JLabel studentlabel;
    JPanel studentpanel;
    JPanel adauga2;
    JTextField studentid;
    JButton studentbutton;
    JButton back;
    Course nou;
    JButton graduated;
    JButton gradesbutton;
    JButton makebackup;
    JButton restore;




    public Cursuripage(String message, Course s) {
        super(message);
        nou = s;
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 800);
        setMinimumSize(new Dimension(800, 800));
        titlu=new JTextArea();
        titlu.setText(s.getNume()+"  "+"Credits:"+s.getCredits()+"  "+"Best Student:"+s.getBestStudent());
        titlu.setFont(new Font("Serif", Font.BOLD, 39));
        titlu.setBackground(new Color(255,111,71));


        titlupanel = new JPanel();
        titlupanel.add(titlu);
        add(titlupanel, BorderLayout.NORTH);
        titlupanel.setBackground(new Color(255,111,71));

        Map<String, Group> map=nou.getMap();
        String asistenti="Assistants and Groups:\n";
        for (Map.Entry<String, Group> mp : map.entrySet()) {
            Group g = mp.getValue();
            asistenti +="\t"+g.getAsistent()+" "+g.getID()+"\n";
            }
        detalii = new JTextArea();
        detalii.setText(asistenti);
        detalii.setEditable(false);
        scrollPane2 = new JScrollPane(detalii);
        scrollPane2.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        ArrayList<Student> students=s.getAllStudents();
        String studenti ="Students:";
        for(int i=0;i<students.size();i++){
            studenti += "\t"+students.get(i).toString()+"\n";
        }
        studentitext=new JTextArea();
        studentitext.setText(studenti);
        studentitext.setEditable(false);
        scrollPane = new JScrollPane(studentitext);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        TreeSet<Grade> grades =s.getGrades();
        TreeSet<Grade> grade_aux=new TreeSet<>();
        Iterator<Grade> it2 =grades.iterator();
        while (it2.hasNext()){
            Grade g=it2.next();
            grade_aux.add(g);
        }

        String grade ="Grades:\n";
        Iterator<Grade> it3 =grade_aux.iterator();
        while (it3.hasNext()){
            Grade g=it3.next();
            grade +=g.toString();
        }

        gradestext=new JTextArea();
        gradestext.setText(grade);
        gradestext.setEditable(false);
        scrollPane3 = new JScrollPane(gradestext);
        scrollPane3.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);



        liste = new JPanel();
        liste.setLayout(new GridLayout(1, 3));
        liste.add(scrollPane);
        liste.add(scrollPane2);
        liste.add(scrollPane3);
        liste.setPreferredSize(new Dimension(1100,700));

        ass=new JLabel("ADD Assistant group,firstname and lastname\n");
        ass.setFont(new Font("Serif", Font.BOLD, 18));
        addasistantid=new JTextField(10);
        addasistantfirstname=new JTextField(10);
        addasistantlastname=new JTextField(12);

        studentlabel=new JLabel("Add student firstname, lastname, partialgrade, examgrade and GroupId\n");
        studentlabel.setFont(new Font("Serif", Font.BOLD, 18));
        studentfirstname=new JTextField(10);
        studentlastname=new JTextField(10);
        studentid=new JTextField(10);
        grade1=new JTextField(10);
        grade2=new JTextField(10);
        adauga=new JPanel();
        adauga.setLayout(new GridLayout(4,1));
        adauga.add(ass);
        adauga.add(addasistantid);
        adauga.add(addasistantfirstname);
        adauga.add(addasistantlastname);
        adauga.setPreferredSize(new Dimension(600,200));

        studentpanel=new JPanel();
        studentpanel.setLayout(new GridLayout(6,1));
        studentpanel.add(studentlabel);
        studentpanel.add(studentfirstname);
        studentpanel.add(studentlastname);
        studentpanel.add(grade1);
        studentpanel.add(grade2);
        studentpanel.add(studentid);
        studentpanel.setPreferredSize(new Dimension(600,200));
        adauga2=new JPanel();
        adauga2.setLayout(new GridLayout(2,1));
        adauga2.add(studentpanel);
        adauga2.add(adauga);

        combina = new JPanel();
        combina.add(liste);

        add(combina, BorderLayout.EAST);
        add(adauga2,BorderLayout.CENTER);
        scrollPane3.revalidate();

        assbutton = new JButton("ADD Assistant");
        assbutton.addActionListener(this);
        studentbutton=new JButton("ADD Student");
        studentbutton.addActionListener(this);
        graduated =new JButton("Check Graduated Students");
        graduated.addActionListener(this);
        gradesbutton=new JButton("Grades");
        gradesbutton.addActionListener(this);
        makebackup=new JButton("backup");
        makebackup.addActionListener(this);
        restore=new JButton("Restore Grades");
        restore.addActionListener(this);

        vali = new JPanel();
        vali.add(assbutton);
        vali.add(studentbutton);
        vali.add(graduated);
        vali.add(gradesbutton);
        vali.add(makebackup);
        vali.add(restore);
        back = new JButton("Go back");
        back.addActionListener(this);
        vali.add(back);
        add(vali, BorderLayout.SOUTH);
        pack();
        setVisible(true);
    }


    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == assbutton) {
            if(addasistantid.getText().equals("") && addasistantlastname.getText().equals("") && addasistantfirstname.getText().equals("")) {
                return;
            }

                UserFactory a = new UserFactory();
                User c = a.getUser("Assistant", addasistantfirstname.getText(), addasistantlastname.getText());
                Map<String, Group> map = nou.getMap();


                if (!map.containsKey(addasistantid.getText())) {
                    nou.addGroup(addasistantid.getText(),null,Comparator.naturalOrder());
                    nou.addAssistant(addasistantid.getText(),(Assistant) c);
                }

                String asistenti="Assistants and Groups:\n";
                for (Map.Entry<String, Group> mp : map.entrySet()) {
                    Group g = mp.getValue();
                    asistenti +="\t"+g.getAsistent()+" "+g.getID()+"\n";
                }

                        detalii.setText(asistenti);




        }
        if (e.getSource() == studentbutton) {
            if(studentlastname !=null && studentfirstname != null && studentid!=null && grade1!=null && grade2!=null) {

                UserFactory a = new UserFactory();
                User c = a.getUser("Student", studentfirstname.getText(), studentlastname.getText());
                Map<String, Group> map = nou.getMap();

                if (map.containsKey(studentid.getText())) {
                    Group group =map.get(studentid.getText());

                    for (Student s :group)
                    {
                        if(s.getLastName().equals(studentlastname.getText() ) &&
                        s.getFirstName().equals(studentfirstname.getText()))
                            return;
                    }

                    nou.addStudent(studentid.getText(), (Student) c);
                    TreeSet<Grade> grade = nou.getGrades();

                    Double d1=Double.parseDouble(grade1.getText());
                    Double d2=Double.parseDouble(grade2.getText());

                    nou.addGrade(new Grade(d1,d2,nou.getNume(),(Student) c));
                    ArrayList<Student> students=nou.getAllStudents();
                    String studenti ="Students:";
                    for(int i=0;i<students.size();i++){
                        studenti += "\t"+students.get(i).toString()+"\n";
                    }
                    studentitext.setText(studenti);

                    TreeSet<Grade> grade_aux=new TreeSet<>();
                    Iterator<Grade> it2 =grade.iterator();
                    while (it2.hasNext()){
                        Grade g=it2.next();
                        grade_aux.add(g);
                    }

                    String grades ="Grades:\n";
                    Iterator<Grade> it3 =grade_aux.iterator();
                    while (it3.hasNext()){
                        Grade g=it3.next();
                        grades +=g.toString();
                    }

                    gradestext.setText(grades);

                    titlu.setText(nou.getNume()+"  "+"Credits:"+nou.getCredits()+"  "+"Best Student:"+nou.getBestStudent());

                    scrollPane3.revalidate();
                }
            }


        }
        if (e.getSource() == back) {
            Mainpage p = new Mainpage("Login");
            dispose();
        }
        if (e.getSource() == graduated) {
            ArrayList<Student> graduated=nou.getGraduatedStudents();
            gradestext.setText(graduated.toString());

        }
        if(e.getSource() == gradesbutton)
        {

            gradestext.setText(nou.getAllStudentGrades().toString());
        }
        if(e.getSource() == makebackup)
        {
            try {
                nou.makeBackup();
            } catch (CloneNotSupportedException ex) {
                throw new RuntimeException(ex);
            }
        }
        if(e.getSource() == restore)
        {
            nou.undo();
            titlu.setText(nou.getNume()+"  "+"Credits:"+nou.getCredits()+"  "+"Best Student:"+nou.getBestStudent());
        }



    }
}