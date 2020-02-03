// Ben Joye
// CPSC-2150
// 10/28/18

package cpsc2150.connectX;

/**
 * This interface controls a Connect 4 Board.
 * Initialization Ensures: a game board will be created and initialized.
 * Defines: BoardArray, Rows, Columns, NumberToWin
 * Constraints: 3 <= rows <= 100; 3 <= cols <= 100; 3 <= numToWin <= 25
 */
public interface IGameBoard {

    void placeToken(char p, int c);
    char whatsAtPos(int r, int c);
    boolean checkTie();
    int getNumRows();
    int getNumColumns();
    int getNumToWin();
    boolean checkIfFree(int c);


    /**
     * Checks if a player has won the game
     * @param c: the column of the last placed piece
     * @return: bool, true if a player has this.getNumToWin in a row, false if the player has not won
     * @pre: 0 <= c < this.getNumColumns
     * @post: a bool will be returned, true if a player has this.getNumToWin in a row, false if the player does not
     */
    default boolean checkForWin(int c) {

        int r = getNumRows()-1;
        while (whatsAtPos(r,c) == ' ') {
            r--;
        }
        char p = whatsAtPos(r,c);
        if (checkHorizWin(r,c,p))
            return true;
        if (checkVertWin(r,c,p))
            return true;
        if (checkDiagWin(r,c,p))
            return true;
        return false;
    }


    /**
     * Checks if the player won by having this.getNumToWin pieces in a horizontal row
     * @param r: row of the most recent move made
     * @param c: column of the most recent move made
     * @param p: symbol of the player who made the most recent move
     * @pre: 0 <= r < this.getNumRows, 0 <= c < this.getNumColumns, p equals 'X' or 'O'
     * @return: bool, true if this.getNumToWin in a row is found, false if not
     */
    default boolean checkHorizWin(int r, int c, char p) {

        int inARow = -1;
        int col = c;
        while (col < getNumColumns()) {
            if (whatsAtPos(r,col) == p) {
                col++;
                inARow++;
            } else {
                col = getNumColumns();
            }
        }
        col = c;

        while (col >= 0) {
            if (whatsAtPos(r,col) == p) {
                col--;
                inARow++;
            } else {
                col = -1;
            }
        }

        if (inARow >= getNumToWin())
            return true;
        return false;
    }

    /**
     * Checks if the player won by having 4 pieces in a vertical row
     * @param r: row of the most recent move made
     * @param c: column of the most recent move made
     * @param p: symbol of the player who made the most recent move
     * @pre: 0 <= r < this.getNumRows, 0 <= c < this.getNumColumns, p equals 'X' or 'O'
     * @return: bool, true if this.getNumToWin in a row is found, false if not
     */
    default boolean checkVertWin(int r, int c, char p) {

        int inARow = -1;
        int row = r;
        while (row < getNumRows()) {
            if (whatsAtPos(row,c) == p) {
                row++;
                inARow++;
            } else {
                row = getNumRows();
            }
        }
        row = r;
        while (row >= 0) {
            if (whatsAtPos(row,c) == p) {
                row--;
                inARow++;
            } else {
                row = -1;
            }
        }

        if (inARow >= getNumToWin())
            return true;
        return false;
    }

    /**
     * Checks if the player won by having this.getNumToWin pieces in a diagonal row
     * @param r: row of the most recent move made
     * @param c: column of the most recent move made
     * @param p: symbol of the player who made the most recent move
     * @pre: 0 <= r < this.getNumRows, 0 <= c < this.getNumColumns, p equals 'X' or 'O'
     * @return: bool, true if this.getNumToWin in a row is found, false if not
     */
    default boolean checkDiagWin(int r, int c, char p) {

        int inARow = -1;
        int row = r;
        int col = c;
        while (row < getNumRows()-1 && col < getNumColumns()-1) {
            if (whatsAtPos(row,col) == p) {
                row++;
                col++;
                inARow++;
            } else {
                row = getNumRows();
            }
        }
        row = r;
        col = c;
        while (row >= 0 && col >= 0) {
            if (whatsAtPos(row,col) == p) {
                row--;
                col--;
                inARow++;
            } else {
                row = -1;
            }
        }

        if (inARow >= getNumToWin())
            return true;


        inARow = -1;
        row = r;
        col = c;

        while (row >= 0 && col < getNumColumns()-1) {
            if (whatsAtPos(row,col) == p) {
                row--;
                col++;
                inARow++;
            } else {
                col = getNumRows();
            }
        }
        row = r;
        col = c;
        while (row < getNumRows()-1 && col >= 0) {
            if (whatsAtPos(row,col) == p) {
                row++;
                col--;
                inARow++;
            } else {
                row = getNumRows();
            }
        }

        if (inARow >= getNumToWin())
            return true;
        return false;
    }


}
