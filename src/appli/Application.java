package appli;

public class Application {
    public static void main(String[] args) {


        /*
        Cartes c = new Cartes();
        System.out.println(c.afficher());
        c.piocher();
        System.out.println(c.afficher());
        */

        /*
        Pile p1 = new Pile();
        Main m1 = new Main();

        Integer i1 = 12;
        Integer i2 = 13;

        p1.setPileAsc(i1);
        System.out.println(p1.afficherPileAsc());
        System.out.println(p1.afficherPileDesc());

        m1.setMain(i1);
        m1.setMain(i2);
        m1.afficherMain();
         */

        /*
        Cartes c1 = new Cartes();
        Main m1 = new Main(c1);
        m1.afficherMain();
        c1.afficher();
        */

        /*
        Cartes c1 = new Cartes();
        c1.afficher();
        */

        /*
        Piles p1 = new Piles(c1);
        System.out.println(p1.afficherPileAsc());
        System.out.println(p1.afficherPileDesc());
        */

        /*
        Joueur j1 = new Joueur();

        j1.afficherInfoJoueur();
        */

        /*
        Cartes c1 = new Cartes();
        Mains m1 = new Mains(c1);
        Joueurs j1 = new Joueurs();
        System.out.println(j1.getNom());
        Joueurs j2 = new Joueurs();
        System.out.println(j2.getNom());
        Joueurs j3 = new Joueurs();
        System.out.println(j3.getNom());
        Joueurs j4 = new Joueurs();
        System.out.println(j4.getNom());
        */

        /*
        Joueurs j1 = new Joueurs();
        System.out.println(j1.afficherInfoJoueur());
        Joueurs j2 = new Joueurs();
        System.out.println(j2.afficherInfoJoueur());
        */

        /*
        Joueurs j1 = new Joueurs();
        Joueurs j2 = new Joueurs();

        System.out.println(j1.afficherInfoJoueur());
        System.out.println(j2.afficherInfoJoueur());
        System.out.println(j1.afficherInfoMainJoueur());

        Integer i1 = 22;
        j1.poserCartePileAsc(i1);

        System.out.println(j1.afficherInfoJoueur());
        System.out.println(j2.afficherInfoJoueur());
        System.out.println(j1.afficherInfoMainJoueur());

        Integer i2 = 12;
        j1.poserCartePileAsc(i2);

        System.out.println(j1.afficherInfoJoueur());
        System.out.println(j2.afficherInfoJoueur());
        System.out.println(j1.afficherInfoMainJoueur());
        */

        /*
        Table t1 = new Table();

        t1.afficherInfoj1();
        t1.lectureEntrerJ1();

        t1.afficherInfoj2();
        t1.lectureEntrerJ2();

        t1.afficherInfoj1();
         */

        Table jeux = new Table();

        while (jeux.verifDefaiteJ1() || jeux.verifVictoireJ1()){

            jeux.afficherInfoj1();
            jeux.lectureEntrerJ1();
            if((!jeux.verifDefaiteJ1() || jeux.verifVictoireJ1())){
                jeux.afficherInfoj2();
                jeux.afficherVictoireJ2();
                break;
            }

            jeux.afficherInfoj2();
            jeux.lectureEntrerJ2();
            if((!jeux.verifDefaiteJ2() || jeux.verifVictoireJ2())){
                jeux.afficherInfoj1();
                jeux.afficherVictoireJ1();
                break;
            }

        }
    }

}