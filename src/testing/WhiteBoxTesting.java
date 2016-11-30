package testing;
import java.util.*;
import com.company.BoardPiece;
import com.company.Main;

/**
 * Created by gideonpotok on 11/26/16.
 */
public class WhiteBoxTesting {
    Object o = null;
    BoardPiece[] board;

    @org.junit.Before
    public void setUp() {
        board = new BoardPiece[110];


    }

    @org.junit.After
    public void tearDown() {

    }


    @org.junit.Test
    public void testAlphaBetaReturnVal_akaUtil_WinsOneMoveAway() {
        com.company.Louis computer = getComputer(100, 109, 0, 30, 15, 25, 35, 53);
        computer.makeTree(0);

        for (com.company.Computer child: computer.children){
            org.junit.Assert.assertTrue(child.isTerminalNode());
        }
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
    public ArrayList<com.company.Computer>  test15makeTree_WinsOneMoveAwayTwoOptions() {

        com.company.Louis computer = getComputer(100, 109, 0, 30, 15, 25, 35, 58);
        com.company.Computer winner = null;
        ArrayList<com.company.Computer> winningChildren = new ArrayList<com.company.Computer>();
        for (com.company.Computer child : computer.children) {
            if (child.originalChange.getKey().equals(new Integer(58))) {
                if (child.human.contains(56) || child.human.contains(76) || child.human.contains(57) || child.human.contains(67)) {
                    winningChildren.add(child);
                }
            }

        }
        ArrayList<com.company.Computer> winningFinalists = new ArrayList<com.company.Computer>();
        for (com.company.Computer w : winningChildren) {
            for (com.company.Computer gc : w.children) {
                for (com.company.Computer c : gc.children) {
                    int count = 0;
                    for (Integer i : c.human) {
                        count += (i % 5);
                    }
                    if (count == 0) {
                        winningFinalists.add(c);
                    }
                }
            }
        }
        return winningFinalists;
    }
    @org.junit.Test
    public void test15makeTreeSkipTheirDecisionSpace_WinsOneMoveAwayTwoOptions(){
        Main.lookAtTheirTurns = false;
        Main.depth = 2;

        ArrayList<com.company.Computer> winningFinalists =test15makeTree_WinsOneMoveAwayTwoOptions();
        for(com.company.Computer c: winningFinalists){
            org.junit.Assert.assertTrue(c.isTerminalNode());
        }
        for(com.company.Computer c: winningFinalists){
            if(!c.opponentWon()){
                System.out.println("");
            }
            //org.junit.Assert.assertTrue();
            //org.junit.Assert.assertEquals(Main.depthPlus*1000, c.utilityProfile() );
        }
        org.junit.Assert.assertEquals(8, winningFinalists.size());
    }
    @org.junit.Test
    public void test15makeTree2_WinsOneMoveAwayTwoOptions(){
        com.company.Main.lookAtTheirTurns = true;
        Main.depth = 2;

        ArrayList<com.company.Computer> winningFinalists =test15makeTree_WinsOneMoveAwayTwoOptions();
        for(com.company.Computer c: winningFinalists){
            org.junit.Assert.assertTrue(c.isTerminalNode());
        }
        for(com.company.Computer c: winningFinalists){
            if(!c.opponentWon()){
                System.out.println("");
            }
            //org.junit.Assert.assertTrue();
            //org.junit.Assert.assertEquals(Main.depthPlus*1000, c.utilityProfile() );
        }
        org.junit.Assert.assertEquals(176, winningFinalists.size());
    }

    @org.junit.Test
    public void test16makeTreeSkipTheirDecisionSpace_WinsOneMoveAwayTwoOptions(){
        Main.lookAtTheirTurns = false;
        Main.depth = 3;

        ArrayList<com.company.Computer> winningFinalists =test15makeTree_WinsOneMoveAwayTwoOptions();
        for(com.company.Computer c: winningFinalists){
            org.junit.Assert.assertTrue(!c.isTerminalNode() || c.opponentWon() );
        }
        for(com.company.Computer c: winningFinalists){
            if(!c.opponentWon()){
                System.out.println("");
            }
            //org.junit.Assert.assertTrue();
            //org.junit.Assert.assertEquals(Main.depthPlus*1000, c.utilityProfile() );
        }
        org.junit.Assert.assertEquals(8, winningFinalists.size());
    }
    @org.junit.Test
    public void test16makeTree2_WinsOneMoveAwayTwoOptions(){
        com.company.Main.lookAtTheirTurns = true;
        Main.depth = 3;

        ArrayList<com.company.Computer> winningFinalists =test15makeTree_WinsOneMoveAwayTwoOptions();
        for(com.company.Computer c: winningFinalists){
            org.junit.Assert.assertTrue(!c.isTerminalNode() || c.opponentWon());
        }
        for(com.company.Computer c: winningFinalists){
            if(!c.opponentWon()){
                System.out.println("");
            }
            //org.junit.Assert.assertTrue();
            //org.junit.Assert.assertEquals(Main.depthPlus*1000, c.utilityProfile() );
        }
        org.junit.Assert.assertEquals(176, winningFinalists.size());
    }





    @org.junit.Test
    public void test15ChoiceFrom_WinsTwoMovesAwayOneOption(){
        com.company.Louis computer = getComputer(100,109,0,30,  15,25,35,58);

        Integer[]  latestTurn  = computer.getTurn();
        org.junit.Assert.assertTrue(58 == latestTurn[0]);
        org.junit.Assert.assertEquals(new Integer(58), latestTurn[0]);
    }
    @org.junit.Test
    public void test15ChoiceTo_WinsTwoMovesAwayOneOption(){
        com.company.Louis computer = getComputer(100,109,0,30,  15,25,35,58);
        //First move towards victory.

        Integer[]  latestTurn  = computer.getTurn();
        org.junit.Assert.assertEquals(new Integer(56), latestTurn[1]);

        /*//Second move towards victory.
        computer.decideTurn();
        latestTurn  = computer.getTurn();
        org.junit.Assert.assertEquals(new Integer(55), latestTurn[1]);*/
    }




    @org.junit.Test
    public void test15levelOneOfTree_WinsOneMoveAwayTwoOptions(){
        com.company.Louis computer = getComputer(100,109,0,30,  15,25,35,58);
        com.company.Computer winner = null;
        ArrayList<com.company.Computer> winningChildren= new ArrayList<com.company.Computer>();
        for(com.company.Computer child: computer.children){
            if(child.originalChange.getKey().equals(new Integer(58))){
                if(child.human.contains(56) || child.human.contains(76) || child.human.contains(57) || child.human.contains(67)){
                    winningChildren.add(child);
                }
            }

        }
        //System.out.println("winning children was " + winningChildren.toString());
        org.junit.Assert.assertEquals(4, winningChildren.size());


    }


}
