package org.example;

public class Task {
    private int id;
    private String name;
    private String status;
    private int userId;         // Een FK to User
    private int userstoryId;    // Een FK to Userstory

    public Task(int id, String name, String status, int userId, int userstoryId) {
        this.id = id;
        this.name = name;
        this.status = status;
        this.userId = userId;
        this.userstoryId = userstoryId;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getStatus() {
        return status;
    }

    public int getUserId() {
        return userId;
    }

    public int getUserstoryId() {
        return userstoryId;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void setUserstoryId(int userstoryId) {
        this.userstoryId = userstoryId;
    }

    @Override
    public String toString() {
        return "Task{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", status='" + status + '\'' +
                ", userId=" + userId +
                ", userstoryId=" + userstoryId +
                '}';
    }
}
