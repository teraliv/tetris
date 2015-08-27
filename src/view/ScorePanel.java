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
import java.util.Observable;
import java.util.Observer;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.border.TitledBorder;

import model.Board;

/**
 * This class creates score panel.
 * 
 * @author  Alex Terikov (teraliv@u.washington.edu)
 * @version May 24, 2015
 */
public class ScorePanel extends JPanel implements Observer {
    
    /** A generated serialization ID. */
    private static final long serialVersionUID = -1045650997186417248L;
    
    /** The default size of the panel. */
    private static final Dimension PANEL_SIZE = new Dimension(150, 120);
    
    /** The default label font. */
    private static final Font LABEL_FONT = new Font("Arial", Font.BOLD, 12);
    
    /** Single cleared line. */
    private static final int SINGLE_LINE = 1;
    
    /** Double cleared line. */
    private static final int DOUBLE_LINE = 2;
    
    /** Triple cleared line. */
    private static final int TRIPLE_LINE = 3;
    
    /** Four cleared line. */
    private static final int TETRIS_LINE = 4;
    
    /** The number of points for 1 cleared line. */
    private static final int SINGLE_POINTS = 100;
    
    /** The number of points for 2 cleared lines. */
    private static final int DOUBLE_POINTS = 300;
    
    /** The number of points for 2 cleared lines. */
    private static final int TRIPLE_POINTS = 500;
    
    /** The number of points for 4 cleared lines. */
    private static final int TETRIS_POINTS = 800;
    
    /** 2nd level. */
    private static final int LEVEL_2 = 2;
    
    /** 3rd level. */
    private static final int LEVEL_3 = 3;
    
    /** 4th level. */
    private static final int LEVEL_4 = 4;
    
    /** 5th level. */
    private static final int LEVEL_5 = 5;
    
    /** The number of cleared lines for level 2. */
    private static final int LEVEL_TWO_LINES = 5;
    
    /** The number of cleared lines for level 3. */
    private static final int LEVEL_THREE_LINES = 10;
    
    /** The number of cleared lines for level 4. */
    private static final int LEVEL_FOUR_LINES = 15;
    
    /** The number of cleared lines for level 5. */
    private static final int LEVEL_FIVE_LINES = 25;
    
    /** The timer delay for level 2. */
    private static final int LEVEL_TWO_DELAY = 400;
    
    /** The timer delay for level 3. */
    private static final int LEVEL_THREE_DELAY = 350;
    
    /** The timer delay for level 4. */
    private static final int LEVEL_FOUR_DELAY = 325;
    
    
    /** The timer delay for level 5. */
    private static final int LEVEL_FIVE_DELAY = 300;
    
    
    /** The default message for lines label. */
    private static final String LINES_LABEL_MSG = "Lines:   ";
    
    /** The default message for score label. */
    private static final String SCORE_LABEL_MSG = "Score:  ";
    
    /** The default message for level label. */
    private static final String LEVEL_LABEL_MSG = "Level:   ";
    
    /** The default message for next level label. */
    private static final String NEXT_LEVEL_LABEL_MSG = "Next level in: ";
    
    /** The default message for single cleared line label. */
    private static final String SINGLE_LABEL_MSG = "Single Line:   ";
    
    /** The default message for double cleared line label. */
    private static final String DOUBLE_LABEL_MSG = "Double Line:  ";
    
    /** The default message for triple cleared line label. */
    private static final String TRIPLE_LABEL_MSG = "Triple Line:    ";
    
    /** The default message for tetris cleared line label. */
    private static final String TETRIS_LABEL_MSG = "Tetris Line:    ";
    
    /** The lines label. */
    private JLabel myLinesLabel;
    
    /** The score label. */
    private JLabel myScoreLabel;
    
    /** The level label. */
    private JLabel myLevelLabel;
    
    /** The next level label. */
    private JLabel myNextLevelLabel;
    
    /** The single line label. */
    private JLabel mySingleLabel;
    
    /** The double line label. */
    private JLabel myDoubleLabel;
    
    /** The triple line label. */
    private JLabel myTripleLabel;
    
    /** The tetris line label. */
    private JLabel myTetrisLabel;
    
