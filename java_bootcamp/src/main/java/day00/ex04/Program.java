package day00.ex04;

import java.util.Scanner;

public class Program {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String inputLine = scanner.nextLine();
        int[] letters = new int[65535];
        char[] dataArray = inputLine.toCharArray();
        for (int i = 0; i < inputLine.length(); i++) {
            letters[dataArray[i]]++;
        }
        char[] resultList = new char[10];
        int[] countCharList = new int[10];
        char maxChar = ' ';
        int maxCount = 0;
        int index = 0;
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 65535; j++) {
                if (letters[j] > maxCount) {
                    maxCount = letters[j];
                    maxChar = (char) j;
                    index = j;
                }
            }
            countCharList[i] = letters[index];
            resultList[i] = maxChar;
            letters[index] = 0;
            maxChar = ' ';
            maxCount = 0;
        }
        if (countCharList[0] > 999) {
            System.err.println("Illegal Argument!");
            System.exit(-1);
        }
        printTable(resultList, countCharList);
        scanner.close();
    }

    public static void printTable(char[] chars, int[] counts) {
        int max = counts[0];
        System.out.println();
        for (int i = 0; i < 10; i++) {
            if (counts[i] == max)
                System.out.print(max + "\t");
        }
        System.out.println();
        for (int i = 10; i > 0; i--) {
            for (int j = 0; j < 10; j++) {
                if (counts[j] * 10 / max >= i)
                    System.out.print("#\t");
                if (counts[j] * 10 / max == i - 1) {
                    if (counts[j] != 0)
                        System.out.print(counts[j] + "\t");
                }
            }
            System.out.println();
        }
        for (int i = 0; i < 10; i++) {
            System.out.print(chars[i] + "\t");
        }
    }
}
