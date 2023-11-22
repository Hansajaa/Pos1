package DB;

import javafx.scene.control.Alert;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DBConnection {
    private static DBConnection database;
    private Statement statement = null;
    private DBConnection() throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection=DriverManager.getConnection("jdbc:mysql://localhost:3306/thogakade","root","Hansaja@1234");
            statement=connection.createStatement();
        } catch (ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR,"Driver Not Found");
        }
    }

    public static DBConnection getInstance() throws SQLException {
        if (database==null){
            database=new DBConnection();
        }
        return database;
    }
    public DBConnection getConnection(){
        return this;
    }

    public Statement getStatement(){
        return statement;
    }
}
