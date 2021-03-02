package appli;

/**
 *  enumeration des noms possibles des joueurs
 */
enum NomJoueur
{
    NORD, SUD
}

public class Joueur {
    /**
     * cartes du joueur qui compose sa pioche
     */
    private final Cartes pioche = new Cartes();
    /** base du joueur */
    private final Base base = new Base(this.pioche);
    /** main du joueur */
    private final Main main = new Main(this.pioche);
    /** nom du joueur */
    private final NomJoueur nom;
    /** nombre de joueurs instanciés */
    private static int nbJoueurs = 0;

    /**
     *
     */
    public Joueur()
    {
        switch (nbJoueurs) {
            case 0 -> {
                this.nom = NomJoueur.NORD;
                ++nbJoueurs;
            }
            case 1 -> {
                this.nom = NomJoueur.SUD;
                ++nbJoueurs;
            }
            default -> throw new IllegalStateException("Beaucoup trop de joueur, il en existe deja " + nbJoueurs);
        }
    }

    /**
     *
     * @param carte
     */
    public void poseCartePileAsc(Integer carte)
    {
        int idx = this.main.trvIdxCarte(carte);
        this.base.setCartePileAsc(this.main.prendreCarte(idx));
    }

    /**
     *
     * @param carte
     * @return
     */
    public boolean verifPoseCartePileAsc(Integer carte)
    {
        if (carte > this.base.getCartePileAsc()) {
            return true;
        }
        else if (carte-10 == this.base.getCartePileAsc()) {
            return true;
        }else {
            return false;
        }
    }

    /**
     *
     * @param carte
     */
    public void poseCartePileDesc(Integer carte)
    {
        int idx = this.main.trvIdxCarte(carte);
        this.base.setCartePileDesc(this.main.prendreCarte(idx));
    }

    /**
     *
     * @param carte
     * @return
     */
    public boolean verifPoseCartePileDesc(Integer carte)
    {
        if (carte < this.base.getCartePileDesc()) {
            return true;
        }
        else if (carte+10 == this.base.getCartePileDesc()) {
            return true;
        }else {
            return false;
        }
    }

    /**
     *
     * @param joueurAdv
     * @param carte
     */
    public void poseCartePileAscAdv(Joueur joueurAdv, Integer carte)
    {
        int idx = this.main.trvIdxCarte(carte);
        joueurAdv.base.setCartePileAsc(this.main.prendreCarte(idx));
    }

    /**
     *
     * @param joueurAdv
     * @param carte
     * @return
     */
    public boolean verifPoseCartePileAscAdv(Joueur joueurAdv, Integer carte)
    {
        return carte < joueurAdv.base.getCartePileAsc();
    }

    /**
     *
     * @param joueurAdv
     * @param carte
     */
    public void poseCartePileDescAdv(Joueur joueurAdv, Integer carte)
    {
        int idx = this.main.trvIdxCarte(carte);
        joueurAdv.base.setCartePileDesc(this.main.prendreCarte(idx));
    }

    /**
     *
     * @param joueurAdv
     * @param carte
     * @return
     */
    public boolean verifPoseCartePileDescAdv(Joueur joueurAdv, Integer carte)
    {
        return carte > joueurAdv.base.getCartePileDesc();
    }

    /**
     *
     * @param carte
     * @return
     */
    public boolean aCetteCarteEnMain(Integer carte)
    {
        return this.main.carteExiste(carte);
    }

    /**
     *
     * @return
     */
    public int nbDeCarteEnMain()
    {
        return this.main.getNbCarte();
    }

    /**
     *
     * @return
     */
    public int nbDeCarteDansPioche()
    {
        return this.pioche.getNbCarte();
    }

    /**
     *
     * @return
     */
    public StringBuilder InfoMainJoueurToString()
    {
        StringBuilder s = new StringBuilder("cartes ");
        s.append(nom);
        s.append(" ");

        return s.append(main.mainToString());
    }

    /**
     *
     * @return
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
     *
     * @return
     */
    public NomJoueur getNom()
    {
        return this.nom;
    }

    public Integer getPile(char Base)
    {
        assert (Base == 'v' || Base == '^');

        if (Base == 'v')
            return this.base.getCartePileDesc();
        else if (Base == '^')
            return this.base.getCartePileAsc();

        return 0;
    }

    public Integer getCarte(int idx)
    {
        return this.main.getCarte(idx);
    }

    /**
     *
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
     *
     */
    public void remplirMain()
    {
       if (this.pioche.getNbCarte() > 0) {
           this.main.setCarte(this.pioche.prendreCarte());
           this.main.setCarte(this.pioche.prendreCarte());
       }
    }
}