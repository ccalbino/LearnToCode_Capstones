package com.pluralsight;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Objects;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    private static Console console = new Console();
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
            option = console.promptForString(homeScreenPrompt);
            switch (option) {
                case "D":
                    addTransaction(true);
                    break;
                case "P":
                    addTransaction(false);
                    break;
                case "L":
                    //x;
                    break;
                case "X":
                    System.out.println("Exiting... Have a great day, and continue to be financially responsible");
                default:
                    System.out.println("Invalid choice. Please try again");
            }
        } while(!option.equals("x"));
    }

    private static void addTransaction(boolean isDeposit){

        try{
            //Tell computer to open up the file(Re-create file overwriting any existing data)
            FileWriter fw = new FileWriter("FileWriter");
            BufferedWriter writer = new BufferedWriter(fw);

            LocalDate date = LocalDate.now();
            LocalTime time = LocalTime.now().withNano(0); //with nano removes nanoseconds to make time appear cleaner


            String description;
            String vendor;
            String transaction;
            double amount;

            description = console.promptForString("Enter description: ");
            vendor = console.promptForString("Enter vendor: ");
            amount = console.promptForDouble("Enter amount: ");

            if (!isDeposit){
                amount = -Math.abs(amount);
                //makes that even if a negative number is typed the computer makes it positive and the " - " makes it negative
            }

            //transaction = console.promptForString("%s|%s|%s|%s|%.2f", date, time, description, vendor, amount);
            transaction = String.format("%s|%s|%s|%s|%.2f", date, time, description, vendor, amount);

            //add my formatted transaction to file, and adds it to new line.
            writer.write(transaction);
            writer.newLine();
            System.out.println("Transaction Added.");

        }catch (Exception e) {
            System.out.println("Error writing to file.");
        }


    }
}