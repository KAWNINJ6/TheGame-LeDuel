package appli;

import java.util.ArrayList;
import java.util.Random;

/**
 * Représente le paquet de carte dont dispose un joueur
 * un paquet est compossé des cartes allant de 1 à 60
 * */
public class Cartes {
    /** paquet de cartes (Tableau) */
    private final ArrayList<Integer> cartes = new ArrayList<>();
    /** nombre de carte maximum utilisables */
    private final int nbCarteMax = 60;

    /**
     * Constructeur d'un paquet de cartes.
     */
    public Cartes()
    {
        for(int carte = 1; this.cartes.size() <= nbCarteMax-1; ++carte) { // toutes les cartes de 1 à 60
            this.cartes.add(carte);
        }
    }

    /**
     * Pioche une carte aléatoirement dans le paquet.
     *
     * @return          la carte piochée
     */
    public Integer prendreCarte()
    {
        Random rd = new Random();
        Integer idx = rd.nextInt(this.cartes.size());
        Integer pch = this.cartes.get(idx);

        supCarte(idx);

        return pch;
    }

    /**
     * Pioche la première carte du paquet.
     *
     * @return          la carte piochée
     */
    public Integer prendrePremCarte()
    {
        Integer pch = this.cartes.get(0);
        this.cartes.remove(0);

        return pch;
    }

    /**
     * Pioche la carte carte du paquet.
     *
     * @return          la carte piochée
     */
    public Integer prendreDernCarte()
    {
        Integer pch = this.cartes.get(cartes.size()-1);
        this.cartes.remove(cartes.size()-1);

        return pch;
    }

    /**
     * Supprime la carte du paquet à un indice donné.
     *
     * @param idx           l'indice de la carte
     */
    private void supCarte(int idx)
    {
        this.cartes.remove(idx);
    }

    /**
     * Donne le nombre de cartes dans le paquet de cartes.
     *
     * @return          le nombre de carte dans le paquet
     */
    public int getNbCarte()
    {
        return this.cartes.size();
    }

}