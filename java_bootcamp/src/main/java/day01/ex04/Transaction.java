package day01.ex04;

import java.util.UUID;

public class Transaction {
    private UUID identifier;
    private User recipient;
    private User sender;
    private Category transferCategory;
    private int transferAmount;
    private Transaction next;

    enum Category {
        DEBIT,
        CREDIT,
    }

    public Transaction(User recipient, User sender, Category transferCategory, int transferAmount) {
        this.identifier = UUID.randomUUID();
        this.recipient = recipient;
        this.sender = sender;
        this.transferCategory = transferCategory;
        setTransferAmount(transferAmount);
    }

    public UUID getIdentifier() {
        return identifier;
    }

    public void setIdentifier(UUID identifier) {
        this.identifier = identifier;
    }

    public User getRecipient() {
        return recipient;
    }

    public void setRecipient(User recipient) {
        this.recipient = recipient;
    }

    public User getSender() {
        return sender;
    }

    public void setSender(User sender) {
        this.sender = sender;
    }

    public Category getTransferCategory() {
        return transferCategory;
    }

    public void setTransferCategory(Category transferCategory) {
        this.transferCategory = transferCategory;
    }

    public int getTransferAmount() {
        return transferAmount;
    }

    public void setTransferAmount(int transferAmount) {
        if (transferCategory == Category.CREDIT && (transferAmount > 0 || sender.getBalance() < transferAmount)) {
            this.transferAmount = 0;
        } else if (transferCategory == Category.DEBIT && (transferAmount < 0 || recipient.getBalance() < transferAmount)) {
            this.transferAmount = 0;
        } else {
            this.transferAmount = transferAmount;
            changeUsersBalance(transferAmount);
        }
    }

    public void changeUsersBalance(int transferAmount) {
        if (transferCategory == Category.CREDIT) {
            sender.setBalance(sender.getBalance() - transferAmount);
            recipient.setBalance(recipient.getBalance() + transferAmount);
        } else {
            sender.setBalance(sender.getBalance() + transferAmount);
            recipient.setBalance(recipient.getBalance() - transferAmount);
        }
    }

    public Transaction getNext() {
        return next;
    }

    public void setNext(Transaction next) {
        this.next = next;
    }

    public void printTransferInfo() {
        System.out.print("Sender: " + this.sender.getName() + ". Recipient: " + this.recipient.getName());
        System.out.println(". Category: " + this.transferCategory + ". Amount: " + this.transferAmount);
    }
}
