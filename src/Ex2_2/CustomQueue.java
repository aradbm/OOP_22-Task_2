package Ex2_2;

import java.util.Comparator;

public class CustomQueue<T extends Task> implements Comparator<T> {
    @Override
    public int compare(Task o1, Task o2) {
        return o1.compareTo(o2);
    }
    public void poll() {


    }
}
//    @Override
//    public int remove(){
//
//    }