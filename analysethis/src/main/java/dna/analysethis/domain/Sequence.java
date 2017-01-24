package dna.analysethis.domain;

import java.util.List;

public class Sequence {
    private List<Base> sequence;

    public Sequence(List<Base> sequence) {
        if (sequence == null || sequence.isEmpty()) {
            throw new IllegalArgumentException("Sekvenssi ei voi olla tyhjä!");
        }
        this.sequence = sequence;
    }

    public List<Base> getSequence() {
        return this.sequence;
    }

    public void setSequence(List<Base> sequence) {
        this.sequence = sequence;
    }
}