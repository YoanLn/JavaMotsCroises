package pobj.motx.csp;

import pobj.csp.IVariable;
import pobj.motx.grille.Case;
import pobj.motx.grille.Emplacement;
import java.util.ArrayList;
import java.util.List;

public class DicoVariable implements IVariable {
    private int index; 
    private GrilleContrainte grilleContrainte; 

    // Constructeur
    public DicoVariable(int index, GrilleContrainte gc) {
        this.index = index;
        this.grilleContrainte = gc;
    }
    
    public int getIndex() {
    	return this.index;
    }

    @Override
    public List<String> getDomain() {
        List<String> domain = new ArrayList<>();
        Emplacement emplacement = grilleContrainte.getGrille().getPlaces().get(index);

        StringBuilder currentWord = new StringBuilder(); 
        for (int i = 0; i < emplacement.size(); i++) {
            Case caseActuelle = emplacement.getCase(i);
            if (caseActuelle != null) {
                String lettre = String.valueOf(caseActuelle.getChar());
                
                if (!lettre.isEmpty()) {
                    currentWord.append(lettre); 
                } else {
                    if (currentWord.length() > 0) {
                        domain.add(currentWord.toString()); 
                        currentWord.setLength(0);
                    }
                }
            }
        }
        if (currentWord.length() > 0) {
            domain.add(currentWord.toString()); 
        }
        return domain; 
    }
   
    @Override
    public String toString() {
        return "DicoVariable{" + "index=" + index + ", domain=" + getDomain() + '}';
    }
}