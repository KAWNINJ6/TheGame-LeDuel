package appli;

public class Application {
    public static void main(String[] args) {

        Table jeux = new Table();

        while (jeux.verifDefaiteJ1() || jeux.verifVictoireJ1()) {

            System.out.println(jeux.infoJ1ToString());
            jeux.lectureEntrerJ1();
            if(!jeux.verifDefaiteJ1() || jeux.verifVictoireJ1()) {
                System.out.println(jeux.infoJ2ToString());
                jeux.afficherVictoireJ2();
                break;
            }

            System.out.println(jeux.infoJ2ToString());
            jeux.lectureEntrerJ2();
            if(!jeux.verifDefaiteJ2() || jeux.verifVictoireJ2()) {
                System.out.println(jeux.infoJ1ToString());
                jeux.afficherVictoireJ1();
                break;
            }

        }

    }

}