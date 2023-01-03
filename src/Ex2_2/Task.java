package Ex2_2;

import java.util.concurrent.Callable;

public class Task<T> implements Callable<T> {
    private final TaskType tType;
    private final Callable<T> methodToExecute;

    public Task(Callable<T> methodToExecute, TaskType tType) {
        this.methodToExecute = methodToExecute;
        this.tType = tType;
    }

    /**
     * @return The task priority number.
     */
    public int getPriority() {
        return this.tType.getPriorityValue();
    }

    @Override
    public T call() throws Exception {
        return this.methodToExecute.call();
    }

}