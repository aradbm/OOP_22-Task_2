package Ex2_2;

public enum TaskType {
    COMPUTATIONAL(1) {
        @Override
        public String toString() {
            return "Computational Task";
        }
    },
    IO(2) {
        @Override
        public String toString() {
            return "IO-Bound Task";
        }
    },
    OTHER(3) {
        @Override
        public String toString() {
            return "Unknown Task";
        }
    };

    private int typePriority;

    /**
     * Creates a TaskType with a given priority.
     * @param priority - the priority of the task
     */
    private TaskType(int priority) {
        if (validatePriority(priority))
            typePriority = priority;
        else
            throw new IllegalArgumentException("Priority is not an integer");
    }

    /**
     * Sets current TaskType priority.
     * @param priority - the priority to set for this TaskType.
     */
    public void setPriority(int priority) {
        if (validatePriority(priority))
            this.typePriority = priority;
        else
            throw new IllegalArgumentException("Priority is not an integer");
    }

    /**
     * Returns current priority.
     * @return typePriority
     */
    public int getPriorityValue() {
        return typePriority;
    }

    /**
     * Returns current TaskType.
     * @return this TaskType.
     */
    public TaskType getType() {
        return this;
    }

    /**
     * Priority is represented by an integer value, ranging from 1 to 10.
     * @param priority - the priority of this TaskType.
     * @return True/False - whether the priority is valid or not.
     */
    private static boolean validatePriority(int priority) {
        return priority >= 1 && priority <= 10;
    }
}