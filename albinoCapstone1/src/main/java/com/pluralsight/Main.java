package com.pluralsight;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    private static Console console = new Console();
    public static ArrayList<Transaction> transactionList = new ArrayList<>();

    public static void main(String[] args) {


        showScreenHome();


    }

    public static void showScreenHome(){

        String homeScreenPrompt =
                "Hello, please select from the options below to continue. \n" +
                        "D) Add Deposit \n" +
                        "P) Make Payment (Debit) \n" +
                        "L) Ledger \n" +
                        "X) Exit \n" +
                        "(D, P, L, X): ";

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
                    System.out.println("Exiting... Have a great day, and continue to be financially responsible");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again");
            }
        } while(!option.equals("X"));
    }

    private static void addTransaction(boolean isDeposit){

        //give option to provide date later during refactor
        LocalDate date = LocalDate.now();
        LocalTime time = LocalTime.now().withNano(0); //with nano removes nanoseconds to make time appear cleaner


        String description = console.promptForString("Enter description: ");
        String vendor = console.promptForString("Enter vendor: ");
        double amount = console.promptForDouble("Enter amount: ");

        if (!isDeposit){
            amount = -Math.abs(amount);
            //makes that even if a negative number is typed the computer makes it positive and the " - " makes it negative
        }
        try{
            //Tell computer to open up the file(Re-create file overwriting any existing data)
            FileWriter fw = new FileWriter("transactions.csv", true);
            BufferedWriter writer = new BufferedWriter(fw);


            //add my formatted transaction to file, and adds it to new line.
            writer.write(String.format("%s|%s|%s|%s|%.2f", date, time, description, vendor, amount));
            writer.newLine();
            System.out.println("Transaction Added.");

            writer.close();

        }catch (Exception e) {
            System.out.println("Error writing to file.");
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

}