package dna.analysethis.domain;

import org.junit.Test;
import static org.junit.Assert.*;

public class SequenceTest {
    @Test
    public void testConstructor() {
        Sequence s = new Sequence("AACCTTGG");
        assertFalse(s.getSequence().isEmpty());
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testEmptySequence() {
        Sequence s = new Sequence("");
    }
}