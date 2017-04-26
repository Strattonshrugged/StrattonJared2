/**
 Created By Jared Stratton on 4/15/17.
 */

import java.util.*;

public class StrattonJared2 {

    public static void main(String[] args)  {
        Scanner MenuSelect = new Scanner(System.in);
        int MainMenu = 0;
        String MMInput;
        int MMInt;

        // I wanted a main menu, it looked stupid with only two options, and so this happened
        System.out.println("GREETINGS PROFESSOR FALKEN ... I MEAN LEE ...");
        System.out.println("WOULD YOU LIKE TO PLAY A GAME?");
        System.out.println("");
        System.out.println("1. Investment Calculator");
        System.out.println("2. Distance Calculator");
        System.out.println("3. Global Thermonuclear War");
        System.out.println("Please make a selection and enter the number: ");

        while (MainMenu == 0) {
            MMInput = MenuSelect.nextLine();
            MMInput = MMInput.replaceAll("[^\\d.]", "");
            // try to parse the string for an integer
            try {
                MMInt = Integer.parseInt(MMInput);
                // if parsing Input for some kind of integer WORKS, make sure that value is actually an integer
                if (MMInt == (int)MMInt)    {
                    // everything checks out, exit loop by changing condition
                    MainMenu = MMInt;
                    if (MainMenu == 1)   {
                        InvestmentCalculator();
                    }   else if (MainMenu == 2)    {
                        DistanceCalculator();
                    }   else if (MainMenu == 3) {
                        System.out.println("Really? Because that worked out so well for Matthew Broderick ...");
                        System.out.println("Try something else.");
                        MainMenu = 0;
                    }   else    {
                        System.out.println("Error: Invalid Selection");
                        MainMenu = 0;
                    }
                }
            }
            catch (NumberFormatException someNameGoesHere) {
                System.out.println("Error: I do not understand. Please enter the number of the activity.");
            }
        }   // end of while loop Main Menu
    }   // end of Main Method

    // Program Two, Part One
    public static void InvestmentCalculator()   {
        System.out.println("Investment Calculator Is Online");
        Scanner console = new Scanner(System.in);
        // declare variables that exist outside of for-loops
        // some variables have a default value which triggers the conditional statements of while-loopsas
        String RawInput;
        int IntInput;
        int Years = -1;
        double Balance = -1;
        double InterestRate = -1;
        double Contribution = -1;
        // when I didn't want to re-input the data I just flicked this variable over to 1, it's just for testing
        int TestMode = 0;
        if (TestMode == 1)  {
            Years = 4;
            Balance = 1000;
            InterestRate = 6.5;
            Contribution = 100;
        }

        // get value for years, doesn't have separate checking method because it's the only int
        while (Years == -1) {
            System.out.println("\nPlease enter the number of years you would like to calculate : ");
            // accept whatever is typed as a string, Input is just whatever gets typed
            RawInput = console.nextLine();
            // remove anything that is not a number from the string (found on stack overflow)
            RawInput = RawInput.replaceAll("[^\\d.]", "");
            // try to parse the string for an integer
            try {
                IntInput = Integer.parseInt(RawInput);
                // if parsing Input for some kind of integer WORKS, make sure that value is actually an integer
                if (IntInput == (int)IntInput)    {
                    // if that integer is negative, print an error
                    if (IntInput < 0)  {
                        System.out.println("Error: Numbers of years to be calculated must be positive");
                    }   else    {
                        // if that integer is positive, accept that as your value
                        Years = IntInput;
                        System.out.println(Years + " years will be calculated.");
                    }
                }   else {
                    System.out.println("Error: Please enter whole years.");
                }
            }
            // if parsing Input for some kind of an integer doesn't work, print an error message and reloop
            catch (NumberFormatException someNameGoesHere) {
                System.out.println("Error: Please indicate how many years you would like calculated.");
            }
        }   // end of while loop Years

        // get value for initial balance
        while (Balance == -1.0)   {
            System.out.println("\nPlease enter your initial investment : ");
            RawInput = console.nextLine();
            Balance = DoubleCheck(RawInput);
            if (Balance == -1.0)  {
                System.out.println("Error: Could not parse number from input");
            }
            if (Balance == -2.0) {
                System.out.println("Error: Initial investment must be a positive number or zero");
                Balance = -1.0;
            }
            if (Balance >= 0)   {
                System.out.printf("Initial deposit shall be $%.2f\n",Balance);
            }
        }

        // get value for interest rate
        while (InterestRate == -1.0)   {
            System.out.println("\nPlease set the interest rate to be calculated : ");
            RawInput = console.nextLine();
            InterestRate = DoubleCheck(RawInput);
            if (InterestRate == -1.0)  {
                System.out.println("Error: Could not parse number from input");
            }
            if (InterestRate == -2.0) {
                System.out.println("Error: Interest rate must be a positive number or zero");
                InterestRate = -1.0;
            }
            if (InterestRate >= 0)   {
                System.out.println("Interest rate set at " + InterestRate +"%");
            }
        }

        // get value for deposit
        while (Contribution == -1.0)   {
            System.out.println("\nPlease enter your expected annual deposit (contribution) : ");
            RawInput = console.nextLine();
            Contribution = DoubleCheck(RawInput);
            if (Contribution == -1.0)  {
                System.out.println("Error: Could not parse number from input");
            }
            if (Contribution == -2.0) {
                System.out.println("Error: Annual Deposit must be a positive number or zero");
                Contribution = -1.0;
            }
            if (Contribution >= 0)   {
                System.out.printf("Annual deposit expected : $%.2f\n",Contribution);
            }
        }

        // display header
        String InterestString = String.valueOf(InterestRate);
        String H1 = "\n  \t\t Current \t\t%s%%\t\t\t\t   New\n";
        String H2 = " Year \t Balance \t Interest\t Deposit \t Balance\n";
        String H3 = "======\t=========\t==========\t=========\t=========\n";

        System.out.printf(H1,InterestString);
        System.out.printf(H2);
        System.out.printf(H3);

        // create and print each year
        String Layout;
        int year;
        double InterestAmount;
        double NewBalance;

        for (year = 1; year < (Years + 1); year++) {
            InterestAmount = Balance * (InterestRate/100.0);
            // persistently had off-by-one error until this, had to cast as double
            InterestAmount = (double)Math.round(InterestAmount * 100) / 100;
            NewBalance = Balance + InterestAmount + Contribution;
            Layout = "%d" + "   \t" + "$%8.2f" + "   " + "$%9.2f" + "  " + "$%8.2f" + "   " + "$%8.2f\n";
            System.out.printf(Layout,year,Balance,InterestAmount,Contribution,NewBalance);
            Balance = NewBalance;
        }

    } // end of Investment Calculator Method



