package com.LearningTime;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class BusTicketingSystem {
    static void system() {

        boolean validName;
        boolean ageIsInt;
        boolean ageIsOkay;
        boolean validAge;
        boolean seatExists;
        boolean seatIsAvailable;
        boolean seatCanBeBooked;
        boolean answerIsValid;
        boolean seatsAreAvailable;
        boolean isBooking;

        String[] seats = {"A1", "A2", "B1", "B2", "C1", "C2", "D1", "D2", "E1", "E2"};
        ArrayList<String> freeOrNot = new ArrayList<>();
        freeOrNot.add("available");
        freeOrNot.add("available");
        freeOrNot.add("unavailable");
        freeOrNot.add("available");
        freeOrNot.add("available");
        freeOrNot.add("available");
        freeOrNot.add("unavailable");
        freeOrNot.add("unavailable");
        freeOrNot.add("available");
        freeOrNot.add("unavailable");
        ArrayList<String> seatsBooked = new ArrayList<>();

        Scanner scanner = new Scanner(System.in);

        System.out.println("Hello and welcome to YourBus.com");
        System.out.println("Before you are allowed to book your ticket");
        System.out.println("Please enter your name");
        System.out.print("Name: ");
        String name = scanner.nextLine();
        validName = Utility.lettersOnlyOrNot(name) && name.length() < 30;
        while (!validName) {
            System.out.println("Please enter a valid first name");
            System.out.print("Name: ");
            name = scanner.nextLine();
            validName = Utility.lettersOnlyOrNot(name) && name.length() < 30;
        }
        System.out.println("Please enter your age");
        System.out.print("Age(years): ");
        String ageInput = scanner.nextLine();

        //To check whether the input is a number or not
        ageIsInt = Utility.numberOrNot(ageInput);
        if (!Utility.numberOrNot(ageInput)) {
            while (!ageIsInt) {
                System.out.println("Enter a numerical value(numbers only)");
                System.out.print("Age(years): ");
                ageInput = scanner.nextLine();
                ageIsInt = Utility.numberOrNot(ageInput);
            }
        }//This also helps in doing the thing below

        //To convert the string into an integer
        var age = Integer.parseInt(ageInput);
        ageIsOkay = age >= 13 && age <= 150;
        validAge = ageIsInt && ageIsOkay;
        while (!validAge) {
            if (!ageIsInt) {
                //Makes sure that the input is a number
                while (!ageIsInt) {
                    System.out.println("Enter a numerical value(numbers only from 13-150)");
                    System.out.print("Age(years): ");
                    ageInput = scanner.nextLine();
                    ageIsInt = Utility.numberOrNot(ageInput);
                }
            }

            //Had I not made the if statement above, the line below this might have not been able to execute
            age = Integer.parseInt(ageInput);
            ageIsOkay = age >= 13 && age <= 150;
            //To check that the input is not too big or small
            while (!ageIsOkay) {
                System.out.println("Enter a numerical value between 13-150");
                System.out.print("Age(years): ");
                ageInput = scanner.nextLine();
                if (!Utility.numberOrNot(ageInput)) {
                    //To stop this while loop and start while(!validAge) again according to the next to next if statement
                    break;
                } else {
                    age = Integer.parseInt(ageInput);
                    ageIsOkay = age >= 13 && age <= 150;
                }
            }

            ageIsInt = Utility.numberOrNot(ageInput);
            if (!ageIsInt) {
                validAge = false;
            } else {
                ageIsInt = Utility.numberOrNot(ageInput);
                age = Integer.parseInt(ageInput);
                ageIsOkay = age >= 13 && age <= 150;
                validAge = ageIsInt && ageIsOkay;
            }
        }
        do {
            System.out.println("Here is a list of all the seats available in this bus");
            for (int seatNumber = 0; seatNumber < seats.length; seatNumber++) {
                System.out.println(seats[seatNumber] + " ----------- " + freeOrNot.get(seatNumber));
            }

            System.out.println("Enter the seat you want to sit in");
            System.out.print("Seat: ");
            String seatInput = scanner.nextLine();
            String seat = seatInput.toUpperCase().trim();
            seatExists = Arrays.asList(seats).contains(seat);
            if (!Arrays.asList(seats).contains(seat)) {
                while (!seatExists) {
                    System.out.println(seatInput + " does not exist");
                    System.out.println("Enter the seat you want to sit in");
                    System.out.print("Seat: ");
                    seatInput = scanner.nextLine();
                    seat = seatInput.toUpperCase().trim();
                    seatExists = Arrays.asList(seats).contains(seat);
                }
            }
            int binaryIndex = Arrays.binarySearch(seats, seat);
            int index = (binaryIndex < 0) ? -1 : binaryIndex;
            seatIsAvailable = freeOrNot.get(index).equals("available");
            seatCanBeBooked = seatExists && seatIsAvailable;
            while (!seatCanBeBooked) {
                while (!seatExists) {
                    System.out.println(seatInput + " does not exist");
                    System.out.println("Enter the seat you want to sit in");
                    System.out.print("Seat: ");
                    seatInput = scanner.nextLine();
                    seat = seatInput.toUpperCase().trim();
                    seatExists = Arrays.asList(seats).contains(seat);
                }
                binaryIndex = Arrays.binarySearch(seats, seat);
                index = (binaryIndex < 0) ? -1 : binaryIndex;
                seatIsAvailable = freeOrNot.get(index).equals("available");
                while (!seatIsAvailable) {
                    System.out.println(seat + " has already been booked");
                    System.out.println("Enter the seat you want to sit in");
                    System.out.print("Seat: ");
                    seatInput = scanner.nextLine();
                    seat = seatInput.toUpperCase().trim();
                    if (!Arrays.asList(seats).contains(seat)) {
                        break;
                    }
                    binaryIndex = Arrays.binarySearch(seats, seat);
                    index = (binaryIndex < 0) ? -1 : binaryIndex;
                    seatIsAvailable = freeOrNot.get(index).equals("available");
                }
                seatExists = Arrays.asList(seats).contains(seat);
                if (!seatExists) {
                    seatCanBeBooked = false;
                } else {
                    seatExists = Arrays.asList(seats).contains(seat);
                    binaryIndex = Arrays.binarySearch(seats, seat);
                    index = (binaryIndex < 0) ? -1 : binaryIndex;
                    seatIsAvailable = freeOrNot.get(index).equals("available");
                    seatCanBeBooked = seatExists && seatIsAvailable;

                }
            }
            System.out.println("Seat number " + seat + " has been successfully booked");
            freeOrNot.set(index, "unavailable");
            seatsBooked.add(seat);
            System.out.println("Would you like to book another seat?");
            System.out.print("Enter either yes or no: ");
            String bookMoreInput = scanner.nextLine();
            String bookMore = bookMoreInput.toLowerCase().trim();
            answerIsValid = bookMore.equals("yes") || bookMore.equals("no");
            while (!answerIsValid) {
                System.out.print("Enter either yes or no: ");
                bookMoreInput = scanner.nextLine();
                bookMore = bookMoreInput.toLowerCase().trim();
                answerIsValid = bookMore.equals("yes") || bookMore.equals("no");
            }
            seatsAreAvailable = freeOrNot.contains("available");
            if (seatsAreAvailable) {
                isBooking = bookMore.equals("yes");
            } else {
                System.out.println("There are no more available seats");
                isBooking = false;
            }

        } while (isBooking);

        System.out.println("Here is the list of all the seats you have booked.");
        for (int displayBookedSeats = 0; displayBookedSeats < seatsBooked.size(); displayBookedSeats++){
            System.out.println((displayBookedSeats + 1) + ". --- " + seatsBooked.get(displayBookedSeats));
        }
        String plural = "";
        if (seatsBooked.size() > 1){
            plural = "s";
        }
        String namePrint = name.substring(0, 1).toUpperCase() + name.substring(1);
        System.out.println("Thank you for using MyBus.com " + namePrint + ". We have booked " + seatsBooked.size() + " seat" + plural + " for you.\nWe hope you were satisfied with our service. We will see you at the bus!");
    }
}

