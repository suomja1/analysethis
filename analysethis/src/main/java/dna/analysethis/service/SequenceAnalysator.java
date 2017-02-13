package dna.analysethis.service;

import dna.analysethis.domain.Base;
import dna.analysethis.domain.Sequence;
import dna.analysethis.utilities.FileHandler;
import dna.analysethis.utilities.Manipulator;
import java.io.FileNotFoundException;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * SequenceAnalysator provides multiple methods for analysing DNA sequences statistically.
 */
public class SequenceAnalysator {
    private Sequence sequence;

    /**
     * Constructor.
     * @param sequence The sequence to be analysed
     */
    public SequenceAnalysator(Sequence sequence) {
        this.sequence = sequence;
    }

    /**
     * Alternative constructor, which creates the sequence to be analysed from a string.
     * @param string    String-representation of the sequence to be analysed
     * @throws FileNotFoundException    If the file can't be found, an exception is thrown.
     */
    public SequenceAnalysator(String string) throws FileNotFoundException {
        if (string.endsWith(".txt") || string.endsWith(".TXT")) {
            this.sequence = Manipulator.stringToSequence(FileHandler.read(string));
        } else {
            this.sequence = Manipulator.stringToSequence(string);
        }
    }
    
    /**
     * Counts the number of occurrences of given base in the sequence. 
     * @param b Base to be analysed
     * @return  number of occurrences
     */
    public int frequency(Base b) {
        return Collections.frequency(this.sequence.getSequence(), b);
    }
    
    /**
     * Counts the total number of bases in the sequence.
     * @return  total number of bases
     */
    public int numberOfBases() {
        return this.sequence.getSequence().size();
    }
    
    /**
     * Counts the number of occurrences of unknown bases in the sequence.
     * @return  number of occurrences
     */
    public int numberOfUnknownBases() {
        return this.frequency(Base.X);
    }
    
    /**
     * Counts the relative frequency of given base in the sequence.
     * @param b Base to be analysed
     * @return  percent as decimal of the relative frequency
     */
    public double relativeFrequency(Base b) {
        return (double) this.frequency(b) / this.numberOfBases();
    }
    
    /**
     * Counts the GC percentage of the sequence.
     * @return  percent as decimal
     */
    public double gcContent() {
        return this.relativeFrequency(Base.G) + this.relativeFrequency(Base.C);
    }
    
    /**
     * Counts the total mass of given base in the sequence.
     * @param b Base to be analysed
     * @return  total mass of the base
     */
    public double mass(Base b) {
        if (b == Base.X) {
            throw new IllegalArgumentException("Tuntemattoman emäksen moolimassaa ei tunneta!");
        }
        return this.frequency(b) * b.getMass();
    }
    
    /**
     * Counts the total mass of given sequence (known bases).
     * @return  mass
     */
    public double mass() {
        return this.mass(Base.C) + this.mass(Base.G) + this.mass(Base.T) + this.mass(Base.A);
    }
    
    /**
     * Counts the mass percentage of given base in the sequence.
     * @param b Base to be analysed
     * @return  percent as decimal
     */
    public double relativeMass(Base b) {
        return this.mass(b) / this.mass();
    }
    
    /**
     * Counts the GC mass percentage of the sequence.
     * @return  percent as decimal
     */
    public double relativeGcMass() {
        return this.relativeMass(Base.G) + this.relativeMass(Base.C);
    }
    
    /**
     * Counts the number of codons in the sequence.
     * @return  number of codons
     */
    public int numberOfCodons() {
        return Manipulator.sequenceToCodons(this.sequence).size();
    }
    
    /**
     * Checks the start and stop codons of given sequence.
     * @param seq   Sequence to be analysed
     * @return  true if both start and stop codons occur in the sequence
     */
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
    
    /**
     * Checks the start and stop codons of the sequence taking into account the multiple reading frames.
     * @return true if both start and stop codon occur in a reading frame
     */
    public boolean checkStartAndStopCodons() {
        return this.checkStartAndStopCodons(this.sequence)
                || this.checkStartAndStopCodons(Manipulator.reverse(this.sequence))
                || this.checkStartAndStopCodons(Manipulator.complement(this.sequence))
                || this.checkStartAndStopCodons(Manipulator.reverseComplement(this.sequence));
    }
    
    /**
     * Checks whether the sequence represents a theoretically functioning gene.
     * @return true if the sequence represents a gene
     */
    public boolean checkIfGene() {
        // Values used here are pulled out of a hat and have little scientific justification
        return this.numberOfCodons() >= 5 && this.relativeGcMass() >= .3 && this.checkStartAndStopCodons();
    }
}