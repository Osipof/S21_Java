package day02;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Map;

public class SignaturesChecker {
    public static void analise(Map<String, String> signatures, String input) {
        System.out.println(input);
        FileInputStream inputStream = null;
        try {
             inputStream = new FileInputStream(input);
             byte[] bytes = new byte[8];
             int resultReading = inputStream.read(bytes);
             if (resultReading == -1) {
                 // error
             } else {
                 System.out.println(bytes);
             }
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
    }
}
