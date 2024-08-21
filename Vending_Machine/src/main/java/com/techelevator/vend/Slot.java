package com.techelevator.vend;

import com.techelevator.items.Item;

public class Slot {

    public final int DEFAULT_STARTING_QUANTITY = 5;
    private String name;
    private Item slottedItem;
    private int quantity;


    public Slot(String name, Item slottedItem){
        this.name = name;
        this.slottedItem = slottedItem;
        this.quantity = DEFAULT_STARTING_QUANTITY;
    }

    public void decrementQuantity(){
        this.quantity -= 1;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Item getSlottedItem() {
        return slottedItem;
    }

    public void setSlottedItem(Item slottedItem) {
        this.slottedItem = slottedItem;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
