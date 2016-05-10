package culecalc;

public class CElement{
    private String name;
    private String symbol;
    private int protons;
    private int electrons;
    private int valence;
    private int neutrons;
    private double mass;
    
    public CElement(){ this("Unobtainium","Uub",0,0,0,0.0,0); }
    public CElement(String name, String symbol, int protons, int electrons, int neutrons, double mass, int valence){
        this.name = name;
        this.symbol = symbol;
        this.protons = protons;
        this.electrons = electrons;
        this.neutrons = neutrons;
        this.mass = mass;
        this.valence = valence;
    }
    public String getName(){ return this.name; }
    public String getSymbol(){ return this.symbol; }
    public int getProtons(){ return this.protons; }
    public int getNumber(){ return this.protons; }
    public int getElectrons(){ return this.electrons; }
    public int getNeutrons(){ return this.neutrons; }
    public double getMass(){ return this.mass; }
    public int getValence(){ return this.valence; }
    
    public boolean equals(CElement e){
        return e.getSymbol().equals(this.symbol);
    }
    
    @Override
    public String toString(){
        return "Name: " + this.name + "\n  " +
                "Symbol: " + this.symbol + "\n  " +
                "Protons: " + this.protons + "\n  " +
                "Electrons: " + this.electrons + "\n  " +
                "Neutrons: " + this.neutrons + "\n  " +
                "Mass: " + this.mass + " amu\n  " +
                "Valence: " + this.valence;
    }
}
