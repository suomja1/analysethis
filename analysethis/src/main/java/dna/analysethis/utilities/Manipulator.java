package dna.analysethis.utilities;

import dna.analysethis.domain.Base;
import dna.analysethis.domain.Sequence;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class Manipulator {
    public static String sequenceToString(Sequence sequence) {
        String string = "";
        
        for (Base b : sequence.getSequence()) {
            switch (b) {
                case C:
                    string += "C";
                    break;
                case G:
                    string += "G";
                    break;
                case A:
                    string += "A";
                    break;
                case T: 
                    string += "T";
                    break;
                default:
                    string += "_";
                    break;
            }
        }
        
        return string;
    }
    
    public static List<Base> stringToList(String string) {
        List<Base> bases = new LinkedList<>();

        for (char c : string.replaceAll(" ", "").toUpperCase().toCharArray()) {
            switch (c) {
                case 'C':
                    bases.add(Base.C);
                    break;
                case 'G':
                    bases.add(Base.G);
                    break;
                case 'A':
                    bases.add(Base.A);
                    break;
                case 'T':
                    bases.add(Base.T);
                    break;
                default:
                    bases.add(Base.X);
                    break;
            }
        }

        return bases;
    }
    
    public static Sequence stringToSequence(String string) {
        return new Sequence(stringToList(string));
    }
    
    public static Sequence reverse(Sequence sequence) {
        List<Base> bases = new LinkedList<>(sequence.getSequence());
        Collections.reverse(bases);
        return new Sequence(bases);
    }
    
    public static Sequence complement(Sequence sequence) {
        List<Base> bases = new LinkedList<>();
        
        for (Base b : sequence.getSequence()) {
            if (b == Base.T) {
                bases.add(Base.A);
            } else if (b == Base.A) {
                bases.add(Base.T);
            } else if (b == Base.C) {
                bases.add(Base.G);
            } else if (b == Base.G) {
                bases.add(Base.C);
            } else if (b == Base.X) {
                bases.add(Base.X);
            }
        }
        
        return new Sequence(bases);
    }
    
    public static Sequence reverseComplement(Sequence sequence) {
        return complement(reverse(sequence));
    }
}