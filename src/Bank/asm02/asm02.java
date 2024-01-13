package Bank.asm02;
import java.util.Scanner;

import Bank.asm02.models.Account;
import Bank.asm02.models.Bank;
import Bank.asm02.models.Customer;
import Bank.asm01.asm01;

import java.util.InputMismatchException;
import java.util.List;
import java.util.NoSuchElementException;
public class asm02 {
     // Create a Scanner object to read user input
     public static Scanner sc = new Scanner(System.in);
     // Declare constant strings for software information
     static final String Software = "NGAN HANG SO";
     static final String Author = "FX21967";
     static final String Version = "@v2.0.0";
     // Create an instance of the Bank class
     private static final Bank bank = new Bank();



    public static void main(String[] args) {
        // The main program loop
        while (true) {
            try {
                logginMenu(); // Display the login menu

                // Read a valid number from the user using asm01.insertValidNumber method
                switch (asm01.insertValidNumber(0, 5)) {
                    case 0:
                        return; // Exit the program if the user chooses 0
                    case 1:
                        addCustomer(); // Call the method to add a new customer
                        break;
                    case 2:
                        addCustomerAccount(); // Call the method to add a customer's account
                        break;
                    case 3:
                        showCustomerList(); // Call the method to display the customer list
                        break;
                    case 4:
                        searchByCccdNumber(); // Call the method to search by CCCD number
                        break;
                    case 5:
                        searchByCustomerName(); // Call the method to search by customer name
                        break;
                }
            } catch (NoSuchElementException e) {
                System.out.println(e.getMessage()); // Print an error message if there's an input error
            }
        }
    }


 public static void logginMenu() {
        System.out.println("*----------*----------------*---------*----------+");
        System.out.println("| " +Software + " | " + Author+ Version + "       |");
        System.out.println("*----------*----------------*--------*----------+");
        System.out.println(" 1. Them khach hang                              ");
        System.out.println(" 2. Them tai khoan cho khach hang                ");
        System.out.println(" 3. Hien thi danh sach khach hang                ");
        System.out.println(" 4. Tim theo CCCD                                ");
        System.out.println(" 5. Tim theo ten khach hang                      ");
        System.out.println(" 0. Thoat                                        ");
        System.out.println("*----------*-----------------*--------*----------*");
        System.out.print("Chuc nang: ");
    }

    public static void addCustomer() {
        System.out.println("Nhap ten khach hang: ");
        String name = sc.nextLine();
        System.out.println("Nhap so CCCD: ");
        String cccd = asm01.validCCCDList();
        if (bank.isCustomerExisted(cccd) == -1) {
            Customer newCus = new Customer();
            newCus.setName(name);
            newCus.setCustomerId(cccd);
            bank.addCustomer(newCus);
        } else
            System.out.println("Khach hang nay da ton tai !!!");
    }

    public static void showCustomerList() {
        List<Customer> curS = bank.getCustomers();
        for (Customer newCus : curS)
            newCus.displayInformation();
    }

    public static String validAccInput() {
        while (true) {
            System.out.println("Nhap so tai khoan gom 6 chu so");
            String acc = sc.nextLine();
            if (Account.isValidAccount(acc))
                return acc;
        }
    }

    public static double insertValidBalance() {
        System.out.println("Vui long nhap so du lon hon 50000 VND");
        while (true) {
            try {
                double AccBalance = sc.nextDouble();
                if (AccBalance >= 50000) {
                    sc.nextLine();
                    return AccBalance;
                } else
                    System.out.println("Vui long nhap so du lon hon 50_000 VND");
            } catch (InputMismatchException e) {
                sc.nextLine();
                System.out.println("Vui long nhap so du lon hon 50_000 VND");
            }
        }
    }

    public static String cccdNumberMatched() {
        String cccd;
        System.out.println("Nhap CCCD Khach hang: ");
        while (true) {
            cccd = asm01.validCCCDList();
        
            if (bank.isCustomerExisted(cccd) != -1)
                return cccd;
            
            else
                System.out.println("Khach hang khong ton tai.Vui long nhap lai so CCCD ");
        }

    }

// Method to add a customer's account
public static void addCustomerAccount() {
    // Prompt the user to enter CCCD number and validate it
    String cccd = cccdNumberMatched();
    // Prompt the user to enter a valid account number
    String acc = validAccInput();
    // Prompt the user to enter a valid account balance
    double AccBalance = insertValidBalance();
    // Create a new Account object and add it to the bank's accounts using the provided CCCD and account details
    bank.addAccount(cccd, new Account(acc, AccBalance));
}

// Method to search for customers by their name
public static void searchByCustomerName() {
    System.out.println("Nhap ten khach hang:");
    // Read the customer name from the user
    String name = sc.nextLine();
    // Search for and display customer information based on the provided name
    bank.searchCustomerByName(name);
}

// Method to search for customers by their CCCD number
public static void searchByCccdNumber() {
    System.out.println("Nhap ma CCCD cua khach hang: ");
    // Read the CCCD number from the user
    String cccd = sc.nextLine();
    // Search for and display customer information based on the provided CCCD number
    bank.searchCustomerByCCCD(cccd);
}

   


  

}
