package com.pluralsight;

import java.time.LocalDate;
import java.time.LocalTime;

public class Transaction {

    private LocalDate date;
    private LocalTime time;
    private String description;
    private String vendor;
    private double amount;
    private double balance;

public Transaction(LocalDate date, LocalTime time, String description, String vendor, double amount){
    this.date = date;
    this.time = time;
    this.description = description;
    this.vendor = vendor;
    this.amount = amount;
    this.balance = balance;
    }


    public LocalDate getDate() {
        return date;
    }


    public String getDescription() {
        return description;
    }


    public String getVendor() {
        return vendor;
    }


    public double getAmount() {
        return amount;
    }

    double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public String getLedgerTextFormatted() {
        String colorCode = this.amount < 0 ? "\u001B[31m" : "\u001B[34m"; // red or blue
        String resetCode = "\u001B[0m";

        return String.format("%-12s %-10s %-30s %-20s %s%12.2f%s",
                date, time, description, vendor, colorCode, amount, resetCode);
    }

    public static String getLedgerTextHeaderFormatted() {
        return "\n" +
                String.format("%-12s %-10s %-30s %-20s %12s",
                        "DATE", "TIME", "DESCRIPTION", "VENDOR", "AMOUNT ($)") + "\n" +
                "------------ ---------- ------------------------------ -------------------- ------------";
    }
}







