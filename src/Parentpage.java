

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import java.util.List;


public class Parentpage extends JFrame implements  ActionListener {
    JLabel titlu;
    JLabel notee;

    JPanel titlupanel;

    JPanel listacurusuri;


    JButton validate;
    JPanel vali;








    JScrollPane scrollPane2;

    JTextArea detalii;


    Parent nou;
    JButton back;


    public Parentpage(String message, Parent s) {
        super(message);
        nou = s;
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 800);
        setMinimumSize(new Dimension(800, 800));

        titlu=new JLabel("Hi" +" " + s.getFirstName() + " "+s.getLastName());
        titlu.setFont(new Font("Serif",Font.BOLD,39));

        titlupanel=new JPanel();
        titlupanel.add(titlu);
        add(titlupanel,BorderLayout.NORTH);
        titlupanel.setBackground(new Color(255,111,71));

        detalii=new JTextArea();

        scrollPane2 =new JScrollPane(detalii);
        scrollPane2.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        notee=new JLabel("Notify:");
        notee.setFont(new Font("Serif",Font.BOLD,39));



        listacurusuri=new JPanel();
        listacurusuri.setLayout(new GridLayout(2,1));
        listacurusuri.add(notee);
        listacurusuri.add(scrollPane2);
        add(listacurusuri,BorderLayout.CENTER);

        validate=new JButton("Check");
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

            List<Notification> notificari =new ArrayList<>();
            Catalog catalog =Catalog.getInstance();
            List<Notification> notificationList =catalog.getNotifications();
            for(int i=0;i<notificationList.size();i++) {
                Grade g = notificationList.get(i).getGrade();
                Parent father = g.getStudent().getFather();
                Parent mother = g.getStudent().getMother();

                if (father != null) {

                    if (father.getFirstName().equals(nou.getFirstName()) && father.getLastName().equals(nou.getLastName())) {
                        notificari.add(notificationList.get(i));
                    }
                }
                if (mother != null) {
                        if (mother.getFirstName().equals(nou.getFirstName()) && mother.getLastName().equals(nou.getLastName())) {
                            notificari.add(notificationList.get(i));
                    }
                }

            }

            detalii.setText(notificari.toString());
            detalii.setFont(new Font("Serif",Font.BOLD,20));
            detalii.setEditable(false);

        }
        if(e.getSource() == back)
        {
            Mainpage p= new Mainpage("Login");
            dispose();
        }


    }
}

