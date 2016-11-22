package com.company;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * @Author Anu Challa (achalla@terpmail.umd.edu)
 * @Author Gideon Potok (gideon.potok@gmail.com)
 * @Author Marc Storm Larsen (mslarsen1992@gmail.com)
 *
 * I pledge on my honor that I have not given or received any unauthorized
 * assistance on this project.
 */

public class Node {

    // Fields
    private int value;
    Node parent;
    ArrayList<Integer> positionsMe;
    ArrayList<Integer> positionsAdversery;
    private boolean maxNode;
    HashMap<Integer, ArrayList<Integer>> placesToGo;
    // These two variables describe what moved were made in other to reach the node's position.
    int fromPosition;
    int toPosition;

    // Constructor if node is defined without parent.
    public Node(ArrayList<Integer> positionsMe, ArrayList<Integer> positionsAdversery) {
        this.value = 0;
        this.positionsMe = positionsMe;
        this.positionsAdversery = positionsAdversery;
        this.parent = null;
        this.maxNode = true;
        if (this.maxNode) {
            this.placesToGo = getPlacesToGo(positionsMe);
        } else {
            this.placesToGo = getPlacesToGo(positionsAdversery);
        }
        this.fromPosition = -1;
        this.toPosition = -1;
    }

    // Constructor if node is defined with parent.
    public Node(ArrayList<Integer> positionsMe, ArrayList<Integer> positionsAdversery, Node parent, int fromPosition, int toPosition) {
        this.value = 0;
        this.positionsMe = positionsMe;
        this.positionsAdversery = positionsAdversery;
        this.parent = parent;
        this.maxNode = !parent.isMaxNode();
        if (this.maxNode) {
            this.placesToGo = getPlacesToGo(positionsMe);
        } else {
            this.placesToGo = getPlacesToGo(positionsAdversery);
        }
        this.fromPosition = fromPosition;
        this.toPosition = toPosition;
    }

    private HashMap getPlacesToGo(ArrayList<Integer> positions) {
        HashMap<Integer, ArrayList<Integer>> placesToGo = new HashMap<>();
        ArrayList<Integer> possiblePlacesToGo = new ArrayList<>();
        // Variable to determine if a possible position is within the game board or not.
        int rangeLower;
        int rangeUpper;
        int possiblePlaceToGo;

        for (int i = 0; i < positions.size(); i++) {
            // Determine places to go for the line x1, x2, x3, x4...
            rangeLower = (positions.get(i)/10) * 10;
            rangeUpper = rangeLower + 9;
            // For loop runs 10 times as at a position the brick has a maximum of 10 possible moves.

            for (int j = -2; j <= 2; j++) {
                if (j != 0) {
                    possiblePlaceToGo = positions.get(i) + j;
                    if (withinRange(rangeLower, rangeUpper, possiblePlaceToGo)) {
                        possiblePlacesToGo.add(possiblePlaceToGo);
                    }
                }
            }

            // Determine places to go for the line 1x, 2x, 3x, 4x...
            rangeLower = 0;
            rangeUpper = 109;
            for (int j = -20; j <= 20; j += 10) {
                possiblePlaceToGo = positions.get(i) + j;
                if (withinRange(rangeLower, rangeUpper, possiblePlaceToGo)) {
                    possiblePlacesToGo.add(possiblePlaceToGo);
                }
            }

            // Determine places to go for the vertical line.
            rangeLower = findRangeLower(positions.get(i));
            rangeUpper = findRangeUpper(positions.get(i));
            for (int j = 0; j < 10; j++) {
                possiblePlaceToGo = positions.get(i);
                if (withinRange(rangeLower, rangeUpper, possiblePlaceToGo)) {
                    possiblePlacesToGo.add(possiblePlaceToGo);
                }
            }

            // Every possible position for a brick have been determined at this point.
            placesToGo.put(positions.get(i), possiblePlacesToGo);
        }


        return placesToGo;
    }

    private boolean withinRange(int rangeLower, int rangeUpper, int possiblePlaceToGo) {
        return possiblePlaceToGo >= rangeLower && possiblePlaceToGo <= rangeUpper;
    }

    private int findRangeUpper(int position) {
        int positionTemp = position;
        while (positionTemp % 10 != 0 || position < 100) {
            positionTemp += 9;
        }
        return positionTemp;
    }

    private int findRangeLower(int position) {
        int positionTemp = position;
        while (positionTemp % 10 != 9 || position > 9) {
            positionTemp -= 9;
        }
        return positionTemp;
    }

    // Is it possible for this problem to define a node as a terminal node?
    // Or would that we it the nodes are in a line in the search?
    public boolean isTerminalNode() {
        return false;
    }

    public int getValue() {
        return value;
    }

    public boolean isMaxNode() {
        return maxNode;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public ArrayList<Integer> getPositions() {
        return positionsMe;
    }

    public ArrayList<Integer> getPositionsAdversery() {
        return positionsAdversery;
    }
}