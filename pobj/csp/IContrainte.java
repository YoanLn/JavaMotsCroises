package pobj.csp;

import pobj.motx.grille.GrillePotentiel;

public interface IContrainte {
    int reduce(GrillePotentiel grille);
}
