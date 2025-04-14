package pobj.motx.grille;
import java.util.ArrayList;
import java.util.List;

public class Emplacement {
	private List<Case> lettres;
	private EmplacementType t = EmplacementType.VID;
	private int nbLettres;
	
	public Emplacement() {
		lettres = new ArrayList<Case>();
		this.nbLettres = 0;
	}
	
	public int size() {
		return lettres.size();
	}
	
	public Case getCase(int i) {
		if(i >= lettres.size()) {
			return null;
		}
		return lettres.get(i);
	}
	
	public EmplacementType getType() {
		return this.t;
	}
	
	public void add(Case c) {
	    if (this.lettres.contains(c)) {
	        this.t = EmplacementType.INC;
	        return;
	    }
		this.lettres.add(c);
		switch(this.t) {
			case VID: 
				this.t = EmplacementType.UNE;
				this.nbLettres++;
				break;
			case UNE:
				if(this.lettres.get(nbLettres-1).getLig() + 1 == c.getLig()) {
					this.t = EmplacementType.VER;
					this.nbLettres++;
					break;
				}
				else if(this.lettres.get(nbLettres-1).getCol() + 1 == c.getCol()) {
					this.t = EmplacementType.HOR;
					this.nbLettres++;
					break;
				}
				else{
					this.t = EmplacementType.INC;
					this.nbLettres++;
					break;
				}
			case VER:
				if(this.lettres.get(nbLettres-1).getLig() + 1 != c.getLig()) {
					this.t = EmplacementType.INC;
					this.nbLettres++;
					break;
				}
				else {
					break;
				}
			case HOR:
				if((this.lettres.get(nbLettres-1).getCol() + 1) != c.getCol()) {
					this.t = EmplacementType.INC;
					this.nbLettres++;
					break;
				}
				else {
					break;
				}
			case INC:
				this.nbLettres++;
				break;
		};
	}
    public boolean hasCaseVide() {
        for (Case c : lettres) {
            if (c.isVide()) { 
                return true; 
            }
        }
        return false;
    }
	
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for(Case l : lettres) {
			sb.append(l.getChar());
		}
		return sb.toString();
	}
}
