package appli;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * La classe table est la piece maitresse du programme, c'est elle qui agence la semantique du jeu.
 * Sur cette table on instancie les joueurs et on lit les coups joués par l'utilisateur.
 * Ces coups sont soumis a une verification strict en raccord avec les regles du jeu.
 *
 * @author Sellam Zakaria
 * @author
 * @version
 * */
public class Table {
    /** premier joueur (NORD) */
    private Joueur j1 = new Joueur();
    /** deuxieme joueur (SUD) */
    private Joueur j2 = new Joueur();
    /** coup joué par les joueurs */
    private ArrayList<String> coupJouées = new ArrayList<>();
    /** carte joué par les joueurs */
    private ArrayList<Integer> carteJouées = new ArrayList<>();

    /**
     * Lis les entrées du premier joueur (NORD) et applique différente vérification
     * Récupère le nombres de cartes du joueur dans sa main avant de jouer
     * Affiche les cartes jouées et cartes piochées
     * Si la verification ne passe pas, la table est nettoyé
     * et la saisie est relancé avec "#>".
     */
    public void lectureEntrerJ1()
    {
        Scanner sc = new Scanner(System.in);
        String entréesJ1;
        int nbCarte = this.j1.nbDeCarteEnMain();

        System.out.print("> ");
        supEntrées();
        entréesJ1 = sc.nextLine();
        décompose(entréesJ1, this.j1);
        if (!verifSyntaxique(this.j1, this.j2)) {
            supEntrées();
            while (!verifSyntaxique(this.j1, this.j2)) {
                supEntrées();
                if (!verifSyntaxique(this.j1, this.j2)) {
                    System.out.print("#> ");
                    entréesJ1 = sc.nextLine();
                    décompose(entréesJ1, this.j1);
                }
            }
        }
        System.out.println(cartesJouéToString(this.j1, nbCarte));
    }

    /**
     * Lis les entrées du deuxième joueur (SUD) et applique différente vérification
     * Récupère le nombres de cartes du joueur dans sa main avant de jouer
     * Affiche les cartes jouées et cartes piochées.
     * Si la verification ne passe pas, la table est nettoyé
     * et la saisie est relancé avec "#>".
     */
    public void lectureEntrerJ2()
    {
        Scanner sc = new Scanner(System.in);
        String entréesJ2;
        int nbCarte = this.j2.nbDeCarteEnMain();

        System.out.print("> ");
        supEntrées();
        entréesJ2 = sc.nextLine();
        décompose(entréesJ2, this.j2);
        if (!verifSyntaxique(this.j2, this.j1)){
            supEntrées();
            while (!verifSyntaxique(this.j2, this.j1)) {
                supEntrées();
                if (!verifSyntaxique(this.j2, this.j1)) {
                    System.out.print("#> ");
                    entréesJ2 = sc.nextLine();
                    décompose(entréesJ2, this.j2);
                }
            }
        }
        System.out.println(cartesJouéToString(this.j2, nbCarte));
    }

