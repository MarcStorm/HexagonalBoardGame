package testing;

import com.company.BoardPiece;

/**
 * Created by gideonpotok on 11/26/16.
 */
public class BlackBoxTesting {
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
    com.company.Louis getComputer(int blue1, int blue2, int blue3, int blue4,
                                  int gold1,int gold2,int gold3, int gold4){
        for(int i = 0; i < 109; i++){
            board[i] = BoardPiece.EMPTY;
        }
        board[blue1] = BoardPiece.BLUE;
        board[blue2] = BoardPiece.BLUE;
        board[blue3] = BoardPiece.BLUE;
        board[blue4] = BoardPiece.BLUE;

        board[gold1] = BoardPiece.GOLD;
        board[gold2] = BoardPiece.GOLD;
        board[gold3] = BoardPiece.GOLD;
        board[gold4] = BoardPiece.GOLD;
        return new com.company.Louis(board, 0,
                BoardPiece.GOLD, BoardPiece.BLUE );
    }
    @org.junit.Test
    public void testUtility_WinsOneMoveAway(){
        com.company.Louis computer = getComputer(100,109,0,30,  15,25,35,53);
        computer.decideTurn();
        int util = computer.alphaBetaPruning(10,Integer.MIN_VALUE,Integer.MAX_VALUE,true);
        org.junit.Assert.assertEquals(9000, util);

        org.junit.Assert.assertEquals(9000, computer.utilityProfile(10, true));
    }
    @org.junit.Test
    public void testChoiceFrom_WinsOneMoveAway(){
        //note: this has to be changed once it returns multiple answers
        //will be like for all latest turns if [0] is 53 all after are 53 too right?
        com.company.Louis computer = getComputer(100,109,0,30,  15,25,35,53);
        computer.decideTurn();
        Integer[]  latestTurn  = computer.getTurn();
        org.junit.Assert.assertEquals(new Integer(53), latestTurn[0]);
    }
    @org.junit.Test
    public void testChoiceTo_WinsOneMoveAway(){
        //note: this has to be changed once it returns multiple answers
        //will be like for all latest turns if [0] is 53 all after are 53 too right?
        com.company.Louis computer = getComputer(100,109,0,30,  15,25,35,53);
        computer.decideTurn();
        Integer[]  latestTurn  = computer.getTurn();
        org.junit.Assert.assertEquals(new Integer(55), latestTurn[1]);
    }
    /*@org.junit.Test
    public void testChoice_LosesOneMoveAwayTwoRecourse() {
        com.company.Louis computer = getComputer(5, 15, 95, 107, 43, 9, 100, 0);
        computer.decideTurn();
        //computer.alphaBetaPruning(10,Integer.MIN_VALUE,Integer.MAX_VALUE,true);
        //TODO: this is for single value decide turn, after that, test get turn, once original change is a43 -> 45 or 43-> 25 test
        // TODO: ...that all redefinitions of original change is one of those two
        org.junit.Assert.assertEquals(new Integer(43), computer.originalChange.getKey());
        org.junit.Assert.assertTrue(
                (new Integer(45)).equals(computer.originalChange.getValue())
                        || (new Integer(25)).equals(computer.originalChange.getValue())
        );
    }*/

    @org.junit.Test
    public void testChoice_LosesOneMoveAwayOneRecourse() {
        com.company.Louis computer = getComputer(5, 25, 95, 107, 43, 9, 100, 0);
        computer.decideTurn();

        //TODO: this is for single value decide turn, after that, test get turn, once original change is a43 -> 45 or 43-> 25 test
        // TODO: ...that all redefinitions of original change is one of those two
        org.junit.Assert.assertEquals(new Integer(43), computer.originalChange.getKey());
        org.junit.Assert.assertEquals(new Integer(45), computer.originalChange.getValue());
    }

    /* Test where the brick have to options of where to move in order to win.
     * The test will check that the brick is actually move to one of those to locations. */
    @org.junit.Test
    public void testUtility_WinsOneMoveAwayTwoOptions(){
        com.company.Louis computer = getComputer(100,109,0,30,  15,25,35,54);
        computer.decideTurn();
        int util = computer.alphaBetaPruning(10,Integer.MIN_VALUE,Integer.MAX_VALUE,true);
        org.junit.Assert.assertEquals(9000, util);

        org.junit.Assert.assertEquals(9000, computer.utilityProfile(10, true));
    }
    @org.junit.Test
    public void testChoiceFrom_WinsOneMoveAwayTwoOptions(){
        com.company.Louis computer = getComputer(100,109,0,30,  15,25,35,54);
        computer.decideTurn();
        Integer[]  latestTurn  = computer.getTurn();
        org.junit.Assert.assertEquals(new Integer(54), latestTurn[0]);
    }
    @org.junit.Test
    public void testChoiceTo_WinsOneMoveAwayTwoOptions(){
        com.company.Louis computer = getComputer(100,109,0,30,  15,25,35,54);
        computer.decideTurn();
        Integer[]  latestTurn  = computer.getTurn();
        if (latestTurn[1]==45) {
            org.junit.Assert.assertEquals(new Integer(45), latestTurn[1]);
        } else {
            org.junit.Assert.assertEquals(new Integer(55), latestTurn[1]);
        }
    }

    /* Test where the brick have to options of where to move in order to win.
     * The test will check that the brick is actually move to one of those to locations. */
    /*@org.junit.Test
    public void testUtility_WinsTwoMovesAwayOneOption(){
        com.company.Louis computer = getComputer(100,109,0,30,  15,25,35,51);
        computer.decideTurn();
        int util = computer.alphaBetaPruning(10,Integer.MIN_VALUE,Integer.MAX_VALUE,true);
        org.junit.Assert.assertEquals(9000, util);

        org.junit.Assert.assertEquals(9000, computer.utilityProfile(10, true));
    }*/
    @org.junit.Test
    public void testChoiceFrom_WinsTwoMovesAwayOneOption(){
        com.company.Louis computer = getComputer(100,109,0,30,  15,25,35,58);
        computer.decideTurn();
        Integer[]  latestTurn  = computer.getTurn();
        org.junit.Assert.assertEquals(new Integer(58), latestTurn[0]);
    }
    @org.junit.Test
    public void testChoiceTo_WinsTwoMovesAwayOneOption(){
        com.company.Louis computer = getComputer(100,109,0,30,  15,25,35,58);
        //First move towards victory.
        computer.decideTurn();
        Integer[]  latestTurn  = computer.getTurn();
        org.junit.Assert.assertEquals(new Integer(56), latestTurn[1]);
        /*//Second move towards victory.
        computer.decideTurn();
        latestTurn  = computer.getTurn();
        org.junit.Assert.assertEquals(new Integer(55), latestTurn[1]);*/
    }
}
