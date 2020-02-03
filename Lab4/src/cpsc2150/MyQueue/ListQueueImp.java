package cpsc2150.MyQueue;

import java.util.*;
/**
 * Correspondence: this = myQ. The front of myQ is the front of the Queue
 * Correspondence: size = myQ.size();
 *
 * @invariant: 0 <= depth <= MAX_DEPTH
 */
public class ListQueueImp implements IntegerQueueI {

    private List<Integer> myQ;

    /**
     * ListQueueImp Constructor
     * @post: creates a new ArrayList
     */
    public ListQueueImp() {
        myQ = new ArrayList<>();
    }

    public void add(Integer x) {
        myQ.add(x);
    }

    public Integer pop() {
        int front = myQ.get(0);
        myQ.remove(0);
        return front;
    }

    public int size() {
        return myQ.size();
    }


    @Override
    public String toString() {

        String s = "";
        for (int x = 0; x < myQ.size(); x++) {
            s += myQ.get(x);
            s += " ";
        }
        return s;
    }
}
