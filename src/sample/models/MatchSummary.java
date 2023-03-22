package sample.models;

public class MatchSummary {
    private String stage;
    private String teams;
    private String firstBatting;
    private String secondBatting;
    private String winner;
    private String toss;

    public MatchSummary() {
    }

    public MatchSummary(Match match) {
        this.stage = match.getStage();
        this.teams = match.getTeam1() + " vs " + match.getTeam2();
        this.firstBatting =  match.getFirstBatting() + ": " + match.getFirstScore() + "/" + match.getFirstWickets();
        this.secondBatting =  match.getSecondBatting() + ": " + match.getSecondScore() + "/" + match.getSecondWickets();
        this.winner = match.getWinningTeam().getCountry();
        this.toss = match.getToss();
    }

    public String getToss() {
        return toss;
    }

    public void setToss(String toss) {
        this.toss = toss;
    }

    public String getStage() {
        return stage;
    }

    public void setStage(String stage) {
        this.stage = stage;
    }

    public String getTeams() {
        return teams;
    }

    public void setTeams(String teams) {
        this.teams = teams;
    }

    public String getFirstBatting() {
        return firstBatting;
    }

    public void setFirstBatting(String firstBatting) {
        this.firstBatting = firstBatting;
    }

    public String getSecondBatting() {
        return secondBatting;
    }

    public void setSecondBatting(String secondBatting) {
        this.secondBatting = secondBatting;
    }

    public String getWinner() {
        return winner;
    }

    public void setWinner(String winner) {
        this.winner = winner;
    }
}
