import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Program {
    private static int[] firstVector;
    private static int[] secondVector;
    private static final List<String> dictionary = new ArrayList<>();

    public static void main(String[] args) {
        checkArgs(args);
        readFiles(args);
        getSimilarity();
    }

    private static void checkArgs(String[] args) {
        if (args.length != 2) {
            System.out.println("You should write two files as command-line arguments");
            System.exit(-1);
        }
    }

    private static void getSimilarity() {
        double numerator = 0;
        double firstSqrt = 0;
        double secondSqrt = 0;
        for (int i = 0; i < firstVector.length; i++) {
            firstSqrt += firstVector[i] * firstVector[i];
            secondSqrt += secondVector[i] * secondVector[i];
            numerator += firstVector[i] * secondVector[i];
        }
        double similarity = 0;
        if (firstSqrt > 0 && secondSqrt > 0) {
            similarity = numerator / (Math.sqrt(firstSqrt) * Math.sqrt(secondSqrt));
        }
        System.out.println("Similarity = " + Math.floor(similarity * 100.00) / 100.00);
    }

    private static void readFiles(String[] args) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("dictionary.txt"))) {

            BufferedReader firstReader = new BufferedReader(new FileReader(args[0]));
            BufferedReader secondReader = new BufferedReader(new FileReader(args[1]));

            fillDictionary(firstReader);
            fillDictionary(secondReader);

            firstVector = new int[dictionary.size()];
            secondVector = new int[dictionary.size()];

            firstReader = new BufferedReader(new FileReader(args[0]));
            secondReader = new BufferedReader(new FileReader(args[1]));

            fillVector(firstReader, firstVector);
            fillVector(secondReader, secondVector);

            for (String word : dictionary) {
                writer.write(word);
                writer.newLine();
            }

        } catch (IOException e) {
            System.out.println("File not found exception");
        }
    }

    private static void fillDictionary(BufferedReader reader) throws IOException{
        String str;

        while ((str = reader.readLine()) != null) {
            String[] words = str.replaceAll("\\p{Punct}", "")
                    .toLowerCase().split("\\s+");

            for (String word : words) {
                if (!dictionary.contains(word) && !word.isEmpty()) {
                    dictionary.add(word);
                }
            }
        }
        reader.close();
    }

    private static void fillVector(BufferedReader reader, int[] vector) throws IOException{
        String str;
        int index;
        while ((str = reader.readLine()) != null) {
            String[] words = str.replaceAll("\\p{Punct}", "")
                    .toLowerCase().split("\\s+");

            for (String word : words) {
                if (!word.isEmpty()) {
                    index = dictionary.indexOf(word);
                    vector[index]++;
                }
            }
        }
        reader.close();
    }
}
