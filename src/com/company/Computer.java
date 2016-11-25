package com.company;

import java.util.*;


/**
 * @Author Anu Challa (achalla@terpmail.umd.edu)
 * @Author Gideon Potok (gideon.potok@gmail.com)
 * @Author Marc Storm Larsen (mslarsen1992@gmail.com)
 *
 * I pledge on my honor that I have not given or received any unauthorized
 * assistance on this project.
 */

public abstract class Computer {
    int from = 0, to = 0, numTurns = 0;
    Integer util = 0;
    AbstractMap.SimpleEntry<Integer, Integer> originalChange = null;
    Random r ;
    ArrayList<Integer> computer = new ArrayList<Integer>(),  human = new ArrayList<Integer>();
    BoardPiece me,  adversery;
    List<Computer> children ;
    static ArrayDeque<AbstractMap.SimpleEntry<Integer, Integer>> changeStatic = null;
    static Queue<AbstractMap.SimpleEntry<Integer, Integer>> BetterAndBetterOptions
            = new ArrayDeque<AbstractMap.SimpleEntry<Integer, Integer>>();
    static BoardPiece actualMe;
    Set<AbstractMap.SimpleEntry<Integer, Integer>> destinations;

    public Computer(){children = new ArrayList<Computer>();//System.out.println("Call to 17");//System.out.println("My piece is " + me)
        }
    public String toString(){
        String s =  "from,to,numTurns,util,me,adversery:" + from +", "+to+", "+numTurns+", "+util+", "+me+", "+adversery
                + " originalChange: " + originalChange
                +", changeStatic:  "+changeStatic +
                "computer + human " ;
        for(Integer i : computer) {
            s =s + i + "," ;
        }
        s=s+"\n";
        for(Integer i : human) {
            s =s + i + ",";
        }
             s=s   + " children: \n" ;
        for(Computer child: children) {
            s = s +  "   " + child.toString() + " \n" ;
        }
        return s;
    }
    public Computer(BoardPiece[] board, Integer numTurns, BoardPiece me, BoardPiece adversery){
        //System.out.println("Call to 18");//System.out.println("My piece is " + me);
        children = new ArrayList<Computer>();
        if(changeStatic == null)
            changeStatic= new ArrayDeque<AbstractMap.SimpleEntry<Integer, Integer>>();
        this.numTurns=numTurns;
        this.r = new Random();
        this.me=me;
        actualMe= BoardPiece.GOLD;
        this.adversery=adversery;

        for(int i = 0; i <= 109; i++){
            if(board[i] == me){
                computer.add(i);
            } else if (board[i] == adversery){
                human.add(i);
            }
        }

    }
    private boolean available(int location) {
        ////System.out.print("Call to 19, ");//System.out.println("My piece is " + me);
        for (Integer them : human) {
            if (them == location)
                return false;
        }
        return true;
    }

    public void getPlacesToGo(ArrayList<Integer> whichPlayer) { //always should be computer passed in
        //System.out.println("Call to 20");//System.out.println("My piece is " + me);
        destinations = new HashSet<AbstractMap.SimpleEntry<Integer, Integer>>();
        for (Integer atNow : whichPlayer) {
            AbstractMap.SimpleEntry<Integer, Integer> pair;
        int temp = 0;



            temp = atNow - 9;
            if ( temp >= 0 && temp <= 109 && temp >=0&& available(atNow - 9)) {

                    destinations.add(new AbstractMap.SimpleEntry<Integer, Integer>(atNow, atNow - 9));
            }
            temp=atNow - 18;
            if (temp >= 0 && temp <= 109 && temp >=0 && available(atNow - 18)) {
                destinations.add(new AbstractMap.SimpleEntry<Integer, Integer>(atNow, atNow - 18));
            }
            temp= atNow + 9;
            if (atNow + 9 <= 109&& temp <= 109 && temp >=0 && available(atNow + 9)) {
                destinations.add(new AbstractMap.SimpleEntry<Integer, Integer>(atNow, atNow + 9));
            }
            temp=atNow + 18;
            if (atNow + 18 <= 109&& temp <= 109 && temp >=0 && available(atNow + 18)) {
                destinations.add(new AbstractMap.SimpleEntry<Integer, Integer>(atNow, atNow + 18));
            }
            temp= atNow + 1;
            if (atNow + 1 % 10 > 0&& temp <= 109 && temp >=0 && available(atNow + 1)) {
                if(temp/10 == atNow /10)
                    destinations.add(new AbstractMap.SimpleEntry<Integer, Integer>(atNow, atNow + 1));
            }
            temp=atNow - 1;
            if (atNow - 1 % 10 < 9 && temp <= 109 && temp >=0&& available(atNow - 1)) {
                if(temp/10 == atNow /10)
                    destinations.add(new AbstractMap.SimpleEntry<Integer, Integer>(atNow, atNow - 1));
            }
            temp=atNow + 2;
            if (atNow + 2 % 10 > 1 && temp <= 109 && temp >=0&& available(atNow + 2)) {
                if(temp/10 == atNow /10)
                    destinations.add(new AbstractMap.SimpleEntry<Integer, Integer>(atNow, atNow + 2));
            }
            temp=atNow - 2;
            if ((atNow - 2) % 10 < 8 && temp <= 109 && temp >=0 && available(atNow - 2)) {
                if(temp/10 == atNow /10)
                    destinations.add(new AbstractMap.SimpleEntry<Integer, Integer>(atNow, atNow - 2));
            }/*
            temp=atNow - 10;
            if (atNow - 10 >= 0 && temp <= 109 && temp >=0&& available(atNow - 10)) {
                destinations.add(new AbstractMap.SimpleEntry<Integer, Integer>(atNow, atNow - 10));
            }
            temp=atNow - 20;
            if (atNow - 20 >= 0 && temp <= 109 && temp >=0&& available(atNow - 20)) {
                destinations.add(new AbstractMap.SimpleEntry<Integer, Integer>(atNow, atNow - 20));
            }
            temp=atNow + 10;
            if (atNow + 10 <= 109 && temp <= 109 && temp >=0 && available(atNow + 10)) {
                destinations.add(new AbstractMap.SimpleEntry<Integer, Integer>(atNow, atNow + 10));
            }
            temp=atNow + 20;
            if (atNow + 20 <= 109 && temp <= 109 && temp >=0 && available(atNow + 20)) {
                destinations.add(new AbstractMap.SimpleEntry<Integer, Integer>(atNow, atNow + 20));
            }
            */
        }
        //System.out.println();

    }
    abstract void makeTree(int level);
    abstract boolean decideTurn();