    /** The default timer for the tetris. */
    private final Timer myTimer;
    
    /** The number of cleared lines. */
    private int myLines;
    
    /** The score number. */
    private int myScore;
    
    /** The level number. */
    private int myLevel;
    
    /** The number of single line cleared.  */
    private int mySingleLines;
    
    /** The number of double line cleared. */
    private int myDoubleLines;
    
    /** The number of triple line cleared. */
    private int myTripleLines;
    
    /** The number of tetris line cleared. */
    private int myTetrisLines;
    
    /**
     * Constructs new score panel.
     * 
     * @param theBoard The Board that will use this JPanel.
     * @param theTimer The Timer that will use this JPanel.
     */
    public ScorePanel(final Board theBoard, final Timer theTimer) {
        super();
        setPreferredSize(PANEL_SIZE);
        setBackground(Color.WHITE);
        
        myTimer = theTimer;
        
        final Board board = theBoard;
        board.addObserver(this);
        
        init();
        setupBorder();
        printScore();
    }
    
    /**
     * This method initializes score values.
     */
    private void init() {
        final Color bg = new Color(55, 55, 65);
        setBackground(bg);
        
        myLevel = 1;
        myScore = 0;
        myLines = 0;
        mySingleLines = 0;
        myDoubleLines = 0;
        myTripleLines = 0;
        myTetrisLines = 0;
        
    }
    
    /**
     * This method creates a border for the panel.
     */
    private void setupBorder() {
        final TitledBorder title = BorderFactory.createTitledBorder(null, 
                                          "Score", TitledBorder.CENTER, 
                                          TitledBorder.TOP, LABEL_FONT, 
                                          Color.YELLOW);
        
        setBorder(title);
    }
    
    /**
     * This method prints the score information.
     */
    private void printScore() {
        
        final Box box = Box.createVerticalBox();
        
        myLinesLabel = new JLabel(LINES_LABEL_MSG + myLines);
        myScoreLabel = new JLabel(SCORE_LABEL_MSG + myScore);
        myLevelLabel = new JLabel(LEVEL_LABEL_MSG + myLevel);
        myNextLevelLabel = new JLabel(NEXT_LEVEL_LABEL_MSG + LEVEL_TWO_LINES + " line(s)");
        mySingleLabel = new JLabel(SINGLE_LABEL_MSG + mySingleLines);
        myDoubleLabel = new JLabel(DOUBLE_LABEL_MSG + myDoubleLines);
        myTripleLabel = new JLabel(TRIPLE_LABEL_MSG + myTripleLines);
        myTetrisLabel = new JLabel(TETRIS_LABEL_MSG + myTetrisLines);
        
        myLinesLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        myScoreLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        myLevelLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        myNextLevelLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        mySingleLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        myDoubleLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        myTripleLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        myTetrisLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        
        myLinesLabel.setForeground(Color.WHITE);
        myScoreLabel.setForeground(Color.WHITE);
        myLevelLabel.setForeground(Color.WHITE);
        myNextLevelLabel.setForeground(Color.WHITE);
        mySingleLabel.setForeground(Color.WHITE);
        myDoubleLabel.setForeground(Color.WHITE);
        myTripleLabel.setForeground(Color.WHITE);
        myTetrisLabel.setForeground(Color.WHITE);
        
        box.add(myLevelLabel);
        box.add(myScoreLabel);
        box.add(myLinesLabel);
        box.add(myNextLevelLabel);
        box.add(mySingleLabel);
        box.add(myDoubleLabel);
        box.add(myTripleLabel);
        box.add(myTetrisLabel);
        
        add(box);
    }
    
    /**
     * Gets the number of cleared lines from the Board.
     * 
     * {@inheritDoc}
     */
    @Override
    public void update(final Observable theObservable, final Object theData) {
        if (theObservable instanceof Board && theData instanceof Integer[]) {
            
            final int clearedLines = ((Integer[]) theData).length;
            
            calculateScore(clearedLines);
            calculateLevel();
            
            repaint();
        }
        
    }
    
