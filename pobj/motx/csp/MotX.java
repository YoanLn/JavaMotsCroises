package pobj.motx.csp;

import pobj.csp.ICSP;
import pobj.csp.IVariable;
import pobj.motx.grille.Case;
import pobj.motx.grille.Emplacement;
import java.util.ArrayList;
import java.util.List;

public class MotX implements ICSP {
    private List<DicoVariable> variables; 
    private GrilleContrainte grilleContrainte; 

    public MotX(GrilleContrainte gc) {
        this.grilleContrainte = gc;
        this.variables = new ArrayList<>();

        List<Emplacement> emplacements = gc.getGrille().getPlaces();
        for (int index = 0; index < emplacements.size(); index++) {
            Emplacement emplacement = emplacements.get(index);
            if (emplacement.hasCaseVide()) { 
                variables.add(new DicoVariable(index, gc));
            }
        }
    }

    @Override
    public List<IVariable> getVars() {
        return new ArrayList<>(variables);
    }

    @Override
    public boolean isConsistent() {
        for (IVariable variable : getVars()) {
            if (variable instanceof DicoVariable) {
                DicoVariable dicoVar = (DicoVariable) variable;
                Emplacement emplacement = grilleContrainte.getGrille().getPlaces().get(dicoVar.getIndex());
                StringBuilder motActuel = new StringBuilder();
                boolean hasEmptyCase = false;

                for (int i = 0; i < emplacement.size(); i++) {
                    Case caseActuelle = emplacement.getCase(i);
                    if (caseActuelle.isVide()) {
                        hasEmptyCase = true; 
                    } else {
                        motActuel.append(caseActuelle.getChar());
                    }
                }
                if (hasEmptyCase) {
                    continue; 
                }
                if (!grilleContrainte.getDico().contains(motActuel.toString())) {
                    return false; 
                }
            }
        }
        return true;
    }

    @Override
    public ICSP assign(IVariable vi, String val) {
        if (vi instanceof DicoVariable) {
            DicoVariable dicoVar = (DicoVariable) vi;
            GrilleContrainte nouvelleGrille = grilleContrainte.fixer(dicoVar.getIndex(), val);
            return new MotX(nouvelleGrille); 
        }
        return null; 
    }
}