package pobj.motx.mots;

import java.util.ArrayList;
import java.util.List;

public class EnsembleLettre {

    private List<Character> lettres;

    public EnsembleLettre() {
        this.lettres = new ArrayList<>();
    }

    public void add(char c) {
        if (!lettres.contains(c)) {
            lettres.add(c);
        }
    }

    public int size() {
        return lettres.size();
    }

    public boolean contains(char c) {
        return lettres.contains(c);
    }

    public EnsembleLettre intersection(EnsembleLettre autre) {
        EnsembleLettre intersection = new EnsembleLettre();
        for (char c : lettres) {
            if (autre.contains(c)) {
                intersection.add(c);
            }
        }
        return intersection;
    }

    @Override
    public String toString() {
        return lettres.toString();
    }
}