package Bank.asm04.models;

import java.io.Serializable;
import java.text.NumberFormat;
import java.util.*;

public class Customer implements Serializable {

    private final int PREMIUM_AMOUNT = 10000000;
    private String customerId;
    private String name;

    private long serialVersionUID;
    public Customer(){}
    public Customer(String customerId, String name){
        this.customerId = customerId;
        this.name = name;
    }

    public Customer(List<String> values){
        this(values.get(0), values.get(1));
    }

    // Get id of Customer
    public String getCustomerId() {
        return customerId;
    }

    // Get name of customer
    public String getName() {
        return name;
    }

    // Get all account of customer from file
    public List<Account> getAccounts(){
        List<Account> accounts = new ArrayList<>();
        List<Account> allAccount = AccountDao.list();
        allAccount.forEach(account -> {
            if (Objects.equals(account.getCustomerId(), this.customerId)){
                accounts.add(account);
            }
        });
        return accounts;
    }

    // Display information of account
    public void displayInformation(){
        String isPre = "Normal";
        // Calculate sum of all balance
        double sum = 0;
        List<Account> accounts = getAccounts();
        for (Account account : accounts) {
            sum += account.getBalance();
        }
        // Format type number balance
        Locale localeEN = new Locale("en", "EN");
        NumberFormat en = NumberFormat.getInstance(localeEN);
        String strSum = en.format(sum);
        // Change type print if customer is Premium
        if (sum >= PREMIUM_AMOUNT) {
            isPre = "Premium";
        }
        System.out.println("+--------------------------------------------------------------------------+");
        System.out.printf("%-12s |%-30s | %-10s | %15sđ\n", this.customerId, this.name, isPre, strSum);
        for(int i = 0; i < accounts.size(); i++){
            String sTT = String.valueOf(i + 1);
            String accountBalance = en.format(accounts.get(i).getBalance());
            System.out.printf("%-2s %9s | %-29s | %28sđ\n"
                    , sTT
                    , accounts.get(i).getAccountNumber()
                    , accounts.get(i).getAccountType()
                    , accountBalance
            );
        }
    }

    // Set customer id with catch exception
    public void setCustomerId(String customerId) throws CustomerIdNotValidException {
        if (!Account.validID(customerId)) {
            throw new CustomerIdNotValidException();
        } else {
            this.customerId = customerId;
        }
    }

    public void setName(String name){
        this.name = name;
    }

    // Get balance of all account
    public double getbalance(){
        double sum = 0;
        List<Account> accounts = getAccounts();
        for (Account account : accounts) {
            sum += account.getBalance();
        }
        return  sum;
    }
    public boolean isPremium(){
        return getbalance() > PREMIUM_AMOUNT;
    }

    // Check if account existed
    public boolean isAccountExisted (Account newAccount){
        List<Account> accounts = new ArrayList<>();
        List<Account> accountList = AccountDao.list();
        accountList.forEach(account -> {
            if (Objects.equals(account.getAccountNumber(), newAccount.getAccountNumber())){
                accounts.add(account);
            }
        } );
        return accounts.size() > 0;
    }

    // Get account from List by number Account
    public Account getAccountByNumberAccount(String accountNumber){
        List<Account> accounts = getAccounts();
        for (Account account : accounts) {
            if (Objects.equals(account.getAccountNumber(), accountNumber)) {
                return account;
            }
        }
        return null;
    }
}
