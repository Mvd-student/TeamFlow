package org.example;

public class Chat {
    private int id;
    private int userstoryId;  // Een FK to Userstory
    private int sprintId;     // Een FK to Sprint

    public Chat(int id, int userstoryId, int sprintId) {
        this.id = id;
        this.userstoryId = userstoryId;
        this.sprintId = sprintId;
    }

    public int getId() {
        return id;
    }

    public int getUserstoryId() {
        return userstoryId;
    }

    public int getSprintId() {
        return sprintId;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setUserstoryId(int userstoryId) {
        this.userstoryId = userstoryId;
    }

    public void setSprintId(int sprintId) {
        this.sprintId = sprintId;
    }

    @Override
    public String toString() {
        return "Chat{" +
                "id=" + id +
                ", userstoryId=" + userstoryId +
                ", sprintId=" + sprintId +
                '}';
    }
}
