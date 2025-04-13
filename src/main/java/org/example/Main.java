package org.example;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
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