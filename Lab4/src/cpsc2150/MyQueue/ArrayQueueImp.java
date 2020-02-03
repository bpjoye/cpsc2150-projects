package cpsc2150.MyQueue;

/**
 * Correspondence: this = myQ[0...depth-1], myQ[0] is the front of the Queue
 * Correspondence: size = depth
 *
 * @invariant: 0 <= depth <= MAX_DEPTH
 */
public class ArrayQueueImp implements IntegerQueueI {

    private Integer[] myQ;
    private int depth;

    /**
     * ArrayQueueImp Constructor
     * @post
     *  depth = 0, myQ has length MAX_DEPTH
     */
    public ArrayQueueImp() {
        depth = 0;
        myQ = new Integer[MAX_DEPTH];
    }

    public void add(Integer x) {
        myQ[depth] = x;
        depth++;
    }

    public Integer pop() {
        int front = myQ[0];

        for (int x = 0; x < depth; x++) {
            myQ[x] = myQ[x+1];
        }
        myQ[depth] = 0;
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
