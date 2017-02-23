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
    private JPanel comparisonPanel;
    final static String COMPARISON = "COMPARISON";
    private SequenceAnalysator analysator;
    private SequenceAnalysator compare;

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
                        "Sekvenssin generointi epäonnistui." + System.getProperty("line.separator") + "Yritä uudelleen.",
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
                input.setText("");
            } catch (IOException | IllegalArgumentException e) {
                JOptionPane.showMessageDialog(this.frame,
                        "Sekvenssinluku epäonnistui." + System.getProperty("line.separator") + "Yritä uudelleen.",
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
                    + System.getProperty("line.separator");
        }
        
        resultsText += "Emästen lukumäärä: " + this.analysator.numberOfBases() + System.getProperty("line.separator")
                + "GC%: " + Math.round(this.analysator.gcContent() * 10000.0) / 100.0 + " %" + System.getProperty("line.separator")
                + "Onko geeni? " + this.analysator.checkIfGene();
        
        JTextArea results = new JTextArea(resultsText);
        results.setEditable(false);
        this.resultPanel.add(results);
        
        JButton printButton = new JButton("Tulosta.");
        String resultsText2 = this.analysator.getSequence() + System.getProperty("line.separator") + System.getProperty("line.separator") + resultsText;
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
            String string = JOptionPane.showInputDialog(this.frame,
                    "Syötä verrattava sekvessi:",
                    "Vertaa",
                    JOptionPane.PLAIN_MESSAGE);
            
            try {
                this.compare = new SequenceAnalysator(string);
                
                createComparisonPanel();
                this.panels.add(this.comparisonPanel, COMPARISON);

                showPanel(COMPARISON);
            } catch (IOException | IllegalArgumentException e) {
                JOptionPane.showMessageDialog(this.frame,
                        "Sekvenssinluku epäonnistui." + System.getProperty("line.separator") + "Yritä uudelleen.",
                        "Virhe!",
                        JOptionPane.ERROR_MESSAGE);
            }
        });
        this.resultPanel.add(compareButton);
        
        JButton backButton = new JButton("Aloita alusta.");
        backButton.addActionListener(a -> showPanel(INDEX));
        this.resultPanel.add(backButton);
    }
    
    private void createComparisonPanel() {
        this.comparisonPanel = new JPanel(new GridLayout(4, 1));
        
        String result = Manipulator.compoundSequence(analysator.getSequence(), compare.getSequence());
        String resultText = "Vertailun tulos:" + System.getProperty("line.separator") + result;
        JTextArea results = new JTextArea(8, 1);
        results.setText(resultText);
        results.setLineWrap(true);
        results.setEditable(false);
        JScrollPane scroll = new JScrollPane(results);
        this.comparisonPanel.add(scroll);
        
        JTextArea text = new JTextArea("Sekvenssien Levenšteinin etäisyys on "
                + this.analysator.levenshteinDistance(this.compare.getSequence()));
        text.setEditable(false);
        text.setLineWrap(true);
        this.comparisonPanel.add(text);
        
        JButton backButton = new JButton("Takaisin.");
        backButton.addActionListener(a -> showPanel(RESULT));
        this.comparisonPanel.add(backButton);
        
        JButton back2Button = new JButton("Aloita alusta.");
        back2Button.addActionListener(a -> showPanel(INDEX));
        this.comparisonPanel.add(back2Button);
    }    
    
    private void showPanel(String card) {
        CardLayout cl = (CardLayout) (this.panels.getLayout());
        cl.show(this.panels, card);
    }
}