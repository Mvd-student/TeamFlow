package org.example;
import org.example.databaseConnection;
import org.example.User;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.sql.Date;
import java.util.Scanner;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        ArrayList<User> users = new ArrayList<User>();
        loadUsers(users);

        ArrayList<Epic> epics = new ArrayList<Epic>();
        loadEpics(epics);

        ArrayList<Sprint> sprints = new ArrayList<Sprint>();
        loadSprints(sprints);





        boolean usingApp = true;
        System.out.println("Type \"help\" to see a list of all functions (The console is not case sensitive)");

        while(usingApp){
            System.out.println("What do you want to do?");

            Scanner scanner = new Scanner(System.in);

            String input = scanner.nextLine();

            switch (input.toLowerCase()) {
                case "help":
                    System.out.println("-> Show Users");
                    System.out.println("-> show epics");
                    System.out.println("-> show sprints");
                    System.out.println("-> Close app");
                    System.out.println();
                    break;
                case "show users":
                    for(User user : users){
                        System.out.println("Id: " + user.getId());
                        System.out.println("Name: " + user.getName());
                        System.out.println("Username: " + user.getUsername());
                        System.out.println("Password: " + user.getPassword());
                        System.out.println();
                    }
                    break;
                    case "show epics":
                        for(Epic epic : epics){
                            System.out.println("Id: " + epic.getId());
                            System.out.println("Name: " + epic.getEpicNaam());
                            System.out.println("End date: " + epic.getEndDate());
                            System.out.println();
                        }
                        break;
                case "show sprints":
                    for(Sprint sprint : sprints){
                        System.out.println("Id: " + sprint.getId());
                        System.out.println("Name: " + sprint.getSprintNaam());
                        System.out.println("Start date: " + sprint.getStartDate());
                        System.out.println("End date: " + sprint.getEndDate());
                        System.out.println();
                    }
                    break;
                case "close app":
                    usingApp = false;
                    break;
            }
        }


    }

    public static void loadUsers(ArrayList<User> users) {
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

    }

    public static void loadEpics(ArrayList<Epic> epics) {
        databaseConnection DB_Connection = new databaseConnection();
        Connection connection = DB_Connection.getConnection();

        String query = "SELECT * FROM epic";

        try{
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()){
                int id = resultSet.getInt("Id");
                String epicName = resultSet.getString("Epic_naam");
                Date endDate = resultSet.getDate("End_date");

                LocalDate localDate = endDate.toLocalDate();

                new Epic(id, epicName, localDate);
                epics.add(new Epic(id, epicName, localDate));

            }
            resultSet.close();
            statement.close();
            connection.close();
        }
        catch (SQLException e){
            e.printStackTrace();
        }

    }

    public static void loadSprints(ArrayList<Sprint> sprints) {
        databaseConnection DB_Connection = new databaseConnection();
        Connection connection = DB_Connection.getConnection();

        String query = "SELECT * FROM Sprint";

        try{
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()){
                int id = resultSet.getInt("Id");
                String sprintName = resultSet.getString("Sprint_naam");
                Date startDate = resultSet.getDate("Start_date");
                Date endDate = resultSet.getDate("End_date");

                LocalDate localDateStartDate = startDate.toLocalDate();
                LocalDate localDateEndDate = endDate.toLocalDate();

                new Sprint(id, sprintName, localDateStartDate, localDateEndDate);
                sprints.add(new Sprint(id, sprintName, localDateStartDate, localDateEndDate));

            }
            resultSet.close();
            statement.close();
            connection.close();
        }
        catch (SQLException e){
            e.printStackTrace();
        }

    }





    public static void printMessages(List<Message> messages, int currentUserId, java.util.Map<Integer, String> userIdToUsername) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");

        for (Message msg : messages) {
            String tijd = msg.getDateTime().format(formatter);
            String afzender;

            if (msg.getUserId() == currentUserId) {
                afzender = "Jij";
            } else {
                afzender = userIdToUsername.getOrDefault(msg.getUserId(), "Onbekend");
            }

            System.out.println("[" + tijd + "] " + afzender + ": " + msg.getContent());
        }
    }

}