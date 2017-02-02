package dna.analysethis.service;

import dna.analysethis.utilities.Manipulator;
import dna.analysethis.domain.Base;
import dna.analysethis.domain.Sequence;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;

public class SequenceAnalysatorTest {
    Sequence s;
    SequenceAnalysator a;
    
    @Before
    public void setUp() {
        s = Manipulator.stringToSequence("AAAATTTTCCXY");
        a = new SequenceAnalysator(s);
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
        
        s.setSequence(Manipulator.stringToList("AAAATTTTCC"));
        assertEquals(0, a.numberOfUnknownBases());
    }
    
    @Test
    public void testRelativeFrequency() {
        assertEquals(.16666666, a.relativeFrequency(Base.X), .001);
        assertEquals(.0, a.relativeFrequency(Base.G), .001);
        assertEquals(.33333333333, a.relativeFrequency(Base.A), .001);
    }
    
    @Test
    public void testGcContent() {
        assertEquals(.16666666, a.gcContent(), .001);
        
        s.setSequence(Manipulator.stringToList("AAAATTTTCCGGGGGG"));
        assertEquals(.5, a.gcContent(), .001);
        
        s.setSequence(Manipulator.stringToList("AAAATTTT"));
        assertEquals(0, a.gcContent(), .001);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testMass1() {
        a.mass(Base.X);
    }
    
    @Test
    public void testMass2() {
        assertEquals(4 * Base.A.getMass(), a.mass(Base.A), .001);
        assertEquals(4 * Base.T.getMass(), a.mass(Base.T), .001);
        assertEquals(2 * Base.C.getMass(), a.mass(Base.C), .001);
        assertEquals(0 * Base.G.getMass(), a.mass(Base.G), .001);
    }
    
    @Test
    public void testMass3() {
        s.setSequence(Manipulator.stringToList("AAAATTTTCCXYG"));
        assertEquals(4 * Base.A.getMass()
                + 4 * Base.T.getMass()
                + 2 * Base.C.getMass()
                + 1 * Base.G.getMass(), 
                a.mass(), .001);
    }
    
    @Test
    public void testRelativeMass() {
        assertEquals(a.mass(Base.C) / a.mass(), a.relativeMass(Base.C), .001);
    }
    
    @Test
    public void testRelativeGcMass() {
        assertEquals(a.relativeMass(Base.C) + a.relativeMass(Base.G), a.relativeGcMass(), .001);
    }
    
    @Test
    public void testNumberOfCodons() {
        assertEquals(4, a.numberOfCodons());
        
        s.setSequence(Manipulator.stringToList("AA"));
        assertEquals(0, a.numberOfCodons());
        
        s.setSequence(Manipulator.stringToList("AAAATTTTCCGGGGGG"));
        assertEquals(5, a.numberOfCodons());
    }
    
    @Test
    public void testCheckStartAndStopCodons1() {
        assertFalse(a.checkStartAndStopCodons(s));
        
        s.setSequence(Manipulator.stringToList("ATGAAATTTTCCXY"));
        assertFalse(a.checkStartAndStopCodons(s));
        
        s.setSequence(Manipulator.stringToList("AAAATTTTCCXYTAG"));
        assertFalse(a.checkStartAndStopCodons(s));
        
        s.setSequence(Manipulator.stringToList("ATGAAAATTXTTCCXYTAA"));
        assertFalse(a.checkStartAndStopCodons(s));
        
        s.setSequence(Manipulator.stringToList("ATGAAAATTTTCCXYTGA"));
        assertTrue(a.checkStartAndStopCodons(s));
        
        s.setSequence(Manipulator.stringToList("ATGAAAATTTTCCXYTAA"));
        assertTrue(a.checkStartAndStopCodons(s));
        
        s.setSequence(Manipulator.stringToList("ATGAAAATTTTCCXYTAG"));
        assertTrue(a.checkStartAndStopCodons(s));
        
        s.setSequence(Manipulator.stringToList("ATGTGAAA")); // The tail 'AA' is cut off
        assertTrue(a.checkStartAndStopCodons(s));
    }
    
    @Test
    public void testCheckStartAndStopCodons2() {
        assertFalse(a.checkStartAndStopCodons());
        
        s.setSequence(Manipulator.stringToList("ATGAAATTTTCCTGA"));
        assertTrue(a.checkStartAndStopCodons());
        
        s.setSequence(Manipulator.stringToList("AATAAATTTTCCGTA"));
        assertTrue(a.checkStartAndStopCodons());
        
        s.setSequence(Manipulator.stringToList("TACTTTAAAAGGACT"));
        assertTrue(a.checkStartAndStopCodons());
        
        s.setSequence(Manipulator.stringToList("TCAGGAAAATTTCAT"));
        assertTrue(a.checkStartAndStopCodons());
    }
    
    @Test
    public void testCheckIfGene() {
        assertFalse(a.checkIfGene());
        
        s.setSequence(Manipulator.stringToList("ATGGGGGGGTGA"));
        assertFalse(a.checkIfGene());
        
        s.setSequence(Manipulator.stringToList("ATGGGGGGGTGAAAA"));
        assertFalse(a.checkIfGene());
        
        s.setSequence(Manipulator.stringToList("ATGAAAAAAAAATGA"));
        assertFalse(a.checkIfGene());
        
        s.setSequence(Manipulator.stringToList("ATGGGGCCCGGGTGA"));
        assertTrue(a.checkIfGene());
        
        s.setSequence(Manipulator.stringToList("ATGAAAGGGCCCGGGTGA"));
        assertTrue(a.checkIfGene());
        
        // To get rid of the mutation, construct a sequence with relative GC-mass of exactly 0.3
    }
}