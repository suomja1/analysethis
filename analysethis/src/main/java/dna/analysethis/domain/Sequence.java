package dna.analysethis.domain;
import java.util.ArrayList;
import java.util.List;

public class Sequence {
    private List<Character> sequence;

    public Sequence(String seqString) {
        if (seqString.isEmpty()) {
            throw new IllegalArgumentException("Sekvenssi ei voi olla tyhjä!");
        }

        List<Character> seqList = new ArrayList<>();
        
        for (char c : seqString.toCharArray()) {
            seqList.add(c);
        }
        
        this.sequence = seqList;
    }

    public List<Character> getSequence() {
        return this.sequence;
    }
}