package org.example;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class databaseConnection {
    public Connection databaseLink;
    String url = "jdbc:mysql://127.0.0.1:3306/testdb";
    String user = "testDbUser";
    String password = "test123";

    public Connection getConnection() {
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            databaseLink = DriverManager.getConnection(url, user, password);
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return databaseLink;
    }
}
