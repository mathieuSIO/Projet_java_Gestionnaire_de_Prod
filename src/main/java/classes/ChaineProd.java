package classes;

import java.util.HashMap;

public class ChaineProd{
    /// Les attributs
    private String codeC;
    private String nomC;
    private int niveauA;
    private HashMap<Element, Integer> entrees;
    private HashMap<Element, Integer> sorties;

    /// LES GETTEURS
    public String getCodeC() {
        return codeC;
    }

    public String getNomC() {
        return nomC;
    }

    public int getNiveauA() {
        return niveauA;
    }

    public HashMap<Element, Integer> getEntrees() {
        return entrees;
    }

    public HashMap<Element, Integer> getSorties() {
        return sorties;
    }

    /// Constructeur

    public ChaineProd(String codeC, String nomC, int niveauA, HashMap<Element, Integer> entrees, HashMap<Element, Integer> sorties) {
        this.codeC = codeC;
        this.nomC = nomC;
        this.niveauA = niveauA;
        this.entrees = entrees;
        this.sorties = sorties;
    }

    /// LES METHODES
}