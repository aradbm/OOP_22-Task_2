package test;

import org.junit.jupiter.api.Test;
import java.util.concurrent.Callable;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.junit.platform.commons.logging.Logger;
import org.junit.platform.commons.logging.LoggerFactory;
import java.util.concurrent.*;


import Ex2_2.*;


class Part2Test {

/* ********************************* Task Tests ********************************* */

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
     // Creating task with  Callable object expression.
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

    /* ********************************* CustomExecutor Tests ********************************* */
    @Test
    void Test() {
        CustomExecutor customExecutor = new CustomExecutor();
        Task task1 = Task.createTask(() -> {
            int sum = 0;
            for (int i = 1; i <= 10; i++) {
                sum += i;
            }
            return sum;
        }, TaskType.COMPUTATIONAL);
        customExecutor.submit(task1);
        System.out.println(customExecutor.shutdownNow());
        System.out.println(customExecutor.isShutdown());

    }
}