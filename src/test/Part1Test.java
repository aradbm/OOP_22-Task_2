package test;

import Ex2_1.Ex2_1;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.Duration;
import java.time.Instant;
import org.junit.jupiter.api.Test;

class Part1Test {
    /*@Test
    void asd() {
        CustomExecutor sd = new CustomExecutor();

        var task = createTask(() -> {
            int sum = 0;
            for (int i = 1; i <= 10; i++) {
                sum += i;
            }
            return sum;
        }, TaskType.COMPUTATIONAL);

        var sumTask = sd.submit(task);*/

        // TaskType a = TaskType.COMPUTATIONAL;
        // Callable<Double> callable1 = () -> {
        // return 1000 * Math.pow(1.02, 5);
        // };
        // // Task<Object> task2 = new Task<Object>(callable1, a);

        // Callable<String> callable2 = () -> {
        // StringBuilder sb = new StringBuilder("ABCDEFGHIJKLMNOPQRSTUVWXYZ");
        // return sb.reverse().toString();
        // };

        // Future<Double> priceTask = sd.submit(() -> {
        // return 1000 * Math.pow(1.02, 5);
        // }, TaskType.COMPUTATIONAL);

        // var sasd = sd.submit(callable1, a);
        // Future<String> reverseTask = sd.submit(callable2, TaskType.IO);

        //Double totalPrice;
        //String reversed;
        // totalPrice = priceTask.get();
        // reversed = reverseTask.get();

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
        System.out.println("(Time): Without Threads: " + Duration.between(start, end) + "\n"); // Calculating execution
                                                                                               // time for first option.
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
        System.out.println("(Time): With Threads: " + Duration.between(start, end) + "\n"); // Calculating execution
                                                                                            // time for second option.
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
        System.out.println("(Time): With ThreadPool: " + Duration.between(start, end) + "\n"); // Calculating execution
                                                                                               // time for third option.
        Ex2_1.deleteFiles(fileNames); // Delete files.
    }
}