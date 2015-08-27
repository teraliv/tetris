/*
 * TCSS 305 - Spring 2015
 * 
 * Assignment 6 - Tetris.
 * Alex Terikov
 */

package view;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

/**
 * This class creates tetris controls panel.
 * 
 * @author Alex Terikov (teraliv@u.washington.edu)
 * @version May 24, 2015
 */
public class ControlPanel extends JPanel {
    
    /** A generated serialization ID.  */
    private static final long serialVersionUID = -6899658540250320208L;
    
    /** The default size of the panel. */
    private static final Dimension PANEL_SIZE = new Dimension(150, 110);
    
    /** The default label font. */
    private static final Font LABEL_FONT = new Font("Arial", Font.BOLD, 12);
    
    /**
     * Constructs new tetris controls panel.
     */
    public ControlPanel() {
        super();
        
        final Color bg = new Color(55, 55, 65);
        setBackground(bg);
        setPreferredSize(PANEL_SIZE);
        
        setupBorder();
        setupMessage();
    }
    
    /**
     * This method creates a border for the panel.
     */
    private void setupBorder() {
        final TitledBorder title = BorderFactory.createTitledBorder(null, 
                                           "Control", TitledBorder.CENTER, 
                                           TitledBorder.TOP, LABEL_FONT, 
                                           Color.YELLOW);
        
        setBorder(title);
    }
    
    /**
     * This method creates a how to control information for the tetris.
     */
    private void setupMessage() {
        final Box box = Box.createVerticalBox();
        
        final JLabel left = new JLabel("Move Left:      Left");
        final JLabel right = new JLabel("Move Right:    Right");
        final JLabel rotateLeft = new JLabel("Rotate Left:    Z");
        final JLabel rotateRight = new JLabel("Rotate Right:  X or Up");
        final JLabel softDrop = new JLabel("Soft Drop:       Down");
        final JLabel hardDrop = new JLabel("Hard Drop:     Space");
        final JLabel pause = new JLabel("Pause:            P");
        
        
        left.setAlignmentX(Component.LEFT_ALIGNMENT);
        right.setAlignmentX(Component.LEFT_ALIGNMENT);
        rotateLeft.setAlignmentX(Component.LEFT_ALIGNMENT);
        rotateRight.setAlignmentX(Component.LEFT_ALIGNMENT);
        softDrop.setAlignmentX(Component.LEFT_ALIGNMENT);
        hardDrop.setAlignmentX(Component.LEFT_ALIGNMENT);
        pause.setAlignmentX(Component.LEFT_ALIGNMENT);
        
        left.setForeground(Color.WHITE);
        right.setForeground(Color.WHITE);
        rotateLeft.setForeground(Color.WHITE);
        rotateRight.setForeground(Color.WHITE);
        softDrop.setForeground(Color.WHITE);
        hardDrop.setForeground(Color.WHITE);
        pause.setForeground(Color.WHITE);
        
        box.add(left);
        box.add(right);
        box.add(rotateLeft);
        box.add(rotateRight);
        box.add(softDrop);
        box.add(hardDrop);
        box.add(pause);
        
        add(box);
    }
}
