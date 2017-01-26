package dna.analysethis;

import dna.analysethis.dao.Manipulator;
import dna.analysethis.domain.Sequence;

public class Analysethis {
    public static void main(String[] args) {
        Sequence s = Manipulator.stringToSequence("AAAAGGGGCCCCTTTT");
        
        System.out.println(s);
        System.out.println(Manipulator.complement(s));
        System.out.println(s);

    }
}