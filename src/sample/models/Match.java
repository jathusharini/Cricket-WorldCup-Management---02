package sample.models;

import javafx.util.Pair;

import java.util.ArrayList;
import java.util.List;

public class Match {
    private Team team1;
    private Team team2;
    private Team winningTeam, losingTeam, firstBatting, secondBatting;

    private List<Player> team2PlayerStats;
    private int firstScore, firstWickets;
    private int secondScore, secondWickets;
    private String stage;
    private int status;

    public String getToss() {
        return toss;
    }

    public void setToss(String toss) {
        this.toss = toss;
    }

    private String toss;
    private List<Player> team1PlayerStats;

    public Team getLosingTeam() {
        return losingTeam;
    }

    public void setLosingTeam(Team losingTeam) {
        this.losingTeam = losingTeam;
    }

    public Team getFirstBatting() {
        return firstBatting;
    }

    public void setFirstBatting(Team firstBatting) {
        this.firstBatting = firstBatting;
    }

    public Team getSecondBatting() {
        return secondBatting;
    }

    public void setSecondBatting(Team secondBatting) {
        this.secondBatting = secondBatting;
    }

    public List<Player> getTeam1PlayerStats() {
        return team1PlayerStats;
    }

    public void setTeam1PlayerStats(List<Player> team1PlayerStats) {
        this.team1PlayerStats = team1PlayerStats;
    }

    public List<Player> getTeam2PlayerStats() {
        return team2PlayerStats;
    }

    public void setTeam2PlayerStats(List<Player> team2PlayerStats) {
        this.team2PlayerStats = team2PlayerStats;
    }



    public Match(Team team1, Team team2, String stage) {
        this.team1 = team1;
        this.team2 = team2;
        this.team1PlayerStats = this.team1.getPlayers();
        this.team2PlayerStats = this.team2.getPlayers();
        this.team1PlayerStats.forEach(stat -> {
            stat.setRuns(0);
            stat.setWickets(0);
        });
        this.team2PlayerStats.forEach(stat -> {
            stat.setRuns(0);
            stat.setWickets(0);
        });
        this.stage = stage;
        this.status = 0;
    }

    public Team getTeam1() {
        return team1;
    }

    public void setTeam1(Team team1) {
        this.team1 = team1;
    }

    public Team getTeam2() {
        return team2;
    }

    public void setTeam2(Team team2) {
        this.team2 = team2;
    }

    public String getStage() {
        return stage;
    }

    public void setStage(String stage) {
        this.stage = stage;
    }

    public Team getWinningTeam() {
        return winningTeam;
    }

    public void setWinningTeam(Team winningTeam) {
        this.winningTeam = winningTeam;
    }

    public int getStatus() {
        return status;
    }

    public int getFirstScore() {
        return firstScore;
    }

    public void setFirstScore(int firstScore) {
        this.firstScore = firstScore;
    }

    public int getFirstWickets() {
        return firstWickets;
    }

    public void setFirstWickets(int firstWickets) {
        this.firstWickets = firstWickets;
    }

    public int getSecondScore() {
        return secondScore;
    }

    public void setSecondScore(int secondScore) {
        this.secondScore = secondScore;
    }

    public int getSecondWickets() {
        return secondWickets;
    }

    public void setSecondWickets(int secondWickets) {
        this.secondWickets = secondWickets;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Stage: " + stage +
                ", " + team1 +
                " vs " + team2 +
                ", First batting: " + firstBatting + ": " + firstScore + "/" + firstWickets +
                ", Second batting: " + secondBatting + ": " + secondScore + "/" + secondWickets +
                ", Winner: " + winningTeam;
    }

