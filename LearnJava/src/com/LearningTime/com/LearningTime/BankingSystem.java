package com.LearningTime;

import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.*;


public class BankingSystem extends BankingOperations{
    static void bankingApplication(){

        boolean inMyBankOrNot;
        boolean isChoice1Okay;
        boolean validAccount;
        boolean passwordPass;
        boolean validPassword;
        boolean newAccount;
        boolean accountChoiceIsValid;
        boolean inAccountOrNot;
        boolean isChoice5Okay;
        boolean isChoice6Okay;
        boolean inSettings;
        boolean validUsername;
        boolean validPassword2;
        boolean yearIsInt;
        boolean yearIsOkay;
        boolean yearWorksWithDayOfMonth;
        boolean validYear;
        boolean monthsIsInt;
        boolean monthIsOkay;
        boolean validMonth;
        boolean dayIsInt;
        boolean dayIsOkay;
        boolean validDayOfMonth;
        boolean validAccountNumber;
        int accountNum;

        Scanner scanner = new Scanner(System.in);


        System.out.println("|||||||||||****** Welcome to MyBank, the safest online bank ******|||||||||||");
        inMyBankOrNot = true;
        while (inMyBankOrNot) {
            System.out.println("\nType 1 to log in");
            System.out.println("Type 2 to create a new account");
            System.out.println("Type e to exit MyBank");
            System.out.print("Choice: ");
            String choice1 = scanner.nextLine();

            isChoice1Okay = choice1.equals("1") || choice1.equals("2") || choice1.equals("e");
            while (!isChoice1Okay) {
                System.out.println("Type either 1 or 2 or e to exit");
                System.out.println("Type 1 to log in");
                System.out.println("Type 2 to create a new account");
                System.out.println("Type e to exit MyBank");
                System.out.print("Choice: ");
                choice1 = scanner.nextLine();
                isChoice1Okay = choice1.equals("1") || choice1.equals("2") || choice1.equals("e");
            }
            if (choice1.equals("1")) {
                passwordPass = false;
                System.out.print("Enter Username: ");
                String choice2 = scanner.nextLine();

                validAccount = userName.contains(choice2);
                while (!validAccount) {
                    System.out.println("Enter your username or type q to create account");
                    System.out.print("Username: ");
                    choice2 = scanner.nextLine();
                    if (choice2.equals("q")) {
                        createAccount(userName, userPassword, userBirthYear, userBirthMonth, userBirthDay, checkingBalance, savingsBalance, previousTransaction, accountNumber);
                        choice2 = userName.get(userName.size() - 1);
                        passwordPass = true;
                        break;
                    }
                    validAccount = userName.contains(choice2);
                }
                String email = choice2 + "@mybank.com";
                newAccount = false;
                if (!passwordPass) {
                    System.out.println("Enter the password for " + email);
                    System.out.print("Password: ");
                    String choice3 = scanner.nextLine();
                    accountNum = userName.indexOf(choice2);
                    String password = userPassword.get(accountNum);
                    validPassword = choice3.equals(password);
                    while (!validPassword) {
                        System.out.println("Incorrect password, try again or type q to create account");
                        System.out.print("Password: ");
                        choice3 = scanner.nextLine();
                        if (choice3.equals("q")){
                            createAccount(userName, userPassword, userBirthYear, userBirthMonth, userBirthDay, checkingBalance, savingsBalance, previousTransaction, accountNumber);
                            newAccount = true;
                            validPassword = true;
                        }else {
                            accountNum = userName.indexOf(choice2);
                            password = userPassword.get(accountNum);
                            validPassword = choice3.equals(password);
                        }

                    }
                }
                if (newAccount){
                    accountNum = userPassword.indexOf(userPassword.get(userPassword.size() - 1));
                }else {
                    accountNum = userName.indexOf(choice2);
                }

                email = userName.get(accountNum) + "@mybank.com";
                System.out.println("Logged in " + email);
                System.out.println("Here is what you can do in your account, " + userName.get(accountNum));
                do {
                    System.out.println("Type 1 to access your Checking Account");
                    System.out.println("Type 2 to access your Savings Account");
                    System.out.println("Type 3 to view your account details");
                    System.out.println("Type l to log out");
                    System.out.print("Choice: ");
                    String accountChoice = scanner.nextLine();
                    accountChoiceIsValid = accountChoice.equals("1") || accountChoice.equals("2") || accountChoice.equals("3") || accountChoice.equals("l");
                    while (!accountChoiceIsValid){
                        System.out.println("Enter one of the choices bellow");
                        System.out.println("Type 1 to access your Checking Account");
                        System.out.println("Type 2 to access your Savings Account");
                        System.out.println("Type 3 to view your account details");
                        System.out.println("Type l to log out");
                        System.out.print("Choice: ");
                        accountChoice = scanner.nextLine();
                        accountChoiceIsValid = accountChoice.equals("1") || accountChoice.equals("2") || accountChoice.equals("3") || accountChoice.equals("l");
                    }
                    switch (accountChoice){
                        case "1":
                            account(checkingBalance, accountNum, previousTransaction, "Checking");
                            inAccountOrNot = true;
                            break;
                        case "2":
                            account(savingsBalance, accountNum, previousTransaction, "Savings");
                            inAccountOrNot = true;
                            break;
                        case "3":
                            int birthYear = userBirthYear.get(accountNum);
                            int birthMonth = userBirthMonth.get(accountNum);
                            int birthDay = userBirthDay.get(accountNum);
                            LocalDate date = LocalDate.of(birthYear, birthMonth, birthDay);
                            DateTimeFormatter formatDate = DateTimeFormatter.ofPattern("E, MMM dd yyyy");
                            String formattedDate = date.format(formatDate);
                            System.out.println("Birth Date: " + formattedDate);
                            System.out.println("Account Number: " + (accountNumber.get(accountNum)));
                            System.out.println("Would you like to change your settings");
                            System.out.print("Enter either yes or no: ");
                            String choice5Input = scanner.nextLine();
                            String choice5 = choice5Input.toLowerCase().trim();
                            isChoice5Okay = choice5.equals("yes") || choice5.equals("no");
                            while (!isChoice5Okay) {
                                System.out.println("Enter either yes or no");
                                System.out.print("Choice: ");
                                choice5Input = scanner.nextLine();
                                choice5 = choice5Input.toLowerCase().trim();
                                isChoice5Okay = choice5.equals("yes") || choice5.equals("no");
                            }
                            if (choice5.equals("yes")) {
                                System.out.println("To be able to change your settings, verify it's you who's doing this");
                                System.out.print("Password: ");
                                String accountPassword = scanner.nextLine();
                                if(accountPassword.equals(userPassword.get(accountNum))){
                                    inSettings = true;
                                }else {
                                    System.out.println("Sorry, only the owner of this account can change the settings");
                                    inSettings = false;
                                }
                                while (inSettings){
                                    System.out.println("Enter any of the following");
                                    System.out.println("Type 1 to change Username");
                                    System.out.println("Type 2 to change Password");
                                    System.out.println("Type 3 to change Birth Year");
                                    System.out.println("Type 4 to change Birth Month");
                                    System.out.println("Type 5 to change Birth Day");
                                    System.out.println("Type 6 to change Account Number");
                                    System.out.println("Type h to exit");
                                    System.out.print("Choice: ");
                                    String choice6 = scanner.nextLine();
                                    isChoice6Okay = choice6.equals("1") || choice6.equals("2") || choice6.equals("3") || choice6.equals("4")  || choice6.equals("5") || choice6.equals("6") || choice6.equals("h");
                                    while (!isChoice6Okay) {
                                        System.out.println("Type any of the following");
                                        System.out.println("Type 1 to change Username");
                                        System.out.println("Type 2 to change Password");
                                        System.out.println("Type 3 to change Birth Year");
                                        System.out.println("Type 4 to change Birth Month");
                                        System.out.println("Type 5 to change Birth Day");
                                        System.out.println("Type 6 to change Account Number");
                                        System.out.println("Type h to exit");
                                        System.out.print("Choice: ");
                                        choice6 = scanner.nextLine();
                                        isChoice6Okay = choice6.equals("1") || choice6.equals("2") || choice6.equals("3") || choice6.equals("4")  || choice6.equals("5") || choice6.equals("6") || choice6.equals("h");
                                    }
                                    switch (choice6) {
                                        case "1":
                                            System.out.println("Enter a Username for your account");
                                            System.out.print("Username: ");
                                            String newUsername = scanner.nextLine();
                                            validUsername = newUsername.length() >= 5 && newUsername.length() <= 15 && !userName.contains(newUsername);
                                            while (!validUsername){
                                                System.out.println("Your Username is either already available or your Username is not 5-15 characters long");
                                                System.out.println("Or type U to keep the current the current username");
                                                System.out.print("Username: ");
                                                newUsername = scanner.nextLine();
                                                if(newUsername.equalsIgnoreCase("u")){
                                                    validUsername = true;
                                                }else {
                                                    validUsername = newUsername.length() >= 5 && newUsername.length() <= 15 && !userName.contains(newUsername);
                                                }
                                            }
                                            if (!newUsername.equalsIgnoreCase("u")){
                                                userName.set(accountNum, newUsername);
                                                System.out.println("New Username: " + newUsername);
                                            }else{
                                                System.out.println("Current Username: " + userName.get(accountNum));
                                            }

                                            inSettings = true;
                                            break;
                                            case "2":
                                                System.out.println("Enter a Password for your account");
                                                System.out.print("Password: ");
                                                String newPassword = scanner.nextLine();
                                                validPassword2 = newPassword.length() >= 5 && newPassword.length() <= 15 && !userPassword.contains(newPassword);
                                                while (!validPassword2){
                                                    System.out.println("Your Password is either already available or your Password is not 5-15 characters long");
                                                    System.out.println("Or type P to keep the current password");
                                                    System.out.print("Password: ");
                                                    newPassword = scanner.nextLine();
                                                    if(newPassword.equalsIgnoreCase("p")){
                                                        validPassword2 = true;
                                                    }else{
                                                        validPassword2 = newPassword.length() >= 5 && newPassword.length() <= 15 && !userPassword.contains(newPassword);
                                                    }
                                                }
                                                if(newPassword.equalsIgnoreCase("p")){
                                                    System.out.println("Current Password: " + userPassword.get(accountNum));
                                                }else{
                                                    userPassword.set(accountNum, newPassword);
                                                    System.out.println("New Password: " + newPassword);
                                                }
                                                inSettings = true;
                                                break;
                                            case "3":
                                                Date today = new Date();
                                                Calendar cal = Calendar.getInstance();
                                                cal.setTime(today);
                                                int currentYear = cal.get(Calendar.YEAR);
                                                System.out.print("Enter a year: ");
                                                String yearInput = scanner.nextLine();

                                                //To check whether the input is a number or not
                                                yearIsInt = Utility.numberOrNot(yearInput);
                                                if (!Utility.numberOrNot(yearInput)) {
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
                                                yearWorksWithDayOfMonth = (Utility.validDateOrNot(year, userBirthMonth.get(accountNum), userBirthDay.get(accountNum)));
                                                validYear = yearIsInt && yearIsOkay && yearWorksWithDayOfMonth;
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
                                                    yearWorksWithDayOfMonth = true;
                                                    if(yearIsOkay){
                                                        yearWorksWithDayOfMonth = (Utility.validDateOrNot(year, userBirthMonth.get(accountNum), userBirthDay.get(accountNum)));
                                                    }
                                                    while (!yearWorksWithDayOfMonth){
                                                        System.out.println("The year has to be a leap year because your day and month of birth is 29 and February respectively");
                                                        System.out.print("Year: ");
                                                        yearInput = scanner.nextLine();

                                                        if (!Utility.numberOrNot(yearInput)) {
                                                            //To stop this while loop and start while(!validYear) again according to the next to next if statement
                                                            break;
                                                        }
                                                        year = Integer.parseInt(yearInput);
                                                        yearIsOkay = year >= (currentYear - 150) && year <= (currentYear - 13);
                                                        if(!yearIsOkay){
                                                            break;
                                                        }else {
                                                            year = Integer.parseInt(yearInput);
                                                            yearWorksWithDayOfMonth = (Utility.validDateOrNot(year, userBirthMonth.get(accountNum), userBirthDay.get(accountNum)));
                                                        }
                                                    }
                                                    if (Utility.numberOrNot(yearInput)){
                                                        yearIsOkay = true;
                                                    }else {
                                                        year = Integer.parseInt(yearInput);
                                                        yearIsOkay = year >= (currentYear - 150) && year <= (currentYear - 13);
                                                    }
                                                    //To check that the input is not too big or small
                                                    while (!yearIsOkay) {
                                                        System.out.println("The year has to be between 13-150 yrs before the current year");
                                                        System.out.print("Year: ");
                                                        yearInput = scanner.nextLine();
                                                        yearWorksWithDayOfMonth = (Utility.validDateOrNot(year, userBirthMonth.get(accountNum), userBirthDay.get(accountNum)));
                                                        if (!Utility.numberOrNot(yearInput)) {
                                                            //To stop this while loop and start while(!validYear) again according to the next if statement
                                                            break;
                                                        }
                                                        year = Integer.parseInt(yearInput);
                                                        if (!yearWorksWithDayOfMonth){
                                                            //To stop this while loop and start while(!validYear) again according to the next if statement
                                                            break;
                                                        } else {
                                                            year = Integer.parseInt(yearInput);
                                                            yearIsOkay = year >= (currentYear - 150) && year <= (currentYear - 13);
                                                        }
                                                    }

                                                    yearIsInt = Utility.numberOrNot(yearInput);
                                                    if (!yearIsInt) {
                                                        validYear = false;
                                                    } else {
                                                        yearIsInt = Utility.numberOrNot(yearInput);
                                                        year = Integer.parseInt(yearInput);
                                                        yearIsOkay = year >= (currentYear - 150) && year <= (currentYear - 13);
                                                        yearWorksWithDayOfMonth = (Utility.validDateOrNot(year, userBirthMonth.get(accountNum), userBirthDay.get(accountNum)));
                                                        validYear = yearIsInt && yearIsOkay && yearWorksWithDayOfMonth;
                                                    }
                                                }
                                                userBirthYear.set(accountNum, year);
                                                System.out.println("New Birth Year: " + userBirthYear.get(accountNum));
                                                inSettings = true;
                                                break;
                                            case "4":
                                                System.out.print("Month: ");
                                                String monthsInput = scanner.nextLine();

                                                //To check whether the input is a number or not
                                                monthsIsInt = Utility.numberOrNot(monthsInput);
                                                if (!Utility.numberOrNot(monthsInput)) {
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
                                                while (!validMonth) {
                                                    if (!monthsIsInt) {
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
                                                        System.out.println("Enter a numerical period between 0-12 months");
                                                        System.out.print("Month: ");
                                                        monthsInput = scanner.nextLine();
                                                        if (!Utility.numberOrNot(monthsInput)) {
                                                            //To stop this while loop and start while(!validInterest) again according to the next to next if statement
                                                            break;
                                                        } else {
                                                            month = Integer.parseInt(monthsInput);
                                                            monthIsOkay = month >= 1 && month <= 12;
                                                        }
                                                    }

                                                    monthsIsInt = Utility.numberOrNot(monthsInput);
                                                    if (!monthsIsInt) {
                                                        validMonth = false;
                                                    } else {
                                                        monthsIsInt = Utility.numberOrNot(monthsInput);
                                                        monthIsOkay = month >= 1 && month <= 12;
                                                        validMonth = monthsIsInt && monthIsOkay;
                                                    }
                                                }
                                                userBirthMonth.set(accountNum, month);
                                                // Create a YearMonth object
                                                YearMonth thisYearMonth = YearMonth.of(userBirthYear.get(accountNum), month);
                                                // Get the month field
                                                String newMonth = thisYearMonth.getMonth().toString().toLowerCase();
                                                System.out.println("New Birth Month: " + newMonth.substring(0, 1).toUpperCase() + newMonth.substring(1));
                                                inSettings = true;
                                                break;
                                            case "5":
                                                birthYear = userBirthYear.get(accountNum);
                                                birthMonth = userBirthMonth.get(accountNum);
                                                birthDay = userBirthDay.get(accountNum);

                                                System.out.print("Day of Month: ");
                                                String dayOfMonthInput = scanner.nextLine();

                                                //To check whether the input is a number or not
                                                dayIsInt = Utility.numberOrNot(dayOfMonthInput);
                                                if (!Utility.numberOrNot(dayOfMonthInput)) {
                                                    while (!dayIsInt) {
                                                        System.out.println("Enter a numerical value(numbers only)");
                                                        System.out.print("Day of Month: ");
                                                        dayOfMonthInput = scanner.nextLine();
                                                        dayIsInt = Utility.numberOrNot(dayOfMonthInput);
                                                    }
                                                }//This also helps in doing the thing below

                                                //To convert the string into an integer
                                                var dayOfMonth = Integer.parseInt(dayOfMonthInput);
                                                dayIsOkay = (Utility.validDateOrNot(birthYear, birthMonth, birthDay)) && (dayOfMonth < 32);
                                                validDayOfMonth = dayIsInt && dayIsOkay;
                                                while (!validDayOfMonth) {
                                                    if (!dayIsInt) {
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
                                                    dayIsOkay = (Utility.validDateOrNot(birthYear, birthMonth, birthDay)) && (dayOfMonth < 32);
                                                    //Makes sure that the input is not to big or small
                                                    while (!dayIsOkay) {
                                                        System.out.println("Enter a valid numerical day");
                                                        System.out.print("Day of Month: ");
                                                        dayOfMonthInput = scanner.nextLine();
                                                        if (!Utility.numberOrNot(dayOfMonthInput)) {
                                                            //To stop this while loop and start while(!validInterest) again according to the next to next if statement
                                                            break;
                                                        } else {
                                                            dayOfMonth = Integer.parseInt(dayOfMonthInput);
                                                            dayIsOkay = Utility.validDateOrNot(birthYear, birthMonth, birthDay) && dayOfMonth < 32;
                                                        }
                                                    }
                                                    dayIsInt = Utility.numberOrNot(dayOfMonthInput);
                                                    if (!dayIsInt) {
                                                        validDayOfMonth = false;
                                                    } else {
                                                        dayIsInt = Utility.numberOrNot(dayOfMonthInput);
                                                        dayIsOkay = Utility.validDateOrNot(birthYear, birthMonth, birthDay);
                                                        validDayOfMonth = dayIsInt && dayIsOkay;
                                                    }
                                                }
                                                userBirthDay.set(accountNum, dayOfMonth);
                                                System.out.println("New Birth Day: " + userBirthDay.get(accountNum));
                                                inSettings = true;
                                                break;
                                            case "6":
                                                System.out.print("Password: ");
                                                String ownerPassword = scanner.nextLine();

                                                if (ownerPassword.equals("MyBankSettings123")) {
                                                    System.out.print("Account Number: ");
                                                    String numberInput = scanner.nextLine();

                                                    //To check whether the input is a number or not
                                                    validAccountNumber = Utility.numberOrNot(numberInput);
                                                    if (!Utility.numberOrNot(numberInput)) {
                                                        while (!validAccountNumber) {
                                                            System.out.println("Enter a numerical value(numbers only)");
                                                            System.out.print("Account Number: ");
                                                            numberInput = scanner.nextLine();
                                                            validAccountNumber = Utility.numberOrNot(numberInput);
                                                        }
                                                    }//This also helps in doing the thing below
                                                    //To convert the string into an integer
                                                    var accountNumber = Integer.parseInt(numberInput);
                                                    Accounts.accountNumber.set(accountNum, accountNumber);
                                                    System.out.println("New Account Number: " + Accounts.accountNumber.get(accountNum));
                                                } else {
                                                    System.out.println("Only the owner can change your Account Number");
                                                }
                                                inSettings = true;
                                                break;
                                            default:
                                                inSettings = false;

                                        }

                                }
                            }
                            inAccountOrNot = true;
                            break;
                        default:
                            System.out.println("Logged out of " + email);
                            inAccountOrNot = false;
                    }
                } while (inAccountOrNot);
                inMyBankOrNot = true;

            }else if (choice1.equals("2")) {
                    createAccount(userName, userPassword, userBirthYear, userBirthMonth, userBirthDay, checkingBalance, savingsBalance, previousTransaction, accountNumber);
                    System.out.println("Account successfully created");
                    System.out.println("You will be automatically logged out");
                    System.out.println("Log in to use the account");
            }else{
                System.out.println("Thank you for using MyBank");
                inMyBankOrNot = false;
            }

        }
    }
}

