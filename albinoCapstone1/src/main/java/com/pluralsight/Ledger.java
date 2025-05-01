package com.pluralsight;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.List;

public class Ledger {

    public static Console console = new Console();
    private static List<Transaction> transactions = allEntries();


    public static void ledgerMenu(){

        String ledgerPrompt =
                "-----------\n" +
                "A) All Entries   - Displays all transactions\n" +
                "D) Deposits      - Displays only deposits\n" +
                "P) Payments      - Displays only payments\n" +
                "R) Reports       - Ability to search through all reports\n" +
                "H) Home          - Return to home page \n" +
                "\n" +
                "Please enter your selection";

    String option;

        do {
        option = console.promptForString(ledgerPrompt).toUpperCase();
        switch (option) {
            case "A":
                //All Entries;
                break;
            case "D":
                //Deposits;
                break;
            case "P":
                //Payments;
                break;
            case "R":
                //Reports;
                break;
            case "H":
                System.out.println("Exiting... Have a great day, and continue to be financially responsible");
                break;
            default:
                System.out.println("Invalid choice. Please try again");
        }
    } while(!option.equals("X"));


}

    public static List<Transaction> allEntries() {

        try {

            //open file and read
            FileReader fr = new FileReader("transactions.csv");
            BufferedReader reader = new BufferedReader(fr);
            Main.transactionList.clear(); //This clears data in file before adding new. Very important.





            return Main.transactionList; //returns whole transactionList

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


}
