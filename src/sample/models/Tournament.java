package sample.models;

import com.opencsv.bean.CsvToBeanBuilder;
import javafx.util.Pair;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;


public final class Tournament {
    private static List<Team> teams = new ArrayList<>();
    private static List<Match> matches = new ArrayList<>();
    private static String[] teamNames = {"England", "Pakistan", "India", "New Zealand", "South Africa", "Australia", "Sri Lanka", "Bangladesh"};
    private Tournament() {}

    public static List<Team> getTeams() {
        return teams;
    }

    public static void setTeams(List<Team> teams) {
        Tournament.teams = teams;
    }

    public static List<Match> getMatches() {
        return matches;
    }

    public static void setMatches(List<Match> matches) {
        Tournament.matches = matches;
    }

    public static String[] getTeamNames() {
        return teamNames;
    }

    public static void setTeamNames(String[] teamNames) {
        Tournament.teamNames = teamNames;
    }

    public static void init() {
        for (int i = 0; i < teamNames.length; i++) {
            try {
                List<Player> players = new CsvToBeanBuilder<Player>(new FileReader("src/sample/csv/" + teamNames[i] + ".csv"))
                            .withType(Player.class)
                            .build().parse();
                teams.add(new Team(teamNames[i], players));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

        }
    }

    public static List<Match> generateMatches() {
        for (int i = 0; i < teams.size() / 2; i++) {
            for (int j = i+1; j < teams.size() / 2; j++) {
                matches.add(new Match(teams.get(i), teams.get(j), "A"));
            }
        }

        for (int i = teams.size() / 2; i < teams.size(); i++) {
            for (int j = i+1; j < teams.size(); j++) {
                matches.add(new Match(teams.get(i), teams.get(j), "B"));
            }
        }
        return matches;
    }

    public static List<Match> playMatches() {
        for (int i = 0; i < matches.size(); i++) {
            Pair<Team, Team> result =   matches.get(i).playMatch();
            Team winningTeam = result.getKey();
            Team losingteam = result.getValue();
            int wIndex = teams.indexOf(winningTeam);
            int lIndex = teams.indexOf(losingteam);

            teams.get(wIndex).incMatches();
            teams.get(wIndex).incWins();
            teams.get(wIndex).incPoints();

            teams.get(lIndex).incMatches();
            teams.get(lIndex).incLoses();
        }
        return  matches;
    }

    public static void getRankings() {
        for (int i = 0; i < teams.size() / 2; i++) {
            System.out.println(teams.get(i).getMatches() + " " + teams.get(i).getWins() + " " + teams.get(i).getLoses() + " " + teams.get(i).getPts());
        }

        for (int i = teams.size() / 2; i < teams.size(); i++) {
            System.out.println(teams.get(i).getMatches() + " " + teams.get(i).getWins() + " " + teams.get(i).getLoses() + " " + teams.get(i).getPts());
        }
    }

    public static void generatePlayOffs() {
        int maxPtsA = 0, secondPtsA = 0;
        Team maxTeamA = null, secondTeamA = null;

        for (int i = 0; i < teams.size() / 2; i++) {
            int pts = teams.get(i).getPts();
            if (pts > maxPtsA) {
                secondPtsA = maxPtsA;
                secondTeamA = maxTeamA;
                maxPtsA = pts;
                maxTeamA = teams.get(i);
            }
            else if (pts > secondPtsA) {
                secondPtsA = pts;
                secondTeamA = teams.get(i);
            }
        }

        int maxPtsB = 0, secondPtsB = 0;
        Team maxTeamB = null, secondTeamB = null;

        for (int i = teams.size() / 2; i < teams.size(); i++) {
            int pts = teams.get(i).getPts();
            if (pts > maxPtsB) {
                secondPtsB = maxPtsB;
                secondTeamB = maxTeamB;
                maxPtsB = pts;
                maxTeamB = teams.get(i);
            }
            else if (pts > secondPtsB) {
                secondPtsB = pts;
                secondTeamB = teams.get(i);
            }
        }

        matches.add(new Match(teams.get(teams.indexOf(maxTeamA)), teams.get(teams.indexOf(secondTeamB)), "Semi Final 1"));
        matches.add(new Match(teams.get(teams.indexOf(maxTeamB)), teams.get(teams.indexOf(secondTeamA)), "Semi Final 2"));
    }

    public static void playPlayOffs() {
        for (int i = 0; i < matches.size(); i++) {
            if (matches.get(i).getStatus() != 1){
                matches.get(i).playMatch();
            }
        }
    }

    public static void generateFinal() {
        Team team1 = null, team2 = null;
        for (int i = 0; i < matches.size(); i++) {
            if (matches.get(i).getStage().equals("Semi Final 1") && matches.get(i).getStatus() == 1 ){
                team1 = matches.get(i).getWinningTeam();
            }
            else if (matches.get(i).getStage().equals("Semi Final 2") && matches.get(i).getStatus() == 1 ) {
                team2 = matches.get(i).getWinningTeam();
            }
        }
        matches.add(new Match(teams.get(teams.indexOf(team1)), teams.get(teams.indexOf(team2)), "Final"));
    }

    public static void playFinal() {
        for (int i = 0; i < matches.size(); i++) {
            if (matches.get(i).getStage().equals("Final") && matches.get(i).getStatus() == 0 ){
                matches.get(i).playMatch();
                break;
            }
        }
    }

    public static void printMatches() {
        for (int i = 0; i < matches.size(); i++) {
            Match match = matches.get(i);
            System.out.println(match);
        }
    }

    public static void printPlayerStats() {
        for (int i = 0; i < teams.size(); i++) {
            List<Player> players = teams.get(i).getPlayers();
            for (int j = 0; j < players.size(); j++) {
                System.out.println(players.get(j) + ", " + teams.get(i).getCountry());
            }
        }
    }


}
