package Ex2_2;

import java.util.Objects;
import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

public class Task<T> extends FutureTask<T> implements Callable<Object>, Comparable<Task<T>> {
    private final TaskType taskType;
    private final Callable<?> methodToExecute;


    /* ********************************* Constructor ********************************* */
    /**
     * Constructor.
     * @param methodToExecute - the method we want the task to execute.
     * @param tType           - the task's type.
     */
    private  Task(Callable<T> methodToExecute, TaskType tType) {
        super(methodToExecute);
        this.taskType = tType;
        this.methodToExecute = methodToExecute;
    }

    /* ******************************** Factory Methods ********************************* */
     /**
     * Factory method that creates a new Task.
     * @param methodToExecute - the method we want the task to execute.
     * @param tType           - the task's type.
     * @return a new Task.
     */
    public static <T> Task<T> createTask(Callable<T> methodToExecute, TaskType tType) {
        return new Task<>(methodToExecute, tType);
    }

    /**
     * Factory method that creates a new Task.
     * @param methodToExecute - the method we want the task to execute.
     * @return a new Task.
     */
    public static <T> Task<T> createTask(Callable<T> methodToExecute) {
        return new Task<>(methodToExecute, TaskType.OTHER);
    }

    /* ******************************** Return Tasks' Priority ********************************* */
     /**
     * @return the task's priority.
     */
    public int getPriority() {
        return this.taskType.getPriorityValue();
    }

     /* ******************************** Task Execution ********************************* */
     /**
     * The tasks' method we want to execute. Will throw an exception if failed.
      * When the task is executed, it is polled from the queue and thus, we decrease his priority from the array.
     * @return whatever "methodToExecute" returns.
     * @throws Exception - If the execution is failed.
     */
    @Override
    public Object call() throws Exception {
        return this.methodToExecute.call();
    }

    /* ******************************* Return Tasks' "taskType" ********************************* */
    /**
     * @return type - the tasks' type.
     */
    public TaskType getTaskType() {
        return taskType;
    }

     /* ******************************* Comparing Method ********************************* */
     /**
     * Compares between 2 tasks.
     * @param other - the object to be compared.
     * @return 1 if the difference between "other" task priority and this tasks' priority > 0, and -1 otherwise.
     *         The low priority value is preferred. Thus, If the difference value > 0, this task is preferred,
     *         If value < 0, "other" task is preferred, and if value = 0, it doesn't matter.
     */
    @Override
    public int compareTo(Task other) {
        return (other.getPriority() - this.getPriority()) > 0 ? 1 : -1;
    }

    /* ******************************* Equals Method ********************************* */
    /**
     * @param o - object to check if equals to this task.
     * @return true if tasks are equal, false otherwise.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Task<?> task = (Task<?>) o;
        return taskType == task.taskType && Objects.equals(methodToExecute, task.methodToExecute);
    }

    /* ******************************* Hash Code Method ********************************* */
    /**
     * @return hash value of the objects.
     */
    @Override
    public int hashCode() {
        return Objects.hash(taskType, methodToExecute);
    }

    /**
     * @return string that represents this CustomExecutor.
     */
    @Override
    public String toString() {
        return "Task Priority: " + getPriority();
    }
}