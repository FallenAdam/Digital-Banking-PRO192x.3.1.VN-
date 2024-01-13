package Bank.asm04.models;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class AccountDao {
    private final static String FILE_PATH = "src/Bank/asm04/store/accounts.dat";
    private static final int MAX_THREAD = 10;

    public static void save(List<Account> accounts) throws IOException {
        BinaryFileService.writeFile(FILE_PATH, accounts);
    }

    public static List<Account> list() {
        return BinaryFileService.readFile(FILE_PATH);
    }

    public static void update(Account editAccount) throws IOException {
        List<Account> accounts = list();
        ExecutorService executorService = Executors.newFixedThreadPool(MAX_THREAD);
        List<Future<Boolean>> futures = new ArrayList<>();

        for (Account account : accounts) {
            Callable<Boolean> task = () -> account.getAccountNumber().equals(editAccount.getAccountNumber());
            Future<Boolean> future = executorService.submit(task);
            futures.add(future);
        }

        try {
            boolean isNew = true;
            for (int i = 0; i < accounts.size(); i++) {
                if (futures.get(i).get()) {
                    isNew = false;
                    break;
                }
            }
            if (isNew) {
                accounts.add(editAccount);
            }
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace(); // Handle exceptions appropriately
        } finally {
            executorService.shutdown();
        }

        save(accounts);
    }
}
