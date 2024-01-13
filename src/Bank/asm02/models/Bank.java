package Bank.asm02.models;
import java.util.ArrayList;

import java.util.List;
import java.util.UUID;

public class Bank {
    private final String id;
    private final List<Customer> customers;

    public Bank() {
        // Khởi tạo danh sách khách hàng và tạo một ID duy nhất cho ngân hàng.
        this.customers = new ArrayList<>();
        this.id = String.valueOf(UUID.randomUUID());
    }

    public List<Customer> getCustomers() {
        // Trả về danh sách khách hàng.
        return customers;
    }

    // Kiểm tra xem khách hàng có tồn tại trong danh sách dựa trên mã khách hàng.
    public int isCustomerExisted(String customerId) {
        List<Customer> curS = getCustomers();
        ArrayList<String> cccdS = new ArrayList<>();
        for (Customer cus : curS)
            cccdS.add(cus.getCustomerId());
        return cccdS.indexOf(customerId);
    }

    // Kiểm tra xem số tài khoản có tồn tại trong danh sách tài khoản của tất cả khách hàng.
    public int isAccountExisted(String soTaiKhoan) {
        List<Customer> curS = getCustomers();
        ArrayList<Account> accS = new ArrayList<>();
        ArrayList<String> stkS = new ArrayList<>();
        for (Customer cus : curS)
            accS.addAll(cus.getAccounts());
        for (Account acc : accS)
            stkS.add(acc.getAccountNumber());
        return stkS.indexOf(soTaiKhoan);
    }

    // Thêm một khách hàng mới vào danh sách nếu mã khách hàng chưa tồn tại.
    public void addCustomer(Customer newCustomer) {
        if (isAccountExisted(newCustomer.getCustomerId()) == -1)
            customers.add(newCustomer);
        else
            System.out.println("khach hang da ton tai");
    }

    // Thêm một tài khoản vào danh sách tài khoản của một khách hàng.
    public void addAccount(String customerId, Account account) {
        int indexCus = isCustomerExisted(customerId);
        if (indexCus > -1) {
            if (isAccountExisted(account.getAccountNumber()) == -1)
                customers.get(indexCus).addAccount(account);
            else {
                System.out.println("Số tài khoản đã được sử dụng");
            }
        } else
            System.out.println("Khong ton tai khach hang nay!");
    }

    // Tìm kiếm và hiển thị thông tin khách hàng dựa trên mã CCCD.
    public void searchCustomerByCCCD(String customerId) {
        int indexCus = isCustomerExisted(customerId);
        List<Customer> curS = getCustomers();
        if (indexCus == -1)
            System.out.println("Khach hang khong ton tai!");
        else
            curS.get(indexCus).displayInformation();
    }

    // Tìm kiếm và hiển thị thông tin khách hàng dựa trên tên.
    public void searchCustomerByName(String name) {
        List<Customer> curS = getCustomers();
        for (Customer cus : curS) {
            if (cus.getName().contains(name) || name.contains(cus.getName())) {
                cus.displayInformation();
            }
        }
    }
}
