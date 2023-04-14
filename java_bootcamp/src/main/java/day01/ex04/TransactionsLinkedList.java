package day01.ex04;

import java.util.UUID;

public class TransactionsLinkedList implements TransactionsList {

    private Node first;
    private Node last;
    private int size;

    private static class Node {
        Transaction transaction;
        Node next;
        Node prev;

        Node(Node next, Node prev, Transaction transaction) {
            this.next = next;
            this.prev = prev;
            this.transaction = transaction;
        }
    }

    public TransactionsLinkedList() {}

    @Override
    public void addTransaction(Transaction transaction) {
        final Node lst = last;
        final Node newNode = new Node(null, lst, transaction);
        last = newNode;

        if (lst == null) {
            first = newNode;
        } else {
            lst.next = newNode;
        }
        size++;
    }

    @Override
    public void removeTransactionById(UUID id) {
        if (id == null) {
            throw new TransactionNotFoundException("Cannot be removed transaction with 'null' UUID.");
        }
        for (Node node = first; node != null; node = node.next) {
            if (node.transaction != null && id.equals(node.transaction.getIdentifier())) {
                final Node next = node.next;
                final Node prev = node.prev;
                if (prev == null) {
                    first = next;
                } else {
                    prev.next = next;
                    node.prev = null;
                }

                if (next == null) {
                    last = prev;
                } else {
                    next.prev = prev;
                    node.next = null;
                }
                node.transaction = null;
                size--;
                return;
            }
        }
        throw new TransactionNotFoundException("Transaction with UUID " + id + " not found.");
    }

    @Override
    public Transaction[] toArray() {
        if (size == 0) {
            return null;
        }
        Transaction[] transactions = new Transaction[this.size];
        int i = 0;
        for (Node node = first; node != null; node = node.next) {
            transactions[i++] = node.transaction;
        }
        return transactions;
    }

    public int getSize() {
        return size;
    }
}
