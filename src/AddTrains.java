import javax.swing.*;
import javax.swing.plaf.basic.BasicComboBoxUI;
import java.awt.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import java.awt.event.*;
import java.sql.*;
import java.util.ArrayList;

public class AddTrains  extends JFrame implements ActionListener {
    JPanel panel ;
    JTextField trainNo,trainName,seats,time;
    JComboBox stationId;
    JButton saveTrain,Add, back, delete, submit;
    JTable table = new JTable();
    JScrollPane pane;
    Object[] cols = null;
    DefaultTableModel model;
    String userName;
    String FromStations[];
    String adminId,train_no,train_name,End_seats;
    JRadioButton Mon,Tue,Wed,Thur,Fri,Sat,Sun;
    String Monday="n",Tuesday="n",Wednesday="n",Thursday="n",Friday="n",Saturday="n",Sunday="n";
    static Box vertical = Box.createVerticalBox();
        
    AddTrains(String adminId) {
        this.adminId=adminId;
        setTitle("IRCTC");

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("img/addTrains.png"));
        Image i2 = i1.getImage().getScaledInstance(983, 660, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(0, 0, 983, 660);
        add(image);   

        trainNo=new JTextField("Train No");
        trainNo.setBounds(45,95,200,40);
        trainNo.setFont(new Font("Raleway", Font.PLAIN, 17));
        trainNo.setForeground(Color.gray);
        trainNo.setBorder(null);
        TextAnimator.textAnimator(trainNo,"Train NO");
        image.add(trainNo);

        trainName=new JTextField("Train Name");
        trainName.setBounds(290,95,250,40);
        trainName.setFont(new Font("Raleway", Font.PLAIN, 17));
        trainName.setForeground(Color.gray);
        trainName.setBorder(null);
        TextAnimator.textAnimator(trainName,"Train Name");
        image.add(trainName);

        seats=new JTextField("Seats");
        seats.setBounds(585,95,100,40);
        seats.setFont(new Font("Raleway", Font.PLAIN, 17));
        seats.setForeground(Color.gray);
        seats.setBorder(null);
        TextAnimator.textAnimator(seats,"Seats");
        image.add(seats);

        Mon=new JRadioButton("Mon");
        Mon.setBounds(160,180,60,30);
        Mon.setBackground(Color.white);
        Mon.setFont(new Font("Raleway", Font.PLAIN, 15));
        Mon.setForeground(Color.gray);
        image.add(Mon);

        Tue=new JRadioButton("Tue");
        Tue.setBounds(240,180,60,30);
        Tue.setBackground(Color.white);
        Tue.setFont(new Font("Raleway", Font.PLAIN, 15));
        Tue.setForeground(Color.gray);
        image.add(Tue);

        Wed=new JRadioButton("Wed");
        Wed.setBounds(320,180,60,30);
        Wed.setBackground(Color.white);
        Wed.setFont(new Font("Raleway", Font.PLAIN, 15));
        Wed.setForeground(Color.gray);
        image.add(Wed);

        Thur=new JRadioButton("Thur");
        Thur.setBounds(400,180,60,30);
        Thur.setBackground(Color.white);
        Thur.setFont(new Font("Raleway", Font.PLAIN, 15));
        Thur.setForeground(Color.gray);
        image.add(Thur);

        Fri=new JRadioButton("Fri");
        Fri.setBounds(480,180,60,30);
        Fri.setBackground(Color.white);
        Fri.setFont(new Font("Raleway", Font.PLAIN, 15));
        Fri.setForeground(Color.gray);
        image.add(Fri);

        Sat=new JRadioButton("Sat");
        Sat.setBounds(560,180,60,30);
        Sat.setBackground(Color.white);
        Sat.setFont(new Font("Raleway", Font.PLAIN, 15));
        Sat.setForeground(Color.gray);
        image.add(Sat);

        Sun=new JRadioButton("Sun");
        Sun.setBounds(640,180,60,30);
        Sun.setBackground(Color.white);
        Sun.setFont(new Font("Raleway", Font.PLAIN, 15));
        Sun.setForeground(Color.gray);
        image.add(Sun);

        JLabel dash=new JLabel();
        saveTrain=new JButton("SAVE TRAIN");
        saveTrain.setBounds(760,185,200,30);
        saveTrain.setForeground(Color.decode("#e87020"));
        saveTrain.setBackground(Color.white);
        saveTrain.setFont(new Font("Raleway", Font.BOLD, 24));
        saveTrain.setBorder(null);
        saveTrain.addActionListener(this);
        image.add(saveTrain);

        // stopNo = new JTextField("Stop Number");
        // stopNo.setBounds(45, 303, 150, 30);
        // stopNo.setFont(new Font("Raleway", Font.PLAIN, 17));
        // stopNo.setForeground(Color.gray);
        // stopNo.setBorder(null);
        // TextAnimator.textAnimator(stopNo,"Stop Number");
        // image.add(stopNo);

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

        stationId = new JComboBox(FromStations);
        stationId.setBounds(45, 305, 170, 30);
        stationId.setFont(new Font("Raleway", Font.BOLD, 15));
        stationId.setForeground(Color.black);
        stationId.setBackground(Color.white);
        stationId.setUI(new BasicComboBoxUI() {
            @Override
            protected JButton createArrowButton() {
                JButton button = new JButton();
                button.setContentAreaFilled(false);
                button.setBorder(null);
                return button;
            }
        });
        image.add(stationId);

        time = new JTextField("Time");
        time.setBounds(290, 303, 150, 30);
        time.setFont(new Font("Raleway", Font.PLAIN, 17));
        time.setForeground(Color.gray);
        time.setBorder(null);
        TextAnimator.textAnimator(time,"Time");
        image.add(time);

        // cost = new JTextField("Cost");
        // cost.setBounds(767, 303, 150, 30);
        // cost.setFont(new Font("Raleway", Font.PLAIN, 17));
        // cost.setForeground(Color.gray);
        // cost.setBorder(null);
        // TextAnimator.textAnimator(cost,"Cost");
        // image.add(cost);

        Add = new JButton("ADD");
        Add.setBounds(40, 360, 70, 30);
        Add.setForeground(Color.decode("#e87020"));
        Add.setBackground(Color.white);
        Add.setFont(new Font("Raleway", Font.BOLD, 22));
        Add.setBorder(null);
        Add.addActionListener(this);
        image.add(Add);

        delete = new JButton("DELETE");
        delete.setBounds(170, 360, 100, 30);
        delete.setForeground(Color.decode("#e87020"));
        delete.setBackground(Color.white);
        delete.setFont(new Font("Raleway", Font.BOLD, 22));
        delete.setBorder(null);
        delete.addActionListener(this);
        image.add(delete);

        panel=new JPanel(new BorderLayout());
        panel.setBounds(0,280,1000,360);
        panel.setLayout(null);
        panel.setBackground(Color.white);

        cols = new String[] { "station id", "time"};

        model = (DefaultTableModel) table.getModel();

        model.setColumnIdentifiers(cols);

        table.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        JTableHeader Theader=table.getTableHeader();
        Theader.setBackground(Color.decode("#e87020"));
        Theader.setFont(new Font("Raleway", Font.BOLD, 20));
        table.setFont(new Font("Raleway", Font.PLAIN, 16));

        pane = new JScrollPane(table);
        pane.setBounds(100, 150, 800, 120);
        panel.add(pane,BorderLayout.CENTER);

        submit = new JButton("SUBMIT");
        submit.setBounds(820, 580, 100, 30);
        submit.setForeground(Color.decode("#e87020"));
        submit.setBackground(Color.white);
        submit.setFont(new Font("Raleway", Font.BOLD, 22));
        submit.setBorder(null);
        submit.addActionListener(this);
        image.add(submit);

        back = new JButton("BACK");
        back.setBounds(45, 580, 100, 30);
        back.setForeground(Color.decode("#e87020"));
        back.setBackground(Color.white);
        back.setFont(new Font("Raleway", Font.BOLD, 22));
        back.setBorder(null);
        back.addActionListener(this);
        image.add(back);

        panel.setVisible(false);
        image.add(panel);

        // setLayout(null);
        setLocation(180, 20);
        setSize(1000, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        ArrayList<String> stations=new ArrayList<>();
        try{
            Conn c = new Conn();
            ResultSet rs=c.s.executeQuery("select station_id from stations;");
            while(rs.next()){
                stations.add(rs.getString("station_id"));
            }
        }catch(Exception error){
            System.out.println(error);            
        }
        
        if(e.getSource()==saveTrain){
            if(trainNo.getText().equals("") ||trainNo.getText().equals("Train NO") ||trainName.getText().equals("")||trainName.getText().equals("Train Name")||seats.getText().equals("")||seats.getText().equals("Seats")){
                JOptionPane.showMessageDialog(null,"Please Fill all the details");
            }
            else{
            train_no=trainNo.getText();
            train_name=trainName.getText();
            End_seats=seats.getText();
            if(Mon.isSelected()){
                Monday="y";
            }

            if(Tue.isSelected()){
                Tuesday="y";
            }
            if(Wed.isSelected()){
                Wednesday="y";
            }
            if(Thur.isSelected()){
                Thursday="y";
            }
            if(Fri.isSelected()){
                Friday="y";
            }
            if(Sat.isSelected()){
                Saturday="y";
            }
            if(Sun.isSelected()){
                Sunday="y";
            }
            System.out.println(Monday+Tuesday+Wednesday+Thursday+Friday+Saturday+Sunday);

            JOptionPane.showMessageDialog(null, "train saved successfully");
        }
    }
        else if (e.getSource() == Add) {
            panel.setVisible(true);
            if (stationId.getSelectedItem().equals("") ||  time.getText().equals("") ) {
                JOptionPane.showMessageDialog(null, "Please fill all the details");
            } 
        
                else{
                    model.addRow(new Object[] { stationId.getSelectedItem(),time.getText()});
                }
            }
        else if (e.getSource() == delete) {
            if (table.getSelectedRow() != -1) {
                // remove selected row from the model
                model.removeRow(table.getSelectedRow());
                JOptionPane.showMessageDialog(null, "Selected row deleted successfully");
            }
        } else if (e.getSource() == back) {
            setVisible(false);
            new Admin(adminId);
        } else if (e.getSource() == submit) {
            try {
                int start_seat=0;
                Conn c = new Conn();
                String query1="insert into trains values ('"+Integer.parseInt(train_no)+"','"+train_name+"',"+start_seat+","+Integer.parseInt(End_seats)+",'"+adminId+"');";
                c.s.executeUpdate(query1);
                String query ="insert into schedule values('"+Integer.parseInt(train_no)+"','"+Monday+"','"+Tuesday+"','"+Wednesday+"','"+Thursday+"','"+Friday+"','"+Saturday+"','"+Sunday+"');";
                c.s.executeUpdate(query);
                int rows = table.getRowCount();
                System.out.println(rows);
                for (int row = 0; row < rows; row++) {
                    String stationId= "";
                    try{
                        // Conn c=new Conn();
                    ResultSet rs=c.s.executeQuery("select station_id from stations where station_name='"+(String)table.getValueAt(row, 0)+"';");
                if(rs.next()){
                    stationId=rs.getString("station_id");
                    System.out.println(stationId);
                }
                    }catch(Exception error){
                        System.out.println(error);
                    }
                    String time= (String) table.getValueAt(row, 1);
                    // String cost = (String) table.getValueAt(row, 3);
                    String query2= "create table  if not exists `"+train_no+"`("+
                        "train_no varchar(10),"+
                        "stop_no integer auto_increment primary key,"+
                        "station_id varchar(10),"+
                        "arrival_time varchar(20),"+
                        "cost integer,"+
                        "foreign key (station_id) references stations(station_id),"+
                        "foreign key (train_no) references trains(train_no)"+
                        ");";
                    String query3="drop trigger if exists cost;";
                    String query4="""
                        create trigger cost
                        before INSERT 
                        on """+
                        " `"+train_no+"` "+"""
                        for each row 
                            BEGIN"""+
"\nIF (select count(*) from `"+train_no+"`)=0 THEN"+"""
                                    set new.cost=0;
                                ELSE 
                                    BEGIN
                                        declare id_val int default 0;
                                        SELECT LAST_INSERT_ID() into id_val;
                                        set new.cost = id_val*10;
                                    END;
                                END IF;
                            END;
                                """;
                    String query5= "Insert into `"+train_no+"`(train_no,station_id,arrival_time) values ('"+train_no+"','"+stationId+"','"+time+"');";
                    System.out.println(query2);
                    System.out.println(query3);
                    System.out.println(query4);
                    System.out.println(query5);
                    c.s.executeUpdate(query2);
                    c.s.executeUpdate(query3);
                    c.s.execute(query4);
                    c.s.executeUpdate(query5);
                    }
                
                    JOptionPane.showMessageDialog(null, "train added successfully");
                    setVisible(false);
                    new Admin(adminId);

            } catch (Exception error) {
                error.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        new AddTrains("shashidhar");
    }    
}