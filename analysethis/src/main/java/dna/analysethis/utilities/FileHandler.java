package dna.analysethis.utilities;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Basic utility class for reading files.
 */
public final class FileHandler {

    /**
     * Reads the given file to a string.
     * @param filename  File to be read
     * @return string-representation of given file
     * @throws FileNotFoundException
     */
    public static String read(String filename) throws FileNotFoundException {
        File file = new File(filename);
        String string = "";
        
        try (Scanner reader = new Scanner(file)) {
            while (reader.hasNextLine()) {
                string += reader.nextLine();
            }
        }
        
        return string;
    }
}