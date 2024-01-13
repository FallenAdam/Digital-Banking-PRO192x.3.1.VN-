package Bank.asm03;
import Bank.asm02.asm02;
import Bank.asm02.models.Customer;
import Bank.asm03.models.DigitalBank;
import Bank.asm03.models.DigitalCustomer;
import Bank.asm03.models.LoanAccount;
import Bank.asm03.models.SavingAccount;
import Bank.asm01.asm01;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

public class Asm03 {
    static final String SOFTWARE = "NGAN HANG SO";
    static final String Author = "FX21967";
    static final String Version = "@v3.0.0";


    // Khởi tạo Scanner và activeBank
    private static final Scanner sc = new Scanner(System.in);
    private static final DigitalBank activeBank = new DigitalBank();
    private static final String CUSTOMER_ID = "001215000001";
    private static final String CUSTOMER_NAME = "Funix";


    public static void main(String[] args) {
        activeBank.addCustomer(CUSTOMER_ID, CUSTOMER_NAME);
        do {
            try {
                displayLoginMenu();
                int choice = asm01.insertValidNumber(0, 5);
                processMenuChoice(choice);
            } catch (NoSuchElementException e) {
                System.out.println("Thông báo lỗi: " + e.getMessage());
                return;
            }
        } while (true);
    }

    // Process user's menu choice
    public static void processMenuChoice(int choice) {
        switch (choice) {
            case 0:
                System.exit(0);
            case 1:
                showCustomerInformation();
                break;
            case 2:
                addSavingsAccount();
                break;
            case 3:
                addLoanAccount();
                break;
            case 4:
                performWithdrawal();
                break;
            case 5:
                displayTransactionHistory();
                break;
            default:
                System.out.println("Lựa chọn không hợp lệ.");
        }
    }

    // Display the login menu
    public static void displayLoginMenu() {
        System.out.println("+----------+-------------------------+----------+");
        System.out.println("| " + SOFTWARE + " | " + Author + Version + "   |");
        System.out.println("+----------+-------------------------+----------+");
        System.out.println(" 1. Thong tin khach hang");
        System.out.println(" 2. Them tai khoan ATM");
        System.out.println(" 3. Them tai khoan tin dung");
        System.out.println(" 4. Rut tien");
        System.out.println(" 5. Lich su giao dich");
        System.out.println(" 0. Thoat");
        System.out.println("+----------+-------------------------+----------+");
        System.out.print("Chuc nang: ");
    }


    // Display customer information
    public static void showCustomerInformation() {
        Customer customer = activeBank.getCustomerById(CUSTOMER_ID);
        if (customer != null) {
            customer.displayInformation();
        }
    }

    // Add a savings account
    public static void addSavingsAccount() {
        String accountNumber = asm02.validAccInput();
        double initialBalance = asm02.insertValidBalance();
        activeBank.addAccount(CUSTOMER_ID, new SavingAccount(accountNumber, initialBalance));
    }


    // Add a loan account
    public static void addLoanAccount() {
        String accountNumber = asm02.validAccInput();
        activeBank.addAccount(CUSTOMER_ID, new LoanAccount(accountNumber));
    }

    // Validate withdrawal amount
    public static double validateWithdrawAmount() {
        do {
            try {
                System.out.println("Vui lòng nhập số tiền muốn rút: ");
                double withdrawalAmount = sc.nextDouble();
                if (withdrawalAmount > 0) {
                    sc.nextLine();
                    return withdrawalAmount;
                } else {
                    System.out.println("Số tiền rút phải lớn hơn 0 VND.");
                }
            } catch (InputMismatchException e) {
                sc.nextLine();
                System.out.println("Nhập số tiền rút > 0.");
            }
        } while (true);
    }

    // Perform withdrawal
    public static void performWithdrawal() {
        String accountNumber = asm02.validAccInput();
        double withdrawalAmount = validateWithdrawAmount();
        activeBank.withdraw(CUSTOMER_ID, accountNumber, withdrawalAmount);
    }

    // Display transaction history
    public static void displayTransactionHistory() {
        DigitalCustomer customer = (DigitalCustomer) activeBank.getCustomerById(CUSTOMER_ID);
        if (customer.hasTransactions()) {
            customer.displayHistoryTransaction();
        } else {
            System.out.println("Chưa có giao dịch nào được ghi lại.");
        }
    }

    // Hàm để lấy ngày và giờ hiện tại
    public static String getDateTime() {
        DateFormat ttgd = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date today = Calendar.getInstance().getTime();
        return ttgd.format(today);
    }

}
