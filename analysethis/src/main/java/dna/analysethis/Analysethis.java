package dna.analysethis;

import javax.swing.SwingUtilities;

/**
 * Basic DNA analysator.
 * @author Janne
 */
public class Analysethis {
    /**
     * Starts the graphical user interface of the analysator.
     * @param args  Possible arguments for main.
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new UI());
    }
}