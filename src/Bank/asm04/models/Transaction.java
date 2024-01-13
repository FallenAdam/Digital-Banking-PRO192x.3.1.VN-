package Bank.asm04.models;

import java.io.Serializable;

public class Transaction implements Serializable {

    private long serialVersionUID ;
    private final String id;
    private final String accountNumber;
    private final double amount;
    private final String time;
    private final boolean status;
    private final String Type;

    public Transaction(String id, String accountNumber, double amount, String time, boolean status, String type) {
        this.id = id;
        this.accountNumber = accountNumber;
        this.amount = amount;
        this.time = time;
        this.status = status;
        Type = type;
    }

    public String getId() {
        return id;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public double getAmount() {
        return amount;
    }

    public String getTime() {
        return time;
    }

    public boolean isStatus() {
        return status;
    }

    public String getType() {
        return Type;
    }
}
