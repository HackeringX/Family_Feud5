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

    /**
    * While also setting the difficulty, it also sets the accuracy of the robot depending on the difficulty
    * @param The difficulty
    */
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

    /**
    * This is to set the times when the computer will answer a question
    */
    public void startRound() {
        for (int i = 0; i < 6; i++) {
            int randTime = ((int) (Math.random() * 10)) + (i * 10);
            times[i] = randTime;
        }
    }

    /**
    * It calculates what the computer will do:
    * 1. Answer the question - It will either get it right (gains however many points the question is worth) or wrong (returns -5). It might get many right initially, but loses momentum
    * 2. Not answer the question
    * @param The current time left
    * @return The result (positive - gets that many points, -5 - incorrect, 0 - nothing)
    */
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
