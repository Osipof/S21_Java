package day01.ex01;

public class UserIdsGenerator {

    private static UserIdsGenerator generator;
    private static int identifier = 0;

    public static UserIdsGenerator getInstance() {
        if (generator == null) {
            generator = new UserIdsGenerator();
        }
        return generator;
    }
    private UserIdsGenerator() {}

    public int generateId() {
        return ++identifier;
    }
}
