package com.company;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
/**
 * Created by gideonpotok on 11/5/16.
 */
public class Hexgame {



    Hexgame() {
        initGame();
        createAndShowGUI();
    }

    public static void doMain()
    {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new Hexgame();
            }
        });
    }

    //constants and global variables
    final static Color COLOURBACK =  Color.BLACK;
    final static Color COLOURCELL =  Color.ORANGE;
    final static Color COLOURGRID =  Color.ORANGE;
    final static Color COLOURONE = Color.GREEN;
    final static Color COLOURONETXT = Color.WHITE;
    final static Color COLOURTWO = Color.pink;
    final static Color COLOURTWOTXT = Color.BLACK;
    final static Color COLOURTHREE = Color.cyan;
    final static Color COLOURTHREETXT = Color.ORANGE;
    final static int EMPTY = 0;
    final static int BSIZE = 21; //board size.
    final static int HEXSIZE = 60;	//hex size in pixels
    final static int BORDERS = 100;
    final static int SCRSIZE = HEXSIZE * (BSIZE + 1) + BORDERS*3; //screen size (vertical dimension).

    int[][] board = new int[BSIZE][BSIZE];
    void initGame(){

        Hexmech.setXYasVertex(false); //RECOMMENDED: leave this as FALSE.

        Hexmech.setHeight(HEXSIZE); //Either setHeight or setSize must be run to initialize the hex
        Hexmech.setBorders(BORDERS);

        for (int i=0;i<BSIZE;i++) {
            for (int j=0;j<BSIZE;j++) {
                board[i][j]=EMPTY;
            }
        }

    }

    private void createAndShowGUI()
    {
        DrawingPanel panel = new DrawingPanel();


        //JFrame.setDefaultLookAndFeelDecorated(true);
        JFrame frame = new JFrame("Four in a Row Game!");
        frame.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
        Container content = frame.getContentPane();
        content.add(panel);
        //this.add(panel);  -- cannot be done in a static context
        //for hexes in the FLAT orientation, the height of a 10x10 grid is 1.1764 * the width. (from h / (s+t))
        frame.setSize( (int)(SCRSIZE/1.23), SCRSIZE);
        frame.setResizable(false);
        frame.setLocationRelativeTo( null );
        frame.setVisible(true);
    }


class DrawingPanel extends JPanel
{
    //mouse variables here
    //Point mPt = new Point(0,0);

    public DrawingPanel()
    {
        setBackground(COLOURBACK);

        MyMouseListener ml = new MyMouseListener();
        addMouseListener(ml);
    }

    public void paintComponent(Graphics g)
    {
        Graphics2D g2 = (Graphics2D)g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g.setFont(new Font("TimesRoman", Font.PLAIN, 20));
        super.paintComponent(g2);
        //draw grid
        for (int i=0;i<BSIZE;i++) {
            for (int j=0;j<BSIZE;j++) {
                Hexmech.drawHex(i,j,g2);
            }
        }
        //fill in hexes
        for (int i=0;i<BSIZE;i++) {
            for (int j=0;j<BSIZE;j++) {
                //if (board[i][j] < 0) Hexmech.fillHex(i,j,COLOURONE,-board[i][j],g2);
                //if (board[i][j] > 0) Hexmech.fillHex(i,j,COLOURTWO, board[i][j],g2);
                boolean found = false;

                Point p = new Point(i,j);
                Integer coor = Hexmech.zeroTo110.get(p);
                if(coor != null){
                    found = true;
                    //System.out.println("coor is " + coor.toString());
                    BoardPiece piece = Main.board[coor];
                    int n = 0;
                    if(piece == BoardPiece.BLUE) {
                        n = 1;
                    } else if (piece == BoardPiece.GOLD) {
                        n = 2;
                    }else {
                        n = 3;
                    }
                    Hexmech.fillHex(p, n,g2);
                }
                if(!found){
                    Hexmech.fillHex(new Point(i,j),-1,g2);
                }

            }
        }

    }

    class MyMouseListener extends MouseAdapter	{	//inner class inside DrawingPanel
        public void mouseClicked(MouseEvent e) {
            int x = e.getX();
            int y = e.getY();
            //mPt.x = x;
            //mPt.y = y;
            Point p = new Point( Hexmech.pxtoHex(e.getX(),e.getY()) );
            if (p.x < 0 || p.y < 0 || p.x >= BSIZE || p.y >= BSIZE) return;

            //What do you want to do when a hexagon is clicked?
            Computer p2 = null;

            p2 = Main.click(Hexmech.zeroTo110.get(p));

            repaint();
            if(Main.p2 == PlayerType.COMPUTER && p2 != null){
                Integer[]  latestTurn = new Integer[2];
                final long start = System.nanoTime();
                //do {
                    Main.currentTurn = BoardPiece.GOLD;
                    latestTurn = p2.getTurn();


                //} while (System.nanoTime()-start < 5L*1000L*1000L*1000L);
                Main.click(latestTurn[0]);
                p2 = Main.click(latestTurn[1]);
            }
        }
    }
} //end of MyMouseListener class
	} // end of DrawingPanel class
