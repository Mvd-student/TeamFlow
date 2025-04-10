package org.example;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Message extends Userstory {
    private int id;
    private String content;
    private LocalDate dateTime;
    private int userId;   // Foreign key to User
    private int chatId;   // Foreign key to Chat

    private final List<String> messages = new ArrayList<>();

    public Message(
            int id,
            String content,
            LocalDate dateTime,
            int userId,
            int chatId,
            int storyId,
            String title,
            String description,
            int epicId,
            int sprintId
    ){
        super(storyId, title, description, epicId, sprintId);
        this.id = id;
        this.content = content;
        this.dateTime = dateTime;
        this.userId = userId;
        this.chatId = chatId;
    }


    public void testProcess(String input) {
        messages.add(input);
        System.out.println("Verwerkte string: " + input.toUpperCase());
    }

    public List<String> getMessages() {
        return messages;
    }

    public int getId() {
        return id;
    }

    public String getContent() {
        return content;
    }

    public LocalDate getDateTime() {
        return dateTime;
    }

    public int getUserId() {
        return userId;
    }

    public int getChatId() {
        return chatId;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setDateTime(LocalDate dateTime) {
        this.dateTime = dateTime;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void setChatId(int chatId) {
        this.chatId = chatId;
    }

    @Override
    public String toString() {
        return "Message{" +
                "id=" + id +
                ", content='" + content + '\'' +
                ", dateTime=" + dateTime +
                ", userId=" + userId +
                ", chatId=" + chatId +
                '}';
    }
}
