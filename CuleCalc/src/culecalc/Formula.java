package culecalc;

import java.util.ArrayList;

public class Formula {
    private int coefficient;
    private String formula = "";
    private ArrayList<FormulaPart> elements = new ArrayList<FormulaPart>();
    public Formula(){}
    public Formula(String formula){
        this.formula = formula;
        this.coefficient = getCoefficient();
        this.elements = getElements();
    }
    
    public int getCoefficient(){
        String[] split = formula.split("(?=\\p{Upper})");
        return Integer.parseInt(!(split[0].charAt(0) > '0' && split[0].charAt(0) <= '9') ? "1" : split[0]);
    }
    public ArrayList<FormulaPart> getElements(){
        ArrayList<FormulaPart> elements = new ArrayList<FormulaPart>();
        
        String[] split = formula.split("(?=\\p{Upper})");
        for(int i = 0; i < split.length; i++){
            if(!(split[i].charAt(0) > '0' && split[i].charAt(0) <= '9')){
                String[] e = split[i].split("(?<=[\\w&&\\D])(?=\\d)");
                CElement element = new CElement();
                for(CElement t : CuleCalc.elements){
                    if(t.getSymbol().equals(e[0])){
                        element = t;
                        break;
                    }
                }
                elements.add(new FormulaPart(element, e.length > 1 ? Integer.parseInt(e[1]) : 1));
            }
        }
        
        return elements;
    }
    
    public double getMass(){
        double mass = 0.0;
        for(FormulaPart f : elements){
            mass += f.getElement().getMass() * f.getCount();
        }
        return mass * coefficient;
    }
    
    public FormulaPart getPart(int i){ if(i > elements.size()){ return new FormulaPart(); }else{ return elements.get(i); } }
}

class FormulaPart {
    private CElement element;
    private int count;
    public FormulaPart(){}
    public FormulaPart(CElement element, int count){
        this.element = element;
        this.count = count;
    }
    public CElement getElement(){ return this.element; }
    public int getCount(){ return this.count; }
}

