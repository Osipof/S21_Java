package day01.ex05;

import java.util.Scanner;

public class Menu {

    private TransactionsService facade;
    private Scanner scanner;

    public Menu() {
        facade = new TransactionsService();
        scanner = new Scanner(System.in);
    }

    public void run(boolean devMode) {
        System.out.println();
        while (true) {
            if (devMode) {
                selectionMenu(true);
                choiceMenu(getAnswer(true), true);
            } else {
                selectionMenu(false);
                choiceMenu(getAnswer(false), false);
            }
        }
    }

    public int getAnswer(boolean devMode) {
        String scan = scanner.nextLine().trim();
        int answer = 0;
        try {
            answer = Integer.parseInt(scan);
            if (answer <= 0 || (devMode && answer > 7 || !devMode && answer > 5)) {
                throw new RuntimeException("Invalid action. Enter a valid number: ");
            }
        } catch (RuntimeException e) {
            System.out.println(e);
            answer = getAnswer(devMode);
        }
        return answer;
    }

    public void selectionMenu(boolean devMode) {
        System.out.println("1. Add a user");
        System.out.println("2. View user balances");
        System.out.println("3. Perform a transfer");
        System.out.println("4. View all transactions for a specific user");
        if (!devMode) {
            System.out.println("5. Finish execution");
            return;
        }
        System.out.println("5. DEV – remove a transfer by ID");
        System.out.println("6. DEV – check transfer validity");
        System.out.println("7. Finish execution");
    }

    public void choiceMenu(int answer, boolean devMode) {
        switch (answer) {
            case 1:
                addUser();
                break;
            case 2:
                viewUserBalance();
                break;
            case 3:
                performTransfer();
                break;
            case 4:
                viewAllTransactionsOfUser();
                break;
            case 5:
                if (!devMode) {
                    System.exit(0);
                }
                removeTransferById();
                break;
            case 6:
                checkTransferValidity();
                break;
            case 7:
                System.exit(0);
        }
    }

    private void addUser() {
        System.out.println("Enter a user name and a balance");
        String input = scanner.nextLine().trim();

        try {
            String[] inputArr = input.split("\\s+");
            if (inputArr.length != 2) {
                throw new RuntimeException("Invalid data. Try again");
            }
            String name = inputArr[0];
            int balance = Integer.parseInt(inputArr[1]);
            User user = new User(name, balance);
            facade.addUser(user);
            System.out.println("User with id = " + user.getIdentifier() + " is added");
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            System.out.println("---------------------------------------------------------");
        }
    }

    private void viewUserBalance() {
        System.out.println("Enter a user ID");
        String input = scanner.nextLine().trim();
        try {
            int id = Integer.parseInt(input);
            int balance = facade.getUserBalance(id);
            System.out.println(facade.usersList.getUserById(id).getName() + " - " + balance);
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            System.out.println("---------------------------------------------------------");
        }
    }

    private void performTransfer() {
        System.out.println("Enter a sender ID, a recipient ID, and a transfer amount");
        String input = scanner.nextLine().trim();
        try {
            String[] inputArr = input.split("\\s+");
            if (inputArr.length != 3) {
                throw new RuntimeException("Invalid data. Try again");
            }
            int senderId = Integer.parseInt(inputArr[0]);
            int recipientId = Integer.parseInt(inputArr[1]);
            int amount = Integer.parseInt(inputArr[2]);
            facade.executeTransaction(senderId, recipientId, amount);
            System.out.println("The transfer is completed");
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            System.out.println("---------------------------------------------------------");
        }
    }

    private void viewAllTransactionsOfUser() {
        System.out.println("Enter a user ID");
        String input = scanner.nextLine().trim();
        try {
            int id = Integer.parseInt(input);
            Transaction[] transactions = facade.getTransactionsList(id);
            if (transactions == null) {
                throw new RuntimeException("User with ID = " + id + " hasn't any transactions");
            }
            for (Transaction item : transactions) {
                int idUser = (item.getTransferCategory() == Transaction.Category.DEBIT) ?
                        item.getSender().getIdentifier() :
                        item.getRecipient().getIdentifier();
                String category = (item.getTransferCategory() == Transaction.Category.DEBIT) ?
                        "From " :
                        "To ";
                String name = (item.getTransferCategory() == Transaction.Category.DEBIT) ?
                        item.getSender().getName() :
                        item.getRecipient().getName();
                System.out.println(category + name + "(id = " + idUser + ") " +
                        item.getTransferAmount() + " with id = " + item.getIdentifier());
            }
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            System.out.println("---------------------------------------------------------");
        }
    }

    private void removeTransferById() {
        System.out.println("removeTransferById");
    }

    private void checkTransferValidity() {
        System.out.println("checkTransferValidity");
    }
}
