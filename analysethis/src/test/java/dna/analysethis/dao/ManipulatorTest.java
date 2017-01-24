package dna.analysethis.dao;

import dna.analysethis.domain.Base;
import java.util.List;
import org.junit.Test;
import static org.junit.Assert.*;

public class ManipulatorTest {
    @Test
    public void testStringToList() {
        List<Base> bases = Manipulator.stringToList(" AC6   h gX! t");
        assertFalse(bases.isEmpty());
        assertEquals(8, bases.size());
        assertArrayEquals(new Base[]{Base.A,Base.C,Base.X,Base.X,Base.G,Base.X,Base.X,Base.T},
                bases.toArray());
    }
    
    @Test
    public void testStringToSequence() {
        assertFalse(Manipulator.stringToSequence(" AC6   h gX! t") == null);
    }
    
    @Test
    public void testSequenceToString() {
        assertEquals("AC__G__T", Manipulator.sequenceToString(Manipulator.stringToSequence(" AC6   h gX! t")));
    }
}