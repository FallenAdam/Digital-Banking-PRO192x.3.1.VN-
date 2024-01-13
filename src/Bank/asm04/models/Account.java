package Bank.asm04.models;

import java.io.Serializable;
import java.text.NumberFormat;
import java.util.*;

public class Account implements Serializable {
    private String accountNumber;
    private double balance;

    private String customerId;
    private String accountType;
    
    private long serialVersionUID ;

    public Account() {
    }

    public Account(String accountNumber, double balance, String accountType, String customerId) {
        this.accountNumber = accountNumber;
        this.balance = balance;
        this.accountType = accountType;
        this.customerId = customerId;
    }

    // Set balance of account
    public void setBalance(double balance) {
        this.balance = balance;
    }

    // Get balance of aacount
    public double getBalance() {
        return this.balance;
    }

    // Check account number is valid or not
    public static boolean validAccountNumber(String accountNumber) {
        // Check length
        boolean lengthCondition;
        lengthCondition = accountNumber.length() == 6;
        // Check is number
        boolean numCondition;
        try {
            double d = Double.parseDouble(accountNumber);
            numCondition = true;
        } catch (NumberFormatException nfe) {
            numCondition = false;
        }
        return lengthCondition && numCondition;
    }

    // Set account number
    public void setAccountNumber(String accountNumber) {
        if (validAccountNumber(accountNumber)) {
            this.accountNumber = accountNumber;
        }
    }

    // Get account number
    public String getAccountNumber() {
        return this.accountNumber;
    }

    // Check account is Premium or not
    public boolean isPremium() {
        return this.balance >= 10000000;
    }

    // Print information of account
    public void toStringUser() {
        String typeAccount = "UNKNOWN";
        if(Objects.equals(getAccountType(), "SAVINGS")){
            typeAccount = "ATM";
        }
        if(Objects.equals(getAccountType(), "LOAN")){
            typeAccount = "CREDIT";
        }
        // Format type number
        NumberFormat currentLocale = NumberFormat.getInstance();
        Locale localeEN = new Locale("en", "EN");
        NumberFormat en = NumberFormat.getInstance(localeEN);
        String str1 = en.format(this.balance);
        // Print to screen
        System.out.printf("%s    | %-14s  | %28sđ\n", this.accountNumber, typeAccount, str1);
    }

    // Get account Type
    public String getAccountType() {
        return accountType;
    }

    // Set account Type
    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    // Check id of customer is valid or not
    public static boolean validID(String customerId){
        //Creating a dictionary for place
        Map<String, String> dict1 = new HashMap<String, String>();
        //Adding values in the dictionary
        dict1.put("001", "Hà Nội");
        dict1.put("002", "Hà Giang");
        dict1.put("004", "Cao Bằng");
        dict1.put("006", "Bắc Kạn");
        dict1.put("008", "Tuyên Quang");
        dict1.put("010", "Lào Cai");
        dict1.put("011", "Điện Biên");
        dict1.put("012", "Lai Châu");
        dict1.put("014", "Sơn La");
        dict1.put("015", "Yên Bái");
        dict1.put("017", "Hòa Bình");
        dict1.put("019", "Thái Nguyên");
        dict1.put("020", "Lạng Sơn");
        dict1.put("022", "Quảng Ninh");
        dict1.put("024", "Bắc Giang");
        dict1.put("025", "Phú Thọ");
        dict1.put("026", "Vĩnh Phúc");
        dict1.put("027", "Bắc Ninh");
        dict1.put("030", "Hải Dương");
        dict1.put("031", "Hải Phòng");
        dict1.put("033", "Hưng Yên");
        dict1.put("034", "Thái Bình");
        dict1.put("035", "Hà Nam");
        dict1.put("036", "Nam Định");
        dict1.put("037", "Ninh Bình");
        dict1.put("038", "Thanh Hóa");
        dict1.put("040", "Nghệ An");
        dict1.put("042", "Hà Tĩnh");
        dict1.put("044", "Quảng Bình");
        dict1.put("045", "Quảng Trị");
        dict1.put("046", "Thừa Thiên Huế");
        dict1.put("048", "Đà Nẵng");
        dict1.put("049", "Quảng Nam");
        dict1.put("051", "Quảng Ngãi");
        dict1.put("052", "Bình Định");
        dict1.put("054", "Phú Yên");
        dict1.put("056", "Khánh Hòa");
        dict1.put("058", "Ninh Thuận");
        dict1.put("060", "Bình Thuận");
        dict1.put("062", "Kon Tum");
        dict1.put("064", "Gia Lai");
        dict1.put("066", "Đắk Lắk");
        dict1.put("067", "Đắk Nông");
        dict1.put("068", "Lâm Đồng");
        dict1.put("070", "Bình Phước");
        dict1.put("072", "Tây Ninh");
        dict1.put("074", "Bình Dương");
        dict1.put("075", "Đồng Nai");
        dict1.put("077", "Bà Rịa - Vũng Tàu");
        dict1.put("079", "Hồ Chí Minh");
        dict1.put("080", "Long An");
        dict1.put("082", "Tiền Giang");
        dict1.put("083", "Bến Tre");
        dict1.put("084", "Trà Vinh");
        dict1.put("086", "Vĩnh Long");
        dict1.put("087", "Đồng Tháp");
        dict1.put("089", "An Giang");
        dict1.put("091", "Kiên Giang");
        dict1.put("092", "Cần Thơ");
        dict1.put("093", "Hậu Giang");
        dict1.put("094", "Sóc Trăng");
        dict1.put("095", "Bạc Liêu");
        dict1.put("096", "Cà Mau");
        //Check length of string
        boolean lengthCondition;
        lengthCondition = customerId.length() == 12;
        //Check string is number
        boolean numCondition;
        if (customerId == null) {
            numCondition = false;
        }
        try {
            double d = Double.parseDouble(customerId);
            numCondition = true;
        } catch (NumberFormatException nfe) {
            numCondition = false;
        }
        // Check city in string
        boolean cityCondition;
        String numCity = String.valueOf(customerId.charAt(0))
                + customerId.charAt(1)
                + customerId.charAt(2);
        cityCondition = !(dict1.get(numCity) == null);
        return lengthCondition && numCondition && cityCondition;
    }

    // Get id of customer
    public String getCustomerId(){
        return this.customerId;
    }

    // Get list transactions of account
    public List<Transaction> getTransactions(){
        List<Transaction> transactionList = TransactionDao.list();
        List<Transaction> accountTransactions = new ArrayList<>();
        transactionList.forEach(transaction -> {
            if(Objects.equals(transaction.getAccountNumber(), accountNumber)){
                accountTransactions.add(transaction);
            }
        });
        return accountTransactions;
    }
}
