package dna.analysethis.utilities;

import java.io.FileNotFoundException;
import org.junit.Test;
import static org.junit.Assert.*;

public class FileHandlerTest {    
    @Test
    public void testRead1() throws FileNotFoundException {
        assertEquals("ACTCTGCTGAGCCTAGGCTAG", FileHandler.read("testi.txt"));
    }
    
    @Test(expected = FileNotFoundException.class)
    public void testRead2() throws FileNotFoundException {
        FileHandler.read("tatatiedostoaeioleolemassa.txt");
    }
}