    /**
     * Supprime les coupJouées et carteJouées sur la table.
     */
    private void supEntrées()
    {
        this.carteJouées.clear();
        this.coupJouées.clear();
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
     * @param entréesJoueur         la chaine de caractéres des entrées Joueur
     * @param joueur                le joueur en question
     */
    private void décompose(String entréesJoueur, Joueur joueur)
    {
        Scanner scs = new Scanner(entréesJoueur);

        while (scs.hasNext()) {
            String coup = scs.next();

            try {
                if (joueur.aCetteCarteEnMain(Integer.parseInt(coup.substring(0, 2), 10))) {
                    if (coup.charAt(2) == 'v') {
                        if (coup.length()==4 && coup.charAt(3) == '\'') {
                            coupJouées.add(coup.substring(0, 4));
                            carteJouées.add(Integer.parseInt(coup.substring(0, 2), 10));
                        }
                        else if (coup.length() == 3) {
                            coupJouées.add(coup.substring(0, 3));
                            carteJouées.add(Integer.parseInt(coup.substring(0, 2), 10));
                        }

                    } else if (coup.charAt(2) == '^') {
                        if (coup.length()==4 && coup.charAt(3) == '\'') {
                            coupJouées.add(coup.substring(0, 4));
                            carteJouées.add(Integer.parseInt(coup.substring(0, 2), 10));
                        }
                        else if (coup.length() == 3) {
                            coupJouées.add(coup.substring(0, 3));
                            carteJouées.add(Integer.parseInt(coup.substring(0, 2), 10));
                        }
                    } else {
                        supEntrées();
                    }
                } else {
                    supEntrées();
                }
            } catch (NumberFormatException | StringIndexOutOfBoundsException ignored) { }
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
    private boolean verifSyntaxique(Joueur joueur, Joueur joueurAdv)
    {
        if (this.coupJouées.size() < 2){
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
        String coup = this.coupJouées.get(0);
        Integer carte = this.carteJouées.get(0);

        for (int i = 1; i <= coupJouées.size() - 1; ++i) {
            String coupSuiv = this.coupJouées.get(i);
            Integer carteSuiv = this.carteJouées.get(i);

            if ((coup.contains("'") && coupSuiv.contains("'")) || carte.equals(carteSuiv)){
                return true;
            }
        }
        return false;
    }

    /**
     * Verifie que tout les coups indiqués comme ascendant par le joueur sont croissant
     * En cas de coups speciaux (voir méthodes), compare la copie de la carte avec les
     * autres.
     *
     * @param joueur
     * @return              les coups sont croissant ou non
     * @see                 Table#verifCoupSpec(char, Integer, Joueur)
     * @see                 Table#verifCoupSpec2(char, Integer, Integer, Joueur)
     */
    private boolean verifAsc(Joueur joueur)
    {
        for (int i = 0; i <= this.coupJouées.size() - 1; ++i) {
            String coup = this.coupJouées.get(i); // coup

            if (coup.contains("^") && !coup.contains("'")) { // verification que le coup est ascendant
                Integer carteAsc = this.carteJouées.get(i); // premier carte ascendant

                if (!joueur.verifPoseCartePileAsc(carteAsc)) {  // regarde si la premiere carte asc est jouable
                    return false;
                } else {
                    for (int j = i + 1; j <= this.coupJouées.size() - 1; ++j) { // regarde les autres coups
                        Integer newCarteAsc = verifCoupSpec('^', carteAsc, joueur); // voir méthode verifCoupSpec
                        String coupAscSuiv = this.coupJouées.get(j);
                        Integer carteAscSuiv = this.carteJouées.get(j);

                        if (coupAscSuiv.contains("^") && !coupAscSuiv.contains("'")) {
                            if (verifCoupSpec('^',newCarteAsc, joueur) > verifCoupSpec('^', carteAscSuiv, joueur)) {
                                if (!verifCoupSpec2('^', newCarteAsc, carteAscSuiv, joueur)) {
                                    return false;
                                }
                            }
                        }
                    }
                    break;
                }
            }
        }
        return true;
    }

    /**
     * Verifie que tout les coups indiqués comme descendant par le joueur sont decroissant
     * En cas de coups speciaux (voir méthodes), compare la copie de la carte avec les
     * autres.
     *
     * @param joueur        le joueur
     * @return              les coups sont decroissant ou non
     * @see                 Table#verifCoupSpec(char, Integer, Joueur)
     * @see                 Table#verifCoupSpec2(char, Integer, Integer, Joueur)
     */
    private boolean verifDesc(Joueur joueur)
    {
        for (int i = 0; i <= this.coupJouées.size() - 1; ++i) {
            String coup = this.coupJouées.get(i);

            if (coup.contains("v") && !coup.contains("'")) {
                Integer carteDesc = this.carteJouées.get(i);

                if (!joueur.verifPoseCartePileAsc(carteDesc)) {
                    return false;
                } else {
                    for (int j = i + 1; j <= this.coupJouées.size() - 1; ++j) {
                        Integer newCarteDesc = verifCoupSpec('v', carteDesc, joueur);
                        String coupDescSuiv = this.coupJouées.get(j);
                        Integer carteDescSuiv = this.carteJouées.get(j);

                        if (coupDescSuiv.contains("v") && !coupDescSuiv.contains("'")) {
                            if (verifCoupSpec('v', newCarteDesc, joueur) < verifCoupSpec('v', carteDescSuiv, joueur)) {
                                if (!verifCoupSpec2('v', newCarteDesc, carteDescSuiv, joueur)) {
                                    return false;
                                }
                            }
                        }
                    }
                    break;
                }
            }
        }
        return true;
    }

    /**
     *
     * @param base
     * @param carte
     * @param joueur
     * @return
     */
    private Integer verifCoupSpec(char base, Integer carte, Joueur joueur)
    {
        Integer newCarte = carte;

        if (base == 'v') {
            newCarte = carte-10;
            if (newCarte.equals(joueur.getPileDansBase('v'))){
                return newCarte;
            }
        }
        else if (base == '^') {
            newCarte = carte+10;
            if (newCarte.equals(joueur.getPileDansBase('^'))){
                return newCarte;
            }
        }
        return carte;
    }

    /**
     *
     * @param base
     * @param carte
     * @param carteSuiv
     * @param joueur
     * @return
     */
    private boolean verifCoupSpec2(char base, Integer carte, Integer carteSuiv, Joueur joueur)
    {
        Integer newCarte;

        if (base == 'v' && joueur.getPileDansBase('v') == 60) {
            newCarte = carte+10;
            return newCarte.equals(carteSuiv);
        }
        else if (base == '^' && joueur.getPileDansBase('^') == 1) {
            newCarte = carte-10;
            return newCarte.equals(carteSuiv);
        }
        return false;
    }

    /**
     * Verifie pour un coup donnée chez l'adversaire (avec un "\'") si le coup est jouable.
     *
     * @param joueur        Le joueur
     * @param joueurAdv     le joueur adverse
     * @return              si le coup est jouable ou non
     */
    private boolean verifPoseAdv(Joueur joueur, Joueur joueurAdv) {

        for (int i = 0; i <= this.coupJouées.size() - 1 ; ++i) {
            String coup = this.coupJouées.get(i);
            Integer carte = this.carteJouées.get(i);

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

        for (int cpt = 0; cpt <= this.coupJouées.size() - 1; ++cpt) {
            String str = this.coupJouées.get(cpt);
            Integer coup = this.carteJouées.get(cpt);

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
    private StringBuilder cartesJouéToString(Joueur joueur, Integer nbCarte)
    {
        StringBuilder s = new StringBuilder();
        s.append(this.carteJouées.size()).append(" cartes posées, ");

        return s.append(joueur.nbDeCarteEnMain() - (nbCarte - this.carteJouées.size())).append(" cartes piochées");
    }

    /**
     * Cette methode verifie tous les coups possibles selon les cartes en main du joueur
     * les regles du jeu demande qu'il y ait au moins deux coups jouer pour un tour
     * donc si l'algo trouve deux coups alors la partie continue sinon elle se termine.
     * (Pour le joueur NORD)
     *
     * @return              si le joueur NORD peut jouer 2 cartes
     */
    public boolean verifDefaiteJ1()
    {
        int cptPossible = 0;
        Integer carteEnMain;
        boolean coupAdv = false;

        for (int i = 0; i <= this.j1.nbDeCarteEnMain()-1; ++i) {
            carteEnMain = this.j1.getCarteDansMain(i);

            if (this.j1.verifPoseCartePileDesc(carteEnMain)) {
                cptPossible+=verifDefaiteSpec(this.j1, i, 'v');
            }
            else if (this.j1.verifPoseCartePileDescAdv(this.j2, carteEnMain) && !coupAdv) {
                ++cptPossible;
                coupAdv = true;
            }
        }

        for (int i = this.j1.nbDeCarteEnMain()-1; i >= 0; --i) {
            carteEnMain = this.j1.getCarteDansMain(i);

            if (this.j1.verifPoseCartePileAsc(carteEnMain)) {
                cptPossible+=verifDefaiteSpec(this.j1, i, '^');
            }
            else if (this.j1.verifPoseCartePileAscAdv(this.j2, carteEnMain) && !coupAdv) {
                ++cptPossible;
                coupAdv = true;
            }
        }
        return cptPossible >= 2;
    }

    /**
     * Cette methode verifie tous les coups possibles selon les cartes en main du joueur
     * les regles du jeu demande qu'il y ait au moins deux coups jouer pour un tour
     * donc si l'algo trouve deux coups alors la partie continue sinon elle se termine.
     * (Pour le joueur SUD)
     *
     * @return              si le joueur SUD peut jouer 2 cartes
     */
    public boolean verifDefaiteJ2()
    {
        int cptPossible = 0;
        Integer carteEnMain;
        boolean coupAdv = false;

        for (int i = 0; i <= this.j2.nbDeCarteEnMain()-1; ++i) {
            carteEnMain = this.j2.getCarteDansMain(i);

            if (this.j2.verifPoseCartePileDesc(carteEnMain)) {
                cptPossible+=verifDefaiteSpec(this.j2, i, 'v');
            }
            else if (this.j2.verifPoseCartePileDescAdv(this.j1, carteEnMain) && !coupAdv) {
                ++cptPossible;
                coupAdv = true;
            }
        }

        for (int i = this.j2.nbDeCarteEnMain()-1; i >= 0; --i) {
            carteEnMain = this.j2.getCarteDansMain(i);

            if (this.j2.verifPoseCartePileAsc(carteEnMain)) {
                cptPossible+=verifDefaiteSpec(this.j2, i, '^');
            }
            else if (this.j2.verifPoseCartePileAscAdv(this.j1, carteEnMain) && !coupAdv)  {
                ++cptPossible;
                coupAdv = true;
            }
        }
        return cptPossible >= 2;
    }

    /**
     * Incremente le compteur de coup possible pour une carte donnée si la carte suivante est égale.
     *
     * @param joueur            le joueur qui joue
     * @param idx               indice de la carte choisie
     * @param base              la base choisie ("v" ou "^"
     * @return                  le compteur incrementé ou non
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
     * (pour le joueur NORD)
     *
     * @return              si le joueur NORD a gagné
     */
    public boolean verifVictoireJ1()
    {
        return (this.j1.nbDeCarteEnMain() == 0 && this.j1.nbDeCarteDansPioche() == 0);
    }

    /**
     * Verifie les conditions de victoire, c'est a dire que le joueur
     * qui joue n'a plus de carte en main et dans sa pioche.
     * (pour le joueur SUD)
     *
     * @return              si le joueur SUD n'a plus de cartes
     */
    public boolean verifVictoireJ2()
    {
        return (this.j2.nbDeCarteEnMain() == 0 && this.j2.nbDeCarteDansPioche() == 0);
    }

    /**
     * Renvoie la chaine de caractere contenant le message de fin
     * exemple : "partie finie, SUD a gagné"
     * (pour NORD)
     *
     * @return          la chaine de caractères
     */
    public String afficherVictoireJ1()
    {
        return "partie finie, " + this.j1.getNom() + " a gagné";
    }

    /**
     * Renvoie la chaine de caractere contenant le message de fin
     * exemple : "partie finie, SUD a gagné"
     * (pour SUD)
     *
     * @return          la chaine de caractères
     */
    public String afficherVictoireJ2()
    {
        return "partie finie, " + this.j1.getNom() + " a gagné";
    }

    /**
     * regroupe et renvoie toutes les informations demandées lors du tour d'un joueur
     * exemple "NORD ^[17] v[60] (m6p48)
     *          SUD ^[10] v[52] (m6p50)
     *          cartes SUD { 17 20 25 32 35 50 }"
     * (Pour NORD)
     *
     * @return          la chaine de caractères
     */
    public String infoJ1ToString()
    {
        return this.j1.InfoJoueurToSring() + System.lineSeparator()
                + this.j2.InfoJoueurToSring() + System.lineSeparator()
                + this.j1.InfoMainJoueurToString();
    }

    /**
     * regroupe et renvoie toutes les informations demandées lors du tour d'un joueur
     * exemple "NORD ^[17] v[60] (m6p48)
     *          SUD ^[10] v[52] (m6p50)
     *          cartes SUD { 17 20 25 32 35 50 }"
     * (Pour SUD)
     *
     * @return          la chaine de caractères
     */
    public String infoJ2ToString()
    {
        return this.j1.InfoJoueurToSring() + System.lineSeparator()
                + this.j2.InfoJoueurToSring() + System.lineSeparator()
                + this.j2.InfoMainJoueurToString();
    }
}

