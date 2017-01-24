package dna.analysethis.dao;

import dna.analysethis.domain.Base;
import dna.analysethis.domain.Sequence;
import java.util.Collections;

public class Analysator {
    private Sequence sequence;

    public Analysator(Sequence sequence) {
        this.sequence = sequence;
    }
    
    public int frequency(Base b) {
        return Collections.frequency(this.sequence.getSequence(), b);
    }
    
    public int numberOfBases() {
        return this.sequence.getSequence().size();
    }
    
    public int numberOfUnknownBases() {
        return this.frequency(Base.X);
    }
    
    public double relativeFrequency(Base b) {
        return (double) this.frequency(b) / this.numberOfBases();
    }
    
    public double gcContent() {
        return this.relativeFrequency(Base.G) + this.relativeFrequency(Base.C);
    }
}