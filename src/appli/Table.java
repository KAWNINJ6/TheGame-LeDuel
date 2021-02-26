package appli;

import java.util.ArrayList;
import java.util.Scanner;

public class Table {

    private final Joueur j1 = new Joueur();
    private final Joueur j2 = new Joueur();

    private ArrayList<String> coutJoué = new ArrayList<>();
    private ArrayList<Integer> carteJoué = new ArrayList<>();

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
        else {
            for (int cpt = 0; cpt <= this.coutJoué.size() - 1; ++cpt){
                String str = this.coutJoué.get(cpt);
                Integer cout = this.carteJoué.get(cpt);

                for (int cptRev = cpt + 1; cptRev < this.coutJoué.size(); ++cptRev) {
                    String strSuiv = this.coutJoué.get(cptRev);
                    Integer coutSuiv = carteJoué.get(cptRev);

                    if (strSuiv.equals(str)) {
                        return false;
                    }
                    else if ((strSuiv.contains("'")) && str.contains("'")) {
                        return false;
                    }
                    else if (str.contains("v") && (cout < coutSuiv) && !(str.contains("'") || strSuiv.contains("^"))){
                        return false;
                    }
                    else if (str.contains("^") && (cout > coutSuiv) && !(str.contains("'") || strSuiv.contains("v"))){
                        return false;
                    }
                }
            }
            return verifSemantique(joueur,nbCarte, joueurAdv);
        }
    }

    /**
     *
     * @param joueur
     * @param nbCarte
     * @param joueurAdv
     * @return
     */
    private boolean verifSemantique(Joueur joueur,int nbCarte, Joueur joueurAdv)
    {
        boolean poserAdv = false;
        boolean poserCarte = false;

        for (int cpt = 0; cpt <= this.coutJoué.size() - 1; ++cpt) {
            String str = this.coutJoué.get(cpt);
            Integer cout = this.carteJoué.get(cpt);

            if (str.contains("v'"))
            {
                if (!joueur.verifPoseCartePileDescAdv(joueurAdv, cout)) {
                    return false;
                }else {
                    joueur.poseCartePileDescAdv(joueurAdv, cout);
                    poserAdv = true;
                }
            }
            else if (str.contains("^'"))
            {
                if (!joueur.verifPoseCartePileAscAdv(joueurAdv, cout)) {
                    return false;
                } else {
                    joueur.poseCartePileAscAdv(joueurAdv, cout);
                    poserAdv = true;
                }
            }
            else if (str.contains("^") && !str.contains("'") && (joueur.verifPoseCartePileAsc(cout)))
            {
                joueur.poseCartePileAsc(cout);
                poserCarte = true;
            }
            else if (str.contains("v") && !str.contains("'") && (joueur.verifPoseCartePileDesc(cout)))
            {
                joueur.poseCartePileDesc(cout);
                poserCarte = true;
            }
        }
        if (!verifCartePosées(joueur, nbCarte)) {
            return false;
        }
        else if (poserAdv) {
                joueur.remplirMainComplet();
            }
            else {
            joueur.remplirMain();
        }
        return true;
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
     */
    public void afficherInfoj1()
    {
        System.out.println(this.j1.afficherInfoJoueur());
        System.out.println(this.j2.afficherInfoJoueur());
        System.out.println(this.j1.afficherInfoMainJoueur());
    }

    /**
     *
     */
    public void afficherInfoj2()
    {
        System.out.println(this.j1.afficherInfoJoueur());
        System.out.println(this.j2.afficherInfoJoueur());
        System.out.println(this.j2.afficherInfoMainJoueur());
    }

    /**
     *
     * @return
     */
    public boolean verifDefaiteJ1()
    {
        int pileAsc = j1.getPile('^');
        int pileDesc = j1.getPile('v');
        int cpt = 0;

        for (int i = 0; i < this.j1.nbDeCarteEnMain(); ++i) {
            int carte = this.j1.getCarte(i);

            if (cpt < 2) {
                if (pileAsc < carte) {
                    pileAsc = carte;
                    ++cpt;
                } else {
                    if (pileDesc > carte) {
                        pileDesc = carte;
                        ++cpt;
                    }
                }
            }
            else{
                return true;
            }
        }

        pileAsc = j2.getPile('^');
        pileDesc = j2.getPile('v');

        for (int i = 0; i < this.j1.nbDeCarteEnMain(); ++i) {
            int carte = this.j1.getCarte(i);

            if (cpt < 2) {
                if (pileAsc < carte) {
                    pileAsc = carte;
                    ++cpt;
                } else {
                    if (pileDesc > carte) {
                        pileDesc = carte;
                        ++cpt;
                    }
                }
            }
            else{
                return true;
            }
        }

        return cpt >= 2;
    }

    /**
     *
     * @return
     */
    public boolean verifDefaiteJ2()
    {
        int pileAsc = j2.getPile('^');
        int pileDesc = j2.getPile('v');
        int cpt = 0;

        for (int i = 0; i < this.j2.nbDeCarteEnMain(); ++i) {
            int carte = this.j2.getCarte(i);

            if (cpt < 2) {
                if (pileAsc < carte) {
                    pileAsc = carte;
                    ++cpt;
                } else {
                    if (pileDesc > carte) {
                        pileDesc = carte;
                        ++cpt;
                    }
                }
            }
            else{
                return true;
            }
        }

        pileAsc = j1.getPile('^');
        pileDesc = j1.getPile('v');

        for (int i = 0; i < this.j2.nbDeCarteEnMain(); ++i) {
            int carte = this.j2.getCarte(i);

            if (cpt < 2) {
                if (pileAsc < carte) {
                    pileAsc = carte;
                    ++cpt;
                } else {
                    if (pileDesc > carte) {
                        pileDesc = carte;
                        ++cpt;
                    }
                }
            }
            else{
                return true;
            }
        }

        return cpt >= 2;
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
}