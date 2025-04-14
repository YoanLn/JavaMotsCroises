package pobj.motx.grille;

public class Grille {
	private Case g[][];
	private int hauteur, largeur;
	
	public Grille(int hauteur, int largeur){
			this.hauteur = hauteur;
			this.largeur = largeur;
			g = new Case[hauteur][];
			for(int i = 0; i < hauteur; i ++) {
				g[i] = new Case[largeur];
				for(int j = 0; j < largeur; j++) {
					g[i][j] = new Case(i,j,' ');
				}
			}
	}
	
	public Case getCase(int lig, int col) {
		if(g[lig][col].getLig() != lig) {
			return null;
		}
		return g[lig][col];
	}
	
	public String toString() {
		return GrilleLoader.serialize(this, false);
	}
	
	public int nbLig() {
		return this.hauteur;
	}
	
	public int nbCol() {
		return this.largeur;
	}
	
	public Grille copy() {
		Grille grilleCopy = new Grille(this.hauteur,this.largeur);
		for(int i = 0; i < this.hauteur; i++) {
			for(int j = 0; j < this.largeur; j++) {
				grilleCopy.g[i][j] = new Case(i,j,this.g[i][j].getChar());
			}
		}
		return grilleCopy;
	}
}
