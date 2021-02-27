package appli;

import java.util.ArrayList;
import java.util.Random;

public class Cartes {
    /** paquet de cartes (Tableau) */
    private final ArrayList<Integer> cartes = new ArrayList<>();
    /** nombre de carte maximum utilisable */
    private final int nbCarteMax = 60;

    /**
     *
     */
    public Cartes()
    {
        for(int carte = 1; this.cartes.size() <= nbCarteMax-1; ++carte) {
            this.cartes.add(carte);
        }
    }

    /**
     *
     * @return
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
     *
     * @return
     */
    public Integer prendrePremCarte()
    {
        Integer pch = this.cartes.get(0);
        this.cartes.remove(0);

        return pch;
    }

    /**
     *
     * @return
     */
    public Integer prendreDernCarte()
    {
        Integer pch = this.cartes.get(cartes.size()-1);
        this.cartes.remove(cartes.size()-1);

        return pch;
    }

    /**
     *
     * @param idx
     */
    private void supCarte(int idx)
    {
        this.cartes.remove(idx);
    }

    /**
     *
     * @return
     */
    public int getNbCarte()
    {
        return this.cartes.size();
    }

}