package Bank.asm01;
import java.lang.Math;
import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class asm01 {

    static Scanner sc = new Scanner(System.in);
    static final String Software = "NGAN HANG SO";
    static final String Author = "FX21967";
    static final String Version= "@v1.0.0";
    static final String[] birthAndSex = {"019", "220", "421", "622", "823",
            "119", "320", "521", "722", "923"};
    static final String[] ProvinceNumber = {
              "001-Ha Noi"
            , "002-Ha Giang"
            , "004-Cao Bang"
            , "006-Bac Kan"
            , "008-Tuyen Quang"
            , "010-Lao Cai"
            , "011-Dien Bien"
            , "012-Lai Chau"
            , "014-Son La"
            , "015-Yen Bai"
            , "017-Hoa Binh"
            , "019-Thai Nguyen"
            , "020-Lang Son"
            , "022-Quang Ninh"
            , "024-Bac Giang"
            , "025-Phu Tho"
            , "026-Vinh Phuc"
            , "027-Bac Ninh"
            , "030-Hai Duong"
            , "031-Hai Phong"
            , "033-Hung Yen"
            , "034-Thai Binh"
            , "035-Ha Nam"
            , "036-Nam Dinh"
            , "037-Ninh Binh"
            , "038-Thanh Hoa"
            , "040-Nghe An"
            , "042-Ha Tinh"
            , "044-Quang Binh"
            , "045-Quang Tri"
            , "046-Thua Thien Hue"
            , "048-Da Nang"
            , "049-Quang Nam"
            , "051-Quang Ngai"
            , "052-Binh Dinh"
            , "054-Phu Yen"
            , "056-Khanh Hoa"
            , "058-Ninh Thuan"
            , "060-Binh Thuan"
            , "062-Kon Tum"
            , "064-Gia Lai"
            , "066-Dak Lak"
            , "067-Dak Nong"
            , "068-Lam Dong"
            , "070-Binh Phuoc"
            , "072-Tay Ninh"
            , "074-Binh Duong"
            , "075-Dong Nai"
            , "077-Ba Ria Vung Tau"
            , "079-Ho Chi Minh"
            , "080-Long An"
            , "082-Tien Giang"
            , "083-Ben Tre"
            , "084-Tra Vinh"
            , "086-Vinh Long"
            , "087-Dong Thap"
            , "089-An Giang"
            , "091-Kien Giang"
            , "092-Can Tho"
            , "093-Hau Giang"
            , "094-Soc Trang"
            , "095-Bac Lieu"
            , "096-Ca Mau"};
//  main String khởi tạo chuong trình
   public static void main(String[] args) {
        try {
            logginMenu();
            switch (insertValidNumber(0, 1)) {
                case 0:
                    return;
                case 1:
                    optionMenu();
                    int temp = insertValidNumber(0, 2);
                    switch (temp) {
                        case 0:
                            return;
                        case 1:
                        case 2:
                            String otpNumber = taoOtpNumber(temp);
                            if (!isValidNumber(otpNumber))
                                return;
                            else {
                                System.out.print("Vui long nhap so CCCD: ");
                                String choice = validCCCDList();
                                if (choice.contentEquals("false")) return;
                                else {
                                    subMenu(choice);
                                }
                            }
                            break;
                    }
            }
        } catch (NoSuchElementException e) {
            System.out.println(e.getMessage());
        }
    }
    //Main user interface menu
    public static void logginMenu() {
        System.out.println("*----------*------------------------*----------*");
        System.out.println("| " + Software + " | " + Author + Version + "     |");
        System.out.println("*----------+-------------------------+----------*");
        System.out.println("| 1. Nhap CCCD                                  |");
        System.out.println("| 0. Thoat                                      |");
        System.out.println("*----------*-------------------------*----------");
        System.out.print("Chuc nang: ");
    }

    // Tùy chon do kho cua ma xac thuc
    public static void optionMenu() {
        System.out.println("Chon do kho cua ma xac thuc: ");
        System.out.println("\t| 1. Easy");
        System.out.println("\t| 2. Hard");
        System.out.println("\t| 0. Thoat");
        System.out.print("Chuc nang: ");
    }

    //SubMenu (tra cuu cac thong tin lien quan tren CCCD)
    public static void subMenu() {
        System.out.println("\t| 1. Kiem tra noi sinh");
        System.out.println("\t| 2. Kiem tra tuoi, gioi tinh");
        System.out.println("\t| 3. Kiem tra so ngau nhien");
        System.out.println("\t| 0. Thoat");
        System.out.print("Chuc nang: ");
    }

    // Tạo 1 số ngẫu nhiên nằm trong đoạn [min, max]
    public static int ramdomNumber(int min, int max) {
        return min + (int) ((max - min + 1) * Math.random());
    }

    // Tao ma xac thuc
    public static String taoOtpNumber(int userChoice) {
       // Mã số bảo mật ngẫu nhiên từ 100 đến 999
        if (userChoice == 1) {
            return String.valueOf(ramdomNumber(100, 999));
        }
        // Mã xác thực gồm có 6 ký tự [0..9, A..Z, a..z]
        StringBuilder otpNumber = new StringBuilder();
        for (int i = 0; i < 6; i++) {
     
            int choiceList = ramdomNumber(0, 2);
            switch (choiceList) {
                case 0:
                    otpNumber.append((char) (ramdomNumber(48, 57)));
                    break;
                case 1:
                    otpNumber.append((char) (ramdomNumber(65, 90)));
                    break;
                default:
                    otpNumber.append((char) (ramdomNumber(97, 122)));
                    break;
            }
        }
        return otpNumber.toString();
    }

    // Tìm tên tỉnh thành dựa trên mã tỉnh
    public static String getProvince(String provinceCode) {
        for (String unit : ProvinceNumber) {
            if (unit.startsWith(provinceCode)) {
                return unit.substring(4);
            }
        }
        return "-1"; //  
    }

   //     Trả về kết quả giới tính và năm sinh
    public static String checkBirthYearAndGender(String cccd) {
        String outPut = "Gioi tinh: ";
        for (String unit : birthAndSex) {
            // Neu ky tu thu 3 cua cccd ma CHAN thi la Nam va LE thi la Nu
            if (cccd.charAt(3) == unit.charAt(0)) {
                if ((cccd.charAt(3)) % 2 == 0) outPut += "Nam | ";
                else outPut += "Nu | ";
                outPut += unit.substring(1) + cccd.substring(4, 6);
                break;
            }
        }
        return outPut;
    }

    // Kiểm tra mã CCCD mà người dùng nhập vào.
    // Nếu không hợp lệ, ghi chi tiết lí do
    public static int checkCCCD(String cccd) {
        if (cccd.length() != 12) return -3; // Không hợp lệ về độ dài
        else {
            for (int i = 0; i < 12; i++) {
                if (cccd.charAt(i) < 48 || cccd.charAt(i) > 57)
                    return -2; // Không hợp lệ vì có chứa ký tự là chữ
            }
        }
        if (getProvince(cccd.substring(0, 3)).contentEquals(""))
            return -1; // Không hợp lệ vì không tồn tại mã tỉnh
        else return 0; // Hợp lệ
    }

    // Cho phép truy cập thông tin nếu điền cccd hợp lệ
    public static void subMenu(String cccd) {
        do {
            int insert;
            subMenu();
            insert = insertValidNumber(0, 3);
            switch (insert) {
                case 0:
                    sc.close();
                    return;
                case 1:
                    System.out.println("Noi Sinh: " + getProvince(cccd.substring(0, 3)));
                    break;
                case 2:
                    System.out.println(checkBirthYearAndGender(cccd));
                    break;
                case 3:
                    System.out.println("So ngau nhien: " + cccd.substring(6));
                    break;
            }
        } while (true);
    }

    // Buộc người dùng nhập các số nguyên nằm trong phạm vi của các menu
    public static int insertValidNumber(int min, int max) {
        int insert;
        while (true) {
            try {
                insert = sc.nextInt();
                if (insert < min || insert > max) {
                    sc.nextLine(); // Đọc ký tự newline sau lệnh nextInt()
                    System.out.println("Vui long nhap so nguyen nam trong doan [" + min + " ... " + max + "]");
                } else {
                    sc.nextLine();
                    return insert;
                }
            } catch (InputMismatchException e) {
                sc.nextLine();
                System.out.println("Vui long nhap so nguyen nam trong doan [" + min + " ... " + max + "]");
            }
        }
    }

    // Buộc người dùng phải nhập CCCD hợp lệ, hoặc thoát chương trình
    public static String validCCCDList() {
        while (true) {
            String choice = sc.nextLine();
            if (checkCCCD(choice) == 0)
                return choice;
            else {
                System.out.println("CCD khong hop le. Vui long nhap lai: ");
                //System.out.println("Vui long nhap lai, hoac nhap \"No\" de thoat.");
                switch (checkCCCD(choice)) {
                    case -3:
                        System.out.println("(Loi: CCCD co do dai KHAC 12 ky tu!)");
                        break;
                    case -2:
                        System.out.println("(Loi: CCCD phai la chuoi 12 so nguyen!)");
                        break;
                    case -1:
                        System.out.println("(Loi: Ma tinh khong ton tai!)");
                        break;
                }
            }
        }
    }

    // Buộc người dùng nhập ĐÚNG mã xác thực hoặc thoát chương trình
    public static boolean isValidNumber(String otpNumber) {
        System.out.println("Nhap ma xac thuc: " + otpNumber);
        while (true) {
            String choice = sc.nextLine();
            // chioce la "no"
            if (choice.toLowerCase().contentEquals("no"))
                return false;
            else if (choice.contentEquals(otpNumber)) return true;
            else {
                System.out.println("Ma xac thuc khong dung.");
                System.out.println("Vui long nhap lai, hoac nhap \"No\" de thoat.");
            }
        }
    }   
}