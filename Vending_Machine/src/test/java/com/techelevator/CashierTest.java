package com.techelevator;
import com.techelevator.vend.Cashier;
import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

public class CashierTest {

    @Test
    public void getChangeTest(){
        double money = 10.15;
        int[] coins = Cashier.getChange(money);
        assertArrayEquals(new int[]{1, 1, 40}, coins);
    }

    @Test
    public void getChangeMessageTest(){
        int[] coins = {0, 0, 40};
        assertEquals("Dispensing 40 quarters", Cashier.getChangeMessage(coins));
    }

    @Test
    public void getChangeMessageTestWithNoMoney(){
        int[] coins = {0, 0, 0};
        assertEquals("No change", Cashier.getChangeMessage(coins));
    }


}
