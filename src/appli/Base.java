package appli;

import java.util.LinkedList;

public class Base {
    /** pile ascendante de cartes (liste chainée) */
    public final LinkedList<Integer> pileAsc = new LinkedList<>();
    /** pile descendante de cartes (liste chainée) */
    public final LinkedList<Integer> pileDesc = new LinkedList<>();

    /**
     *
     * @param c
     */
    public Base(Cartes c)
    {
        this.pileAsc.push(c.prendrePremCarte());
        this.pileDesc.push(c.prendreDernCarte());
    }

    /**
     *
     * @return
     */
    public Integer getCartePileAsc()
    {
        return pileAsc.peek();
    }

    /**
     *
     * @return
     */
    public Integer getCartePileDesc()
    {
        return pileDesc.peek();
    }

    /**
     *
     * @param carte
     */
    public void setCartePileAsc(Integer carte)
    {
        this.pileAsc.clear();
        this.pileAsc.push(carte);
    }

    /**
     *
     * @param carte
     */
    public void setCartePileDesc(Integer carte)
    {
        this.pileDesc.clear();
        this.pileDesc.push(carte);
    }

    /**
     *
     * @return
     */
    public StringBuilder pileAscToString()
    {
        StringBuilder s = new StringBuilder("^[");
        s.append(String.format("%02d", this.pileAsc.peek()));

        return s.append("]");
    }

    /**
     *
     * @return
     */
    public StringBuilder pileDescToString()
    {
        StringBuilder s = new StringBuilder("v[");
        s.append(String.format("%02d", this.pileDesc.peek()));

        return s.append("]");
    }
}