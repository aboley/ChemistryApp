package culecalc;

public class Element extends Formula{
    private String name;
    private String symbol;
    private int protons;
    private int electrons;
    private int valence;
    private int neutrons;
    private double mass;
    
    public Element(){}
    public Element(String name, String symbol, int protons, int electrons, int valence, int neutrons, double mass){
        this.name = name;
        this.symbol = symbol;
        this.protons = protons;
        this.electrons = electrons;
        this.valence = valence;
        this.neutrons = neutrons;
        this.mass = mass;
    }
}
