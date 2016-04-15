package culecalc;

import java.util.ArrayList;

public class Formula {
    private int coefficient;
    private String formula = "";
    private ArrayList<Object> elements = new ArrayList<Object>();
    public Formula(){ this(""); }
    public Formula(String formula){
        this.formula = formula;
        this.coefficient = getCoefficient();
        this.elements = getElements();
    }
    
    public int getCoefficient(){
        int coefficient = 0;
        for(int i = 0; i < formula.length(); i++){
            if(!(formula.charAt(i) > '0' && formula.charAt(i) <= '9')){ 
                coefficient = Integer.parseInt(formula.substring(0,i));
                break;
            }
        }
        return coefficient;
    }
    public ArrayList<Object> getElements(){
        ArrayList<Object> elements = new ArrayList<Object>();
        for(int i = 0; i < formula.length()- 1; i++){
            if(formula.charAt(i) >= 'A' && formula.charAt(i) <= 'Z'){ 
                if(formula.charAt(i+1) >= 'a' && formula.charAt(i+1) <= 'z'){
                    
                }else{
                    
                }
            }
        }
        return elements;
    }
}
