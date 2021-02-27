package appli;

import java.util.ArrayList;
import static java.util.Collections.sort;

public class Main {
    /** set de cartes */
    private final ArrayList<Integer> main = new ArrayList<>();
    /** nombre de cartes maximum utilisable */
    private final int nbCarteMax = 6;

    /**
     *
     * @param c
     */
    public Main(Cartes c)
    {
        for (;this.main.size() <= nbCarteMax-1;){
                this.main.add(c.prendreCarte());
            }
    }

    /**
     *
     * @param idx
     * @return
     */
    public Integer prendreCarte(int idx)
    {
        Integer pch = getCarte(idx);
        this.main.remove(idx);
        return pch;
    }

    /**
     *
     * @param idx
     * @return
     */
    public Integer getCarte(int idx)
    {
        return this.main.get(idx);
    }

    /**
     *
     * @param carte
     */
    public void setCarte(Integer carte)
    {
        assert (this.main.size() < this.nbCarteMax);

        this.main.add(carte);
    }

    /**
     *
     * @param carte
     * @return
     */
    public int trvIdxCarte(Integer carte)
    {
        return this.main.indexOf(carte);
    }

    /**
     *
     * @param carte
     * @return
     */
    public boolean carteExiste(Integer carte)
    {
        if (this.main.contains(carte))
        {
            return true;
        }
        return false;
    }

    /**
     *
     * @return
     */
    public int getNbCarte()
    {
        return this.main.size();
    }

    /**
     *
     * @return
     */
    public StringBuilder mainToString()
    {
        trierMain();
        StringBuilder s = new StringBuilder("{ ");

        for(int i = 0; i < main.size(); ++i) {

            if (i == 0){
                s.append(String.format("%02d", this.main.get(i)));
            }
            else {
                s.append(" ").append(String.format("%02d", this.main.get(i)));
            }
        }
        return s.append(" }");
    }

    /**
     *
     */
    private void trierMain()
    {
        sort(main);
    }
}