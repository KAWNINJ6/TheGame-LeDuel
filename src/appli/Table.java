package appli;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * La classe table est la piece maitresse du programme, c'est elle qui agence la semantique du jeu.
 * Sur cette table on instancie les joueurs et on lit les coups joués par l'utilisateur.
 * Ces coups sont soumis a une verification strict en raccord avec les regles du jeu.
 * */
public class Table {
    /** coup joué par les joueurs */
    private ArrayList<String> coupJouees = new ArrayList<>();
    /** carte joué par les joueurs */
    private ArrayList<Integer> carteJouees = new ArrayList<>();

    /**
     * Getter de l'attribut coupJouees
     *
     * @return      renvoie la ArrayList des coupJouees
     */
    public ArrayList<String> getCoupJouees() {
        return coupJouees;
    }

    /**
     * Supprime les coupJouées et carteJouées sur la table.
     */
    public void supEntrees()
    {
        this.carteJouees.clear();
        this.coupJouees.clear();
    }

    /**
     * Décompose les coups du joueur verifie si les 2 premiers entier du coup forme bien une carte,
     * cette carte est disponible dans la main du joueur, le charactère suivant est composé de
     * 'v' ou '^' et le coup de taille 3. Si le coup est de taille 4 alors il verifie si le caractère
     * est bien '\''.
     *
     * Puis si aucune anomalies détectées, alors les coups et la cartes sont stockés.
     *
     * Sinon appelle la methode supEntrées().
     *
     * @param entreesJoueur         la chaine de caractéres des entrées Joueur
     * @param joueur                le joueur en question
     */
    public void decompose(String entreesJoueur, Joueur joueur)
    {
        Scanner scs = new Scanner(entreesJoueur);

        while (scs.hasNext()) {
            String coup = scs.next();

            try {
                if (joueur.aCetteCarteEnMain(Integer.parseInt(coup.substring(0, 2), 10))) {
                    if (coup.charAt(2) == 'v') {
                        if (coup.length()==4 && coup.charAt(3) == '\'') {
                            coupJouees.add(coup.substring(0, 4));
                            carteJouees.add(Integer.parseInt(coup.substring(0, 2), 10));
                        }
                        else if (coup.length() == 3) {
                            coupJouees.add(coup.substring(0, 3));
                            carteJouees.add(Integer.parseInt(coup.substring(0, 2), 10));
                        }

                    } else if (coup.charAt(2) == '^') {
                        if (coup.length()==4 && coup.charAt(3) == '\'') {
                            coupJouees.add(coup.substring(0, 4));
                            carteJouees.add(Integer.parseInt(coup.substring(0, 2), 10));
                        }
                        else if (coup.length() == 3) {
                            coupJouees.add(coup.substring(0, 3));
                            carteJouees.add(Integer.parseInt(coup.substring(0, 2), 10));
                        }
                    } else {
                        supEntrees();
                    }
                } else {
                    supEntrees();
                }
            } catch (NumberFormatException | StringIndexOutOfBoundsException ignored) {
                supEntrees();
            }
        }
        scs.close();
    }

    /**
     * Verifie si minimum 2 coups sont jouées.
     *
     * Verifie la validité de tous les coups et cartes jouées par le joueur.
     * (En cas de doublon, mouvaise pose Ascendant, Descendante
     * et en cas d'anomalie si joueur pose contre l'adversaire)
     *
     * Si tous est bon pose les cartes dans les bonnes piles.
     *
     * @param joueur        le joueur actuel (qui joue)
     * @param joueurAdv     le joueur adverse (ne joue pas)
     * @return              si les coups/cartes jouées sont tous valident
     */
    public boolean verifSyntaxique(Joueur joueur, Joueur joueurAdv)
    {
        if (this.coupJouees.size() < 2){
            return false;
        }
        else if (!verifDoublon() && verifAsc(joueur) && verifDesc(joueur) && verifPoseAdv(joueur, joueurAdv)) {
            poserCartesDansBases(joueur, joueurAdv);
            return true;
        }
        return false;
    }

    /**
     * Recupère le premier coup ainsi que la première carte,
     * puis les compare avec les coups et cartes suivantes.
     *
     * @return              si les coups || cartes joués ne comprennent pas des doublons
     */
    private boolean verifDoublon()
    {
        String coup = this.coupJouees.get(0);
        Integer carte = this.carteJouees.get(0);

        for (int i = 1; i <= coupJouees.size() - 1; ++i) {
            String coupSuiv = this.coupJouees.get(i);
            Integer carteSuiv = this.carteJouees.get(i);

            if ((coup.contains("'") && coupSuiv.contains("'")) || carte.equals(carteSuiv)){
                return true;
            }
        }
        return false;
    }

