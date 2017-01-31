package dna.analysethis;

import dna.analysethis.domain.Sequence;
import dna.analysethis.service.SequenceAnalysator;
import dna.analysethis.utilities.Manipulator;

public class Analysethis {
    public static void main(String[] args) {
        // TEST CODE
        SequenceAnalysator sa = new SequenceAnalysator(Manipulator.stringToSequence("ATGGAATCTCTCGCGCGCGCGCGGGGGTGA"));
        System.out.println(sa.checkIfGene());
    }
}