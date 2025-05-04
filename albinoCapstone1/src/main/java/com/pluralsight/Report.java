package com.pluralsight;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class Report {

    public static Console console = new Console();
    private static final List<Transaction> transactions = Ledger.allEntries(); //loads all transactions from ledger class "allEntries" once report menu is accessed


    public static void reportMenu() {

        String ledgerPrompt =
                """
                        -----------
                        1) Month To Date\s
                        2) Previous Month
                        3) Year to Date
                        4) Previous Year
                        5) Search by Vendor
                        6) Custom Search
                        0) Back
                        
                        Please enter your selection\s
                        """;

        int option;

        do {
            option = (int) console.promptForDouble(ledgerPrompt);
            switch (option) {
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
                    customSearch();
                    break;
                case 0:
                    System.out.println("Exiting back to Ledger screen... Have a great day, and continue to be financially responsible. \n");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again");
            }
        } while (option != 0);

    }

    //All transactions(+/-) from current month
    public static void displayMonthToDate() {
        LocalDate todayDate = LocalDate.now();
        System.out.println(Transaction.getLedgerTextHeaderFormatted());

        for (Transaction transaction : transactions) {
            if (transaction.getDate().getMonthValue() == todayDate.getMonthValue() &&
                    transaction.getDate().getYear() == todayDate.getYear()) {
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
                if (transaction.getVendor().toLowerCase().contains(vendor)) { //allows for a user-friendly partial search
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
                    System.out.println("\nPlease enter 'Y' to search again or 'N' to return.");
                }
            }
        }
    }

    //Custom Search based on user input
    public static void customSearch() {
        LocalDate startDate = null;
        LocalDate endDate = null;
        Double amountFilter = null;

        // Start Date input with validation
        while (true) {
            String startUserInput = console.promptForString("Enter Start Date (yyyy/MM/dd) or press Enter to skip \n");
            if (startUserInput.isBlank()) {
                break; // user chose to skip
            }
            try {
                startDate = LocalDate.parse(startUserInput, DateTimeFormatter.ofPattern("yyyy/MM/dd"));
                break;
            } catch (Exception e) {
                System.out.println("Invalid start date format. Please use (yyyy/MM/dd) ex.(2025/05/21)");
            }
        }

        // End Date input with validation
        while (true) {
            String endUserInput = console.promptForString("Enter End Date (yyyy/MM/dd) or press Enter to skip \n");
            if (endUserInput.isBlank()) {
                break;
            }
            try {
                endDate = LocalDate.parse(endUserInput, DateTimeFormatter.ofPattern("yyyy/MM/dd"));
                break;
            } catch (Exception e) {
                System.out.println("Invalid end date format. Please use (yyyy/MM/dd) ex.(2025/05/21)");
            }
        }

        String descriptionUserInput = console.promptForString("Enter description or press Enter to skip \n");
        String vendorUserInput = console.promptForString("Enter vendor or press Enter to skip \n");

        // Amount input with validation
        while (true) {
            String amountUserInput = console.promptForString("Enter amount (00.00) or press Enter to skip \n");
            if (amountUserInput.isBlank()) {
                break;
            }
            try {
                amountFilter = Double.parseDouble(amountUserInput);
                break;
            } catch (NumberFormatException e) {
                System.out.println("Invalid amount. Please enter a number.");
            }
        }

        if (transactions.isEmpty()) {
            System.out.println("No transactions found.");
            return;
        }

        boolean matchFound = false;
        for (Transaction transaction : transactions) {
            if (startDate != null && transaction.getDate().isBefore(startDate))
                continue;
            if (endDate != null && transaction.getDate().isAfter(endDate))
                continue;
            if (!descriptionUserInput.isBlank() && !transaction.getDescription().toLowerCase().contains(descriptionUserInput.toLowerCase()))
                continue;
            if (!vendorUserInput.isBlank() && !transaction.getVendor().toLowerCase().contains(vendorUserInput.toLowerCase()))
                continue;
            if (amountFilter != null && Math.abs(transaction.getAmount()) != Math.abs(amountFilter)) //allows for + or - amount filter
                continue;

            System.out.println(transaction.getLedgerTextFormatted());
            matchFound = true;
        }

        if (!matchFound) {
            System.out.println("No matching transactions found.");
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

