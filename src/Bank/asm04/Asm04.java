package Bank.asm04;

import Bank.asm04.models.*;

import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Asm04 {

    private static final String FILE_PATH_DEFAULT ="src/Bank/asm04/";
    private static final DigitalBank activeBank = new DigitalBank("ACTIVEBANK");
    private static final Scanner scanner = new Scanner(System.in);

    public static void showScreen() {
        System.out.println("+-----------+-----------------+---------+");
        System.out.println("NGAN HANG SO  |  FX21967  |  @v4.0.0    |");
        System.out.println("+-----------+-----------------+---------+");
        System.out.println("| 1. Danh sách khách hàng               |");
        System.out.println("| 2. Nhập danh sách khách hàng          |");
        System.out.println("| 3. Thêm tài khoản ATM                 |");
        System.out.println("| 4. Chuyển tiền                        |");
        System.out.println("| 5. Rút tiền                           |");
        System.out.println("| 6. Tra cứu lịch sử giao dịch          |");
        System.out.println("| 0. Thoát                              |");
        System.out.println("+-----------+-----------------+---------+");
    }

    public static int getNumberEnter() {
        while (true) {
            System.out.print("Chọn chức năng: ");
            try {
                return scanner.nextInt();
            } catch (InputMismatchException e) {
                scanner.nextLine();
                System.out.println("Hãy nhập vào số thứ tự của chức năng !!!");
            }
        }
    }

    public static void main(String[] args) {
        showScreen();
        boolean isRunning = true;
        while (isRunning) {
            int numEnter = getNumberEnter();
            switch (numEnter) {
                case 1:
                    activeBank.showCustomers();
                    break;
                case 2:
                    try {
                        scanner.nextLine();
                        System.out.println("Nhập đường dẫn đến tệp");
                        String filePath = scanner.nextLine();
                        String filePathCustomerTXT = FILE_PATH_DEFAULT + filePath;
                        activeBank.addCustomerFromFile(filePathCustomerTXT);
                    } catch (IOException | CustomerIdNotValidException e) {
                        e.printStackTrace();
                    }
                    break;
                case 3:
                    try {
                        scanner.nextLine();
                        System.out.println("Nhập mã số của khách hàng");
                        String customerId = scanner.nextLine();
                        System.out.print("Nhập mã số tài khoản gồm 6 chữ số: ");
                        String numberAccount = scanner.nextLine();
                        System.out.print("Nhập số dư tài khoản >= 50.000 đ: ");
                        String strNumberBalance = scanner.nextLine();
                        activeBank.addSavingsAccount(customerId, numberAccount, strNumberBalance);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    break;
                case 4:
                    try {
                        scanner.nextLine();
                        System.out.println("Nhập mã số của khách hàng");
                        String customerId = scanner.nextLine();
                        activeBank.transferAmount(customerId);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    break;
                case 5:
                    try {
                        scanner.nextLine();
                        System.out.println("Nhập mã số của khách hàng: ");
                        String customerId = scanner.nextLine();
                        activeBank.withdraw(customerId);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    break;
                case 6:
                    scanner.nextLine();
                    System.out.println("Nhập mã số của khách hàng: ");
                    String customerId = scanner.nextLine();
                    activeBank.showTransactionsCustomer(customerId);
                    break;
                case 0:
                    isRunning = false;
                    System.out.println("Shutting down");
                    break;
                default:
                    System.out.println("Lỗi, vui lòng chọn 1 trong các tùy chọn có sẵn");
            }
        }
    }
}
