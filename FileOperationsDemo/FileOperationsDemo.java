import java.io.*;
import java.util.Scanner;

/**
 * FileOperationsDemo
 * -------------------
 * This program demonstrates basic file operations in Java:
 * 1. Writing data to a text file
 * 2. Reading data from a text file
 * 3. Modifying (appending) data in the same file
 *
 * Author : Your Name
 * Date   : YYYY-MM-DD
 */

public class FileOperationsDemo {

    // File name to be used for operations
    private static final String FILE_NAME = "sample.txt";

    public static void main(String[] args) {
        writeToFile();
        readFromFile();
        modifyFile();
        readFromFile();   // Read again to show modification
    }

    /**
     * Writes initial content to the file.
     * If the file does not exist, it will be created.
     */
    public static void writeToFile() {
        try (FileWriter writer = new FileWriter(FILE_NAME)) {
            writer.write("Hello, this is the first line in the file.\n");
            writer.write("Java file handling is simple and powerful.\n");
            System.out.println("✔ File written successfully.");
        } catch (IOException e) {
            System.out.println("❌ Error writing to file: " + e.getMessage());
        }
    }

    /**
     * Reads and displays content from the file.
     */
    public static void readFromFile() {
        System.out.println("\n📖 Reading file content:");
        try (Scanner scanner = new Scanner(new File(FILE_NAME))) {
            while (scanner.hasNextLine()) {
                System.out.println(scanner.nextLine());
            }
        } catch (FileNotFoundException e) {
            System.out.println("❌ File not found: " + e.getMessage());
        }
    }

    /**
     * Modifies the file by appending new content.
     */
    public static void modifyFile() {
        try (FileWriter writer = new FileWriter(FILE_NAME, true)) { // true = append mode
            writer.write("This line was added later to modify the file.\n");
            System.out.println("\n✔ File modified successfully.");
        } catch (IOException e) {
            System.out.println("❌ Error modifying file: " + e.getMessage());
        }
    }
}