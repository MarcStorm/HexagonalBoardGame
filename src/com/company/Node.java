package com.company;

import java.util.ArrayList;

/**
 * Created by Marc on 04/11/16.
 */

public class Node {

    // Fields
    private int value;
//    int alpha;
//    int beta;
    Node parent;
    int position;
    ArrayList<Integer> placesToGo;
    private boolean maxNode;

    // Constructor if node is defined without parent.
//    public Node(int value, int alpha, int beta, int position) {
    public Node(int position) {
        this.value = 0;
//        this.alpha = alpha;
//        this.beta = beta;
        this.position = position;
        this.placesToGo = getPlacesToGo(position);
        this.parent = null;
        this.maxNode = true;
    }

    // Constructor if node is defined with parent.
//    public Node(int value, int alpha, int beta, int position, Node parent) {
    public Node(int position, Node parent) {
        this.value = 0;
//        this.alpha = alpha;
//        this.beta = beta;
        this.position = position;
        this.placesToGo = getPlacesToGo(position);
        this.parent = parent;
        this.maxNode = !parent.isMaxNode();
    }

    private ArrayList getPlacesToGo(int position) {
        ArrayList<Integer> placesToGo = new ArrayList<>();
        // Variable to determine if a possible position is within the game board or not.
        int rangeLower;
        int rangeUpper;
        int possiblePlaceToGo;

        // Determine places to go for the line x1, x2, x3, x4...
        rangeLower = (position/10) * 10;
        rangeUpper = rangeLower + 9;
        // For loop runs 10 times as at a position the brick has a maximum of 10 possible moves.

        for (int i = -2; i <= 2; i++) {
            if (i != 0) {
                possiblePlaceToGo = position + i;
                if (withinRange(rangeLower, rangeUpper, possiblePlaceToGo)) {
                    placesToGo.add(possiblePlaceToGo);
                }
            }
        }

        // Determine places to go for the line 1x, 2x, 3x, 4x...
        rangeLower = 0;
        rangeUpper = 109;
        for (int i = -20; i <= 20; i += 10) {
            possiblePlaceToGo = position + i;
            if (withinRange(rangeLower, rangeUpper, possiblePlaceToGo)) {
                placesToGo.add(possiblePlaceToGo);
            }
        }

        // Determine places to go for the vertical line.
        rangeLower = findRangeLower(position);
        rangeUpper = findRangeUpper(position);
        for (int i = 0; i < 10; i++) {
            possiblePlaceToGo = position;
            if (withinRange(rangeLower, rangeUpper, possiblePlaceToGo)) {
                placesToGo.add(possiblePlaceToGo);
            }
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
}