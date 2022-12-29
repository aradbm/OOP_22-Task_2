package Ex2_1;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.Duration;
import java.time.Instant;
import java.util.Random;
import java.util.concurrent.*;

public class Ex2_1 {
    /**
     * Creates n text files with random numbers of rows in each file,
     * and returns the files' names in a String array.
     * 
     * @param n     - number of files to create.
     * @param seed  - seed for random number generator.
     * @param bound - upper bound for random number generator.
     * @return fileNames
     */
    public static String[] createTextFiles(int n, int seed, int bound) {
        String[] fileNames = new String[n];
        Random rand = new Random(seed);
        for (int i = 0; i < n; i++) {
            try {
                fileNames[i] = "file_" + i + ".txt";
                PrintWriter writer = new PrintWriter(fileNames[i]);
                int numOfRand = rand.nextInt(bound);
                System.out.println("Rows in " + fileNames[i] + ": "+numOfRand);
                for (int j = 0; j < numOfRand; j++) {
                    writer.println("hello world!");
                }
                writer.close();
            } catch (IOException e) {
                System.out.println("Error: " + e);
            }
        }
        return fileNames;
    }

    /**
     * Returns the number of lines in the files in "fileNames" array.
     * 
     * @param fileNames   - array of files' names.
     * @return numOfLines - number of lines in all files.
     */
    public static int getNumOfLines(String[] fileNames) {
        int numOfLines = 0;
        for (String fileName : fileNames) {
            try {
                BufferedReader reader = new BufferedReader(new FileReader(fileName));
                while (reader.readLine() != null) {
                    numOfLines++;
                }
                reader.close();
            } catch (IOException e) {
                System.out.println("Error: " + e);
            }
        }
        return numOfLines;
    }

    /**
     * Creates a thread for each file that calculates his number of lines.
     * 
     * @param fileNames   - array of files' names.
     * @return numOfLines - total number of lines in all the files.
     */
    public static int getNumOfLinesThreads(String[] fileNames) {
        int numOfLines = 0;
        for (String fileName : fileNames) {
            numOfLinesThreads nol = new numOfLinesThreads(fileName);
            Thread myThread = new Thread(nol);
            myThread.start();
            numOfLines += nol.getNumOfLines();
        }
        return numOfLines;
    }

    /**
     * Computes the total number of lines in files using thread-pool.
     * 
     * @param fileNames   - array of file names
     * @return numOfLines - total number of lines in all the files
     */
    public static int getNumOfLinesThreadPool(String[] fileNames) {
        int numOfLines = 0;
        int MAX_THREADS = fileNames.length;
        ExecutorService threadPool = Executors.newFixedThreadPool(MAX_THREADS); {
            for (String fileName : fileNames) {
                Future<Integer> result = threadPool.submit(new numOfLinesThreadPool(fileName));
                try {
                    numOfLines += result.get();
                } catch (InterruptedException | ExecutionException e) {
                    e.printStackTrace();
                }
            }
            threadPool.shutdown();
        }
        return numOfLines;
    }

    /**
     * Deletes each file in "fileNames" array.
     *
     * @param fileNames - array of files' names.
     */
    public static void deleteFiles(String[] fileNames) {
        for (String fileName : fileNames) {
            File file = new File(fileName);
            file.delete();
        }
    }

    public static void main(String[] args) throws IOException {
        String[] fileNames = createTextFiles(5, 2, 100);
        for (int i = 0; i < fileNames.length; i++) {
            System.out.println("Created:  " + fileNames[i]);
        }
        // Calculating execution time for first option:
        Instant start = Instant.now();
        System.out.println("Number of lines: " + getNumOfLines(fileNames));
        Instant end = Instant.now();
        System.out.println(Duration.between(start, end));

        // Calculating execution time for second option:
        start = Instant.now();
        System.out.println("Number of lines: " + getNumOfLinesThreads(fileNames));
        end = Instant.now();
        System.out.println(Duration.between(start, end));

        // Calculating execution time for third option:
        start = Instant.now();
        System.out.println("Number of lines: " + getNumOfLinesThreadPool(fileNames));
        end = Instant.now();
        System.out.println(Duration.between(start, end));

        deleteFiles(fileNames);
    }

}