    public Pair<Team, Team> playMatch(){
        if (Math.random() > 0.5) {
           toss = team1.getCountry();
            if (Math.random() > 0.5) {
                firstBatting = team1;
                secondBatting = team2;
                toss += ": Batting";
            } else {
                firstBatting = team2;
                secondBatting = team1;
                toss += ": Bowling";
            }
        } else {
            toss = team2.getCountry();
            if (Math.random() > 0.5) {
                firstBatting = team1;
                secondBatting = team2;
                toss += ": Bowling";
            } else {
                firstBatting = team2;
                secondBatting = team1;
                toss += ": Batting";
            }
        }




        playInnings(firstBatting, true);
        playInnings(secondBatting, false);

        if (firstScore > secondScore) {
            winningTeam = firstBatting;
            losingTeam = secondBatting;
        } else {
            winningTeam = secondBatting;
            losingTeam = firstBatting;
        }
        setWinningTeam(winningTeam);
        setStatus(1);
        updatePlayerStats();
        return new Pair<Team, Team>(winningTeam, losingTeam);
    }

    private void updatePlayerStats() {
        team1.getPlayers().forEach(player -> {
            for (int i = 0; i < team1PlayerStats.size(); i++) {
                Player statPlayer = team1PlayerStats.get(i);
                if (statPlayer.getName().equals(player.getName())) {
                    player.setRuns(player.getRuns() + statPlayer.getRuns());
                    player.setWickets(player.getWickets() + statPlayer.getWickets());
                }
            }
        });
        team2.getPlayers().forEach(player -> {
            for (int i = 0; i < team2PlayerStats.size(); i++) {
                Player statPlayer = team2PlayerStats.get(i);
                if (statPlayer.getName().equals(player.getName())) {
                    player.setRuns(player.getRuns() + statPlayer.getRuns());
                    player.setWickets(player.getWickets() + statPlayer.getWickets());
                }
            }
        });
    }

    public void playInnings(Team team, boolean isFirstBatting) {
        List<Player> battingStats, bowlingStats;
        if (team == team1) {
            battingStats = team1PlayerStats;
            bowlingStats = team2PlayerStats;
        } else {
            battingStats = team2PlayerStats;
            bowlingStats = team1PlayerStats;
        }

        if (isFirstBatting) {
            firstScore = (int)Math.floor(Math.random()*(400-200+1)+200);
            firstWickets = (int)Math.floor(Math.random()*(10-0+1)+0);

            spreadRuns(battingStats, firstScore, firstWickets);
            spreadWickets(bowlingStats, firstScore, firstWickets);
        } else {
            if (Math.random() > 0.5) {
                // second batting wins
                secondScore = firstScore + 1;
                secondWickets = (int)Math.floor(Math.random()*(firstWickets-1+1)+0);
            } else {
                // first batting wins
                secondScore = (int)Math.floor(Math.random()*(firstScore-1-200+1)+200);
                secondWickets = (int)Math.floor(Math.random()*(10-0+1)+0);
            }

            spreadRuns(battingStats, secondScore, secondWickets);
            spreadWickets(bowlingStats, secondScore, secondWickets);
        }
    }

    public void spreadRuns(List<Player> players, int runs, int wickets) {
        List<Integer> splittedRuns = splitNumIntoXRandomComponents(runs, wickets + 2, 0);
        for (int i = 0; i < wickets + 1; i++) {
            players.get(i).setRuns(splittedRuns.get(i));
        }
    }

    public void spreadWickets(List<Player> players, int runs, int wickets) {
        List<Integer> splittedWickets = splitNumIntoXRandomComponents(wickets, 5, 0);
        for (int i = 0; i < 5; i++) {
            players.get(i).setWickets(splittedWickets.get(i));
        }
    }

    public static List<Integer> splitNumIntoXRandomComponents(int num, int x, int min_num) {
        List<Integer> components = new ArrayList<>();
        int count = 1;
        int cumulative = 0;
        int balance = num;

        for (int i = 0; i<x-1; i++) {
            int max_num = balance - ((x-count)*min_num);

            if (Math.random() > 0.5){
                max_num = (int) (Math.floor(max_num / 2) + min_num);
            }
            int c = (int) Math.floor(Math.random()*(max_num-min_num+1)+min_num);

            cumulative += c;
            balance -= c;
            count++;

            components.add(c);

        }

        components.add(balance);
        return components;
    }
}
