package com.LearningTime;

import java.io.File;  // Import the File class
import java.io.IOException;  // Import the IOException class to handle errors
import java.io.FileWriter;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class CreateFile {
    static void test() {

        Scanner scanner = new Scanner(System.in);

        System.out.println("Do you want to create or edit a file: ");
        String name = scanner.nextLine();

        switch (name) {
            case "create":
                try {
                    File myObj = new File("C:\\Users\\Pinky Laptop\\filename.txt");
                    if (myObj.createNewFile()) {
                        System.out.println("File created: " + myObj.getName());
                    } else {
                        System.out.println("File already exists.");
                    }
                } catch (IOException e) {
                    System.out.println("An error occurred.");
                    e.printStackTrace();
                }
                break;
            case "edit":
                try {
                    FileWriter myWriter = new FileWriter("C:\\Users\\Pinky Laptop\\filename.txt");
                    myWriter.write("Files in Java might be tricky, but it is fun enough!");
                    myWriter.close();
                    System.out.println("Successfully wrote to the file.");
                } catch (IOException e) {
                    System.out.println("An error occurred.");
                    e.printStackTrace();
                }
                break;
            case "read":
                try {
                    File myObj = new File("C:\\Users\\Pinky Laptop\\filename.txt");
                    Scanner myReader = new Scanner(myObj);
                    while (myReader.hasNextLine()) {
                        String data = myReader.nextLine();
                        System.out.println(data);
                    }
                    myReader.close();
                } catch (FileNotFoundException e) {
                    System.out.println("An error occurred.");
                    e.printStackTrace();
                }
                break;
            default:
                File myObj = new File("C:\\Users\\Pinky Laptop\\filename.txt");
                if (myObj.exists()) {
                    System.out.println("File name: " + myObj.getName());
                    System.out.println("Absolute path: " + myObj.getAbsolutePath());
                    System.out.println("Writeable: " + myObj.canWrite());
                    System.out.println("Readable " + myObj.canRead());
                    System.out.println("File size in bytes " + myObj.length());
                } else {
                    System.out.println("The file does not exist.");
                }
                break;
        }
    }
}