    // method to verify and clean inputs of double-type variables
    public static double DoubleCheck(String RawInput)  {
        // Remove everything from the string that is not a number
        RawInput = RawInput.replaceAll("[^\\d.]", "");
        try {
            double DoubleInput = Double.parseDouble(RawInput);
            // makes sure it is a double
            if (DoubleInput == (double)DoubleInput)    {
                // check to make sure it isn't negative
                if (DoubleInput < 0)  {
                    return -2.0;
                // I'm sure it's a double and it's positive
                }   else    {
                    return DoubleInput;
                }
            // else, NOT a double, just an input error
            }   else {
                return -1.0;
            }
        }
        // if the 'try' fails there was no double to parse, it's just an input error
        catch (NumberFormatException someNameGoesHere) {
            return -1.0;
        }
    }   // end of Doublecheck method



    // Program Two Part Two
    // Tried numerous times using arccos method in book
    // looked up Haversine Method and received a very close answer on the first try
    // this part does not have the input resiliency of the first part
    public static void DistanceCalculator() {
        System.out.println("Find the distance between two cities!");
        Scanner console = new Scanner(System.in);
        String RawInput;
        double Input;
        double Lat1 = 200.0;
        double Long1 = 200.0;
        double Lat2 = 200.0;
        double Long2 = 200.0;

        while (Lat1 == 200.0) {
            System.out.println("Please enter the latitude of point A in decimal format");
            RawInput = console.nextLine();
            Input = Double.parseDouble(RawInput);
            if ((Input <= -90.0) || (Input >= 90.0))  {
                System.out.println("Error: Latitude out of bounds");
            }   else    {
                Lat1 = Input;
            }
        }
        while (Long1 == 200.0) {
            System.out.println("Please enter the longitude of point A in decimal format");
            RawInput = console.nextLine();
            Input = Double.parseDouble(RawInput);
            if ((Input <= -180.0) || (Input >= 180.0))  {
                System.out.println("Error: Longitude out of bounds");
            }   else    {
                Long1 = Input;
            }
        }
        while (Lat2 == 200.0) {
            System.out.println("Please enter the latitude of point B in decimal format");
            RawInput = console.nextLine();
            Input = Double.parseDouble(RawInput);
            if ((Input <= -90.0) || (Input >= 90.0))  {
                System.out.println("Error: Latitude out of bounds");
            }   else    {
                Lat2 = Input;
            }
        }
        while (Long2 == 200.0) {
            System.out.println("Please enter the longitude of point B in decimal format");
            RawInput = console.nextLine();
            Input = Double.parseDouble(RawInput);
            if ((Input <= -180.0) || (Input >= 180.0))  {
                System.out.println("Error: Longitude out of bounds");
            }   else    {
                Long2 = Input;
            }
        }

        double Radius = 6372.795;
        //double Lat1 = 36.12;
        //double Long1 = -86.67;
        //double Lat2 = 33.94;
        //double Long2 = -118.40;
        double LatDiff = Math.toRadians(Lat1 - Lat2);
        double LongDiff = Math.toRadians(Long1 - Long2);
        double theA =
            Math.sin(LatDiff/2) *
            Math.sin(LatDiff/2) +
            Math.cos(Math.toRadians(Lat1)) *
            Math.cos(Math.toRadians(Lat2)) *
            Math.sin(LongDiff/2) *
            Math.sin(LongDiff/2);
        double theC = 2 * Math.atan2(Math.sqrt(theA), Math.sqrt(1-theA));
        // theC must be equal to DeltaSigma in the book's description
        double Answer = Radius * theC;

        System.out.printf("Radius of the Earth is set at %.3f km\n",Radius);
        System.out.printf("The distance between these points is %.3f km",Answer);
    }   // end of Distance Calculator method



}   // end of Class
