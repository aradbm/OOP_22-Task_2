package Ex2_1;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
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
    public static int getNumOfLinesThreads(String[] fileNames) throws InterruptedException {
        int numOfLines = 0;
        numOfLinesThreads[] threads = new numOfLinesThreads[fileNames.length];
        for (int i = 0; i < fileNames.length; i++) {
            threads[i] = new numOfLinesThreads(fileNames[i]);
            threads[i].start();
        }
        for (numOfLinesThreads thread : threads) {
            thread.join();
        }
        for (numOfLinesThreads thread : threads) {
            numOfLines += thread.getNumOfLines();
        }
        return numOfLines;
    }

    /**
     * Computes the total number of lines in files using thread-pool.
     * 
     * @param fileNames   - array of files' names
     * @return numOfLines - total number of lines in all the files
     */
    public static int getNumOfLinesThreadPool(String[] fileNames) {
        int numOfLines = 0;
        int MAX_THREADS = fileNames.length;
        Future<?>[] results = new Future<?>[MAX_THREADS];
        ExecutorService threadPool = Executors.newFixedThreadPool(MAX_THREADS);
        for (int i = 0; i < MAX_THREADS; i++) {
            results[i] = threadPool.submit(new numOfLinesThreadPool(fileNames[i]));
        }
        for (int i = 0; i < MAX_THREADS; i++) {
            try {
                numOfLines += (int) results[i].get();
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        }
        threadPool.shutdown();
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
            if(!file.delete()) {
                System.out.println("Deleting  " + fileName +" failed!");
            }
        }
    }
}