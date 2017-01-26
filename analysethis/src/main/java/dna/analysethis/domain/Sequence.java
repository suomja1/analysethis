package dna.analysethis.domain;

import dna.analysethis.dao.Manipulator;
import java.util.List;
import java.util.Objects;

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

    @Override
    public String toString() {
        return Manipulator.sequenceToString(this);
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Sequence other = (Sequence) obj;
        if (!this.sequence.equals(other.sequence)) {
            return false;
        }
        return true;
    }
}