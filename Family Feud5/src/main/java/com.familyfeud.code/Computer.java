package com.familyfeud.code;

public class Computer extends Player{

    private String difficulty;

    public String getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }

    public Computer() {
        name = "Computer";
    }

    public boolean isBot() {
        return true;
    }

}
