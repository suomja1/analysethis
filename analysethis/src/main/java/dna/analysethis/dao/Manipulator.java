package dna.analysethis.dao;

import dna.analysethis.domain.Base;
import dna.analysethis.domain.Sequence;
import java.util.LinkedList;
import java.util.List;

public class Manipulator {
    private Sequence sequence;

    public Manipulator(Sequence sequence) {
        this.sequence = sequence;
    }

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
        return new Sequence(Manipulator.stringToList(string));
    }
}