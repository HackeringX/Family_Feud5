package com.LearningTime;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;

public class BankingOperations extends Accounts{
    static void createAccount(ArrayList<String> userName, ArrayList<String> userPassword, ArrayList<Integer> userBirthYear, ArrayList<Integer> userBirthMonth, ArrayList<Integer> userBirthDay, ArrayList<Double> checkingBalance, ArrayList<Double> savingsBalance, ArrayList<String> previousTransaction, ArrayList<Integer> accountNumber){
        boolean validUsername;
        boolean validPassword;
        boolean yearIsInt;
        boolean yearIsOkay;
        boolean validYear;
        boolean monthsIsInt;
        boolean monthIsOkay;
        boolean validMonth;
        boolean dayIsInt;
        boolean dayIsOkay;
        boolean validDayOfMonth;

        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter a Username for your account");
        System.out.print("Username: ");
        String newUsername = scanner.nextLine();
        validUsername = newUsername.length() >= 5 && newUsername.length() <= 15 && !userName.contains(newUsername);
        while (!validUsername){
            System.out.println("Your Username is either already available or your Username is not 5-15 characters long");
            System.out.print("Username: ");
            newUsername = scanner.nextLine();
            validUsername = newUsername.length() >= 5 && newUsername.length() <= 15 && !userName.contains(newUsername);
        }
        userName.add(newUsername);

        System.out.println("Enter a Password for your account");
        System.out.print("Password: ");
        String newPassword = scanner.nextLine();
        validPassword = newPassword.length() >= 5 && newPassword.length() <= 15 && !userPassword.contains(newPassword);
        while (!validPassword){
            System.out.println("Your Password is either already available or your Password is not 5-15 characters long");
            System.out.print("Password: ");
            newPassword = scanner.nextLine();
            validPassword = newPassword.length() >= 5 && newPassword.length() <= 15 && !userPassword.contains(newPassword);
        }
        userPassword.add(newPassword);

        Date today = new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(today);
        int currentYear = cal.get(Calendar.YEAR);
        System.out.println("Enter your Birth Year");
        System.out.print("Year: ");
        String yearInput = scanner.nextLine();

        //To check whether the input is a number or not
        yearIsInt = Utility.numberOrNot(yearInput);
        if (!Utility.numberOrNot(yearInput)){
            while (!yearIsInt) {
                System.out.println("Enter a numerical value(numbers only)");
                System.out.print("Year: ");
                yearInput = scanner.nextLine();
                yearIsInt = Utility.numberOrNot(yearInput);
            }
        }//This also helps in doing the thing below

        //To convert the string into an integer
        var year = Integer.parseInt(yearInput);
        yearIsOkay = year >= (currentYear - 150) && year <= (currentYear - 13);
        validYear= yearIsInt && yearIsOkay;
        while (!validYear) {
            if (!yearIsInt) {
                //Makes sure that the input is a number
                while (!yearIsInt) {
                    System.out.println("Enter a numerical value(numbers only)");
                    System.out.print("Year: ");
                    yearInput = scanner.nextLine();
                    yearIsInt = Utility.numberOrNot(yearInput);
                }
            }

            //Had I not made the if statement above, the line below this might have not been able to execute
            year = Integer.parseInt(yearInput);
            yearIsOkay = year >= (currentYear - 150) && year <= (currentYear - 13);
            //To check that the input is not too big or small
            while (!yearIsOkay) {
                System.out.println("The year has to be between 13-150 yrs before the current year");
                System.out.print("Year: ");
                yearInput = scanner.nextLine();
                if (!Utility.numberOrNot(yearInput)){
                    //To stop this while loop and start while(!validYear) again according to the next to next if statement
                    break;
                }else{
                    year = Integer.parseInt(yearInput);
                    yearIsOkay = year >= (currentYear - 150) && year <= (currentYear - 13);
                }
            }

            yearIsInt = Utility.numberOrNot(yearInput);
            if(!yearIsInt){
                validYear = false;
            }else{
                yearIsInt = Utility.numberOrNot(yearInput);
                year = Integer.parseInt(yearInput);
                yearIsOkay = year >= (currentYear - 150) && year <= (currentYear - 13);
                validYear = yearIsInt && yearIsOkay;
            }
        }
        userBirthYear.add(year);

        System.out.print("Month: ");
        String monthsInput = scanner.nextLine();

        //To check whether the input is a number or not
        monthsIsInt = Utility.numberOrNot(monthsInput);
        if (!Utility.numberOrNot(monthsInput)){
            while (!monthsIsInt) {
                System.out.println("Enter a numerical value(numbers only)");
                System.out.print("Month: ");
                monthsInput = scanner.nextLine();
                monthsIsInt = Utility.numberOrNot(monthsInput);
            }
        }//This also helps in doing the thing below

        //To convert the string into an integer
        var month = Integer.parseInt(monthsInput);
        monthIsOkay = month >= 1 && month <= 12;
        validMonth = monthsIsInt && monthIsOkay;
        while (!validMonth){
            if (!monthsIsInt){
                //Makes sure that the input is a number
                while (!monthsIsInt) {
                    System.out.println("Enter a numerical value(numbers only)");
                    System.out.print("Month: ");
                    monthsInput = scanner.nextLine();
                    monthsIsInt = Utility.numberOrNot(monthsInput);
                }
            }

            //Again, had I not made the if statement above, the line below this might have not been able to execute
            month = Integer.parseInt(monthsInput);
            monthIsOkay = month >= 1 && month <= 12;
            //Makes sure that the input is not to big or small
            while (!monthIsOkay) {
                System.out.println("Enter a numerical month value between 0-12");
                System.out.print("Month: ");
                monthsInput = scanner.nextLine();
                if (!Utility.numberOrNot(monthsInput)){
                    //To stop this while loop and start while(!validInterest) again according to the next to next if statement
                    break;
                }else{
                    month = Integer.parseInt(monthsInput);
                    monthIsOkay = month >= 1 && month <= 12;
                }
            }

            monthsIsInt = Utility.numberOrNot(monthsInput);
            if (!monthsIsInt){
                validMonth = false;
            }else{
                monthsIsInt = Utility.numberOrNot(monthsInput);
                monthIsOkay = month >= 1 && month <= 12;
                validMonth = monthsIsInt && monthIsOkay;
            }
        }
        userBirthMonth.add(month);

        System.out.print("Day of Month: ");
        String dayOfMonthInput = scanner.nextLine();

        //To check whether the input is a number or not
        dayIsInt = Utility.numberOrNot(dayOfMonthInput);
        if (!Utility.numberOrNot(dayOfMonthInput)){
            while (!dayIsInt) {
                System.out.println("Enter a numerical value(numbers only)");
                System.out.print("Day of Month: ");
                dayOfMonthInput = scanner.nextLine();
                dayIsInt = Utility.numberOrNot(dayOfMonthInput);
            }
        }//This also helps in doing the thing below

        //To convert the string into an integer
        var dayOfMonth = Integer.parseInt(dayOfMonthInput);
        dayIsOkay = Utility.validDateOrNot(year, month, dayOfMonth);
        validDayOfMonth = dayIsInt && dayIsOkay;
        while (!validDayOfMonth){
            if (!dayIsInt){
                //Makes sure that the input is a number
                while (!dayIsInt) {
                    System.out.println("Enter a numerical value(numbers only)");
                    System.out.print("Day of Month: ");
                    dayOfMonthInput = scanner.nextLine();
                    dayIsInt = Utility.numberOrNot(dayOfMonthInput);
                }
            }

            //Again, had I not made the if statement above, the line below this might have not been able to execute
            dayOfMonth = Integer.parseInt(dayOfMonthInput);
            dayIsOkay = Utility.validDateOrNot(year, month, dayOfMonth);
            //Makes sure that the input is not to big or small
            while (!dayIsOkay) {
                System.out.println("Enter a valid numerical day");
                System.out.print("Day of Month: ");
                dayOfMonthInput = scanner.nextLine();
                if (!Utility.numberOrNot(dayOfMonthInput)){
                    //To stop this while loop and start while(!validInterest) again according to the next to next if statement
                    break;
                }else{
                    dayOfMonth = Integer.parseInt(dayOfMonthInput);
                    dayIsOkay = Utility.validDateOrNot(year, month, dayOfMonth);
                }
            }
            dayIsInt = Utility.numberOrNot(dayOfMonthInput);
            if (!dayIsInt){
                validDayOfMonth = false;
            }else{
                dayIsInt = Utility.numberOrNot(dayOfMonthInput);
                dayIsOkay = Utility.validDateOrNot(year, month, dayOfMonth);
                validDayOfMonth = dayIsInt && dayIsOkay;
            }
        }
        userBirthDay.add(dayOfMonth);

        checkingBalance.add(0.0);
        savingsBalance.add(0.0);
        previousTransaction.add("No transaction taken yet");
        accountNumber.add(accountNumber.size() + 1);

    }

