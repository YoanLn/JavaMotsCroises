package pobj.motx.grille;

import java.util.ArrayList;
import java.util.List;

public class GrillePlaces {
    private List<Emplacement> places; 
    private int nbHorizontal;         
    private Grille grille;

    public GrillePlaces(Grille grille) {
    	this.grille = grille;
        this.places = new ArrayList<>();
        this.nbHorizontal = 0;

        for (int lig = 0; lig < grille.nbLig(); lig++) {
            cherchePlaces(getLig(grille, lig));
        }
        
        this.nbHorizontal = places.size();
        
        for (int col = 0; col < grille.nbCol(); col++) {
            cherchePlaces(getCol(grille, col));
        }
    }

    private List<Case> getLig(Grille grille, int lig) {
        List<Case> casesLigne = new ArrayList<>();
        for (int col = 0; col < grille.nbCol(); col++) {
            casesLigne.add(grille.getCase(lig, col));
        }
        return casesLigne;
    }

    private List<Case> getCol(Grille grille, int col) {
        List<Case> casesColonne = new ArrayList<>();
        for (int lig = 0; lig < grille.nbLig(); lig++) {
            casesColonne.add(grille.getCase(lig, col));
        }
        return casesColonne;
    }

    private void cherchePlaces(List<Case> cases) {
        Emplacement emplacement = new Emplacement();
        for (Case c : cases) {
            if (!c.isPleine()) { 
                emplacement.add(c);
            } else {  
                if (emplacement.size() > 1) {
                    places.add(emplacement);  
                }
                emplacement = new Emplacement();  
            }
        }
        if (emplacement.size() > 1) {
            places.add(emplacement);
        }
    }

    public List<Emplacement> getPlaces() {
        return new ArrayList<Emplacement>(places);  
    }

    public int getNbHorizontal() {
        return nbHorizontal;
    }
    
    public Grille getGrille() {
    	return this.grille;
    }
    
    public int getPlacesSize() {
    	return places.size();
    }
    
    public GrillePlaces fixer(int m, String soluce) {
        Grille nouvelleGrille = this.grille.copy();
        Emplacement emplacement = places.get(m);
        for (int i = 0; i < emplacement.size(); i++) {
            Case c = emplacement.getCase(i); 
            nouvelleGrille.getCase(c.getLig(), c.getCol()).setChar(soluce.charAt(i)); 
        }
        return new GrillePlaces(nouvelleGrille);
    }

}