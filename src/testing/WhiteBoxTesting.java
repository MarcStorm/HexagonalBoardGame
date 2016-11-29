package testing;
import java.util.*;
import com.company.BoardPiece;

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


}
