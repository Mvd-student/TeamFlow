package org.example;

import java.time.LocalDate;

public class Epic {
    private int id;
    private String epicNaam;
    private LocalDate endDate;

    public Epic(int id, String epicNaam, LocalDate endDate) {
        this.id = id;
        this.epicNaam = epicNaam;
        this.endDate = endDate;
    }

    public int getId() {
        return id;
    }

    public String getEpicNaam() {
        return epicNaam;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setEpicNaam(String epicNaam) {
        this.epicNaam = epicNaam;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    @Override
    public String toString() {
        return "Epic{" +
                "id=" + id +
                ", epicNaam='" + epicNaam + '\'' +
                ", endDate=" + endDate +
                '}';
    }
}
