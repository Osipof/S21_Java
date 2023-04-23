package day02.ex02;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.util.Scanner;

public class Program {
    private static Path path, nPath;
    private static final String ARG_START = "--current-folder=";

    public static void main(String[] args) {
        checkArgs(args);
        path = Paths.get(args[0].substring(ARG_START.length()));
        checkPath(path);
        System.out.println(path);
        Scanner scanner = new Scanner(System.in);
        while (true) {
            scanInput(scanner);
        }
    }

    private static void scanInput(Scanner scanner) {
        String inputAll = scanner.nextLine().trim();
        String[] input = inputAll.split("\\s+");
        if (input.length == 1 && input[0].equals("exit")) {
            scanner.close();
            System.exit(0);
        } else if (input.length == 1 && input[0].equals("ls")) {
            commandLS();
        } else if (input[0].equals("cd")) {
            if (input.length == 2) {
                commandCD(input[1]);
            } else {
                System.out.println("cd: wrong number of arguments");
            }
        } else if (input[0].equals("mv")) {
            if (input.length == 3) {
                commandMV(input[1], input[2]);
            } else {
                System.out.println("mv: missing file operand");
            }
        } else {
            System.out.println(input[0] + ": command not found");
        }
    }

    private static void commandLS() {
        try (DirectoryStream<Path> files = Files.newDirectoryStream(path)) {
            for (Path temp : files) {
                long size;
                if (Files.isDirectory(temp)) {
                    size = directorySize(temp);
                } else {
                    size = Files.size(temp);
                }
                System.out.println(temp.getFileName() + " " + (size / 1000) + " KB");
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void commandCD(String s) {
        if (isDirectory(s) && Files.exists(nPath)) {
            path = nPath;
            System.out.println(path);
        } else {
            System.out.println("cd: " + s + ": Not a directory");
        }
    }

    private static void commandMV(String what, String where) {
        Path source = null;

        try (DirectoryStream<Path> files = Files.newDirectoryStream(path)) {
            for (Path temp : files) {
                if (temp.getFileName().toString().equals(what) && Files.isRegularFile(temp)) {
                    source = temp;
                    break;
                }
            }

            if (source == null) {
                System.out.println("mv: " + what + " no such file");
                return;
            }

            if (isDirectory(where)) {
                Files.move(source, nPath.resolve(source.getFileName()), StandardCopyOption.REPLACE_EXISTING);
            } else {
                Files.move(source, source.resolveSibling(Paths.get(where)));
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void checkArgs(String[] args) {
        if (args.length != 1 || !args[0].startsWith(ARG_START)) {
            System.out.println("You should write one argument like \"--current-folder=YOUR_FOLDER\"");
            System.exit(-1);
        }
    }

    private static void checkPath(Path path) {
        if (!path.isAbsolute()) {
            System.out.println("Error: Path must be absolute");
            System.exit(-1);
        }
        if (!Files.isDirectory(path)) {
            System.out.println("Error: Path must be a directory");
            System.exit(-1);
        }
    }

    private static boolean isDirectory(String strPath) {
        nPath = Paths.get(strPath);
        nPath = path.resolve(nPath).normalize();

        return Files.isDirectory(nPath);
    }

    private static long directorySize(Path path) throws IOException {
        return Files.walk(path)
                .filter(p -> p.toFile().isFile())
                .mapToLong(p -> p.toFile().length())
                .sum();
    }

}


