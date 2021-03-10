package appli;

/**
 *
 *
 * @author Sellam Zakaria
 * @author
 * @version
 * */
public class Application {
    public static void main(String[] args) {
        Table jeux = new Table();

        while (true) {
            System.out.println(jeux.infoJ1ToString());
            jeux.lectureEntrerJ1();

            if (jeux.verifVictoireJ1()) {
                System.out.println(jeux.infoJ2ToString());
                System.out.println(jeux.afficherVictoireJ1());
                break;
            }

            if (!jeux.verifDefaiteJ1() && jeux.verifDefaiteJ2()) {
                System.out.println(jeux.infoJ2ToString());
                jeux.lectureEntrerJ2();

                if(!jeux.verifDefaiteJ2()) {
                    System.out.println(jeux.afficherVictoireJ1());
                } else {
                    System.out.println(jeux.infoJ1ToString());
                    System.out.println(jeux.afficherVictoireJ2());
                }
                break;
            }

            System.out.println(jeux.infoJ2ToString());
            jeux.lectureEntrerJ2();

            if (jeux.verifVictoireJ2()) {
                System.out.println(jeux.infoJ1ToString());
                System.out.println(jeux.afficherVictoireJ2());
                break;
            }

            if (!jeux.verifDefaiteJ2() && jeux.verifDefaiteJ1()) {
                System.out.println(jeux.infoJ1ToString());
                jeux.lectureEntrerJ1();

                if(!jeux.verifDefaiteJ1()) {
                    System.out.println(jeux.afficherVictoireJ2());
                } else {
                    System.out.println(jeux.infoJ2ToString());
                    System.out.println(jeux.afficherVictoireJ1());
                }
                break;
            }
        }
    }
}