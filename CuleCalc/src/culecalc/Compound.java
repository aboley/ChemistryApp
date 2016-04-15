package culecalc;

public class Compound{
    private static CElement elements[];
    public Compound(){}
    public Compound(CElement elements[]){
        this.elements = elements;
    }
    public CElement[] getElements() { return this.elements; }
    public double getMass(){
        double mass = 0;
        for(CElement e : elements)
            mass += e.getMass();
        return mass;
    }
}
