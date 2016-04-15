
package culecalc;

import java.io.InputStream;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import java.util.ArrayList;

public class XMLReader {
   private ArrayList<CElement> elements = new ArrayList<CElement>();
   public XMLReader(String document) {
       if(!document.endsWith(".xml")){document += ".xml";}
      DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

      try {
         DocumentBuilder builder = factory.newDocumentBuilder();

         InputStream fis = getClass().getResourceAsStream("/culecalc/XML/" + document);
         Document doc = builder.parse(fis);

         Element element = doc.getDocumentElement();

         NodeList nodes = element.getElementsByTagName("element");
         for(int t = 0; t < nodes.getLength(); t++){
             Node cur = nodes.item(t);
            if (cur.getNodeType() == Node.ELEMENT_NODE) {
               Element e = (Element) cur;
               String name = e.getElementsByTagName("name").item(0).getTextContent();
               String symbol = e.getElementsByTagName("symbol").item(0).getTextContent();
               int protons = Integer.parseInt(e.getAttribute("number"));
               double mass = Double.parseDouble(e.getElementsByTagName("mass").item(0).getTextContent());
               int neutrons = (int)(Math.round(mass)) - protons;
               int valence = Integer.parseInt(e.getElementsByTagName("valence").item(0).getTextContent());
               
               elements.add(new CElement(name,symbol,protons,protons,neutrons,mass,valence));
            }
         }
      } catch (Exception ex) {
         ex.printStackTrace();
      }
   }
   
   public ArrayList<CElement> getElements(){ 
       elements.add(0,new CElement("Unubtanium","Uub",0,0,0,0.0,0)); 
       return this.elements;
   }
}