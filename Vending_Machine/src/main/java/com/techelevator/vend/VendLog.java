package com.techelevator.vend;

import java.io.*;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Locale;
import java.util.Scanner;

public class VendLog {

    public static final String DEFAULT_LOG_FILEPATH = "logs/Log.txt";
    private final static DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("MM/dd/yyyy HH:mm:ss a");

    public static void log(String message) {
        try (FileWriter writer = new FileWriter(DEFAULT_LOG_FILEPATH, true)) {
            writer.write(message + System.lineSeparator());
        } catch (IOException e) {
            System.err.println("IOException: " + e.getMessage());
        }
    }

    public static String generateMessage(LocalDateTime dateTime, String transactionType, double oldCash, double newCash){
        NumberFormat nF = NumberFormat.getCurrencyInstance(Locale.US);
        return dateTime.format(myFormatObj) + " " + String.format("%1$-18s", transactionType) + " " + nF.format(oldCash) + " " + nF.format(newCash);
    }

    public static void logSale(Slot slot, double oldBalance, double newBalance){
        LocalDateTime timestamp = LocalDateTime.now();
        String transactionType = slot.getSlottedItem().getName() + " " + slot.getName();
        log(generateMessage(timestamp, transactionType, oldBalance, newBalance));
    }

    public static void logFeedMoney(double moneyFed, double newBalance){
        LocalDateTime timestamp = LocalDateTime.now();
        String transactionType = "FEED MONEY:";
        log(generateMessage(timestamp, transactionType, moneyFed, newBalance));
    }

    public static void logGiveChange(double oldBalance){
        LocalDateTime timestamp = LocalDateTime.now();
        String transactionType = "GIVE CHANGE:";
        log(generateMessage(timestamp, transactionType, oldBalance, 0.00));
    }

    public static void generateSalesReport(LocalDateTime startingTimestamp){
        String fileName = "logs/Log";
                fileName += new SimpleDateFormat("MM_dd_yyyy_HH_mm_ss").format(new Date());
                fileName += ".txt";
        File inputFile = new File(DEFAULT_LOG_FILEPATH);
        try (
                Scanner dataInput = new Scanner(inputFile);
                PrintWriter dataOutput = new PrintWriter(fileName)
            )
        {
            while(dataInput.hasNextLine()) {
                String lineOfInput = dataInput.nextLine();
                String splitLine = lineOfInput.substring(0, 22);
                LocalDateTime lineTimestamp = LocalDateTime.parse(splitLine, myFormatObj);
                if(startingTimestamp.isBefore(lineTimestamp) || startingTimestamp.isEqual(lineTimestamp)){
                    dataOutput.println(lineOfInput);
                }
            }
        } catch (FileNotFoundException e) {
            System.err.println("Cannot open the file for writing." + e.getMessage());
        }
    }

}
