package com.pluralsight;

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
                case "D".equalsIgnoreCase("d"):
                    x;
                    break;
                case "P".equalsIgnoreCase("p"):
                    //x;
                    break;
                case "L".equalsIgnoreCase("L"):
                    //x;
                    break;
                case "X".equalsIgnoreCase("x"):
                    System.out.println("Exiting... Have a great day, and continue to be financially responsible");
                default:
                    System.out.println("Invalid choice. Please try again");
            }
        } while(!Objects.equals(option, "d"));



    }
}