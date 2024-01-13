package Bank.asm02.models;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import Bank.asm03.models.Transaction;

public class Account {
    private String accountNumber;
    private double balance;
    private List<Transaction> transactions;

    public Account(String accountNumber, double balance) {
        this.accountNumber = accountNumber;
        this.balance = balance;
        this.transactions = new ArrayList<>();
    }

    public Account() {
    }

    public void setAccountNumber(String accountNumber) {
        if (Account.isValidAccount(accountNumber))
            this.accountNumber = accountNumber;
    }

    public void setBalance(double balance) {
        if (balance >= 50000)
            this.balance = balance;
        else
            System.out.println("So du phai lon hon 50_000 VND");
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public double getBalance() {
        return balance;
    }
     public List<Transaction> getTransactions() {
        return transactions;
    }


//    Boolean kiem tra loai tai khoan khach hang

    public boolean isPremium() {
        return balance >= 10000000;
    }
      public static boolean isValidAccount(String accountNumber) {
        return accountNumber != null && accountNumber.matches("\\d{6}");
    }

    public String toString() {
        String s = String.format("%10s", accountNumber) + "  |" + String.format("%31s", "") +
                String.format("%15s", String.format(Locale.ENGLISH, "%,d", (long) balance) + "VND");
        return s;
    }

  
    
}
