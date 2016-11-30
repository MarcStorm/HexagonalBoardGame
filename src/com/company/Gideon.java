package com.company;

import java.util.AbstractMap;
import java.util.ArrayList;

/**
 * @Author Anu Challa (achalla@terpmail.umd.edu)
 * @Author Gideon Potok (gideon.potok@gmail.com)
 * @Author Marc Storm Larsen (mslarsen1992@gmail.com)
 *
 * I pledge on my honor that I have not given or received any unauthorized assistance on this project.
 */
// TODO: Make sure this is max player
public class Gideon extends Computer {
    BoardPiece[] board;

    public Gideon(BoardPiece[] board, Integer numTurns, BoardPiece me, BoardPiece adversery){
        super(board,numTurns,me,adversery);
        this.board = board;
        //System.out.println("Call to 10");
        //System.out.println("my piece is " + me);
    }

    public Gideon(BoardPiece me, BoardPiece adversery,
                  AbstractMap.SimpleEntry<Integer, Integer> change,
                  ArrayList<Integer> positionsMe, ArrayList<Integer> positionsAdversery, AbstractMap.SimpleEntry<Integer, Integer> heritage) {
        //System.out.println("my piece is " + me);
        this.me=me;
        this.adversery=adversery;
        //System.out.print("Call to 11: ");
        //children = new ArrayList<Computer>();
        this.computer = new ArrayList<Integer>(positionsMe);//(level %2 == 0)? positionsMe:positionsAdversery;
        this.human = new ArrayList<Integer>(positionsAdversery);
        //System.out.println("Gideon");
        //System.out.println(computer);
        //System.out.println("VS");
        //System.out.println("1"+human);
        //System.out.println("("+change + ")");

        this.human.remove(change.getKey()); //THIS IS CORRECT
        //System.out.println("2"+human);
        this.human.add(positionsMe.size() - 1, change.getValue()); //THIS IS CORRECT
        //System.out.println("3"+human);
        //System.out.println();
        //getPlacesToGo(computer);
        this.originalChange = heritage;
        //System.out.println("heritage = " + heritage);
    }

    public  boolean isTerminalNode(){
        //System.out.println("Call to 9");
        //System.out.println("my piece is " + me);
        return true;
    }

    public int utilityProfile(int depth, boolean maximizing){
        String s = "";
        if(!maximizing){
            // if MINimizing
            if(this.opponentWon()){
                s = "Gideon" +"min-imizing,opponent wins: " + this.toString();
                util = depth*1000;
                return util;
            }
            if(this.wins()){
                s = "Gideon" +"min-imizing,wins: " + this.toString();
                util = depth* (-1000);
                return util;
            }
        }else {
            //If MAXimizing
            if (this.opponentWon()) {
                s = "Gideon" +"max-imizing,opponent wins: " + this.toString();
                util = depth * (-1000);
                return util;
            }
            if (this.wins()) {
                s = "Gideon" +"max-imizing,wins: " + this.toString();
                util = depth * 1000;
                return util;
            }
        }
        if(!s.equals(""))
            System.out.println(s);

        return utilityProfile(depth);

    }

    public int utilityProfile(int depth){
        //TODO: make better. Must be less then or equal to 999 greater then or equal to -999
        util = (Main.counter%100 ) ;

        if(Main.counter < 0)
            Main.counter--;
        else
            Main.counter++;
        Main.counter*=(-1);
        //if(Main.counter %25 == 0)
            //System.out.println("counter is " + Main.counter + ", utility is  " + util);

        return util;
    }
    boolean decideTurn(){

        return false;
    }
    Boolean inRangeOfImportantStuff(Integer location){
        //System.out.println("calling  menthod inRangeOfImportantStuff. location is " + location.toString());
        return withinRange(33,location)
                || withinRange(37, location)
                || withinRange(73, location)
                || withinRange(77, location);



    }
    Boolean inRangeOfNotImportantStuff(Integer location){
        //System.out.println("calling  menthod inRangeOfNotImportantStuff. location is " + location.toString());
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
        //System.out.println("Call to 14");
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
            //System.out.println("No gain from switching in terms of num of computer tokens in a line (max was " + max + ").");
            return false;
        } else if (max < 1){
            return false;
        }
        //System.out.println("MOVING SO MORE IN SAME ROW from is " + from2 + ", to is " + to2 +". max was " + max);
        return true;
    }
    public void makeTree(int level) {
        System.out.println("call to gideon.makeTree(" + level + ");");
        //System.out.println("Call to 16");

    }


}

