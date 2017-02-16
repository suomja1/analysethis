package dna.analysethis;

import dna.analysethis.domain.Base;
import dna.analysethis.service.SequenceAnalysator;
import dna.analysethis.utilities.FileHandler;
import dna.analysethis.utilities.Manipulator;
import java.awt.CardLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.io.IOException;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
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
        
        this.panels = new JPanel(new CardLayout());
        this.panels.add(this.indexPanel, INDEX);
        
        showPanel(INDEX);
        
        this.frame.add(this.panels);
    }

    private void createIndexPanel() {
        this.indexPanel = new JPanel(new GridLayout(4, 1));
        
        JLabel text = new JLabel("Syötä DNA-sekvenssi.", SwingConstants.CENTER);
        this.indexPanel.add(text);

        JTextArea input = new JTextArea(8, 1);
        input.setLineWrap(true);
        JScrollPane scroll = new JScrollPane(input);
        this.indexPanel.add(scroll);
        
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
                
                createResultPanel();
                this.panels.add(this.resultPanel, RESULT);
                
                showPanel(RESULT);
            } catch (IOException | IllegalArgumentException e) {
                JOptionPane.showMessageDialog(this.frame,
                        "Sekvenssinluku epäonnistui.\nYritä uudelleen.",
                        "Virhe!",
                        JOptionPane.ERROR_MESSAGE);
            }
        });
        this.indexPanel.add(button);
    }
    
    private void createResultPanel() {
        this.resultPanel = new JPanel(new GridLayout(4, 1));
        String resultsText = "";
        
        for (Base b : Base.values()) {
            resultsText += b.getName() 
                    + ": "
                    + this.analysator.frequency(b)
                    + " (" + Math.round(this.analysator.relativeFrequency(b) * 10000.0) / 100.0 + " %)"
                    + "\n";
        }
        
        resultsText += "Emästen lukumäärä: " + this.analysator.numberOfBases() + "\n"
                + "GC%: " + Math.round(this.analysator.gcContent() * 10000.0) / 100.0 + " %";
        
        JTextArea results = new JTextArea(resultsText);
        results.setEditable(false);
        this.resultPanel.add(results);
        
        JButton printButton = new JButton("Tulosta.");
        String resultsText2 = resultsText;
        printButton.addActionListener(a -> {
            boolean success = FileHandler.write(resultsText2);
            
            if (!success) {
                JOptionPane.showMessageDialog(this.frame,
                        "Tulostus epäonnistui.",
                        "Virhe!",
                        JOptionPane.ERROR_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this.frame,
                        "Tiedosto 'tuloste.txt' kirjoitettu.",
                        "Tulostus",
                        JOptionPane.INFORMATION_MESSAGE);
            }
        });
        this.resultPanel.add(printButton);
        
        JButton compareButton = new JButton("Vertaa.");
        compareButton.addActionListener(a -> {
            // TODO
        });
        this.resultPanel.add(compareButton);
        
        JButton backButton = new JButton("Aloita alusta.");
        backButton.addActionListener(a -> showPanel(INDEX));
        this.resultPanel.add(backButton);
    }
    
    private void showPanel(String card) {
        CardLayout cl = (CardLayout) (this.panels.getLayout());
        cl.show(this.panels, card);
    }
}