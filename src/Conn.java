import java.sql.*;

public class Conn {
    Connection c;
    Statement s;

    public Conn() {
        try {
            c = DriverManager.getConnection("jdbc:mysql:///irctcproject", "root", "shashi3927");
            s = c.createStatement();

        } catch (Exception e) {
            System.out.println(e);
        }
    }
}