package com.familyfeud.code;

public class Computer extends Player{

    public Computer() {
        name = "Computer";
    }

    public boolean isBot() {
        return true;
    }

}
