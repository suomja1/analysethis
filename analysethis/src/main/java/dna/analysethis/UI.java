package dna.analysethis;

import dna.analysethis.service.SequenceAnalysator;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.Timer;
import javax.swing.WindowConstants;
import static javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE;

public class UI implements Runnable {

    private JFrame frame;
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
        GridLayout layout = new GridLayout(3, 1);
        container.setLayout(layout);

        JLabel text = new JLabel("Syötä DNA-sekvenssi.", SwingConstants.CENTER);
        container.add(text);

        JTextArea input = new JTextArea();
        input.setLineWrap(true);
        container.add(input);

        JButton button = new JButton("Analysoi!");

        button.addActionListener(a -> {
            try {
                this.analysator = new SequenceAnalysator(input.getText());

                JDialog dialog = new JDialog(this.frame, "OK!", true);
                dialog.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
                JLabel message = new JLabel("Analysoidaan...", SwingConstants.CENTER);
                dialog.add(message);
                dialog.setSize(250, 50);
                
                Timer timer = new Timer(3000, (ActionEvent e) -> {
                    dialog.setVisible(false);
                    dialog.dispose();
                });
                timer.start();

                dialog.setVisible(true);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this.frame,
                        "Sekvenssin luku epäonnistui.\nYritä uudelleen.",
                        "Virhe!",
                        JOptionPane.ERROR_MESSAGE);
            }
        });

        container.add(button);
    }
}
