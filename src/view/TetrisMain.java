/*
 * TCSS 305 - Spring 2015
 * 
 * Assignment 6 - Tetris.
 * Alex Terikov
 */

package view;

import java.awt.EventQueue;

/**
 * Runs Tetris by instantiation and starting TetrisGUI.
 * 
 * @author Alex Terikov (teraliv@u.washington.edu)
 * @version May 24, 2015
 */
public final class TetrisMain {
    
    /**
     * private constructor to prevent external instantiation.
     */
    private TetrisMain() {
        throw new IllegalStateException();
    }
    
    /**
     * The starter method for TetrisGUI class.
     * 
     * @param theArgs Command line arguments.
     */
    public static void main(final String... theArgs) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new TetrisGUI(); // create the graphical user interface
            }
        });
    }
    
}
