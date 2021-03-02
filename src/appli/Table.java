package appli;

import java.util.ArrayList;
import java.util.Scanner;

public class Table {
    /** premier joueur */
    private final Joueur j1 = new Joueur();
    /** deuxieme joueur */
    private final Joueur j2 = new Joueur();
    /** cout joué par les joueurs */
    private final ArrayList<String> coutJouées = new ArrayList<>();
    /** carte joué par les joueurs */
    private final ArrayList<Integer> carteJouées = new ArrayList<>();

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
        décompose(s, j1);
        if (!verifSyntaxique(j1, j2)){
            supEntrées();
            while (!verifSyntaxique(j1, j2)) {
                supEntrées();
                if (!verifSyntaxique(j1, j2)) {
                    System.out.print("#> ");
                    s = sc.nextLine();
                    décompose(s, this.j1);
                }
            }
        }
        System.out.println(cartesJouéToString(j1, nbCarte));
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
        décompose(s, j2);
        if (!verifSyntaxique(j2, j1)){
            supEntrées();
            while (!verifSyntaxique(j2, j1)) {
                supEntrées();
                if (!verifSyntaxique(j2, j1)) {
                    System.out.print("#> ");
                    s = sc.nextLine();
                    décompose(s, j2);
                }
            }
        }
        System.out.println(cartesJouéToString(j2, nbCarte));
    }

    /**
     *
     */
    private void supEntrées()
    {
        this.carteJouées.clear();
        this.coutJouées.clear();
    }

    /**
     *
     * @param s
     * @param joueur
     */
    private void décompose(String s, Joueur joueur)
    {
        Scanner scs = new Scanner(s);

        while (scs.hasNext()) {

            String coup = scs.next();
            try {

                if (joueur.aCetteCarteEnMain(Integer.parseInt(coup.substring(0, 2), 10))) {

                    if (coup.charAt(2) == 'v') {

                        if (coup.length() > 3 && coup.charAt(3) == '\'') {
                            coutJouées.add(coup.substring(0, 4));
                            carteJouées.add(Integer.parseInt(coup.substring(0, 2), 10));
                        }
                        else {
                            coutJouées.add(coup.substring(0, 3));
                            carteJouées.add(Integer.parseInt(coup.substring(0, 2), 10));
                        }

                    } else if (coup.charAt(2) == '^') {

                        if (coup.length() > 3 && coup.charAt(3) == '\'') {
                            coutJouées.add(coup.substring(0, 4));
                            carteJouées.add(Integer.parseInt(coup.substring(0, 2), 10));
                        }
                        else {
                            coutJouées.add(coup.substring(0, 3));
                            carteJouées.add(Integer.parseInt(coup.substring(0, 2), 10));
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
     * @param joueurAdv
     * @return
     */
    private boolean verifSyntaxique(Joueur joueur, Joueur joueurAdv)
    {
        if (this.coutJouées.size() < 2){
            return false;
        }
        else if (verifDoublon() && verifAsc(joueur) && verifDesc(joueur) && verifPoseAdv(joueur, joueurAdv)){
            verifSemantique(joueur, joueurAdv);
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
        String cout = this.coutJouées.get(0);
        Integer carte = this.carteJouées.get(0);

        for (int i = 1; i <= coutJouées.size() - 1 ; ++i) {
            String coutSuiv = this.coutJouées.get(i);
            Integer carteSuiv = this.carteJouées.get(i);

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
        for (int i = 0; i <= this.coutJouées.size() - 1; ++i) {
            String cout = this.coutJouées.get(i);

            if (cout.contains("^") && !cout.contains("'")) {
                Integer carteAsc = this.carteJouées.get(i);

                if (!joueur.verifPoseCartePileAsc(carteAsc)) {
                    return false;
                } else {
                    for (int j = i+1; j <= this.coutJouées.size() - 1; ++j) {
                        Integer newCarteAsc = verifCoutSpec('^', carteAsc, joueur);
                        String coutAscSuiv = this.coutJouées.get(j);
                        Integer carteAscSuiv = this.carteJouées.get(j);

                        if (coutAscSuiv.contains("^") && !coutAscSuiv.contains("'")) {
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
        for (int i = 0; i <= this.coutJouées.size() - 1; ++i) {
            String cout = this.coutJouées.get(i);

            if (cout.contains("v") && !cout.contains("'")) {
                Integer carteDesc = this.carteJouées.get(i);

                if (!joueur.verifPoseCartePileAsc(carteDesc)) {
                    return false;
                } else {
                    for (int j = i+1; j <= this.coutJouées.size() - 1; ++j) {
                        Integer newCarteDesc = verifCoutSpec('v', carteDesc, joueur);
                        String coutDescSuiv = this.coutJouées.get(j);
                        Integer carteDescSuiv = this.carteJouées.get(j);

                        if (coutDescSuiv.contains("v") && !coutDescSuiv.contains("'")) {
                            if (verifCoutSpec('v', newCarteDesc, joueur) < verifCoutSpec('v', carteDescSuiv, joueur)) {
                                if (!verifCoutSpec2('v', newCarteDesc, carteDescSuiv, joueur)) {
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

        for (int i = 0; i <= this.coutJouées.size() - 1 ; ++i) {
            String cout = this.coutJouées.get(i);
            Integer carte = this.carteJouées.get(i);

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
     * @param joueurAdv
     */
    private void verifSemantique(Joueur joueur, Joueur joueurAdv)
    {
        boolean poserAdv = false;

        for (int cpt = 0; cpt <= this.coutJouées.size() - 1; ++cpt) {
            String str = this.coutJouées.get(cpt);
            Integer cout = this.carteJouées.get(cpt);

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
    private StringBuilder cartesJouéToString(Joueur joueur, Integer nbCarte)
    {
        StringBuilder s = new StringBuilder();
        s.append(this.carteJouées.size()).append(" cartes posées, ");

        return s.append(joueur.nbDeCarteEnMain() - (nbCarte - this.carteJouées.size())).append(" cartes piochées");
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
        Integer carteEnMain;

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
    public StringBuilder infoJ1ToString()
    {
        StringBuilder s = new StringBuilder();
        s.append(this.j1.InfoJoueurToSring()).append(System.lineSeparator());
        s.append(this.j2.InfoJoueurToSring()).append(System.lineSeparator());
        return s.append(this.j1.InfoMainJoueurToString());
    }

    /**
     *
     */
    public StringBuilder infoJ2ToString()
    {
        StringBuilder s = new StringBuilder();
        s.append(this.j1.InfoJoueurToSring()).append(System.lineSeparator());
        s.append(this.j2.InfoJoueurToSring()).append(System.lineSeparator());
        return s.append(this.j2.InfoMainJoueurToString());
    }
}