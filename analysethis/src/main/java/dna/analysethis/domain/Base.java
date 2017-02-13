package dna.analysethis.domain;

/**
 * Class for the representation of nucleobases in DNA.
 */
public enum Base {
    /**
     * Cytosine.
     */
    C (111.10, "Sytosiini"),

    /**
     * Guanine.
     */
    G (151.13, "Guaniini"),

    /**
     * Adenine.
     */
    A (135.13, "Adeniini"),

    /**
     * Thymine.
     */
    T (126.12, "Tymiini"),

    /**
     * Unknown base.
     */
    X (-1.0, "Tuntematon emäs");
    
    private final double mass;
    private final String name;

    private Base(double mass, String name) {
        this.mass = mass;
        this.name = name;
    }

    public double getMass() {
        return this.mass;
    }

    public String getName() {
        return name;
    }
}