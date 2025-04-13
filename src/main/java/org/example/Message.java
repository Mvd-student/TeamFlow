package org.example;

import java.time.LocalDate;

public class Message {
    private int id;
    private String content;
    private LocalDate dateTime;
    private int userId;   // Foreign key to User
    private int chatId;   // Foreign key to Chat

    public Message(int id, String content, LocalDate dateTime, int userId, int chatId) {
        this.id = id;
        this.content = content;
        this.dateTime = LocalDate.now();
        this.userId = userId;
        this.chatId = chatId;
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
