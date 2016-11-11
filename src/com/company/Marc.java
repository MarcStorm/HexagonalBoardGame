package com.company;

import java.util.HashMap;
import java.util.Stack;

public class Marc extends Computer {



    public Marc(BoardPiece[] board, Integer numTurns, BoardPiece me, BoardPiece adversery){
        super(board, numTurns, me, adversery);
    }

    @Override
    boolean decideTurn() {
        for (int i = 0; i < computer.size(); i++) {
            System.out.println("Brick is placed at: " + computer.get(i));
        }

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
		} else if (node.isMaxNode()) {
			node.setValue(Integer.MIN_VALUE);

			for (int i = 0; i < node.placesToGo.size(); i++) {
				int move = node.placesToGo.get(i);
				// Make a check to see if there are any other bricks at the move position,
				// either own or opponent's bricks.
				if (brickAt(move)) {
					continue;
				}
				Node childNode = produceChildNodeOf(node, move);
				node.setValue(Math.max(node.getValue(), alphaBetaPruning(childNode, depth-1, alpha, beta)));
				if (node.getValue() >= beta) {
					// Beta cutoff.
					return node.getValue();
				} else {
					alpha = Math.max(alpha, node.getValue());
				}
				return node.getValue();
			}
		} else {
			node.setValue(Integer.MAX_VALUE);
			for (int i = 0; i < node.placesToGo.size(); i++) {
				int move = node.placesToGo.get(i);
				// Make a check to see if there are any other bricks at the move position,
				// either own or opponent's bricks.
				if (brickAt(move)) {
					continue;
				}
				Node childNode = produceChildNodeOf(node, move);
				node.setValue(Math.min(node.getValue(), alphaBetaPruning(childNode, depth-1, alpha, beta)));
				if (node.getValue() <= alpha) {
					// Alpha cutoff.
					return node.getValue();
				} else {
					beta = Math.max(alpha, node.getValue());
				}
				return node.getValue();
			}
		}


		// This return statement is probably not correct.
		return 0;
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

	private void nodeOrdering() {

	}

	/*public static boolean iterativeDeepening(int maxDepth, HashMap<String, Integer> problems){
		int currentMaxDepth = 1;
		long startTime = System.currentTimeMillis();

		Stack<Reaction> reactionStack = new Stack<>();

		while(maxDepth > currentMaxDepth){
			if(depthFirstSearch(currentMaxDepth, 0, problems, reactionStack, startTime)){
				return true;
			}
			//System.out.println(stateCount);
			//stateCount = 0;
			currentMaxDepth++;
		}
		return false;
	}*/
}
