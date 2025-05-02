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
    private static List<Transaction> transactions = allEntries(); //loads all transactions once entered


    public static void ledgerMenu() {

        String ledgerPrompt =
                "-----------\n" +
                        "A) All Entries   - Displays all transactions\n" +
                        "D) Deposits      - Displays only deposits\n" +
                        "P) Payments      - Displays only payments\n" +
                        "B) Balance      -  Displays only balance\n" +
                        "R) Reports       - Ability to search through all reports\n" +
                        "H) Home          - Return to home page \n" +
                        "\n" +
                        "Please enter your selection \n";

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
    public static List<Transaction> allEntries() {
        ArrayList<Transaction> entries = new ArrayList<>();

        try {

            //open file and read
            FileReader fr = new FileReader("transactions.csv");
            BufferedReader reader = new BufferedReader(fr);
            Main.transactionList.clear(); //This clears data in file before adding new. Very important.

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
        System.out.println(Transaction.getLedgerTextHeaderFormatted());

        for (Transaction t : transactions) {
            System.out.println(t.getLedgerTextFormatted());

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
        transactions = allEntries();

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

    public static void calculateBalances() {
        double balance = 0;
        for (int i = transactions.size() -1; i >=  0; i--) {
            balance += transactions.get(i).getAmount();
            transactions.get(i).setBalance(balance);
        }
    }

    private static void displayBalances() {
        calculateBalances();
        double balance = transactions.isEmpty() ? 0 : transactions.get(0).getBalance();
        System.out.printf("Current balance: %.2f\n", balance);
    }

}

// add D and P from




         //returns whole transactionList




