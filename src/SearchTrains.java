import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.plaf.basic.BasicComboBoxUI;

import java.awt.*;
import java.awt.event.*;
import java.time.*;
import java.util.Date;
import com.toedter.calendar.JDateChooser;
import java.sql.*;
import java.text.DateFormat;

public class SearchTrains extends JFrame implements ActionListener{
    String FromStations[];
    JComboBox From, To;
    JButton Search,back;
    JDateChooser dateChooser;
    String user_name;

    SearchTrains(String user_name) {
        this.user_name=user_name;
        setTitle("IRCTC");
        setLayout(null);

        //Main frame image
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("img/searchtrain.png"));
        Image i2 = i1.getImage().getScaledInstance(983, 660, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(0, 0, 983, 660);
        add(image);

        //username text feild
        // String fromstations[]={"bay","chi","del","dev","ham","kdy","ken","ksr","sol","ypr"};
        
        try{
        Conn c=new Conn();
        int count=0;
        ResultSet rs=c.s.executeQuery("select count(station_id) as count from stations");
        if(rs.next()){
            count=rs.getInt("count");
            System.out.println(count);
            FromStations=new String[count];
            ResultSet rsd=c.s.executeQuery("select station_name from stations");

            for(int i=0;i<count&&rsd.next();i++){
                FromStations[i]=rsd.getString("station_name").toUpperCase();
                // System.out.println(FromStations[i]);
            }
        }}catch(Exception error){
            error.printStackTrace();
        }

        From = new JComboBox(FromStations);
        From.setBounds(107, 175, 230, 30);
        From.setFont(new Font("Raleway", Font.BOLD, 15));
        From.setForeground(Color.black);
        From.setBackground(Color.white);
        DefaultListCellRenderer  listRenderer = new DefaultListCellRenderer();
        listRenderer.setHorizontalAlignment(DefaultListCellRenderer.CENTER); // center-aligned items
        From.setRenderer(listRenderer);
        From.setUI(new BasicComboBoxUI() {
            @Override
            protected JButton createArrowButton() {
                JButton button = new JButton();
                button.setContentAreaFilled(false);
                button.setBorder(null);
                return button;
            }
        });
        image.add(From);

        To = new JComboBox(FromStations);
        To.setBounds(650, 175, 230, 30);
        To.setFont(new Font("Raleway", Font.BOLD, 15));
        listRenderer = new DefaultListCellRenderer();
        listRenderer.setHorizontalAlignment(DefaultListCellRenderer.CENTER); // center-aligned items
        To.setRenderer(listRenderer);
        To.setForeground(Color.black);
        To.setBackground(Color.white);
        To.setUI(new BasicComboBoxUI() {
            @Override
            protected JButton createArrowButton() {
                JButton button = new JButton();
                button.setContentAreaFilled(false);
                button.setBorder(null);
                return button;
            }
        });
        image.add(To);

        dateChooser=new JDateChooser();
        dateChooser.setBounds(380,252,230,30);
        dateChooser.setForeground(new Color(105,105,105));
        image.add(dateChooser);

        
        Search = new JButton("SEARCH TRAINS");
        Search.setBounds(663, 447, 216, 30);
        Search.setForeground(Color.decode("#e87020"));
        Search.setBackground(Color.white);
        Search.setFont(new Font("Raleway", Font.BOLD, 24));
        Search.setBorder(null);
        Search.addActionListener(this);
        image.add(Search);

        back=new JButton("BACK");
        back.setBounds(105, 453, 73, 30);
        back.setForeground(Color.decode("#e87020"));
        back.setBackground(Color.white);
        back.setFont(new Font("Raleway", Font.BOLD, 24));
        back.setBorder(null);
        back.addActionListener(this);
        image.add(back);

        getContentPane().setBackground(Color.white);

        setSize(1000, 700);
        setVisible(true);
        setLocation(180, 20);
    }

    public void actionPerformed(ActionEvent e) {

        try{
        //check for login
        if(e.getSource()==Search){
            //check if all the details are entered
            if(From.getSelectedItem().equals("") || To.getSelectedItem().equals("") ||getDay(dateChooser.getDate())==""){
                JOptionPane.showMessageDialog(null,"Please fill all the details");  
            }
            else{
                String source="";
                String destination="";
                try{
                    Conn c=new Conn();
                    ResultSet rs=c.s.executeQuery("select station_id from stations where station_name='"+(String)From.getSelectedItem()+"';");
                if(rs.next()){
                    source=rs.getString("station_id");
                    System.out.println(source);
                }
                rs=c.s.executeQuery("select station_id from stations where station_name='"+(String)To.getSelectedItem()+"';");
                if(rs.next()){
                    destination=rs.getString("station_id");
                    System.out.println(destination);
                }
                }catch(Exception error){
                    error.printStackTrace();
                }
                String day=getDay(dateChooser.getDate());
                Date date = dateChooser.getDate();
                String date_of_travel = DateFormat.getDateInstance().format(date);
                System.out.println(date_of_travel);
                setVisible(false);
                new BookTickets(source, destination,day,user_name,date_of_travel).setVisible(true);
                // System.out.println(source);
            }
        }
        else if(e.getSource()==back){
            setVisible(false);
            new HomePage(user_name).setVisible(true);
        } 
    }catch(Exception error){
        JOptionPane.showMessageDialog(null,"Please fill all the details");  
    }
    }

    public static String getDay(Date d){
        LocalDateTime ldt=d.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
        DayOfWeek dow=ldt.getDayOfWeek();
        return dow.name();
    }
    public static void main(String args[]) {
        new SearchTrains("shas");
    }
}