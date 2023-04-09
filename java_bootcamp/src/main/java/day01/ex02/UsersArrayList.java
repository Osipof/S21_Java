package day01.ex02;

public class UsersArrayList implements UsersList {

    private Integer numberOfUsers = 0;
    private Integer maxNumberOfUsers = 10;
    private User[] users = new User[maxNumberOfUsers];

    @Override
    public void addUser(User newUser) {
        if (numberOfUsers == maxNumberOfUsers) {
            maxNumberOfUsers *= 2;
            User[] tempUsers = new User[maxNumberOfUsers];
            for (int i = 0; i < numberOfUsers; i++) {
                tempUsers[i] = users[i];
            }
            users = tempUsers;
        }
        users[numberOfUsers++] = newUser;
    }

    @Override
    public User getUserById(Integer id) {
        for (int i = 0; i < numberOfUsers; i++) {
            if (users[i].getIdentifier() == id) {
                return users[i];
            }
        }
        throw new UserNotFoundException("index with id " + id + " is not found");
    }

    @Override
    public User getUserByIndex(Integer index) {
        if (index < 0 || index > numberOfUsers) {
            throw new UserNotFoundException("index with index " + index + " is not found");
        }
        return users[index];
    }

    @Override
    public Integer getNumberOfUsers() {
        return numberOfUsers;
    }
}
