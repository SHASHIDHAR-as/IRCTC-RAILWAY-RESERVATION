import javax.swing.*;
import java.awt.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.event.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Random;
import java.sql.*;

public class ConfirmBooking extends JFrame implements ActionListener {
    JTextField PassengerName, Age;
    JRadioButton male, female, other;
    JButton Add, back, delete, submit,show,confirm;
    JTable table = new JTable();
    JScrollPane pane;
    Object[] cols = null;
    DefaultTableModel model;
    static Box vertical = Box.createVerticalBox();
    String Pnr_no;
    int train_no,total,seats;
    String train_name ,source,destination ,arrival_time ,destinationTime ,user_name,timeStamp;
    String email,genOtp,date_of_travel;
    boolean buttonPressed = false;
    int seatsAvailable;
    BookedTrain details;
    JPanel panel3;

        ConfirmBooking(BookedTrain details,String Pnr_no,String user_name,int seats,String sourceName,String destinationName,String date_of_travel){
            this.date_of_travel=date_of_travel;
            this.details=details;
            train_no = details.train_no;
            train_name = details.train_name;
            source = details.source;
            destination =details. destination; 
            seatsAvailable=details.seatsAvailable; 
            // System.out.println(train_no); 
            arrival_time = details.arrival_time;
            destinationTime = details.destinationTime;
            this.Pnr_no=Pnr_no;
            total=details.cost;
            this.seats=seats;
            this.user_name=user_name;
            setTitle("IRCTC");

            JPanel headerPanel=new JPanel();
            String content="<html><p>CONFIRM BOOKING</p><br> </html>";
            JLabel header=new JLabel(content,JLabel.CENTER);
            header.setAlignmentX(JLabel.CENTER_ALIGNMENT);
            headerPanel.setBackground(Color.decode("#e87020"));
            header.setForeground(Color.white);
            header.setFont(new Font("Raleway", Font.BOLD, 20));
            headerPanel.add(header);
            // headerPanel.setMaximumSize( new Dimension(  983, 200) );
            headerPanel.setBounds(0, 0, 1000, 60);
            add(headerPanel);

            // to show the selectd train
            Container c = getContentPane();
            JPanel panel = new JPanel();
            panel.setLayout(null);

            JLabel label1 = new JLabel("TRAIN SELECTED :");
            label1.setBounds(190, 15, 200, 50);
            label1.setFont(new Font("Raleway", Font.BOLD, 20));
            panel.add(label1);

            JLabel label = new JLabel( train_name + " ("+train_no+")" );
            // + " " + source + " " + destination + " " + arrivalTime
            //         + " " + destinationTime
            label.setBounds(410, 15, 500, 50);
            label.setFont(new Font("Raleway", Font.PLAIN, 20));
            panel.add(label);
            JLabel froms = new JLabel("FROM                     :");
            froms.setBounds(190, 45, 200, 50);
            froms.setFont(new Font("Raleway", Font.BOLD, 20));
            panel.add(froms);

            JLabel from1 = new JLabel( sourceName+" ("+arrival_time+")" );
            // + " " + source + " " + destination + " " + arrivalTime
            //         + " " + destinationTime
            from1.setBounds(410, 45, 500, 50);
            from1.setFont(new Font("Raleway", Font.PLAIN, 20));
            panel.add(from1);
            JLabel tod = new JLabel("TO                           :");
            tod.setBounds(190, 75, 200, 50);
            tod.setFont(new Font("Raleway", Font.BOLD, 20));
            panel.add(tod);
    
            JLabel to1 = new JLabel( destinationName+" ("+destinationTime+")");
            // + " " + source + " " + destination + " " + arrivalTime
            //         + " " + destinationTime
            to1.setBounds(410, 75, 500, 50);
            to1.setFont(new Font("Raleway", Font.PLAIN, 20));
            panel.add(to1);

            JLabel label02 = new JLabel("DATE - TIME          :");
            label02.setBounds(190, 108, 200, 50);
            label02.setFont(new Font("Raleway", Font.BOLD, 20));
            panel.add(label02);

            timeStamp = new SimpleDateFormat("dd/MM/yyyy \n HH-mm-ss").format(Calendar.getInstance().getTime());
            JLabel label01 = new JLabel(timeStamp);
            label01.setFont(new Font("Raleway", Font.PLAIN, 20));
            label01.setBounds(400, 108, 300, 50);
            panel.add(label01);

            JLabel label05 = new JLabel("SEATS BOOKED   :");
            label05.setFont(new Font("Raleway", Font.BOLD, 20));
            label05.setBounds(190, 140, 300, 50);
            panel.add(label05);
            JLabel label06 = new JLabel(seats+" ");
            label06.setBounds(400, 140, 200, 50);
            label06.setFont(new Font("Raleway", Font.PLAIN, 20));
            panel.add(label06);

            JLabel label03 = new JLabel("TOTAL FARE         :");
            label03.setBounds(190, 173, 200, 50);
            label03.setFont(new Font("Raleway", Font.BOLD, 20));
            panel.add(label03);
            JLabel label04 = new JLabel(total+" ");
            label04.setBounds(400, 173, 200, 50);
            label04.setFont(new Font("Raleway", Font.PLAIN, 20));
            panel.add(label04);

            show = new JButton("SHOW PASSENGERS");
            show.setBounds(240, 218, 300, 30);
            show.setForeground(Color.decode("#e87020"));
            show.setFont(new Font("Raleway", Font.BOLD, 20));
            show.setBorder(null);
            show.setBackground(Color.white);
            show.addActionListener(this);
            panel.add(show);

            panel.setBackground(Color.WHITE);
            panel.setBounds(0, 60, 1000, 250);
            c.add(panel);

            Container c3 = getContentPane();
            panel3 = new JPanel();
            panel3.setLayout(null);
            panel3.setBackground(Color.WHITE);
            panel3.setVisible(false);
            panel3.setBounds(0, 310, 1000, 600);

            cols = new String[] { "Name", "AGE", "GENDER","SEAT_NO" };

            model = (DefaultTableModel) table.getModel();

            model.setColumnIdentifiers(cols);

            table.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
            JTableHeader Theader=table.getTableHeader();
            Theader.setBackground(Color.decode("#e87020"));
            Theader.setFont(new Font("Raleway", Font.BOLD, 20));
            table.setFont(new Font("Raleway", Font.PLAIN, 16));

            pane = new JScrollPane(table);
            pane.setBounds(100, 10, 800, 200);
            pane.setBackground(Color.WHITE);
            panel3.add(pane);

            confirm = new JButton("CONFIRM");
            confirm.setBounds(50, 250, 250, 30);
            confirm.setForeground(Color.decode("#e87020"));
            confirm.setFont(new Font("Raleway", Font.BOLD, 20));
            confirm.setBorder(null);
            confirm.setBackground(Color.white);
            confirm.addActionListener(this);
            panel3.add(confirm);
            c3.add(panel3);

            back = new JButton("BACK");
            back.setBounds(780, 250, 100, 30);
            back.setForeground(Color.decode("#e87020"));
            back.setFont(new Font("Raleway", Font.BOLD, 20));
            back.setBorder(null);
            back.setBackground(Color.white);
            back.addActionListener(this);
            panel3.add(back);

            c3.add(panel3);
            setLayout(null);
            setLocation(180, 20);
            setSize(1000, 700);
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        
        // if (!buttonPressed) {
        if (e.getSource() == show & !buttonPressed) {
                try{
                    panel3.setVisible(true);
                    Conn c = new Conn();
                    // String Pnrnum="2276717745";
                    ResultSet rs = c.s.executeQuery("select * from Passengers where pnr_no = '" + Pnr_no+ "'");
                while (rs.next()) {

                    String name = rs.getString("Name");
                    String age = rs.getString("Age");
                    String Gender = rs.getString("Gender");
                    // String pnr = rs.getString("pnr_num");
                    int Seat_num = rs.getInt("seat_no");
                    model.addRow(new Object[] { name,age,Gender,Seat_num });
                }
                
            }
                catch (Exception error) {
                    System.out.println(error);
                }
                buttonPressed = true;
                }
            // }
            
            
        else if (e.getSource() == confirm) {
            try {       int booking_id;

                Random ran = new Random();
                booking_id= ran.nextInt(3000);
                System.out.println("bookin_id"+booking_id);
                        Conn c = new Conn();
                        int rows = table.getRowCount();
                        System.out.println(rows);
        
                        // String query = "Insert into pnr_status(pnr_no,train_no,train_name,from_station,to_station) values ('"+Pnr_no+"','"+train_no+"','"+train_name+"','"+source+"','"+destination+"')";

                        // c.s.executeUpdate(query);
                                    //insert into booking
                                    
                        String query2 = "Insert into bookings(booking_id,pnr_no,user_name,date,ticket_cost) values ('"+booking_id+"','"+Pnr_no+"','"+user_name+"','"+timeStamp+"','"+total+"')";
                        c.s.executeUpdate(query2);

                        ArrayList<String> details=new ArrayList<String>();
                        details.add("PNR NUM    :"+Pnr_no);
                        details.add("USER NAME  :"+user_name);
                        details.add("SOURCE     :"+source);
                        details.add("DESTINATION:"+destination);
                        details.add("TIME       :"+timeStamp);
                        details.add("BOOKING ID :"+booking_id);
                        details.add("NUM SEATS  :"+String.valueOf(seats));
                        details.add("TOTAL FAIR :"+String.valueOf(total));
                        
                        ResultSet rs = c.s.executeQuery("SELECT email  FROM user_login WHERE user_name='"+user_name+"';");
                        
                        if(rs.next()){
                            email=rs.getString("email");
                            System.out.println(email);
                            genOtp=String.copyValueOf(OTP(4));
                            SendOTP.sendOTP(genOtp,email);
                            String enteredOtp= JOptionPane.showInputDialog("Enter the otp sent to your email to confirm tickets "); 
                            System.out.println(enteredOtp);
                            if(genOtp.equals(enteredOtp)){
                                JOptionPane.showMessageDialog(null,"Your tickets are being confirmed \nwait for 5 seconds");
                                // MailAttachment.sendConfirmation(email,user_name,details);
                                MailAttachment.sendConfirmation(email,user_name,details,"Railway tickets booking confirmation","\n\nYour Train tickets are booked \n\nDETAILS :\n\n");
                                JOptionPane.showMessageDialog(null, "Tickets confirmed \nBooking details are sent to your email\n"+"BOOKING ID:"+booking_id);
                            }
                            else{
                                JOptionPane.showMessageDialog(null,"Incorrect OTP. please try again"); 
                            }
                        }

                    } catch (Exception error) {
                        System.out.println(error);
                    }
                    setVisible(false);
                    new HomePage(user_name).setVisible(true);
        } 
        else if (e.getSource() == back) {
            try{
                Conn c=new Conn();
                String query="delete from passengers where pnr_no='"+Pnr_no+"';";
                String query3="delete from pnr_status where pnr_no='"+Pnr_no+"';";
                String query1="update trains set start_seat=start_seat-"+seats+ " where train_no="+train_no+";";
                c.s.executeUpdate(query1);
                c.s.executeUpdate(query);
                c.s.executeUpdate(query3);
            }catch(Exception error){
                System.out.println(error);
            }
            setVisible(false);
            System.out.println(source+"  "+destination);
            new AddPassengers(details,user_name,date_of_travel).setVisible(true);
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

    public static void main(String[] args) {

        BookedTrain details = new BookedTrain(22626, "Mas Double Decker", "sbc", "csmt", "20:40", "20:15", 360, 30);

        new ConfirmBooking(details,"54654655","shas",30,"bellary","solapur","01-04-2023");
    }
}