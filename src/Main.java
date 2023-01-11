import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Main extends JFrame implements ActionListener{
    JButton user,admin;
    Main(){
        setTitle("IRCTC");
        setLayout(null);

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("img/main.png"));
        Image i2 = i1.getImage().getScaledInstance(983, 660, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(0, 0, 983, 660);
        add(image);
        
        admin=new JButton("ADMIN");
        admin.setBounds(51,573,120,45);
        admin.setFont(new Font("Raleway", Font.BOLD,35));
        admin.setForeground(Color.decode("#E87020"));
        admin.setBackground(Color.white);
        admin.setBorder(null);
        admin.setOpaque(true);
        admin.addActionListener(this);
        image.add(admin);

        user=new JButton("USER");
        user.setBounds(839,573,100,40);
        user.setFont(new Font("Raleway", Font.BOLD, 35));
        user.setForeground(Color.decode("#E87020"));
        user.setBackground(Color.white);
        user.setBorder(null);
        user.setOpaque(true);
        user.addActionListener(this);
        image.add(user);

        getContentPane().setBackground(Color.white);

        setSize(1000, 700);
        setVisible(true);
        setLocation(180, 20);
    }

    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==user){
            setVisible(false);
            new Login().setVisible(true);
        }
        else if(e.getSource()==admin){
            setVisible(false);
            new AdminLogin().setVisible(true);
        }
    }

    public static void main(String args[])
    {
        new Main();
    }
}