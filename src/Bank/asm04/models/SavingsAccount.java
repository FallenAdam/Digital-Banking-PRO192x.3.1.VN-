package Bank.asm04.models;

import java.text.NumberFormat;
import java.util.Date;
import java.util.Locale;

public class SavingsAccount extends Account implements ITtransfer {
    private final double SAVINGS_ACCOUNT_MAX_WITHDRAW = 5000000;

    public SavingsAccount(String accountNumber, double balance, String accountType, String customerId){
        super(accountNumber, balance, "SAVINGS", customerId);
    }

    // Check amount can withdraw or not
    public boolean isAccepted(double amount) {
        if((amount >= 50000.0) && (amount % 10000.0 == 0) && (getBalance() - amount >= 50000.0)){
            if(isPremium()){
                return true;
            }
            return !isPremium() && (amount < SAVINGS_ACCOUNT_MAX_WITHDRAW);
        }
        return false;
    }

    // Withdraw money
    public boolean withdraw(double amount) {
        double newBalance;
        if(isAccepted(amount)){
            newBalance = getBalance() - amount;
            setBalance(newBalance);
            log(amount);
            return true;
        }
        System.out.println("Số tiền rút không khả dụng");
        return false;
    }

    @Override
    public boolean transfer(Account receiveAccount, double amount) {
        double newBalanceTran;
        double newBalanceRece;
        if(isAccepted(amount)){
            newBalanceTran = getBalance() - amount;
            setBalance(newBalanceTran);
            newBalanceRece = receiveAccount.getBalance() + amount;
            receiveAccount.setBalance(newBalanceRece);
            logTransfer(receiveAccount, amount);
            return true;
        }
        System.out.println("Số tiền chuyển không khả dụng");
        return false;
    }

    // Create print Screen transaction
    public void logTransfer(Account receiveAccount, double amount){
        String time = getTime();
        // Make object format tyoe
        Locale localeEN = new Locale("en", "EN");
        NumberFormat en = NumberFormat.getInstance(localeEN);
        // Format type amount and balance
        String strAmount = en.format(amount);
        String strBalance = en.format(getBalance());
        System.out.println("+-----------+-------------------------+--------+");
        System.out.println("|        BIÊN LAI GIAO DỊCH SAVINGS            |");
        System.out.printf("| NGÀY GD: %35s |\n" , time);
        System.out.println("| ATM ID:                DIGITAL-BANK-ATM 2022 |");
        System.out.printf("| SỐ TK: %37s |\n", getAccountNumber());
        System.out.printf("| SỐ TK NHẬN: %32s |\n", receiveAccount.getAccountNumber());
        System.out.printf("| SỐ TIỀN CHUYỂN: %27sđ |\n" , strAmount);
        System.out.printf("| SỐ DƯ TK: %33sđ |\n" , strBalance);
        System.out.println("| PHÍ + VAT:                                0đ |");
        System.out.println("+-----------+-------------------------+--------+");
    }


    // Display bill for withdraw
    public void log(double amount) {
        // Make object format tyoe
        Locale localeEN = new Locale("en", "EN");
        NumberFormat en = NumberFormat.getInstance(localeEN);
        // Format type amount and balance
        String strAmount = en.format(amount);
        String strBalance = en.format(getBalance());
        // Make string of date
        String dateDisplay = getTime();
        // Display Screen
        System.out.println("+------------+----------------------+----------+");
        System.out.println("|        BIÊN LAI GIAO DỊCH SAVINGS            |");
        System.out.printf("| NGÀY GC: %35s |\n" ,dateDisplay);
        System.out.println("| ATM ID:                DIGITAL-BANK-ATM 2022 |");
        System.out.printf("| SỐ TK: %37s |\n", getAccountNumber());
        System.out.printf("| SỐ TIỀN RÚT: %30sđ |\n" , strAmount);
        System.out.printf("| SỐ DƯ TK: %33sđ |\n" , strBalance);
        System.out.println("| PHÍ + VAT:                                0đ |");
        System.out.println("+-------------+----------------------+---------+");
    }


    // Get string time of transaction
    public static String getTime(){
        // Make string of time
        Date date1 = new Date();
        String getDate;
        if (date1.getDate() < 10) {
            getDate = "0" + date1.getDate();
        } else {
            getDate = String.valueOf(date1.getDate());
        }
        String getMonth;
        if (date1.getMonth() < 10) {
            getMonth = "0" + date1.getMonth();
        } else {
            getMonth = String.valueOf(date1.getMonth());
        }
        String getYear;
        getYear = String.valueOf(1900 + date1.getYear());
        String getHours;
        if (date1.getHours() < 10) {
            getHours = "0" + date1.getHours();
        } else {
            getHours = String.valueOf(date1.getHours());
        }
        String getMinutes;
        if (date1.getMinutes() < 10) {
            getMinutes = "0" + date1.getMinutes();
        } else {
            getMinutes = String.valueOf(date1.getMinutes());
        }
        String getSeconds;
        if (date1.getSeconds() < 10) {
            getSeconds = "0" + date1.getSeconds();
        } else {
            getSeconds = String.valueOf(date1.getSeconds());
        }
        return getDate + "/" + getMonth + "/" + getYear + " " + getHours + ":" + getMinutes + ":" + getSeconds;
    }
}
