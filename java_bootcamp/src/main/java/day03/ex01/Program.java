package day03.ex01;

public class Program {
    private static final String ARG_START = "--count=";
    public static boolean isEggPrinted = false;
    public static synchronized void sayEgg() {
        if (isEggPrinted) {
            try {
                Program.class.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("Egg");
        isEggPrinted = true;

        Program.class.notify();
    }
    public static synchronized void sayHen() {
        if (!isEggPrinted) {
            try {
                Program.class.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("Hen");
        isEggPrinted = false;

        Program.class.notify();
    }

    public static void main(String[] args) {
        if (args.length != 1 || !args[0].startsWith(ARG_START)) {
            System.out.println("Error: You should write one argument like \"--count=COUNT_OF_REPEAT\"");
            System.exit(-1);
        }
        int count = Integer.parseInt(args[0].substring(ARG_START.length()));

        if (count <= 0) {
            System.out.println("Error: Incorrect input: " + count);
            System.exit(-1);
        }

        Egg egg = new Egg(count);
        Hen hen = new Hen(count);

        egg.start();
        hen.start();
    }
}
