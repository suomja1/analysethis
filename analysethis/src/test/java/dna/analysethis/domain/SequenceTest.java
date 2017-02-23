package dna.analysethis.domain;

import dna.analysethis.utilities.Manipulator;
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
        assertEquals("AACXXXXCTTGG", s.toString());
    }

    @Test
    public void testEquals() {
        String s = "AACTXTG";
        List<Base> bases = Manipulator.stringToList(s);
        Sequence s1 = new Sequence(bases);
        Sequence s2 = new Sequence(bases);
        assertEquals(s1, s1);
        assertNotEquals(s1, null);
        assertNotEquals(s1, s);
        assertEquals(s1, s2);
        s2.setSequence(Manipulator.stringToList("ATCTXTG"));
        assertNotEquals(s1, s2);
    }
}