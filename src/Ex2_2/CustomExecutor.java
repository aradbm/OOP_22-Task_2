package Ex2_2;

import java.util.ArrayList;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.LinkedBlockingQueue;

public class CustomExecutor implements Callable {
    /*
     * MAX_THREADS calculation: (stack overflow)
     * The max number of threads for a JVM is generally somewhere in the thousands.
     * If you're using multiple threads to optimize a computational algorithm you
     * don't really want more than the number of processors in the system its run
     * on. Use Runtime.getRuntime().availableProcessors() to find out.
     */
    private final int MAX_THREADS = Runtime.getRuntime().availableProcessors() - 1;
    private final int MIN_THREADS = Runtime.getRuntime().availableProcessors() / 2;
    private int maxPriority;
    ArrayList<Thread> myThreads;
    private final BlockingQueue <Task> runQueue = new LinkedBlockingQueue<>();

    public CustomExecutor() {
        myThreads = new ArrayList<>();
        maxPriority = 0;
    }

    public void submit (Task taskToSubmit) {
        if (taskToSubmit.getPriority() > maxPriority) {
            maxPriority = taskToSubmit.getPriority();
        }
        addToQueue(taskToSubmit);
    }
    public int getMaxPriority() {
        return this.maxPriority;
    }

    public void addToQueue (Task taskToSubmit) {
        runQueue.add(taskToSubmit);
    }

    private Runnable take() throws InterruptedException {
        return (Runnable) runQueue.take();
    }

    // public gracefullyTerminate(){

    // }

    @Override
    public Object call() throws Exception {
        return null;
    }
}
