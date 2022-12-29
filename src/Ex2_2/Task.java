package Ex2_2;

import java.util.concurrent.Callable;

public class Task<T> implements Callable {
    private TaskType tType;
    private Callable<T> methodToExecute;
    private T ans;

    public Task(Callable<T> methodToExecute, TaskType tType) {
        this.tType = tType;
        this.methodToExecute = methodToExecute;
    }

    public int createTask(Runnable ts, TaskType tt) {
        
        return -1;
    }

    /**
     * @return this task priority number
     */
    public int getPriority() {
        return this.tType.getPriorityValue();
    }

    @Override
    public T call() throws Exception {

        return null;
    }

}