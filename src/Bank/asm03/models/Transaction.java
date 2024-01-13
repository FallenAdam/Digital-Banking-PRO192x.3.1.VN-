package Bank.asm03.models;

import Bank.asm03.Asm03;

import java.util.Locale;
import java.util.UUID;

public class Transaction {
    private String id;
    private String accountNumber;
    private double amount;
    private String time;
    private double transcationFee;
    private double currentBalance;
    private boolean status;

    public Transaction(String accountNumber, double amount, double transcationFee, double currentBalance, boolean status) {
        this.accountNumber = accountNumber;
        this.amount = amount;
        this.transcationFee = transcationFee;
        this.currentBalance = currentBalance;
        this.status = status;
        this.id = String.valueOf(UUID.randomUUID());
        this.time = Asm03.getDateTime();
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

    public double getTranscationFee() {
        return transcationFee;
    }

    public double getCurrentBalance() {
        return currentBalance;
    }

    public String toString() {
        String s = String.format("%14s", accountNumber) + "  |" +
                String.format("%15s", String.format(Locale.ENGLISH, "%,d", (long) amount) + "đ") + "  |" +
                String.format("%10s", String.format(Locale.ENGLISH, "%,d", (long) transcationFee) + "đ") + "  |" +
                String.format("%15s", String.format(Locale.ENGLISH, "%,d", (long) currentBalance) + "đ") + "  |" +
                String.format("%20s", time);
        return s;
    }
}
