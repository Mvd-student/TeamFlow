package org.example;

import java.time.LocalDate;

public class Sprint {
    private int id;
    private String sprintNaam;
    private LocalDate startDate;
    private LocalDate endDate;

    public Sprint(int id, String sprintNaam, LocalDate startDate, LocalDate endDate) {
        this.id = id;
        this.sprintNaam = sprintNaam;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public int getId() {
        return id;
    }

    public String getSprintNaam() {
        return sprintNaam;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setSprintNaam(String sprintNaam) {
        this.sprintNaam = sprintNaam;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    @Override
    public String toString() {
        return "Sprint{" +
                "id=" + id +
                ", sprintNaam='" + sprintNaam + '\'' +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                '}';
    }
}
