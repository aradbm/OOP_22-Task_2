package Ex2_2;

import java.util.Collection;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class CustomExecutor implements ExecutorService {
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
    // ArrayList<Thread> myThreads;
    private BlockingQueue<Runnable> workingBlockingQueue = new LinkedBlockingQueue<>();
    private ExecutorService pool;

    public CustomExecutor() {
        // myThreads = new ArrayList<>();
        maxPriority = 0;
        pool = new ThreadPoolExecutor(MIN_THREADS, MAX_THREADS, 300,
                TimeUnit.MILLISECONDS, workingBlockingQueue);
    }

    // public Future<Object> submit(Task<Object> taskToSubmit) {
    // if (taskToSubmit.getPriority() > maxPriority) {
    // maxPriority = taskToSubmit.getPriority();
    // }
    // addToQueue(taskToSubmit);
    // return null;
    // }

    public int getMaxPriority() {
        return this.maxPriority;
    }

    public <T> void addToQueue(Task taskToSubmit) {
        workingBlockingQueue.add((Runnable) taskToSubmit);
    }

    @Override
    public void execute(Runnable command) {
        // TODO Auto-generated method stub

    }

    @Override
    public void shutdown() {
        // TODO Auto-generated method stub

    }

    @Override
    public List<Runnable> shutdownNow() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public boolean isShutdown() {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean isTerminated() {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean awaitTermination(long timeout, TimeUnit unit) throws InterruptedException {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public <T> Future<T> submit(Callable<T> task) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public <T> Future<T> submit(Runnable task, T result) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Future<?> submit(Runnable task) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public <T> List<Future<T>> invokeAll(Collection<? extends Callable<T>> tasks) throws InterruptedException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public <T> List<Future<T>> invokeAll(Collection<? extends Callable<T>> tasks, long timeout, TimeUnit unit)
            throws InterruptedException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public <T> T invokeAny(Collection<? extends Callable<T>> tasks) throws InterruptedException, ExecutionException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public <T> T invokeAny(Collection<? extends Callable<T>> tasks, long timeout, TimeUnit unit)
            throws InterruptedException, ExecutionException, TimeoutException {
        // TODO Auto-generated method stub
        return null;
    }

    public Object submit(Object task) {
        return null;
    }

    // public gracefullyTerminate(){

    // }
}
