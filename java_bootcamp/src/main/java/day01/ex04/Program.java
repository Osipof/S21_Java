package day01.ex04;

public class Program {
    public static void main(String[] args) {
        TransactionsService facade = new TransactionsService();

        User user1 = new User("Ivan", 10000);
        User user2 = new User("Anatoliy", 17330);

        facade.addUser(user1);
        facade.addUser(user2);

        System.out.println("Users before transactions:");
        facade.usersList.printList();

        facade.executeTransaction(user1.getIdentifier(), user2.getIdentifier(), 3000);
        facade.executeTransaction(user1.getIdentifier(), user2.getIdentifier(), 150);
        facade.executeTransaction(user2.getIdentifier(), user1.getIdentifier(), 4001);

        System.out.println("\nUsers after three transactions:");
        facade.usersList.printList();

        System.out.println("\nInformation about " + user1.getName() + "'s transactions:");
        Transaction[] transactionsArr1 = facade.getTransactionsList(user1.getIdentifier());
        for (Transaction item : transactionsArr1) {
            item.printTransferInfo();
        }

        System.out.println("\nInformation about " + user2.getName() + "'s transactions:");
        Transaction[] transactionsArr2 = facade.getTransactionsList(user2.getIdentifier());
        for (Transaction item : transactionsArr2) {
            item.printTransferInfo();
        }

        System.out.println("\nRemove one " + user1.getName() + "'s transaction:");
        facade.removeTransaction(transactionsArr1[1].getIdentifier(), user1.getIdentifier());

        System.out.println("\nInformation about " + user1.getName() + "'s transactions:");
        transactionsArr1 = facade.getTransactionsList(user1.getIdentifier());
        for (Transaction item : transactionsArr1) {
            item.printTransferInfo();
        }

        System.out.println("\nInformation about " + user2.getName() + "'s transactions:");
        transactionsArr2 = facade.getTransactionsList(user2.getIdentifier());
        for (Transaction item : transactionsArr2) {
            item.printTransferInfo();
        }

        System.out.println("\nInformation about unpaired operations:");
        Transaction[] invalid = facade.checkValidityOfTransactions();
        for (Transaction item : invalid) {
            item.printTransferInfo();
        }

        System.out.println("Exceptions:");
        try {
            facade.executeTransaction(user1.getIdentifier(), user2.getIdentifier(), 100000);
        } catch (Exception e) {
            e.printStackTrace();
        }

        User user = new User("Kirill", 100);
        try {
            facade.getUserBalance(user);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
