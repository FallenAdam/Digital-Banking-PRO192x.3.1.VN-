package Bank.asm04.testModels;

import org.junit.Test;
import Bank.asm04.models.*;
import static org.junit.Assert.*;

public class SavingsAccountTest {
    private SavingsAccount transferAccount;
    private SavingsAccount receiveAccount;

    @org.junit.Before
    public void setup(){
        transferAccount = new SavingsAccount("123456", 1000000,"SAVINGS", "001099123456");
        receiveAccount = new SavingsAccount("456789", 1000000,"SAVINGS", "001099123456");
    }

    @Test
    public void isAccepted() {
        boolean accepted = transferAccount.isAccepted(100000);
        assertTrue(accepted);
    }

    @Test
    public void withdraw() {
        transferAccount.withdraw(100000);
        assertEquals(900000, transferAccount.getBalance(), 0.1);
    }

    @Test
    public void transfer() {
        transferAccount.transfer(receiveAccount, 100000);
        assertEquals(900000, transferAccount.getBalance(), 0.1);
        assertEquals(1100000, receiveAccount.getBalance(), 0.1);
    }
}