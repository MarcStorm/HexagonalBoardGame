package testing;

import com.company.BoardPiece;

/**
 * Created by gideonpotok on 11/26/16.
 */
public class WhiteBoxTesting {
    Object o = null;
    BoardPiece[] board;
    @org.junit.Before
    public void setUp(){
        board = new BoardPiece[110];


    }
    @org.junit.After
    public void tearDown(){

    }
    @org.junit.Test
    public void test1(){
        o = null;
        org.junit.Assert.assertNull("sorry was null", o);
    }
    private com.company.Louis oneAwayFromWinHumanCantBlock(){
        for(int i = 0; i < 109; i++){
            board[i] = BoardPiece.EMPTY;
        }
        board[100] = BoardPiece.BLUE;
        board[109] = BoardPiece.BLUE;
        board[0] = BoardPiece.BLUE;
        board[30] = BoardPiece.BLUE;

        board[15] = BoardPiece.GOLD;
        board[25] = BoardPiece.GOLD;
        board[35] = BoardPiece.GOLD;
        board[53] = BoardPiece.GOLD;
        return new com.company.Louis(board, 0,
                BoardPiece.GOLD, BoardPiece.BLUE );
    }
    @org.junit.Test
    public void testUtility_WinsOneMoveAway(){
        com.company.Louis computer =oneAwayFromWinHumanCantBlock();
        computer.decideTurn();
        org.junit.Assert.assertEquals(9000, computer.utilityProfile(10, true));
    }
    @org.junit.Test
    public void testChoiceFrom_WinsOneMoveAway(){
        //note: this has to be changed once it returns multiple answers
        //will be like for all latest turns if [0] is 53 all after are 53 too right?
        com.company.Louis computer =oneAwayFromWinHumanCantBlock();
        Integer[]  latestTurn  = computer.getTurn();
        org.junit.Assert.assertEquals(new Integer(53), latestTurn[0]);
    }
    @org.junit.Test
    public void testChoiceTo_WinsOneMoveAway(){
        //note: this has to be changed once it returns multiple answers
        //will be like for all latest turns if [0] is 53 all after are 53 too right?
        com.company.Louis computer =oneAwayFromWinHumanCantBlock();
        Integer[]  latestTurn  = computer.getTurn();
        org.junit.Assert.assertTrue(new Integer(55) == latestTurn[1]);
    }
}
