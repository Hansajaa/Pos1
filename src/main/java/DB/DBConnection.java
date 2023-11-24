package DB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DBConnection {
    private static DBConnection database;
    private Connection connection;
    private DBConnection() throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/thogakade", "root", "Hansaja@1234");
    }

    public static DBConnection getInstance() throws SQLException, ClassNotFoundException {
        return database!=null ? database:(database=new DBConnection());
    }
    public Connection getConnection(){
        return connection;
    }
}
