package day02;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class SignaturesReader {

    private static final String SIGNATURES_TXT_PATH = "./src/main/java/day02/signatures.txt";

    public static Map<String, String> read() {
        Map<String, String> signatures = new HashMap<>();
        FileInputStream inputStream = null;
        Scanner scanner = null;
        try {
            inputStream = new FileInputStream(SIGNATURES_TXT_PATH);
            scanner = new Scanner(inputStream);
            while (scanner.hasNextLine()) {
                String format = scanner.next().replace(',', ' ').trim();
                String byteCode = scanner.nextLine().trim();
                signatures.put(format, byteCode);
            }
            scanner.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return signatures;
    }
}
