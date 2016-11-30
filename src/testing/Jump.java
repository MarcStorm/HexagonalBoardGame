package testing;

import com.company.BoardPiece;
import com.company.Computer;
import com.company.Main;

import java.util.ArrayList;

/**
 * Created by gideonpotok on 11/26/16.
 */
public class Jump {
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
    public void testChoice_JumpAwayWin() {
        Main.lookAtTheirTurns = true;
        Main.depth = 3;

        com.company.Louis computer = getComputer(86, 87, 92, 63, 72, 27, 36, 45);
        Integer[] latestTurn = computer.getTurn();
        org.junit.Assert.assertEquals(new Integer(72), latestTurn[0]);
        org.junit.Assert.assertEquals(new Integer(54), latestTurn[1]);
    }
    @org.junit.Test
    public void test1() {
        Main.lookAtTheirTurns = true;
        Main.depth = 2;
        ArrayList<Integer> a = new ArrayList<Integer>();
        a.add(54);

        ArrayList<com.company.Computer> winningFinalists = listOfwinningChildren(86, 87, 92, 63, 72, 27, 36, 45, a);
        org.junit.Assert.assertFalse(winningFinalists.isEmpty());
    }
    public ArrayList<com.company.Computer> listOfwinningChildren(int blue1, int blue2, int blue3, int blue4,
                                                                                    int gold1, int gold2, int gold3, int gold4, ArrayList<Integer> winningTo) {

        com.company.Louis computer = getComputer(blue1, blue2, blue3, blue4, gold1, gold2, gold3, gold4);
        com.company.Computer winner = null;
        ArrayList<com.company.Computer> winningChildren = new ArrayList<com.company.Computer>();
        for (com.company.Computer child : computer.children) {
            if (child.originalChange.getKey().equals(new Integer(gold1))) {
                //winningChildren.add(child);
                for(Integer toGo : winningTo){
                    if (child.human.contains(toGo)){
                        winningChildren.add(child);
                    }
                }


            }
        }
        return winningChildren;
    }
    public ArrayList<com.company.Computer> test15makeTree_WinsOneMoveAwayTwoOptions(int blue1, int blue2, int blue3, int blue4,
                                                                                    int gold1, int gold2, int gold3, int gold4, ArrayList<Integer> winningTo) {
        com.company.Louis computer = getComputer(blue1, blue2, blue3, blue4, gold1, gold2, gold3, gold4);
        com.company.Computer winner = null;
        ArrayList<com.company.Computer> winningChildren = new ArrayList<com.company.Computer>();
        for (com.company.Computer child : computer.children) {
            if (child.originalChange.getKey().equals(new Integer(gold1))) {
                for(Integer toGo : winningTo){
                    if (child.human.contains(toGo)){
                        winningChildren.add(child);
                    }
                }


            }

        }
        ArrayList<com.company.Computer> winningFinalists = new ArrayList<com.company.Computer>();
        for (com.company.Computer w : winningChildren) {
            for (com.company.Computer gc : w.children) {
                for (com.company.Computer c : gc.children) {

                    if (c.opponentWon()) {
                        winningFinalists.add(c);
                    }
                }
            }
        }
        return winningFinalists;
    }

    com.company.Louis getComputer(int blue1, int blue2, int blue3, int blue4,
                                  int gold1, int gold2, int gold3, int gold4) {
        for (int i = 0; i < 109; i++) {
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
        com.company.Louis l =  new com.company.Louis(board, 100,
                BoardPiece.GOLD, BoardPiece.BLUE);
        l.decideTurn();
        return l;
    }


}