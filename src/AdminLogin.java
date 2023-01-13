import javax.net.ssl.TrustManager;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.Random;

public class AdminLogin extends JFrame implements ActionListener{
    String genOtp,email;
    JTextField adminId,otp;
    JPasswordField password;
    JButton login,back;
    AdminLogin(){
        setTitle("IRCTC");
        setLayout(null);  
        
        //Main frame image
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("img/adminLogin.png"));
        Image i2 = i1.getImage().getScaledInstance(983, 660, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(0, 0, 983, 660);
        add(image); 

        adminId =new JTextField("Admin Id");
        adminId.setBounds(250,240,490,30);
        adminId.setFont(new Font("Raleway", Font.BOLD, 20));
        adminId.setForeground(Color.gray);
        adminId.setBorder(null);
        textAnimator(adminId,"Admin Id");
        image.add(adminId);

        password =new JPasswordField("Password");
        password.setBounds(250,318,490,30);
        password.setFont(new Font("Raleway", Font.BOLD, 20));
        password.setForeground(Color.gray);
        password.setBorder(null);
        textAnimator(password,"Password");
        image.add(password);

        login=new JButton("LOGIN");
        login.setBounds(662,408,90,40);
        login.setForeground(Color.white);
        login.setBackground(Color.decode("#e87020"));
        login.setFont(new Font("Raleway", Font.BOLD, 24));
        login.setBorder(null);
        login.addActionListener(this);
        image.add(login);

        back=new JButton("BACK");
        back.setBounds(250,410,70,30);
        back.setFont(new Font("Raleway", Font.BOLD, 20));
        back.setForeground(Color.decode("#E87020"));
        back.setBackground(Color.white);
        back.setBorder(null);
        back.addActionListener(this);
        image.add(back);
        
        getContentPane().setBackground(Color.white);

        setSize(1000, 700);
        setVisible(true);
        setLocation(180, 20);
    }

    public void textAnimator(JTextField text,String content){
        text.addFocusListener(new FocusAdapter(){
            //clear the text when the field gains focus
            public void focusGained(FocusEvent e){
                text.setText("");
            }
            //set the initial text when the field loses focus
            public void focusLost(FocusEvent e){
                if(text.getText().isEmpty()){
                    text.setText(content);
                }
            }
        });
    }
    public void actionPerformed(ActionEvent e){
        if(e.getSource()==login){
            genOtp=String.copyValueOf(OTP(4));
            System.out.println(genOtp);
            String admin_id=adminId.getText();
            String passwordString=password.getText();
            try{
                Conn c=new Conn();

                String query="SELECT COUNT(admin_id) as valid FROM admin_login WHERE admin_id='"+admin_id+"' and password='"+passwordString+"';";
                ResultSet rs=c.s.executeQuery(query);
                if(rs.next()){
                    if(rs.getInt("valid")!=0){
                        rs=c.s.executeQuery("select email from admin_login where admin_id='"+adminId.getText()+"';");
                        if(rs.next()){
                            email=rs.getString("email");
                            System.out.println(email);
                            SendOTP.sendOTP(genOtp,email);
                            String enteredOtp= JOptionPane.showInputDialog("Enter the otp sent to your email "); 
                            System.out.println(enteredOtp);
                            if(genOtp.equals(enteredOtp)){
                                setVisible(false);
                                new Admin(admin_id);
                            }
                            else{
                                JOptionPane.showMessageDialog(null,"Incorrect OTP. please try again"); 
                            }
                        }
                    }
                    else{
                    JOptionPane.showMessageDialog(null,"Incorrect User Name or Password"); 
                    adminId.setText("");
                    password.setText("");
                }
                }
                else{
                    JOptionPane.showMessageDialog(null,"Incorrect User Name or Password"); 
                    adminId.setText("");
                    password.setText("");
                }
            }catch(Exception error){
                System.out.println(error);
            }
        }else if(e.getSource()==back){
            setVisible(false);
            new Main();
        }
    }

    static char[] OTP(int len)
    {
        System.out.print("You OTP is : ");
        String numbers = "0123456789";
        Random rndm_method = new Random();
        char[] otp = new char[len];
        for (int i = 0; i < len; i++)
        {
            otp[i] =numbers.charAt(rndm_method.nextInt(numbers.length()));
        }
        return otp;
    }
    public static void main(String args[])
    {
        new AdminLogin();
    }
}