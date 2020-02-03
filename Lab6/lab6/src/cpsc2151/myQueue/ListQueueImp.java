package cpsc2151.myQueue;

import java.util.*;

public class ListQueueImp<T> implements IQueue<T> {

    private List<T> myQ;

    /**
     * ListQueueImp Constructor
     * @post: creates a new ArrayList
     */
    public ListQueueImp() {

        myQ = new ArrayList<>();
    }

    public void add(T x) {

        myQ.add(x);
    }

    public T pop() {
        T front = myQ.get(0);
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
