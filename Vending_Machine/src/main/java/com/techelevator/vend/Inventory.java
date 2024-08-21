package com.techelevator.vend;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

import com.techelevator.items.*;

public class Inventory {

    private static final String DEFAULT_INVENTORY_FILEPATH = "vendingmachine.csv";


    //Takes a string array and creates the appropriate item object them puts the item in a slot and returns the slot.
    public static Slot generateFilledSlot(String[] splitLine) throws ClassNotFoundException, InstantiationException, IllegalAccessException {
//3

        String slotName = null;
        String itemName = null;
        Double itemPrice = null;


        Item item = null;
        try {

            slotName = splitLine[0];
            itemName = splitLine[1];
            itemPrice = Double.parseDouble(splitLine[2]);
            String itemSubclassName = splitLine[3];
            item = null;

            if(itemSubclassName.equals("Chip")){
                // display the sound effect by calling the constructor of chip
                item = new Chip();

            }

            if(itemSubclassName.equals("Candy")){
                item = new Candy();
            }

            if(itemSubclassName.equals("Gum")){
                item = new Gum();
            }

            if(itemSubclassName.equals("Drink")){
                item = new Drink();
            }

            item.setName(itemName);
            item.setPrice(itemPrice);

        } catch (Exception e) {
            System.err.println("Incorrectly formatted line array: " + e.getMessage());
            System.exit(-1);
        }


       Slot slot = new Slot(slotName, item);

        return slot;
    }


    public static Map<String, Slot> generateSlots(String filename) {
        Map<String, Slot> slots = new LinkedHashMap<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] splitLine = line.split("\\|");
                Slot slot = generateFilledSlot(splitLine);
                slots.put(slot.getName(), slot);
            }
        } catch (IOException | IllegalAccessException e) {
            System.err.println(e.getMessage());
        } catch (ClassNotFoundException e) {
            System.err.println("Class not found: " + e.getMessage());
        } catch (InstantiationException e) {
            System.err.println("Instantiation problem: " + e.getMessage());
        }

        return slots;
    }

    public static Map<String, Slot> generateSlots() {
        return generateSlots(DEFAULT_INVENTORY_FILEPATH);
    }





}
