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
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.util.Observable;
import java.util.Observer;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import model.Board;
import model.Point;
import model.TetrisPiece;

/**
 * This class creates next tetris piece panel.
 * 
 * @author Alex Terikov (teraliv@u.washington.edu)
 * @version May 24, 2015
 */
public class NextPiecePanel extends JPanel implements Observer {

    /** A default serialization ID. */
    private static final long serialVersionUID = 4342848274444040935L;
    
    /** The default size of the panel. */
    private static final Dimension PANEL_SIZE = new Dimension(165, 45);
    
    /** The default label font. */
    private static final Font LABEL_FONT = new Font("Arial", Font.BOLD, 12);
    
    /** The default size of the tetris piece block. */
    private static final int BLOCK_SIZE = 20;
    
    /** The default size to align piece to center. */
    private static final int SHIFT_X = 40;
    
    /** The default size to align piece to center. */
    private static final int SHIFT_Y = 40;
    
    /** Piece that is next to play. */
    private TetrisPiece myNextPiece;

    /**
     * Constructs a next piece panel.
     */
    public NextPiecePanel() {
        super();
        
        final Color bg = new Color(55, 55, 65);
        setBackground(bg);
        setPreferredSize(PANEL_SIZE);
        
        setupBorder();
    }
    
    /**
     * This method creates a border for the panel.
     */
    private void setupBorder() {
        final TitledBorder title = BorderFactory.createTitledBorder(null, 
                                               "Next", TitledBorder.CENTER, 
                                               TitledBorder.TOP, LABEL_FONT, 
                                               Color.YELLOW);
        setBorder(title);
    }
    
    /** 
     * {@inheritDoc} 
     */
    @Override
    protected void paintComponent(final Graphics theGraphics) {
        super.paintComponent(theGraphics);
        final Graphics2D g2d = (Graphics2D) theGraphics;
        
        Image image = null;
        
        if (myNextPiece != null) {
            
            image = getBlockImage(myNextPiece.getColor());
            
            for (int i = 0; i < myNextPiece.getPoints().length; i++) {
                
                final Point point = myNextPiece.getPoints()[i];
                
                g2d.drawImage(image, (point.x() * BLOCK_SIZE) + SHIFT_X, 
                              getHeight() - (point.y() * BLOCK_SIZE) - SHIFT_Y, this);
            }
        }
        
        
        final int height = 101;
        final int width = 160;
        final int wSize = 100;
        final int hSize = 140;
        
        final Color gridColor = new Color(91, 91, 101);
        final BasicStroke gridStroke = new BasicStroke(1);
        
        g2d.setStroke(gridStroke);
        g2d.setColor(gridColor);
        
        // Draw vertical lines
        for (int column = BLOCK_SIZE; column < width; column += BLOCK_SIZE) {
            g2d.drawLine(column, BLOCK_SIZE, column, wSize);
        }
        
        // Draw horizontal lines
        for (int row = BLOCK_SIZE; row < height; row += BLOCK_SIZE) {
            g2d.drawLine(BLOCK_SIZE, row, hSize, row);
        }
        
    }
    
    /** 
     * Gets the next tetris piece from the Board.
     * 
     * {@inheritDoc} 
     */
    @Override
    public void update(final Observable theObservable, final Object theData) {
        
        if (theObservable instanceof Board && theData instanceof TetrisPiece) {
            myNextPiece = (TetrisPiece) theData;
        }

        repaint();
    }
    
    /**
     * This method select block image for a piece color.
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
}
