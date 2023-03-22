package sample.models;

public class PlayerSummary {
    private String player;
    private String country;
    private int runs;
    private int wickets;

    public PlayerSummary(Player player, String country) {
        this.player = player.getName();
        this.country = country;
        runs = player.getRuns();
        wickets = player.getWickets();
    }

    public String getPlayer() {
        return player;
    }

    public void setPlayer(String player) {
        this.player = player;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

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
}
