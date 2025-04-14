package pobj.motx.mots;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Dictionnaire {
	private List<String> mots;
	
	public Dictionnaire(){
		this.mots = new ArrayList<String>();
	}
	
	public void add(String mot){
		mots.add(mot.toLowerCase());
	}
	
	public int size() {
		return mots.size();
	}
	
	public String get(int i) {
	    return mots.get(i);
	}
	
	public Dictionnaire copy() {
	    Dictionnaire dicoCopy = new Dictionnaire();
	    for (String s : this.mots) {
	        dicoCopy.add(new String(s));  
	    }
	    return dicoCopy;
	}
	

	public int filtreLongueur(int len) {
	    int removed = 0;
	    Iterator<String> it = mots.iterator();  
	    while (it.hasNext()) {
	        String s = it.next();
	        if (s.length() != len) {
	            it.remove(); 
	            removed++;
	        }
	    }
	    return removed;
	}
	
	public int filtreParLettre(char c, int i) {
	    int removed = 0;
	    Iterator<String> it = mots.iterator();  
	    while (it.hasNext()) {
	        String s = it.next();
	        if (s.length() <= i || s.charAt(i) != c) {
	            it.remove(); 
	            removed++;
	        }
	    }
	    return removed;
	}
	
	public static Dictionnaire loadDictionnaire(String path) {
	    Dictionnaire dico = new Dictionnaire();
	    try {
	        List<String> lines = Files.readAllLines(Paths.get(path));
	        dico.mots = new ArrayList<>(lines);  
	    } catch (IOException e) {
	        e.printStackTrace(); 
	    }
	    return dico;
	}

    public EnsembleLettre calculEnsembleLettre(int position) {
        EnsembleLettre ensemble = new EnsembleLettre();
        for (String mot : mots) {
            if (position < mot.length()) {
                ensemble.add(mot.charAt(position));
            }
        }
        return ensemble;
    }

    public int filtreParEnsemble(int index, EnsembleLettre ensemble) {
        int tailleInitiale = mots.size();
        mots.removeIf(mot -> index >= mot.length() || !ensemble.contains(mot.charAt(index)));
        return tailleInitiale - mots.size();
    }

    public boolean contains(String string) {
        return mots.contains(string); 
    }
}
