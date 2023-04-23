package day03.ex02;

import java.util.List;

public class TempName extends Thread {
    private static int sumOfThreads = 0;
    int bI;
    int lI;
    int localSum;

    private static synchronized void addToSum(int localSum, int b, int l) {
        System.out.println(Thread.currentThread().getName() +
                ": from " + b + " to " + l + " sum is " + localSum);
        sumOfThreads += localSum;
    }

    public static int getSumOfThreads() {
        return sumOfThreads;
    }

    public TempName(List<Integer> list, int b, int l) {
        bI = b;
        lI = l;
        localSum = 0;
        for (int num : list) {
            localSum += num;
        }
    }

    @Override
    public void run() {
        addToSum(localSum, bI, lI);
    }
}
