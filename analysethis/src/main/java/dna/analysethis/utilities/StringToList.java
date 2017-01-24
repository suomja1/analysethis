package dna.analysethis.utilities;

import dna.analysethis.domain.Base;
import java.util.LinkedList;
import java.util.List;

public class StringToList {
    public static List<Base> convert(String string) {
        List<Base> bases = new LinkedList<>();
        
        for (char c : string.replaceAll(" ", "").toUpperCase().toCharArray()) {
            switch (c) {
                case 'C':
                    bases.add(Base.C);
                    break;
                case 'G':
                    bases.add(Base.G);
                    break;
                case 'A':
                    bases.add(Base.A);
                    break;
                case 'T':
                    bases.add(Base.T);
                    break;
                default:
                    bases.add(Base.X);
                    break;
            }
        }
        
        return bases;
    }
}