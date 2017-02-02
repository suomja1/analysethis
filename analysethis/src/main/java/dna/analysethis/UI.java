package dna.analysethis;

import java.awt.Container;
import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.WindowConstants;

public class UI implements Runnable {
    private JFrame frame;

    public UI() {
    }

    public JFrame getFrame() {
        return this.frame;
    }
    
    @Override
    public void run() {
        this.frame = new JFrame("analysethis");
        this.frame.setPreferredSize(new Dimension(300, 534));
        this.frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.createComponents(this.frame.getContentPane());
        this.frame.pack();
        this.frame.setVisible(true);
    }
    
    private void createComponents(Container container) {
    }
}