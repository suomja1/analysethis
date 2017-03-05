package dna.analysethis;

import javax.swing.SwingUtilities;

/**
 * Basic DNA analysator. Analysethis is a program for analysing DNA sequences.
 * This is the main class.
 * @author Janne
 */
public class Analysethis {
    /**
     * The main method launches the graphical user interface of the analysator.
     * @param args  Possible arguments for main
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new UI());
    }
}