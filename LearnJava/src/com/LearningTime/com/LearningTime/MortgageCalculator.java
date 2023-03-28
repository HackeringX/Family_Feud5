package com.LearningTime;

import java.text.NumberFormat;
import java.util.Scanner;

public class MortgageCalculator {
    static void calculations() {
        /*
        *** Mortgage Calculator ***
        */

        //Two constants that make the code readable
        final byte MONTHS_IN_A_YEAR = 12;
        final byte PERCENT = 100;

        //To check whether the user input is valid or not
        boolean principalIsInt;
        boolean principalIsOkay;
        boolean validPrincipal;
        boolean interestIsDec;
        boolean interestIsOkay;
        boolean validInterest;
        boolean periodIsInt;
        boolean periodIsOkay;
        boolean validPeriod;

        //A scanner object, which allow the user to input things, will be used later
        Scanner scanner = new Scanner(System.in);
        //To format the results
        NumberFormat moneyFormat = NumberFormat.getCurrencyInstance();


        //Principal
        System.out.print("Principal($1K-$100M): $");
        //Told that we will use the scanner object
        String principalInput = scanner.nextLine();

        //To check whether the input is a number or not
        principalIsInt = Utility.numberOrNot(principalInput);
        if (!Utility.numberOrNot(principalInput)){
            while (!principalIsInt) {
                System.out.println("Enter a numerical value(numbers only)");
                System.out.print("Principal($1K-$100M): $");
                principalInput = scanner.nextLine();
                principalIsInt = Utility.numberOrNot(principalInput);
            }
        }//This also helps in doing the thing below

        //To convert the string into an integer
        var principal = Integer.parseInt(principalInput);
        principalIsOkay = principal >= 1_000 && principal <= 100_000_000;
        validPrincipal = principalIsInt && principalIsOkay;
        while (!validPrincipal) {
            if (!principalIsInt) {
                //Makes sure that the input is a number
                while (!principalIsInt) {
                    System.out.println("Enter a numerical value(numbers only from $1k-$100M)");
                    System.out.print("Principal($1K-$100M): $");
                    principalInput = scanner.nextLine();
                    principalIsInt = Utility.numberOrNot(principalInput);
                }
            }

            //Had I not made the if statement above, the line below this might have not been able to execute
            principal = Integer.parseInt(principalInput);
            principalIsOkay = principal >= 1_000 && principal <= 100_000_000;
            //To check that the input is not too big or small
            while (!principalIsOkay) {
                System.out.println("Enter a numerical value between $1K-$100M");
                System.out.print("Principal($1K-$100M): $");
                principalInput = scanner.nextLine();
                if (!Utility.numberOrNot(principalInput)){
                    //To stop this while loop and start while(!validPrincipal) again according to the next to next if statement
                    break;
                }else{
                    principal = Integer.parseInt(principalInput);
                    principalIsOkay = principal >= 1_000 && principal <= 100_000_000;
                }
            }

            principalIsInt = Utility.numberOrNot(principalInput);
            if(!principalIsInt){
                validPrincipal = false;
            }else{
                principalIsInt = Utility.numberOrNot(principalInput);
                principal = Integer.parseInt(principalInput);
                principalIsOkay = principal >= 1_000 && principal <= 100_000_000;
                validPrincipal = principalIsInt && principalIsOkay;
            }

        }


        //Interest Rate
        System.out.print("Annual Interest Rate (in %): ");
        String interestInput = scanner.nextLine();

        interestIsDec = Utility.decimalOrNot(interestInput);
        if (!Utility.decimalOrNot(interestInput)){
            //Makes sure that the input is a number or decimal
            while (!interestIsDec) {
                System.out.println("Enter a decimal(decimal or number only)");
                System.out.print("Annual Interest Rate (in %): ");
                interestInput = scanner.nextLine();
                interestIsDec = Utility.decimalOrNot(interestInput);
            }
        }//This also helps in doing the thing below

        //To convert the string into a decimal
        double interest = Double.parseDouble((interestInput));
        interestIsOkay = !(interest <= 0) && !(interest > 30);
        validInterest = interestIsDec && interestIsOkay;
        while (!validInterest){
            if (!interestIsDec){
                //To check whether the input is a number or decimal
                while (!interestIsDec) {
                    System.out.println("Enter a decimal(decimal or number only)");
                    System.out.print("Annual Interest Rate (in %): ");
                    interestInput = scanner.nextLine();
                    interestIsDec = Utility.decimalOrNot(interestInput);
                }
            }

            //Again, had I not made the if statement above, the line below this might have not been able to execute
            interest = Double.parseDouble((interestInput));
            interestIsOkay = !(interest <= 0) && !(interest > 30);
            //Makes sure that the input is not to big or small
            while (!interestIsOkay) {
                System.out.println("Enter a numerical percentage between 0.01%-30%");
                System.out.print("Annual Interest Rate (in %): ");
                interestInput = scanner.nextLine();
                if (!Utility.decimalOrNot(interestInput)){
                    //To stop this while loop and start while(!validInterest) again according to the next to next if statement
                    break;
                }else {
                    interest = Double.parseDouble((interestInput));
                    interestIsOkay = !(interest <= 0) && !(interest > 30);
                }
            }

            interestIsDec = Utility.decimalOrNot(interestInput);
            if (!interestIsDec){
                validInterest = false;
            }else{
                interestIsDec = Utility.decimalOrNot(interestInput);
                interestIsOkay = !(interest <= 0) && !(interest > 30);
                validInterest = interestIsDec && interestIsOkay;
            }

        }


        //Period
        System.out.print("Period (Years): ");
        String periodInput = scanner.nextLine();

        //To check whether the input is a number or not
        periodIsInt = Utility.numberOrNot(periodInput);
        if (!Utility.numberOrNot(periodInput)){
            while (!periodIsInt) {
                System.out.println("Enter a numerical value(numbers only)");
                System.out.print("Period (Years): ");
                periodInput = scanner.nextLine();
                periodIsInt = Utility.numberOrNot(periodInput);
            }
        }//This also helps in doing the thing below

        //To convert the string into an integer
        var period = Integer.parseInt(periodInput);
        periodIsOkay = period >= 1 && period <= 30;
        validPeriod = periodIsInt && periodIsOkay;
        while (!validPeriod){
            if (!periodIsInt){
                //Makes sure that the input is a number
                while (!periodIsInt) {
                    System.out.println("Enter a numerical value(numbers only)");
                    System.out.print("Period (Years): ");
                    periodInput = scanner.nextLine();
                    periodIsInt = Utility.numberOrNot(periodInput);
                }
            }

            //Again, had I not made the if statement above, the line below this might have not been able to execute
            period = Integer.parseInt(periodInput);
            periodIsOkay = period >= 1 && period <= 30;
            //Makes sure that the input is not to big or small
            while (!periodIsOkay) {
                System.out.println("Enter a numerical period between 0-30 years");
                System.out.print("Period (Years): ");
                periodInput = scanner.nextLine();
                if (!Utility.numberOrNot(periodInput)){
                    //To stop this while loop and start while(!validInterest) again according to the next to next if statement
                    break;
                }else{
                    period = Integer.parseInt(periodInput);
                    periodIsOkay = period >= 1 && period <= 30;
                }
            }
            
            periodIsInt = Utility.numberOrNot(periodInput);
            if (!periodIsInt){
                validPeriod = false;
            }else{
                periodIsInt = Utility.numberOrNot(periodInput);
                periodIsOkay = period >= 1 && period <= 30;
                validPeriod = periodIsInt && periodIsOkay;
            }
        }


        //Just to clarify things
        double interestRate = interest / MONTHS_IN_A_YEAR / PERCENT;
        int months = period * MONTHS_IN_A_YEAR;

        //The formula to calculate result
        double mortgage = principal * ((interestRate * Math.pow((1 + interestRate), months)) / (Math.pow((1 + interestRate), months) - 1));
        //This is the total payment
        double payment = mortgage * months;
        //Formatting payment
        String paymentResult = moneyFormat.format(payment);
        //Formatting Result
        String result = moneyFormat.format(mortgage);
        //And FINALLY, printing result
        System.out.println("Mortgage(Monthly): " + result);
        System.out.println("Total Payment: " + paymentResult);
    }
}