    public Integer[] getTurn() {

        //System.out.println  ("Call to 22"); //System.out.println("My piece is " + me);
        Integer[] nextTurn = new Integer[2];

        decideTurn();
        //System.out.println("Call to 22B   ");
        //System.out.print  ("from: " + from + ", to: " + to);


        nextTurn[0] = from;
        nextTurn[1] = to;


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
            ////System.out.println("In forms line: one is " + one + ", other is " + other);
            ////System.out.println("mod ten the same");
            return true;
        } else if (one/10 == other/10){
            ////System.out.println("In forms line: one is " + one + ", other is " + other);
            ////System.out.println("10 times same x plus single dig num the same");
            return true;
        } else if (one%9 == other%9 && one != 0 && other != 0){
            ////System.out.println("In forms line: one is " + one + ", other is " + other);
            ////System.out.println("mod nine the same");
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
                if(between(this.computer.get(i), theirPiece, this.computer.get(j))) {
                    if (formsXLine(this.computer.get(i), theirPiece) && formsXLine(theirPiece, this.computer.get(j)))
                        return true;
                    if (formsYLine(this.computer.get(i), theirPiece) && formsYLine(theirPiece, this.computer.get(j)))
                        return true;
                    if(formsZLine(this.computer.get(i), theirPiece) && formsZLine(theirPiece, this.computer.get(j)))
                        return true;
                }

            }
        }
        return false;
    }


    /*
    private boolean wins(BoardPiece[] possBoard) {
        // TODO Auto-generated method stub
        //System.out.println("My piece is " + me);
        boolean wins = false;
        Computer c = new Louis(possBoard, 1000, me, adversery);

        return c.wins();

    }*/

    public abstract boolean isTerminalNode();

    public abstract int utilityProfile();
    public boolean isMaxNode(boolean maximizing){

        //System.out.println("Call to 23");//System.out.println("My piece is " + me);
        if(maximizing == (me == actualMe)){
            return maximizing;
        }
        //System.out.print("ERROR in isMaxNode, ");
        //System.out.println("me is " + me+ ", p2 aka actualME is " + actualMe + ", maximizing is " + maximizing);
        return maximizing;
    }
    int alphaBetaPruning(int depth, int alpha, int beta, boolean maximizing) {
        //System.out.println("Call to 24");//System.out.println("My piece is " + me);
        System.out.println(this.toString());
        if (this.isTerminalNode()) {
            return this.utilityProfile();
        } else {
            if (this.isMaxNode(maximizing)) {
                this.util = Integer.MIN_VALUE;
                for (Computer child : children) {
                    int temp =alpha;
                    int minValOfThisChoice = child.alphaBetaPruning(depth - 1, alpha, beta,!maximizing);
                    this.util = (Math.max(this.utilityProfile(), minValOfThisChoice));
                    alpha = Math.max(alpha, this.utilityProfile());//in no other branch where min can choose lower then this is it worth it for min to keep looking around--ill never pick this choice/child
                    //System.out.println("Call to 24b: this.util = " + this.utilityProfile() + ", alpha is + "+ alpha );
                    if (alpha >= beta) {
                        // Beta cutoff.
                        break;
                    }
                    if(alpha > temp) {
                        //we can now unambiguously choose a child that will maximize payoff no matter
                        //what min does. This is a choice with a higher payoff then we previously saw was available to us
                        this.originalChange = child.originalChange;
                    }
                    //System.out.println("Computer.changeStatic.offer(child.originalChange); " + child.originalChange);
                    //Computer.changeStatic.offer(child.originalChange);

                }
                return this.utilityProfile();
            } else {
                this.util=Integer.MAX_VALUE;
                for (Computer child : children) {
                    int temp = beta;
                    this.util = (Math.min(this.utilityProfile(), child.alphaBetaPruning( depth - 1, alpha, beta, !maximizing)));
                    beta = Math.min(alpha, this.utilityProfile());
                    //System.out.println("Call to 24c: this.util = " + this.utilityProfile() + ", beta is + "+ beta );
                    if (beta <= alpha) {
                        // Alpha cutoff.
                        break;
                    }
                    if(beta < temp) {

                    }
                    //System.out.println("Computer.changeStatic. does not offer(child.originalChange); " + child.originalChange);


                }
                return this.utilityProfile();
            }
        }
    }
}

