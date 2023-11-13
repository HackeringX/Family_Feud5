package com.familyfeud.code;

import java.util.ArrayList;

public class Player {

    protected String name;
    protected ArrayList<Integer> scores = new ArrayList<Integer>();
    protected int total;

    public Player(String name) {
        this.name = name;
    }

    public Player() {
        this.name = "User";
    }

    public String getName() {
        return name;
    }

    public ArrayList<Integer> getScores() {
        return scores;
    }

    public boolean isBot() {
        return false;
    }


    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void addRound(int score) {
        scores.add(score);

        total = 0;
        for (int i = 0; i < scores.size(); i++) {
            total += scores.get(i) * (1 + i);
        }
    }
}
