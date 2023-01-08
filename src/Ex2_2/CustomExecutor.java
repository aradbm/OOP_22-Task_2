package Ex2_2;

import java.util.Arrays;
import java.util.Comparator;
import java.util.concurrent.*;

public class CustomExecutor extends ThreadPoolExecutor {
    private static final int MAX_THREADS = Runtime.getRuntime().availableProcessors() - 1;
    private static final int MIN_THREADS = Runtime.getRuntime().availableProcessors() / 2;
    //private int maxPriority;
    static volatile int[] maxPriority;

    public static BlockingQueue<Runnable> workingBlockingQueue = new PriorityBlockingQueue<>(MIN_THREADS,
            Comparator.comparing(o -> ((Task) o)));

     /* ********************************* Constructor ********************************* */
    /**
     * Constructor.
     */
    public CustomExecutor() {
        super(MIN_THREADS, MAX_THREADS, 300,
                TimeUnit.MILLISECONDS, workingBlockingQueue);
        //maxPriority = 10;
        maxPriority = new int[11];
    }

    /* ********************************* Submit Methods ********************************* */

    public <T> Future<T> submit(Task taskToSubmit) {
        maxPriority[taskToSubmit.getPriority()]++;
        RunnableFuture<T> s = (RunnableFuture<T>) super.submit(taskToSubmit);
        System.out.println("is empty:" + workingBlockingQueue.isEmpty() + ", priority:" + getCurrentMax() + " arr" + Arrays.toString(maxPriority));
        return s;
    }

    public <T> Future<T> submit(Callable<T> TaskMethod, TaskType tType) {
        return submit(Task.createTask(TaskMethod, tType));
    }

    @Override
    public <T> Future<T> submit(Callable<T> TaskMethod) {
        return submit(Task.createTask(TaskMethod));
    }
    /* ********************************* Return Max Priority ********************************* */
    /**
     * @return the max priority (which is the lowest value of the tasks' priority in the pool).
     */
    public int getCurrentMax() {
        for (int i = 1; i < maxPriority.length; i++) {
            if (maxPriority[i]!=0)
                return i;
        }
            return 10;
    }
    /* ********************************* Terminating ThreadPool ********************************* */
    /**
     * Performing a shutdown.
     */
    public void gracefullyTerminate() {
        super.shutdown();
    }
}
