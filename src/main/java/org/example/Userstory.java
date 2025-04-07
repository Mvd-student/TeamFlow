package org.example;

public class Userstory {
    private int id;
    private String title;
    private String description;
    private int epicId;    // Een FK to Epic
    private int sprintId;  // Een FK to Sprint

    public Userstory(int id, String title, String description, int epicId, int sprintId) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.epicId = epicId;
        this.sprintId = sprintId;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public int getEpicId() {
        return epicId;
    }

    public int getSprintId() {
        return sprintId;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setEpicId(int epicId) {
        this.epicId = epicId;
    }

    public void setSprintId(int sprintId) {
        this.sprintId = sprintId;
    }

    @Override
    public String toString() {
        return "Userstory{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", epicId=" + epicId +
                ", sprintId=" + sprintId +
                '}';
    }
}
