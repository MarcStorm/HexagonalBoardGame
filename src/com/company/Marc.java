package com.company;

import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;

/**
 * @Author Anu Challa (achalla@terpmail.umd.edu)
 * @Author Gideon Potok (gideon.potok@gmail.com)
 * @Author Marc Storm Larsen (mslarsen1992@gmail.com)
 *
 * I pledge on my honor that I have not given or received any unauthorized
 * assistance on this project.
 */

public class Marc extends Computer {
	private Queue<Integer> pretendConsole = new LinkedList<Integer>();

    public Marc(BoardPiece[] board, Integer numTurns, BoardPiece me, BoardPiece adversery){
        super(board, numTurns, me, adversery);
		//super.createTree();
    }



	@Override
    boolean decideTurn() {

        //The only thing you have to do
        //is set this.from and this.to how you like,
        //Use Computer's helper methods and helper members if you like!


        return false;

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

	/* Method is used for the following:
	 * - Check if all of the players 4 bricks are on a row.
	 * - Check that the opponent isn't blocking the line.
	 * - Check that none of the bricks are in one of the starting positions. */
	private boolean goalTest() {
		return false;
	}
}
