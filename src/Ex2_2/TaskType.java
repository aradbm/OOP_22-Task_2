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
     * creates a Tasktype with given priority
     * 
     * @param priority of task
     */
    private TaskType(int priority) {
        if (validatePriority(priority))
            typePriority = priority;
        else
            throw new IllegalArgumentException("Priority is not an integer");
    }

    /**
     * sets corent TaskType priority
     * 
     * @param priority
     */
    public void setPriority(int priority) {
        if (validatePriority(priority))
            this.typePriority = priority;
        else
            throw new IllegalArgumentException("Priority is not an integer");
    }

    /**
     * returns corrent priority
     * 
     * @return
     */
    public int getPriorityValue() {
        return typePriority;
    }

    /**
     * return corrent TaskType
     * 
     * @return
     */
    public TaskType getType() {
        return this;
    }

    /**
     * priority is represented by an integer value, ranging from 1 to 10 * @param
     * priority * @return whether the priority is valid or not
     */
    private static boolean validatePriority(int priority) {
        if (priority < 1 || priority > 10)
            return false;
        return true;
    }
}