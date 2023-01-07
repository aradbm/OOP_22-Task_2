package Ex2_2;

import java.util.Collection;
import java.util.Collections;
import java.util.concurrent.*;

import static java.util.Collections.reverseOrder;

public class CustomExecutor extends ThreadPoolExecutor {
    private static final int MAX_THREADS = Runtime.getRuntime().availableProcessors() - 1;
    private static final int MIN_THREADS = Runtime.getRuntime().availableProcessors() / 2;
    private int maxPriority;
    public static CustomQueue<Runnable> workingBlockingQueue= new CustomQueue<>(MIN_THREADS,Collections.reverseOrder(),10); // 10 is lowest

    /* ********************************* Constructor ********************************* */
    /**
     * Constructor.
     */
    public CustomExecutor() {
        // myThreads = new ArrayList<>();
        super(MIN_THREADS, MAX_THREADS, 300,
                TimeUnit.MILLISECONDS, workingBlockingQueue);
        maxPriority = 10;
    }

    /* ********************************* Submit Methods ********************************* */
    public Future<?> submit(Task taskToSubmit) {
         if (taskToSubmit == null) throw new NullPointerException();
//         if (taskToSubmit.getPriority() < maxPriority) {
//             maxPriority = taskToSubmit.getPriority();
//         }
         workingBlockingQueue.updatePriority(taskToSubmit.getPriority());
         RunnableFuture<?> futureObj = newTaskFor(taskToSubmit);
         addToQueue(taskToSubmit);
         execute(futureObj);
         return futureObj;
     }

    public Future<?> submit(Callable<?> TaskMethod, TaskType tType) {
        return submit(Task.createTask(TaskMethod, tType));
    }

    /* public Future<?> submit(Callable<?> TaskMethod) {
        return submit(Task.createTask(TaskMethod));
    }*/

    /* ********************************* Return Max Priority ********************************* */
    /**
     * @return the max priority (which is the lowest value of the tasks' priority in the pool).
     */
    public int getCurrentMax() {
        this.maxPriority = workingBlockingQueue.getMaxPriority();
        return this.maxPriority;
    }

    private void addToQueue(Callable<?> taskToSubmit) {
        super.submit(taskToSubmit);
    }
    public void gracefullyTerminate(){
        super.shutdown();
    }
}
