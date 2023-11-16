package com.familyfeud.code;

import java.util.ArrayList;

public class Game {

    // Format: [[UserScoreRound1, ComputerScoreRound1],..., [UserRound3, ComputerRound3]]
    // Three rounds
    private ArrayList<int[]> scores = new ArrayList<int[]>();
    private Integer[] totals = new Integer[2];
    private Player[] players = new Player[2];
    private String name;

    private static ArrayList<Game> history = new ArrayList<>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Game(String name, Player user, Computer computer) {
        this.name = name;
        players[0] = user;
        players[1] = computer;

    }

    public Integer[] getTotals() {
        return totals;
    }

    public void setTotals(Integer[] total) {
        totals = total;
    }

    public ArrayList<int[]> getScores() {
        return scores;
    }

    public static ArrayList<Game> getHistory() {
        return history;
    }

    public void finish() {
        history.add(this);
    }

    public void addRound(int[] round) {
        scores.add(round);

        for (int i = 0; i < players.length; i++) {
            players[i].addRound(round[i]);
            totals[i] = players[i].getTotal();
        }

    }

    public String toString() {
        String scoreTable = "";

        scoreTable += "Game Name: " + name + "\n\n              "+ players[0].getName() + "    " + players[1].getName()+ "\n------------------------------";
        for (int i = 0; i < scores.size(); i++) {
            scoreTable += "\nRound " + (i + 1) + ":\t" + scores.get(i)[0] + "\t" + scores.get(i)[1] + "\tx" + (i + 1);
        }

        scoreTable += "\n------------------------------\nTotals: \t" + totals[0] + "\t" + totals[1] + "\n";

        return scoreTable;
    }
}
