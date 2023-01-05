package Ex2_2;

@FunctionalInterface
public interface taskInterface<T> {
    abstract T createTask(T ytT, TaskType tt);
}