    /**
     * Verifie que tout les coups indiqués comme ascendant par le joueur sont croissant
     *
     * @param joueur        le joueur
     * @return              les coups sont croissant ou non
     */
    private boolean verifAsc(Joueur joueur)
    {
        ArrayList<Integer> cartesAsc = new ArrayList<>();

        for (int i = 0; i <= this.coupJouees.size() - 1; ++i) {
            if (this.coupJouees.get(i).contains("^") && !this.coupJouees.get(i).contains("'")) {
                cartesAsc.add(this.carteJouees.get(i));
            }
        }
        for (int i = 0; i < cartesAsc.size() - 1; ++i) {

            if (i < 1 && !joueur.verifPoseCartePileAsc(cartesAsc.get(0))) {
                return false;
            }
            else if (cartesAsc.size() >= 1) {
                if (cartesAsc.get(i) < cartesAsc.get(i+1) || cartesAsc.get(i)-10 == cartesAsc.get(i+1)) { }
                else {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Verifie que tout les coups indiqués comme descendant par le joueur sont decroissant
     *
     * @param joueur        le joueur
     * @return              les coups sont decroissant ou non
     */
    private boolean verifDesc(Joueur joueur)
    {
        ArrayList<Integer> cartesDesc = new ArrayList<>();

        for (int i = 0; i <= this.coupJouees.size() - 1; ++i) {
            if (this.coupJouees.get(i).contains("v") && !this.coupJouees.get(i).contains("'")) {
                cartesDesc.add(this.carteJouees.get(i));
            }
        }
        for (int i = 0; i < cartesDesc.size() - 1; ++i) {

            if (i < 1 && !joueur.verifPoseCartePileDesc(cartesDesc.get(0))) {
                return false;
            }
            else if (cartesDesc.size() >= 1) {
                if (cartesDesc.get(i) > cartesDesc.get(i+1) || cartesDesc.get(i)+10 == cartesDesc.get(i+1)) { }
                else {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Verifie pour un coup donnée chez l'adversaire (avec un "\'") si le coup est jouable.
     *
     * @param joueur        Le joueur
     * @param joueurAdv     le joueur adverse
     * @return              si le coup est jouable ou non
     */
    private boolean verifPoseAdv(Joueur joueur, Joueur joueurAdv)
    {

        for (int i = 0; i <= this.coupJouees.size() - 1 ; ++i) {
            String coup = this.coupJouees.get(i);
            Integer carte = this.carteJouees.get(i);

            if (coup.contains("v'") && !joueur.verifPoseCartePileDescAdv(joueurAdv, carte)) {
                return false;
            }
            else if (coup.contains("^'") && !joueur.verifPoseCartePileAscAdv(joueurAdv, carte)) {
                return false;
            }
        }
        return true;
    }

    /**
     * pose pour un coup joué toutes les cartes dans les bases respectivement designées
     * (base perso comme adverse)
     * /!\ les coups sont supposés valides (la semantique est bonne)
     *
     * @param joueur        le joueur
     * @param joueurAdv     le joueur adverse
     */
    private void poserCartesDansBases(Joueur joueur, Joueur joueurAdv)
    {
        boolean poserAdv = false;

        for (int cpt = 0; cpt <= this.coupJouees.size() - 1; ++cpt) {
            String str = this.coupJouees.get(cpt);
            Integer coup = this.carteJouees.get(cpt);

            if (str.contains("v'"))
            {
                joueur.poseCartePileDescAdv(joueurAdv, coup);
                poserAdv = true;
            }
            else if (str.contains("^'"))
            {
                joueur.poseCartePileAscAdv(joueurAdv, coup);
                poserAdv = true;
            }
            else if (str.contains("^") && !str.contains("'"))
            {
                joueur.poseCartePileAsc(coup);
            }
            else if (str.contains("v") && !str.contains("'"))
            {
                joueur.poseCartePileDesc(coup);
            }
        }
        distributionDeCarte(joueur, poserAdv);  // pioche apres coup
    }

    /**
     * S'occuper de la pioche apres un coup joué
     * si un coup a ete joué chez l'adversaire la main se remplie
     * sinon deux cartes sont piochées
     *
     * @param joueur        le joueur
     * @param poserAdv      si une carte a été jouée chez l'adversaire
     */
    private void distributionDeCarte(Joueur joueur, boolean poserAdv)
    {
        if (poserAdv) {
            joueur.remplirMainComplet();
        } else {
            joueur.remplirMain();
        }
    }

    /**
     * Crée une chaine de caractere avec le nombre de carte posée et piochée.
     * exemple : "2 cartes posées, 4 cartes piochées"
     *
     * @param joueur        le joueur qui joue
     * @param nbCarte       le nombre de carte initiale du joueur
     * @return              la chaine de caractères
     */
    public String cartesJoueToString(Joueur joueur, Integer nbCarte)
    {
        return this.carteJouees.size() + " cartes posées, "
                + (joueur.nbDeCarteEnMain() - (nbCarte - this.carteJouees.size()))
                + " cartes piochées";
    }

    /**
     * Cette methode verifie tous les coups possibles selon les cartes en main du joueur
     * les regles du jeu demande qu'il y ait au moins deux coups jouer pour un tour
     * donc si l'algo trouve deux coups alors la partie continue sinon elle se termine.
     *
     * @return              si le joueur peut jouer 2 cartes
     */
    public boolean verifDefaite(Joueur joueur, Joueur joueurAdv)
    {
        int cptPossible = 0;
        Integer carteEnMain;
        boolean coupAdv = false;

        for (int i = 0; i <= joueur.nbDeCarteEnMain()-1; ++i) {
            carteEnMain = joueur.getCarteDansMain(i);

            if (joueur.verifPoseCartePileDesc(carteEnMain)) {
                cptPossible+=verifDefaiteSpec(joueur, i, 'v');
            }
            else if (joueur.verifPoseCartePileDescAdv(joueurAdv, carteEnMain) && !coupAdv) {
                ++cptPossible;
                coupAdv = true;
            }
        }

        for (int i = joueur.nbDeCarteEnMain()-1; i >= 0; --i) {
            carteEnMain = joueur.getCarteDansMain(i);

            if (joueur.verifPoseCartePileAsc(carteEnMain)) {
                cptPossible+=verifDefaiteSpec(joueur, i, '^');
            }
            else if (joueur.verifPoseCartePileAscAdv(joueurAdv, carteEnMain) && !coupAdv) {
                ++cptPossible;
                coupAdv = true;
            }
        }
        return cptPossible >= 2;
    }

    /**
     * Incremente de 1 le nombre de coup possible
     * Incremente à nouveau le compteur de coup possible si la carte suivante :
     *
     * Dans le cas la base est demandé est ASC, alors compare la carte à l'indice donné
     * avec la carte precedente décrementé de 10 et verifie qu'ils sont égaux. (Donc posable)
     *
     * Dans le cas la base est demandé est DESC, alors compare la carte à l'indice donné
     * avec la carte suivante incrémenté de 10 et verifie qu'ils sont égaux. (Donc posable)
     *
     * Par exemple : le joueur a {18, 28} est que sa base est composé de ^[58] v[08]
     *               dans ce cas 18 et 28 sont posable.
     *
     * @param joueur            le joueur qui joue
     * @param idx               indice de la carte choisie
     * @param base              la base choisie ("v" ou "^")
     * @return                  le compteur incrementé
     */
    private int verifDefaiteSpec(Joueur joueur, int idx, char base)
    {
        Integer carteEnMain = joueur.getCarteDansMain(idx);
        Integer carteEnMainSuiv;
        int cptPossible = 1;

        if (base == 'v' && idx < joueur.nbDeCarteEnMain()-1) {
            carteEnMainSuiv = joueur.getCarteDansMain(idx+1);
            carteEnMain+=10;

            if (carteEnMain.equals(carteEnMainSuiv)) {
                return cptPossible + 1;
            }
        }
        else if (base == '^' && idx > 0) {
            carteEnMainSuiv = joueur.getCarteDansMain(idx-1);
            carteEnMainSuiv-=10;

            if (carteEnMain.equals(carteEnMainSuiv)) {
                return cptPossible + 1;
            }
        }
        return cptPossible;
    }

    /**
     * Verifie les conditions de victoire, c'est a dire que le joueur
     * qui joue n'a plus de carte en main et dans sa pioche.
     *
     * @return              si le joueur a gagné
     */
    public boolean verifVictoire(Joueur joueur)
    {
        return (joueur.nbDeCarteEnMain() == 0 && joueur.nbDeCarteDansPioche() == 0);
    }

    /**
     * Renvoie la chaine de caractere contenant le message de fin
     * exemple : "partie finie, SUD a gagné"
     *
     * @return          la chaine de caractères
     */
    public String victoireToString(Joueur joueur)
    {
        return "partie finie, " + joueur.getNom() + " a gagné";
    }

    /**
     * regroupe et renvoie toutes les informations demandées lors du tour d'un joueur
     * exemple "NORD ^[17] v[60] (m6p48)
     *          SUD ^[10] v[52] (m6p50)
     *          cartes SUD { 17 20 25 32 35 50 }"
     *
     * @return          la chaine de caractères
     */
    public String infoJoueurToString(Joueur joueur, Joueur joueurAdv, char tours)
    {
        assert (tours == '1' || tours == '2');

        if (tours == '1') {
            return joueur.InfoJoueurToSring() + System.lineSeparator()
                    + joueurAdv.InfoJoueurToSring() + System.lineSeparator()
                    + joueur.InfoMainJoueurToString();
        } else {
            return joueurAdv.InfoJoueurToSring() + System.lineSeparator()
                    + joueur.InfoJoueurToSring() + System.lineSeparator()
                    + joueur.InfoMainJoueurToString();
        }
    }
}