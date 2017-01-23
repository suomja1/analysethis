package dna.analysethis;

import dna.analysethis.domain.Sequence;
import java.util.Collections;

public class Analysator {
    private Sequence sequence;

    public Analysator(Sequence sequence) {
        this.sequence = sequence;
    }
    
    public int frequency(char c) {
        return Collections.frequency(this.sequence.getSequence(), c);
    }
    
    public int numberOfBases() {
        return this.sequence.getSequence().size();
    }
    
    public int numberOfUnknownBases() {
        // Hidas ratkaisu
        return (int) this.sequence.getSequence().stream()
                .filter(c -> (c != 'A' && c != 'C' && c != 'G' && c != 'T'))
                .count();
    }
    
    public double relativeFrequency(char c) {
        return (double) this.frequency(c) / this.numberOfBases();
    }
    
    public double GCcontent() {
        return this.relativeFrequency('G') + this.relativeFrequency('C');
    }
}