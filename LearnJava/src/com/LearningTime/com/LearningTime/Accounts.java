package com.LearningTime;

import java.util.ArrayList;


public class Accounts {

    protected static ArrayList<String> userName = new ArrayList<>();
    protected static ArrayList<String> userPassword = new ArrayList<>();
    protected static ArrayList<Integer> userBirthDay = new ArrayList<>();
    protected static ArrayList<Integer> userBirthMonth = new ArrayList<>();
    protected static ArrayList<Integer> userBirthYear = new ArrayList<>();
    protected static ArrayList<Double> checkingBalance = new ArrayList<>();
    protected static ArrayList<Double> savingsBalance = new ArrayList<>();
    protected static ArrayList<String> previousTransaction = new ArrayList<>();
    protected static ArrayList<Integer> accountNumber = new ArrayList<>();

    static {
        userName.add("abhiroop");
        userName.add("aaron");
        userPassword.add("d56059!");
        userPassword.add("hello123!");
        userBirthDay.add(26);
        userBirthDay.add(11);
        userBirthMonth.add(11);
        userBirthMonth.add(5);
        userBirthYear.add(2007);
        userBirthYear.add(2016);
        checkingBalance.add(0.0);
        checkingBalance.add(0.0);
        savingsBalance.add(0.0);
        savingsBalance.add(0.0);
        previousTransaction.add("No transaction taken yet");
        previousTransaction.add("No transaction taken yet");
        accountNumber.add(1);
        accountNumber.add(2);
    }
}
