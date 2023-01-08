package test;

import Ex2_1.Ex2_1;
import static org.junit.jupiter.api.Assertions.assertEquals;
import java.time.Duration;
import java.time.Instant;
import org.junit.jupiter.api.Test;

class Part1Test {
    @Test
    void createTextFiles() {
        String[] fileNames = Ex2_1.createTextFiles(4, 5, 10);
        System.out.println(
                "------------------- Number of lines: " + Ex2_1.getNumOfLines(fileNames) + " -------------------");
        int numOfLines = Ex2_1.getNumOfLines(fileNames);
        assertEquals(17, numOfLines);
        Ex2_1.deleteFiles(fileNames); // Delete files.
    }

    @Test
    void getNumOfLines() {
        String[] fileNames = Ex2_1.createTextFiles(3000, 5, 10000);
        Instant start = Instant.now(); // Start timer.
        System.out.println(
                "------------------- Number of lines: " + Ex2_1.getNumOfLines(fileNames) + " -------------------");
        Instant end = Instant.now(); // End times.
        // Calculating execution time for first option.
        System.out.println("(Time): Without Threads: " + Duration.between(start, end) + "\n");
        Ex2_1.deleteFiles(fileNames); // Delete files.
    }

    @Test
    void getNumOfLinesThreads() throws InterruptedException {
        String[] fileNames = Ex2_1.createTextFiles(3000, 5, 10000);
        Ex2_1 obj = new Ex2_1();
        Instant start = Instant.now();
        System.out.println("------------------- Number of lines: " + obj.getNumOfLinesThreads(fileNames)
                + " -------------------");
        Instant end = Instant.now();
        // Calculating execution time for second option.
        System.out.println("(Time): With Threads: " + Duration.between(start, end) + "\n");
        Ex2_1.deleteFiles(fileNames); // Delete files.
    }

    @Test
    void getNumOfLinesThreadPool() {
        String[] fileNames = Ex2_1.createTextFiles(3000, 5, 10000);
        Ex2_1 obj = new Ex2_1();
        Instant start = Instant.now();
        System.out.println("------------------- Number of lines: " + obj.getNumOfLinesThreadPool(fileNames)
                + " -------------------");
        Instant end = Instant.now();
        // Calculating execution time for third option.
        System.out.println("(Time): With ThreadPool: " + Duration.between(start, end) + "\n");
        Ex2_1.deleteFiles(fileNames); // Delete files.
    }
}