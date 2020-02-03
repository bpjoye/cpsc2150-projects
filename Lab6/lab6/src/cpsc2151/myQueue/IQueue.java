package cpsc2151.myQueue;

public interface IQueue<T> {

    int MAX_DEPTH = 100;

    /**
     * Adds x to the end of the Queue
     * @param x: thing to add
     * @pre: this.size + 1 <= MAX_DEPTH
     * @post: x will be at the end of the list, depth++
     */
    void add(T x);

    /**
     * Removes and returns the object at the front of the queue
     * @return: the first object in the list
     * @pre: this.size > 0
     * @post: this.size--, the first element will be removed
     */
    T pop();

    /**
     * Returns the number of objects in the Queue
     * @return: the number of objects in the Queue
     * @pre: this.size > 0
     * @post: will return the size of the list
     */
    int size();

    /**
     * Returns the object at the front of the queue
     * @return the object at the front of the queue
     * @pre: this.size > 0
     * @post: the object at the front of the queue will be returned
     */
    default T peek() {

        T i = null;
        T temp;
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
     * Returns the object at the end of the queue
     * @return: the object at the end of the queue
     * @pre: this.size > 0
     * @post: the object at the end of the queue will be returned
     */
    default T endOfQueue() {

        T i = null;
        T temp;
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
     * Inserts an object at the given position
     * @param x: object to insert
     * @param pos: position to insert at
     * @pre: 0 < pos < this.size
     * @post: x will be inserted at position po
     */
    default void insert(T x, int pos) {

        T temp;
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
     * Removes the object at the position passed
     * @param pos: position in the queue to remove
     * @return: the object being removed
     * @pre: 0 < pos < this.size
     * @post: the object at position pos will be removed from the queue
     */
    default T remove(int pos) {

        T temp;
        T x = null;
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
     * Returns the object at the position
     * @param pos: position in the queue to return
     * @return: the object at position pos in the queue
     * @pre this.size > 0 && 0 < pos < this.size
     */
    default T get(int pos) {

        T temp;
        T x = null;
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
