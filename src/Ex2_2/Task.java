package Ex2_2;

import java.util.concurrent.Callable;

public class Task<T> implements Callable<T> {
    private TaskType tType;
    private Callable<?> methodToExecute;

    void createTask(T aT, TaskType aTaskType) {
        this.methodToExecute = (Callable<?>) (aT);
        this.tType = aTaskType;
    }

    // public Task<T> createTask(taskInterface<T> methodToExecute, TaskType tType) {
    // this.methodToExecute = methodToExecute;
    // this.tType = tType;
    // return this;

    /**
     * @return The task priority number.
     */
    public int getPriority() {
        return this.tType.getPriorityValue();
    }

    @Override
    public T call() throws Exception {
        return (T) this.methodToExecute.call();
    }

}