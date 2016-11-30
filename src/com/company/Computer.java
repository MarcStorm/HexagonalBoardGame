package com.company;

import java.util.*;

/**
 * @Author Anu Challa (achalla@terpmail.umd.edu)
 * @Author Gideon Potok (gideon.potok@gmail.com)
 * @Author Marc Storm Larsen (mslarsen1992@gmail.com)
 *
 * I pledge on my honor that I have not given or received any unauthorized assistance on this project.
 */
public abstract class Computer {

    // TODO: Add private, protected or public to fields.
    int from = 0, to = 0, numTurns = 0;
    Integer util = Integer.MIN_VALUE;
    public AbstractMap.SimpleEntry<Integer, Integer> originalChange = null;
    Random r ;
    public ArrayList<Integer> computer = new ArrayList<Integer>(),  human = new ArrayList<Integer>();
    BoardPiece me,  adversery;
    public List<Computer> children ;
    static HashMap<AbstractMap.SimpleEntry<Integer, Integer>, Integer> movesAndUtils = null;
    static BoardPiece actualMe;
    public Set<AbstractMap.SimpleEntry<Integer, Integer>> destinations;

    public Computer(){
        children = new ArrayList<Computer>();//System.out.println("Call to 17");//System.out.println("My piece is " + me)

        this.r = new Random();
    }