    /**
     * This method calculates number of cleared lines.
     * 
     * @param theLines The number of cleared lines.
     */
    private void calculateScore(final int theLines) {
        
        final int clearedLines = theLines;
        
        myLines += clearedLines;
        myLinesLabel.setText(LINES_LABEL_MSG + myLines);
        
        // handle 1 cleared line
        if (clearedLines == SINGLE_LINE) {
            myScore += myLevel * SINGLE_POINTS;
            mySingleLines += clearedLines;
        
        // handle 2 cleared lines
        } else if (clearedLines == DOUBLE_LINE) {
            myScore += myLevel * DOUBLE_POINTS;
            myDoubleLines += clearedLines / clearedLines;
        
        // handle 3 cleared lines
        } else if (clearedLines == TRIPLE_LINE) {
            myScore += myLevel * TRIPLE_POINTS;
            myTripleLines += clearedLines / clearedLines;
        
        // handle 4 cleared lines
        } else if (clearedLines == TETRIS_LINE) {
            myScore += myLevel * TETRIS_POINTS;
            myTetrisLines += clearedLines / clearedLines;
        }
        
        myScoreLabel.setText(SCORE_LABEL_MSG + myScore);
        mySingleLabel.setText(SINGLE_LABEL_MSG + mySingleLines);
        myDoubleLabel.setText(DOUBLE_LABEL_MSG + myDoubleLines);
        myTripleLabel.setText(TRIPLE_LABEL_MSG + myTripleLines);
        myTetrisLabel.setText(TETRIS_LABEL_MSG + myTetrisLines);

    }
    
    /**
     * This method calculates the level.
     */
    private void calculateLevel() {
        
        calculateNextLevel(LEVEL_TWO_LINES);
        
        if (myLines >= LEVEL_TWO_LINES && myLines < LEVEL_THREE_LINES) {
            calculateNextLevel(LEVEL_THREE_LINES);
            
            myLevel = LEVEL_2;
            myLevelLabel.setText(LEVEL_LABEL_MSG + myLevel);
            myTimer.setDelay(LEVEL_TWO_DELAY);
        
        } else if (myLines >= LEVEL_THREE_LINES && myLines < LEVEL_FOUR_LINES) {
            calculateNextLevel(LEVEL_FOUR_LINES);
            
            myLevel = LEVEL_3;
            myLevelLabel.setText(LEVEL_LABEL_MSG + myLevel);
            myTimer.setDelay(LEVEL_THREE_DELAY);
        
        } else if (myLines >= LEVEL_FOUR_LINES && myLines < LEVEL_FIVE_LINES) {
            calculateNextLevel(LEVEL_FIVE_LINES);
            
            myLevel = LEVEL_4;
            myLevelLabel.setText(LEVEL_LABEL_MSG + myLevel);
            myTimer.setDelay(LEVEL_FOUR_DELAY);
        
        } else if (myLines >= LEVEL_FIVE_LINES) {
            myNextLevelLabel.setText("Final level.");
            
            myLevel = LEVEL_5;
            myLevelLabel.setText(LEVEL_LABEL_MSG + myLevel);
            myTimer.setDelay(LEVEL_FIVE_DELAY);
        }
        
    }
    
    /**
     * This method calculates when is the next level.
     * 
     * @param theLevelLines The number of lines to clear for current level.
     */
    private void calculateNextLevel(final int theLevelLines) {
        
        final int result = theLevelLines - myLines;
        myNextLevelLabel.setText(NEXT_LEVEL_LABEL_MSG + result + " line(s)");
    }
    
    /**
     * This method clears the current score.
     */
    public void clearScore() {
        myLines = 0;
        myScore = 0;
        myLevel = 1;
        myLinesLabel.setText(LINES_LABEL_MSG + myLines);
        myScoreLabel.setText(SCORE_LABEL_MSG + myScore);
        myLevelLabel.setText(LEVEL_LABEL_MSG + myLevel);
        mySingleLabel.setText(SINGLE_LABEL_MSG + mySingleLines);
        myDoubleLabel.setText(DOUBLE_LABEL_MSG + myDoubleLines);
        myTripleLabel.setText(TRIPLE_LABEL_MSG + myTripleLines);
        myTetrisLabel.setText(TETRIS_LABEL_MSG + myTetrisLines);
    }
}
