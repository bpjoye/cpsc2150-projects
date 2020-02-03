// Ben Joye
// CPSC-2150
// 11/18/18

package cpsc2150.connectX;

import java.util.Arrays;

public class GameBoard implements IGameBoard {

    /**
     * @invariant:
     * 3 <= rows <= 100
     * 3 <= cols <= 100
     * 3 <= numToWin <= 25
     */

    private char[][] boardArr;
    private int rows;
    private int cols;
    private int numToWin;
    /**
     * Default constructor for the GameBoard class
     * @pre: 3 <= r <= 100; 3 <= c <= 100; 3 <= w <= 25
     * @post
     *  a board array will be created with dimensions [r][c]
     *  the board array will be filled with space characters
     */
    GameBoard(int r, int c, int w) {
        numToWin = w;
        rows = r;
        cols = c;
        boardArr = new char[r][c];
        for (char[] row: boardArr) {
            Arrays.fill(row, ' ');
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

        for (int x = 0; x < getNumRows(); x++) {
            if (whatsAtPos(x,c) == ' ') {
                boardArr[x][c] = p;
                x = getNumRows();
            }
        }
    }

    /**
     * Gets the symbol at the position in the board array specified
     * @param r: row to of the symbol to get
     * @param c: column of the symbol to get
     * @return: char value of the players symbol at [r][c] in the board array
     * @pre: 0 <= r < this.getNumRows, 0 <= c < this.getNumColumns
     * @post: char at board array [r][c] will be returned
     */
    public char whatsAtPos(int r, int c) {

        char p = boardArr[r][c];
        return p;
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

    /**
     * Checks if the game resulted in a tie
     * @pre: the board array is initialized
     * @return: a bool, true if the game ended in tie, false if it did not end in a tie
     * @post: a bool will be returned depending if the game ended in a tie or not
     */
    public boolean checkTie() {

        for (int x = 0; x < getNumColumns(); x++) {
            if (checkIfFree(x)) {
                return false;
            }
        }
        return true;
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

    /**
     * Checks if the column has any open spots
     * @param c: the column to check
     * @return: bool, true if column has open spots, false if it doesn't
     * @pre: 0 <= c < this.getNumColumns
     * @post: will return true or false depending on if the column has open spots
     */
    public boolean checkIfFree(int c) {

        for (int x = 0; x < getNumRows(); x++) {
            if (whatsAtPos(x,c) == ' ') {
                return true;
            }
        }
        return false;
    }
}