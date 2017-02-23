package dna.analysethis.domain;

import dna.analysethis.utilities.Manipulator;
import org.junit.Test;
import static org.junit.Assert.*;

public class CodonTest {
    @Test
    public void testIsStartCodon() {
        assertTrue(new Codon(Manipulator.stringToList("ATG")).isStartCodon());
        assertFalse(new Codon(Manipulator.stringToList("ACG")).isStartCodon());
    }

    @Test
    public void testIsStopCodon() {
        assertTrue(new Codon(Manipulator.stringToList("TAA")).isStopCodon());
        assertTrue(new Codon(Manipulator.stringToList("TAG")).isStopCodon());
        assertTrue(new Codon(Manipulator.stringToList("TGA")).isStopCodon());
        assertFalse(new Codon(Manipulator.stringToList("ATG")).isStopCodon());
    }    
}