    public Computer(BoardPiece[] board, Integer numTurns, BoardPiece me, BoardPiece adversery){
        //System.out.println("Call to 18");//System.out.println("My piece is " + me);
        super();
        this.numTurns=numTurns;

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

    /**
     * This method will tell if a given location is occupied or not.
     *
     * @param location      the location that is wished to be checked whether or not it's occupied.
     * @return boolean      true if the location is EMPTY.
     *                      false if the location is occupied.
     */
    private boolean available(int location) {
        // TODO: Need to add a check to see if your own bricks are at the location.
        ////System.out.print("Call to 19, ");//System.out.println("My piece is " + me);
        for (Integer them : human) {
            if (them == location)
                return false;
        }
        for (Integer them : computer) {
            if (them == location)
                return false;
        }
        return true;
    }

    /**
     * This method will compute all the positions that the player's bricks can move to. Furthermore, the method will
     * store this information in a data structure that specifies which brick is able to move to where.
     * As the game board is a diamond shape constructed by a hexagons, each brick can move in 6 directions from its
     * current position. This method will also check that the computed position is within the game board. This will
     * ensure that a player is never faced with the option to move outside the game board, an illegal move.
     * The data structure used for this method allows to look up the destinations, to and from, in constant time.
     *
     * @param whichPlayer   the player for which it is desired to determine its possible places to move to.
     */
    public void getPlacesToGo(ArrayList<Integer> whichPlayer) { //always should be computer passed in
        //System.out.println("Call to 20");//System.out.println("My piece is " + me);
        destinations = new HashSet<AbstractMap.SimpleEntry<Integer, Integer>>();
        for (Integer atNow : whichPlayer) {
            AbstractMap.SimpleEntry<Integer, Integer> pair;
        int temp = 0;

            temp=atNow - 1;
            if ((temp% 10 )< 9 && temp <= 109 && temp >=0 && available(atNow - 1)) {
                if(temp/10 == atNow /10)
                    destinations.add(new AbstractMap.SimpleEntry<Integer, Integer>(atNow, atNow - 1));
            }
            temp=atNow - 2;
            if ((temp % 10) < 8 && temp <= 109 && temp >=0 && available(atNow - 2)) {
                if(temp/10 == atNow /10)
                    destinations.add(new AbstractMap.SimpleEntry<Integer, Integer>(atNow, atNow - 2));
            }
            temp=atNow + 2;
            if ((temp % 10 )> 1 && temp <= 109 && temp >=0&& available(atNow + 2)) {
                if(temp/10 == atNow /10)
                    destinations.add(new AbstractMap.SimpleEntry<Integer, Integer>(atNow, atNow + 2));
            }
            temp= atNow + 1;
            if ((temp% 10 )> 0&& temp <= 109 && temp >=0 && available(atNow + 1)) {
                if(temp/10 == atNow /10)
                    destinations.add(new AbstractMap.SimpleEntry<Integer, Integer>(atNow, atNow + 1));
            }
            temp=atNow - 10;
            if (atNow - 10 >= 0 && temp <= 109 && temp < atNow && temp >=0&& available(atNow - 10)) {
                destinations.add(new AbstractMap.SimpleEntry<Integer, Integer>(atNow, atNow - 10));
            }temp=atNow + 10;
            if (atNow + 10 <= 109 && temp <= 109 && temp >=0 && temp > atNow &&available(atNow + 10)) {
                destinations.add(new AbstractMap.SimpleEntry<Integer, Integer>(atNow, atNow + 10));
            }
            temp=atNow - 20;
            if (atNow - 20 >= 0 && temp <= 109 && temp >=0&& temp < atNow && available(atNow - 20)) {
                destinations.add(new AbstractMap.SimpleEntry<Integer, Integer>(atNow, atNow - 20));
            }

            temp=atNow + 20;
            if (atNow + 20 <= 109 && temp <= 109 && temp >=0 && temp > atNow && available(atNow + 20)) {
                destinations.add(new AbstractMap.SimpleEntry<Integer, Integer>(atNow, atNow + 20));
            }
            temp= atNow + 9;
            if ((atNow %9) > 1  && atNow + 9 <= 109 && temp <= 109 && temp > atNow && temp >=0 && available(atNow + 9)) {
                destinations.add(new AbstractMap.SimpleEntry<Integer, Integer>(atNow, atNow + 9));
            }
            temp=atNow + 18;
            if ( (atNow %9) > 2 && atNow + 18 <= 109 && temp <= 109 && temp > atNow  && temp >=0 && available(atNow + 18)) {
                destinations.add(new AbstractMap.SimpleEntry<Integer, Integer>(atNow, atNow + 18));
            }
            temp = atNow - 9;
            if ( (temp %9) > 1 &&  temp >= 0 && temp <= 109 && temp < atNow  &&  temp >=0&& available(atNow - 9)) {

                destinations.add(new AbstractMap.SimpleEntry<Integer, Integer>(atNow, atNow - 9));
            }
            temp=atNow - 18;
            if ((temp %9) > 2 && temp >= 0 && temp <= 109 && temp < atNow  && available(atNow - 18)) {
                destinations.add(new AbstractMap.SimpleEntry<Integer, Integer>(atNow, atNow - 18));
            }
        }
    }
    abstract void makeTree(int level);
    abstract boolean decideTurn();

    public Integer[] getTurn() {
        Integer[] nextTurn = new Integer[2];
        if(Main.startingPositionIsGivenAsTextInput ) {
            for(int i = 0; i < 100; i++){
                decideTurn();

            }
        }
        decideTurn();
        nextTurn[0] = from;
        nextTurn[1] = to;
        return nextTurn;
    }

    /**
     * This method will determine whether or not two bricks on the game board form a line across the z axis.
     *
     * @param one       int parameter that is the location of the brick on the game board.
     * @param other     int parameter that is the location of the brick on the game board.
     * @return          boolean. True if the two locations form a line across the z axis, otherwise false.
     */
    boolean formsZLine(Integer one, Integer other) {
        if(one % 10 == other % 10){
            return true;
        }
        return false;
    }

    /**
     * This method will determine whether or not two bricks on the game board form a line across the x axis.
     *
     * @param one       int parameter that is the location of the brick on the game board.
     * @param other     int parameter that is the location of the brick on the game board.
     * @return          boolean. True if the two locations form a line across the x axis, otherwise false.
     */
    boolean formsXLine(Integer one, Integer other) {
        if (one%9 == other%9 && one != 0 && other != 0){
            return true;
        }
        return false;
    }

    /**
     * This method will determine whether or not two bricks on the game board form a line across the y axis.
     *
     * @param one       int parameter that is the location of the brick on the game board.
     * @param other     int parameter that is the location of the brick on the game board.
     * @return          boolean. True if the two locations form a line across the x axis, otherwise false.
     */
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

    public abstract boolean isTerminalNode();

    public abstract int utilityProfile(int depth, boolean maximizing);

    public boolean isMaxNode(boolean maximizing){

        //System.out.println("Call to 23");//System.out.println("My piece is " + me);
        if(maximizing == (me == actualMe)){
            return maximizing;
        }
        //System.out.print("ERROR in isMaxNode, ");
        //System.out.println("me is " + me+ ", p2 aka actualME is " + actualMe + ", maximizing is " + maximizing);
        return maximizing;
    }

    /**
     * This method is a Depth First Search (DFS) which implements alpha-beta pruning. Without the implementation of the
     * alpha-beta pruning, DFS has a running time of O(b^d), where b is the branching factor and d is the depth. With
     * alpha-beta pruning this running time can be improved as it can be determined if certain parts of the tree is
     * worth searching through. However, even with alpha-beta pruning the worst case running time will be the same as
     * DFS.
     *
     * @param depth         The int parameter depth tells how much of the search is left as it will decrease by one
     *                      every time the algorithm is run.
     * @param alpha         This int parameter denotes the largest lower bound on ancestors for min nodes.
     * @param beta          This int parameter denotes the smallest upper bound on ancestors for max nodes.
     * @param maximizing    This boolean parameter will tell whether the node is a max or min node.
     * @return              The return value is a value that is used by the algorithm itself for the search.
     */
    public int alphaBetaPruning(int depth, int alpha, int beta, boolean maximizing) {
        // If the node is a terminal node or the depth is 0,
        // then the bottom of the tree or the limited tree has been reached.
        if (this.isTerminalNode() || this.opponentWon()) {
            return this.utilityProfile(depth,maximizing);
        } else {
            if (this.isMaxNode(maximizing)) {
                this.util = Integer.MIN_VALUE;
                for (Computer child : children) {
                    int minValOfThisChoice = child.alphaBetaPruning(depth - 1, alpha, beta,!maximizing);
                    this.util = Math.max(this.util, minValOfThisChoice);
                    alpha = Math.max(alpha, this.util);

                    // Beta cut-off.
                    if (beta <= alpha) {
                        break;
                    }
                }
                return this.util;
            } else {
                this.util = Integer.MAX_VALUE;
                for (Computer child : children) {
                    int maxValOfThisChoice = child.alphaBetaPruning(depth - 1, alpha, beta, !maximizing);
                    this.util = Math.min(this.util, maxValOfThisChoice);
                    beta = Math.min(beta, this.util);

                    // Alpha cut-off.
                    if (beta <= alpha) {
                        break;
                    }
                }
                return this.util;
            }
        }
    }



    public boolean opponentWon(){
        Computer opponent = new Gideon(adversery,me,new AbstractMap.SimpleEntry<Integer, Integer>(human.get(0),human.get(0)),human,computer,originalChange);
        return opponent.wins();
    }


    /**
     * This method will determine if
     * @return
     */
    boolean wins(){

        int count=0;
        int dimension = 0;
        if(formsXLine(computer.get(0),computer.get(1) )){
                    count++;
        }else if(formsYLine(computer.get(0),computer.get(1))){
                    count++;
                    dimension = 1;
        }else if(formsZLine(computer.get(0),computer.get(1) )){
                    count++;
                    dimension =2;
        }
        if(formsDLine(dimension, computer.get(1),computer.get(2) ) && formsDLine(dimension, computer.get(1),computer.get(3) )){
            count++;
            count++;
            //count += 2;
        }

        boolean aligned = false;
        aligned = (count==3) ;
        if(count==3){
            boolean blocked = false;
            for(Integer theirPiece : this.human){
                if(this.between(theirPiece)){
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    private boolean formsDLine(int dimension, Integer integer, Integer integer1) {
        if(dimension==0){
            return formsXLine(integer,integer1);
        }
        if(dimension==1){
            return formsYLine(integer,integer1);
        }
        if(dimension==2){
            return formsZLine(integer,integer1);
        }
        return false;
    }

    public String toString(){
        String s =  "from,to,numTurns,util,me,adversery:" + from +", "+to+", "+numTurns+", "+util+", "+me+", "+adversery
                + " originalChange: " + originalChange
                +  "computer + human " ;
        for(Integer i : computer) {
            s =s + i + "," ;
        }
        s=s+"\n";
        for(Integer i : human) {
            s =s + i + ",";
        }
        s=s   + "num children: \n" + children.size() ;

        return s;
    }
}

