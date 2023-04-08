package day00.ex02;

import java.util.Scanner;

public class Program {

    public static int sumOfDigits(int number) {
        int result = 0;
        result += number % 10;
        while (number > 10) {
            number /= 10;
            result += number % 10;
        }
        System.out.println(result);
        return result;
    }

    public static boolean checkPrime(int number) {
        boolean isPrime = true;
        if (number != 2 && number != 3) {
            if (number % 2 == 0 || number % 3 == 0) {
                isPrime = false;
            } else {
                for (int i = 5; i <= Math.sqrt(number); i += 6) {
                    if (number % i == 0 || number % (i + 2) == 0) {
                        isPrime = false;
                        break;
                    }
                }
            }
        }
        return isPrime;
    }

    public static boolean isPrime(int number) {
        int sumOfDigits = sumOfDigits(number);
        return checkPrime(sumOfDigits);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int number;
        int coffeeRequests = 0;

        while (scanner.hasNextInt()) {
            number = scanner.nextInt();
            if (number == 42) {
                break;
            }
            if (isPrime(number)) {
                ++coffeeRequests;
            }
        }

        System.out.println("Count of coffee-request - " + coffeeRequests);
        scanner.close();
    }
}
