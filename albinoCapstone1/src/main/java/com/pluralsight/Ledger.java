package com.pluralsight;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class Ledger {

    public static Console console = new Console();

    public static void ledgerMenu() {

        String ledgerPrompt =
                """
                        -----------
                        A) All Entries   - Displays all transactions
                        D) Deposits      - Displays only deposits
                        P) Payments      - Displays only payments
                        B) Balance      -  Displays only balance
                        R) Reports       - Ability to search through all reports
                        H) Home          - Return to home page\s
                        
                        Please enter your selection\s
                        """;

        String option;

        do {
            option = console.promptForString(ledgerPrompt).toUpperCase();
            switch (option) {
                case "A":
                    displayAllTransactions();
                    break;
                case "D":
                    displayDepositsOrPayments(option);
                    break;
                case "P":
                    displayDepositsOrPayments(option);
                    break;
                case "B":
                    displayBalances();
                    break;
                case "R":
                    Report.reportMenu();
                    break;
                case "H":
                    System.out.println("Exiting... Have a great day, and continue to be financially responsible. \n");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again");
            }
        } while (!option.equals("H"));


    }

    public static List<Transaction> allEntries() { //loads transactions from csv file
        ArrayList<Transaction> entries = new ArrayList<>();

        try {

            //open file and read
            FileReader fr = new FileReader("transactions.csv");
            BufferedReader reader = new BufferedReader(fr);
            Main.transactionList.clear(); //This clears data in file before adding new.

            String dataString;

            while ((dataString = reader.readLine()) != null) {
                entries.add(getTransactionFromEncodedString(dataString));


            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return entries;
    }


    public static void displayAllTransactions() {
        List<Transaction> transactions = allEntries(); //loads all transactions and prints below
        System.out.println(Transaction.getLedgerTextHeaderFormatted());

        for (Transaction transaction : transactions) {
            System.out.println(transaction.getLedgerTextFormatted());
        }
    }

    public static Transaction getTransactionFromEncodedString(String encodedTransaction) {
        String[] temp = encodedTransaction.split(Pattern.quote("|"));

        //assigns section each part is in
        LocalDate date = LocalDate.parse(temp[0]);
        LocalTime time = LocalTime.parse(temp[1]);
        String description = temp[2];
        String vendor = temp[3];
        double amount = Double.parseDouble(temp[4]);

        return new Transaction(date, time, description, vendor, amount);
    }


    public static void displayDepositsOrPayments(String option) {
        List<Transaction> transactions = allEntries();

        System.out.println(Transaction.getLedgerTextHeaderFormatted());

        for (Transaction transaction : transactions) {
            if (option.equalsIgnoreCase("d") && transaction.getAmount() < 0) {
                continue;
            }
            if (option.equalsIgnoreCase("p") && transaction.getAmount() > 0) {
                continue;
            }
            System.out.println(transaction.getLedgerTextFormatted());
        }
    }

    public static void calculateBalances(List<Transaction> transactions) {
        double balance = 0;
        for (int i = transactions.size() - 1; i >= 0; i--) {
            balance += transactions.get(i).getAmount();
            transactions.get(i).setBalance(balance);
        }
    }

    private static void displayBalances() {
        List<Transaction> transactions = allEntries(); // always a fresh list
        calculateBalances(transactions);
        //if no transactions set balance to 0, otherwise use the most recent balance
        double balance = transactions.isEmpty() ? 0 : transactions.getFirst().getBalance();

        String colorCode = balance < 0 ? "\u001B[31m" : "\u001B[34m"; // Red for negative, blue for positive
        String resetCode = "\u001B[0m";

        System.out.printf("Current balance: %s$%.2f%s\n", colorCode, balance, resetCode);
    }
}
