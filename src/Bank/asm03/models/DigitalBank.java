package Bank.asm03.models;

import Bank.asm02.models.Bank;
import Bank.asm02.models.Customer;

public class DigitalBank extends Bank {

    // Retrieve a customer by their ID
    public Customer getCustomerById(String customerId) {
        // Check if the customer exists and get their index
        int index = super.isCustomerExisted(customerId);

        // If the customer exists, return the customer object; otherwise, return null
        return (index > -1) ? super.getCustomers().get(index) : null;
    }

    // Add a new customer to the bank
    public void addCustomer(String customerId, String name) {
        // Check if the customer already exists
        if (super.isCustomerExisted(customerId) == -1) {
            // If not, create a new DigitalCustomer and add it to the bank's list of customers
            super.getCustomers().add(new DigitalCustomer(customerId, name));
        } else {
            // If the customer exists, display an error message
            System.out.println("Lỗi. Người dùng này đã là khách hàng !");
        }
    }

    // Perform a withdrawal from a customer's account
    public void withdraw(String customerId, String accountNumber, double amount) {
        // Get the customer by ID
        Customer cus = getCustomerById(customerId);

        // If the customer exists
        if (cus != null) {
            // Check if the customer is of type DigitalCustomer
            if (cus instanceof DigitalCustomer) {
                // If so, perform the withdrawal using the DigitalCustomer's method
                ((DigitalCustomer) cus).withdraw(accountNumber, amount);
            } else {
                // If the customer is not of the expected type, display an error message
                System.out.println("Lỗi. Không hỗ trợ giao dịch số liệu !");
            }
        } else {
            // If the customer doesn't exist, display an error message
            System.out.println("Lỗi. Không tồn tại khách hàng này !");
        }
    }
}
