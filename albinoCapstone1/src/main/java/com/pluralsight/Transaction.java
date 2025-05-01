package com.pluralsight;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Transaction {

    private LocalDate date;
    private LocalTime time;
    private String description;
    private String vendor;
    private double amount;

public Transaction(LocalDate date, LocalTime time, String description, String vendor, double amount){
    this.date = date;
    this.time = time;
    this.description = description;
    this.vendor = vendor;
    this.amount = amount;
    }


    public LocalDate getDate() {
        return date;
    }


    public LocalTime getTime() {
        return time;
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

    public String getFormattedToLog(){
        String dateString = this.date.toString();
        DateTimeFormatter x = DateTimeFormatter.ofPattern("HH:mm:ss");


        String result = String.format("%s|%s|%s|%s|%.2f",
               this.date,
                this.time,
                this.getDescription(),
                this.getVendor(),
                this.amount);


        return result;
    }


    public String getFormattedLedgerText() {
        return String.format("%-12s %-10s %-30s %-20s %10.2f",
                this.date, this.time, this.description, this.vendor, this.amount);
    }


    public static String getFormattedLedgerTextHeader() {
        return    "\nDATE         TIME       DESCRIPTION                    VENDOR               AMOUNT ($)\n"
                + "------------ ---------- ------------------------------ -------------------- ----------";
    }
}







