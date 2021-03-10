package appli;

/**
 *  enumeration des noms possibles des joueurs
 */
enum NomJoueur
{
    NORD, SUD
}
/**
 * La classe joueur répresente un joueur du jeu qui peut jouer des coups
 * Il possède un nom : NORD ou SUD
 * Un paquet de cartes
 * Une pile ascendante et une descendante
 * */
public class Joueur {
    /** nombre de joueurs instanciés */
    private static int nbJoueurs = 0;

    /** cartes du joueur qui compose sa pioche */
    private Cartes pioche;
    /** base du joueur */
    private Base base;
    /** main du joueur */
    private Main main;
    /** nom du joueur */
    private final NomJoueur nom;
    /** nombre de joueurs instanciés */
    private static int nbJoueurs = 0;

    /**
     * constructeur du joueur, applique le nom du joueur
     * (applique NORD au premier, SUD au deuxième)
     */
    public Joueur()
    {
        ++nbJoueurs;

        switch (nbJoueurs) {
            case 1: this.nom = NomJoueur.NORD;
                    break;
            case 2: this.nom = NomJoueur.SUD;
                    break;
            default: throw new IllegalStateException("Beaucoup trop de joueur, il en existe deja " + nbJoueurs);
        }
    }

    /**
     * pose une carte de la main du joueur dans la pile ascendante du joueur.
     *
     * @param carte         la carte à déposer sur la pile
     */
    public void poseCartePileAsc(Integer carte)
    {
        int idx = this.main.trvIdxCarte(carte);
        this.base.setCartePileAsc(this.main.prendreCarte(idx));
    }

    /**
     * vérifie si la carte est posable sur la pile ascendante.
     *
     * @param carte         la carte à poser
     * @return              si la carte est posable sur la pile ou non
     */
    public boolean verifPoseCartePileAsc(Integer carte)
    {
        if (carte > this.base.getCartePileAsc()) {
            return true;
        }
        else return carte - 10 == this.base.getCartePileAsc();
    }

    /**
     * pose une carte de la main du joueur dans la pile descendante du joueur.
     *
     * @param carte         la carte à déposer sur la pile
     */
    public void poseCartePileDesc(Integer carte)
    {
        int idx = this.main.trvIdxCarte(carte);
        this.base.setCartePileDesc(this.main.prendreCarte(idx));
    }

    /**
     * vérifie si la carte est posable sur la pile descendante.
     *
     * @param carte         la carte à poser
     * @return              si la carte est posable sur la pile ou non
     */
    public boolean verifPoseCartePileDesc(Integer carte)
    {
        if (carte < this.base.getCartePileDesc()) {
            return true;
        }
        else return carte + 10 == this.base.getCartePileDesc();
    }

    /**
     * Pose une carte de la main du joueur dans la pile ascendante du joueur adverse.
     *
     * @param joueurAdv         le joueur adverse
     * @param carte             la carte à poser
     */
    public void poseCartePileAscAdv(Joueur joueurAdv, Integer carte)
    {
        int idx = this.main.trvIdxCarte(carte);
        joueurAdv.base.setCartePileAsc(this.main.prendreCarte(idx));
    }

    /**
     * vérifie si la carte est posable sur la pile ascendante adverse.
     *
     * @param joueurAdv         le joueur adverse
     * @param carte             la carte à poser
     * @return                  si la carte est posable sur la pile ou non
     */
    public boolean verifPoseCartePileAscAdv(Joueur joueurAdv, Integer carte)
    {
        return carte < joueurAdv.base.getCartePileAsc();
    }

    /**
     * Pose une carte de la main du joueur dans la pile descendante du joueur adverse.
     *
     * @param joueurAdv         le joueur adverse
     * @param carte             la carte à poser
     */
    public void poseCartePileDescAdv(Joueur joueurAdv, Integer carte)
    {
        int idx = this.main.trvIdxCarte(carte);
        joueurAdv.base.setCartePileDesc(this.main.prendreCarte(idx));
    }

