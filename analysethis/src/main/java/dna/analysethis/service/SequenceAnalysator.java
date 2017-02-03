package dna.analysethis.service;

import dna.analysethis.domain.Base;
import dna.analysethis.domain.Sequence;
import dna.analysethis.utilities.Manipulator;
import java.io.FileNotFoundException;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class SequenceAnalysator {
    private Sequence sequence;

    public SequenceAnalysator(Sequence sequence) {
        this.sequence = sequence;
    }

    public SequenceAnalysator(String string) throws FileNotFoundException {
        if (string.endsWith(".txt")) {
            throw new UnsupportedOperationException("TODO");
        } else {
            this.sequence = Manipulator.stringToSequence(string);
        }
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
    
    public double mass(Base b) {
        if (b == Base.X) {
            throw new IllegalArgumentException("Tuntemattoman emäksen moolimassaa ei tunneta!");
        }
        return this.frequency(b) * b.getMass();
    }
    
    public double mass() {
        return this.mass(Base.C) + this.mass(Base.G) + this.mass(Base.T) + this.mass(Base.A);
    }
    
    public double relativeMass(Base b) {
        return this.mass(b) / this.mass();
    }
    
    public double relativeGcMass() {
        return this.relativeMass(Base.G) + this.relativeMass(Base.C);
    }
    
    public int numberOfCodons() {
        return Manipulator.sequenceToCodons(this.sequence).size();
    }
    
    public boolean checkStartAndStopCodons(Sequence seq) {
        Sequence startCodon = Manipulator.stringToSequence("ATG");
        
        List<Sequence> stopCodons = new LinkedList<>();
        stopCodons.add(Manipulator.stringToSequence("TAA"));
        stopCodons.add(Manipulator.stringToSequence("TAG"));
        stopCodons.add(Manipulator.stringToSequence("TGA"));
        
        LinkedList<Sequence> codons = Manipulator.sequenceToCodons(seq);
        
        return codons.getFirst().equals(startCodon) && stopCodons.contains(codons.getLast());
        // We omit checking for possible stop codons inside the sequence – for now that is
    }
    
    public boolean checkStartAndStopCodons() {
        // Multiple reading frames (4) make this especially difficult
        return this.checkStartAndStopCodons(this.sequence)
                || this.checkStartAndStopCodons(Manipulator.reverse(this.sequence))
                || this.checkStartAndStopCodons(Manipulator.complement(this.sequence))
                || this.checkStartAndStopCodons(Manipulator.reverseComplement(this.sequence));
    }
    
    public boolean checkIfGene() {
        // Values used here are pulled out of a hat and have little scientific justification
        return this.numberOfCodons() >= 5 && this.relativeGcMass() >= .3 && this.checkStartAndStopCodons();
    }
}