package test;

import org.junit.jupiter.api.Test;

import java.sql.SQLOutput;
import java.util.concurrent.Callable;

import static java.lang.Thread.currentThread;
import static java.lang.Thread.sleep;
import static org.junit.jupiter.api.Assertions.*;
//import static sun.awt.FontConfiguration.logger;
//import static sun.security.ssl.SSLLogger.logger;

import org.junit.jupiter.api.Test;
import org.junit.platform.commons.logging.Logger;
import org.junit.platform.commons.logging.LoggerFactory;
import java.util.concurrent.*;

import Ex2_2.*;

class Part2Test {

    public static final Logger logger = LoggerFactory.getLogger(Part2Test.class);

    /* ********************************* Task Tests ********************************** */
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

        // Creating callable with method that returns true.
        Callable<Boolean> f = () -> true;
        // Creating task with Callable object expression and with default taskType.
        Task task2 = Task.createTask(f);

        assertEquals(true, task2.call());
        assertEquals(3, task2.getPriority());
    }

    @Test
    void getPriority() {
        // Creating task with priority 1.
        Task task = Task.createTask(() -> {
            int sum = 0;
            for (int i = 1; i <= 10; i++) {
                sum += i;
            }
            return sum;
        }, TaskType.COMPUTATIONAL);

        assertEquals(1, task.getPriority());

        // Changing tasks' priority to 8.
        task.getTaskType().setPriority(8);
        assertEquals(8, task.getPriority());
    }

    @Test
    void call() throws Exception {
        // Creating task1 that returns 55.
        Task task1 = Task.createTask(() -> {
            int sum = 0;
            for (int i = 1; i <= 10; i++) {
                sum += i;
            }
            return sum;
        });

        assertEquals(55,(int) task1.call());

        // Creating task2 that returns null.
        Task task2 = Task.createTask(() -> {
            return null;
        });

        assertNull(task2.call());
    }

    @Test
    void compareTo() {
    }

    /* ********************************* CustomExecutor Tests ********************************** */

    /* ********************************* General Tests ********************************** */
    @Test
    void Test() {
        CustomExecutor customExecutor = new CustomExecutor();
        Task task = Task.createTask(()->{
            int sum = 0;
            for (int i = 1; i <= 10; i++) {
                sum += i; }
            return sum;
        }, TaskType.COMPUTATIONAL);
        Future<Integer> sumTask = customExecutor.submit(task);
        final int sum;
        try {
            sum = sumTask.get(1, TimeUnit.MILLISECONDS);
        } catch (InterruptedException | ExecutionException | TimeoutException e) {
            throw new RuntimeException(e);
        }
        logger.info(()-> "Sum of 1 through 10 = " + sum);
        Callable<Double> callable1 = ()-> { return 1000 * Math.pow(1.02, 5);
        };
        Callable<String> callable2 = ()-> {
            StringBuilder sb = new StringBuilder("ABCDEFGHIJKLMNOPQRSTUVWXYZ");
            return sb.reverse().toString();
        };
        Future<Double> priceTask = customExecutor.submit(()-> { return 1000 * Math.pow(1.02, 5);
        }, TaskType.COMPUTATIONAL);
        Future<String> reverseTask = customExecutor.submit(callable2, TaskType.IO);
        final Double totalPrice;
        final String reversed;
        try {
            totalPrice = priceTask.get();
            reversed = reverseTask.get();
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }
        logger.info(()-> "Reversed String = " + reversed);
        logger.info(()->String.valueOf("Total Price = " + totalPrice));
        logger.info(()-> "Current maximum priority = " + customExecutor.getCurrentMax());
        customExecutor.gracefullyTerminate();
    }
    @Test
    void Test2() throws InterruptedException {
        CustomExecutor customExecutor = new CustomExecutor();
        Future<?>[] results = new Future<?>[10];
        for (int i = 0; i < 10; i++) {
            results[i] = customExecutor.submit(Task.createTask(() -> {
                return Math.pow(10, 30);
            }, TaskType.OTHER));
        }
        logger.info(()-> "Current maximum priority = " + customExecutor.getCurrentMax());
        customExecutor.gracefullyTerminate();

    }

    }