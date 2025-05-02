package com.pluralsight;

public class Report {
    public static Console console = new Console();


    public static String reportMenu() {

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

        double option;

        do {
            option = console.promptForInt(reportMenu());
            switch ((int) option) {
                case 1:
                    //;
                    break;
                case 2:
                    //;
                    break;
                case 3:
                    //;
                    break;
                case 4:
                    //;
                    break;
                case 5:
                    //Report.;
                    break;
                case 6:
                    //Report.;
                    break;
                case 7:
                    System.out.println("Exiting... Have a great day, and continue to be financially responsible. \n");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again");
            }
        } while (option != 0);

    }
}
