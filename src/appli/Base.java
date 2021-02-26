package appli;

import java.util.LinkedList;

public class Base {

    public final LinkedList<Integer> pileAsc = new LinkedList<>();
    public final LinkedList<Integer> pileDesc = new LinkedList<>();

    public Base(Cartes c)
    {
        this.pileAsc.push(c.piocherPremCarte());
        this.pileDesc.push(c.piocherDernCarte());
    }

    /**
     *
     * @return
     */
    public Integer getPileAsc()
    {
        return pileAsc.peek();
    }

    /**
     *
     * @return
     */
    public Integer getPileDesc()
    {
        return pileDesc.peek();
    }

    /**
     *
     * @param carte
     */
    public void setPileAsc(Integer carte)
    {
        this.pileAsc.clear();
        this.pileAsc.push(carte);
    }

    /**
     *
     * @param carte
     */
    public void setPileDesc(Integer carte)
    {
        this.pileDesc.clear();
        this.pileDesc.push(carte);
    }

    /**
     *
     * @return
     */
    public StringBuilder afficherPileAsc()
    {
        StringBuilder s = new StringBuilder("^[");
        s.append(String.format("%02d", this.pileAsc.peek()));

        return s.append("]");
    }

    /**
     *
     * @return
     */
    public StringBuilder afficherPileDesc()
    {
        StringBuilder s = new StringBuilder("v[");
        s.append(String.format("%02d", this.pileDesc.peek()));

        return s.append("]");
    }
}