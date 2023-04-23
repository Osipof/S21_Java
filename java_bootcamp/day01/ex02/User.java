package day01.ex02;

public class User {
    private final int identifier;
    private String name;
    private int balance;

    public User() {
        this.identifier = UserIdsGenerator.getInstance().generateId();
        this.balance = 0;
    }

    public User(String name, int balance) {
        this.identifier = UserIdsGenerator.getInstance().generateId();
        this.name = name;
        if (balance < 0) {
            this.balance = 0;
        } else {
            this.balance = balance;
        }
    }

    public int getIdentifier() {
        return identifier;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        if (balance < 0) {
            this.balance = 0;
        } else {
            this.balance = balance;
        }
    }

    @Override
    public String toString() {
        return "User " + identifier +
                ":\nname = " + name +
                "\nbalance = " + balance;
    }
}
