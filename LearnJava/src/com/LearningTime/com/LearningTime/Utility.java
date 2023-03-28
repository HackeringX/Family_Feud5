package com.LearningTime;

class Utility {
    //To check if the input is a number or not
    static boolean numberOrNot(String input)
     {
         try
         {
             Integer.parseInt(input);
         }
         catch(NumberFormatException ex)
         {
             return false;
         }
         return true;
     }
    //To check if the input is a decimal or not
     static boolean decimalOrNot(String input)
     {
         try
         {
             Double.parseDouble(input);
         }
         catch(NumberFormatException ex)
         {
             return false;
         }
         return true;
     }
     static boolean validDateOrNot(int year, int month, int day)
     {
         boolean isLeapYear = ((year % 4 == 0) && (year % 100 != 0)) || (year % 400 == 0);

         //to check if the year is a leap year

         if (day < 1 || day > 31)
                 return false;

             // Handle February month with leap year
             if (month == 2)
             {
                 if (isLeapYear)
                     return (day <= 29);
                 else
                     return (day <= 28);
             }

             // Months of April, June, Sept and Nov must have number of days less than or equal to 30.
             if (month == 4 || month == 6 || month == 9 || month == 11)
                 return (day <= 30);

             return true;
         }
         static boolean lettersOnlyOrNot(String s) {
             // checks if the String is null
             if (s == null) {
                 return false;
             }
             int len = s.length();
             for (int i = 0; i < len; i++) {
                 // checks whether the character is not a letter
                 // if it is not a letter ,it will return false
                 if ((!Character.isLetter(s.charAt(i)))) {
                     return false;
                 }
             }
             return true;
         }


}
