package day01.ex02;

public class UserIdsGenerator {

    private static UserIdsGenerator generator;
    private static Integer identifier = 0;

    public static UserIdsGenerator getInstance() {
        if (generator == null) {
            generator = new UserIdsGenerator();
        }
        return generator;
    }

    private UserIdsGenerator() {
    }

    public Integer generateId() {
        return ++identifier;
    }
}
