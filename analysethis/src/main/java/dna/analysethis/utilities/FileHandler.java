package dna.analysethis.utilities;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Basic utility class for reading files.
 */
public final class FileHandler {

    /**
     * Reads the given file to a string.
     * @param filename  File to be read
     * @return string-representation of given file
     * @throws java.io.IOException  Throws an exception if the file can't be found
     */
    public static String read(String filename) throws IOException {
        return new String(Files.readAllBytes(Paths.get(filename)));
    }
    
    /**
     * Writes the given string to a file.
     * @param sequence  String to write
     * @return  True if successful
     */
    public static boolean write(String sequence) {
        try {
            Files.write(Paths.get("./tuloste.txt"), sequence.getBytes());
            return true;
        } catch (IOException es) {
            return false;
        }
    }
}