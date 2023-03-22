package sample.models;

public class GroupSummary {
    private String team;
    private int matches, wins, loses, points;

    public GroupSummary(Team team) {
        this.team = team.getCountry();
        matches = team.getMatches();
        wins = team.getWins();
        loses = team.getLoses();
        points = team.getPts();
    }


    public String getTeam() {
        return team;
    }

    public void setTeam(String team) {
        this.team = team;
    }

    public int getMatches() {
        return matches;
    }

    public void setMatches(int matches) {
        this.matches = matches;
    }

    public int getWins() {
        return wins;
    }

    public void setWins(int wins) {
        this.wins = wins;
    }

    public int getLoses() {
        return loses;
    }

    public void setLoses(int loses) {
        this.loses = loses;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }
}
