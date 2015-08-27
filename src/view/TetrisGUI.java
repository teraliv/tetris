/*
 * TCSS 305 - Spring 2015
 * 
 * Assignment 6 - Tetris.
 * Alex Terikov
 */
 
package view;
 
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.WindowEvent;
import java.util.Observable;
import java.util.Observer;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.KeyStroke;
import javax.swing.Timer;

import model.Board;
 
/**
 * Tetris Graphical User Interface.
 * 
 * @author Alex Terikov (teraliv@u.washington.edu)
 * @version May 24, 2015
 */
public class TetrisGUI implements Observer {
     
    // constants to capture screen dimensions
    /** A ToolKit. */
    private static final Toolkit KIT = Toolkit.getDefaultToolkit();
    
    /** The Dimension of the screen. */
    private static final Dimension SCREEN_SIZE = KIT.getScreenSize();
    
    /** The width of the screen. */
    private static final int SCREEN_WIDTH = SCREEN_SIZE.width;
    
    /** The height of the screen. */
    private static final int SCREEN_HEIGHT = SCREEN_SIZE.height;

    
    /** The default timer delay. */
    private static final int TIMER_INITIAL_DELAY = 500;
    
    /** A default font for "Game Over" message. */
    private static final Font GAME_OVER_FONT = new Font("Arial", 3, 30);
    
    /** The JFrame window for the tetris. */
    private final JFrame myFrame;
    
    /** A Timer for the tetris. */
    private Timer myTimer;
    
    /** The Tetris board.  */
    private Board myBoard;
    
    /** The status of the game. */
    private Boolean myGameStatus;
    
    /** The end game button. */
    private JMenuItem myEndGameButton;
    
    /** The pause button. */
    private JMenuItem myPauseButton;
    
    /**  The new game button. */
    private JMenuItem myNewGameButton;
    
    /** The quit button. */
    private JMenuItem myQuitButton;
    
    /** The grid selection button. */
    private JCheckBoxMenuItem myGridButton;
    
    /** The control of tetris pieces. */
    private TetrisControls myControls;
    
    /** The score panel of the tetris. */
    private ScorePanel myScorePanel;
    
    /** The main tetris panel to display pieces. */
    private TetrisPanel myTetrisPanel;
    
    /** The game over label. */
    private JLabel myGameOverLabel;
    
    /**
     * Constructs Tetris GUI and initializes all fields.
     */
    public TetrisGUI() {
        myFrame = new JFrame("TCSS 305 Tetris");
        
        myGameStatus = false;
        setupGUI();
    }
     
    /**
     * Sets up graphical components.
     */
    private void setupGUI() {
        myTimer = new Timer(TIMER_INITIAL_DELAY, new TimerListener());
        
        final BackgroundPanel panel = new BackgroundPanel();
        myTetrisPanel = new TetrisPanel();
        myTetrisPanel.setLayout(new GridBagLayout()); 
        panel.add(myTetrisPanel);
        
        final JMenuBar menuBar = setupMenuBar();
        myFrame.setJMenuBar(menuBar);
        
        myBoard = new Board();
        myControls = new TetrisControls(myBoard);
        myControls.setPieceMovable(false);
        
        final JPanel sidePanel = new JPanel();
        final BoxLayout boxLayout = new BoxLayout(sidePanel, BoxLayout.PAGE_AXIS);
        sidePanel.setLayout(boxLayout);
        
        final NextPiecePanel nextPiecePanel = new NextPiecePanel();
        final ControlPanel controlPanel = new ControlPanel();
        myScorePanel = new ScorePanel(myBoard, myTimer);
        
        final Box sideBox = new Box(BoxLayout.PAGE_AXIS);
        sideBox.add(nextPiecePanel);
        sideBox.add(myScorePanel);
        sideBox.add(controlPanel);
        
        myGameOverLabel = new JLabel("Game Over");
        myGameOverLabel.setFont(GAME_OVER_FONT);
        myGameOverLabel.setForeground(Color.RED);
        
        sidePanel.add(sideBox);
        myFrame.add(sidePanel, BorderLayout.EAST);
        myFrame.addKeyListener(myControls);
        myFrame.add(panel);
        myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        myFrame.setLocation(SCREEN_WIDTH / 2 - myFrame.getWidth() / 2, 
                    SCREEN_HEIGHT / 2 - myFrame.getHeight() / 2);
        myFrame.setResizable(false);
        myFrame.setVisible(true);
        myFrame.pack();
        
        myBoard.addObserver(nextPiecePanel);
        myBoard.addObserver(myTetrisPanel);
        myBoard.addObserver(this);
    }

