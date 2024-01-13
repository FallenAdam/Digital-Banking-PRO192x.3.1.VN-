

package Bank.asm04.models;

import java.io.IOException;
import java.text.NumberFormat;
import java.util.*;

import static Bank.asm04.Asm04.getNumberEnter;

public class DigitalBank extends Bank {

    private static final Scanner scanner = new Scanner(System.in);

    public DigitalBank(String name) {
        super(name);
    }

    public void showCustomers() {
        List<Customer> customers = CustomerDao.list();
        if (customers.size() == 0) {
            System.out.println("Chưa có khách hàng nào trong danh sách");
            return;
        }
        for (Customer customer : customers) {
            customer.displayInformation();
        }
    }

    // Inside DigitalBank class
    public void addCustomerFromFile(String filePath) throws IOException, CustomerIdNotValidException {
        List<Customer> existingCustomers = CustomerDao.list();

        // Kiểm tra xem mã số khách hàng đã tồn tại trong danh sách hay chưa
        System.out.print("Nhập mã số khách hàng: ");
        String newCustomerId = scanner.nextLine();

        // Kiểm tra nếu mã số khách hàng đã tồn tại
        if (isCustomerExisted(existingCustomers, newCustomerId)) {
            System.out.println("Khách hàng " + newCustomerId + " đã tồn tại, thêm khách hàng không thành công");
        } else {
            // Nếu chưa tồn tại, hỏi người dùng có muốn thêm không
            System.out.println("Khách hàng chưa tồn tại. Bạn có muốn thêm không?");
            System.out.println("1. Đồng ý");
            System.out.println("0. Hủy");
            int choice = getNumberEnter(); // Use the existing getNumberEnter method

            if (choice == 1) {
                // Nếu đồng ý, cho phép nhập tên và thêm mới khách hàng vào danh sách
                try {
                    Customer newCustomer = new Customer();
                    newCustomer.setCustomerId(newCustomerId);

                    // Đọc danh sách khách hàng từ tệp
                    List<List<String>> readCustomers = TextFileService.readFile(filePath);

                    // Tìm thông tin khách hàng trong danh sách đã đọc
                    for (List<String> readCustomer : readCustomers) {
                        if (Objects.equals(newCustomerId, readCustomer.get(0))) {
                            newCustomer.setName(readCustomer.get(1));
                            break;
                        }
                    }

                    // Hiển thị thông báo và yêu cầu nhập tên
                    System.out.print("Nhập tên khách hàng: ");
                    String customerName = scanner.nextLine();
                    newCustomer.setName(customerName);

                    existingCustomers.add(newCustomer);

                    System.out.println("Đã thêm khách hàng " + newCustomerId + " vào danh sách khách hàng");
                } catch (CustomerIdNotValidException e) {
                    System.out.println("Mã số ID của khách hàng không hợp lệ, thêm khách hàng không thành công");
                }
            } else if (choice == 0) {
                // Nếu chọn 0, hủy và quay lại màn hình chính
                return;
            }
        }

        // Lưu lại danh sách khách hàng sau khi cập nhật
        CustomerDao.save(existingCustomers);
    }

    public void addCustomer(String filePath) throws IOException, CustomerIdNotValidException {
        List<Customer> oldCustomers = CustomerDao.list();
        List<List<String>> readCustomers = TextFileService.readFile(filePath);

        for (List<String> readCustomer : readCustomers) {
            boolean isNewCustomer = true;
            for (Customer oldCustomer : oldCustomers) {
                if (Objects.equals(readCustomer.get(0), oldCustomer.getCustomerId())) {
                    isNewCustomer = false;
                    System.out.println("Khách hàng " + readCustomer.get(0) + " đã tồn tại, thêm khách hàng không thành công");
                    break;
                }
            }
            if (isNewCustomer) {
                try {
                    Customer customer = new Customer();
                    customer.setCustomerId(readCustomer.get(0));
                    customer.setName(readCustomer.get(1));
                    oldCustomers.add(customer);
                    System.out.println("Đã thêm khách hàng " + customer.getCustomerId() + " vào danh sách khách hàng");
                } catch (CustomerIdNotValidException e) {
                    System.out.println("Mã số id của khách hàng không hợp lệ, thêm khách hàng không thành công");
                }
            }
        }
        CustomerDao.save(oldCustomers);
    }

