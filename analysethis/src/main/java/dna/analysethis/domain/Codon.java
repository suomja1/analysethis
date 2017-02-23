package dna.analysethis.domain;

import dna.analysethis.utilities.Manipulator;
import java.util.LinkedList;
import java.util.List;

/**
 * Class for the representation of codons that is sequences of three bases.
 */
public class Codon extends Sequence {
    /**
     * Constructor.
     * @param sequence  List of bases to be interpreted as a codon
     */
    public Codon(List<Base> sequence) {
        super(sequence);
    }
    
    /**
     * Checks whether the codon is a start codon that is ATG.
     * @return  True if the codon is a start codon.
     */
    public boolean isStartCodon() {
        return this.equals(new Codon(Manipulator.stringToList("ATG")));
    }
    
    /**
     * Checks whether the codon is a stop codon.
     * @return  True if the codon is a stop codon.
     */
    public boolean isStopCodon() {
        List<Sequence> stopCodons = new LinkedList<>();
        stopCodons.add(new Codon(Manipulator.stringToList("TAA")));
        stopCodons.add(new Codon(Manipulator.stringToList("TAG")));
        stopCodons.add(new Codon(Manipulator.stringToList("TGA")));
        
        return stopCodons.contains(this);
    }
}