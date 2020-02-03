// Ben Joye
// CPSC-2150
// 11/18/18

package cpsc2150.connectX;
import java.util.*;

public class Connect4Game {
    /**
     * @invariant:
     *  0 <= col < 7
     */
    public static void main(String [] args) {

        char again = 'Y';

        while (again == 'Y' || again == 'y') {

            boolean gameWon = false;
            boolean free;
            Scanner scanner = new Scanner(System.in);
            int col;

            System.out.println("How many players");
            int numPlayers = scanner.nextInt();
            while (numPlayers < 2 || numPlayers > 10) {
                if (numPlayers < 2) {
                    System.out.println("Must be at least 2 players");
                } else if (numPlayers > 10) {
                    System.out.println("Must be 10 players or fewer");
                }
                numPlayers = scanner.nextInt();
            }

            Vector<Character> players = new Vector<>();
            char playerChar;

            for (int x = 1; x <= numPlayers; x++) {
                System.out.println("Enter the character to represent player " + x);
                playerChar = scanner.next().charAt(0);
                while (players.contains(playerChar)) {
                    System.out.println(playerChar + " is already taken as a player token!");
                    System.out.println("Enter the character to represent player " + x);
                    playerChar = scanner.next().charAt(0);
                }
                players.add(playerChar);
            }

            System.out.println("How many rows should be on the board?");
            int inputRow = scanner.nextInt();
            while (inputRow < 3 || inputRow > 100) {
                if (inputRow < 3)
                    System.out.println("Must have at least 3 rows.");
                else if (inputRow > 100)
                    System.out.println("Can have at most 100 rows.");
                inputRow = scanner.nextInt();
            }

            System.out.println("How many columns should be on the board?");
            int inputCol = scanner.nextInt();
            while (inputCol < 3 || inputCol > 100) {
                if (inputCol < 3)
                    System.out.println("Must have at least 3 columns.");
                else if (inputCol > 100)
                    System.out.println("Can have at most 100 columns.");
                inputCol = scanner.nextInt();
            }

            System.out.println("How many in a row to win?");
            int rowToWinInput = scanner.nextInt();
            while (rowToWinInput < 3 || rowToWinInput > 25) {
                if (rowToWinInput < 3)
                    System.out.println("Must have at least 3 in a row to win.");
                else if (rowToWinInput > 25)
                    System.out.println("Can have at most 25 in a row to win.");
                rowToWinInput = scanner.nextInt();
            }

            IGameBoard board;
            System.out.println("Would you like a Fast Game (F/f) or a Memory Efficient Game (M/m?)");
            char i = scanner.next().charAt(0);
            if (i == 'F' || i == 'f') {
                board = new GameBoard(inputRow,inputCol,rowToWinInput);
            } else {
                board = new GameBoardMem(inputRow,inputCol,rowToWinInput);
            }

            System.out.println(board.toString());

            int index = -1;
            char player = 'a';

            while (!gameWon) {

                index++;
                index = index % numPlayers;
                player = players.get(index);

                System.out.println("Player " + player + ", what column do you want to place your marker in?");
                col = scanner.nextInt();

                if (col < 0 || col > board.getNumColumns()-1) {
                    while (col < 0 || col > board.getNumColumns()-1) {

                        if (col < 0) {
                            System.out.println("Column cannot be less that 0");
                        } else if (col > board.getNumColumns()-1) {
                            System.out.println("Column cannot be greater than " + (board.getNumColumns()-1));
                        }
                        System.out.println("Player " + player + ", what column do you want to place your marker in?");
                        col = scanner.nextInt();
                    }
                }

                free = board.checkIfFree(col);
                if (free == true) {
                    board.placeToken(player, col);
                } else {
                    while (!board.checkIfFree(col)) {
                        System.out.println("Column is full");
                        System.out.println("Player " + player + ", what column do you want to place your marker in?");
                        col = scanner.nextInt();
                    }
                    board.placeToken(player, col);
                }

                System.out.println(board.toString());
                gameWon = board.checkForWin(col);
            }

            if (board.checkTie()) {
                System.out.println("The game is a tie!");
            } else {
                System.out.println("Player " + player + " Won!");
            }
            System.out.println("Would you like to play again? Y/N");
            again = scanner.next().charAt(0);
        }
    }
}