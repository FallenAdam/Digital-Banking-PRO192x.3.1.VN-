package Bank.asm04.testModels;

import org.junit.Test;
import Bank.asm04.models.*;

import static org.junit.Assert.*;

public class AccountTest {

    private Account account;
    // Set up information
    @org.junit.Before
    public void setup() {
        account = new Account("123456", 10000000, "Unknown", "001099123456");
    }

    @Test
    public void validAccountNumber() {
        boolean validAccount = Account.validAccountNumber(account.getAccountNumber());
        assertTrue(validAccount);
    }

    @Test
    public void isPremium() {
        boolean isPremium = account.isPremium();
        assertTrue(isPremium);
    }
}