package dna.analysethis;

import dna.analysethis.service.SequenceAnalysator;
import dna.analysethis.utilities.Manipulator;

public class Analysethis {
    public static void main(String[] args) {
        // TEST CODE
        SequenceAnalysator sa = new SequenceAnalysator(Manipulator.stringToSequence("TACTTTAAAAGGACT"));
        System.out.println(sa.checkIfGene());
    }
}