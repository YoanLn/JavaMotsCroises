import pobj.csp.CSPSolver;
import pobj.csp.ICSP;
import pobj.motx.csp.GrilleContrainte;
import pobj.motx.csp.MotX;
import pobj.motx.grille.Grille;
import pobj.motx.grille.GrillePlaces;
import pobj.motx.mots.Dictionnaire;

/**
 * Classe principale pour démontrer l'utilisation du solveur de mots croisés
 */
public class Main {
    
    /**
     * Point d'entrée du programme
     * @param args arguments de la ligne de commande (non utilisés)
     */
    public static void main(String[] args) {
        // Exemple de création d'une petite grille 3x3
        Grille grille = new Grille(3, 3);
        
        // Définir certaines cases comme pleines (*)
        grille.getCase(0, 2).setChar('*');
        grille.getCase(2, 0).setChar('*');
        
        System.out.println("Grille initiale:");
        System.out.println(grille);
        
        // Charger le dictionnaire (à adapter selon l'emplacement du fichier dictionnaire)
        System.out.println("Chargement du dictionnaire...");
        Dictionnaire dico = Dictionnaire.loadDictionnaire("data/dico.txt");
        System.out.println("Dictionnaire chargé: " + dico.size() + " mots");
        
        // Extraction des emplacements de mots
        GrillePlaces grillePlaces = new GrillePlaces(grille);
        System.out.println("Nombre d'emplacements: " + grillePlaces.getPlaces().size());
        
        // Création des contraintes
        System.out.println("Création des contraintes...");
        GrilleContrainte grilleContrainte = new GrilleContrainte(grillePlaces, dico);
        
        // Création du problème
        MotX motx = new MotX(grilleContrainte);
        
        // Résolution
        System.out.println("Résolution de la grille...");
        CSPSolver solver = new CSPSolver();
        ICSP solution = solver.solve(motx);
        
        // Vérification de la solution
        if (solution.isConsistent() && solution.getVars().isEmpty()) {
            System.out.println("Solution trouvée!");
            System.out.println(grilleContrainte.getGrille());
        } else {
            System.out.println("Aucune solution trouvée.");
        }
    }
} 