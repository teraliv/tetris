/*
 * TCSS 305 - Spring 2015
 * 
 * Assignment 6 - Tetris.
 * Alex Terikov
 */

package view;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import model.Board;

/**
 * This class creates keyboards events to control tetris pieces.
 * 
 * @author Alex Terikov (teraliv@u.washington.edu)
 * @version May 24, 2015
 */
public class TetrisControls extends KeyAdapter {
    
    /** Move left control key. */
    private final String myLeftKey;
    
    /** Move right control key. */
    private final String myRightKey;
    
    /** Rotate Left control key. */
    private final String myRotateLeftKey;
    
    /** Rotate right control key. */
    private final String myRotateRightKey;
    
    /** Rotate right control key. */
    private final String myRotateRightKey2;
    
    /** Soft drop control key. */
    private final String mySoftDropKey;
    
    /** Hard drop control key. */
    private final String myHardDropKey;
    
    /** The Tetris board.  */
    private final Board myBoard;
    
    /** The status of a piece. */
    private Boolean myPieceMovable;
    
    
    /**
     * Constructs new tetris controls.
     * 
     * @param theBoard The tetris Board.
     */
    public TetrisControls(final Board theBoard) {
        super();
        
        myLeftKey = "Left";
        myRightKey = "Right";
        myRotateRightKey2 = "Up";
        myRotateLeftKey = "Z";
        myRotateRightKey = "X";
        mySoftDropKey = "Down";
        myHardDropKey = "Space";
        
        myBoard = theBoard;
        myPieceMovable = true;
    }
    
    
    /**
     * This method sets the movable state of a piece.
     * 
     * @param theState The state of a piece.
     */
    public void setPieceMovable(final Boolean theState) {
        myPieceMovable = theState;
    }
    
    /**
     * This method creates events to control tetris pieces.
     * 
     * {@inheritDoc}
     */
    @Override
    public void keyPressed(final KeyEvent theEvent) {
        super.keyPressed(theEvent);
        
        final String keyText = KeyEvent.getKeyText(theEvent.getKeyCode());
        
        if (myPieceMovable) {
            
            // handle MOVE LEFT
            if (keyText.equals(myLeftKey)) {
                myBoard.left();
            
            // handle MOVE RIGHT
            } else if (keyText.equals(myRightKey)) {
                myBoard.right();
            
            // handle ROTATE LEFT
            } else if (keyText.equals(myRotateLeftKey)) {
                myBoard.rotateCCW();
            
            // handle ROTATE RIGHT
            } else if (keyText.equals(myRotateRightKey) || keyText.equals(myRotateRightKey2)) {
                myBoard.rotateCW();
            
            // handle SOFT DROP
            } else if (keyText.equals(mySoftDropKey)) {
                myBoard.down();
            
            // handle HARD DROP
            } else if (keyText.equals(myHardDropKey)) {
                myBoard.drop();
            }
        }
    }
}
