package com.techelevator.vend;

import org.junit.jupiter.api.Test;
import com.techelevator.vend.Cashier;
import static org.junit.jupiter.api.Assertions.*;

class CashierTest {

    @Test
    void getChange() {

        double  money=10.00;

       int[] h=Cashier.getChange(money);
        assertEquals ("Dispensing 40 quarters",Cashier.getChangeMessage(h));
    }
}