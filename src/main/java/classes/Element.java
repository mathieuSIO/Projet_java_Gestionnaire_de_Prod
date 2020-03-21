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
	private String prixA;
    private String prixV;
    

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
    public String getPrixA() {
        return prixA;
    }
    public void setPrixA(String prixA) {
		this.prixA = prixA;
	}
    public String getPrixV() {
        return prixV;
    }
	public void setPrixV(String prixV) {
		this.prixV = prixV;
	}

    /// LE CONSTRUCTEUR
    public Element(String codeE, String nomE, String qteE, String unite, String prixA, String prixV) {
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
    
    public String toString() {
    	return this.codeE + " " + this.nomE + " " + this.qteE + " " + this.unite + " " + this.prixA + "€ " + this.prixV + "€";
    }
    

}
