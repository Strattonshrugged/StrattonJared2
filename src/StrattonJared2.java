/**
 Created By Jared Stratton on 4/15/17.
 deficiency/bug: baseline Program 2 data yields answer off by 1-cent, rounding error?
 Cannot investigate without comparing code
 */

import java.util.*;
import java.text.DecimalFormat;

public class StrattonJared2 {
    public static void main(String[] args)  {

        // Part One of Program Two, Call
        InvestmentCalculator();

    }   // end of Main Method

    // Part One of Program Two, Method
    public static void InvestmentCalculator()   {
        System.out.println("Investment Calculator Is Online");
        // creating format for dollar values
        DecimalFormat bucks = new DecimalFormat("#.00");
        // declare variables that exist outside of for-loops
        // some variables have a default value which triggers the conditional statements of while-loopsas
        Scanner console = new Scanner(System.in);
        String RawInput;
        int IntInput;
        int Years = -1;
        // double DoubleInput was right here but it now exists only in the DoubleCheck function
        double Balance = -1;
        double InterestRate = -1;
        double Contribution = -1;

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
                System.out.println("Initial deposit shall be $" + bucks.format(Balance));
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
                System.out.println("Annual deposit expected : $" + bucks.format(Contribution));
            }
        }

        // display header
        String InterestString = String.valueOf(InterestRate);
        String H1 = "  \t\t Current \t\t%s%%\t\t\t\t   New\n";
        String H2 = " Year \t Balance \t Interest\t Deposit \t Balance\n";
        String H3 = "======\t=========\t==========\t=========\t=========\n";

        System.out.printf(H1,InterestString);
        System.out.printf(H2);
        System.out.printf(H3);

        // create and print each year
        String Y;
        int y;
        double Current;
        double InterestAmount;
        double Deposit = Contribution;

        Double NewBalance;
        for (y = 1; y < (Years + 1); y++) {
            NewBalance = Balance + (Balance * (InterestRate/100)) + Contribution;
            //  year      current  interest  deposit  newBalance
            Y = "%d\t\t$%f\t    $%f\t     $%f\t    $%f";
            /*
                + "\t\t$" + bucks.format(Balance)
                + "\t$" + bucks.format((Balance * (InterestRate/100)))
                + "\t$" + bucks.format(Contribution)
                + "\t$" + bucks.format(NewBalance)
                + "\n";
            */
            System.out.printf(Y,
                    bucks.format(Balance),
                    bucks.format((Balance * (InterestRate/100))),
                    bucks.format(Contribution),
                    bucks.format(NewBalance));
            Balance = NewBalance;
        }
    }

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
}   // end of Class
