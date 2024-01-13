package Bank.asm04.testModels;

import org.junit.Test;
import Bank.asm04.models.*;
import static org.junit.Assert.*;

public class CustomerTest {
    private Customer newCustomer;

    @org.junit.Before
    public void setup(){
        newCustomer = new Customer("Lux", "001097123456");
    }
    @Test
    public void isAccountExisted() {
        Account account2 = new Account("123456", 10000000, "Unknown", "001099123456");
        assertTrue(newCustomer.isAccountExisted(account2));
    }
    @Test
    public void isPremium() {
        assertFalse(newCustomer.isPremium());
    }
}