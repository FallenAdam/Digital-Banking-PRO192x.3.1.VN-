package Bank.asm04.models;

public interface ITtransfer {
    boolean transfer(Account receiveAccount, double amount);
}
