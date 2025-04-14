package pobj.motx.csp;

import pobj.motx.mots.Dictionnaire;
import pobj.motx.mots.EnsembleLettre;
import pobj.csp.IContrainte;
import pobj.motx.grille.GrillePotentiel;

public class CroixContrainte implements IContrainte {
    private int m1, c1, m2, c2;

    public CroixContrainte(int m1, int c1, int m2, int c2) {
        this.m1 = m1;
        this.c1 = c1;
        this.m2 = m2;
        this.c2 = c2;
    }

    @Override
    public int reduce(GrillePotentiel grille) {
        Dictionnaire mots1 = grille.getMotsPot().get(m1);
        Dictionnaire mots2 = grille.getMotsPot().get(m2);

        EnsembleLettre l1 = mots1.calculEnsembleLettre(c1);
        EnsembleLettre l2 = mots2.calculEnsembleLettre(c2);
        
        EnsembleLettre intersection = l1.intersection(l2);

        int motsElimines = 0;
        if (l1.size() > intersection.size()) {
            motsElimines += mots1.filtreParEnsemble(c1, intersection);
        }
        if (l2.size() > intersection.size()) {
            int motsEliminesM2 = mots2.filtreParEnsemble(c2, intersection);
            motsElimines += motsEliminesM2;
        }
        return motsElimines;
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        
        CroixContrainte that = (CroixContrainte) o;
        
        return m1 == that.m1 && c1 == that.c1 && m2 == that.m2 && c2 == that.c2;
    }
}