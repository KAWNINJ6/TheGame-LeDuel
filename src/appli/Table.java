package appli;

import java.util.ArrayList;
import java.util.Scanner;

public class Table {
    /** premier joueur */
    private final Joueur j1 = new Joueur();
    /** deuxieme joueur */
    private final Joueur j2 = new Joueur();
    /** cout joué par les joueurs */
    private final ArrayList<String> coutJoué = new ArrayList<>();
    /** carte joué par les joueurs */
    private final ArrayList<Integer> carteJoué = new ArrayList<>();

    /**
     *
     */
    public void lectureEntrerJ1()
    {
        Scanner sc = new Scanner(System.in);
        String s;
        int nbCarte = this.j1.nbDeCarteEnMain();

        System.out.print("> ");
        supEntrées();
        s = sc.nextLine();
        décompose(s, j1, j2);
        if (!verifSyntaxique(j1, nbCarte, j2)){
            supEntrées();
            while (!verifSyntaxique(j1, nbCarte, j2)) {
                supEntrées();
                if (!verifSyntaxique(j1, nbCarte, j2)) {
                    System.out.print("#> ");
                    s = sc.nextLine();
                    décompose(s, this.j1, this.j2);
                }
            }
        }
        System.out.println(afficherCarteJoué(j1, nbCarte));
    }

    /**
     *
     */
    public void lectureEntrerJ2()
    {
        Scanner sc = new Scanner(System.in);
        String s;
        int nbCarte = this.j2.nbDeCarteEnMain();

        System.out.print("> ");
        supEntrées();
        s = sc.nextLine();
        décompose(s, j2, j1);
        if (!verifSyntaxique(j2, nbCarte, j1)){
            supEntrées();
            while (!verifSyntaxique(j2, nbCarte, j1)) {
                supEntrées();
                if (!verifSyntaxique(j2, nbCarte, j1)) {
                    System.out.print("#> ");
                    s = sc.nextLine();
                    décompose(s, this.j2, this.j1);
                }
            }
        }
        System.out.println(afficherCarteJoué(j2, nbCarte));
    }

    /**
     *
     */
    private void supEntrées()
    {
        this.carteJoué.clear();
        this.coutJoué.clear();
    }

    /**
     *
     * @param s
     * @param joueur
     * @param joueurAdv
     */
    private void décompose(String s, Joueur joueur, Joueur joueurAdv)
    {
        Scanner scs = new Scanner(s);

        while (scs.hasNext()) {

            String coup = scs.next();
            try {

                if (joueur.aCetteCarteEnMain(Integer.parseInt(coup.substring(0, 2), 10))) {

                    if (coup.charAt(2) == 'v') {

                        if (coup.length() > 3 && coup.charAt(3) == '\'') {
                            coutJoué.add(coup.substring(0, 4));
                            carteJoué.add(Integer.parseInt(coup.substring(0, 2), 10));
                        }
                        else {
                            coutJoué.add(coup.substring(0, 3));
                            carteJoué.add(Integer.parseInt(coup.substring(0, 2), 10));
                        }

                    } else if (coup.charAt(2) == '^') {

                        if (coup.length() > 3 && coup.charAt(3) == '\'') {
                            coutJoué.add(coup.substring(0, 4));
                            carteJoué.add(Integer.parseInt(coup.substring(0, 2), 10));
                        }
                        else {
                            coutJoué.add(coup.substring(0, 3));
                            carteJoué.add(Integer.parseInt(coup.substring(0, 2), 10));
                        }

                    }
                    else {
                        supEntrées();
                    }
                }
                else {
                    supEntrées();
                }

            } catch (NumberFormatException | StringIndexOutOfBoundsException ignored) { }
        }scs.close();
    }

    /**
     *
     * @param joueur
     * @param nbCarte
     * @param joueurAdv
     * @return
     */
    private boolean verifSyntaxique(Joueur joueur, int nbCarte, Joueur joueurAdv)
    {
        if (this.coutJoué.size() < 2){
            return false;
        }
        else if (verifDoublon() && verifAsc(joueur) && verifDesc(joueur) && verifPoseAdv(joueur, joueurAdv)){
            verifSemantique(joueur,nbCarte, joueurAdv);
            return true;
        }
        return false;
    }

