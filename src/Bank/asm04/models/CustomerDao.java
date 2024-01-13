package Bank.asm04.models;

import java.io.IOException;
import java.util.List;

public class CustomerDao {
    private final static String FILE_PATH = "src/Bank/asm04/store/customers.dat";

    public static void save(List<Customer> customers) throws IOException {
        BinaryFileService.writeFile(FILE_PATH, customers);
    }

    public static List<Customer> list() {
        return BinaryFileService.readFile(FILE_PATH);
    }
}
