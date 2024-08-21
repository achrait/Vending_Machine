package com.techelevator.items;

public abstract class Item {

    private String name;
    private double price;
    private String soundEffect;


    public Item(String soundEffect) {
        this.soundEffect = soundEffect;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSoundEffect() {
        return soundEffect;
    }

    public void setSoundEffect(String soundEffect) {
        this.soundEffect = soundEffect;
    }
}