    /**
     *
     * @return
     */
    private boolean verifDoublon()
    {
        String cout = this.coutJoué.get(0);
        Integer carte = this.carteJoué.get(0);

        for (int i = 1; i <= coutJoué.size() - 1 ; ++i) {
            String coutSuiv = this.coutJoué.get(i);
            Integer carteSuiv = this.carteJoué.get(i);

            if ((cout.contains("'") && coutSuiv.contains("'")) || carte.equals(carteSuiv)){
                return false;
            }
        }

        return true;
    }

    /**
     *
     * @param joueur
     * @return
     */
    private boolean verifAsc(Joueur joueur)
    {
        for (int i = 0; i <= this.coutJoué.size() - 1; ++i) {
            String cout = this.coutJoué.get(i);
            Integer carte = this.carteJoué.get(i);

            if (cout.contains("^") && !cout.contains("'")) {
                String coutAsc = this.coutJoué.get(i);
                Integer carteAsc = this.carteJoué.get(i);

                if (!joueur.verifPoseCartePileAsc(carteAsc)) {
                    return false;
                } else {
                    for (int j = i+1; j < this.coutJoué.size(); ++j) {
                        Integer newCarteAsc = verifCoutSpec('^', carteAsc, joueur);
                        String coutAscSuiv = this.coutJoué.get(j);
                        Integer carteAscSuiv = this.carteJoué.get(j);

                        if (cout.contains("^") && !cout.contains("'")) {
                            if (verifCoutSpec('^', newCarteAsc, joueur) > verifCoutSpec('^', carteAscSuiv, joueur)) {
                                if (!verifCoutSpec2('^', newCarteAsc, carteAscSuiv, joueur)) {
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
     * @param joueur
     * @return
     */
    private boolean verifDesc(Joueur joueur)
    {
        for (int i = 0; i <= this.coutJoué.size() - 1; ++i) {
            String cout = this.coutJoué.get(i);
            Integer carte = this.carteJoué.get(i);

            if (cout.contains("v") && !cout.contains("'")) {
                String coutAsc = this.coutJoué.get(i);
                Integer carteAsc = this.carteJoué.get(i);

                if (!joueur.verifPoseCartePileAsc(carteAsc)) {
                    return false;
                } else {
                    for (int j = i+1; j < this.coutJoué.size(); ++j) {
                        Integer newCarteAsc = verifCoutSpec('v', carteAsc, joueur);
                        String coutAscSuiv = this.coutJoué.get(j);
                        Integer carteAscSuiv = this.carteJoué.get(j);

                        if (cout.contains("v") && !cout.contains("'")) {
                            if (verifCoutSpec('v', newCarteAsc, joueur) < verifCoutSpec('v', carteAscSuiv, joueur)) {
                                if (!verifCoutSpec2('v', newCarteAsc, carteAscSuiv, joueur)) {
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
     * @param joueur
     * @param joueurAdv
     * @return
     */
    private boolean verifPoseAdv(Joueur joueur, Joueur joueurAdv) {

        for (int i = 0; i <= this.coutJoué.size() - 1 ; ++i) {
            String cout = this.coutJoué.get(i);
            Integer carte = this.carteJoué.get(i);

            if (cout.contains("v'") && !joueur.verifPoseCartePileDescAdv(joueurAdv, carte)) {
                return false;
            }
            else if (cout.contains("^'") && !joueur.verifPoseCartePileAscAdv(joueurAdv, carte)) {
                return false;
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
    private Integer verifCoutSpec(char base, Integer carte, Joueur joueur)
    {
        Integer newCarte = carte;

        if (base == 'v') {
            newCarte = carte-10;
            if (newCarte.equals(joueur.getPile('v'))){
                return newCarte;
            }
        }
        else if (base == '^') {
            newCarte = carte+10;
            if (newCarte.equals(joueur.getPile('^'))){
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
    private boolean verifCoutSpec2(char base, Integer carte,Integer carteSuiv, Joueur joueur)
    {
        Integer newCarte = carte;

        if (base == 'v' && joueur.getPile('v') == 60) {
            newCarte = carte+10;
            return newCarte.equals(carteSuiv);
        }
        else if (base == '^' && joueur.getPile('^') == 1) {
            newCarte = carte-10;
            return newCarte.equals(carteSuiv);
        }
        return false;
    }

    /**
     *
     * @param joueur
     * @param nbCarte
     * @param joueurAdv
     * @return
     */
    private void verifSemantique(Joueur joueur, int nbCarte, Joueur joueurAdv)
    {
        boolean poserAdv = false;

        for (int cpt = 0; cpt <= this.coutJoué.size() - 1; ++cpt) {
            String str = this.coutJoué.get(cpt);
            Integer cout = this.carteJoué.get(cpt);

            if (str.contains("v'"))
            {
                joueur.poseCartePileDescAdv(joueurAdv, cout);
                poserAdv = true;
            }
            else if (str.contains("^'"))
            {
                joueur.poseCartePileAscAdv(joueurAdv, cout);
                poserAdv = true;
            }
            else if (str.contains("^") && !str.contains("'"))
            {
                joueur.poseCartePileAsc(cout);
            }
            else if (str.contains("v") && !str.contains("'"))
            {
                joueur.poseCartePileDesc(cout);
            }
        }
        distributionDeCarte(joueur, poserAdv);
    }

    /**
     *
     * @param joueur
     * @param poserAdv
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
     *
     * @param joueur
     * @param nbCarte
     * @return
     */
    private boolean verifCartePosées(Joueur joueur, Integer nbCarte)
    {
        return joueur.nbDeCarteEnMain() <= nbCarte - 2 || joueur.nbDeCarteEnMain() == 0;
    }

    /**
     *
     * @param joueur
     * @param nbCarte
     * @return
     */
    private StringBuilder afficherCarteJoué(Joueur joueur, Integer nbCarte)
    {
        StringBuilder s = new StringBuilder();
        s.append(this.carteJoué.size()).append(" cartes posées, ");

        return s.append(joueur.nbDeCarteEnMain() - (nbCarte - this.carteJoué.size())).append(" cartes piochées");
    }

    /**
     *
     * @return
     */
    public boolean verifDefaiteJ1()
    {
        int cptPossible = 0;
        Integer carteEnMain;

        for (int i = 0; i <= this.j1.nbDeCarteEnMain()-1; ++i) {
            carteEnMain = this.j1.getCarte(i);

            if (this.j1.verifPoseCartePileAsc(carteEnMain)) {
                ++cptPossible;
            }
            else if (this.j1.verifPoseCartePileDesc(carteEnMain)) {
                ++cptPossible;
            }
            else if (this.j1.verifPoseCartePileAscAdv(this.j2, carteEnMain)) {
                ++cptPossible;
            }
            else if (this.j1.verifPoseCartePileDescAdv(this.j2, carteEnMain)) {
                ++cptPossible;
            }
        }

        return cptPossible >= 2;
    }

    /**
     *
     * @return
     */
    public boolean verifDefaiteJ2()
    {
        int cptPossible = 0;
        Integer carteEnMain = 0;

        for (int i = 0; i <= this.j2.nbDeCarteEnMain()-1; ++i) {
            carteEnMain = this.j2.getCarte(i);

            if (this.j2.verifPoseCartePileAsc(carteEnMain)) {
                ++cptPossible;
            }
            else if (this.j2.verifPoseCartePileDesc(carteEnMain)) {
                ++cptPossible;
            }
            else if (this.j2.verifPoseCartePileAscAdv(this.j1, carteEnMain)) {
                ++cptPossible;
            }
            else if (this.j2.verifPoseCartePileDescAdv(this.j1, carteEnMain)) {
                ++cptPossible;
            }
        }

        return cptPossible >= 2;
    }

    /**
     *
     * @return
     */
    public boolean verifVictoireJ1()
    {
        return (this.j1.nbDeCarteEnMain() == 0 && this.j1.nbDeCarteDansPioche() == 0);
    }

    /**
     *
     * @return
     */
    public boolean verifVictoireJ2()
    {
        return (this.j2.nbDeCarteEnMain() == 0 && this.j2.nbDeCarteDansPioche() == 0);
    }

    /**
     *
     */
    public void afficherVictoireJ1()
    {
        System.out.println("partie finie, " + this.j1.getNom() + " a gagné");
    }

    /**
     *
     */
    public void afficherVictoireJ2()
    {
        System.out.println("partie finie, " + this.j2.getNom() + " a gagné");
    }

    /**
     *
     */
    public void afficherInfoj1()
    {
        System.out.println(this.j1.InfoJoueurToSring());
        System.out.println(this.j2.InfoJoueurToSring());
        System.out.println(this.j1.InfoMainJoueurToString());
    }

    /**
     *
     */
    public void afficherInfoj2()
    {
        System.out.println(this.j1.InfoJoueurToSring());
        System.out.println(this.j2.InfoJoueurToSring());
        System.out.println(this.j2.InfoMainJoueurToString());
    }
}