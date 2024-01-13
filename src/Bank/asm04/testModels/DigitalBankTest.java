package Bank.asm04.testModels;

import org.junit.Before;
import org.junit.Test;
import Bank.asm04.models.*;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class DigitalBankTest {

    private DigitalBank digitalBank;

    @Before
    public void setUp() {
        digitalBank = new DigitalBank("GINKOU");
    }

    @Test
    public void isCustomerExisted_ExistingCustomer_ShouldReturnTrue() {
        // Arrange
        List<Customer> customers = Arrays.asList(new Customer("001099123456", "John Doe"));

        // Act
        boolean result = digitalBank.isCustomerExisted(customers, "001099123456");

        // Assert
        assertTrue(result);
    }

    @Test
    public void isCustomerExisted_NonExistingCustomer_ShouldReturnFalse() {
        // Arrange
        List<Customer> customers = Arrays.asList(new Customer("001099123456", "John Doe"));

        // Act
        boolean result = digitalBank.isCustomerExisted(customers, "999999");

        // Assert
        assertFalse(result);
    }

    @Test
    public void transferAmount_ValidTransfer_ShouldUpdateBalances() throws IOException {
        // Arrange
        Account senderAccount = new Account("123456", 1000, "SAVINGS", "001099123456");
        Account receiverAccount = new Account("789012", 500, "SAVINGS", "999999");

        List<Account> accounts = Arrays.asList(senderAccount, receiverAccount);

        // Act
        digitalBank.transferAmount("001099123456");

    }}

