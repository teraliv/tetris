/*
 * TCSS 305 - Spring 2015
 * 
 * Assignment 6 - Tetris.
 * Alex Terikov
 */
 
package view;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JPanel;

import model.Board;
import model.Point;
 
/**
 * This class creates tetris board to draw pieces.
 * 
 * @author Alex Terikov (teraliv@u.washington.edu)
 * @version May 24, 2015
 */
public class TetrisPanel extends JPanel implements Observer {
 
    /** A generated serialization ID. */
    private static final long serialVersionUID = -8374538314410025360L;
     
    /** The default size of tetris pieces board. */
    private static final Dimension BOARD_SIZE = new Dimension(201, 401);
    
    /** The default size of the tetris piece block. */
    private static final int BLOCK_SIZE = 20;
    
    /** The selection status. */
    private boolean mySelected;
    
    /** A list of color to draw tetris pieces. */
    private final List<Color[]> myList;
    
   
    /**
     * Constructs tetris board and initializes all fields.
     */
    public TetrisPanel() {
        super();
        
        myList = new ArrayList<Color[]>();
        mySelected = false;
        
        final Color bg = new Color(55, 55, 65);
        setBackground(bg);
        setPreferredSize(BOARD_SIZE);
    }
    
    /** 
     * Draws tetris board on the panel.
     * 
     * {@inheritDoc} 
     */
    @Override
    protected void paintComponent(final Graphics theGraphics) {
        super.paintComponent(theGraphics);
        
        final Graphics2D g2d = (Graphics2D) theGraphics;
        
        Image image = null;
        
        for (int i = 0; i < myList.size(); i++) {
            
            final Color[] colorArray = myList.get(i);
            
            for (int j = 0; j < colorArray.length; j++) {
                
                final Point point = new Point(j * BLOCK_SIZE, i * BLOCK_SIZE);
                final Color color = colorArray[j];
                
                image = getBlockImage(color);
                
                // draw blocks with observed colors
                //g2d.setColor(color);
                /*if (colorArray[j] != null) {
                    g2d.fill(new Rectangle2D.Double(point.x(), 
                                                    getHeight() - point.y() - BLOCK_SIZE, 
                                                    BLOCK_SIZE, BLOCK_SIZE));
                }*/
                
                // draw block with images
                if (colorArray[j] != null) {
                    g2d.drawImage(image, point.x(), 
                                  getHeight() - point.y() - BLOCK_SIZE, this);
                }
            }
        }
        
        // Draw grid
        if (mySelected) {
            
            final int height = getHeight();
            final int width = getWidth();
            
            final Color gridColor = new Color(43, 42, 48);
            final BasicStroke gridStroke = new BasicStroke(1);
            
            g2d.setStroke(gridStroke);
            g2d.setColor(gridColor);
            
            // Draw vertical lines
            for (int column = 0; column < width; column += BLOCK_SIZE) {
                g2d.drawLine(column, 0, column, height);
            }
            
            // Draw horizontal lines
            for (int row = 0; row < height; row += BLOCK_SIZE) {
                g2d.drawLine(0, row, width, row);
            }
            
            repaint();
        }
        
    } 
    
    /**
     * This method selects a block image for a particular piece color.
     * 
     * @param theColor The color of the tetris piece.
     * @return The block image for specified color.
     */
    private Image getBlockImage(final Color theColor) {
        
        Image image = null;
        
        final Color pieceL = new Color(233, 164, 0);
        final Color pieceT = new Color(185, 33, 255);
        
        if (theColor != null) {
            // J
            if (theColor == Color.BLUE) {
                image = Toolkit.getDefaultToolkit().getImage("res/images/green.png");
            
            // Z
            } else if (theColor == Color.RED) {
                image = Toolkit.getDefaultToolkit().getImage("res/images/orange.png");
            
            // I
            } else if (theColor == Color.CYAN) {
                image = Toolkit.getDefaultToolkit().getImage("res/images/red.png");
            
            // L
            } else if (theColor.getRGB() == pieceL.getRGB()) {
                image = Toolkit.getDefaultToolkit().getImage("res/images/purple.png");
            
            // O
            } else if (theColor == Color.YELLOW) {
                image = Toolkit.getDefaultToolkit().getImage("res/images/blue.png");
            
            // S
            } else if (theColor == Color.GREEN) {
                image = Toolkit.getDefaultToolkit().getImage("res/images/yellow.png");
            
            // T
            } else if (theColor.getRGB() == pieceT.getRGB()) {
                image = Toolkit.getDefaultToolkit().getImage("res/images/pink.png");
            }
        }
        
        return image;
    }
    
    /**
     * This method draws grid on the tetris panel.
     * 
     * @param theSelected The selection status.
     */
    public void drawGrid(final boolean theSelected) {
        mySelected = theSelected;
    }
    
    /** 
     * Gets the data about tetris board.
     * 
     * {@inheritDoc} 
     */
    @Override
    public void update(final Observable theObservable, final Object theData) {
        
        if (theObservable instanceof Board && theData instanceof List) {
            myList.clear();
            
            for (int i = 0; i < ((List) theData).size(); i++) {
                final Color[] colorArray = (Color[]) ((List) theData).get(i);
                myList.add(colorArray);
            }
        }
        
        repaint(); 
    }

}