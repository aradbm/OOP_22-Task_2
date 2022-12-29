package Ex2_2;

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
    private int MAX_THREADS = Runtime.getRuntime().availableProcessors() - 1;
    private int CurrentMax;
    private BlockingQueue runQueue = new LinkedBlockingQueue<Task>();

    /**
     * Empty constructor
     */
    public CustomExecutor() {

    }

    public CustomExecutor(int maxOfThreads) {
        this.CurrentMax = -1;
    }

    public void submit(Task taskToAdd, TaskType taskType) {
        // Runnable runnableTask = (Runnable) (taskToAdd);
        // runQueue.add(runnableTask);
        switch (taskType.getType()) {
            case COMPUTATIONAL:
                System.out.println();
                break;
            case IO:
                System.out.println();
                break;
            case OTHER:
                System.out.println();
                break;
            default: // throw exeption
                break;
        }
    }

    public int getCurrentMax() {
        return this.CurrentMax;
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
