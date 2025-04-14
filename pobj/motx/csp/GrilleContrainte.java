package pobj.motx.csp;

import pobj.motx.grille.GrillePlaces;
import pobj.motx.grille.GrillePotentiel;
import pobj.motx.mots.Dictionnaire;
import pobj.csp.IContrainte;
import pobj.motx.grille.Case;
import pobj.motx.grille.Emplacement;
import java.util.ArrayList;
import java.util.List;

public class GrilleContrainte extends GrillePotentiel {
    private List<IContrainte> contraintes;
    
    public GrilleContrainte(GrillePlaces grille, Dictionnaire dicoComplet) {
        super(grille, dicoComplet);
        contraintes = new ArrayList<>();

        for (int i = 0; i < this.getGrille().getPlaces().size(); i++) {
            for (int j = i + 1; j < this.getGrille().getPlaces().size(); j++) {
                detecterCroisements(i, j);
            }
        }
        if (!propage()) {
            System.out.println("La grille n'est pas réalisable.");
        }
    }

    public GrilleContrainte(GrillePlaces grille, Dictionnaire dicoComplet, List<Dictionnaire> dicoPotentiels) {
        super(grille, dicoComplet, dicoPotentiels);
        contraintes = new ArrayList<>();
        initialiserContraintes();
        if (!propage()) {
            System.out.println("La grille n'est pas réalisable.");
        }
    }

    private void initialiserContraintes() {
        for (int i = 0; i < this.getGrille().getPlaces().size(); i++) {
            for (int j = i + 1; j < this.getGrille().getPlaces().size(); j++) {
                detecterCroisements(i, j);
            }
        }
    }

    public List<IContrainte> getContraintes() {
        return contraintes;
    }

    private void detecterCroisements(int m1, int m2) {
        Emplacement emplacement1 = this.getGrille().getPlaces().get(m1);
        Emplacement emplacement2 = this.getGrille().getPlaces().get(m2);

        for (int i = 0; i < emplacement1.size(); i++) {
            for (int j = 0; j < emplacement2.size(); j++) {
                Case caseM1 = emplacement1.getCase(i);
                Case caseM2 = emplacement2.getCase(j);

                if (caseM1.getLig() == caseM2.getLig() && caseM1.getCol() == caseM2.getCol() && !caseM1.isPleine()) {
                    contraintes.add(new CroixContrainte(m1, i, m2, j));
                }
            }
        }
    }
    
    @Override
    public GrilleContrainte fixer(int m, String soluce) {
        GrillePlaces nouvelleGrille = this.getGrille().fixer(m, soluce);
        
        List<Dictionnaire> nouveauxPotentiels = new ArrayList<>(this.getMotsPot());
        Dictionnaire dicoFixe = new Dictionnaire();
        
        dicoFixe.add(soluce);
        nouveauxPotentiels.set(m, dicoFixe);
        GrilleContrainte nouvelleGrilleContrainte = new GrilleContrainte(nouvelleGrille, this.getDico(), nouveauxPotentiels);
        
        for (IContrainte contrainte : nouvelleGrilleContrainte.contraintes) {
            contrainte.reduce(nouvelleGrilleContrainte);
        }

        return nouvelleGrilleContrainte;
    }
    
    private boolean propage() {
        while (true) {
            int motsElimines = 0;
            for (IContrainte contrainte : contraintes) {
                motsElimines += contrainte.reduce(this);
            }
            if (isDead()) {
                return false;
            }
            if (motsElimines == 0) {
                return true;
            }
        }
    }
}