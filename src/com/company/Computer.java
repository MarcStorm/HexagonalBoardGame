package com.company;

import java.util.*;
import java.util;

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
        createDestinations();
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

    private boolean available(int location){
        for(Integer them: human){
            if(them == location)
                return false;
        }
        return true;
    }
    private void createDestinations() {
        Set<AbstractMap.SimpleEntry<Integer,Integer>> destinations = new HashSet<AbstractMap.SimpleEntry<Integer,Integer>>();
        for(Integer atNow : computer){
            AbstractMap.SimpleEntry<Integer,Integer> pair;

            if(atNow -9 >= 0 && available(atNow -9)){
                destinations.add(new AbstractMap.SimpleEntry<Integer,Integer>(atNow,atNow -9 ));
            }
            if(atNow -18 >= 0 && available(atNow -18)){
                destinations.add(new AbstractMap.SimpleEntry<Integer,Integer>(atNow,atNow -18 ));
            }
            if(atNow +9 <= 109 && available(atNow +9)){
                destinations.add(new AbstractMap.SimpleEntry<Integer,Integer>(atNow,atNow +9 ));
            }
            if(atNow +18 <= 109 && available(atNow +18)){
                destinations.add(new AbstractMap.SimpleEntry<Integer,Integer>(atNow,atNow +18 ));
            }
            if(atNow +1 %10 > 0 && available(atNow +1)){
                destinations.add(new AbstractMap.SimpleEntry<Integer,Integer>(atNow,atNow +1 ));
            }
            if(atNow -1 %10 <9  && available(atNow -1)){
                destinations.add(new AbstractMap.SimpleEntry<Integer,Integer>(atNow,atNow -1 ));
            }
            if(atNow +2 %10 > 0  && available(atNow +2)){
                destinations.add(new AbstractMap.SimpleEntry<Integer,Integer>(atNow,atNow +2 ));
            }
            if(atNow -2 %10 <9 && available(atNow -2)){
                destinations.add(new AbstractMap.SimpleEntry<Integer,Integer>(atNow,atNow -2 ));
            }
            if(atNow -10 >= 0 && available(atNow -10)){
                destinations.add(new AbstractMap.SimpleEntry<Integer,Integer>(atNow,atNow -10 ));
            }
            if(atNow -20 >= 0 && available(atNow -20)){
                destinations.add(new AbstractMap.SimpleEntry<Integer,Integer>(atNow,atNow -20 ));
            }
            if(atNow +10 <= 109 && available(atNow +10)){
                destinations.add(new AbstractMap.SimpleEntry<Integer,Integer>(atNow,atNow +10 ));
            }
            if(atNow +20 <= 109 && available(atNow +20)) {
                destinations.add(new AbstractMap.SimpleEntry<Integer, Integer>(atNow, atNow + 20));
            }
        }
        for (AbstractMap.SimpleEntry<Integer,Integer> each : destinations ){
            System.out.println(each);
        }

    }

    private int alphaBetaPruning(Node node, int depth, int alpha, int beta) {
        if (node.isTerminalNode()) {
            return utilityProfile(node);
        } else if (depth == 0) {
            return evaluationFunction(node);
        } else {
            if (node.isMaxNode()) {
                node.setValue(Integer.MIN_VALUE);
                LinkedList<Node> children = produceChildrenOf(node);
                nodeOrdering(children);
                for (Node child : children) {
                    node.setValue(Math.max(node.getValue(), alphaBetaPruning(child, depth - 1, alpha, beta)));
                    if (node.getValue() >= beta) {
                        // Beta cutoff.
                        return node.getValue();
                    } else {
                        alpha = Math.max(alpha, node.getValue());
                    }
                }
                return node.getValue();
            } else {
                node.setValue(Integer.MAX_VALUE);
                LinkedList<Node> children = produceChildrenOf(node);
                for (Node child : children) {
                    node.setValue(Math.min(node.getValue(), alphaBetaPruning(child, depth - 1, alpha, beta)));
                    if (node.getValue() <= alpha) {
                        // Alpha cutoff.
                        return node.getValue();
                    } else {
                        beta = Math.min(alpha, node.getValue());
                    }
                }
                return node.getValue();
            }
        }
    }

    private LinkedList<Node> produceChildrenOf(Node node) {
        LinkedList<Node> children = new LinkedList<>();
        for (int i = 0; i < node.placesToGo.size(); i++) {
            int newPosition = node.placesToGo.get(i);
            // Make a check to see if there are any other bricks at the move position,
            // either own or opponent's bricks.
            if (brickAt(newPosition)) {
                continue;
            }
            Node child = produceChildNodeOf(node, newPosition);
            children.addLast(child);
        }
        return children;
    }

    // METHOD NOT COMPLETE.
    private boolean brickAt(int move) {
        return false;
    }

    private Node produceChildNodeOf(Node node, int move) {
        Node child = new Node(move, node);
        return child;
    }

    private int utilityProfile(Node node) {
        return 0; //return super.utilityProfile(node);
    }

    private int evaluationFunction(Node node) {
        return 0;//super.evaluationFunction(node);
    }


    private void nodeOrdering(LinkedList<Node> children) {

    }

}

