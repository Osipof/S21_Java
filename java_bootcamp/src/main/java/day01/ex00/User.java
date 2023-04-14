package day01.ex00;

public class User {
    private int identifier;
    private String name;
    private int balance;

    public User(int identifier, String name, int balance) {
        this.identifier = identifier;
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

    public void setIdentifier(int identifier) {
        this.identifier = identifier;
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
        return "User:" +
                "\nidentifier = " + identifier +
                "\nname = " + name +
                "\nbalance = " + balance;
    }
}
