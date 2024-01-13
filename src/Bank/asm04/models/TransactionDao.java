package Bank.asm04.models;

import java.io.IOException;
import java.util.List;

public class TransactionDao {
    private final static String FILE_PATH = "src/Bank/asm04/store/transactions.dat";
    public static void save(List<Transaction> transactions) throws IOException {
        BinaryFileService.writeFile(FILE_PATH, transactions);
    }

    public static List<Transaction> list() {
        return BinaryFileService.readFile(FILE_PATH);
    }
}
