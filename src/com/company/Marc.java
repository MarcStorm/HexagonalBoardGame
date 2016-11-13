package com.company;

import java.util.LinkedList;

/**
 * @Author Anu Challa (achalla@terpmail.umd.edu)
 * @Author Gideon Potok (gideon.potok@gmail.com)
 * @Author Marc Storm Larsen (mslarsen1992@gmail.com)
 *
 * I pledge on my honor that I have not given or received any unauthorized
 * assistance on this project.
 */

public class Marc extends Computer {

    public Marc(BoardPiece[] board, Integer numTurns, BoardPiece me, BoardPiece adversery){
        super(board, numTurns, me, adversery);
    }

    @Override
    boolean decideTurn() {

        //The only thing you have to do
        //is set this.from and this.to how you like,
        //Use Computer's helper methods and helper members if you like!

        return false;
        //return value doesnt matter

        //Make it so that calling this method twice in a row
        //this.decideTurn()
        //this.decideTurn()
        //sets from and to
        //iteratively deeper each time
    }

	/* The iterative deepening method must call the alpha-beta pruning method.
	 * The alpha-beta pruning method will implement a sort of limited depth first search (LDFS).
	 * So far LDFS should definitely pass on the depth to the alpha-beta pruning method as that
	 * will decide how far the method search.
	 */


/*	private void iterativeDeepening() {
		int currentMaxDepth = 0;
		while (true) {
			alphaBetaPruning(currentMaxDepth);
			currentMaxDepth++;
		}
	}*/

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
		return 0;
	}

	private int evaluationFunction(Node node) {
		return 0;
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
