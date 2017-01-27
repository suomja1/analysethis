package dna.analysethis;

import dna.analysethis.domain.Sequence;
import dna.analysethis.service.SequenceAnalysator;
import dna.analysethis.utilities.Manipulator;

public class Analysethis {
    public static void main(String[] args) {
        // TEST CODE
        Sequence s = Manipulator.stringToSequence("ACTGAATXXXATG");
        
        for (Sequence se : Manipulator.sequenceToCodons(s)) {
            System.out.println(se);
        }
        
        SequenceAnalysator sa = new SequenceAnalysator(s);
        System.out.println(sa.numberOfCodons());
    }
}