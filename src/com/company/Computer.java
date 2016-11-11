package com.company;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by gideonpotok on 11/5/16.
 */
public abstract class Computer {
    int from = 0, to = 0, numTurns = 0;
    BoardPiece[] board;
    Random r ;
    ArrayList<Integer> computer = new ArrayList<Integer>(),  human = new ArrayList<Integer>();
    ArrayList<ArrayList<Integer>> placesToGo;
    public Computer(BoardPiece[] board, Integer numTurns, BoardPiece me, BoardPiece adversery){
        this.board = board;
        this.numTurns=numTurns;
        this.r = new Random();


        for(int i = 0; i <= 109; i++){
            if(board[i] == me){
                computer.add(i);
            } else if (board[i] == adversery){
                human.add(i);
            }
        }
    }

    abstract boolean decideTurn();

    public Integer[] getTurn() {
        decideTurn();
        Integer[] nextTurn = new Integer[2];
        nextTurn[0] = from;
        nextTurn[1] = to;
        System.out.println(nextTurn[0] + " " +nextTurn[1]);
        return nextTurn;
    }
    boolean formsZLine(Integer one, Integer other) {
        if(one % 10 == other % 10){
            return true;
        }
        return false;
    }
    boolean formsXLine(Integer one, Integer other) {
        if (one%9 == other%9 && one != 0 && other != 0){
            return true;
        }
        return false;
    }
    boolean formsYLine(Integer one, Integer other) {
        if (one/10 == other/10){
            return true;
        }
        return false;
    }

    boolean between(Integer left, Integer mid, Integer right) {
        return (left < mid && mid < right ) || (left > mid && mid > right ) ;
    }
    boolean formsLine(Integer one, Integer other) {
        if(one % 10 == other % 10){
            //System.out.println("In forms line: one is " + one + ", other is " + other);
            //System.out.println("mod ten the same");
            return true;
        } else if (one/10 == other/10){
            //System.out.println("In forms line: one is " + one + ", other is " + other);
            //System.out.println("10 times same x plus single dig num the same");
            return true;
        } else if (one%9 == other%9 && one != 0 && other != 0){
            //System.out.println("In forms line: one is " + one + ", other is " + other);
            //System.out.println("mod nine the same");
            return true;
        }
        return false;
    }

    boolean withinRange(Integer from, Integer to) {

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
    /* Is their piece blocking my 4 in a row? */
    /*TO DO: this is a very temporary implementation change soon*/
    public boolean between(Integer theirPiece) {
        //eventually we can just ask if it is between the most extreme of computers pieces
        for(int i = 0; i < 4; i++){
            for(int j = i+1; j < 4; j++){
                if(between(this.computer.get(i), theirPiece, this.computer.get(j))){
                    return true;
                }
            }
        }
        return false;
    }

}

