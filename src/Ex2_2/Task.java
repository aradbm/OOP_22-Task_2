package Ex2_2;

import java.util.concurrent.Callable;

public class Task implements taskInterface {
    private final TaskType tType;
    private final Callable<Object> methodToExecute;

    public Task(Callable<Object> methodToExecute, TaskType tType) {
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
    public void call() {
        return;
    }

}