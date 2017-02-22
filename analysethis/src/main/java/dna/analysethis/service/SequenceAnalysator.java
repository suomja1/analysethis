package dna.analysethis.service;

import dna.analysethis.domain.Base;
import dna.analysethis.domain.Codon;
import dna.analysethis.domain.Sequence;
import dna.analysethis.utilities.FileHandler;
import dna.analysethis.utilities.Manipulator;
import java.io.IOException;
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
     * @throws java.io.IOException  File can't be found
     */
    public SequenceAnalysator(String string) throws IOException {
        if (string.endsWith(".txt")) {
            this.sequence = Manipulator.stringToSequence(FileHandler.read(string));
        } else {
            this.sequence = Manipulator.stringToSequence(string);
        }
    }

    public Sequence getSequence() {
        return sequence;
    }
    
    /**
     * Counts the number of occurrences of given base in the sequence. 
     * @param b Base to be analysed
     * @return  Number of occurrences
     */
    public int frequency(Base b) {
        return Collections.frequency(this.sequence.getSequence(), b);
    }
    
    /**
     * Counts the total number of bases in the sequence.
     * @return  Total number of bases
     */
    public int numberOfBases() {
        return this.sequence.getSequence().size();
    }
    
    /**
     * Counts the number of occurrences of unknown bases in the sequence.
     * @return  Number of occurrences
     */
    public int numberOfUnknownBases() {
        return this.frequency(Base.X);
    }
    
    /**
     * Counts the relative frequency of given base in the sequence.
     * @param b Base to be analysed
     * @return  Percent as decimal of the relative frequency
     */
    public double relativeFrequency(Base b) {
        return (double) this.frequency(b) / this.numberOfBases();
    }
    
    /**
     * Counts the GC percentage of the sequence.
     * @return  Percent as decimal
     */
    public double gcContent() {
        return this.relativeFrequency(Base.G) + this.relativeFrequency(Base.C);
    }
    
    /**
     * Counts the total mass of given base in the sequence.
     * @param b Base to be analysed
     * @return  Total mass of the base
     */
    public double mass(Base b) {
        if (b.equals(Base.X)) {
            throw new IllegalArgumentException("Tuntemattoman emäksen moolimassaa ei tunneta!");
        }
        return this.frequency(b) * b.getMass();
    }
    
    /**
     * Counts the total mass of given sequence (known bases).
     * @return  Mass
     */
    public double mass() {
        return this.mass(Base.C) + this.mass(Base.G) + this.mass(Base.T) + this.mass(Base.A);
    }
    
    /**
     * Counts the mass percentage of given base in the sequence.
     * @param b Base to be analysed
     * @return  Percent as decimal
     */
    public double relativeMass(Base b) {
        return this.mass(b) / this.mass();
    }
    
    /**
     * Counts the GC mass percentage of the sequence.
     * @return  Percent as decimal
     */
    public double relativeGcMass() {
        return this.relativeMass(Base.G) + this.relativeMass(Base.C);
    }
    
    /**
     * Counts the number of codons in the sequence.
     * @return  Number of codons
     */
    public int numberOfCodons() {
        return Manipulator.sequenceToCodons(this.sequence).size();
    }
    
    /**
     * Checks the start and stop codons of given sequence.
     * @param seq   Sequence to be analysed
     * @return  True if both start and stop codons occur in the sequence
     */
    public boolean checkStartAndStopCodons(Sequence seq) {
        LinkedList<Codon> codons = Manipulator.sequenceToCodons(seq);
        return codons.getFirst().isStartCodon() && codons.getLast().isStopCodon();
        // We omit checking for possible stop codons inside the sequence – for now that is
    }
    
    /**
     * Checks the start and stop codons of the sequence taking into account the multiple reading frames.
     * @return True if both start and stop codon occur in a reading frame
     */
    public boolean checkStartAndStopCodons() {
        return this.checkStartAndStopCodons(this.sequence)
                || this.checkStartAndStopCodons(Manipulator.reverse(this.sequence))
                || this.checkStartAndStopCodons(Manipulator.complement(this.sequence))
                || this.checkStartAndStopCodons(Manipulator.reverseComplement(this.sequence));
    }
    
    /**
     * Checks whether the sequence represents a theoretically functioning gene.
     * @return True if the sequence represents a gene
     */
    public boolean checkIfGene() {
        // Values used here are pulled out of a hat and have little scientific justification
        return this.numberOfCodons() >= 5 && this.relativeGcMass() >= .3 && this.checkStartAndStopCodons();
    }
    
    /**
     * Counts the Levenshtein distance between given sequence and the sequence. For this the method uses a well-known algorithm for two strings.
     * @param another   Sequence to be compared
     * @return  The minimum number of single-base edits required to change given sequence into the other
     */
    public int levenshteinDistance(Sequence another) {
        return levenshteinDistance(this.sequence.toString(), another.toString());
    }

    private int levenshteinDistance(String a, String b) {
        if (a.equals(b)) {
            return 0;
        }
        
        int[] v0 = new int[b.length() + 1];
        int[] v1 = new int[b.length() + 1];

        for (int i = 0; i < v0.length; i++) {
            v0[i] = i;
        }
        
        for (int i = 0; i < a.length(); i++) {
            v1[0] = i + 1;
            
            for (int j = 0; j < b.length(); j++) {
                int cost = 1;
                
                if (a.charAt(i) == b.charAt(j)) {
                    cost = 0;
                }
                
                v1[j + 1] = Math.min(v1[j] + 1, Math.min(v0[j + 1] + 1, v0[j] + cost));
            }
            
            System.arraycopy(v1, 0, v0, 0, v0.length);
        }
        
        return v1[b.length()];
    }
}