    /** 
     * Check if the game is over and displays message.
     * 
     * {@inheritDoc} 
     */
    @Override
    public void update(final Observable theObservable, final Object theData) {
        if (theObservable instanceof Board && theData instanceof Boolean) {
            myTimer.stop();
            myPauseButton.setEnabled(false);
            myEndGameButton.setEnabled(false);
            myNewGameButton.setEnabled(true);
            myTetrisPanel.add(myGameOverLabel);
            myFrame.validate();
        }
    }
    
    
    // MenuBar 
    /**
     * This method returns the menu bar for the window.
     * 
     * @return Returns menu bar.
     */
    private JMenuBar setupMenuBar() {
        
        final JMenuBar menuBar = new JMenuBar();
        
        final JMenu fileMenu = setupFileMenu();
        final JMenu optionsMenu = setupOptionMenu();
        final JMenu helpMenu = setupHelpMenu();
        
        menuBar.add(fileMenu);
        menuBar.add(optionsMenu);
        menuBar.add(helpMenu);
        
        return menuBar;
    }
    
    /**
     * This method returns the file menu in the menu bar.
     * 
     * @return Returns the file menu.
     */
    private JMenu setupFileMenu() {
        
        final MenuActionListener menuActions = new MenuActionListener();
        final JMenu fileMenu = new JMenu("File");
        fileMenu.setMnemonic(KeyEvent.VK_F);
        
        myNewGameButton = new JMenuItem("New Game", KeyEvent.VK_N);
        myNewGameButton.addActionListener(menuActions);
        
        myEndGameButton = new JMenuItem("End Game", KeyEvent.VK_E);
        myEndGameButton.setEnabled(false);
        myEndGameButton.addActionListener(menuActions);
        
        myQuitButton = new JMenuItem("Quit", KeyEvent.VK_Q);
        myQuitButton.addActionListener(menuActions);
        
        myPauseButton = new JMenuItem("Pause/Resume", KeyEvent.VK_P);
        myPauseButton.setEnabled(false);
        myPauseButton.setAccelerator(KeyStroke.getKeyStroke('p'));
        myPauseButton.addActionListener(menuActions);
        
        fileMenu.add(myNewGameButton);
        fileMenu.add(myEndGameButton);
        fileMenu.addSeparator();
        fileMenu.add(myPauseButton);
        fileMenu.addSeparator();
        fileMenu.add(myQuitButton);
        
        return fileMenu;
    }
    
    /**
     * This method returns the options menu in the menu bar.
     * 
     * @return Returns options menu.
     */
    private JMenu setupOptionMenu() {
        
        final JMenu optionMenu = new JMenu("Option");
        optionMenu.setMnemonic(KeyEvent.VK_O);
        
        // Grid Check Box
        myGridButton = new JCheckBoxMenuItem("Grid");
        myGridButton.setMnemonic(KeyEvent.VK_G);
        myGridButton.addActionListener(new ActionListener() {
            
            @Override
            public void actionPerformed(final ActionEvent theEvent) {
                
                if (myGridButton.isSelected()) {
                    myTetrisPanel.drawGrid(true);
                    myTetrisPanel.repaint();
                
                } else {
                    myTetrisPanel.drawGrid(false);
                }
            }
        });
        optionMenu.add(myGridButton);
        
        return optionMenu;
    }
    
