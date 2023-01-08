package test;

import org.junit.jupiter.api.Test;
import java.util.concurrent.Callable;
import static org.junit.jupiter.api.Assertions.*;
//import static sun.awt.FontConfiguration.logger;
//import static sun.security.ssl.SSLLogger.logger;

import org.junit.jupiter.api.Test;
import org.junit.platform.commons.logging.Logger;
import org.junit.platform.commons.logging.LoggerFactory;
import java.util.concurrent.*;

import Ex2_2.*;

class Part2Test {

    /*
     * ********************************* Task Tests
     * *********************************
     */
    public static final Logger logger = LoggerFactory.getLogger(Part2Test.class);

    @Test
    void createTask() throws Exception {
        // Creating task with lambda expression.
        Task task1 = Task.createTask(() -> {
            int sum = 0;
            for (int i = 1; i <= 10; i++) {
                sum += i;
            }
            return sum;
        }, TaskType.COMPUTATIONAL);

        assertEquals(55, task1.call());

        Callable<Boolean> f = () -> true;
        // Creating task with Callable object expression.
        Task task2 = Task.createTask(f, TaskType.IO);

        assertEquals(true, task2.call());
    }

    @Test
    void testCreateTask() {
    }

    @Test
    void getPriority() {
    }

    @Test
    void call() {
    }

    @Test
    void compareTo() {
    }

    /*
     * ********************************* CustomExecutor Tests
     * *********************************
     */
    @Test
    void Test() {
        CustomExecutor customExecutor = new CustomExecutor();
        var task = Task.createTask(() -> {
            int sum = 0;
            for (int i = 1; i <= 10; i++) {
                sum += i;
            }
            return sum;
        }, TaskType.COMPUTATIONAL);
        Future<?> sumTask = customExecutor.submit(task);
        logger.info(() -> "Current maximum priority = " +
                customExecutor.getCurrentMax());
        final int sum;
        try {
            sum = (int) sumTask.get();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        logger.info(() -> "Current maximum priority = " +
                customExecutor.getCurrentMax());
        logger.info(() -> "Sum of 1 through 10 = " + sum);
        Callable<Double> callable1 = () -> {
            return 1000 * Math.pow(1.02, 5);
        };
        Callable<String> callable2 = () -> {
            StringBuilder sb = new StringBuilder("ABCDEFGHIJKLMNOPQRSTUVWXYZ");
            return sb.reverse().toString();
        };

        Future<?> priceTask = customExecutor.submit(() -> {
            return 1000 * Math.pow(1.02, 5);
        }, TaskType.COMPUTATIONAL);
        // Future<?> reverseTask = customExecutor.submit(callable2, TaskType.IO);
        final Double totalPrice;
        // final String reversed;
        try {
            totalPrice = (Double) priceTask.get();
            // reversed = (String) reverseTask.get();
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }
        // logger.info(() -> "Reversed String = " + reversed);
        logger.info(() -> String.valueOf("Total Price = " + totalPrice));
        logger.info(() -> "Current maximum priority = " +
                customExecutor.getCurrentMax());
        customExecutor.gracefullyTerminate();
    }
}