package culecalc;

import java.text.NumberFormat;
import java.util.ArrayList;

public class CFormula {
    private long coefficient;
    private String formula;
    private ArrayList<CFormulaPart> elements = new ArrayList<CFormulaPart>();
    private double mass;
    
    public CFormula(){}
    public CFormula(String formula){
        //Removes spaces
        this.formula = formatFormula(formula);
        this.coefficient = getCoefficient();
        this.elements = getElements();
        this.mass = getMass();
        //Sets the % composition for each FormulaPart
        for(CFormulaPart f : elements)
            f.setComposition(this.mass / this.coefficient);
    
    }
    
    //
    public final String formatFormula(String a){
        String ff ="";
        a.replaceAll("\\s", "");
        String[] split = this.formula.split("");
        for(int i = 0; i < split.length; i++){
            if(split[i].equals("(")) {
                
            }
        }  
        for(int i = 0; i < split.length; i++){
            if(!(split[i].charAt(0) > '0' && split[i].charAt(0) <= '9')){
                //Uses a Regex lookahead to split between the last character and first digit found.
                String[] e = split[i].split("(?<=[\\w&&\\D])(?=\\d)");
            }
        }
        
        return ff;
    }
    
    public final long getCoefficient(){
        //Uses a Regex lookahead to match first uppercase character and then splits between it and the previous character.
        String[] split = formula.split("(?=\\p{Upper})");
        try{
            return formula.length() >= 1 ? (Long.parseLong(!(split[0].charAt(0) > '0' && split[0].charAt(0) <= '9') ? "1" : split[0])) : 1;
        }catch(Exception ex){
            //TODO: Print error message in panel.
            System.out.println("Check your formula for errors!");
        }
        return 1;
    }
    
    //Returns an ArrayList of all the FormulaParts
    public final ArrayList<CFormulaPart> getElements(){
        if(this.formula.length() == 0){ return new ArrayList<CFormulaPart>(); }
        
        ArrayList<CFormulaPart> elements = new ArrayList<CFormulaPart>();
        //TODO: Take ()'s into account
        String[] split = this.formula.split("(?=\\p{Upper})");
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
                elements.add(new CFormulaPart(element, e.length > 1 ? Integer.parseInt(e[1]) : 1 ));
            }
        }
        
        return elements;
    }
    
    public int getSize(){ return this.elements.size(); }
    
    public final double getMass(){
        double mass = 0.0;
        for(CFormulaPart f : this.elements){
            mass += f.getElement().getMass() * f.getCount();
        }
        return mass * this.coefficient;
    }
    
    public String getMass(boolean units){ return this.getMass() + (units ? " amu" : ""); }
    
    /**
     * CFormulaPart getPart(int i)
     * 
     * @param i
     * @return FormulaPart in the ArrayList elements
     * Usage:
     *  {Formula object}.getPart(i).getElement() -> returns CElement of ArrayList<CElement> @ i
     *  {Formula object}.getPart(i).getCount() -> returns subscript/count of the CElement of ArrayList<CElement> @ i
     *  {Formula object}.getPart(i).getMass() -> returns the mass of the CFormulaPart as a double factoring in the subscript/count
     *  {Formula object}.getPart(i).getMass(boolean) -> returns the mass of the CFormulaPart as a string with or without units
     *  {Formula object}.getPart(i).getComposition() -> returns the % composition as a double
     *  {Formula object}.getPart(i).getComposition(boolean) -> returns the % composition as a formatted string or unformatted
     */
    
    //Returns a part of a formula or Unobtainium if out of bounds or nonexistant 
    public CFormulaPart getPart(int i){ if(i >= elements.size()){ return new CFormulaPart(); }else{ return elements.get(i); } }
    
    @Override
    public String toString(){ return this.formula; }
}

//Data structure for parts of a formula containing the CElement and its subscript/count and its % composition
class CFormulaPart {
    private CElement element;
    private int count;
    private double composition;
    
    public CFormulaPart(){ this(CuleCalc.elements.get(0),1);}
    public CFormulaPart(CElement element, int count){
        this.element = element;
        this.count = count;
    }
    public CElement getElement(){ return this.element; }
    public int getCount(){ return this.count; }
    public int getSubscript(){ return this.count; }
    public double getMass(){ return this.element.getMass() * count; }
    public String getMass(boolean units){ return this.getMass() + (units ? " amu" : ""); }
    public void setComposition(double formulaMass){ this.composition = this.getMass() / formulaMass; }
    public double getComposition(){ return this.composition; }
    public String getComposition(boolean format){
        NumberFormat percentFormat = NumberFormat.getPercentInstance();
	percentFormat.setMinimumFractionDigits(2);
        return format ? percentFormat.format(this.getComposition()) + " " + this.element.getSymbol() : String.valueOf(this.composition);
    }

    @Override
    public String toString(){ return this.element.getSymbol() + this.count; }
}

