import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class pract extends JFrame implements ActionListener{
    JButton user,admin;
    JTextField userName,password;
    pract(){
        setTitle("IRCTC");
        setLayout(null);

        userName=new JTextField("User Name");
        userName.setBounds(225,205,530,30);
        userName.setFont(new Font("Raleway", Font.PLAIN, 17));
        userName.setForeground(Color.gray);
        // userName.setBorder(null);
        // TextAnimator.textAnimator(userName,"User Name");
        add(userName);

        password=new JTextField("password");
        password.setBounds(225,250,235,30);
        password.setFont(new Font("Raleway", Font.PLAIN, 17));
        password.setForeground(Color.gray);
        // password.setBorder(null);
        TextAnimator.textAnimator(password,"password");
        add(password);
        getContentPane().setBackground(Color.white);

        user=new JButton("USER");
        user.setBounds(600,320,100,40);
        user.setFont(new Font("Raleway", Font.BOLD, 25));
        user.setForeground(Color.decode("#E87020"));
        user.setBackground(Color.white);
        user.setBorder(null);
        user.setOpaque(true);
        user.addActionListener(this);
        add(user);

        setSize(1000, 700);
        setVisible(true);
        setLocation(180, 20);
    }

    public void actionPerformed(ActionEvent e) {
        
    }

    public static void main(String args[])
    {
        new pract();
    }
}