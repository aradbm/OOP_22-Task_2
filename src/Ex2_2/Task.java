package Ex2_2;

import java.util.concurrent.Callable;

public class Task implements Callable<Object>, Comparable<Task> {
    private final TaskType tType;
    private final Callable<?> methodToExecute;

    /********************************* Constructor ********************************* /
     /**
     * Constructor.
     * @param methodToExecute - the method we want the task to execute.
     * @param tType - the task's type.
     */
    private Task(Callable<?> methodToExecute, TaskType tType) {
        this.tType = tType;
        this.methodToExecute = methodToExecute;
    }

    /********************************* Factory Methods ********************************* /
    /**
     * Factory method that creates a new Task.
     * @param methodToExecute - the method we want the task to execute.
     * @param tType - the task's type.
     * @return a new Task.
     */
    public static Task createTask(Callable<?> methodToExecute, TaskType tType) {
        return new Task(methodToExecute, tType);
    }

    /**
     * Factory method that creates a new Task.
     * @param methodToExecute - the method we want the task to execute.
     * @return a new Task.
     */
    public Task createTask(Callable<?> methodToExecute) {
        return new Task(methodToExecute, TaskType.OTHER);
    }

    /********************************* Return Tasks' Priority Method ********************************* /
     /**
     * @return the task's priority number.
     */
    public int getPriority() {
        return this.tType.getPriorityValue();
    }

    /********************************* Task Execution  ********************************* /
     /**
     * The tasks' method we want to execute. Will throw an exception if failed.
     * @return whatever "methodToExecute" returns.
     * @throws Exception - If the execution is failed.
     */
    @Override
    public Object call() throws Exception {
            return this.methodToExecute.call();
    }

    /********************************* Comparing Method  ********************************* /
     /**
     * Compares between 2 tasks.
     * @param other - the object to be compared.
     * @return minus the difference between this tasks' priority and "other" task priority.
     * low priority value is preferred. Thus, If the value returned > 0, this task is preferred,
     * if value < 0, "other" task is preferred, and if value = 0, it doesn't matter.
     */
    @Override
    public int compareTo(Task other) {
        return - (this.getPriority() - other.getPriority());
    }
}