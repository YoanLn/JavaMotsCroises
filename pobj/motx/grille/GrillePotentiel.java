package pobj.motx.grille;

import java.util.ArrayList;
import java.util.List;
import pobj.motx.mots.Dictionnaire;

public class GrillePotentiel {
    private GrillePlaces grille; 
    private Dictionnaire dicoComplet; 
    private List<Dictionnaire> motsPot;
    private List<Dictionnaire> dicoPotentiels;


    public GrillePotentiel(GrillePlaces grille, Dictionnaire dicoComplet) {
        this.grille = grille;
        this.dicoComplet = dicoComplet;
        this.motsPot = initialiserMotsPot(grille, dicoComplet);
    }

    public GrillePotentiel(GrillePlaces grille, Dictionnaire dicoComplet, List<Dictionnaire> dicoPotentiels) {
        this.grille = grille;
        this.dicoComplet = dicoComplet;
        this.dicoPotentiels = dicoPotentiels != null ? dicoPotentiels : initialiserDictionnairesPotentiels();
        this.motsPot = initialiserMotsPot(grille, dicoComplet);
    }

    private List<Dictionnaire> initialiserMotsPot(GrillePlaces grille, Dictionnaire dicoComplet) {
        List<Dictionnaire> motsPot = new ArrayList<>();
        for (Emplacement e : grille.getPlaces()) {
            Dictionnaire dicoTmp = dicoComplet.copy();
            dicoTmp.filtreLongueur(e.size());
            for (int i = 0; i < e.size(); i++) {
                Case c = e.getCase(i);
                if (c.getChar() != ' ') {
                    dicoTmp.filtreParLettre(c.getChar(), i);
                }
            }
            motsPot.add(dicoTmp);
        }
        return motsPot;
    }
    
    private List<Dictionnaire> initialiserDictionnairesPotentiels() {
        List<Dictionnaire> listeDictionnaires = new ArrayList<>();
        for (int i = 0; i < grille.getPlacesSize(); i++) {
            Dictionnaire domaineEmplacement = dicoComplet.copy();
            listeDictionnaires.add(domaineEmplacement);
        }
        return listeDictionnaires;
    }
    
    public boolean isDead() {
        for (int i = 0; i < motsPot.size(); i++) {
            if (motsPot.get(i).size() == 0) {
                return true; 
            }
        }
        return false;
    }
    
    public List<Dictionnaire> getMotsPot() {
    	return this.motsPot;
    }
    
    public GrillePotentiel fixer(int m, String soluce) {
        GrillePlaces nouvelleGrille = this.grille.fixer(m, soluce);
        List<Dictionnaire> nouveauxDicoPotentiels = new ArrayList<>();
        int size=0;
        if(this.dicoPotentiels != null) {
        	size = this.dicoPotentiels.size();
        }
        for (int i = 0; i < size; i++) {
            if (i == m) {
                Dictionnaire dicoFixe = new Dictionnaire();
                dicoFixe.add(soluce);
                nouveauxDicoPotentiels.add(dicoFixe);
            } else {
                Dictionnaire dicoExistant = this.dicoPotentiels.get(i);
                nouveauxDicoPotentiels.add(dicoExistant != null ? dicoExistant : new Dictionnaire());
            }
        }
        return new GrillePotentiel(nouvelleGrille, this.dicoComplet, nouveauxDicoPotentiels);
    }
    
    public GrillePlaces getGrille() {
    	return grille;
    }
    public Dictionnaire getDico() {
    	return this.dicoComplet;
    }
}