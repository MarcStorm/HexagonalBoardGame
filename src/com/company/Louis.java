package com.company;

/**
 * Created by gideonpotok on 11/5/16.
 */

        import java.util.*;


public class Louis extends Computer {
    //TIPS
        //if making depth greater, comment out most directions in getPlacesToGo
    /*
    List<Computer> children;
    int from = 0, to = 0, numTurns = 0;
    Random r ;
    ArrayList<Integer> computer = new ArrayList<Integer>(),  human = new ArrayList<Integer>();
    BoardPiece me,  adversery;
    Set<AbstractMap.SimpleEntry<Integer, Integer>> destinations;
     */
    public Louis(BoardPiece[] board, Integer numTurns, BoardPiece me, BoardPiece adversery) {
        super(board,numTurns,me,adversery);//System.out.println("Call to 3");//System.out.println("mypiece is " + me);
        children = new ArrayList<Computer>();
        this.originalChange=null;
        getPlacesToGo(computer); // POSSIBLE MOVES I MIGHT MAKE
        makeTree(3);//has to be ODD//System.out.println("\nTREE: " + this.toString());

    }
    boolean decideTurn() {
        this.alphaBetaPruning(6,Integer.MIN_VALUE,Integer.MAX_VALUE,true);
        /*while(!changeStatic.isEmpty()) {
            fromto = changeStatic.poll();
            //System.out.println("Call to 7b: " + fromto);
        }
        if (fromto == null){
            //System.out.println("fromtowasnull");
            this.getPlacesToGo(computer);
            fromto = this.destinations.iterator().next();
            //System.out.println("Call to 7c: " + fromto);
        }
        //System.out.println("Call to 7d: "+fromto);
        from=fromto.getKey();
        to=fromto.getValue();*/
        from=originalChange.getKey();
        to=originalChange.getValue();
        System.out.println("counter is "+ Main.counter);
        return true;

    }
    public Louis(BoardPiece me, BoardPiece adversery,
                 AbstractMap.SimpleEntry<Integer, Integer> change,
                 ArrayList<Integer> positionsMe, ArrayList<Integer> positionsAdversery) {
        //System.out.println("Call to 2");
        //System.out.println("mypiece is " + me);

        this.me=me;
        this.adversery=adversery;
        children = new ArrayList<Computer>();
        this.computer = new ArrayList<Integer>();//(level %2 == 0)? positionsMe:positionsAdversery;
        for(Integer i : positionsMe){
            computer.add(i);
        }
        this.human = new ArrayList<Integer>();
        for(Integer i : positionsAdversery){
            human.add(i);
        }
        //System.out.println("Louis");
        //System.out.println(computer);
        //System.out.println("VS");
        //System.out.println("1"+positionsAdversery);
        //System.out.println("("+change + ")");
        this.human.remove(change.getKey()); //THIS IS CORRECT
        //System.out.println("2"+positionsAdversery);
        this.human.add(change.getValue()); //THIS IS CORRECT
        //System.out.println("3"+positionsAdversery);
        //System.out.println();
        getPlacesToGo(computer);

    }
    public Louis(BoardPiece me, BoardPiece adversery,
                 AbstractMap.SimpleEntry<Integer, Integer> change,
                 ArrayList<Integer> positionsMe, ArrayList<Integer> positionsAdversery,
                 AbstractMap.SimpleEntry<Integer, Integer> originalChange) {
        this(me,adversery,change,positionsMe,positionsAdversery);
        //System.out.println("Call to 3");
        //System.out.println("mypiece is " + me);
        this.originalChange = originalChange;
    }
    public  boolean isTerminalNode(){
        //if wins return true
        //System.out.println("Call to 4");
        //System.out.println("mypiece is " + me);
        return false;
    }
    public void makeTree(int level) {
        //System.out.println("Call to 5 ");
        //System.out.println("mypiece is " + me);
        AbstractMap.SimpleEntry<Integer, Integer> heritage = this.originalChange;
        if (level > 0) {
            for (AbstractMap.SimpleEntry<Integer, Integer> each : this.destinations) {
                //After roots turn, EACH change is made to the real computer

                if(this.originalChange == null){
                    heritage=each;
                }
                //System.out.println("Call to 5: louis(..." + heritage);
                Louis l = new Louis(adversery ,me , each, human, computer, heritage); //notice THE SWITCH
                children.add(l);
            }

        } else {
            for (AbstractMap.SimpleEntry<Integer, Integer> each : this.destinations) {
                //After roots turn, EACH change is made to the real computer
                AbstractMap.SimpleEntry<Integer, Integer> local  = new AbstractMap.SimpleEntry<Integer, Integer>(each.getKey(),each.getValue());
                if(this.originalChange == null){
                    heritage=local;
                }
                //System.out.println("Call to 5: Gideon(..." + heritage);
                Gideon g = new Gideon(adversery ,me , local, human, computer, heritage); //notice THE SWITCH
                children.add(g);
            }
        }

        for(Computer lg : children){
            lg.makeTree(level - 1 );
        }

    }

    private AbstractMap.SimpleEntry<Integer, Integer> fromto = null;
    public String toString(){
        return super.toString();
    }









    public int utilityProfile(int depth){
        //System.out.println("Call to 8");
        //System.out.println("mypiece is " + me);
        return util;
    }


}
