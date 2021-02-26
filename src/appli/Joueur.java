package appli;

enum NomJoueur
{
    NORD, SUD
}

public class Joueur {

    private final Cartes pioche = new Cartes();
    private final Base base = new Base(this.pioche);
    private final Main main = new Main(this.pioche);
    private NomJoueur nom;

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
        int idx = this.main.trvCarte(carte);
        this.base.setPileAsc(this.main.getMain(idx));
    }

    public boolean verifPoseCartePileAsc(Integer carte)
    {
        return carte > this.base.getPileAsc() || carte-10 == this.base.getPileAsc();
    }

    public void poseCartePileDesc(Integer carte)
    {
        int idx = this.main.trvCarte(carte);
        this.base.setPileDesc(this.main.getMain(idx));
    }

    public boolean verifPoseCartePileDesc(Integer carte)
    {
        return carte < this.base.getPileDesc() || carte+10 == this.base.getPileDesc();
    }

    public void poseCartePileAscAdv(Joueur joueurAdv, Integer carte)
    {
        int idx = this.main.trvCarte(carte);
        joueurAdv.base.setPileAsc(this.main.getMain(idx));
    }

    public boolean verifPoseCartePileAscAdv(Joueur joueurAdv, Integer carte)
    {
        return carte < joueurAdv.base.getPileAsc();
    }

    public void poseCartePileDescAdv(Joueur joueurAdv, Integer carte)
    {
        int idx = this.main.trvCarte(carte);
        joueurAdv.base.setPileDesc(this.main.getMain(idx));
    }

    public boolean verifPoseCartePileDescAdv(Joueur joueurAdv, Integer carte)
    {
        return carte > joueurAdv.base.getPileDesc();
    }

    public boolean aCetteCarteEnMain(Integer carte)
    {
        return this.main.carteExiste(carte);
    }

    public int nbDeCarteEnMain()
    {
        return this.main.getNbCarte();
    }

    public int nbDeCarteDansPioche()
    {
        return this.pioche.getNbCarte();
    }

    public StringBuilder afficherInfoMainJoueur()
    {
        StringBuilder s = new StringBuilder("cartes ");
        s.append(nom);
        s.append(" ");

        return s.append(main.afficherMain());
    }

    public StringBuilder afficherInfoJoueur()
    {
        StringBuilder s = new StringBuilder();

        s.append(this.nom);
        if (this.nom == NomJoueur.SUD)
            s.append(" ");
        s.append(" ").append(this.base.afficherPileAsc());
        s.append(" ").append(this.base.afficherPileDesc());
        s.append(" ");
        s.append("(m").append(main.getNbCarte());
        s.append("p").append(pioche.getNbCarte());

        return s.append(")");
    }

    public NomJoueur getNom() {
        return this.nom;
    }

    public Integer getPile(char Base)
    {
        assert (Base == 'v' || Base == '^');

        if (Base == 'v')
            return this.base.getPileDesc();
        else if (Base == '^')
            return this.base.getPileAsc();

        return 0;
    }

    public Integer getCarte(int idx)
    {
        return this.main.getCarte(idx);
    }

    public void remplirMainComplet()
    {
        while (this.main.getNbCarte() < 6) {
            this.main.setMain(this.pioche.piocherCarte());
        }
    }

    public void remplirMain()
    {
        this.main.setMain(this.pioche.piocherCarte());
        this.main.setMain(this.pioche.piocherCarte());
    }
}