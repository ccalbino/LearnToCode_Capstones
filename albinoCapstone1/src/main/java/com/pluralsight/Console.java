package com.pluralsight;

import java.util.Scanner;

public class Console {

    Scanner scanner = new Scanner(System.in);

    public double promptForDouble(String prompt){
        boolean hasResult = false;
        double result = -1;
        while(!hasResult) {
            try{
                System.out.print(prompt);
                result = scanner.nextDouble()
            ;
                scanner.nextLine();
                hasResult = true;

            } catch (Exception e) {
                System.out.println("\nInvalid entry, please try again! \n");
                scanner.next();
            }
        }

        return result;

    }

    public double promptForInt(String prompt){
        boolean hasResult = false;
        int result = -1;
        while(!hasResult) {
            try{
                System.out.print(prompt);
                result = scanner.nextInt()
                ;
                scanner.nextLine();
                hasResult = true;

            } catch (Exception e) {
                System.out.println("Invalid entry, please try again!");
                scanner.next();
            }
        }

        return result;

    }



    public String promptForString(String prompt){
        System.out.print(prompt);
        return scanner.nextLine().trim();
    }

}
