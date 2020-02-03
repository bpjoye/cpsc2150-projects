// Ben Joye
// CPSC-2150
// 11/18/18

package cpsc2150.connectX;
import java.util.*;

public class GameBoardMem implements IGameBoard {

    /**
     * @invariant:
     * 3 <= rows <= 100
     * 3 <= cols <= 100
     * 3 <= numToWin <= 25
     */

    private Map<Integer,List<Character>> board;
    private int rows;
    private int cols;
    private int numToWin;


    /**
     * Default constructor for the GameBoardMem class
     * @pre: 3 <= r <= 100; 3 <= c <= 100; 3 <= w <= 25
     * @post
     *  a board will be created with dimensions r, c
     *  each key will be initialized with an empty list
     */
    GameBoardMem(int r, int c, int w) {

        board = new HashMap<>();
        rows = r;
        cols = c;
        numToWin = w;

        // initialize all keys with an empty list
        for (int x = 0; x < c; x++) {
            board.put(x, new ArrayList<>());
        }
    }

    /**
     * Places the player's symbol in the specified column
     * @param p: the symbol of the player placing the piece
     * @param c: the column to place the piece in
     * @pre: 0 <= c < this.getNumColumns
     * @post: the players symbol, p, will be placed in the first available spot in the column, c
     */
    public void placeToken(char p, int c) {
        // copy the list at the key
        List<Character> l = board.get(c);
        // add the character to the list
        l.add(p);
        // add the list back to the key
        board.put(c,l);
    }

    /**
     * Gets the symbol at the position in the board array specified
     * @param r: row to of the symbol to get
     * @param c: column of the symbol to get
     * @return: char value of the players symbol at r and c in the board array
     * @pre: 0 <= r < this.getNumRows, 0 <= c < this.getNumColumns
     * @post: char in board in column c and row r will be returned
     */
    public char whatsAtPos(int r, int c) {
        // if it's empty, return a space
        if (board.get(c).isEmpty()) {
            return ' ';
        // the row exists in the list, return that value
        } else if (r < board.get(c).size()) {
            char p = board.get(c).get(r);
            return p;
        // else return space
        } else {
            return ' ';
        }
    }

    /**
     * Checks if the game resulted in a tie
     * @pre: the board array is initialized
     * @return: a bool, true if the game ended in tie, false if it did not end in a tie
     * @post: a bool will be returned depending if the game ended in a tie or not
     */
    public boolean checkTie() {
        return false;
    }


    public int getNumRows() {
        return rows;
    }

    public int getNumColumns() {
        return cols;
    }

    public int getNumToWin() {
        return numToWin;
    }

    public boolean checkIfFree(int c) {
        // if the size of the list is smaller than the max, return true
        if (board.get(c).size() < getNumRows()) {
            return true;
        }
        return false;
    }

    /**
     * Converts the board array into a formatted string
     * @return: a string containing the board array
     * @pre: board array is initialized
     * @post: will return a string containing the board array
     */
    @Override
    public String toString() {

        String matrix = "| 0| 1| 2|";
        for (int x = 3; x < getNumColumns(); x++) {

            if (x < 10) {
                matrix += " ";
                matrix += x;
                matrix += "|";
            } else {
                matrix += x;
                matrix += "|";
            }
        }
        matrix += "\n";

        for (int r = getNumRows()-1; r >= 0; r--) {
            matrix += "|";
            for (int c = 0; c < getNumColumns(); c++) {
                matrix += whatsAtPos(r,c);
                matrix += " |";
            }
            matrix += "\n";
        }
        return matrix;
    }

}
