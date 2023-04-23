package day00.ex01;

import java.util.Scanner;

public class Program {
    public static void checkArgument(int number) {
        if (number <= 1) {
            System.err.println("Illegal Argument");
            System.exit(-1);
        }
    }

    public static void checkPrime(int number) {
        int iterations = 1;
        boolean isPrime = true;
        if (number != 2 && number != 3) {
            if (number % 2 == 0 || number % 3 == 0) {
                isPrime = false;
            } else {
                for (int i = 5; i <= Math.sqrt(number); i += 6, ++iterations) {
                    if (number % i == 0 || number % (i + 2) == 0) {
                        isPrime = false;
                        break;
                    }
                }
            }
        }
        System.out.println(isPrime + " " + iterations);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        if (scanner.hasNextInt()) {
            int number = scanner.nextInt();

            checkArgument(number);
            checkPrime(number);

        }
        scanner.close();
    }
}
