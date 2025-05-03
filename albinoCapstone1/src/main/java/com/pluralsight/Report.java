package com.pluralsight;

import java.time.LocalDate;
import java.util.List;

public class Report {

    public static Console console = new Console();
    private static List<Transaction> transactions = Ledger.allEntries(); //loads all transactions from ledger class "allEntries" once report menu is accessed



    public static String reportMenu() {

        String ledgerPrompt =
                "-----------\n" +
                        "1) Month To Date \n" +
                        "2) Previous Month\n" +
                        "3) Year to Date\n" +
                        "4) Previous Year\n" +
                        "5) Search by Vendor\n" +
                        "6) Custom Search\n" +
                        "0) Back\n" +
                        "\n" +
                        "Please enter your selection \n";

        int option;

        do {
            option = (int) console.promptForInt(ledgerPrompt);
            switch ((int) option) {
                case 1:
                    displayMonthToDate();
                    break;
                case 2:
                    displayPreviousMonth();
                    break;
                case 3:
                    displayYearToDate();
                    break;
                case 4:
                    displayPreviousYear();
                    break;
                case 5:
                    searchByVendor();
                    break;
                case 6:
                    //Report.;
                    break;
                case 0:
                    System.out.println("Exiting back to Ledger screen... Have a great day, and continue to be financially responsible. \n");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again");
            }
        } while (option != 0);

        return ledgerPrompt;
    }

    //All transactions(+/-) from current month
    public static void displayMonthToDate() {
        LocalDate todayDate = LocalDate.now();
        System.out.println(Transaction.getLedgerTextHeaderFormatted());

        for (Transaction transaction : transactions) {
            if (transaction.getDate().getMonthValue() == todayDate.getMonthValue() &&
            transaction.getDate().getYear() == todayDate.getYear()){
                System.out.println(transaction.getLedgerTextFormatted());
            }
        }
    }

    //All transactions(+/-) from previous month
    public static void displayPreviousMonth() {
        LocalDate todayDate = LocalDate.now();
        LocalDate previousMonth = todayDate.minusMonths(1);
        System.out.println(Transaction.getLedgerTextHeaderFormatted());

        for (Transaction transaction : transactions) {
            if (transaction.getDate().getMonthValue() == previousMonth.getMonthValue() &&
            transaction.getDate().getYear() == previousMonth.getYear()) {
                System.out.println(transaction.getLedgerTextFormatted());
            }
        }

    }

    //All transactions(+/-) from current year
    public static void displayYearToDate() {
        LocalDate todayDate = LocalDate.now();
        System.out.println(Transaction.getLedgerTextHeaderFormatted());

        for (Transaction transaction : transactions) {
            if (transaction.getDate().getYear() == todayDate.getYear()) {
                System.out.println(transaction.getLedgerTextFormatted());
            }
        }
    }

    //All transactions(+/-) from previous year
    public static void displayPreviousYear() {
        LocalDate todayDate = LocalDate.now();
        System.out.println(Transaction.getLedgerTextHeaderFormatted());

        for (Transaction transaction : transactions) {
            if (transaction.getDate().getYear() == todayDate.minusYears(1).getYear()) {
                System.out.println(transaction.getLedgerTextFormatted());
            }
        }
    }

    //Get all transactions by user input for vendor
    public static void searchByVendor() {
        boolean keepSearching = true;

        while (keepSearching) {
            String vendor = console.promptForString("\nEnter vendor you would like to find: ").toLowerCase();

            boolean found = false;
            for (Transaction transaction : transactions) {
                if (transaction.getVendor().toLowerCase().contains(vendor)) {
                    if (!found) {
                        System.out.println(Transaction.getLedgerTextHeaderFormatted());
                        found = true;
                    }
                    System.out.println(transaction.getLedgerTextFormatted());
                }
            }

            if (!found) {
                System.out.println("\nVendor '" + vendor + "' not found.\n");
            }

            // Ask to search again
            String askAgain;
            while (true) {
                askAgain = console.promptForString("\nWould you like to search for another vendor? (Y/N): ").trim().toLowerCase();
                if (askAgain.equals("y")) {
                    break; // Continue outer loop
                } else if (askAgain.equals("n")) {
                    keepSearching = false; // Exit outer loop
                    break;
                } else {
                    System.out.println("Please enter 'Y' to search again or 'N' to return.");
                }
            }
        }
    }

    //Recursion: everytime I entered y the method called itself again
//    public static void searchByVendor() {
//        String vendor = console.promptForString("\nEnter vendor you would like to find: \n");
//
//
//        boolean found = false; //tracks if the vendor is found in csv file.
//        for (Transaction transaction : transactions) {
//            if (transaction.getVendor().toLowerCase().contains(vendor)) {
//                if (!found)
//                System.out.println(Transaction.getLedgerTextHeaderFormatted());
//                System.out.println(transaction.getLedgerTextFormatted());
//                found = true;
//            }
//        }
//        if (!found) {
//            System.out.println("\nVendor " + vendor + " not found.\n");
//        }
//        //Ask if user wants to search again instead of exiting
//        String askAgain;
//        while (true) {
//            askAgain = console.promptForString("\nWould you like to search for another vendor? (Y/N): ");
//            if (askAgain.equalsIgnoreCase("y")) {
//                searchByVendor();
//            } else if (askAgain.equalsIgnoreCase("n")) {
//                break;
//            } else {
//                System.out.println("Please enter 'Y' to search again or 'N' to return.");
//            }
//        }
//    }
}
