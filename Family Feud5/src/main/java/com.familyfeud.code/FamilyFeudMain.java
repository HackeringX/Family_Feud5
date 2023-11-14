package com.familyfeud.code;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.sun.tools.javac.Main;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class FamilyFeudMain extends Thread {

    private static Scanner  input = new Scanner(System.in);
    private static boolean running = false;
    private static String ans = "";

    public static void main(String[] args) {

        ArrayList<JsonNode> list = new ArrayList<>();
        ArrayList<Game> history = new ArrayList<>();
        FamilyFeudMain f1 = new FamilyFeudMain();

        File data = new File("C:\\Users\\Pinky laptop\\IdeaProjects\\Family Feud5\\train.jsonl");

        try {
            Scanner fileReader = new Scanner(data);
            while (fileReader.hasNextLine()) {
                list.add(Json.parse(fileReader.nextLine()));
            }

            System.out.println(list.get((int) (Math.random() * list.size())).get("metadata").get("id"));

        } catch (FileNotFoundException | JsonProcessingException e) {
            e.printStackTrace();
        }

        // Start of the program

        System.out.print("Welcome to Family Feud The Java Game! ");
        String choice;

        while (true) {
            System.out.print("Type \"new\" to start a new game, and type \"history\" to view history of games. If you want to exit, type \"exit\": ");
            choice = input.nextLine().toLowerCase().trim();
            clearScreen();

            if (choice.equals("new") || choice.equals("n")) {

                System.out.print("What would you like to name this game? ");
                String name = input.nextLine().strip();

                Player user = new Player("User");
                Computer computer = new Computer();
                Game newGame = new Game(name, user, computer);

                while (true) {
                    System.out.print("Which difficulty do you wish to play on? Options are \"easy\", \"medium\", or \"hard\": ");
                    choice = input.nextLine().toLowerCase().trim();

                    if (choice.equals("easy") || choice.equals("medium") || choice.equals("hard")) {
                        computer.setDifficulty(choice);
                        clearScreen();
                        break;
                    } else {
                        System.out.println("That is not a valid option, try again.\n");
                    }
                }


                System.out.print("Click ENTER to start Round 1.");
                input.nextLine();
                clearScreen();

                // Start

                int timeLeft = 60;
                System.out.println("Time Left: " + timeLeft);

                int randIndex = (int) (Math.random() * list.size());
                while (list.get(randIndex).get("answers").get("clusters").size() < 5) {
                    randIndex = (int) (Math.random() * list.size());
                }

                JsonNode question = list.get(randIndex);
                String[][] answers = new String[5][];
                String[] starts = new String[5];
                int[] worth = new int[5];

                System.out.println(question.get("question").get("original") + "\nAnswers:");
                // .get("metadata").get("id").asText() + ".1"
                for (int i = 0; i < 5; i++) {
                    worth[i] = question.get("answers").get("clusters").get(question.get("metadata").get("id").asText() + "." + i).get("count").asInt();
                    starts[i] = "Answer " + (i + 1) + " (" + worth[i] + "): ";
                    System.out.println(starts[i]);
                    answers[i] = question.get("answers").get("clusters").get(question.get("metadata").get("id").asText() + "." + i).get("answers").toString().replaceAll("\\[|]", "").split(",");
                }

                int userScore = 0;
                int numberOfUserMistakes = 0;
                String userMistakes = "";
                System.out.println("\nUser Score: " + userScore + "\nUser Mistakes: " + userMistakes);

                System.out.println("\nComputer Score: 0" + "\nComputer Mistakes: " + "\n");

                running = true;
                f1.start();


                while (timeLeft > 0) {
                    int correctIndex = 0;

                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    System.out.print("\0337");
                    System.out.print("\033[15A\r\033[K");
                    timeLeft--;
                    System.out.println("Time Left: " + timeLeft);
                    System.out.print("\0338");

                    if (ans.length() > 0) {
                        boolean correct = false;

                        for (int i = 0; i < answers.length; i++) {
                            String[] aList = answers[i];

                            for (String a : aList) {
                                if (ans.equals(a)) {
                                    correct = true;
                                    correctIndex = i;
                                    userScore += worth[i];
                                    break;
                                }
                            }

                        }

                        if (correct) {
                            ans = "";
                            System.out.print("\0337");
                            System.out.print("\033[6A\r\033[K");
                            System.out.println("User Score: " + userScore);
                            System.out.print("\0338");

                            System.out.print("\0337");
                            System.out.print("\033["+ (6 + (5 - correctIndex)) + "A\r\033[K");
                            System.out.println(starts[correctIndex] + Arrays.toString(answers[correctIndex]));
                            System.out.print("\0338");

                        } else {
                            ans = "";
                            numberOfUserMistakes++;
                            if (3 - numberOfUserMistakes < 0) {
                                userScore -= 5;
                            }
                            userMistakes += "X";

                            System.out.print("\0337");
                            System.out.print("\033[6A\r\033[K");
                            System.out.println("User Score: " + userScore);
                            System.out.print("\0338");

                            System.out.print("\0337");
                            System.out.print("\033[5A\r\033[K");
                            System.out.println("User Mistakes: " + userMistakes);
                            System.out.print("\0338");
                        }

                    }



                }

                running = false;
                System.out.println("\033[K");
                System.out.print("Enter to continue: ");

                while (f1.isAlive()) {
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                // End

            } else if (choice.equals("history") || choice.equals("h")) {

                while (true) {
                    if (history.size() == 0) {
                        System.out.print("You have not played any games. Come back when you have. Click ENTER to go back.");
                        input.nextLine();
                        clearScreen();
                        break;
                    }

                    for (int i = 0; i < history.size(); i++) {
                        System.out.println(history.toString());
                    }
                    System.out.println("Click ENTER to continue:");
                    input.nextLine();
                    clearScreen();
                    break;

                }

            } else if (choice.equals("exit") || choice.equals("e")) {
                break;

            } else {
                System.out.println("That is not a valid option; try again.\n");
            }

        }

        /*try {
            JsonNode node = Json.parse(src);

            /*
            Refer back to the following:
            String str = node.get("answers").get("clusters").get(node.get("metadata").get("id").asText() + ".1").get("answers").toString();
            String[] list = str.replaceAll("\\[|]", "").split(",");
            System.out.println(list[0]); // The answer

            Use this to find how many answers there are:
            node.size(); \\ Replace node with the expression for str

            Test node.traverse() to see how it works

            System.out.println(node.get("name"));

        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }



        System.out.println("Hi");

        f1.start();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.print("\0337");
        System.out.print("\033[1A\r\033[K");
        System.out.println("Hi, again.");
        System.out.print("\0338");

        //System.out.println("Hi");

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        running = false;

        System.out.println("\033[K");
        System.out.print("Enter to continue: ");*/





    }

    public static void clearScreen() {
        System.out.println("\033[H\033[2J");
        System.out.flush();
    }

    public void run() {
        Scanner primeTime = new Scanner(System.in);

        while (running) {
            System.out.print("Enter text: ");

            ans = primeTime.nextLine();

            System.out.print("\033[1A\r\033[K");

        }
    }

}
