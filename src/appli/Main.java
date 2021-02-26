package appli;

import java.util.ArrayList;
import static java.util.Collections.sort;

public class Main {

    private final ArrayList<Integer> main = new ArrayList<>();
    private final int nbCarteMax = 6;

    /**
     *
     * @param c
     */
    public Main(Cartes c)
    {
        for (;this.main.size() <= nbCarteMax-1;){
                this.main.add(c.piocherCarte());
            }
    }

    /**
     *
     * @param carte
     */
    public void setMain(Integer carte)
    {
        assert (!(main.size() < nbCarteMax));

        this.main.add(carte);
    }

    /**
     *
     * @param idx
     * @return
     */
    public Integer getMain(int idx)
    {
        Integer pch = this.main.get(idx);
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
     * @return
     */
    public int trvCarte(Integer carte)
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
    public StringBuilder afficherMain()
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