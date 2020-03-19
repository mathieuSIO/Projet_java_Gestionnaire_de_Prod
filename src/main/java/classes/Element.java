package classes;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Element {
	
    /// LES ATTRIBUTS
    private String codeE;
    private String nomE;
    private String qteE;
    private String unite;
    private float prixA;
    private float prixV;
    

    /// LES GETTEURS
    public String getCodeE() {
        return codeE;
    }
    public String getNomE() {
        return nomE;
    }
    public String getQteE() {
        return qteE;
    }
    public String getUnite() {
        return unite;
    }
    public float getPrixA() {
        return prixA;
    }
    public float getPrixV() {
        return prixV;
    }

    /// LE CONSTRUCTEUR
    public Element(String codeE, String nomE, String qteE, String unite, float prixA, float prixV) {
        this.codeE = codeE;
        this.nomE = nomE;
        this.qteE = qteE;
        this.unite = unite;
        this.prixA = prixA;
        this.prixV = prixV;
    }

    public Element(){
        codeE = "Test liaison entre classes et affichage";
    }
    /// LES METHODES
    

}

