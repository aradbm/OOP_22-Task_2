import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.security.Provider.Service;
import java.time.Duration;
import java.time.Instant;
import java.util.List;
import java.util.Random;
import java.util.concurrent.*;

public class Ex2_1 {
    // delete all files in fileNames array
    public static void deleteFiles(String[] fileNames) {
        for (int i = 0; i < fileNames.length; i++) {
            File file = new File(fileNames[i]);
            file.delete();
        }
    }

    /**
     * Creates n text files with random numbers in each file, returns the file names
     * in an array
     * 
     * @param n     - number of files to create
     * @param seed  - seed for random number generator
     * @param bound - upper bound for random number generator
     * @return fileNames
     */
    public static String[] createTextFiles(int n, int seed, int bound) {
        String[] fileNames = new String[n];
        Random rand = new Random(seed);
        for (int i = 0; i < n; i++) {
            try {
                fileNames[i] = "file_" + i + ".txt";
                PrintWriter writer = new PrintWriter(fileNames[i], "UTF-8");
                int numOfRand = rand.nextInt(bound);
                System.out.println(numOfRand);
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
     * Returns the number of lines in the files in fileNames array
     * 
     * @param fileNames - array of file names
     * @return numOfLines - number of lines in all files
     */
    public static int getNumOfLines(String[] fileNames) {
        int numOfLines = 0;
        for (int i = 0; i < fileNames.length; i++) {
            try {
                BufferedReader reader = new BufferedReader(new FileReader(fileNames[i]));
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
     * This method creates a thread for each file to get his number of lines
     * 
     * @param fileNames - array of file names
     * @return total number of lines in all the files
     */
    public static int getNumOfLinesThreads(String[] fileNames) {
        int countLines = 0;
        for (int i = 0; i < fileNames.length; i++) {
            numOfLinesThreads nol = new numOfLinesThreads(fileNames[i]);
            Thread myThread = new Thread(nol);
            myThread.start();
            countLines += nol.getNumOfLines();
        }
        return countLines;
    }

    public static int getNumOfLinesThreadPool(String[] fileNames) {
        int countLines = 0;
        ExecutorService threadPool1 = new ThreadPoolExecutor(3, 5,
                0, TimeUnit.SECONDS, new ArrayBlockingQueue<Runnable>(fileNames.length));

        for (int i = 0; i < fileNames.length; i++) {
            Future<Integer> task = threadPool1.submit(new numOfLinesThreadPool(fileNames[i]));
            // threadPool1.submit();
            try {
                countLines += task.get();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }
        threadPool1.shutdown();
        return countLines;
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