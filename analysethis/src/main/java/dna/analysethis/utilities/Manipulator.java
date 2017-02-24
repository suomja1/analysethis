package dna.analysethis.utilities;

import dna.analysethis.domain.Base;
import dna.analysethis.domain.Codon;
import dna.analysethis.domain.Sequence;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.stream.Collectors;

/**
 * Basic utility class for manipulating DNA sequences.
 */
public final class Manipulator {
    /**
     * Converts given string to a list of bases.
     * @param string    String to be converted
     * @return  Result of the conversion
     */
    public static List<Base> stringToList(String string) {
        return string.replaceAll(" ", "").toUpperCase().replaceAll("[^CGAT]", "X")
                .chars().mapToObj(i -> String.valueOf((char) i))
                .map(Base::valueOf).collect(Collectors.toList());
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
        Map<Base, Base> basePairing = new HashMap<>();
        basePairing.put(Base.C, Base.G);
        basePairing.put(Base.G, Base.C);
        basePairing.put(Base.A, Base.T);
        basePairing.put(Base.T, Base.A);
        basePairing.put(Base.X, Base.X);
        
        return new Sequence(sequence.getSequence().stream()
                .map(b -> basePairing.get(b)).collect(Collectors.toList()));
    }
    
    /**
     * Reverses the order of the bases of given sequence and creates its complement using the Base-pairing rule.
     * @param sequence  Sequence to be reversed and complemented
     * @return  Reverse-complement of given sequence
     * @see dna.analysethis.utilities.Manipulator#reverse(dna.analysethis.domain.Sequence) 
     * @see dna.analysethis.utilities.Manipulator#complement(dna.analysethis.domain.Sequence) 
     */
    public static Sequence reverseComplement(Sequence sequence) {
        return complement(reverse(sequence));
    }
    
    /**
     * Creates a random sequence of given length.
     * @param length    The length of the sequence
     * @return  Random sequence of required length
     * @throws IllegalArgumentException Required length is out of bounds
     */
    public static Sequence random(int length) {
        if (length < 1) {
            throw new IllegalArgumentException("Emäksiä on oltava vähintään yksi!");
        }
        
        // We don't want unknown bases in our random sequences
        List<Base> bases = new LinkedList<>();
        Base[] options = Base.values();
        Random random = new Random();
        
        for (int i = 0; i < length; i++) {
            bases.add(options[random.nextInt(4)]);
        }
        
        return new Sequence(bases);
    }
    
    /**
     * Converts given sequence to a list of codons.
     * @param sequence  Sequence to be converted
     * @return  List of codons (sequences)
     */
    public static LinkedList<Codon> sequenceToCodons(Sequence sequence) {
        // Returns only triples of bases, that is some bases from the original sequence might get dropped
        LinkedList<Codon> codons = new LinkedList<>();
        
        int j = 1;
        for (int i = 0; i <= sequence.getSequence().size() - 3; i += 3) {
            codons.add(new Codon(sequence.getSequence().subList(i, 3 * j)));
            j++;
        }
        
        return codons;
    }
    
    /**
     * Compares given sequences and retuns a String-representation. All mismatches in the sequences are marked with an asterix.
     * @param seq1  First sequence to be compared
     * @param seq2  Second sequence to be compared
     * @return  String-representation of the comparison
     */
    public static String compoundSequence(Sequence seq1, Sequence seq2) {
        String string = "";

        for (int i = 0; i < Math.min(seq1.getSequence().size(), seq2.getSequence().size()); i++) {
            Base b = seq1.getSequence().get(i);

            if (b.equals(seq2.getSequence().get(i))) {
                string += b;
            } else {
                string += "*";
            }
        }

        for (int i = 0; i < Math.abs(seq1.getSequence().size() - seq2.getSequence().size()); i++) {
            string += "*";
        }

        return string;
    }
}