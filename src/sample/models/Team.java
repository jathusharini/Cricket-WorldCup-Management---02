package sample.models;

import java.util.ArrayList;
import java.util.List;

public class Team {
    private String country;
    private List<Player> players;
    private int matches, wins, loses, nrr, pts;

    public Team(String country, List<Player> players) {
        this.country = country;
        this.players = players;
    }

    public Team() {
    }

    public int getMatches() {
        return matches;
    }

    public void setMatches(int matches) {
        this.matches = matches;
    }

    public void incMatches() {
        this.matches++;
    }

    public int getWins() {
        return wins;
    }

    public void setWins(int wins) {
        this.wins = wins;
    }

    public void incWins() {
        this.wins++;
    }

    public int getLoses() {
        return loses;
    }

    public void setLoses(int loses) {
        this.loses = loses;
    }

    public void incLoses() {
        this.loses++;
    }

    public int getNrr() {
        return nrr;
    }

    public void setNrr(int nrr) {
        this.nrr = nrr;
    }

    public int getPts() {
        return pts;
    }

    public void setPts(int pts) {
        this.pts = pts;
    }

    public void incPoints() {
        this.pts += 2;
    }


    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }

    @Override
    public String toString() {
        return country;
    }
}
