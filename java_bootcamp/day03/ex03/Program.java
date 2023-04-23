package day03.ex03;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Program {
    private static final String ARG_THREADS_COUNT = "--threadsCount=";
    private static final String SIGNATURES_TXT_PATH = "./files_urls.txt";

    public static void main(String[] args) {
        int numThreads = checkInput(args);

        List<String> urls = readUrls();

        List<DownloadThread> threads = new ArrayList<>(numThreads);

        for (int i = 0; i < numThreads; i++) {
            DownloadThread thread = new DownloadThread(urls, i, numThreads);
            thread.start();
            threads.add(thread);
        }

        for (DownloadThread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private static int checkInput(String[] args) {
        if (args.length != 1 || !args[0].startsWith(ARG_THREADS_COUNT)) {
            System.out.println("Error: You should write one argument like " +
                    "\"--threadsCount=COUNT_OF_THREADS\"");
            System.exit(-1);
        }
        int count = Integer.parseInt(args[0].substring(ARG_THREADS_COUNT.length()));

        if (count < 2) {
            System.out.println("Threads count must be greater than 1");
            System.exit(-1);
        }
        return count;
    }

    public static List<String> readUrls() {
        List<String> urls = new ArrayList<>();
        try (FileInputStream inputStream = new FileInputStream(SIGNATURES_TXT_PATH)) {
            Scanner scanner = new Scanner(inputStream);
            while (scanner.hasNextLine()) {
                String url = scanner.nextLine().trim();
                urls.add(url);
            }
            scanner.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return urls;
    }
}
