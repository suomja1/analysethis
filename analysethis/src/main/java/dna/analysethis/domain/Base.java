package dna.analysethis.domain;

public enum Base {
    C (111.10), // cytosine
    G (151.13), // guanine
    A (135.13), // adenine
    T (126.12), // thymine
    X (-1.0); // unknown
    
    private final double mass;

    private Base(double mass) {
        this.mass = mass;
    }

    public double getMass() {
        return this.mass;
    }
}