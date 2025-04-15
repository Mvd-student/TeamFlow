package org.example;
import org.example.databaseConnection;
import org.example.User;

import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
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



        ArrayList<Userstory> userstories = new ArrayList<Userstory>();
        loadUserstories(userstories);

        ArrayList<Chat> chats = new ArrayList<Chat>();



        boolean loggedIn = false;

        while (CurrentUser.getLoggedInUser() == null) {
            login(users);
        }


        boolean usingApp = true;
        System.out.println("Type \"help\" to see a list of all functions (The console is not case sensitive)");

        while(usingApp){
            System.out.println("What do you want to do?");

            Scanner scanner = new Scanner(System.in);

            String input = scanner.nextLine();

            switch (input.toLowerCase()) {
                case "help":
                    clearTerminal();
                    System.out.println("-> Show Users");
                    System.out.println("-> show epics");
                    System.out.println("-> show sprints");
                    System.out.println("-> show userstories");
                    System.out.println("-> Close app");
                    System.out.println();
                    break;
                case "current user":
                    clearTerminal();
                    System.out.println("Current user id:" + CurrentUser.getLoggedInUser().getId());
                    System.out.println("Current user name:" + CurrentUser.getLoggedInUser().getUsername());
                    System.out.println("Current user username:" + CurrentUser.getLoggedInUser().getUsername());
                    System.out.println("Current user password:" + CurrentUser.getLoggedInUser().getPassword());
                case "show users":
                    clearTerminal();
                    for(User user : users){
                        System.out.println("Id: " + user.getId());
                        System.out.println("Name: " + user.getName());
                        System.out.println("Username: " + user.getUsername());
                        System.out.println("Password: " + user.getPassword());
                        System.out.println();
                    }
                    break;
                    case "show epics":
                        clearTerminal();
                        for(Epic epic : epics){
                            System.out.println("Id: " + epic.getId());
                            System.out.println("Name: " + epic.getEpicNaam());
                            System.out.println("End date: " + epic.getEndDate());
                            System.out.println();
                        }
                        break;
                case "show userstories":
                    clearTerminal();
                    for(Userstory userstory : userstories){
                        System.out.println("Id: " + userstory.getId());
                        System.out.println("Title: " + userstory.getTitle());
                        System.out.println("Description: " + userstory.getDescription());
                        System.out.println("Epic id: " + userstory.getEpicId());
                        System.out.println("Sprint id: " + userstory.getSprintId());
                        System.out.println();
                    }
                    break;
                case "show sprints":
                    clearTerminal();
                    for(Sprint sprint : sprints){
                        System.out.println("Id: " + sprint.getId());
                        System.out.println("Name: " + sprint.getSprintNaam());
                        System.out.println("Start date: " + sprint.getStartDate());
                        System.out.println("End date: " + sprint.getEndDate());
                        System.out.println();
                    }
                    break;
                case "show chats by userstory":
                    clearTerminal();
                    showChatsByUserstory();
                    break;

                case "close app":
                    usingApp = false;
                    break;
            }
        }


    }

    public static void login(ArrayList<User> users) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter your username: ");
        String username = scanner.nextLine();

        System.out.println("Please enter your password: ");
        String password = scanner.nextLine();

        boolean loggedIn = false;

        for(User user : users){
            if(user.getUsername().equals(username) && user.getPassword().equals(password)){
                CurrentUser.setLoggedInUser(user);
                loggedIn = true;
                System.out.println("Welcome " + CurrentUser.getLoggedInUser().getUsername() + " (" + CurrentUser.getLoggedInUser().getName()+ ")");
            }
        }

        if(!loggedIn){
            clearTerminal();
            System.out.println("Incorrect username or password, please try again.");
            System.out.println();
        }
    }

    public static void clearTerminal() {
        for (int i = 0; i < 100; i++) {
            System.out.println();
        }
    }



    public static void showChatsByUserstory() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter Userstory ID: ");
        int userstoryId = scanner.nextInt();

        databaseConnection DB_Connection = new databaseConnection();
        Connection connection = DB_Connection.getConnection();

        String query = "SELECT c.id, c.name FROM chat c WHERE c.userstoryId = ?";

        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, userstoryId);
            ResultSet resultSet = statement.executeQuery();

            boolean found = false;

            System.out.println("Chats with this userstory ID " + userstoryId + ":");
            while (resultSet.next()) {
                found = true;
                int chatId = resultSet.getInt("id");
                String chatName = resultSet.getString("name");
                System.out.println("Chat ID: " + chatId + " | name: " + chatName);
            }

            if (!found) {
                System.out.println("No chats found with this userstory ID.");
            }

            resultSet.close();
            statement.close();
            connection.close();

        } catch (SQLException e) {
            System.out.println("Something went wrong.");
            e.printStackTrace();
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


    public static void loadUserstories(ArrayList<Userstory> userstories) {
        databaseConnection DB_Connection = new databaseConnection();
        Connection connection = DB_Connection.getConnection();

        String query = "SELECT * FROM userstory";

        try{
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()){
                int id = resultSet.getInt("Id");
                String title = resultSet.getString("Title");
                String description = resultSet.getString("Description");
                int epicId = resultSet.getInt("Epic_id");
                int SprintId = resultSet.getInt("Sprint_id");

                new Userstory(id, title, description, epicId, SprintId);
                userstories.add(new Userstory(id, title, description, epicId, SprintId));

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