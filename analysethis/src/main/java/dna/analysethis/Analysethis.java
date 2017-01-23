package dna.analysethis;

import dna.analysethis.domain.Sequence;

public class Analysethis {
    public static void main(String[] args) {
        Sequence s = new Sequence("AACCTTGG");
        Analysator a = new Analysator(s);
        
        System.out.println(a.numberOfUnknownBases());
    }
}