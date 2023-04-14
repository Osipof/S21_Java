package day01.ex02;

public class Program {
    public static void main(String[] args) {
        User[] users = new User[21];
        for (int i = 0; i < 21; i++) {
            users[i] = new User("User" + (i + 1), (i + 1000) * 2 / 5);
            System.out.println(users[i]);
        }
        UsersArrayList usersArrayList = new UsersArrayList();
        for (int i = 0; i < 21; i++) {
            usersArrayList.addUser(users[i]);
        }

        System.out.println("Все пользователи:");
        usersArrayList.printList();

        User userId = usersArrayList.getUserById(15);
        User userIndex = usersArrayList.getUserByIndex(15);
        System.out.println("Получение пользователя с id = 15:");
        System.out.println(userId);
        System.out.println("Получение пользователя с index = 15:");
        System.out.println(userIndex);

        try {
            User userError1 = usersArrayList.getUserById(50);
        } catch (UserNotFoundException exception) {
            exception.printStackTrace();
        }

        try {
            User userError2 = usersArrayList.getUserByIndex(100);
        } catch (UserNotFoundException exception) {
            exception.printStackTrace();
        }

        System.out.println();
        UsersArrayList list = new UsersArrayList();
        list.addUser(new User("Ivan", 1000));
        System.out.println(list.getUserByIndex(1));
    }
}
