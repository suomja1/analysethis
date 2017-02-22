package dna.analysethis.domain;

import dna.analysethis.utilities.Manipulator;
import java.util.LinkedList;
import java.util.List;

public class Codon extends Sequence {
    public Codon(List<Base> sequence) {
        super(sequence);
    }
    
    public boolean isStartCodon() {
        return this.equals(new Codon(Manipulator.stringToList("ATG")));
    }
    
    public boolean isStopCodon() {
        List<Sequence> stopCodons = new LinkedList<>();
        stopCodons.add(new Codon(Manipulator.stringToList("TAA")));
        stopCodons.add(new Codon(Manipulator.stringToList("TAG")));
        stopCodons.add(new Codon(Manipulator.stringToList("TGA")));
        
        return stopCodons.contains(this);
    }
}