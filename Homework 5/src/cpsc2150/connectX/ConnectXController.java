package cpsc2150.connectX;

/**
 * The controller class will handle communication between our View and our Model (IGameBoard)
 *
 * This is where you will write code
 *
 * You will need to include your IGameBoard interface
 * and both of the IGameBoard implementations from Homework 3
 * If your code was correct you will not need to make any changes to your IGameBoard implementation class
 */

public class ConnectXController {
    //our current game that is being played
    private IGameBoard curGame;


    //The screen that provides our view
    private ConnectXView screen;



    public static final int MAX_PLAYERS = 10;
    //our play tokens are hard coded. We could make a screen to get those from the user, but
    //I want to keep this example simple
    private char[] players = {'X', 'O', 'Y', 'Z', 'A', 'K', 'E', 'J', 'N', 'H'};

   private int numPlayers;
   private int turn;
   private boolean gameWon;


    /**
     *
     * @param model the board implementation
     * @param view the screen that is shown
     * @post the controller will respond to actions on the view using the model.
     */
    ConnectXController(IGameBoard model, ConnectXView view, int np){
        this.curGame = model;
        this.screen = view;
        numPlayers = np;
        turn = 0;
        gameWon = false;
    }

    /**
     *
     *
     * @param col the column of the activated button
     * @post will allow the player to place a token in the column if it is not full, otherwise it will display an error
     * and allow them to pick again. Will check for a win as well. If a player wins it will allow for them to play another
     * game hitting any button
     */
    public void processButtonClick(int col) {

        if (!gameWon) {

            // get character token for this turn
            char p = players[turn%numPlayers];

            // only let the user place a token if the column isn't full
            if (curGame.checkIfFree(col)) {
                curGame.placeToken(p,col);
                int x = curGame.getNumRows()-1;

                // loop to find the first open spot on the visual board
                for (int i = 0; i < curGame.getNumRows(); i++) {
                    if (curGame.whatsAtPos(i,col) == ' ') {
                        x = i-1;
                        break;
                    }
                }

                // place the marker on the screen
                screen.setMarker(x,col,p);

                // check if the game was won
                gameWon = curGame.checkForWin(col);
                if (gameWon) {
                    screen.setMessage("Player " + p + " won! Press any button to start new game.");
                } else {
                    turn++;
                    screen.setMessage("It is " + players[turn%numPlayers] + "'s turn");
                }
            } else {
                screen.setMessage("Column is full");
            }
        } else {
            // if game is won, start a new game when the button is pressed again
            newGame();
        }
    }

    /**
     * This method will start a new game by returning to the setup screen and controller
     */
    private void newGame()
    {
        //close the current screen
        screen.dispose();
        //start back at the set up menu
        SetupView screen = new SetupView();
        SetupController controller = new SetupController(screen);
        screen.registerObserver(controller);
    }
}
