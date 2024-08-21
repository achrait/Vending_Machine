package com.techelevator.vend;

import com.techelevator.items.Item;

import java.text.NumberFormat;
import java.util.Locale;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Menu {

    private final Scanner SCANNER;
    private final VendingMachine vendingMachine;

    public Menu(VendingMachine vendingMachine) {
        this.vendingMachine = vendingMachine;
        this.SCANNER = new Scanner(System.in);
    }

    public void mainMenu()
    {
        System.out.println("*****************************");
        System.out.println("*      Vending Machine      *");
        System.out.println("*****************************");
        boolean exit = true;

        while (exit) {
            System.out.println("(1) Display Vending Machine Items");
            System.out.println("(2) Purchase");
            System.out.println("(3) Exit");
            System.out.println("Please enter your choice: ");
            try {
              String line = SCANNER.next();
              int choice = Integer.parseInt(line);
              
              switch (choice){
                case 1:
                    System.out.println(vendingMachine.slotsToString());
                    break;
                case 2 :
                    purchaseMenu();
                    break;
                case 3 :
                    System.out.println("Thank you for using Vend-O-Matic!");
                    exit=false;
                    break;
                case 4 :
                    VendLog.generateSalesReport(vendingMachine.getSTARTING_TIMESTAMP());
                    System.out.println("Sales report generated.");

                    break;
                default:
                    System.out.println("invalid number try again please ");
             }
            } catch (NumberFormatException e ) {
                System.out.println("ERROR: Insert a number");
            }
            
            
        }
    }

    public void purchaseMenu(){

        boolean exit = true;
        NumberFormat nF = NumberFormat.getCurrencyInstance(Locale.US);

        while (exit) {
            System.out.println("Current money provided: " + nF.format(vendingMachine.getBalance()));
            System.out.println("\n(1) Feed Money\n(2) Select Product\n(3) Finish Transaction");
            try {
               String line = SCANNER.next();
                int choice = Integer.parseInt(line);
                switch (choice) {
                case 1:
                    feedCash();
                    break;
                case 2 :
                    selectProduct();
                    break;
                case 3 :
                    finishTransaction();
                    exit=false;
                    break;
                default:
                    System.out.println("invalid number try again please "); 
                }
            } catch (NumberFormatException e) {
                System.out.println("ERROR: Insert a number");
            }
            
            }
        }
    

    public void selectProduct(){
        System.out.println(vendingMachine.slotsToString());
        System.out.println("Input a product code: ");
        try {
            String slotKey = SCANNER.next();
            Pattern pattern = Pattern.compile("[a-d][1-4]", Pattern.CASE_INSENSITIVE);
            Matcher matcher = pattern.matcher(slotKey);
            boolean match = matcher.find();
            if (match) {
               slotKey = slotKey.toUpperCase();
                buyItem(slotKey); 
            } else {
                throw new IllegalArgumentException("ERROR: Input the code in the [A-D][1-4] format");
            }
        } catch (IllegalArgumentException e) {
            System.out.println("Bad input. Please make another selection.");
        }

    }

    public void buyItem(String slotKey){
        Slot slot = vendingMachine.getSlots().get(slotKey);
        double balance = vendingMachine.getBalance();;

        if(slot.getQuantity() <= 0) {
            System.out.println(slot.getSlottedItem().getName() + " is SOLD OUT!");
        } else if (slot.getSlottedItem().getPrice() > balance){
            System.out.println("Insufficient funds.");
        } else {
            vendingMachine.getSlots().get(slotKey).decrementQuantity();
            double newBalance = balance - slot.getSlottedItem().getPrice();
            vendingMachine.setBalance(newBalance);
            VendLog.logSale(slot, balance, newBalance);
            Item item = slot.getSlottedItem();
            System.out.println(item.getSoundEffect());
            System.out.println(item.getName() + " dispensed for " +
                    NumberFormat.getCurrencyInstance(Locale.US).format(item.getPrice()) + ".");
        }
    }

    public void feedCash(){
        System.out.println("Enter the cash you are depositing in whole dollar amounts: ");
        try {
            String line = SCANNER.next();
            double fedCash = vendingMachine.parseFedCash(line);
            vendingMachine.feedCash(fedCash);
            double newBalance = vendingMachine.getBalance();
            VendLog.logFeedMoney(fedCash, newBalance);
        } catch (Exception e) {
           System.out.println("ERROR: " + e.getMessage());
        }
        
    }

    public void finishTransaction(){
        System.out.println(vendingMachine.giveChange());
    }

}
