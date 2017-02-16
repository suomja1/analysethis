package dna.analysethis.utilities;

import dna.analysethis.domain.Base;
import dna.analysethis.domain.Sequence;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

/**
 * Basic utility class for manipulating DNA sequences.
 */
public final class Manipulator {
    /**
     * Converts given sequence to string format.
     * @param sequence  Sequence to be converted
     * @return  Result of the conversion
     */
    public static String sequenceToString(Sequence sequence) {
        String string = "";
        
        for (Base b : sequence.getSequence()) {
            if (!b.equals(Base.X)) {
                string += b;
            } else {
                string += "_";
            }
        }
        
        return string;
    }
    
    /**
     * Converts given string to a list of bases.
     * @param string    String to be converted
     * @return  Result of the conversion
     */
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
    
    /**
     * Converts given string to a sequence.
     * @param string    String to be converted
     * @return  Result of the conversion
     */
    public static Sequence stringToSequence(String string) {
        return new Sequence(stringToList(string));
    }
    
    /**
     * Reverses the order of the bases of given sequence.
     * @param sequence  Sequence to be reversed
     * @return  Reverse of given sequence
     */
    public static Sequence reverse(Sequence sequence) {
        List<Base> bases = new LinkedList<>(sequence.getSequence());
        Collections.reverse(bases);
        return new Sequence(bases);
    }
    
    /**
     * Creates the complement of given sequence using the Base-pairing rule.
     * @param sequence  Sequence to be complemented
     * @return  Complement of given sequence
     */
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
    
    /**
     * Reverses the order of the bases of given sequence and creates its complement using the Base-pairing rule.
     * @param sequence  Sequence to be reversed and complemented
     * @return  Reverse-complement of given sequence
     */
    public static Sequence reverseComplement(Sequence sequence) {
        return complement(reverse(sequence));
    }
    
    /**
     * Creates a random sequence of given length.
     * @param length    The length of the sequence
     * @return  Random sequence of required length
     */
    public static Sequence random(int length) {
        if (length < 1) {
            throw new IllegalArgumentException("Emäksiä on oltava vähintään yksi!");
        }
        
        // We don't want unknown bases in our random sequences
        List<Base> bases = new LinkedList<>();
        Random random = new Random();
        
        for (int i = 0; i < length; i++) {
            int x = random.nextInt(4);
            
            if (x == 0) {
                bases.add(Base.C);
            } else if (x == 1) {
                bases.add(Base.G);
            } else if (x == 2) {
                bases.add(Base.A);
            } else if (x == 3) {
                bases.add(Base.T);
            }
        }
        
        return new Sequence(bases);
    }
    
    /**
     * Converts given sequence to a list of codons.
     * @param sequence  Sequence to be converted
     * @return  List of codons (sequences)
     */
    public static LinkedList<Sequence> sequenceToCodons(Sequence sequence) {
        // Returns only triples of bases, that is some bases from the original sequence might get dropped
        LinkedList<Sequence> codons = new LinkedList<>();
        
        int j = 1;
        for (int i = 0; i <= sequence.getSequence().size() - 3; i += 3) {
            codons.add(new Sequence(sequence.getSequence().subList(i, 3 * j)));
            j++;
        }
        
        return codons;
    }
}