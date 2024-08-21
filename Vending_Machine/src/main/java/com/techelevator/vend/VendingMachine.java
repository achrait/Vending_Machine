package com.techelevator.vend;

import java.text.NumberFormat;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Locale;
import java.util.Map;

public class VendingMachine {

    private Map<String, Slot> slots;
    private double balance;
    private final LocalDateTime STARTING_TIMESTAMP;

    public VendingMachine() {
        this.slots = Inventory.generateSlots();
        this.balance = 0.00;
        STARTING_TIMESTAMP = LocalDateTime.now();
    }

    public VendingMachine(String filepath) {
        this.slots = Inventory.generateSlots(filepath);
        this.balance = 0.00;
        STARTING_TIMESTAMP = LocalDateTime.now();
    }



    public void  feedCash(double fedCash){


        balance += fedCash;
    }

    public double parseFedCash(String fedCashStr){
        double fedCash = Double.parseDouble(fedCashStr);

        if (fedCash < 0){
            throw new RuntimeException("Must give whole number greater than zero");
        }

        if(fedCash % 1 != 0){
            throw new RuntimeException("Whole numbers only.");
        }

        return fedCash;
    }

    public Map<String, Slot> getSlots() {
        return slots;
    }

    public void setSlots(Map<String, Slot> slots) {
        this.slots = slots;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double moneyRemaining) {
        this.balance = moneyRemaining;
    }

    public LocalDateTime getSTARTING_TIMESTAMP() {
        return STARTING_TIMESTAMP;
    }

    public String slotsToString(){
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<String, Slot> slot : slots.entrySet()){
            sb.append(slot.getKey());
            sb.append(": ");
            sb.append(String.format("%1$-18s",slot.getValue().getSlottedItem().getName()));
            sb.append(" ");
            sb.append(NumberFormat.getCurrencyInstance(Locale.US).format(slot.getValue().getSlottedItem().getPrice()));
            sb.append(" Quantity: ");
            if(slot.getValue().getQuantity() == 0){
                sb.append("SOLD OUT");
            } else {
                sb.append(slot.getValue().getQuantity());
            }
            sb.append("\n");
        }
        return sb.toString();
    }

    public String giveChange(){
        double oldBalance = getBalance();
        VendLog.logGiveChange(oldBalance);
        setBalance(0.00);
        return Cashier.getChangeMessage(Cashier.getChange(oldBalance));
    }





}
