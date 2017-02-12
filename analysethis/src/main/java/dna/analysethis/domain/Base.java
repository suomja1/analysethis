package dna.analysethis.domain;

/**
 * Class for the representation of nucleobases in DNA.
 */
public enum Base {
    /**
     * Cytosine.
     */
    C (111.10),

    /**
     * Guanine.
     */
    G (151.13),

    /**
     * Adenine.
     */
    A (135.13),

    /**
     * Thymine.
     */
    T (126.12),

    /**
     * Unknown base.
     */
    X (-1.0);
    
    private final double mass;

    private Base(double mass) {
        this.mass = mass;
    }

    public double getMass() {
        return this.mass;
    }
}