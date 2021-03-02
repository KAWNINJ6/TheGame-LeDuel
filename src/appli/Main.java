package appli;

import java.util.ArrayList;
import static java.util.Collections.sort;

/**
 * La classe main represente la main du joueur
 * il peut poser et piocher des cartes dans sa main
 * */
public class Main {
    /** set de cartes */
    private final ArrayList<Integer> main = new ArrayList<>();
    /** nombre de cartes maximum utilisables */
    private final int nbCarteMax = 6;

    /**
     * constructeur de la main d'un joueur
     *
     * @param c le paquet de carte du joueur
     */
    public Main(Cartes c)
    {
        while (this.main.size() <= nbCarteMax-1) { // rempli la main
            this.main.add(c.prendreCarte());
        }
    }

    /**
     * Prend une carte dans la main.
     *
     * @param idx           l'indice de la carte
     * @return              la carte
     */
    public Integer prendreCarte(int idx)
    {
        Integer pch = getCarte(idx);
        this.main.remove(idx);
        return pch;
    }

    /**
     * Getter d'une carte de la main.
     *
     * @param idx           l'indice de la carte
     * @return              la carte
     */
    public Integer getCarte(int idx)
    {
        return this.main.get(idx);
    }

    /**
     * Ajoute une carte dans la main.
     *
     * @param carte         la carte a ajouter
     */
    public void setCarte(Integer carte)
    {
        assert (this.main.size() < this.nbCarteMax);

        this.main.add(carte);
    }

    /**
     * Recherche dans la main l'indice d'une carte.
     *
     * @param carte         la carte voulue
     * @return              l'indice de la carte dans la main
     */
    public int trvIdxCarte(Integer carte)
    {
        return this.main.indexOf(carte);
    }

    /**
     * Regarde si une carte est dans la main ou non.
     *
     * @param carte         la carte voulue
     * @return              si la carte est dans la main ou non
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
     * Retourne le nombre de cartes dans la main
     *
     * @return          le nombre de cartes
     */
    public int getNbCarte()
    {
        return this.main.size();
    }

    /**
     * Crée une chaine de caractères contenant les cartes de la main
     *
     * @return          la chaine de caractères
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
     * Trie les cartes de la main dans l'ordre croissant
     */
    private void trierMain()
    {
        sort(main);
    }
}