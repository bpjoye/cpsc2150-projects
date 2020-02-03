package cpsc2150.connectX;

import org.junit.Test;
import static org.junit.Assert.*;

public class TestIGameBoard {

    String expectedString(char[][] board) {

        int length = board[0].length;
        int width = board.length;

        String matrix = "| 0| 1| 2|";
        for (int x = 3; x < length; x++) {

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


        for (int r = width-1; r >= 0; r--) {
            matrix += "|";
            for (int c = 0; c < length; c++) {
                matrix += board[r][c];
                matrix += " |";
            }
            matrix += "\n";
        }
        return matrix;
    }

    char[][] makeExpectedBoard(int row,int col) {

        char[][] board = new char[row][col];
        for (int r = 0; r < row; r++) {
            for (int c = 0; c < col; c++) {
                board[r][c] = ' ';
            }
        }
        return board;
    }


    // FACTORY (FFactory / MFactory)
    private IGameBoardFactory factory = new FFactory();

    // CONSTRUCTOR TESTS (3)

    @Test
    public void testIGameBoard_min() {

        IGameBoard gb = factory.makeStack(3,3,3);
        char[][] egb = makeExpectedBoard(3,3);
        assertEquals(gb.toString(),expectedString(egb));

    }

    @Test
    public void testIGameBoard2_max() {

        IGameBoard gb = factory.makeStack(100,100,25);
        char[][] egb = makeExpectedBoard(100,100);
        assertEquals(gb.toString(),expectedString(egb));
    }

    @Test
    public void testIGameBoard3_minandmax() {

        IGameBoard gb = factory.makeStack(3,100,25);
        char[][] egb = makeExpectedBoard(3,100);
        assertEquals(gb.toString(),expectedString(egb));
    }


    // CHECKIFFREE TESTS (3)

    @Test
    public void testCheckIfFree_emptyboard() {

        IGameBoard gb = factory.makeStack(4,6,3);
        char[][] egb = makeExpectedBoard(4,6);

        assertEquals(gb.checkIfFree(0),true);
    }

    @Test
    public void testCheckIfFree_fullcolumn() {

        IGameBoard gb = factory.makeStack(4,6,3);
        char[][] egb = makeExpectedBoard(4,6);

        gb.placeToken('X',2);
        egb[0][2] = 'X';
        gb.placeToken('O',2);
        egb[1][2] = 'O';
        gb.placeToken('X',2);
        egb[2][2] = 'X';
        gb.placeToken('O',2);
        egb[3][2] = 'O';

        assertEquals(gb.checkIfFree(2),false);
    }

    @Test
    public void testCheckIfFree_halffull() {

        IGameBoard gb = factory.makeStack(4,6,3);
        char[][] egb = makeExpectedBoard(4,6);

        gb.placeToken('X',2);
        egb[0][2] = 'X';
        gb.placeToken('O',2);
        egb[1][2] = 'O';

        assertEquals(gb.checkIfFree(2),true);
    }


    // CHECKHORIZWIN TESTS (5)

    @Test
    public void testCheckHorizWin_bottomrow() {

        IGameBoard gb = factory.makeStack(4,6,3);
        char[][] egb = makeExpectedBoard(4,6);

        gb.placeToken('X',0);
        egb[0][0] = 'X';
        gb.placeToken('X',1);
        egb[0][1] = 'X';
        gb.placeToken('X',2);
        egb[0][2] = 'X';

        assertEquals(gb.checkHorizWin(0,2,'X'),true);
    }

    @Test
    public void testCheckHorizWin_bottomrowdifchars() {

        IGameBoard gb = factory.makeStack(4,6,3);
        char[][] egb = makeExpectedBoard(4,6);

        gb.placeToken('X',0);
        egb[0][0] = 'X';
        gb.placeToken('O',1);
        egb[0][1] = 'O';
        gb.placeToken('X',2);
        egb[0][2] = 'X';

        assertEquals(gb.checkHorizWin(0,2,'X'),false);
    }

    @Test
    public void testCheckHorizWin_toprow() {

        IGameBoard gb = factory.makeStack(4,6,3);
        char[][] egb = makeExpectedBoard(4,6);

        gb.placeToken('O',0);
        egb[0][0] = 'O';
        gb.placeToken('O',1);
        egb[0][1] = 'O';
        gb.placeToken('O',2);
        egb[0][2] = 'O';

        gb.placeToken('O',0);
        egb[1][0] = 'O';
        gb.placeToken('O',1);
        egb[1][1] = 'O';
        gb.placeToken('O',2);
        egb[1][2] = 'O';

        gb.placeToken('O',0);
        egb[2][0] = 'O';
        gb.placeToken('O',1);
        egb[2][1] = 'O';
        gb.placeToken('O',2);
        egb[2][2] = 'O';

        gb.placeToken('X',0);
        egb[3][0] = 'X';
        gb.placeToken('X',1);
        egb[3][1] = 'X';
        gb.placeToken('X',2);
        egb[3][2] = 'X';

        assertEquals(gb.checkHorizWin(3,2,'X'),true);
    }

    @Test
    public void testCheckHorizWin_middle() {

        IGameBoard gb = factory.makeStack(4,6,3);
        char[][] egb = makeExpectedBoard(4,6);

        gb.placeToken('O',2);
        egb[0][2] = 'O';
        gb.placeToken('O',3);
        egb[0][3] = 'O';
        gb.placeToken('O',4);
        egb[0][4] = 'O';

        gb.placeToken('O',2);
        egb[1][2] = 'O';
        gb.placeToken('O',3);
        egb[1][3] = 'O';
        gb.placeToken('O',4);
        egb[1][4] = 'O';

        gb.placeToken('X',2);
        egb[2][2] = 'X';
        gb.placeToken('X',3);
        egb[2][3] = 'X';
        gb.placeToken('X',4);
        egb[2][4] = 'X';

        assertEquals(gb.checkHorizWin(2,4,'X'),true);
    }

    @Test
    public void testCheckHorizWin_mixed() {

        IGameBoard gb = factory.makeStack(4,6,3);
        char[][] egb = makeExpectedBoard(4,6);

        gb.placeToken('X',1);
        egb[0][1] = 'X';
        gb.placeToken('O',2);
        egb[0][2] = 'O';
        gb.placeToken('X',1);
        egb[1][1] = 'X';
        gb.placeToken('O',1);
        egb[2][1] = 'O';
        gb.placeToken('X',2);
        egb[1][2] = 'X';
        gb.placeToken('O',3);
        egb[0][3] = 'O';
        gb.placeToken('X',3);
        egb[1][3] = 'X';

        assertEquals(gb.checkHorizWin(1,3,'X'),true);
    }


    // CHECKVERTWIN TESTS (5)

    @Test
    public void testCheckVertWin_left() {

        IGameBoard gb = factory.makeStack(4,6,3);
        char[][] egb = makeExpectedBoard(4,6);

        gb.placeToken('O',0);
        egb[0][0] = 'O';
        gb.placeToken('O',0);
        egb[1][0] = 'O';
        gb.placeToken('O',0);
        egb[2][0] = 'O';

        assertEquals(gb.checkVertWin(2,0,'O'),true);
    }

    @Test
    public void testCheckVertWin_nowin() {

        IGameBoard gb = factory.makeStack(4,6,3);
        char[][] egb = makeExpectedBoard(4,6);

        gb.placeToken('X',1);
        egb[0][1] = 'X';
        gb.placeToken('O',1);
        egb[1][1] = 'O';
        gb.placeToken('O',1);
        egb[2][1] = 'O';

        assertEquals(gb.checkVertWin(2,1,'O'),false);
    }

    @Test
    public void testCheckVertWin_right() {

        IGameBoard gb = factory.makeStack(4,6,3);
        char[][] egb = makeExpectedBoard(4,6);

        gb.placeToken('X',5);
        egb[0][5] = 'X';
        gb.placeToken('O',5);
        egb[1][5] = 'O';
        gb.placeToken('O',5);
        egb[2][5] = 'O';
        gb.placeToken('O',5);
        egb[3][5] = 'O';

        assertEquals(gb.checkVertWin(3,5,'O'),true);
    }

    @Test
    public void testCheckVertWin_middle() {

        IGameBoard gb = factory.makeStack(4,6,3);
        char[][] egb = makeExpectedBoard(4,6);

        gb.placeToken('O',1);
        egb[0][1] = 'O';
        gb.placeToken('O',1);
        egb[1][1] = 'O';
        gb.placeToken('O',1);
        egb[2][1] = 'O';

        assertEquals(gb.checkVertWin(2,1,'O'),true);
    }

    @Test
    public void testCheckVertWin_horiz() {

        IGameBoard gb = factory.makeStack(4,6,3);
        char[][] egb = makeExpectedBoard(4,6);

        gb.placeToken('X',0);
        egb[0][0] = 'X';
        gb.placeToken('X',1);
        egb[0][1] = 'X';
        gb.placeToken('X',2);
        egb[0][2] = 'X';

        assertEquals(gb.checkVertWin(2,0,'X'),false);
    }


    // CHECKDIAGWIN TESTS (8)

    @Test
    public void testCheckDiagWin_lefttoright() {

        IGameBoard gb = factory.makeStack(4,6,3);
        char[][] egb = makeExpectedBoard(4,6);

        gb.placeToken('X',0);
        egb[0][0] = 'X';
        gb.placeToken('O',1);
        egb[0][1] = 'O';
        gb.placeToken('X',1);
        egb[1][1] = 'X';
        gb.placeToken('O',2);
        egb[0][2] = 'O';
        gb.placeToken('X',2);
        egb[1][2] = 'X';
        gb.placeToken('X',2);
        egb[2][2] = 'X';

        assertEquals(gb.checkDiagWin(2,2,'X'),true);
    }

    @Test
    public void testCheckDiagWin_righttoleft() {

        IGameBoard gb = factory.makeStack(4,6,3);
        char[][] egb = makeExpectedBoard(4,6);

        gb.placeToken('X',2);
        egb[0][2] = 'X';
        gb.placeToken('O',1);
        egb[0][1] = 'O';
        gb.placeToken('X',1);
        egb[1][1] = 'X';
        gb.placeToken('O',0);
        egb[0][0] = 'O';
        gb.placeToken('X',0);
        egb[1][0] = 'X';
        gb.placeToken('X',0);
        egb[2][0] = 'X';

        assertEquals(gb.checkDiagWin(2,0,'X'),true);
    }

    @Test
    public void testCheckDiagWin_horiz() {

        IGameBoard gb = factory.makeStack(4,6,3);
        char[][] egb = makeExpectedBoard(4,6);

        gb.placeToken('X',0);
        egb[0][0] = 'X';
        gb.placeToken('X',1);
        egb[0][1] = 'X';
        gb.placeToken('X',2);
        egb[0][2] = 'X';

        assertEquals(gb.checkDiagWin(0,2,'X'),false);
    }

    @Test
    public void testCheckDiagWin_middle() {

        IGameBoard gb = factory.makeStack(4,6,3);
        char[][] egb = makeExpectedBoard(4,6);

        gb.placeToken('X',1);
        egb[0][1] = 'X';
        gb.placeToken('O',2);
        egb[0][2] = 'O';
        gb.placeToken('X',2);
        egb[1][2] = 'X';
        gb.placeToken('O',3);
        egb[0][3] = 'O';
        gb.placeToken('X',3);
        egb[1][3] = 'X';
        gb.placeToken('X',3);
        egb[2][3] = 'X';

        assertEquals(gb.checkDiagWin(2,3,'X'),true);
    }

    @Test
    public void testCheckDiagWin5() {

    }

    @Test
    public void testCheckDiagWin6() {

    }

    @Test
    public void testCheckDiagWin7() {

    }

    @Test
    public void testCheckDiagWin8() {

    }


    // CHECKTIE TESTS (4)

    @Test
    public void testCheckTie_notie() {

        IGameBoard gb = factory.makeStack(4,6,3);
        char[][] egb = makeExpectedBoard(4,6);

        gb.placeToken('X',1);
        egb[0][1] = 'X';
        gb.placeToken('O',2);
        egb[0][2] = 'O';
        gb.placeToken('X',1);
        egb[1][1] = 'X';
        gb.placeToken('O',1);
        egb[2][1] = 'O';
        gb.placeToken('X',2);
        egb[1][2] = 'X';
        gb.placeToken('O',3);
        egb[0][3] = 'O';
        gb.placeToken('X',3);
        egb[1][3] = 'X';

        assertEquals(gb.checkTie(),false);
    }

    @Test
    public void testCheckTie_fullboard() {

        IGameBoard gb = factory.makeStack(4,6,3);
        char[][] egb = makeExpectedBoard(4,6);

        gb.placeToken('O',0);
        egb[0][0] = 'O';
        gb.placeToken('O',1);
        egb[0][1] = 'O';
        gb.placeToken('O',2);
        egb[0][2] = 'O';

        gb.placeToken('O',0);
        egb[1][0] = 'O';
        gb.placeToken('O',1);
        egb[1][1] = 'O';
        gb.placeToken('O',2);
        egb[1][2] = 'O';

        gb.placeToken('O',0);
        egb[2][0] = 'O';
        gb.placeToken('O',1);
        egb[2][1] = 'O';
        gb.placeToken('O',2);
        egb[2][2] = 'O';

        gb.placeToken('X',0);
        egb[3][0] = 'X';
        gb.placeToken('X',1);
        egb[3][1] = 'X';
        gb.placeToken('X',2);
        egb[3][2] = 'X';

        gb.placeToken('O',3);
        egb[0][3] = 'O';
        gb.placeToken('O',4);
        egb[0][4] = 'O';
        gb.placeToken('O',5);
        egb[0][5] = 'O';

        gb.placeToken('O',3);
        egb[1][3] = 'O';
        gb.placeToken('O',4);
        egb[1][4] = 'O';
        gb.placeToken('O',5);
        egb[1][5] = 'O';

        gb.placeToken('O',3);
        egb[2][3] = 'O';
        gb.placeToken('O',4);
        egb[2][4] = 'O';
        gb.placeToken('O',5);
        egb[2][5] = 'O';

        gb.placeToken('X',3);
        egb[3][3] = 'X';
        gb.placeToken('X',4);
        egb[3][4] = 'X';
        gb.placeToken('X',5);
        egb[3][5] = 'X';

        assertEquals(gb.checkTie(),true);
    }

    @Test
    public void testCheckTie_onecolfull() {

        IGameBoard gb = factory.makeStack(4,6,3);
        char[][] egb = makeExpectedBoard(4,6);

        gb.placeToken('X',5);
        egb[0][5] = 'X';
        gb.placeToken('O',5);
        egb[1][5] = 'O';
        gb.placeToken('O',5);
        egb[2][5] = 'O';
        gb.placeToken('O',5);
        egb[3][5] = 'O';

        assertEquals(gb.checkTie(),false);
    }

    @Test
    public void testCheckTie_onerowfull() {

        IGameBoard gb = factory.makeStack(4,6,3);
        char[][] egb = makeExpectedBoard(4,6);

        gb.placeToken('X',0);
        egb[0][0] = 'X';
        gb.placeToken('X',1);
        egb[0][1] = 'X';
        gb.placeToken('X',2);
        egb[0][2] = 'X';
        gb.placeToken('X',3);
        egb[0][3] = 'X';
        gb.placeToken('X',4);
        egb[0][4] = 'X';
        gb.placeToken('X',5);
        egb[0][5] = 'X';

        assertEquals(gb.checkTie(),false);
    }


    // WHATSATPOS TESTS (7)

    @Test
    public void testWhatsAtPos_origin() {

        IGameBoard gb = factory.makeStack(4,6,3);
        char[][] egb = makeExpectedBoard(4,6);

        gb.placeToken('X',0);
        egb[0][0] = 'X';

        assertEquals(gb.toString(),expectedString(egb));
        assertEquals(gb.whatsAtPos(0,0),egb[0][0]);
    }

    @Test
    public void testWhatsAtPos_2ndrow() {

        IGameBoard gb = factory.makeStack(4,6,3);
        char[][] egb = makeExpectedBoard(4,6);

        gb.placeToken('X',0);
        egb[0][0] = 'X';
        gb.placeToken('O',0);
        egb[1][0] = 'O';

        assertEquals(gb.toString(),expectedString(egb));
        assertEquals(gb.whatsAtPos(0,1),egb[0][1]);
    }

    @Test
    public void testWhatsAtPos_top() {

        IGameBoard gb = factory.makeStack(4,6,3);
        char[][] egb = makeExpectedBoard(4,6);

        gb.placeToken('X',0);
        egb[0][0] = 'X';
        gb.placeToken('X',0);
        egb[1][0] = 'X';
        gb.placeToken('X',0);
        egb[2][0] = 'X';
        gb.placeToken('X',0);
        egb[3][0] = 'X';

        assertEquals(gb.toString(),expectedString(egb));
        assertEquals(gb.whatsAtPos(0,3),egb[0][3]);
    }

    @Test
    public void testWhatsAtPos_3rdrow() {

        IGameBoard gb = factory.makeStack(4,6,3);
        char[][] egb = makeExpectedBoard(4,6);

        gb.placeToken('X',0);
        egb[0][0] = 'X';
        gb.placeToken('X',0);
        egb[1][0] = 'X';
        gb.placeToken('X',0);
        egb[2][0] = 'X';

        assertEquals(gb.toString(),expectedString(egb));
        assertEquals(gb.whatsAtPos(0,2),egb[0][2]);
    }

    @Test
    public void testWhatsAtPos_middle() {

        IGameBoard gb = factory.makeStack(4,6,3);
        char[][] egb = makeExpectedBoard(4,6);

        gb.placeToken('X',3);
        egb[0][3] = 'X';
        gb.placeToken('X',3);
        egb[1][3] = 'X';
        gb.placeToken('X',3);
        egb[2][3] = 'X';

        assertEquals(gb.toString(),expectedString(egb));
        assertEquals(gb.whatsAtPos(2,3),egb[2][3]);
    }

    @Test
    public void testWhatsAtPos_topmiddle() {

        IGameBoard gb = factory.makeStack(4,6,3);
        char[][] egb = makeExpectedBoard(4,6);

        gb.placeToken('X',3);
        egb[0][3] = 'X';
        gb.placeToken('X',3);
        egb[1][3] = 'X';
        gb.placeToken('X',3);
        egb[2][3] = 'X';
        gb.placeToken('X',3);
        egb[3][3] = 'X';

        assertEquals(gb.toString(),expectedString(egb));
        assertEquals(gb.whatsAtPos(3,3),egb[3][3]);
    }

    @Test
    public void testWhatsAtPos_topcorner() {

        IGameBoard gb = factory.makeStack(4,6,3);
        char[][] egb = makeExpectedBoard(4,6);

        gb.placeToken('X',5);
        egb[0][5] = 'X';
        gb.placeToken('X',5);
        egb[1][5] = 'X';
        gb.placeToken('X',5);
        egb[2][5] = 'X';
        gb.placeToken('X',5);
        egb[3][5] = 'X';

        assertEquals(gb.toString(),expectedString(egb));
        assertEquals(gb.whatsAtPos(3,5),egb[3][5]);
    }


    // PLACETOKEN TESTS (5)

    @Test
    public void testPlaceToken_origin() {

        IGameBoard gb = factory.makeStack(4,6,3);
        char[][] egb = makeExpectedBoard(4,6);

        gb.placeToken('X',0);
        egb[0][0] = 'X';

        assertEquals(gb.toString(),expectedString(egb));
    }

    @Test
    public void testPlaceToken_opencolumn() {

        IGameBoard gb = factory.makeStack(4,6,3);
        char[][] egb = makeExpectedBoard(4,6);

        gb.placeToken('X',2);
        egb[0][2] = 'X';

        assertEquals(gb.toString(),expectedString(egb));
    }

    @Test
    public void testPlaceToken_difchars() {

        IGameBoard gb = factory.makeStack(4,6,3);
        char[][] egb = makeExpectedBoard(4,6);

        gb.placeToken('X',2);
        egb[0][2] = 'X';
        gb.placeToken('O',2);
        egb[1][2] = 'O';

        assertEquals(gb.toString(),expectedString(egb));
    }

    @Test
    public void testPlaceToken_toprow() {

        IGameBoard gb = factory.makeStack(4,6,3);
        char[][] egb = makeExpectedBoard(4,6);

        gb.placeToken('X',2);
        egb[0][2] = 'X';
        gb.placeToken('O',2);
        egb[1][2] = 'O';
        gb.placeToken('X',2);
        egb[2][2] = 'X';
        gb.placeToken('O',2);
        egb[3][2] = 'O';

        assertEquals(gb.toString(),expectedString(egb));
    }

    @Test
    public void testPlaceToken5_topcorner() {

        IGameBoard gb = factory.makeStack(4,6,3);
        char[][] egb = makeExpectedBoard(4,6);

        gb.placeToken('X',5);
        egb[0][5] = 'X';
        gb.placeToken('X',5);
        egb[1][5] = 'X';
        gb.placeToken('X',5);
        egb[2][5] = 'X';
        gb.placeToken('X',5);
        egb[3][5] = 'X';

        assertEquals(gb.toString(),expectedString(egb));
    }
}
