package day03.ex02;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Program {
    private static final String ARG_ARRAY_SIZE = "--arraySize=";
    private static final String ARG_THREADS_COUNT = "--threadsCount=";
    private static final int MAX_SIZE = 2_000_000;
    private static final int MIN_SIZE = 0;
    private static int arrSize = 0;
    private static int threadsCount = 0;
    public static void main(String[] args) {
        checkInput(args);
        Random random = new Random();
        List<Integer> array = new ArrayList<>(arrSize);
        for (int i = 0; i < arrSize; i++) {
            array.add(random.nextInt() % 1000);
        }
        System.out.println("//////////");
        for (int num : array) {
            System.out.println(num);
        }
        System.out.println("//////////");
        printSum(array);

        int range = arrSize / (threadsCount - 1);
        if (range * (threadsCount - 1) == arrSize && range != 1) {
            range--;
        }

        int begin = 0;
        int end = 0;
        List<Thread> threadList = new ArrayList<>(threadsCount);
        for (int i = 0; i < threadsCount - 1; i++) {
            begin = i * range;
            end = (i + 1) * range;
            threadList.add(new TempName(array.subList(begin, end), begin, end));
        }
        begin = range * (threadsCount - 1);
        end = arrSize;
        threadList.add(new TempName(array.subList(begin, end), begin, end));

        for (Thread thread : threadList) {
            thread.start();
        }

        for (Thread thread : threadList) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println("Sum by threads: " + TempName.getSumOfThreads());


    }

    private static void checkInput(String[] args) {
        if (args.length != 2
                || !args[0].startsWith(ARG_ARRAY_SIZE)
                || !args[1].startsWith(ARG_THREADS_COUNT)) {
            System.out.println("Error: You should write two arguments like");
            System.out.println("\"--arraySize=ARRAY_SIZE --threadsCount=THREADS_COUNT\"");
            System.exit(-1);
        }
        arrSize = Integer.parseInt(args[0].substring(ARG_ARRAY_SIZE.length()));
        threadsCount = Integer.parseInt(args[1].substring(ARG_THREADS_COUNT.length()));

        // UNNECESSARY, because input data is always correct
        if (arrSize <= MIN_SIZE || arrSize > MAX_SIZE) {
            System.out.println("Error: Impossible size of array: " + arrSize);
            System.exit(-1);
        }
        if (threadsCount == 1) {
            System.out.println("Error: Threads count must be greater than one");
            System.exit(-1);
        }
        if (threadsCount > arrSize) {
            System.out.println("Error: Threads count must be greater than array size");
            System.exit(-1);
        }
        // UNNECESSARY, because input data is always correct
    }

    private static void printSum(List<Integer> list) {
        int sum = 0;
        for (int num : list) {
            sum += num;
        }
        System.out.println("Sum: " + sum);
    }
}
