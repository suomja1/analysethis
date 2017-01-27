package dna.analysethis.utilities;

import dna.analysethis.domain.Base;
import dna.analysethis.domain.Sequence;
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
    
    @Test
    public void testReverse() {
        Sequence s1 = Manipulator.stringToSequence("ACXACCXGCGAX");
        Sequence temp = new Sequence(s1.getSequence());
        Sequence s2 = Manipulator.stringToSequence("XAGCGXCCAXCA");
        assertEquals(s1, temp);
        assertEquals(Manipulator.reverse(s1), s2);
        assertEquals(s1, temp);
   }
    
    @Test
    public void testComplement() {
        Sequence s1 = Manipulator.stringToSequence("ACXACCXGCGAXT");
        Sequence temp = new Sequence(s1.getSequence());
        Sequence s2 = Manipulator.stringToSequence("TGXTGGXCGCTXA");        
        assertEquals(s1, temp);
        assertEquals(Manipulator.complement(s1), s2);
        assertEquals(s1, temp);
    }
    
    @Test
    public void testReverseComplement() {
        Sequence s1 = Manipulator.stringToSequence("ACXACCXGCGAX");
        Sequence temp = new Sequence(s1.getSequence());
        Sequence s2 = Manipulator.stringToSequence("XTCGCXGGTXGT");
        assertEquals(s1, temp);
        assertEquals(Manipulator.reverseComplement(s1), s2);
        assertEquals(s1, temp);
    }
}