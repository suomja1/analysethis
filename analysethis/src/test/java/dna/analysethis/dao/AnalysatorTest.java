package dna.analysethis.dao;

import dna.analysethis.domain.Base;
import dna.analysethis.domain.Sequence;
import dna.analysethis.utilities.StringToList;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;

public class AnalysatorTest {
    Sequence s;
    Analysator a;
    
    @Before
    public void setUp() {
        s = new Sequence(StringToList.convert("AAAATTTTCCXY"));
        a = new Analysator(s);
    }
    
    @Test
    public void testFrequency() {
        assertEquals(2, a.frequency(Base.X));
        assertEquals(0, a.frequency(Base.G));
        assertEquals(4, a.frequency(Base.A));
    }
    
    @Test
    public void testNumberOfBases() {
        assertEquals(12, a.numberOfBases());
    }
    
    @Test
    public void testNumberOfUnknownBases() {
        assertEquals(2, a.numberOfUnknownBases());
        
        s.setSequence(StringToList.convert("AAAATTTTCC"));
        assertEquals(0, a.numberOfUnknownBases());
    }
    
    @Test
    public void testRelativeFrequency() {
        assertEquals(.16666666, a.relativeFrequency(Base.X), .001);
        assertEquals(.0, a.relativeFrequency(Base.G), .001);
        assertEquals(.33333333333, a.relativeFrequency(Base.A), .001);
    }
    
    @Test
    public void testGCcontent() {
        assertEquals(.16666666, a.GCcontent(), .001);
        
        s.setSequence(StringToList.convert("AAAATTTTCCGGGGGG"));
        assertEquals(.5, a.GCcontent(), .001);
        
        s.setSequence(StringToList.convert("AAAATTTT"));
        assertEquals(0, a.GCcontent(), .001);
    }
}