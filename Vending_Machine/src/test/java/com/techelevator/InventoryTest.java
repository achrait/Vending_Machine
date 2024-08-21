package com.techelevator;

import com.techelevator.items.Chip;
import com.techelevator.items.Item;
import com.techelevator.vend.Inventory;
import com.techelevator.vend.Slot;
import org.junit.Assert;
import org.junit.Test;

import java.util.LinkedHashMap;
import java.util.Map;

public class InventoryTest {

    public final static String DEFAULT_TEST_VENDING_MACHINE_FILEPATH = "src/test/resources/testvendingmachine.csv";

    @Test
    public void generateSlotsTest(){

        Item chip = new Chip();
        chip.setPrice(3.05);
        chip.setName("Potato Crisps");
        Slot slot = new Slot("A1", chip);
        Map<String, Slot> expected = new LinkedHashMap<>();
        expected.put("A1", slot);

        Map<String, Slot> actual = Inventory.generateSlots(DEFAULT_TEST_VENDING_MACHINE_FILEPATH);
        Assert.assertEquals(expected.get("A1").getSlottedItem().getName(), actual.get("A1").getSlottedItem().getName());
    }

    @Test
    public void generateFilledSlotTest() throws ClassNotFoundException, InstantiationException, IllegalAccessException {
        Item chip = new Chip();
        chip.setPrice(3.05);
        chip.setName("Potato Crisps");
        Slot expected = new Slot("A1", chip);

        String[] lineArr = {"A1", "Potato Crisps", "3.05", "Chip"};
        Slot actual = Inventory.generateFilledSlot(lineArr);
        Assert.assertEquals(expected.getSlottedItem().getName(), actual.getSlottedItem().getName());
    }

}
