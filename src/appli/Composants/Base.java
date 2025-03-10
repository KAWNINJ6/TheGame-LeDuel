package appli.Composants;

import java.util.LinkedList;

/**
 * La classe Base represente les 2 bases qu'un joueur à besoin
 * une ascendante(^) et l'autre descendante (v)
 * qui constituent le plan du jeu
 * */
public class Base {
    /** pile ascendante de cartes (liste chainée) */
    public LinkedList<Integer> pileAsc = new LinkedList<>();
    /** pile descendante de cartes (liste chainée) */
    public LinkedList<Integer> pileDesc = new LinkedList<>();

    /**
     * Constructeur des bases d'un joueur.
     *
     * @param c         le paquet de cartes du joueur
     */
    public Base(Cartes c)
    {
        this.pileAsc.push(c.prendrePremCarte()); //insère la carte 1 dans la pile asc
        this.pileDesc.push(c.prendreDernCarte()); //insère la carte 60 dans la pile desc
    }

    /**
     * Retourne la valeur de la base ascendante
     *
     * @return          la valeur de la base
     */
    public Integer getCartePileAsc()
    {
        return pileAsc.peek();
    }

    /**
     * Retourne la valeur de la base descendante
     *
     * @return          la valeur de la base
     */
    public Integer getCartePileDesc()
    {
        return pileDesc.peek();
    }

    /**
     * Ajoute une nouvelle carte dans la pile ascendante
     *
     * @param carte         la carte à ajouter
     */
    public void setCartePileAsc(Integer carte)
    {
        this.pileAsc.clear();
        this.pileAsc.push(carte);
    }

    /**
     * Ajoute une nouvelle carte dans la pile descendante
     *
     * @param carte         la carte à ajouter
     */
    public void setCartePileDesc(Integer carte)
    {
        this.pileDesc.clear();
        this.pileDesc.push(carte);
    }

    /**
     * Crée une chaine de caractères contenant la valeur de la base ascendante
     *
     * @return          la chaine de caractères
     */
    public String pileAscToString()
    {
        return "^[" + String.format("%02d", this.pileAsc.peek()) + "]";
    }

    /**
     * Crée une chaine de caractères contenant la valeur de la base descendante
     *
     * @return          la chaine de caractères
     */
    public String pileDescToString()
    {
        return "v[" + String.format("%02d", this.pileDesc.peek()) + "]";
    }
}