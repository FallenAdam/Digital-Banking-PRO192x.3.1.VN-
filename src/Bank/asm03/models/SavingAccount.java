package Bank.asm03.models;

import Bank.asm02.models.Account;
import Bank.asm03.Asm03;

import java.util.Locale;

public class SavingAccount extends Account implements Withdraw, ReportService {
    static final double SAVINGS_ACCOUNT_MAX_WITHDRAW = 5000000;
    static final String ACCOUNT_TYPE = "SAVINGS";

    public SavingAccount(String accountNumber, double balance) {
        super(accountNumber, balance);
    }

    @Override
    public void log(Transaction transaction) {
        System.out.println("*------ BIÊN LAI GIAO DỊCH SAVINGS  ------*");
        System.out.println("|   NGÀY G/D:  " + String.format("%24s", Asm03.getDateTime()) + "   |");
        System.out.println("|   ATM ID:    " + String.format("%24s", "DIGITAL-BANK-ATM-2023") + "   |");
        System.out.println("|   SO TK:     " + String.format("%24s", transaction.getAccountNumber()) + "   |");
        System.out.println("|   SO TIEN:  " + String.format("%24s", String.format("%,d", (long) transaction.getAmount())) + "đ   |");
        System.out.println("|   SO DU:    " + String.format("%24s", String.format("%,d", (long) transaction.getCurrentBalance())) + "đ   |");
        System.out.println("|   PHI + VAT:" + String.format("%24s", String.format("%,d", (long) transaction.getTranscationFee())) + "đ   |");
        System.out.println("*-----------------------------------------*");
    }

    // xử lí nghiệp vụ rút tiền, lưu lịch sử giao dịch
    // true: là rút tiền thành công
    @Override
    public boolean withdraw(double amount) {
        if (isAccepted(amount)) {
            String accountNumber = super.getAccountNumber();
            double transactionFee = 0;
            super.setBalance(super.getBalance() - amount);
            Transaction trs = new Transaction(accountNumber, amount, transactionFee, super.getBalance(), true);
            super.getTransactions().add(trs);
            log(trs);
            return true;
        }
        System.out.println("Không thể thực hiện lệnh rút tiền");
        return false;

    }

    // Xác định xem giá trị có thỏa điều kiện rút tiền hay không?
    // True: là thỏa mãn
    @Override
    public boolean isAccepted(double amount) {
        double soDuTruocRut = super.getBalance();
        if (soDuTruocRut - amount < 50000 || amount < 50000 || amount % 10000 > 0) {
            System.out.println("Số tiền rút không hợp lệ!");
            return false;
        } else if (!super.isPremium() && amount > SAVINGS_ACCOUNT_MAX_WITHDRAW) {
            System.out.println("Số tiền rút không hợp lệ! Khách hàng THƯỜNG 1 lần rút PHẢI <= 5_000_000 VND");
            return false;
        } else
            return true;
    }

    //In thông tin 1 account
    @Override
    public String toString() {
        String s = String.format("%10s", super.getAccountNumber()) + "  |" + String.format("%18s", ACCOUNT_TYPE + " |") +
                String.format("%13s", "") +
                String.format("%15s", String.format(Locale.ENGLISH, "%,d", (long) super.getBalance()) + "đ");
        return s;
    }
}

