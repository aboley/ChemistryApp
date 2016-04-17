package culecalc;

import java.util.ArrayList;

public class Formula {
    private int coefficient;
    private String formula;
    private ArrayList<FormulaPart> elements = new ArrayList<FormulaPart>();
    public Formula(){}
    public Formula(String formula){
        this.formula = formula;
        this.coefficient = getCoefficient();
        this.elements = getElements();
    }
    
    public int getCoefficient(){
        //Uses a Regex lookahead to match first uppercase character and then splits between it and the previous character.
        String[] split = formula.split("(?=\\p{Upper})");
        try{
            return formula.length() >= 1? (Integer.parseInt(!(split[0].charAt(0) > '0' && split[0].charAt(0) <= '9') ? "1" : split[0])) : 1;
        }catch(Exception ex){
            //TODO: Print error message in panel.
            System.out.println("Check your formula for errors!");
        }
        return 1;
    }
    
    //Returns an ArrayList of all the FormulaParts
    public ArrayList<FormulaPart> getElements(){
        if(formula.length() == 0){ return new ArrayList<FormulaPart>(); }
        
        ArrayList<FormulaPart> elements = new ArrayList<FormulaPart>();
        //TODO: Take ()'s into account
        String[] split = formula.split("(?=\\p{Upper})");
        for(int i = 0; i < split.length; i++){
            if(!(split[i].charAt(0) > '0' && split[i].charAt(0) <= '9')){
                //Uses a Regex lookahead to split between the last character and first digit found.
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
    
    /**
     * FormulaPart getPart(int i)
     * 
     * @param i
     * @return FormulaPart in the ArrayList elements
     * 
     * Usage:
     *  {Formula object}.getPart(i).getElement() -> returns CElement of ArrayList<CElement> @ i
     *  {Formula object}.getPart(i).getCount() -> returns subscript/count of the CElement of ArrayList<CElement> @ i
     *  {Formula object}.getPart(i).getMass() -> returns the mass of the FormulaPart factoring in the subscript/count
     */
    
    //Returns a part of a formula or Unobtainium if out of bounds or nonexistant 
    public FormulaPart getPart(int i){ if(i >= elements.size()){ return new FormulaPart(); }else{ return elements.get(i); } }
    
    @Override
    public String toString(){ return this.formula; }
}

//Data structure for parts of a formula containing the CElement and its subscript/count
class FormulaPart {
    private CElement element;
    private int count;
    public FormulaPart(){ this(CuleCalc.elements.get(0),1);}
    public FormulaPart(CElement element, int count){
        this.element = element;
        this.count = count;
    }
    public CElement getElement(){ return this.element; }
    public int getCount(){ return this.count; }
    public int getSubscript(){ return this.count; }
    public double getMass(){ return element.getMass() * count; }
    
    @Override
    public String toString(){ return this.element.getSymbol() + this.count; }
}

