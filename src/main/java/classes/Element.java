package classes;

public class Element {
    /// LES ATTRIBUTS
    private String codeE;
    private String nomE;
    private int qteE;
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
    public int getQteE() {
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
    public Element(String codeE, String nomE, int qteE, String unite, float prixA, float prixV) {
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

