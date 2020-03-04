import java.util.ArrayList;

public class Usine{

    // LES ATTRIBUTS
    private ArrayList<Element> leStock;
    private ArrayList<ChaineProd> lesChaines;
    private ArrayList<Element> lesAchats;

    /// LES GETTEURS
    public ArrayList<Element> getLeStock() {
        return leStock;
    }
    public ArrayList<ChaineProd> getLesChaines() {
        return lesChaines;
    }

    public HashMap<Element, Integer> getLesAchats() {
        return lesAchats;
    }
    /// CONSTRUCTEUR
    public Usine(){
        leStock = new ArrayList<Element>();
        lesChaines = new ArrayList<ChaineProd>();
        lesAchats = new HashMap<Element, Integer>();
    }


    /// LES METHODES

    /**
     * Ajoute l'élément/prod dans la liste de stock
     * @param leprod = le produit à ajouter au stock
     */
    public void stocker(Element leprod){
        leStock.add(leprod);
    }

    /**
     * Supprime l'élément du stock
     * @param elem = l'élément à supprimer
     */
    public void destocker(Element elem){
        // Faire le test de savoir si l'élément est dans la liste du stock ou non
        int i = 0;
        while(leStock[i]!=elem)&&(i<leStock.size()){
            i++;
        }
        leStock.remove(i);
    }

    /**
     * Ajoute un élément dans la liste d'achat
     * @param elem = l'élément à ajouter
     */
    public void ajouterunAchat(Element elem){
        lesAchats.add(elem);
    }

    /**
     * Permet de supprimer un élément de la liste d'achat
     * @param elem = l'élément/produit à supprimer de la liste
     */
    public void supprimerunAchat(Element elem){
        // Il faut vérifier que l'élément soit dans la liste
        int i = 0;
        while(lesAchats[i]!=elem)&&(i<leStock.size()){
            i++;
        }
        leStock.remove(i);
    }

    /**
     * Permet de calculer le coût total de la liste d'achat
     * @return le coût total
     */
    public int lecoutdesAchat(){
        int somme = 0;
        for (int i = 0; i<lesAchats.size();i++){
            somme += lesAchats[i].prixA;
        }
        return somme;
    }

    /**
     * Permet d'acheter tout les éléments présents dans la liste d'achat
     */
    public void acheter(){
        for (int i = 0; i<lesAchats.size();i++){
            stocker(lesAchats[i]);
        }
        //Affichage de la somme à payer
    }
}
