package pobj.motx.grille;

public class Case {
	private int lig, col;
	private char c;
	
	public Case(int lig, int col, char c) {
		this.c = c;
		this.lig = lig;
		this.col = col;
	}
	
	public int getLig(){
		return this.lig;
	}
	
	public int getCol(){
		return this.col;
	}
	
	public char getChar(){
		return this.c;
	}
	
	public void setChar(char c){
		this.c = c;
	}
	
	public boolean isVide(){
		return(this.c == ' ');
	}
	
	public boolean isPleine(){
		return(this.c == '*');
	}
	
	@Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Case other = (Case) obj;
        return lig == other.lig && col == other.col && c == other.c;
    }
}
