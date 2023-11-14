package com.LearningTime;

import java.util.Scanner;

import static com.LearningTime.BankingSystem.bankingApplication;
import static com.LearningTime.BusTicketingSystem.system;
import static com.LearningTime.FizzBuzz.fizzBuzzMain;
import static com.LearningTime.MortgageCalculator.calculations;
import static com.LearningTime.CreateFile.test;
import static com.LearningTime.Experiments.actual;

public class Main {
    public static void main(String[] args) throws NoSuchMethodException {
        //Scanner Object
        Scanner scanner = new Scanner(System.in);

        //Security is important
        System.out.print("Enter the password for com.LearningTime: ");
        String password = scanner.nextLine();

        //To check if the password is correct or not
        if (password.equals("LearnJava")){
            //Password is correct
            System.out.println("Access Granted");
            System.out.print("Enter a method found in com.LearningTime: ");


            String methodInput = scanner.nextLine();
            //Just to increase flexibility
            String method = methodInput.toLowerCase().trim();

            //There are 4 methods you can access: "fizz buzz", "calculations", "banking application", "bus ticketing system"
            switch (method){
                case "fizz buzz":
                    System.out.println("You have called the method com.LearningTime.FizzBuzz.fizzBuzzMain()\n");
                    //Does whatever there is in fizzBuzzMain()
                    fizzBuzzMain();
                break;

                case "calculations":
                    System.out.println("You have called the method com.LearningTime.MortgageCalculator.calculations()\n");
                    //Does whatever there is in calculations()
                    calculations();
                break;

                case "banking application":
                    System.out.println("You have called the method com.LearningTime.BankingSystem.bankingMethod\n");
                    //Does whatever there is in bankingApplication()
                    bankingApplication();
                break;

                case "bus ticketing system":
                    System.out.println("You have called the method com.LearningTime.BusTicketingSystem.system\n");
                    //Does whatever there is in system()
                    system();
                break;

                case "create file":
                    System.out.println("You have called the method com.LearningTime.CreateFile.test\n");
                    //Does whatever there is in fizzBuzzMain()
                    test();
                break;

                case "actual":
                    //System.out.println("You have called the method com.LearningTime.CreateFile.test\n");
                    //Does whatever there is in fizzBuzzMain()
                    actual();
                    break;

                default:
                    //There is no other method that the user can access, so it will throw an exception
                    throw new NoSuchMethodException(methodInput + " does not exist in com.LearningTime.");
            }


        } else {
            //You have to get the password right if you don't want to face this
            System.out.println("Access Denied, the password is incorrect");
        }


    }
}