    public void addSavingsAccount(String customerId, String numberAccount, String strBalance) throws IOException {
        List<Customer> customers = CustomerDao.list();
        List<Account> accounts = AccountDao.list();
        List<Transaction> transactions = TransactionDao.list();

        boolean isCustomerExists = isCustomerExisted(customers, customerId);

        if (isCustomerExists) {
            // Remaining code for adding a savings account
            while (true) {
                boolean isAccountExisted = isAccountExisted(accounts, numberAccount);

                if (isAccountExisted) {
                    System.out.println("Tài khoản đã tồn tại, tác vụ không thành công");
                    System.out.print("Nhập mã số tài khoản gồm 6 chữ số: ");
                    numberAccount = scanner.nextLine();
                } else if (Account.validAccountNumber(numberAccount)) {
                    break;
                } else {
                    System.out.println("Không thể nhập ký tự hoặc chuỗi có chiều dài không hợp lệ !!!");
                    System.out.print("Nhập mã số tài khoản gồm 6 chữ số: ");
                    numberAccount = scanner.nextLine();
                }
            }

            double numberBalance;

            while (true) {
                try {
                    numberBalance = Double.parseDouble(strBalance);

                    if (numberBalance < 50000) {
                        System.out.println("Số dư ban đầu không được nhỏ hơn 50,000 đ");
                        System.out.print("Nhập số dư tài khoản >= 50.000 đ: ");
                        strBalance = scanner.nextLine();
                    } else {
                        break;
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Vui lòng nhập số tiền !!!");
                    System.out.print("Nhập số dư tài khoản >= 50.000 đ: ");
                    strBalance = scanner.nextLine();
                }
            }

            Account newAccount = new Account(numberAccount, numberBalance, "SAVINGS", customerId);
            accounts.add(newAccount);
            AccountDao.save(accounts);

            String time = getTimeD();
            Transaction newTransaction = new Transaction(customerId, numberAccount, numberBalance, time, true, "DEPOSIT");
            transactions.add(newTransaction);
            TransactionDao.save(transactions);

            System.out.println("Tạo tài khoản thành công");
        } else {
            System.out.println("Không tìm thấy khách hàng " + customerId + " tác vụ không thành công");
        }
    }

    public void showTransactionsCustomer(String customerId) {
        List<Customer> customers = CustomerDao.list();
        List<Transaction> transactions = TransactionDao.list();
        boolean isCustomer = false;

        // Find customer
        for (Customer customer : customers) {
            if (Objects.equals(customer.getCustomerId(), customerId)) {
                isCustomer = true;
                customer.displayInformation();
            }
        }

        if (!isCustomer) {
            System.out.println("Khách hàng chưa đăng ký !!!");
            return;
        }

        // Show history transaction
        try {
            for (Transaction transaction : transactions) {
                if (Objects.equals(transaction.getId(), customerId)) {
                    // Format type number balance
                    Locale localeEN = new Locale("en", "EN");
                    NumberFormat en = NumberFormat.getInstance(localeEN);
                    String strBalance = en.format(transaction.getAmount());
                    System.out.printf("[GD] %7s | %-10s | %14s đ |  %s",
                            transaction.getAccountNumber(), transaction.getType(), strBalance, transaction.getTime());
                    System.out.println();
                }
            }
        } catch (Exception e) {
            System.out.println("Khách hàng chưa thực hiện giao dịch !!!");
        }
    }

    public void withdraw(String customerId) throws IOException {
        List<Customer> customers = CustomerDao.list();
        List<Account> accounts = AccountDao.list();
        List<Transaction> transactions = TransactionDao.list();
        boolean isCustomer = false;
        for (Customer customer : customers) {
            if (Objects.equals(customer.getCustomerId(), customerId)) {
                isCustomer = true;
                customer.displayInformation();
            }
        }
        if (!isCustomer) {
            System.out.println("Khách hàng chưa đăng ký");
            return;
        }
        String accountNumber;
        String amountStr;
        double amount;
        // Find account number
        boolean isAccount = false;
        boolean tryWithdraw = true;
        while (tryWithdraw) {
            System.out.print("Nhập số tài khoản: ");
            accountNumber = scanner.nextLine();
            for (int k = 0; k < accounts.size(); k++) {
                if (Objects.equals(accounts.get(k).getAccountNumber(), accountNumber)) {
                    isAccount = true;
                    // Enter amount
                    while (true) {
                        try {
                            System.out.print("Nhập số tiền rút: ");
                            amountStr = scanner.nextLine();
                            amount = Double.parseDouble(amountStr);
                            break;
                        } catch (NumberFormatException e) {
                            System.out.println("Số tiền nhập vào phải là định dạng số !!!");
                        }
                    }
                    // Withdraw
                    SavingsAccount amountAccount = new SavingsAccount(
                            accounts.get(k).getAccountNumber()
                            , accounts.get(k).getBalance()
                            , "SAVINGS"
                            , customerId);
                    boolean isTransaction = amountAccount.withdraw(amount);
                    // Rewrite balance of account
                    accounts.get(k).setBalance(amountAccount.getBalance());
                    AccountDao.save(accounts);
                    // Add transaction file
                    if (isTransaction) {
                        String time = getTimeD();
                        Transaction newTransaction = new Transaction(
                                customerId
                                , accountNumber
                                , -amount
                                , time
                                , true
                                , "WITHDRAW");
                        transactions.add(newTransaction);
                    }
                    TransactionDao.save(transactions);
                    // end
                    tryWithdraw = false;
                    break;
                }
            }
            if (!isAccount) {
                System.out.println("Tài khoản chưa đăng ký !!!");
            }
        }
    }

    public void transferAmount(String customerId) throws IOException {
        List<Customer> customers = CustomerDao.list();
        List<Account> accounts = AccountDao.list();
        List<Transaction> transactions = TransactionDao.list();
        boolean isCustomer = false;
        for (Customer customer : customers) {
            if (Objects.equals(customer.getCustomerId(), customerId)) {
                isCustomer = true;
                customer.displayInformation();
            }
        }
        if (!isCustomer) {
            System.out.println("Khách hàng chưa đăng ký");
            return;
        }
        String accountNumberT = "";
        String accountNumberR = "";
        String amountStr;
        int accountT = -1;
        int accountR = -1;
        double amount;
        boolean loop1 = true;
        while (loop1) {
            System.out.print("Nhập số tài khoản: ");
            accountNumberT = scanner.nextLine();
            for (int i = 0; i < accounts.size(); i++) {
                if (Objects.equals(accounts.get(i).getAccountNumber(), accountNumberT)) {
                    accountT = i;
                    loop1 = false;
                    break;
                }
            }
            if (loop1) {
                System.out.println("Tài khoản chưa đăng ký");
            }
        }
        boolean loop2 = true;
        while (loop2) {
            System.out.print("Nhập số tài khoản nhận (exit) để thoát: ");
            accountNumberR = scanner.nextLine();
            if (Objects.equals(accountNumberR, "exit")) {
                return;
            }
            for (int i = 0; i < accounts.size(); i++) {
                if (Objects.equals(accounts.get(i).getAccountNumber(), accountNumberR)) {
                    accountR = i;
                    loop2 = false;
                    break;
                }
            }
            if (loop2) {
                System.out.println("Tài khoản chưa đăng ký");
            }
        }
        // Find account of customer receive
        String nameReceive = "";
        for (Customer customer : customers) {
            if (Objects.equals(customer.getCustomerId(), accounts.get(accountR).getCustomerId())) {
                nameReceive = customer.getName();
            }
        }
        System.out.println("Gửi tiền đến số tài khoản " + accountNumberR
                + " | " + nameReceive);
        // Scan input amount
        while (true) {
            try {
                System.out.print("Nhập số tiền chuyển: ");
                amountStr = scanner.nextLine();
                amount = Double.valueOf(amountStr);
                break;
            } catch (NumberFormatException e) {
                System.out.println("Số tiền nhập vào phải là định dạng số !!!");
            }
        }
        // Make object format type
        Locale localeEN = new Locale("en", "EN");
        NumberFormat en = NumberFormat.getInstance(localeEN);
        String strAmount = en.format(amount);
        while (true) {
            String inputEnterS;
            System.out.print("Xác nhận thực hiện chuyển " + strAmount + "đ từ tài khoản ["
                    + accountNumberT + "] đến tài khoản [" + accountNumberR + "] (Y/N): ");
            inputEnterS = scanner.nextLine();
            String inputEnter = inputEnterS.toLowerCase();
            if (Objects.equals(inputEnter, "n")) {
                System.out.println("Hủy giao dịch");
                break;
            } else if (Objects.equals(inputEnter, "y")) {
                System.out.println("Chuyển tiền thành công, biên lai giao dịch");
                SavingsAccount accountTransfer = new SavingsAccount(
                        accounts.get(accountT).getAccountNumber()
                        , accounts.get(accountT).getBalance()
                        , "SAVINGS"
                        , accounts.get(accountT).getCustomerId()
                );
                SavingsAccount accountReceive = new SavingsAccount(
                        accounts.get(accountR).getAccountNumber()
                        , accounts.get(accountR).getBalance()
                        , "SAVINGS"
                        , accounts.get(accountR).getCustomerId()
                );
                boolean isTransaction = accountTransfer.transfer(accountReceive, amount);
                accounts.get(accountT).setBalance(accountTransfer.getBalance());
                accounts.get(accountR).setBalance(accountReceive.getBalance());
                if (isTransaction) {
                    String time = getTimeD();
                    Transaction transactionTran = new Transaction(
                            accountTransfer.getCustomerId()
                            , accountNumberT
                            , -amount
                            , time
                            , true
                            , "TRANSFER"
                    );
                    Transaction transactionRece = new Transaction(
                            accountReceive.getCustomerId()
                            , accountNumberR
                            , amount
                            , time
                            , true
                            , "TRANSFER"
                    );
                    transactions.add(transactionTran);
                    transactions.add(transactionRece);
                }
                // Write and close file
                AccountDao.save(accounts);
                TransactionDao.save(transactions);
                break;
            } else {
                System.out.println("Ký tự nhập vào không hợp lệ. Hãy lựa chọn ký tự Y hoặc N");
            }
        }
    }

    private boolean isAccountExisted(List<Account> existingAccounts, String accountNumber) {
        for (Account existingAccount : existingAccounts) {
            if (Objects.equals(existingAccount.getAccountNumber(), accountNumber)) {
                return true;
            }
        }
        return false;
    }

    public boolean isCustomerExisted(List<Customer> existingCustomers, String customerId) {
        return existingCustomers.stream().anyMatch(customer -> Objects.equals(customer.getCustomerId(), customerId));
    }


public String getTimeD(){
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
