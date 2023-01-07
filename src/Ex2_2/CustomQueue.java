package Ex2_2;
import java.util.Comparator;
import java.util.Objects;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.TimeUnit;

public class CustomQueue<Task> extends PriorityBlockingQueue<Task> {
    private int maxPriority;

    public CustomQueue(int initialCapacity, Comparator<? super Task> comparator, int maxPriority) {
        super(initialCapacity, comparator);
        this.maxPriority = maxPriority;
    }



    public void updatePriority(int priority)
    {
        if (priority<this.maxPriority)
            this.maxPriority = priority;
    }
    private void updateMax() // first in queue is the lowest number
    {
        Object taskObject = super.peek();
        int priority =checkPriority(taskObject);
        this.maxPriority = maxPriority; // =============== problem here, never gets here
    }
    private int checkPriority(Object taskObject)
    {
        return ((Ex2_2.Task) taskObject).getPriority();
    }
    public int getMaxPriority() {
        return this.maxPriority;
    }
    /* ************************************************************* */
    // trying to find where the queue removes a Task
    @Override
    public Task remove() {
        Task taskObject =super.remove();
        int priority = checkPriority(taskObject);
        if (priority>maxPriority)
        {
            updateMax();

        }
        this.maxPriority=99;
        return taskObject;

    }

    @Override
    public Task poll() {
        Task taskObject = super.poll();
        int priority = checkPriority(taskObject);
        if (priority>maxPriority)
        {
                updateMax();

        }
        this.maxPriority=99;
        return taskObject;
    }

    @Override
    public Task take() throws InterruptedException {
        Task taskObject = super.take();
//        int priority = checkPriority(taskObject);
//        if (priority>maxPriority)
//        {
//            updateMax();
//
//        }
        this.maxPriority=99;
        return taskObject;
    }

    @Override
    public Task poll(long timeout, TimeUnit unit) throws InterruptedException {
        Task taskObject = super.poll(timeout, unit);
        int priority = checkPriority(taskObject);
        if (priority>maxPriority)
        {
            updateMax();

        }
        this.maxPriority=99;
        return taskObject;

    }

    @Override
    public boolean remove(Object o) {
//        Task taskObject = (Task) o;
//        int priority = checkPriority(taskObject);
//        if (priority>maxPriority)
//        {
//            updateMax();
//
//        }
        this.maxPriority=99;
        return super.remove(o);
    }
    /* ************************************************************* */
}
