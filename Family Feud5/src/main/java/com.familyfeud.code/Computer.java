package com.familyfeud.code;

import java.util.Arrays;

public class Computer extends Player{

    private String difficulty;
    private double accuracy;
    private int[] worths;
    private int[] times = new int[6];

    public int[] getTimes() {
        return times;
    }

    public void setTimes(int[] times) {
        this.times = times;
    }

    public double getAccuracy() {
        return accuracy;
    }

    public void setAccuracy(double accuracy) {
        this.accuracy = accuracy;
    }

    public int[] getWorths() {
        return worths;
    }

    public void setWorths(int[] worths) {
        this.worths = worths;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(String difficulty) {

        this.difficulty = difficulty;

        switch (difficulty.charAt(0)) {
            case 'e':
                setAccuracy(0.5);
                break;

            case 'm':
                setAccuracy(0.8);
                break;

            case 'h':
                setAccuracy(1);
                break;
        }
    }

    public Computer() {
        name = "Computer";
    }

    public void startRound() {
        for (int i = 0; i < 6; i++) {
            int randTime = ((int) (Math.random() * 10)) + (i * 10);
            times[i] = randTime;
        }
    }

    public int playGame(int time) {

        if (Arrays.stream(worths).sum() == 0) {
            return 0;
        }

        if (Arrays.stream(times).anyMatch(argument -> argument == time)) {
            int right = (int) (Math.random() + getAccuracy());

            if (right == 1) {
                int worthI = (int) (Math.random() * worths.length);
                int ans = getWorths()[worthI];
                worths[worthI] = 0;
                return ans;
            } else {
                return -5;
            }
        }

        return 0;
    }

    public boolean isBot() {
        return true;
    }

}