    /**
     * vérifie si la carte est posable sur la pile descendante adverse.
     *
     * @param joueurAdv         le joueur adverse
     * @param carte             la carte à poser
     * @return                  si la carte est posable sur la pile ou non
     */
    public boolean verifPoseCartePileDescAdv(Joueur joueurAdv, Integer carte)
    {
        return carte > joueurAdv.base.getCartePileDesc();
    }

    /**
     * Vérifie si la carte est dans la main du joueur.
     *
     * @param carte         la carte à trouver
     * @return              si la carte est dans la main ou non
     */
    public boolean aCetteCarteEnMain(Integer carte)
    {
        return this.main.carteExiste(carte);
    }

    /**
     * Retourne le nombre de carte dans la main du joueur.
     *
     * @return              le nombre de cartes dans la main du joueur
     */
    public int nbDeCarteEnMain()
    {
        return this.main.getNbCarte();
    }

    /**
     * Retourne le nombre de carte dans la pioche du joueur.
     *
     * @return          le nombre de cartes dans la pioche du joueur
     */
    public int nbDeCarteDansPioche()
    {
        return this.pioche.getNbCarte();
    }

    /**
     * Crée une chaine de caractères contenant le nom et la main du joueur.
     *
     * @return          la chaine de caractères
     */
    public StringBuilder InfoMainJoueurToString()
    {
        StringBuilder s = new StringBuilder("cartes ");
        s.append(nom);
        s.append(" ");

        return s.append(main.mainToString());
    }

    /**
     * Crée une chaine de caractères contenant le nom du joueur, les bases du joueur
     * ainsi que le nombre de cartes dans sa main et pioche.
     *
     * @return          la chaine de caractères
     */
    public StringBuilder InfoJoueurToSring()
    {
        StringBuilder s = new StringBuilder();

        s.append(this.nom);
        if (this.nom == NomJoueur.SUD)
            s.append(" ");
        s.append(" ").append(this.base.pileAscToString());
        s.append(" ").append(this.base.pileDescToString());
        s.append(" ");
        s.append("(m").append(main.getNbCarte());
        s.append("p").append(pioche.getNbCarte());

        return s.append(")");
    }

    /**
     * Renvoie le nom du joueur
     *
     * @return          la chaine de caractères
     */
    public String getNom()
    {
        if (this.nom == NomJoueur.NORD) {
            return "NORD";
        }
        if (this.nom == NomJoueur.SUD) {
            return "SUD";
        }
        return "Nom pas défini";
    }

    /**
     * getter de la valeur d'une pile dans la base du joueur.
     *
     * @param pile          la pile voulue ^(ascendante) ou v(descendante)
     * @return              la valeur de la base
     * */
    public Integer getPileDansBase(char pile)
    {
        assert (pile == 'v' || pile == '^');

        if (pile == 'v')
            return this.base.getCartePileDesc();
        else if (pile == '^')
            return this.base.getCartePileAsc();

        return 0;
    }

    /**
     * getter de la carte dans la main du joueur.
     * (sans supprimer la carte de la main)
     *
     * @param idx          l'indice de la carte voulue
     * @return             la carte
     * */
    public Integer getCarteDansMain(int idx)
    {
        return this.main.getCarte(idx);
    }

    /**
     * Ajoute des cartes dans la main du joueur jusqu'à qu'elle soit pleine.
     */
    public void remplirMainComplet()
    {
        if (this.pioche.getNbCarte() > 0) {
            while (this.main.getNbCarte() < 6) {
                this.main.setCarte(this.pioche.prendreCarte());
            }
        }
    }

    /**
     * Ajoute 2 cartes dans la main du joueur.
     */
    public void remplirMain()
    {
       if (this.pioche.getNbCarte() > 0) {
           this.main.setCarte(this.pioche.prendreCarte());
           this.main.setCarte(this.pioche.prendreCarte());
       }
    }
}