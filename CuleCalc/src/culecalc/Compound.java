package culecalc;

public class Compound extends Formula {
    private static Element elements[];
    public Compound(){}
    public Compound(Element elements[]){
        this.elements = elements;
    }
    public Element[] getElements() { return this.elements; }
    public double getMass(){
        double mass = 0;
        for(Element e : elements)
            mass += e.getMass();
        return mass;
    }
}
