import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.util.TreeSet;
import java.util.Vector;

public class Teacherpage extends JFrame implements  ActionListener {
    JLabel titlu;
    JLabel notee;

    JPanel titlupanel;

    JPanel listacurusuri;
    JPanel combina;

    JButton validate;
    JPanel vali;


    Vector<String> note;
    DefaultListModel<String> defList;

    JList list;

    JScrollPane scrollPane;
    JScrollPane scrollPane2;

    JTextArea detalii;
    JTextField Cursuri;
    JButton back;

    Teacher nou;


    public Teacherpage(String message, Teacher s) {
        super(message);
        nou = s;
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 800);
        setMinimumSize(new Dimension(800, 800));
        note = new Vector<>();
        String courses = "Courses:";
        Catalog catalog = Catalog.getInstance();
        for (int i = 0; i < catalog.getCursuri().size(); i++) {
            Course course = catalog.getCursuri().get(i);
            Teacher teacher=course.getTitular();

            if (teacher.getFirstName().equals(s.getFirstName()) &&
             teacher.getLastName().equals(s.getLastName())) {
                courses += " " + course.getNume()+" ";


            }

        }
        courses += "\n";

        Cursuri=new JTextField(30);
        Cursuri.setText(courses);
        Cursuri.setEditable(false);
        Cursuri.setFont(new Font("Serif",Font.BOLD,20));
        ScoreVisitor visitor=ScoreVisitor.getInstance();



        visitor.addlist(s,note);

        defList = new DefaultListModel<>();

        for (int i = 0; i < note.size(); i++) {
            defList.add(i, note.get(i));
        }
        list = new JList(defList);

        titlu=new JLabel("Hi" +" " + s.getFirstName() + " "+s.getLastName());
        titlu.setFont(new Font("Serif",Font.BOLD,39));


        titlupanel=new JPanel();
        titlupanel.add(titlu);
        add(titlupanel,BorderLayout.NORTH);
        titlupanel.setBackground(new Color(255,111,71));

        notee=new JLabel("Grades to validate :");
        notee.setFont(new Font("Serif",Font.BOLD,20));
        scrollPane =new JScrollPane(list);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        detalii=new JTextArea();
        scrollPane2 =new JScrollPane(detalii);

        listacurusuri=new JPanel();
        listacurusuri.setLayout(new GridLayout(1,2));
        listacurusuri.add(scrollPane);
        listacurusuri.add(scrollPane2);


        combina=new JPanel();
        combina.setLayout(new GridLayout(3,1));
        combina.add(Cursuri);
        combina.add(notee);
        combina.add(listacurusuri);
        add(combina,BorderLayout.CENTER);

        validate=new JButton("Validare");
        if(note.size() ==0)
            validate.setEnabled(false);
        back=new JButton("Logout");
        back.addActionListener(this);
        validate.addActionListener(this);
        vali=new JPanel();
        vali.add(validate);
        vali.add(back);
        add(vali,BorderLayout.SOUTH);
        pack();
        setVisible(true);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == validate)
        {
           detalii.setText(note.toString());
           validate.setBackground(Color.green);
            ScoreVisitor v = ScoreVisitor.getInstance();
            nou.accept(v);
           validate.setEnabled(false);
           detalii.setEditable(false);
        }
        if(e.getSource() == back)
        {
            Mainpage p =new Mainpage("Login");
            dispose();
        }


    }
}

