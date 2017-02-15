package dna.analysethis.utilities;

import java.io.IOException;
import org.junit.Test;
import static org.junit.Assert.*;

public class FileHandlerTest {    
    @Test
    public void testRead1() throws IOException {
        assertEquals("ACTCTGCTGAGCCTAGGCTAG", FileHandler.read("testi.txt"));
    }
    
    @Test(expected = IOException.class)
    public void testRead2() throws IOException {
        FileHandler.read("tatatiedostoaeioleolemassa.txt");
    }
}