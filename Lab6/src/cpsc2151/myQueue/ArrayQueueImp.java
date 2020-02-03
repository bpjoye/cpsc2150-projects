package cpsc2151.myQueue;

public class ArrayQueueImp<T> implements IQueue<T> {

    private T myQ[];
    private int depth;

    /**
     * ArrayQueueImp Constructor
     * @post
     *  depth = 0, myQ has length MAX_DEPTH
     */
    public ArrayQueueImp() {
        depth = 0;
        // generic array initialization
        myQ = (T[]) new Object[MAX_DEPTH];
    }

    public void add(T x) {
        myQ[depth] = x;
        depth++;
    }

    public T pop() {
        T front = myQ[0];

        for (int x = 0; x < depth; x++) {
            myQ[x] = myQ[x+1];
        }
        depth--;
        return front;
    }

    public int size() {
        return depth;
    }

    @Override
    public String toString() {

        String s = "";
        for (int x = 0; x < depth; x++) {
            s += myQ[x];
            s += " ";
        }

        return s;
    }
}
