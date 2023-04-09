package day01.ex00;

public class Program {
    public static void main(String[] args) {
        User user1 = new User(1000, "Valeriy", 50000);
        User user2 = new User(3000, "Nikolay", 15000);

        System.out.println("Вывод информации о пользователях до проведения операций:\n");
        System.out.println(user1 + "\n");
        System.out.println(user2);

        System.out.println("Информация об операциях:\n");
        Transaction transaction1 = new Transaction(user1, user2, Transaction.Category.DEBIT, 5000);
        System.out.println(transaction1 + "\n");
        Transaction transaction2 = new Transaction(user1, user2, Transaction.Category.CREDIT, -1500);
        System.out.println(transaction2 + "\n");
        Transaction transaction3 = new Transaction(user2, user1, Transaction.Category.DEBIT, -1000);
        System.out.println(transaction3 + "\n");
        Transaction transaction4 = new Transaction(user2, user1, Transaction.Category.CREDIT, 1500);
        System.out.println(transaction4 + "\n");

        System.out.println("Вывод информации о пользователях после проведения операций:\n");
        System.out.println(user1 + "\n");
        System.out.println(user2);

    }
}
