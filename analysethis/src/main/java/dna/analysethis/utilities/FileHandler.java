package dna.analysethis.utilities;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public final class FileHandler {
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