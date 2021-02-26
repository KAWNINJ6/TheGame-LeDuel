package appli;

import java.util.ArrayList;
import java.util.Random;

public class Cartes {

    private final ArrayList<Integer> cartes = new ArrayList<>();
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
    public Integer piocherCarte()
    {
        Random rd = new Random();
        Integer idx = rd.nextInt(this.cartes.size());
        Integer pch = this.cartes.get(idx);

        retirerCarte(idx);

        return pch;
    }

    /**
     *
     * @param idx
     */
    private void retirerCarte(int idx)
    {
        this.cartes.remove(idx);
    }

    /**
     *
     * @return
     */
    public Integer piocherPremCarte()
    {
        Integer pch = this.cartes.get(0);
        this.cartes.remove(0);

        return pch;
    }

    /**
     *
     * @return
     */
    public Integer piocherDernCarte()
    {
        Integer pch = this.cartes.get(cartes.size()-1);
        this.cartes.remove(cartes.size()-1);

        return pch;
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