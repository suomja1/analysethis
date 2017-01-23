package dna.analysethis;

import dna.analysethis.domain.Sequence;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;

public class AnalysatorTest {
    Sequence s;
    Analysator a;
    
    @Before
    public void setUp() {
        s = new Sequence("AAAATTTTCCXX");
        a = new Analysator(s);
    }
    
    @Test
    public void testFrequency() {
        assertEquals(0, a.frequency('Y'));
        assertEquals(0, a.frequency('G'));
        assertEquals(4, a.frequency('A'));
    }
    
    @Test
    public void testNumberOfBases() {
        assertEquals(12, a.numberOfBases());
    }
    
    @Test
    public void testNumberOfUnknownBases() {
        assertEquals(2, a.numberOfUnknownBases());
        s = new Sequence("AAAATTTTCC");
        a = new Analysator(s);
        assertEquals(0, a.numberOfUnknownBases());
    }
    
    @Test
    public void testRelativeFrequency() {
        assertEquals(.0, a.relativeFrequency('Y'), .001);
        assertEquals(.0, a.relativeFrequency('G'), .001);
        assertEquals(.33333333333, a.relativeFrequency('A'), .001);
    }
    
    @Test
    public void testGCcontent() {
        assertEquals(.16666666, a.GCcontent(), .001);
        s = new Sequence("AAAATTTTCCGGGGGG");
        a = new Analysator(s);
        assertEquals(.5, a.GCcontent(), .001);
        s = new Sequence("AAAATTTT");
        a = new Analysator(s);
        assertEquals(0, a.GCcontent(), .001);
    }
}