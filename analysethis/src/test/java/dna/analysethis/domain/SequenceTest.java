package dna.analysethis.domain;

import dna.analysethis.utilities.StringToList;
import java.util.LinkedList;
import java.util.List;
import org.junit.Test;
import static org.junit.Assert.*;

public class SequenceTest {
    @Test
    public void testConstructor() {
        Sequence s = new Sequence(StringToList.convert("AACCTTGG"));
        assertFalse(s.getSequence().isEmpty());
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testEmptySequence() {
        List<Base> bases = new LinkedList<>();
        Sequence s = new Sequence(bases);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testNullSequence() {
        List<Base> bases = null;
        Sequence s = new Sequence(bases);
    }
}