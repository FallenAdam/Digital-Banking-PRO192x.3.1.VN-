package Bank.asm03.models;

import Bank.asm02.models.Account;
import Bank.asm03.Asm03;

import java.util.Locale;

public class LoanAccount extends Account implements Withdraw, ReportService {
    static final double LOAN_ACCOUNT_WITHDRAW_FEE = 0.05;
    static final double LOAN_ACCOUNT_WITHDRAW_PREMIUM_FEE = 0.01;
    static final double LOAN_ACCOUNT_MAX_BALANCE = 100000000;
    static final String ACCOUNT_TYPE = "LOAN";

    public LoanAccount(String accountNumber) {
        super(accountNumber, LOAN_ACCOUNT_MAX_BALANCE);
    }

    // In biên lai giao dịch
    @Override
    public void log(Transaction transaction) {
        System.out.println("*------ BIÊN LAI GIAO DỊCH LOAN  ------*");
        System.out.println("|   NGÀY G/D:  " + String.format("%24s", Asm03.getDateTime()) + "   |");
        System.out.println("|   ATM ID:    " + String.format("%24s", "DIGITAL-BANK-ATM-2023") + "   |");
        System.out.println("|   SO TK:     " + String.format("%24s", transaction.getAccountNumber()) + "   |");
        System.out.println("|   SO TIEN:  " + String.format("%24s", String.format("%,d", (long) transaction.getAmount())) + "đ   |");
        System.out.println("|   SO DU:    " + String.format("%24s", String.format("%,d", (long) transaction.getCurrentBalance())) + "đ   |");
        System.out.println("|   PHI + VAT:" + String.format("%24s", String.format("%,d", (long) transaction.getTranscationFee())) + "đ   |");
        System.out.println("*-----------------------------------------*");
    }

    // xử lí rút tiền, lưu lịch sử giao dịch
    @Override
    public boolean withdraw(double amount) {
        if (isAccepted(amount)) {
            String accountNumber = super.getAccountNumber();
            double transactionFee = amount * (isPremium() ? LOAN_ACCOUNT_WITHDRAW_PREMIUM_FEE : LOAN_ACCOUNT_WITHDRAW_FEE);
            super.setBalance(super.getBalance() - amount - transactionFee);
            Transaction trs = new Transaction(accountNumber, amount, transactionFee, super.getBalance(), true);
            super.getTransactions().add(trs);
            log(trs);
            return true;
        }
        System.out.println("Rút tiền KHÔNG THÀNH CÔNG!");
        return false;
    }

    // Check điều kiện số tiền được phép rút
    @Override
    public boolean isAccepted(double amount) {

        double soDuSauRut = super.getBalance() - amount - amount * (isPremium() ? LOAN_ACCOUNT_WITHDRAW_PREMIUM_FEE : LOAN_ACCOUNT_WITHDRAW_FEE);
        if (soDuSauRut < 50000) {
            System.out.println("Không thể thực hiện lệnh rút tiền! (Số dư tài khoản sau khi rút phải lớn hơn 50_000 VND)");
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        String s = String.format("%10s", super.getAccountNumber()) + "  |" + String.format("%18s", ACCOUNT_TYPE + " |") +
                String.format("%13s", "") +
                String.format("%15s", String.format(Locale.ENGLISH, "%,d", (long) super.getBalance()) + "VND");
        return s;
    }
}
