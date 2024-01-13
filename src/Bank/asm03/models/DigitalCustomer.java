package Bank.asm03.models;


import Bank.asm02.models.Account;
import Bank.asm02.models.Customer;

import java.util.List;


public class DigitalCustomer extends Customer {

    public DigitalCustomer(String customerId, String name) {
        super(customerId, name);
    }

    // Thực hiện rút tiền từ tài khoản

    public void withdraw(String accountNumber, double amount) {
        List<Account> accs = super.getAccounts();
        for (Account acc : accs) {
            if (acc.getAccountNumber().equals(accountNumber)) {
                ((Withdraw) acc).withdraw(amount); // vận dụng đa hình
                return;
            }
        }
        System.out.println("Không tồn tại Số tài khoản này");
    }

    // Kiểm tra xem khách hàng đã có giao dịch nào hay chưa

    public boolean hasTransactions() {
        for (Account acc : getAccounts()) {
            if (!acc.getTransactions().isEmpty()) {
                return true;
            }
        }
        return false;
    }
    // Hiển thị lịch sử giao dịch
    public void displayHistoryTransaction() {
        System.out.println("+----------+-------------------------+----------+");
        System.out.println("| " + "LỊCH SỬ GIAO DỊCH " + "                  |");
        System.out.println("+----------+-------------------------+----------+");
        displayInformation();
        System.out.println("--------------------------------------------------------------------------------------");
        System.out.printf("| %10s | %15s | %10s | %15s | %18s |\n",
                "Số tài khoản", "Số tiền rút", "Thuế + Phí", "Số dư", "Thời gian");
        for (Account acc : super.getAccounts()) {
            System.out.println("--------------------------------------------------------------------------------------");
            for (Transaction trs : acc.getTransactions()) {
                System.out.println(trs.toString());
            }
        }
    }

    }



