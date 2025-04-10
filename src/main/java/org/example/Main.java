package org.example;
import org.example.databaseConnection;
import org.example.User;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

        ArrayList<User> users = new ArrayList<User>();



        databaseConnection DB_Connection = new databaseConnection();
        Connection connection = DB_Connection.getConnection();

        String query = "SELECT * FROM user";

        try{
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()){
                int id = resultSet.getInt("Id");
                String name = resultSet.getString("Name");
                String userName = resultSet.getString("Username");
                String password = resultSet.getString("Password");

                new User(id, name, userName, password);
                users.add(new User(id, userName, name, password));

            }
            resultSet.close();
            statement.close();
            connection.close();
        }
        catch (SQLException e){
            e.printStackTrace();
        }

        System.out.println("What do you want to do?");

            Scanner scanner = new Scanner(System.in);

            String input = scanner.nextLine();

            switch (input) {
                case "show users":
                    for(User user : users){
                        System.out.println("Id: " + user.getId());
                        System.out.println("Name: " + user.getName());
                        System.out.println("Username: " + user.getUsername());
                        System.out.println("Password: " + user.getPassword());
                        System.out.println();
                    }
                    break;
            }
    }
}