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
        loadChats(chats);

        ArrayList<Message> messages = new ArrayList<Message>();
        loadMessages(messages);



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
                    System.out.println("-> Show Chatlist");
                    System.out.println("-> Insert Chat");
                    System.out.println("-> Show Epics");
                    System.out.println("-> Insert Epic");
                    System.out.println("-> Show Sprints");
                    System.out.println("-> Show Userstories");
                    System.out.println("-> Insert Userstory");
                    System.out.println("-> Show All Messages");
                    System.out.println("-> Show Chat");
                    System.out.println("-> Send Message");
                    System.out.println("-> Current User");
                    System.out.println("-> Close App");
                    System.out.println();
                    break;
                    case "show chat":
                        for(Chat chat : chats){
                            System.out.println("Chat id: " + chat.getId());
                            for(Userstory userstory : userstories){
                                if(userstory.getId() == chat.getUserstoryId()){
                                    System.out.println("Userstory title: " + userstory.getTitle());
                                }
                            }
                        }
                        System.out.println("What chat do you want to open? (Enter chat ID)");
                        int chatIDHolder = scanner.nextInt();
                        for(Message message : messages){
                            if(message.getChatId() == chatIDHolder){
                                for(User user : users){
                                    if(user.getId() == message.getUserId()){
                                        System.out.println("User: " + user.getName());
                                    }
                                    else{}
                                }
                                    System.out.println("Message id: " + message.getId());
                                    System.out.println("Chat id: " + message.getChatId());
                                    System.out.println("Date send: " + message.getDateTime());
                                    System.out.println("Content: " + message.getContent());
                                    System.out.println();
                            }
                        }
                        break;
                case "send message":
                    insertMessage(chats, userstories);
                    break;
                case "current user":
                    clearTerminal();
                    System.out.println("Current user id:" + CurrentUser.getLoggedInUser().getId());
                    System.out.println("Current user name:" + CurrentUser.getLoggedInUser().getUsername());
                    System.out.println("Current user username:" + CurrentUser.getLoggedInUser().getUsername());
                    System.out.println("Current user password:" + CurrentUser.getLoggedInUser().getPassword());
                    break;
                case "show all messages":
                    clearTerminal();
                    for(Message message : messages){
                        for(User user : users){
                            if(user.getId() == message.getUserId()){
                                System.out.println("User: " + user.getName());
                            }
                            else{}
                        }
                        System.out.println("Message id: " + message.getId());
                        System.out.println("Chat id: " + message.getChatId());
                        System.out.println("Date send: " + message.getDateTime());
                        System.out.println("Content: " + message.getContent());
                        System.out.println();
                    }
                    break;
                case "show chatlist":
                    clearTerminal();
                    for(Chat chat : chats){
                        System.out.println("Chat id: " + chat.getId());
                        System.out.println("Userstory id: " + chat.getUserstoryId());
                        System.out.println("Sprint id: " + chat.getSprintId());
                        System.out.println();
                    }
                    break;
                case "insert chat":
                    insertIntoChat(userstories, sprints);
                    break;
                case "show users":
                    clearTerminal();
                    for(User user : users){
                        System.out.println("User id: " + user.getId());
                        System.out.println("Name: " + user.getName());
                        System.out.println("Username: " + user.getUsername());
                        System.out.println("Password: " + user.getPassword());
                        System.out.println();
                    }
                    break;
                    case "show epics":
                        clearTerminal();
                        for(Epic epic : epics){
                            System.out.println("Epic id: " + epic.getId());
                            System.out.println("Name: " + epic.getEpicNaam());
                            System.out.println("End date: " + epic.getEndDate());
                            System.out.println();
                        }
                        break;
                case "insert epic":
                    insertIntoEpic();
                    break;
                case "show userstories":
                    clearTerminal();
                    for(Userstory userstory : userstories){
                        System.out.println("Userstory id: " + userstory.getId());
                        System.out.println("Title: " + userstory.getTitle());
                        System.out.println("Description: " + userstory.getDescription());
                        System.out.println("Epic id: " + userstory.getEpicId());
                        System.out.println("Sprint id: " + userstory.getSprintId());
                        System.out.println();
                    }
                    break;
                case "insert userstory":
                    insertIntoUserstory(epics, sprints);
                    break;
                case "show sprints":
                    clearTerminal();
                    for(Sprint sprint : sprints){
                        System.out.println("Sprint id: " + sprint.getId());
                        System.out.println("Name: " + sprint.getSprintNaam());
                        System.out.println("Start date: " + sprint.getStartDate());
                        System.out.println("End date: " + sprint.getEndDate());
                        System.out.println();
                    }
                    break;
                case "insert sprint":
                    insertIntoSprint();
                    break;
                case "close app":
                    usingApp = false;
                    break;
            }
        }


    }

    public static void insertMessage(ArrayList<Chat> chats, ArrayList<Userstory> userstories) {
        databaseConnection DB_Connection = new databaseConnection();
        Connection connection = DB_Connection.getConnection();
        Scanner scanner = new Scanner(System.in);

        try {
            // Display available chats
            for (Chat chat : chats) {
                System.out.println("Chat id: " + chat.getId());
                for (Userstory userstory : userstories) {
                    if (userstory.getId() == chat.getUserstoryId()) {
                        System.out.println("Userstory title: " + userstory.getTitle());
                    }
                }
            }
            System.out.println();
            System.out.println("In which chat do you want to send a message? (give chat Id):");
            int chatId = scanner.nextInt();
            scanner.nextLine(); // Consume leftover newline

            System.out.println("Enter your message:");
            String content = scanner.nextLine();

            java.sql.Timestamp timestamp = new java.sql.Timestamp(System.currentTimeMillis()); // Current timestamp

            String query = "INSERT INTO message (Chat_id, User_id, Content, DateTime) VALUES (?, ?, ?, ?)";

            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, chatId);
            preparedStatement.setInt(2, CurrentUser.getLoggedInUser().getId());
            preparedStatement.setString(3, content);
            preparedStatement.setTimestamp(4, timestamp);

            int rowsInserted = preparedStatement.executeUpdate();

            if (rowsInserted > 0) {
                System.out.println("Message inserted successfully!");
            }

            preparedStatement.close();
            connection.close();
        } catch (SQLException e) {
            System.out.println("SQL Error:");
            e.printStackTrace();
        } catch (Exception e) {
            System.out.println("Input or parsing error:");
            e.printStackTrace();
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

    public static void loadChats(ArrayList<Chat> chats) {
        databaseConnection DB_Connection = new databaseConnection();
        Connection connection = DB_Connection.getConnection();

        String query = "SELECT * FROM chat";

        try{
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()){
                int id = resultSet.getInt("Id");
                int userstoryId = resultSet.getInt("Userstory_Id");
                int sprintId = resultSet.getInt("Sprint_Id");

                new Chat(id, userstoryId, sprintId);
                chats.add(new Chat(id, userstoryId, sprintId));

            }
            resultSet.close();
            statement.close();
            connection.close();
        }
        catch (SQLException e){
            e.printStackTrace();
        }
    }

    public static void insertIntoChat(ArrayList<Userstory> userstories, ArrayList<Sprint> sprints) {
        databaseConnection DB_Connection = new databaseConnection();
        Connection connection = DB_Connection.getConnection();
        Scanner scanner = new Scanner(System.in);

        try {
            // Show existing Userstories
            System.out.println("Available Userstories:");
            for (Userstory userstory : userstories) {
                System.out.println("Userstory id: " + userstory.getId() + " | Title: " + userstory.getTitle());
            }
            System.out.println();

            System.out.println("Please enter Userstory ID (must exist in Userstory table):");
            int userstoryId = Integer.parseInt(scanner.nextLine());

            // Show existing Sprints
            System.out.println("Available Sprints:");
            for (Sprint sprint : sprints) {
                System.out.println("Sprint id: " + sprint.getId() + " | Name: " + sprint.getSprintNaam());
            }
            System.out.println();

            System.out.println("Please enter Sprint ID (must exist in Sprint table):");
            int sprintId = Integer.parseInt(scanner.nextLine());

            // Insert into database
            String query = "INSERT INTO chat (Userstory_Id, Sprint_Id) VALUES (?, ?)";

            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, userstoryId);
            preparedStatement.setInt(2, sprintId);

            int rowsInserted = preparedStatement.executeUpdate();

            if (rowsInserted > 0) {
                System.out.println("Chat inserted successfully!");
            }

            preparedStatement.close();
            connection.close();
        } catch (SQLException e) {
            System.out.println("SQL Error:");
            e.printStackTrace();
        } catch (Exception e) {
            System.out.println("Input or parsing error:");
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


    public static void insertIntoUserstory(ArrayList<Epic> epics, ArrayList<Sprint> sprints) {
        databaseConnection DB_Connection = new databaseConnection();
        Connection connection = DB_Connection.getConnection();
        Scanner scanner = new Scanner(System.in);

        try {
            System.out.println("Please enter Userstory title:");
            String title = scanner.nextLine();

            System.out.println("Please enter Userstory description:");
            String description = scanner.nextLine();

            for(Epic epic : epics){
                System.out.println("Epic id: " + epic.getId());
                System.out.println("Name: " + epic.getEpicNaam());
                System.out.println();
            }
            System.out.println();
            System.out.println("Please enter Epic ID (must exist in Epic table):");
            int epicId = Integer.parseInt(scanner.nextLine());

            for(Sprint sprint : sprints){
                System.out.println("Sprint id: " + sprint.getId());
                System.out.println("Name: " + sprint.getSprintNaam());
                System.out.println();
            }
            System.out.println();
            System.out.println("Please enter Sprint ID (must exist in Sprint table):");
            int sprintId = Integer.parseInt(scanner.nextLine());

            String query = "INSERT INTO userstory (Title, Description, Epic_id, Sprint_id) VALUES (?, ?, ?, ?)";

            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, title);
            preparedStatement.setString(2, description);
            preparedStatement.setInt(3, epicId);
            preparedStatement.setInt(4, sprintId);

            int rowsInserted = preparedStatement.executeUpdate();

            if (rowsInserted > 0) {
                System.out.println("Userstory inserted successfully!");
            }

            preparedStatement.close();
            connection.close();
        } catch (SQLException e) {
            System.out.println("SQL Error:");
            e.printStackTrace();
        } catch (Exception e) {
            System.out.println("Input or parsing error:");
            e.printStackTrace();
        }
    }


    public static void insertIntoEpic() {
        databaseConnection DB_Connection = new databaseConnection();
        Connection connection = DB_Connection.getConnection();
        Scanner scanner = new Scanner(System.in);

        try {
            System.out.println("Please enter Epic name:");
            String epicName = scanner.nextLine();

            System.out.println("Please enter Epic end date (YYYY-MM-DD):");
            String endDateStr = scanner.nextLine();

            // Parse the string into LocalDate and then convert to SQL Date
            LocalDate endDate = LocalDate.parse(endDateStr);
            Date sqlEndDate = Date.valueOf(endDate);

            String query = "INSERT INTO epic (Epic_naam, End_date) VALUES (?, ?)";

            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, epicName);
            preparedStatement.setDate(2, sqlEndDate);

            int rowsInserted = preparedStatement.executeUpdate();

            if (rowsInserted > 0) {
                System.out.println("Epic inserted successfully!");
            }

            preparedStatement.close();
            connection.close();
        } catch (SQLException e) {
            System.out.println("SQL Error:");
            e.printStackTrace();
        } catch (Exception e) {
            System.out.println("Input or parsing error:");
            e.printStackTrace();
        }
    }

    public static void loadMessages(ArrayList<Message> messages) {
        databaseConnection DB_Connection = new databaseConnection();
        Connection connection = DB_Connection.getConnection();

        String query = "SELECT * FROM message";

        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                int id = resultSet.getInt("Id");
                int chatId = resultSet.getInt("Chat_id");
                int userId = resultSet.getInt("User_id");
                String content = resultSet.getString("Content");
                Timestamp timestamp = resultSet.getTimestamp("DateTime");

                LocalDate date = timestamp.toLocalDateTime().toLocalDate();  // <-- Correct type

                Message message = new Message(id, content, date, userId, chatId);
                messages.add(message);
            }

            resultSet.close();
            statement.close();
            connection.close();
        } catch (SQLException e) {
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
    public static void insertIntoSprint() {
        databaseConnection DB_Connection = new databaseConnection();
        Connection connection = DB_Connection.getConnection();
        Scanner scanner = new Scanner(System.in);

        try {
            System.out.println("Please enter Sprint name:");
            String sprintName = scanner.nextLine();

            System.out.println("Please enter Start date (YYYY-MM-DD):");
            String startDateStr = scanner.nextLine();

            System.out.println("Please enter End date (YYYY-MM-DD):");
            String endDateStr = scanner.nextLine();

            // Parse the strings into LocalDate and then to SQL Date
            LocalDate startDate = LocalDate.parse(startDateStr);
            LocalDate endDate = LocalDate.parse(endDateStr);

            Date sqlStartDate = Date.valueOf(startDate);
            Date sqlEndDate = Date.valueOf(endDate);

            String query = "INSERT INTO Sprint (Sprint_naam, Start_date, End_date) VALUES (?, ?, ?)";

            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, sprintName);
            preparedStatement.setDate(2, sqlStartDate);
            preparedStatement.setDate(3, sqlEndDate);

            int rowsInserted = preparedStatement.executeUpdate();

            if (rowsInserted > 0) {
                System.out.println("Sprint inserted successfully!");
            }

            preparedStatement.close();
            connection.close();
        } catch (SQLException e) {
            System.out.println("SQL Error:");
            e.printStackTrace();
        } catch (Exception e) {
            System.out.println("Input or parsing error:");
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