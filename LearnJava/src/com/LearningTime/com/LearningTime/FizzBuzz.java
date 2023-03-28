package com.LearningTime;

import java.util.Scanner;

public class FizzBuzz {
    static void fizzBuzzMain(){
        // Create Scanner object
        Scanner scanner = new Scanner(System.in);

        System.out.print("Number: ");
        //Usage of the Scanner object
        String input = scanner.nextLine();

        //Checks whether the input is a number or not
        if (!com.LearningTime.Utility.numberOrNot(input)){
            while(!Utility.numberOrNot(input)){
                System.out.println("Please input a valid whole number");
                System.out.print("Number: ");
                input = scanner.nextLine();
            }
        }

        //Had I not made that if statement above, this couldn't happen
        var number = Integer.parseInt(input);
         if (number % 5 == 0 && number % 3 == 0 ){
             //If the input is divisible by both 5 and 3, print "FizzBuzz"
             System.out.println("FizzBuzz");
         }else if (number % 5 == 0){
             //Else if the input is only divisible by 5, print "Fizz"
             System.out.println("Fizz");
         }else if (number % 3 == 0){
             //Else if the input is only divisible by 3, print "Buzz"
             System.out.println("Buzz");
         }else {
             //Else, just print the number
             System.out.println(number);
         }
    }
}
