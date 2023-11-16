package com.familyfeud.code;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.sun.tools.javac.Main;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Locale;
import java.util.Scanner;

public class FamilyFeudMain extends Thread {

    private static boolean running = false;
    private static String ans = "";

    public static void main(String[] args) {

        ArrayList<JsonNode> list = new ArrayList<>();
        Scanner  input = new Scanner(System.in);

        File data = new File("C:\\Users\\Pinky laptop\\IdeaProjects\\Family Feud5\\train.jsonl");

        try {
            Scanner fileReader = new Scanner(data);
            while (fileReader.hasNextLine()) {
                list.add(Json.parse(fileReader.nextLine()));
            }


        } catch (FileNotFoundException | JsonProcessingException e) {
            e.printStackTrace();
        }

        // Start of the program

        clearScreen();
        System.out.print("Welcome to Family Feud The Java Game! ");
        String choice;

        label:
        while (true) {
            System.out.print("Type \"new\" to start a new game, and type \"history\" to view history of games. If you want to exit, type \"exit\": ");
            choice = input.nextLine().toLowerCase().trim();
            clearScreen();

            switch (choice) {
                case "new":
                case "n":

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

                    int randIndex;
                    int lastRandIndex = -1;

                    for (int z = 1; z <= 3; z++) {
                        ans = "";
                        System.out.print("Click ENTER to start Round " + z + ".");
                        input.nextLine();
                        clearScreen();

                        FamilyFeudMain f1 = new FamilyFeudMain();

                        int timeLeft = 60;
                        System.out.println("Time Left: " + timeLeft);

                        randIndex = (int) (Math.random() * list.size());
                        while (list.get(randIndex).get("answers").get("clusters").size() < 5 || randIndex == lastRandIndex) {
                            randIndex = (int) (Math.random() * list.size());
                        }

                        lastRandIndex = randIndex;
                        randIndex = 3819;

                        JsonNode question = list.get(randIndex);
                        String[][] answers = new String[5][];
                        String[] starts = new String[5];
                        int[] worth = new int[5];
                        ArrayList<String> used = new ArrayList<>();

                        System.out.println(question.get("question").get("original") + "\nAnswers:");
                        // .get("metadata").get("id").asText() + ".1"
                        for (int i = 0; i < 5; i++) {
                            worth[i] = question.get("answers").get("clusters").get(question.get("metadata").get("id").asText() + "." + i).get("count").asInt();
                            starts[i] = "Answer " + (i + 1) + " (" + worth[i] + "): ";
                            System.out.println(starts[i]);
                            String[] arrayList = question.get("answers").get("clusters").get(question.get("metadata").get("id").asText() + "." + i).get("answers").toString().replaceAll("\\[|]", "").replaceAll("\"", "").split("/");
                            answers[i] = Arrays.stream(arrayList).map(s -> s.toLowerCase().strip()).toArray(String[]::new);
                        }

                        int userScore = 0;
                        int numberOfUserMistakes = 0;
                        String userMistakes = "";
                        System.out.println("\nUser Score: " + userScore + "\nUser Mistakes: " + userMistakes + "(" + numberOfUserMistakes + ")");

                        int computerScore = 0;
                        int numberOfComputerMistakes = 0;
                        String computerMistakes = "";
                        computer.setWorths(worth);
                        computer.startRound();
                        System.out.println("\nComputer Score: " + computerScore + "\nComputer Mistakes: " + computerMistakes + "\n");

                        running = true;
                        f1.start();

                        while (timeLeft > 0) {
                            int correctIndex = 0;

                            try {
                                Thread.sleep(1000);
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
                                        if (ans.toLowerCase().strip().equals(a)) {
                                            correct = true;
                                            correctIndex = i;
                                            break;
                                        }
                                    }

                                }

                                if (correct) {

                                    if (!used.contains(ans)) {
                                        userScore += worth[correctIndex];

                                        System.out.print("\0337");
                                        System.out.print("\033[6A\r\033[K");
                                        System.out.println("User Score: " + userScore);
                                        System.out.print("\0338");

                                        System.out.print("\0337");
                                        System.out.print("\033[" + (7 + (5 - correctIndex)) + "A\r\033[K");
                                        System.out.println(starts[correctIndex] + Arrays.toString(answers[correctIndex]));
                                        System.out.print("\0338");

                                        used.addAll(Arrays.asList(answers[correctIndex]));
                                    }

                                    ans = "";

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

                            int botAns = computer.playGame(timeLeft);
                            if (botAns != 0) {
                                if (botAns == -5) {
                                    computerMistakes += "X";
                                    numberOfComputerMistakes++;
                                    if (numberOfComputerMistakes <= 3) {
                                        computerScore -= botAns;
                                    }
                                }
                                computerScore += botAns;

                                System.out.print("\0337");
                                System.out.print("\033[3A\r\033[K");
                                System.out.println("Computer Score: " + computerScore);
                                System.out.print("\0338");

                                System.out.print("\0337");
                                System.out.print("\033[2A\r\033[K");
                                System.out.println("Computer Mistakes: " + computerMistakes);
                                System.out.print("\0338");

                            }

                        }

                        for (int i = 0; i < answers.length; i++) {
                            System.out.print("\0337");
                            System.out.print("\033[" + (7 + (5 - i)) + "A\r\033[K");
                            System.out.println(starts[i] + Arrays.toString(answers[i]));
                            System.out.print("\0338");
                        }

                        newGame.addRound(new int[]{userScore, computerScore});

                        running = false;
                        ans = "";
                        System.out.println("\033[K");
                        System.out.print("\nEnter to continue: ");

                        while (f1.isAlive()) {
                            try {
                                Thread.sleep(100);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }

                        clearScreen();
                        System.out.println("Results after Round " + z + ":");
                        System.out.println(newGame.toString());
                        System.out.println("\nWinner of Round: " + ((userScore == computerScore) ? "Nobody! (Tie)" : ((userScore > computerScore) ? "User" : "Computer")));
                    }

                    newGame.finish();
                    System.out.println("\nWinner of Game: " + ((user.getTotal() == computer.getTotal()) ? "Nobody! (Tie)" : ((user.getTotal() > computer.getTotal()) ? "User" : "Computer")));

                    break;

                case "history":
                case "h":

                    ArrayList<Game> history = Game.getHistory();

                    if (history.size() == 0) {
                        System.out.print("You have not played any games. Come back when you have. Click ENTER to go back.");
                        input.nextLine();
                        clearScreen();
                        break;

                    } else {
                        for (Game game : history) {
                            System.out.println(game + "\n");
                        }
                        System.out.println("Click ENTER to continue:");
                        input.nextLine();
                        clearScreen();
                        break;
                    }

                case "exit":
                case "e":
                    break label;

                default:
                    System.out.println("That is not a valid option; try again.\n");
                    break;
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
