package com.techelevator;

import com.techelevator.vend.Menu;
import com.techelevator.vend.VendingMachine;
import org.junit.Assert;
import org.junit.Test;

public class MenuTest {


    @Test
    public void buyItemTest(){
        VendingMachine vendingMachine = new VendingMachine();
        Menu menu = new Menu(vendingMachine);
        vendingMachine.setBalance(10);
        int oldQuantity = vendingMachine.getSlots().get("A1").getQuantity();
        int expectedQuantity = oldQuantity - 1;
        menu.buyItem("A1");
        int actualQuantity = vendingMachine.getSlots().get("A1").getQuantity();

        Assert.assertEquals(expectedQuantity, actualQuantity);
    }
}
