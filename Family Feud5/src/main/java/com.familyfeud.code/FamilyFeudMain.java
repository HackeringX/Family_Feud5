package com.familyfeud.code;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.sun.tools.javac.Main;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class FamilyFeudMain extends Thread {

    private static Scanner  input = new Scanner(System.in);
    private static boolean running = true;
    private static String ans = "";

    public static void main(String[] args) {

        ArrayList<JsonNode> list = new ArrayList<>();
        ArrayList<Game> history = new ArrayList<>();

        File data = new File("C:\\Users\\Pinky laptop\\IdeaProjects\\Family Feud5\\train.jsonl");

        try {
            Scanner fileReader = new Scanner(data);
            while (fileReader.hasNextLine()) {
                list.add(Json.parse(fileReader.nextLine()));
            }

            System.out.println(list.get(1).get("metadata").get("id"));

        } catch (FileNotFoundException | JsonProcessingException e) {
            e.printStackTrace();
        }

        // Start while loop

        System.out.print("Welcome to Family Feud The Java Game! ");
        String choice;

        while (true) {
            System.out.print("Type \"new\" to start a new game, and type \"history\" to view history of games. If you want to exit, type \"exit\": ");
            choice = input.nextLine().toLowerCase().trim();
            clearScreen();

            if (choice.equals("new") || choice.equals("n")) {

            } else if (choice.equals("history") || choice.equals("h")) {

                while (true) {
                    if (history.size() == 0) {
                        System.out.print("You have not played any games. Come back when you have. Click ENTER to go back.");
                        input.nextLine();
                        clearScreen();
                        break;
                    }

                    // CONTINUE HERE
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



        FamilyFeudMain f1 = new FamilyFeudMain();

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

        while (running) {
            System.out.print(ans + "Enter text: ");

            ans = input.nextLine();

            System.out.print("\033[1A\r\033[K");

        }
    }

}
