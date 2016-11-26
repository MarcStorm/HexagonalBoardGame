package com.company;

import java.util.AbstractMap;
import java.util.ArrayList;

/**
 * Created by gideonpotok on 11/5/16.
 */
//to do make sure this is min player
public class Amalia extends Computer {
    BoardPiece[] board;
    public  boolean isTerminalNode(){
        //System.out.println("Call to 9");
        //System.out.println("my piece is " + me);
        return true;
    }
    public Amalia(BoardPiece[] board, Integer numTurns, BoardPiece me, BoardPiece adversery){
        super(board,numTurns,me,adversery);
        this.board = board;
        //System.out.println("Call to 10");
        //System.out.println("my piece is " + me);

    }
    public Amalia(BoardPiece me, BoardPiece adversery,
                  AbstractMap.SimpleEntry<Integer, Integer> change,
                  ArrayList<Integer> positionsMe, ArrayList<Integer> positionsAdversery, AbstractMap.SimpleEntry<Integer, Integer> heritage) {
        //System.out.println("my piece is " + me);
        this.me=me;
        this.adversery=adversery;
        //System.out.print("Call to 11: ");
        //children = new ArrayList<Computer>();
        this.computer = new ArrayList<Integer>(positionsMe);//(level %2 == 0)? positionsMe:positionsAdversery;
        this.human = new ArrayList<Integer>(positionsAdversery);
        //System.out.println("Gideon");
        //System.out.println(computer);
        //System.out.println("VS");
        //System.out.println("1"+human);
        //System.out.println("("+change + ")");

        this.human.remove(change.getKey()); //THIS IS CORRECT
        //System.out.println("2"+human);
        this.human.add(positionsMe.size() - 1, change.getValue()); //THIS IS CORRECT
        //System.out.println("3"+human);
        //System.out.println();
        //getPlacesToGo(computer);
        this.originalChange = heritage;
        //System.out.println("heritage = " + heritage);
    }
    public int utilityProfile(int depth){
        //System.out.println("my piece is " + me);
        //System.out.println("Call to 13");
        util = this.originalChange.getKey() + this.originalChange.getValue();
        //System.out.println("this.originalChange.getKey() is" + this.originalChange.getKey());
        //System.out.println("this.originalChange.getValue() = " + this.originalChange.getValue());
        if(this.opponentWon()) {
            util = depth * 1000;
            //System.out.println("WE WIN" + this.toString());
            //System.out.println();//System.out.println();//System.out.println();//System.out.println();//System.out.println();//System.out.println();//System.out.println();//System.out.println();//System.out.println();//System.out.println();//System.out.println();//System.out.println();//System.out.println();//System.out.println();//System.out.println();//System.out.println();
        } else {
            util = Main.counter;

            Main.counter++;
            if(Main.counter == 999){
                Main.counter=1;
                System.out.println("counter is 999, calling computer was " + this.toString());
            }

        }
        System.out.println("counter is " + Main.counter + ", utility is  " + util);
        if(Main.counter %39 == 0) {

            System.out.println("calling computer was " + this.toString());
        }
        return util;
    }
    boolean decideTurn(){
        return false;
    }
    public void makeTree(int level) {
        //System.out.println("my piece is " + me);
        //System.out.println("Call to 16");

    }


}

