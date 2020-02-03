package cpsc2150.MyQueue;

/**
 * A queue containing integers.
 * A queue is a data structure where the first item added to the structure is the first item removed from the structure
 * This queue is bounded by MAX_DEPTH
 *
 * Initialization ensures the queue is empty
 * Defines: size:Z
 * Constraints: 0 <= size <= MAX_DEPTH
 */
public interface IntegerQueueI {

    int MAX_DEPTH = 100;

    /**
     * Adds x to the end of the Queue
     * @param x: int to add
     * @pre: x is an int
     * @post: x will be at the end of the list, depth++
     */
    public void add(Integer x);

    /**
     * Removes and returns the Integer at the front of the queue
     * @return: the first integer in the list
     * @pre: depth > 0
     * @post: depth--, the first element will be removed
     */
    public Integer pop();

    /**
     * Returns the number of Integers in the Queue
     * @return: the number of Integers in the Queue
     * @pre: depth > 0
     * @post: will return the size of the list
     */
    public int size();

    /**
     * Returns the number at the front of the queue
     * @return the integer at the front of the queue
     * @pre: queue size > 0
     * @post: the integer at the front of the queue will be returned
     */
    default Integer peek() {

        int i = 0;
        int temp;
        for (int x = 0; x < size(); x++) {
            if (x == 0) {
                i = pop();
                temp = i;
            } else {
                temp = pop();
            }
            add(temp);
        }

        return i;
    }

    /**
     * Returns the number at the end of the queue
     * @return: the integer at the end of the queue
     * @pre: queue size > 0
     * @post: the integer at the end of the queue will be returned
     */
    default Integer endOfQueue() {

        int i = 0;
        int temp;
        for (int x = 0; x < size(); x++) {
            if (x == size()-1) {
                i = pop();
                temp = i;
            } else {
                temp = pop();
            }
            add(temp);
        }

        return i;
    }

    /**
     * Inserts an integer at the given position
     * @param x: number to insert
     * @param pos: position to insert at
     * @pre: pos > 0 && pos < queue size
     * @post: x will be inserted at position po
     */
    default void insert(Integer x, int pos) {

        int temp;
        for (int i = 0; i < size(); i++) {

            if (i == pos-1) {
                add(x);
            } else {
                temp = pop();
                add(temp);
            }
        }
        if (pos == size()+1) {
            add(x);
        }
    }

    /**
     * Removes the number at the position passed
     * @param pos: position in the queue to remove
     * @return: the integer being removed
     * @pre: pos > 0 && pos < size
     * @post: the number at position pos will be removed from the queue
     */
    default Integer remove(int pos) {

        int temp;
        int x = 0;
        for (int i = 0; i <= size(); i++) {

            if (i == pos-1) {
                x = pop();
            } else {
                temp = pop();
                add(temp);
            }
        }
        if (pos == size()) {
            x = pop();
        }
        return x;
    }

    /**
     * Returns the integer at the position
     * @param pos: position in the queue to return
     * @return: the integer at position pos in the queue
     * @pre size > 0 && pos > 0 && pos < size
     */
    default Integer get(int pos) {

        int temp;
        int x = 0;
        for (int i = 0; i < size(); i++) {

            if (i == pos-1) {
                x = pop();
                add(x);
            } else {
                temp = pop();
                add(temp);
            }
        }
        return x;
    }
}
