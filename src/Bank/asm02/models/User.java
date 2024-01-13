package Bank.asm02.models;
import Bank.asm01.asm01; // Import the required package (assuming it's named correctly)

public abstract class User {
    private String name;
    private String customerId;

    // Default constructor
    public User() {
    }

    // Parameterized constructor
    public User(String customerId, String name) {
        this.customerId = customerId;
        this.name = name;
    }

    // Getter for name
    public String getName() {
        return name;
    }

    // Setter for name
    public void setName(String name) {
        this.name = name;
    }

    // Getter for customerId
    public String getCustomerId() {
        return customerId;
    }

    // Setter for customerId
    public void setCustomerId(String customerId) {
        // Assuming asm01.checkCCCD returns 0 for a valid CCCD
        if (asm01.checkCCCD(customerId) == 0) {
            this.customerId = customerId;
        } else {
            System.out.println("Ma CCCD khong hop le !");
    }
}
}