package day00.ex03;

import java.util.Scanner;

public class Program {

    public static long getGrades(long grades, int week, Scanner scanner) {
        int minGrade = 9;
        int grade;
        for (int i = 0; i < 5; ++i) {
            if (!scanner.hasNextInt()) {
                System.err.println("Illegal Argument!");
                System.exit(-1);
            }
            grade = scanner.nextInt();
            if (grade > 9 || grade < 1) {
                System.err.println("Illegal Argument!");
                System.exit(-1);
            }
            if (grade < minGrade) {
                minGrade = grade;
            }
        }

        int pow = 1;
        for (int j = 1; j < week; ++j) {
            pow *= 10;
        }

        grades += (long) minGrade * pow;
        return grades;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.next();
        int week = 1;
        long grades = 0;

        while (!input.equals("42") && week != 18) {
            if (!input.equals("Week") || scanner.nextInt() != week) {
                System.err.println("Illegal Argument!");
                System.exit(-1);
            }

            grades = getGrades(grades, week, scanner);
            input = scanner.next();
            ++week;
        }

        for (int i = 1; i < week; ++i) {
            System.out.print("Week " + i + " ");
            long grade = grades % 10;
            grades /= 10;
            for (int j = 0; j < grade; ++j) {
                System.out.print("=");
            }
            System.out.println(">");
        }
        scanner.close();
    }
}