    static void account(ArrayList<Double> balance, int accountNum, ArrayList<String> previousTransaction, String accountName){

        boolean inAccountOrNot;
        boolean isChoiceOkay;
        boolean depositIsDec;
        boolean depositIsOkay;
        boolean validDeposit;
        boolean withdrawalIsDec;
        boolean withdrawalIsOkay;
        boolean validWithdrawal;
        double newBalance;

        Scanner scanner = new Scanner(System.in);
        //To format the results
        NumberFormat moneyFormat = NumberFormat.getCurrencyInstance();

        newBalance = balance.get(accountNum);
        System.out.println("You have entered your " + accountName + " Account");
        do {
            System.out.println("Type 1 to view balance");
            System.out.println("Type 2 to deposit money");
            System.out.println("Type 3 to withdraw money");
            System.out.println("Type 4 to view previous transaction");
            System.out.println("Type o to exit " + accountName + " Account");
            System.out.print("Choice: ");
            String choice4 = scanner.nextLine();
            isChoiceOkay = choice4.equals("1") || choice4.equals("2") || choice4.equals("3") || choice4.equals("4") || choice4.equals("o");
            while (!isChoiceOkay) {
                System.out.println("Enter any of the following");
                System.out.println("Type 1 to view balance");
                System.out.println("Type 2 to deposit money");
                System.out.println("Type 3 to withdraw money");
                System.out.println("Type 4 to view previous transaction");
                System.out.println("Type o to exit " + accountName + " Account");
                System.out.print("Choice: ");
                choice4 = scanner.nextLine();
                isChoiceOkay = choice4.equals("1") || choice4.equals("2") || choice4.equals("3") || choice4.equals("4") || choice4.equals("o");
            }
            String balanceResult;
            switch (choice4) {
                case "1":
                    balanceResult = moneyFormat.format(newBalance);
                    System.out.println("Balance: " + balanceResult);
                    inAccountOrNot = true;
                    break;
                case "2":
                    System.out.print("The amount you want to deposit: $");
                    String depositInput = scanner.nextLine();

                    //To check whether the input is a number or not
                    depositIsDec = Utility.decimalOrNot(depositInput);
                    if (!Utility.decimalOrNot(depositInput)) {
                        while (!depositIsDec) {
                            System.out.println("Enter a numerical value(numbers only)");
                            System.out.print("Deposit: $");
                            depositInput = scanner.nextLine();
                            depositIsDec = Utility.decimalOrNot(depositInput);
                        }
                    }//This also helps in doing the thing below

                    //To convert the string into an integer
                    var deposit = Double.parseDouble(depositInput);
                    depositIsOkay = deposit >= 1_000.0 && deposit <= 100_000_000.0;
                    validDeposit = depositIsDec && depositIsOkay;
                    while (!validDeposit) {
                        if (!depositIsDec) {
                            //Makes sure that the input is a number
                            while (!depositIsDec) {
                                System.out.println("Enter a numerical value(numbers only from $1k-$100M)");
                                System.out.print("Deposit: $");
                                depositInput = scanner.nextLine();
                                depositIsDec = Utility.decimalOrNot(depositInput);
                            }
                        }

                        //Had I not made the if statement above, the line below this might have not been able to execute
                        deposit = Double.parseDouble(depositInput);
                        depositIsOkay = deposit >= 1_000.0 && deposit <= 100_000_000.0;
                        //To check that the input is not too big or small
                        while (!depositIsOkay) {
                            System.out.println("Enter a numerical value between $1K-$100M");
                            System.out.print("Deposit: $");
                            depositInput = scanner.nextLine();
                            if (!Utility.decimalOrNot(depositInput)) {
                                //To stop this while loop and start while(!validDeposit) again according to the next if statement
                                break;
                            } else {
                                deposit = Double.parseDouble(depositInput);
                                depositIsOkay = deposit >= 1_000.0 && deposit <= 100_000_000.0;
                            }
                        }

                        depositIsDec = Utility.decimalOrNot(depositInput);
                        if (!depositIsDec) {
                            validDeposit = false;
                        } else {
                            depositIsDec = Utility.decimalOrNot(depositInput);
                            deposit = Double.parseDouble(depositInput);
                            depositIsOkay = deposit >= 1_000.0 && deposit <= 100_000_000.0;
                            validDeposit = depositIsDec && depositIsOkay;
                        }

                    }
                    newBalance += deposit;
                    String depositResult = moneyFormat.format(deposit);
                    balanceResult = moneyFormat.format(newBalance);
                    if (newBalance > 2_000_000_000.0) {
                        newBalance -= deposit;
                        System.out.println("We were unable to deposit because your balance was to large");
                        previousTransaction.set(accountNum, "Failed to deposit " + depositResult + " in " + accountName + " Account\nCurrent " + accountName + " Account Balance: " + balanceResult);
                    }else {
                        previousTransaction.set(accountNum, "Deposited " + depositResult + " in " + accountName + " Account");
                    }
                    System.out.println("Balance: " + balanceResult);
                    inAccountOrNot = true;
                    break;
                case "3":
                    System.out.print("The amount you want to withdraw: $");
                    String withdrawInput = scanner.nextLine();

                    //To check whether the input is a number or not
                    withdrawalIsDec = Utility.decimalOrNot(withdrawInput);
                    if (!Utility.decimalOrNot(withdrawInput)) {
                        while (!withdrawalIsDec) {
                            System.out.println("Enter a numerical value(numbers only)");
                            System.out.print("Withdrawal: $");
                            withdrawInput = scanner.nextLine();
                            withdrawalIsDec = Utility.decimalOrNot(withdrawInput);
                        }
                    }//This also helps in doing the thing below

                    //To convert the string into an integer
                    var withdrawal = Double.parseDouble(withdrawInput);
                    withdrawalIsOkay = withdrawal >= 1_000.0 && withdrawal <= 100_000_000.0;
                    validWithdrawal = withdrawalIsDec && withdrawalIsOkay;
                    while (!validWithdrawal) {
                        if (!withdrawalIsDec) {
                            //Makes sure that the input is a number
                            while (!withdrawalIsDec) {
                                System.out.println("Enter a numerical value(numbers only from $1k-$100M)");
                                System.out.print("Withdrawal: $");
                                withdrawInput = scanner.nextLine();
                                withdrawalIsDec = Utility.decimalOrNot(withdrawInput);
                            }
                        }

                        //Had I not made the if statement above, the line below this might have not been able to execute
                        withdrawal = Double.parseDouble(withdrawInput);
                        withdrawalIsOkay = withdrawal >= 1_000.0 && withdrawal <= 100_000_000.0;
                        //To check that the input is not too big or small
                        while (!withdrawalIsOkay) {
                            System.out.println("Enter a numerical value between $1K-$100M");
                            System.out.print("Withdrawal: $");
                            withdrawInput = scanner.nextLine();
                            if (!Utility.decimalOrNot(withdrawInput)) {
                                //To stop this while loop and start while(!validDeposit) again according to the next if statement
                                break;
                            } else {
                                withdrawal = Double.parseDouble(withdrawInput);
                                withdrawalIsOkay = withdrawal >= 1_000.0 && withdrawal <= 100_000_000.0;
                            }
                        }

                        withdrawalIsDec = Utility.decimalOrNot(withdrawInput);
                        if (!withdrawalIsDec) {
                            validWithdrawal = false;
                        } else {
                            withdrawalIsDec = Utility.decimalOrNot(withdrawInput);
                            withdrawal = Double.parseDouble(withdrawInput);
                            withdrawalIsOkay = withdrawal >= 1_000.0 && withdrawal <= 100_000_000.0;
                            validWithdrawal = withdrawalIsDec && withdrawalIsOkay;
                        }

                    }
                    String withdrawalResult = moneyFormat.format(withdrawal);
                    balanceResult = moneyFormat.format(newBalance);
                    if (withdrawal > newBalance) {
                        System.out.println("We were unable to withdraw because\nyour balance is less than your withdrawal");
                        previousTransaction.set(accountNum, "Failed to withdraw " + withdrawalResult + " in " + accountName + " Account\nCurrent " + accountName + " Account Balance: " + balanceResult);
                    } else {
                        newBalance -= withdrawal;
                        previousTransaction.set(accountNum, "Withdrawn " + withdrawalResult + " in " + accountName + " Account");
                    }
                    balanceResult = moneyFormat.format(newBalance);
                    System.out.println("Balance: " + balanceResult);
                    inAccountOrNot = true;
                    break;
                case "4":
                    System.out.println("Previous Transaction: " + previousTransaction.get(accountNum));
                    inAccountOrNot = true;
                    break;
                default:
                    balance.set(accountNum, newBalance);
                    inAccountOrNot = false;
            }
        }while (inAccountOrNot);
    }

}
