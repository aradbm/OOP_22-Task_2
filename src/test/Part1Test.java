package test;

import Ex2_1.Ex2_1;
import Ex2_2.CustomExecutor;
import Ex2_2.Task;
import Ex2_2.TaskType;

import static org.junit.Assert.assertEquals;

import java.time.Duration;
import java.time.Instant;
import java.util.concurrent.Callable;
import java.util.concurrent.Future;

import org.junit.Test;

class Part1Test {
    @Test
    double asd() {
        CustomExecutor sd = new CustomExecutor();

        Task task = Task(() -> {
            int sum = 0;
            for (int i = 1; i <= 10; i++) {
                sum += i;
            }
            return sum;
        }, TaskType.COMPUTATIONAL);

        var sumTask = sd.submit(task);

        TaskType a = TaskType.COMPUTATIONAL;
        Callable<Double> callable1 = () -> {
            return 1000 * Math.pow(1.02, 5);
        };
        Task<Object> task2 = new Task<Object>(callable1, a);

        Callable<String> callable2 = () -> {
            StringBuilder sb = new StringBuilder("ABCDEFGHIJKLMNOPQRSTUVWXYZ");
            return sb.reverse().toString();
        };

        // Future<Double> priceTask = sd.submit(() -> {
        // return 1000 * Math.pow(1.02, 5);
        // }, TaskType.COMPUTATIONAL);

        var sasd = sd.submit(callable1, a);
        Future<String> reverseTask = sd.submit(callable2, TaskType.IO);

        Double totalPrice;
        String reversed;
        // totalPrice = priceTask.get();
        reversed = reverseTask.get();

    }

    private Object Task(Object object, TaskType computational) {
        return null;
    }

    private Object Task(Object object, TaskType computational) {
        return null;
    }

    private Object Task(Object object, TaskType computational) {
        return null;
    }

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
        String[] fileNames = Ex2_1.createTextFiles(3000, 5, 5000);
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
        String[] fileNames = Ex2_1.createTextFiles(3000, 5, 5000);
        Instant start = Instant.now();
        System.out.println("------------------- Number of lines: " + Ex2_1.getNumOfLinesThreads(fileNames)
                + " -------------------");
        Instant end = Instant.now();
        System.out.println("(Time): With Threads: " + Duration.between(start, end) + "\n"); // Calculating execution
                                                                                            // time for second option.
        Ex2_1.deleteFiles(fileNames); // Delete files.
    }

    @Test
    void getNumOfLinesThreadPool() {
        String[] fileNames = Ex2_1.createTextFiles(3000, 5, 5000);
        Instant start = Instant.now();
        System.out.println("------------------- Number of lines: " + Ex2_1.getNumOfLinesThreadPool(fileNames)
                + " -------------------");
        Instant end = Instant.now();
        System.out.println("(Time): With ThreadPool: " + Duration.between(start, end) + "\n"); // Calculating execution
                                                                                               // time for third option.
        Ex2_1.deleteFiles(fileNames); // Delete files.
    }
}