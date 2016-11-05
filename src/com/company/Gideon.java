package com.company;
import java.util.ArrayList;
import java.util.Random;
/**
 * Created by gideonpotok on 11/5/16.
 */

public class Gideon extends Computer {

    public Gideon(BoardPiece[] board, Integer numTurns, BoardPiece me, BoardPiece adversery){
        super(board,numTurns,me,adversery);
    }

    boolean decideTurn(){
        placesToGo = new ArrayList<ArrayList<Integer>>();
        for(int i = 0; i< 4; i++){
            //ArrayList<Integer> choices = new ArrayList<Integer>();
            //placesToGo.add(choices);
        }
        for(int i = 0; i< 4; i++){
            Integer position = computer.get(i);
            //ArrayList<Integer> choices = placesToGo.remove(i);

            if(position %10 <= 3 && position /10 >= 6) {
                int placeToGo = position-18;
                if(board[placeToGo] == BoardPiece.EMPTY) {
                    //choices.add(placeToGo);
                    if (maybeUse(position,placeToGo))
                        return true;
                }

            }
            else if(position %10 >= 6 && position /10 <= 3) {
                int placeToGo = position+18;
                if(board[placeToGo] == BoardPiece.EMPTY) {
                    //choices.add(placeToGo);
                    if (maybeUse(position,placeToGo))
                        return true;
                }

            }
            //placesToGo.add(i, choices);
        }
		/*
		for(int i = 0; i < 4; i++){
			if(!placesToGo.get(i).isEmpty()){
				to = placesToGo.get(i).get(r.nextInt(placesToGo.get(i).size()));
				from = i;
				return;

			}
		}*/
        for(int i = 0; i< 4; i++){
            int position = computer.get(i);
            //ArrayList<Integer> choices = placesToGo.get(i);
            if(position %10 <= 3 && position /10 <= 3) {
                if(board[position+2] == BoardPiece.EMPTY)
                    if( ((position+2) / 10) == ((position) / 10) ) {
                        //choices.add(position+2);
                        if (maybeUse(position,position+2))
                            return true ;

                    }
                if(board[position+20] == BoardPiece.EMPTY){
                    //choices.add(position+20);
                    if (maybeUse(position,position+20))
                        return true ;
                }
            }
            else if(position %10 >= 6 && position /10 >= 6) {
                if(board[position-2] == BoardPiece.EMPTY)
                    if( ((position-2) / 10) == ((position) / 10) ) {
                        //choices.add(position-2);
                        if (maybeUse(position,position-2))
                            return true;
                    }
                if(board[position - 20] == BoardPiece.EMPTY) {
                    //choices.add(position-20);
                    if (maybeUse(position,position-20))
                        return true;
                }
            }
            //placesToGo.add(i,choices);
        }

        for(int i = 0; i< 109; i++){
            for(int j = 0; j< 4; j++){
                int x = i + r.nextInt(50) + 25;
                int y = x % 109;
                if(board[y] == BoardPiece.EMPTY && withinRange(computer.get(j), y)){
                    from = computer.get(j);
                    to = y;
                    return true;
					/*ArrayList<Integer> choice = placesToGo.get(j);
					choice.add(j);
					choice.add(i);
					placesToGo.add(j,choice);*/
                }
            }
        }




        ArrayList<Integer> choices = new ArrayList<Integer>();
        int i = 0;
        while(choices.isEmpty()){
            choices = placesToGo.remove(0);
            int randVal = r.nextInt(choices.size());
            from = computer.get(i++);
            to = choices.get(randVal);
        }
        return false;
    }
    Boolean inRangeOfImportantStuff(Integer location){
        System.out.println("calling  menthod inRangeOfImportantStuff. location is " + location.toString());
        return withinRange(33,location)
                || withinRange(37, location)
                || withinRange(73, location)
                || withinRange(77, location);



    }
    Boolean inRangeOfNotImportantStuff(Integer location){
        System.out.println("calling  menthod inRangeOfNotImportantStuff. location is " + location.toString());
        return withinRange(90,location)
                || withinRange(91, location)
                || withinRange(101, location)
                || withinRange(98, location)
                || withinRange(99,location)
                || withinRange(108, location)
                || withinRange(19, location)
                || withinRange(18, location)
                || withinRange(8, location)
                || withinRange(1, location)
                || withinRange(11, location)
                || withinRange(10, location);




    }
    boolean maybeUseHelper(Integer from, Integer to){
        int useThisOne = 800;
        Boolean useOrNo = false;
        useOrNo = r.nextInt(useThisOne) %9 == 0;
        if(useOrNo){
            this.from=from;
            this.to=to;
            return true;
        }
        return false;
    }
    public boolean maybeUse(Integer from, Integer to){
        if(fromVsTo_toWins(from,to, computer.get(0), computer.get(1),computer.get(2),computer.get(3))){
            //return true;
        }

        for(int i = 0; i < 4; i++){
            for(int j = i+1; j < 4; j++){
                if(formsLine(human.get(i), human.get(j))){
                    if(formsLine(to, human.get(i)) &&
                            formsLine(to, human.get(j))){
                        if(maybeUseHelper(from,to))
                            return true;
                        if(between(human.get(i), to, human.get(j)))
                            if(maybeUseHelper(from,to))
                                return true;
                    }
                }
            }
        }



        for(int i = 0; i < 4; i++){
            if(withinRange(to, human.get(i))
                    && !withinRange(from, human.get(i))){
                if(maybeUseHelper(from,to))
                    return true;
            }
        }
        if(maybeUseHelper(from,to))
            return true;
        return false;
    }
    boolean fromVsTo_toWins(Integer from2, Integer to2, Integer integer, Integer integer2, Integer integer3,
                            Integer integer4) {
        if(integer == from2){
            return fromVsTo_toWins_helper(from2,to2, integer2,integer3, integer4);
        }
        if(integer2 == from2){
            return fromVsTo_toWins_helper(from2,to2, integer,integer3, integer4);
        }
        if(integer3 == from2){
            return fromVsTo_toWins_helper(from2,to2, integer,integer2, integer4);
        }
        if(integer4 == from2){
            return fromVsTo_toWins_helper(from2,to2, integer,integer2, integer3);
        }
        return false;
    }
    boolean fromVsTo_toWins_helper(Integer from2, Integer to2, Integer integer, Integer integer2, Integer integer3
    ) {
        ArrayList<Integer> three = new ArrayList<Integer>();
        three.add(integer);
        three.add(integer2);
        three.add(integer3);
        int countWithoutChangeX = 0;
        int countWithoutChangeY = 0;
        int countWithoutChangeZ = 0;

        for(int i = 0; i < 3; i++){
            for(int j = i+1; j < 3; j++){
                if(formsXLine(three.get(i),three.get(j) )){
                    countWithoutChangeX++;
                }else if(formsYLine(three.get(i),three.get(j) )){
                    countWithoutChangeY++;
                }else if(formsZLine(three.get(i),three.get(j) )){
                    countWithoutChangeZ++;
                }
            }
        }
        int countWithChangeX = countWithoutChangeX;
        int countWithChangeY = countWithoutChangeY;
        int countWithChangeZ = countWithoutChangeZ;
        for(int i = 0; i < 3; i++){
            if(formsXLine(three.get(i),from )){
                countWithoutChangeX++;
            }else if(formsYLine(three.get(i),from )){
                countWithoutChangeY++;
            }else if(formsZLine(three.get(i),from)){
                countWithoutChangeZ++;
            }
            if(formsXLine(three.get(i),to )){
                countWithChangeX++;
            }else if(formsYLine(three.get(i),to )){
                countWithChangeY++;
            }else if(formsZLine(three.get(i),to)){
                countWithChangeZ++;
            }
        }
        int max = 0;
        if(countWithChangeX > max)
            max = countWithChangeX;
        if(countWithChangeY > max)
            max = countWithChangeY;
        if(countWithChangeZ > max)
            max = countWithChangeZ;

        if(max <= countWithoutChangeX || max <= countWithoutChangeY || max <= countWithoutChangeZ ){
            System.out.println("No gain from switching in terms of num of computer tokens in a line (max was " + max + ").");
            return false;
        } else if (max < 1){
            return false;
        }
        System.out.println("MOVING SO MORE IN SAME ROW from is " + from2 + ", to is " + to2 +". max was " + max);
        return true;
    }
}

