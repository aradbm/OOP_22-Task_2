package Ex2_2;

import java.util.Arrays;
import java.util.Collections;
import java.util.concurrent.*;

public class CustomExecutor extends ThreadPoolExecutor {
    private static final int MAX_THREADS = Runtime.getRuntime().availableProcessors() - 1;
    private static final int MIN_THREADS = Runtime.getRuntime().availableProcessors() / 2;
    int[] priorityArray;
    public static BlockingQueue<Runnable> workingBlockingQueue = new PriorityBlockingQueue<>(MIN_THREADS,
            Collections.reverseOrder());

    /* ********************************* Constructor ********************************* */

    /**
     * Constructor.
     */
    public CustomExecutor() {
        super(MIN_THREADS, MAX_THREADS, 300,
                TimeUnit.MILLISECONDS, workingBlockingQueue);
        priorityArray = new int[11];
    }

    /* ********************************* Submit Methods ********************************* */

    /**
     * submit task to the thread-pool.
     *
     * @param taskToSubmit - the task we want to submit.
     * @param <T>          - the type of the returned result.
     * @return a future object that holds the result from the executed method.
     */
    public <T> Future<T> submit(Task<T> taskToSubmit) {
        priorityArray[taskToSubmit.getPriority()]++;
        super.execute(taskToSubmit);
        return taskToSubmit;
        //return (FutureTask<T>) super.submit(taskToSubmit);
    }

    /**
     * submit callable and TaskType to the thread-pool.
     *
     * @param TaskMethod - the method we want to submit and execute.
     * @param tType      - the tasks' type.
     * @param <T>        - the type of the returned result.
     * @return a future object that holds the result from the executed method.
     */
    public <T> Future<T> submit(Callable<T> TaskMethod, TaskType tType) {
        return submit(Task.createTask(TaskMethod, tType));
    }

    /**
     * submit callable to the thread-pool.
     *
     * @param TaskMethod the task to submit
     * @param <T>        - the type of the returned result.
     * @return - the method we want to submit and execute.
     */
    @Override
    public <T> Future<T> submit(Callable<T> TaskMethod) {
        return submit(Task.createTask(TaskMethod));
    }

    /* ********************************* Return Max Priority ********************************* */

    /**
     * @return the max priority (which is the lowest value of the tasks' priority in the pool).
     */
    public int getCurrentMax() {
        for (int i = 1; i < priorityArray.length; i++) {
            if (priorityArray[i] != 0) {
                return i;
            }
        }
        return 10;
    }

    /* ********************************* Terminating ThreadPool ********************************* */

    /**
     * Performing a shutdown.
     */
    public void gracefullyTerminate() {
        super.shutdown();
        try {
            if (!super.awaitTermination(10, TimeUnit.SECONDS)) {
                System.err.println("Tasks didn't finish in 10 seconds!");
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /* ************************* Before Each Execution Update Priority Array ************************* */

    @Override
    protected void beforeExecute(Thread t, Runnable r) {
        if (r instanceof Task<?> task) {
            if (priorityArray[task.getPriority()] > 0)
                priorityArray[task.getPriority()]--;
        }
    }

    /* ******************************* Hash Code Method ********************************* */

    /**
     * @return a hash code value for this object.
     */
    @Override
    public int hashCode() {
        return super.hashCode();
    }

    /* ******************************* Equals Method ********************************* */

    /**
     * @param obj - the reference object with which to compare.
     * @return true if tasks are equal, false otherwise.
     */
    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    /* ********************************* ToString Method ********************************* */

    /**
     * @return string that represents this CustomExecutor.
     */
    @Override
    public String toString() {
        return "Max Priority: " + getCurrentMax() + ", Priority Array: " + Arrays.toString(priorityArray) +
                " ,current Blocking Queue: " + workingBlockingQueue.toString();
    }
}
