package dna.analysethis;

import dna.analysethis.service.SequenceAnalysator;
import dna.analysethis.utilities.Manipulator;
import java.awt.CardLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.io.FileNotFoundException;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;

public class UI implements Runnable {
    private JFrame frame;
    private JPanel panels;
    private JPanel indexPanel;
    final static String INDEX = "INDEX";
    private JPanel resultPanel;
    final static String RESULT = "RESULT";
    private SequenceAnalysator analysator;

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
        createIndexPanel();
        createResultPanel();
        
        this.panels = new JPanel(new CardLayout());
        this.panels.add(this.indexPanel, INDEX);
        this.panels.add(this.resultPanel, RESULT);
        
        showPanel(INDEX);
        
        this.frame.add(this.panels);
    }

    private void createIndexPanel() {
        this.indexPanel = new JPanel(new GridLayout(4, 1));
        
        JLabel text = new JLabel("Syötä DNA-sekvenssi.", SwingConstants.CENTER);
        this.indexPanel.add(text);

        JTextArea input = new JTextArea();
        input.setLineWrap(true);
        this.indexPanel.add(input);
        
        JButton button2 = new JButton("Satunnainen sekvenssi.");
        button2.addActionListener(a -> {
            String string = JOptionPane.showInputDialog(this.frame,
                    "Emästen lukumäärä:",
                    "Satunnainen sekvenssi",
                    JOptionPane.PLAIN_MESSAGE);
            
            try {
                input.setText(Manipulator.random(Integer.parseInt(string)).toString());
            } catch (IllegalArgumentException e) {
                JOptionPane.showMessageDialog(this.frame,
                        "Sekvenssin generointi epäonnistui.\nYritä uudelleen.",
                        "Virhe!",
                        JOptionPane.ERROR_MESSAGE);
            }
        });
        this.indexPanel.add(button2);

        JButton button = new JButton("Analysoi!");
        button.addActionListener(a -> {
            try {
                this.analysator = new SequenceAnalysator(input.getText());
                showPanel(RESULT);
            } catch (FileNotFoundException | IllegalArgumentException e) {
                JOptionPane.showMessageDialog(this.frame,
                        "Sekvenssinluku epäonnistui.\nYritä uudelleen.",
                        "Virhe!",
                        JOptionPane.ERROR_MESSAGE);
            }
        });
        this.indexPanel.add(button);
    }
    
    private void createResultPanel() {
        this.resultPanel = new JPanel(new GridLayout(8, 1));
        
        JLabel adenine = new JLabel("Adeniini: ");
        this.resultPanel.add(adenine);
        
        JLabel guanine = new JLabel("Guaniini: ");
        this.resultPanel.add(guanine);
        
        JLabel cytosine = new JLabel("Sytosiini: ");
        this.resultPanel.add(cytosine);
        
        JLabel thymine = new JLabel("Tymiini: ");
        this.resultPanel.add(thymine);
        
        JLabel unknown = new JLabel("Tuntematon emäs: ");
        this.resultPanel.add(unknown);
        
        JLabel total = new JLabel("Emästen lukumäärä: ");
        this.resultPanel.add(total);
        
        JLabel gc = new JLabel("GC%: ");
        this.resultPanel.add(gc);
        
        JButton button = new JButton("Aloita alusta.");
        button.addActionListener(a -> showPanel(INDEX));
        this.resultPanel.add(button);
    }
    
    private void showPanel(String card) {
        CardLayout cl = (CardLayout) (this.panels.getLayout());
        cl.show(this.panels, card);
    }
}