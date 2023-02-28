

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.TreeSet;

public class Mainpage extends JFrame implements ActionListener
{
    JLabel titlu;
    JLabel user;
    JLabel password;
    JTextField usertext;
    JPasswordField passwordtext;
    JButton login;
    JPanel titlupanel;
    JPanel userpanel;
    JPanel passwordpanel;
    JPanel loginpanel;
    JTextField curs;
    JLabel cursinfo;
    JPanel curspanel;

    JPanel button;

    JPanel combina;

    JButton vezicursuri;



    public  Mainpage (String message)
    {
        super(message);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 800);
        setMinimumSize(new Dimension(800, 800));
        getContentPane().setBackground(new Color(255,111,71));
        titlu =new JLabel("Sign in to your account");
        titlu.setFont(new Font("Serif",Font.BOLD,39));

        titlupanel =new JPanel();
        titlupanel.add(titlu);

        user=new JLabel("Username");
        usertext =new JTextField(30);


        userpanel =new JPanel();
        userpanel.add(user);
        userpanel.add(usertext);
        userpanel.setBackground(new Color(255,111,71));

        password =new JLabel("Password");
        passwordtext =new JPasswordField(30);

         passwordpanel= new JPanel();
         passwordpanel.add(password);
         passwordpanel.add(passwordtext);
         passwordpanel.setBackground(new Color(255,111,71));

         login =new JButton("Sign in");
         vezicursuri=new JButton("See info about a cours");
         vezicursuri.addActionListener(this);
         login.addActionListener(this);
         button=new JPanel();
         button.add(login);
         button.add(vezicursuri);
         //login.setEnabled(false);

         loginpanel =new JPanel();
         loginpanel.setLayout(new GridLayout(3,1));

         cursinfo=new JLabel("Enter a Course to see into");
         curs=new JTextField(30);
         curspanel=new JPanel(new GridLayout(2,1));
         curspanel.add(cursinfo);
         curspanel.add(curs);
         curspanel.setBackground(new Color(255,111,71));

         loginpanel.add(userpanel);
         loginpanel.add(passwordpanel);
         loginpanel.add(curspanel);





         combina=new JPanel();
         combina.add(loginpanel);



         combina.setBackground(new Color(255,111,71));
         add(titlu,BorderLayout.NORTH);
         add(combina,BorderLayout.CENTER);

         add(button,BorderLayout.SOUTH);



        pack();
        setVisible(true);

    }
    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == login) {
            Catalog catalog = Catalog.getInstance();
            int ok=0;
            for (int i = 0; i < catalog.getCursuri().size(); i++) {
                String nume;
                String parola;
                Course course = catalog.getCursuri().get(i);
                ArrayList<Student> students= course.getAllStudents();


                for (int j=0;j<students.size();j++)
                {
                    if(students.get(j).getFather() != null) {
                        nume = students.get(j).getFather().getFirstName();
                        parola = students.get(j).getFather().getLastName();

                        if (nume.equals(usertext.getText()) && parola.equals(passwordtext.getText()) && ok == 0) {
                            ok = 1;
                            Parentpage s = new Parentpage("Parent page", students.get(j).getFather());
                            dispose();
                            break;
                        }
                    }
                    if(students.get(j).getMother() != null) {
                        nume = students.get(j).getMother().getFirstName();
                        parola = students.get(j).getMother().getLastName();

                        if (nume.equals(usertext.getText()) && parola.equals(passwordtext.getText()) && ok == 0) {
                            ok = 1;
                            Parentpage s = new Parentpage("Parent page", students.get(j).getMother());
                            dispose();
                            break;
                        }
                    }

                }



                for (int j=0;j<students.size();j++)
                {
                    nume = students.get(j).getFirstName();
                    parola = students.get(j).getLastName();

                    if(nume.equals(usertext.getText()) && parola.equals(passwordtext.getText()) && ok == 0)
                    {
                        ok=1;
                        Studentpage s =new Studentpage("Student page",students.get(j));
                        dispose();
                        break;
                    }
                }

                nume=course.getTitular().getFirstName();
                parola=course.getTitular().getLastName();
                if(nume.equals(usertext.getText()) && parola.equals(passwordtext.getText()))
                {

                    Teacherpage s =new Teacherpage("Teacher page",course.getTitular());
                    dispose();
                    break;
                }


                TreeSet<Assistant> asistenti=course.getAsistenti();
                Iterator<Assistant> it =asistenti.iterator();
                while (it.hasNext())
                {
                    Assistant a=it.next();
                    nume=a.getFirstName();
                    parola=a.getLastName();

                    if(nume.equals(usertext.getText()) && parola.equals(passwordtext.getText()) && ok == 0)
                    {

                        ok=1;
                        Assistantpage s =new Assistantpage("Assistant page",a);
                        dispose();
                        break;
                    }
                }
                titlu.setText("Invalid");
            }
        } else if (e.getSource() == vezicursuri) {

            Catalog catalog= Catalog.getInstance();
            for (int i = 0; i < catalog.getCursuri().size(); i++) {
                Course course = catalog.getCursuri().get(i);

                if (course.getNume().equals(curs.getText())) {
                    Cursuripage s = new Cursuripage("Curs info", course);
                    dispose();
                    break;
                }
            }
        }
    }
}
