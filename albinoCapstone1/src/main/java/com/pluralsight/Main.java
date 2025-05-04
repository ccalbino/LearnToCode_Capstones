package com.pluralsight;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;


//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    private static Console console = new Console();
    public static ArrayList<Transaction> transactionList = new ArrayList<>(); //load transaction csv

    public static void main(String[] args) {
        showScreenHome();
    }

    public static void showScreenHome() {

        String homeScreenPrompt =
                """
                        \nHello, please select from the options below to continue.\s
                        D) Add Deposit\s
                        P) Make Payment (Debit)\s
                        L) Ledger\s
                        X) Exit\s
                        (D, P, L, X):\s
                        
                        """;

        String option;

        do {
            option = console.promptForString(homeScreenPrompt).toUpperCase();
            switch (option) {
                case "D":
                    addTransaction(true);
                    break;
                case "P":
                    addTransaction(false);
                    break;
                case "L":
                    Ledger.ledgerMenu(); //call ledgerMenu from class ledger
                    break;
                case "X":
                    System.out.println("Exiting... Have a great day, and continue to be financially responsible \n");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again \n");
            }
        } while (!option.equals("X"));
    }

    private static void addTransaction(boolean isDeposit) {

        // Ask if the user wants to enter a custom date
        String useCustomDate = console.promptForString("Would you like to enter a custom date? (Y/N): \n").toUpperCase();

        LocalDate date;
        if (useCustomDate.equals("Y")) {
            while (true) {
                try {
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
                    String dateInput = console.promptForString("Enter date (yyyy/MM/dd): \n");
                    date = LocalDate.parse(dateInput, formatter);
                    break;
                } catch (DateTimeException e) {
                    System.out.println("\nInvalid date format. Please try again (format: yyyy/MM/dd). \n");
                }
            }
        } else {
            date = LocalDate.now();
        }

        // Ask if the user wants to enter a custom time
        String useCustomTime = console.promptForString("Would you like to enter a custom time? (Y/N): \n").toUpperCase();

        LocalTime time;
        if (useCustomTime.equals("Y")) {
            while (true) {
                try {
                    String timeInput = console.promptForString("Enter time (HH:mm): \n");
                    time = LocalTime.parse(timeInput);
                    break;
                } catch (DateTimeException e) {
                    System.out.println("\nInvalid time format. Please try again (format: HH:mm). \n");
                }
            }
        } else {
            time = LocalTime.now().withNano(0);
        }


        String description = console.promptForString("Enter description: ");
        String vendor = console.promptForString("Enter vendor: ");
        double amount = console.promptForDouble("Enter amount: ");

        if (!isDeposit) {
            amount = -Math.abs(amount);
            //makes that even if a negative number is typed the computer makes it positive and the " - " makes it negative
        }
        try {
            //opens csv in append mode, so new transactions are added w/o deleting old ones
            FileWriter fw = new FileWriter("transactions.csv", true);
            BufferedWriter writer = new BufferedWriter(fw);


            //add my formatted transaction to file, and adds it to new line.
            writer.write(String.format("%s|%s|%s|%s|%.2f", date, time, description, vendor, amount));
            writer.newLine();
            System.out.println("\nTransaction Added. \n");

            writer.close();

        } catch (IOException e) {
            System.out.println("Error writing to file. \n");
        }
    }
}

//    private static void writeTransactionToFile(Transaction t){
//        //do the work of saving t to the file
//
//        try{
//            //Tell computer to open up the file(Re-create file overwriting any existing data)
//            FileWriter fw = new FileWriter("transactions.csv", true);
//
//            BufferedWriter writer = new BufferedWriter(fw);
//
//
//            //add my formatted transaction to file, and adds it to new line.
//            writer.write(String.format("%s|%s|%s|%s|%.2f", date));
//            writer.newLine();
//            System.out.println("Transaction Added.");
//
//            writer.close();
//
//        }catch (Exception e) {
//            System.out.println("Error writing to file.");
//        }
//    }
