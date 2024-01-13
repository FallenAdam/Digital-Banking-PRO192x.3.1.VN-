package Bank.asm02.models;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class Customer extends User {
    private List<Account> accounts;

    public Customer() {
        super();
        accounts = new ArrayList<>();
    }
    public Customer(String customerId, String name){
        super(customerId, name);
        accounts = new ArrayList<>();
    }

    public List<Account> getAccounts() {

        return accounts;
    }


    public boolean isPremium() {
        List<Account> accs = getAccounts();
        for (Account acc :
                accs) {
            if (acc.isPremium())
                return true;
        }
        return false;
    }

    public void addAccount(Account newAccount) {
            accounts.add(newAccount);
    }

    public double getBalance() {
        double sum = 0D;
        List<Account> accs = getAccounts();
        for (Account acc : accs) {
            sum += acc.getBalance();
        }
        return sum;
    }

    public void displayInformation() {
        List<Account> accs = getAccounts();
        String loaiKhachHang;
        int countLine = 0; 
        if (isPremium())
            loaiKhachHang = "Premium";
        else
            loaiKhachHang = "Normal";

        String s = String.format("%12s", super.getCustomerId()) + "  |" + String.format("%15s", super.getName()) + "  |" +
                String.format("%10s", loaiKhachHang) + "  |" +
                String.format("%15s", String.format(Locale.ENGLISH, "%,d", (long) getBalance()) + "VND");
        System.out.println(s);
        for (Account acc : accs) {
            System.out.println(++countLine + "." + acc.toString());
        }
    }
}
