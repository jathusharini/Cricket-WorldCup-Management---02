package sample.models;

import com.opencsv.bean.CsvBindByPosition;

public class Player {
    @CsvBindByPosition(position = 0)
    private String name;

    @CsvBindByPosition(position = 2)
    private String role;

    private int runs;
    private int wickets;

    public int getRuns() {
        return runs;
    }

    public void setRuns(int runs) {
        this.runs = runs;
    }

    public int getWickets() {
        return wickets;
    }

    public void setWickets(int wickets) {
        this.wickets = wickets;
    }

    public Player(String name, String role) {
        this.name = name;
        this.role = role;
    }

    public Player() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "Name= " + name  +
                ", role=" + role  +
                ", runs=" + runs +
                ", wickets=" + wickets;
    }
}
