package fr.miage.lcl.model;

import java.util.ArrayList;
import java.util.Iterator;

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

    public ArrayList<Element> getLesAchats() {
        return lesAchats;
    }
    /// CONSTRUCTEUR
    public Usine(){
        leStock = new ArrayList<Element>();
        lesChaines = new ArrayList<ChaineProd>();
        lesAchats = new ArrayList<Element>();
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
        Iterator<Element> it = leStock.iterator();
        while (it.hasNext()) {
            Element e = it.next();
            if(elem == e){
                leStock.remove(e);
            };
        }
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
        Iterator<Element> it = lesAchats.iterator();
        while (it.hasNext()) {
            Element e = it.next();
            if(elem == e){
                lesAchats.remove(e);
            };
        }
    }

    /**
     * Permet de calculer le coût total de la liste d'achat
     * @return le coût total
     */
    public int lecoutdesAchat(){
        int somme = 0;
        Iterator<Element> it = lesAchats.iterator();
        while (it.hasNext()) {
            Element e = it.next();
            somme += Integer.parseInt(e.getPrixA());
        }
        return somme;
    }

    /**
     * Permet d'acheter tout les éléments présents dans la liste d'achat
     */
    public int acheter(){

        Iterator<Element> it = lesAchats.iterator();
        while (it.hasNext()) {
            Element e = it.next();
            leStock.add(e);
        }

        //Affichage de la somme à payer
        return lecoutdesAchat();

    }
}
