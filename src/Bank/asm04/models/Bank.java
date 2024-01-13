package Bank.asm04.models;

import java.util.UUID;

public class Bank {
    private final String id;
    private final String name;

    public Bank(String name) {
        this.id = String.valueOf(UUID.randomUUID());
        this.name = name;
    }

    public String getId() {
        return this.id;
    }

    public String getName(){
        return this.name;
    }
}
