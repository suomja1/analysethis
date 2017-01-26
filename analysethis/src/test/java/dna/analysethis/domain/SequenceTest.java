package dna.analysethis.domain;

import dna.analysethis.dao.Manipulator;
import java.util.LinkedList;
import java.util.List;
import org.junit.Test;
import static org.junit.Assert.*;

public class SequenceTest {
    @Test
    public void testConstructor() {
        Sequence s = Manipulator.stringToSequence("AACCTTGG");
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
    
    @Test
    public void testToString() {
        Sequence s = Manipulator.stringToSequence("   A AC8sP OCTT GG");
        assertEquals(Manipulator.sequenceToString(s), s.toString());
    }

    @Test
    public void testEquals() {
        List<Base> bases = Manipulator.stringToList("AACTXTG");
        Sequence s1 = new Sequence(bases);
        Sequence s2 = new Sequence(bases);
        assertTrue(s1.equals(s2));
    }
}