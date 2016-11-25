package com.company;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
public class Main {
    static int counter = 1;
    public static void main(String[] args) {
	// write your code here
        initialiseField();
        Hexgame.doMain();

        /*while(numTurns > 0){

            numTurns--;
        }*/
    }

    /*Section 1: stuff teammates should be aware they can toggle*/

    static PlayerType p2 = PlayerType.COMPUTER;
    private static Integer numTurns = 300;
    private static final boolean startBoardRandomPositions = false;


    private static Computer computerGenerator() {
        System.out.println("Call to Generator current turn is " + currentTurn);
        return new Louis // <- you might change which Computer (Anu, Marc, etc). No constructor signature standard, whatever params you need!
                (board,numTurns,
                (currentTurn == BoardPiece.BLUE)? BoardPiece.BLUE: BoardPiece.GOLD,
                (currentTurn == BoardPiece.BLUE)? BoardPiece.GOLD: BoardPiece.BLUE);
    }
    /*End Section 1*/


    /*Section 2: Should work, not yet tested*/
    private static boolean  startingPositionIsGivenAsTextInput = false;
    /*End section 2*/
    /*Section 2: soon to be toggleable*/


    /*Section 3: soon to be toggleable*/
    private static PlayerType p1 = PlayerType.HUMAN;
    private static final boolean readInInput = false; //
    /*End Section 3*/

    private static final int numberOfMarkers = 4;
    private static int playerToMakeMove;
    public static BoardPiece[] board;
    public static BoardPiece currentTurn = BoardPiece.BLUE;
    private static Scanner scan;
    private static Computer computer1 = null , computer2;

    private static Integer from = null;


    private static void initialiseField() {
        board = new BoardPiece[110];
        for(int i = 0; i < board.length; i++){
            board[i] = BoardPiece.EMPTY;
        }

        placeTokens();




    }

    private static void placeTokens() {

        boolean twoAwayStartBoard = true;
        if(startingPositionIsGivenAsTextInput){
            scan = new Scanner(System.in);
            String blueStart = scan.nextLine();
            String goldStart = scan.nextLine();
            currentTurn = scan.nextInt() == 0 ? BoardPiece.BLUE: BoardPiece.GOLD;
            for(String each: blueStart.split(" ")){
                board[Integer.parseInt(each)] = BoardPiece.BLUE;
            }
            for(String each: goldStart.split(" ")){
                board[Integer.parseInt(each)] = BoardPiece.GOLD;
            }
        }
        else if(startBoardRandomPositions) {
            Random r = new Random();
            ArrayList<Integer> arr = new ArrayList<Integer>();
            int x = 0;
            for (int i = 0; i < 4; i++) {
                x = r.nextInt(109);
                while (arr.contains(x)) {
                    x = r.nextInt(109);
                }
                arr.add(x);
                board[x] = BoardPiece.GOLD;
            }
            for (int i = 0; i < 4; i++) {
                x = r.nextInt(109);
                while (arr.contains(x)) {
                    x = r.nextInt(109);
                }
                arr.add(x);
                board[x] = BoardPiece.BLUE;
            }
        } else if(twoAwayStartBoard) {
            board[100] = BoardPiece.BLUE;
            board[109] = BoardPiece.BLUE;
            board[0] = BoardPiece.BLUE;
            board[30] = BoardPiece.BLUE;

            board[15] = BoardPiece.GOLD;
            board[25] = BoardPiece.GOLD;
            board[35] = BoardPiece.GOLD;
            //board[43] = BoardPiece.GOLD;
            board[67] = BoardPiece.GOLD;
        }else{
            board[0] = BoardPiece.GOLD;
            board[9] = BoardPiece.GOLD;
            board[103] = BoardPiece.GOLD;
            board[106] = BoardPiece.GOLD;

            board[100] = BoardPiece.BLUE;
            board[109] = BoardPiece.BLUE;
            board[3] = BoardPiece.BLUE;
            board[6] = BoardPiece.BLUE;
        }
    }

    /*
     * METHODS BELOW ARE USED FOR READING THE INPUT TO INITIALISE THE GAME.
     */

    private static void readInput() throws IOException {
      //Look in this file's history for an early implementation.
        //Deleted until it will be useful
    }



    private static boolean takeTurn(BoardPiece turn, Integer from, Integer to) {
        if(board[from] == turn) {
            if(board[to] == BoardPiece.EMPTY){
                if(withinRange(from, to)){
                    board[from] = BoardPiece.EMPTY;
                    board[to] = turn;
                    return true;
                }
            }
        }
        return false;

    }
    /*
    * Called by mouse listener as a response to human player
    * called by computer player
    * */
    public static Computer click(Integer integer) {


        //return null;
        if(from == null ){

            if(board[integer] == currentTurn){
                from = integer;
            }
        } else if (from == integer){
            from = null; //double click the same tile to deselect
        }else if (board[integer] == BoardPiece.EMPTY) {
            if(withinRange(from, integer)){
                board[integer] = board[from];
                board[from] = BoardPiece.EMPTY;
                from = null;
                currentTurn = (currentTurn == BoardPiece.BLUE)? BoardPiece.GOLD: BoardPiece.BLUE;
                numTurns--;

                return computerGenerator();
            } else {
                from = null;
            }
        }
        return null;

    }

    private void printInput(){
        //Look in this file's history for an early implementation.
        //Deleted until it will be useful
    }
    private static boolean withinRange(Integer from, Integer to) {
        if(to == from + 1 || to == from + 2 || to == from - 1 || to == from - 2 ) {
            if(to/10 == from /10)
                return true;
        } else if (to == from + 10 || to == from +20||to == from - 10 || to == from -20) {
            return true;
        } else if (to == from + 9 || to == from + 18||to == from - 9 || to == from - 18) {
            return true;
        }  else {
            return false;
        }
        return false;
    }

    private static void readPlayerPositions(BufferedReader bufferedReader) throws IOException {
        //Look in this file's history for an early implementation.
        //Deleted until it will be useful
    }
    public static void isItAComputersTurn() {
		/*
		 if(FourInARow.p2 == PlayerType.COMPUTER && p2 != null){
						Integer[]  latestTurn = new Integer[2];
						final long start = System.nanoTime();
						do {

							latestTurn = p2.getTurn();


						} while (System.nanoTime()-start < 1L*1000L*1000L*1000L);
					FourInARow.click(latestTurn[0]);
					p2 = FourInARow.click(latestTurn[1]);
					FourInARow.currentTurn = BoardPiece.BLUE;
		 */
		/*if(p1 == PlayerType.COMPUTER && currentTurn == BoardPiece.BLUE){
			computer1 = computerGenerator();

			Integer[]  latestTurn = null;
			final long start = System.nanoTime();
			do {
				latestTurn = computer1.getTurn();

			} while (System.nanoTime()-start < 5L*1000L*1000L*1000L);
			if(latestTurn != null) {
				click(latestTurn[0]);
				click(latestTurn[1]);
			}
    		numTurns--;

		}*/
        if(p2 == PlayerType.COMPUTER  && currentTurn == BoardPiece.GOLD){
            computer2 = computerGenerator();

            Integer[]  latestTurn = null;
            final long start = System.nanoTime();
            latestTurn = computer2.getTurn();
            /*
            do {
                latestTurn = computer2.getTurn();

            } while (System.nanoTime()-start < 5L*1000L*1000L*1000L);*/
            if(latestTurn != null) {
                click(latestTurn[0]);
                click(latestTurn[1]);
            }

            numTurns--;

        }

    }
}
