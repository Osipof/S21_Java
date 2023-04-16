package day02.ex00;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Map;

public class SignaturesChecker {
    public static void analise(Map<String, String> signatures, String input) {
        try (FileInputStream inputStream = new FileInputStream(input)) {
            byte[] bytes = new byte[8];
            int resultReading = inputStream.read(bytes);
            if (resultReading != -1) {
                String result = bytesToHex(bytes);
                writeSignature(signatures, result);
            }
        } catch (IOException e) {
            System.out.println("File not found");
        }
    }

    private static String bytesToHex(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        for (byte item : bytes) {
            sb.append(String.format("%02X", item)).append(" ");
        }
        return sb.toString().trim();
    }

    private static void writeSignature(Map<String, String> signatures, String result) {
        try (FileOutputStream outputStream = new FileOutputStream("./src/main/java/day02/ex00/result.txt", true)) {
            for (Map.Entry<String, String> item : signatures.entrySet()) {
                if (result.contains(item.getValue())) {
                    outputStream.write(item.getKey().getBytes());
                    outputStream.write('\n');
                    System.out.println("PROCESSED");
                    return;
                }
            }
            System.out.println("UNDEFINED");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
