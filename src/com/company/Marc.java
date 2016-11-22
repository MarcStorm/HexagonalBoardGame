package com.company;

<<<<<<< HEAD
import java.util.Collection;
import java.util.Iterator;
=======
import java.util.ArrayList;
>>>>>>> 25a23885189ad9ec3770ec7b7ccb67bbca7a8cdf
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

		// Find locations of me and adversery to start the search.
		ArrayList<Integer> currentPositionsMe = new ArrayList<>();
		ArrayList<Integer> currentPositionsAdversery = new ArrayList<>();
		for (int i = 0; i < board.length; i++) {

		}

		//iterativeDeepening();

        //The only thing you have to do
        //is set this.from and this.to how you like,
        //Use Computer's helper methods and helper members if you like!


        return false;

    }

<<<<<<< HEAD
=======
	/* The iterative deepening method must call the alpha-beta pruning method.
	 * The alpha-beta pruning method will implement a sort of limited depth first search (LDFS).
	 * So far LDFS should definitely pass on the depth to the alpha-beta pruning method as that
	 * will decide how far the method search.
	 */



	private void iterativeDeepening(ArrayList<Integer> currentPositionsMe, ArrayList<Integer> currentPositionsAdversery) {
		Node node = new Node(currentPositionsMe, currentPositionsAdversery);
		int currentMaxDepth = 1;
		while (true) {
			alphaBetaPruning(node, currentMaxDepth, Integer.MIN_VALUE, Integer.MAX_VALUE);
			returnSolution();
			currentMaxDepth++;
		}
	}

	// WRITE METHOD THAT RETURNS THE SOLUTION.
	private void returnSolution() {

	}
>>>>>>> 25a23885189ad9ec3770ec7b7ccb67bbca7a8cdf

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
				nodeOrdering(children);
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
		ArrayList<Integer> positions = node.getPositions();
		// Create a queue for all the children.
		LinkedList<Node> children = new LinkedList<>();
		for (int i = 0; i < positions.size(); i++) {

			int position = positions.get(i);

			ArrayList<Integer> partOfPositionsToGo = node.placesToGo.get(position);
			// Get the old positions for the node itself and the positions of the adversery to create a new node.
			ArrayList<Integer> oldPositions = node.getPositions();
			ArrayList<Integer> adverseryPositions = node.getPositionsAdversery();

			// For loop that will create a child node for every possible position that the can be reached from the
			// current position.
			for (int j = 0; j < partOfPositionsToGo.size(); j++) {
				// Make a copy of the old positions and change the one position that have changed.
				ArrayList<Integer> newPositions = oldPositions;
				int newPosition = partOfPositionsToGo.get(i);
				newPositions.set(i, newPosition);

				// Make a check to see if there are any other bricks at the move position,
				// either own or opponent's bricks.
				if (brickAt(newPosition)) {
					continue;
				}

				// Create the child node and add it to the list.
				Node child = new Node(newPositions, adverseryPositions, node, position, newPosition);
				children.addLast(child);
			}
		}
		return children;
	}

	// Method to tell if there's a brick at the position you try to move to.
	private boolean brickAt(int move) {
		if (board[move] == BoardPiece.EMPTY) {
			return true;
		} else {
			return false;
		}
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
