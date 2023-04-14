package day01.ex03;

import day01.ex01.UserIdsGenerator;

public class User {
    private final int identifier;
    private String name;
    private int balance;
    private TransactionsLinkedList transactionsList;

    public User() {
        this.identifier = UserIdsGenerator.getInstance().generateId();
        this.balance = 0;
        this.transactionsList = new TransactionsLinkedList();
    }

    public User(String name, int balance) {
        this.identifier = UserIdsGenerator.getInstance().generateId();
        this.name = name;
        this.transactionsList = new TransactionsLinkedList();
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

    public TransactionsLinkedList getTransactionsList() {
        return transactionsList;
    }

    public void setTransactionsList(TransactionsLinkedList transactionsList) {
        this.transactionsList = transactionsList;
    }

    @Override
    public String toString() {
        return "User " + identifier +
                ":\nname = " + name +
                "\nbalance = " + balance;
    }
}