    /**
     * This method returns the help menu in the menu bar.
     * 
     * @return Returns help menu.
     */
    private JMenu setupHelpMenu() {
        final JMenu helpMenu = new JMenu("Help");
        helpMenu.setMnemonic(KeyEvent.VK_H);
        
        final JMenuItem credits = new JMenuItem("Credits", KeyEvent.VK_C);
        credits.addActionListener(new ActionListener() {
            
            @Override
            public void actionPerformed(final ActionEvent theEvent) {
                JOptionPane.showMessageDialog(myFrame, 
                                "The graphic elements for TCSS 305 Tetris "
                                + "game was made me using turolial from \n"
                                + "design.tutsplus.com/tutorials/how-to-create-a-detailed-"
                                + "tetris-game-interface-in-idraw--cms-22433 \n",
                                "Tetris Credits", JOptionPane.INFORMATION_MESSAGE);
            }
        });
        
        final JMenuItem score = new JMenuItem("Score", KeyEvent.VK_S);
        score.addActionListener(new ActionListener() {
            
            @Override
            public void actionPerformed(final ActionEvent theEvent) {
                JOptionPane.showMessageDialog(myFrame, 
                                 "Tetris starts with Level 1 \n"
                               + "Level increases every 5 cleared lines \n"
                               + "For every cleared line you get score points. \n"
                               + "Score point vary, depends on the nuber of cleared lines. \n"
                               + "For single cleared line: you get Level x 100 points. \n"
                               + "For double cleared line: you get Level x 300 points. \n"
                               + "For triple cleared line: you get Level x 500 points. \n"
                               + "For tetris(4) cleared line: you get Level x 800 points. \n", 
                                 "Tetris Score", JOptionPane.INFORMATION_MESSAGE);
            }
        });
        
        helpMenu.add(credits);
        helpMenu.add(score);
        
        return helpMenu;
    
    } // End of MenuBar
    
    
    // Inner Classes
   
    /**
     * Inner class for timer to process board with a step. 
     * 
     * @author Alex Terikov (teraliv@u.washington.edu)
     * @version May 24, 2015
     */
    private class TimerListener implements ActionListener {

        @Override
        public void actionPerformed(final ActionEvent theEvent) {
            myBoard.step();
        }
    }
    
    
    /**
     * Inner class that creates action listeners for tetris menu bar.
     * 
     * @author Alex Terikov (teraliv@u.washington.edu)
     * @version May 24, 2015
     */
    private class MenuActionListener implements ActionListener {

        @Override
        public void actionPerformed(final ActionEvent theEvent) {
            
            // New Game
            if (theEvent.getSource() == myNewGameButton) {
                myTimer.start();
                myBoard.newGame();
                myScorePanel.clearScore();
                myGameStatus = true;
                myControls.setPieceMovable(true);
                myNewGameButton.setEnabled(false);
                myEndGameButton.setEnabled(true);
                myPauseButton.setEnabled(true);
                myTetrisPanel.remove(myGameOverLabel);
            }
            
            // End Game
            if ((theEvent.getSource() == myEndGameButton) && myGameStatus) {
                myTimer.stop();
                myControls.setPieceMovable(false);
                myEndGameButton.setEnabled(false);
                myNewGameButton.setEnabled(true);
                myPauseButton.setEnabled(false);
                myTetrisPanel.add(myGameOverLabel);
                myTetrisPanel.validate();
                myTetrisPanel.repaint();
            }
            
            // Pause/Resume Game
            if (theEvent.getSource() == myPauseButton) {
                
                // pause
                if (myTimer.isRunning()) {
                    myTimer.stop();
                    myControls.setPieceMovable(false);
                
                // resume
                } else {
                    myTimer.start();
                    myControls.setPieceMovable(true);
                }
            }
            
            // Quit Game
            if (theEvent.getSource() == myQuitButton) {
                myFrame.dispatchEvent(new WindowEvent(myFrame,
                                                      WindowEvent.WINDOW_CLOSING));
            }
            
        }
        
    }
    
    
    /**
     * Inner class that creates a picture background on the tetris frame.
     * 
     * @author Alex Terikov (teraliv@u.washington.edu)
     * @version May 24, 2015
     */
    private final class BackgroundPanel extends JPanel {
        
        /** A default serialization ID. */
        private static final long serialVersionUID = 1073937863574422710L;
        
        /** The window background picture. */
        private final Image myImage;
        
        /**
         * Constructs a panel with an image on the background.
         */
        public BackgroundPanel() {
            super();
            
            setLayout(new GridBagLayout());
            final ImageIcon image = new ImageIcon("res/images/frame_bg.png");
            myImage = image.getImage();
            setPreferredSize(new Dimension(myImage.getWidth(null), myImage.getHeight(null)));
        }
        
        /**
         * {@inheritDoc}
         */
        @Override
        protected void paintComponent(final Graphics theGraphics) {
            super.paintComponent(theGraphics);
            
            setOpaque(false);
            theGraphics.drawImage(myImage, 0, 0, null);
            
        }
    }
    
}