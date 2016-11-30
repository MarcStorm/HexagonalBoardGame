package com.company;

import java.util.*;

/**
 * @Author Anu Challa (achalla@terpmail.umd.edu)
 * @Author Gideon Potok (gideon.potok@gmail.com)
 * @Author Marc Storm Larsen (mslarsen1992@gmail.com)
 *
 * I pledge on my honor that I have not given or received any unauthorized assistance on this project.
 */
public class Louis extends Computer {

    public Louis(BoardPiece[] board, Integer numTurns, BoardPiece me, BoardPiece adversery) {
        super(board,numTurns,me,adversery);//System.out.println("Call to 3");//System.out.println("mypiece is " + me);
        this.originalChange=null;
        getPlacesToGo(computer); // POSSIBLE MOVES I MIGHT MAKE

    }

    public Louis(BoardPiece me, BoardPiece adversery,
                 AbstractMap.SimpleEntry<Integer, Integer> change,
                 ArrayList<Integer> positionsMe, ArrayList<Integer> positionsAdversery) {
        //System.out.println("Call to 2");
        //System.out.println("mypiece is " + me);

        this.me=me;
        this.adversery=adversery;
        children = new ArrayList<Computer>();
        this.computer = new ArrayList<Integer>();//(level %2 == 0)? positionsMe:positionsAdversery;
        for(Integer i : positionsMe){
            computer.add(i);
        }
        this.human = new ArrayList<Integer>();
        for(Integer i : positionsAdversery){
            human.add(i);
        }
        this.children = new ArrayList<Computer>();
        //System.out.println("Louis");
        //System.out.println(computer);
        //System.out.println("VS");
        //System.out.println("1"+positionsAdversery);
        //System.out.println("("+change + ")");
        this.human.remove(change.getKey()); //THIS IS CORRECT
        //System.out.println("2"+positionsAdversery);
        this.human.add(change.getValue()); //THIS IS CORRECT
        //System.out.println("3"+positionsAdversery);
        //System.out.println();
        getPlacesToGo(computer);

    }

    public Louis(BoardPiece me, BoardPiece adversery,
                 AbstractMap.SimpleEntry<Integer, Integer> change,
                 ArrayList<Integer> positionsMe, ArrayList<Integer> positionsAdversery,
                 AbstractMap.SimpleEntry<Integer, Integer> originalChange) {
        this(me,adversery,change,positionsMe,positionsAdversery);
        //System.out.println("Call to 3");
        //System.out.println("mypiece is " + me);
        this.originalChange = originalChange;
    }

    public boolean wins(){
        if(wins == null){
            wins = super.wins();
        }
        return wins;
    }

    public boolean decideTurn() {
        makeTree(Main.depth, true);//has to be ODD//System.out.println("\nTREE: " + this.toString());
        Computer.movesAndUtils = new HashMap<AbstractMap.SimpleEntry<Integer,Integer>, Integer>();
        //maybe somehow make it able to use the one from last turn
        //NOTE DO NOT CHANGE LEVEL from 10 unless makeTree increases in which case increase 10, too
        //It does not correspond to level exactly. And it gets negative if you have it the
        //the same number of levels as the tree.
        this.alphaBetaPruning(Main.depth+Main.depthPlus,Integer.MIN_VALUE,Integer.MAX_VALUE,true);
        int max = Integer.MIN_VALUE;
        for(Computer c : children){
            if(c.util > max){
                max = c.util;
                this.originalChange = c.originalChange;

            }
        }
        System.out.println(this.originalChange.getKey() + " " + this.originalChange.getValue());

        from=originalChange.getKey();
        to=originalChange.getValue();
        return true;
    }

    public  boolean isTerminalNode(){
        return false;
    }

    public void makeTree(int level, boolean maximizing) {
        //System.out.println("Call to 5 ");
        //System.out.println("mypiece is " + me);
        children= new ArrayList<Computer>();
        AbstractMap.SimpleEntry<Integer, Integer> heritage = this.originalChange;
        if (level > 0) {

            for (AbstractMap.SimpleEntry<Integer, Integer> each : this.destinations) {
                //After roots turn, EACH change is made to the real computer

                if(this.originalChange == null){
                    heritage=each;
                }
                Computer l = new Louis(adversery ,me , each, human, computer, heritage); //notice THE SWITCH

                if(l.opponentWon()){
                    l = new Gideon(adversery,me,each,human,computer,heritage);
                    children.clear();
                    children.add(l);
                    break;
                } else {
                    children.add(l);
                }

                if(!maximizing && !Main.lookAtTheirTurns){
                    break;
                }

            }
        } else {
            for (AbstractMap.SimpleEntry<Integer, Integer> each : this.destinations) {
                //After roots turn, EACH change is made to the real computer
                AbstractMap.SimpleEntry<Integer, Integer> local  = new AbstractMap.SimpleEntry<Integer, Integer>(each.getKey(),each.getValue());
                if(this.originalChange == null){
                    heritage=local;
                }
                Gideon g = new Gideon(adversery ,me , local, human, computer, heritage); //notice THE SWITCH
                children.add(g);
            }
        }

        for(Computer lg : children){
            if(!lg.isTerminalNode())
                lg.makeTree(level - 1, !maximizing );
        }

    }

    private Boolean wins = null;

    public int utilityProfile(int depth,boolean maximizing){
        String s = "";
        if(!maximizing){
            // if MINimizing
           if(this.opponentWon()){
               s = "Louis: "+ "min-imizing,opponent wins: " + this.toString();
               util = depth*1000;
               return util;
           }
           if(this.wins()){
               s = "Louis: "+ "min-imizing,wins: " + this.toString();
               util = depth* (-1000);
               return util;
           }
        }else {
            //If MAXimizing
            if (this.opponentWon()) {
                s = "Louis: "+ "max-imizing,opponent wins: " + this.toString();
                util = depth * (-1000);
                return util;
            }
            if (this.wins()) {
                s ="Louis: "+  "max-imizing,wins: " + this.toString();
                util = depth * 1000;
                return util;
            }
        }
        if(!s.equals(""))
            System.out.println(s);
        return util;
    }

}
